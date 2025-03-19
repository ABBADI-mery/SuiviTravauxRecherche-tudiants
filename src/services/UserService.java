/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import beans.User;
import connexion.Connexion;
import dao.IUserDao;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Random;
import java.util.Base64;
import javax.swing.JOptionPane;

/**
 *
 * @author pc
 */
public class UserService implements IUserDao {

    private Connexion connexion;

    public UserService() {
        connexion = Connexion.getInstance();
    }

    @Override
    public boolean addUser(User user) {
        String req = "INSERT INTO user (login, password, securityQuestion, securityAnswer) VALUES (?, SHA1(?), ?, SHA1(?))";
        try {
            PreparedStatement ps = connexion.getCn().prepareStatement(req);
            ps.setString(1, user.getLogin());
            ps.setString(2, user.getPassword()); // Mot de passe en clair
            ps.setString(3, user.getSecurityQuestion());
            ps.setString(4, user.getSecurityAnswer()); // Réponse secrète en clair
            ps.executeUpdate();
            return true;
        } catch (SQLException ex) {
            System.out.println("Erreur lors de l'ajout de l'utilisateur : " + ex.getMessage());
        }
        return false;
    }

    @Override
    public User findUserByLogin(String login) {
        String req = "SELECT * FROM user WHERE login = ?";
        try {
            PreparedStatement ps = connexion.getCn().prepareStatement(req);
            ps.setString(1, login);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new User(
                        rs.getString("login"),
                        rs.getString("password"),
                        rs.getString("securityQuestion"),
                        rs.getString("securityAnswer")
                );
            }
        } catch (SQLException ex) {
            System.out.println("Erreur lors de la recherche de l'utilisateur : " + ex.getMessage());
        }
        return null;
    }

    @Override
    public boolean authenticate(String login, String password) {
        String req = "SELECT * FROM user WHERE login = ? AND password = SHA1(?)";
        try {
            PreparedStatement ps = connexion.getCn().prepareStatement(req);
            ps.setString(1, login);
            ps.setString(2, password); // Mot de passe en clair
            ResultSet rs = ps.executeQuery();
            return rs.next();
        } catch (SQLException ex) {
            System.out.println("Erreur lors de l'authentification : " + ex.getMessage());
        }
        return false;
    }

    public boolean resetPasswordBySecurityQuestion(String login, String securityAnswer) {
        try {
            User user = findUserByLogin(login);
            if (user != null) {
                String req = "SELECT * FROM user WHERE login = ? AND securityAnswer = SHA1(?)";
                PreparedStatement ps = connexion.getCn().prepareStatement(req);
                ps.setString(1, login);
                ps.setString(2, securityAnswer); // Réponse secrète en clair
                ResultSet rs = ps.executeQuery();
                if (rs.next()) {
                    String newPassword = generateTemporaryPassword();
                    if (updatePassword(login, newPassword)) {
                        JOptionPane.showMessageDialog(null, "Votre nouveau mot de passe est : " + newPassword);
                        return true;
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Réponse secrète incorrecte.");
                }
            } else {
                JOptionPane.showMessageDialog(null, "Utilisateur introuvable.");
            }
        } catch (SQLException e) {
            System.out.println("Erreur : " + e.getMessage());
        }
        return false;
    }

    public boolean updatePassword(String login, String newPassword) {
        String query = "UPDATE user SET password = SHA1(?) WHERE login = ?";
        try {
            PreparedStatement pstmt = connexion.getCn().prepareStatement(query);
            pstmt.setString(1, newPassword); // Nouveau mot de passe en clair
            pstmt.setString(2, login);
            int rowsUpdated = pstmt.executeUpdate();
            return rowsUpdated > 0;
        } catch (SQLException e) {
            System.out.println("Erreur lors de la mise à jour du mot de passe : " + e.getMessage());
        }
        return false;
    }

    private String hashPassword(String password) throws NoSuchAlgorithmException {
        MessageDigest digest = MessageDigest.getInstance("SHA1");
        byte[] hash = digest.digest(password.getBytes());
        return Base64.getEncoder().encodeToString(hash);
    }

    public String generateTemporaryPassword() {
        String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        Random random = new Random();
        StringBuilder sb = new StringBuilder(12);
        for (int i = 0; i < 12; i++) {
            sb.append(chars.charAt(random.nextInt(chars.length())));
        }
        return sb.toString();
    }
}
