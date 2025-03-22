/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import beans.EncadrementRecherche;
import beans.Etudiant;
import beans.TravailRecherche;
import connexion.Connexion;
import dao.IDao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;

/**
 *
 * @author pc
 */
public class EncadrementRechercheService implements IDao<EncadrementRecherche> {

    private Connexion connexion;
    private TravailRechercheService trs;
    private EtudiantService es;

    public EncadrementRechercheService() {
        connexion = Connexion.getInstance();
        trs = new TravailRechercheService();
        es = new EtudiantService();
    }

    @Override
    public boolean create(EncadrementRecherche o) {
        String req = "INSERT INTO EncadrementRecherche (travail_id, etudiant_id, professeur) VALUES (?, ?, ?)";
        try {
            PreparedStatement ps = connexion.getCn().prepareStatement(req);
            ps.setInt(1, o.getTravail_id().getId());
            ps.setInt(2, o.getEtudiant_id().getId());
            ps.setString(3, o.getProfesseur());
            ps.executeUpdate();
            return true;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return false;
    }

    @Override
    public boolean delete(EncadrementRecherche o) {
        String req = "DELETE FROM EncadrementRecherche WHERE travail_id = ? AND etudiant_id = ?";
        try {
            PreparedStatement ps = connexion.getCn().prepareStatement(req);
            ps.setInt(1, o.getTravail_id().getId());
            ps.setInt(2, o.getEtudiant_id().getId());
            ps.executeUpdate();
            return true;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return false;
    }

    @Override
    public boolean update(EncadrementRecherche o) {
        String req = "UPDATE EncadrementRecherche SET professeur = ? WHERE travail_id = ? AND etudiant_id = ?";
        try {
            PreparedStatement ps = connexion.getCn().prepareStatement(req);
            ps.setString(1, o.getProfesseur());
            ps.setInt(2, o.getTravail_id().getId());
            ps.setInt(3, o.getEtudiant_id().getId());
            ps.executeUpdate();
            return true;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return false;
    }

    @Override
    public EncadrementRecherche findById(int id) {
        // Une classe d'association est souvent définie par une clé composée (les identifiants des entités qu'elle relie).une classe d'association n'a généralement pas d'ID propre
        return null;
    }

    @Override
    public List<EncadrementRecherche> findAll() {
        List<EncadrementRecherche> encadrements = new ArrayList<>();
        String req = "SELECT * FROM EncadrementRecherche";
        try {
            PreparedStatement ps = connexion.getCn().prepareStatement(req);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                TravailRecherche travail_id = trs.findById(rs.getInt("travail_id"));
                Etudiant etudiant_id = es.findById(rs.getInt("etudiant_id"));
                String professeur = rs.getString("professeur");
                encadrements.add(new EncadrementRecherche(travail_id, etudiant_id, professeur));
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return encadrements;
    }

    public List<String> getAllProfesseurs() {
        List<String> professeurs = new ArrayList<>();
        String req = "SELECT DISTINCT professeur FROM EncadrementRecherche";
        try (PreparedStatement ps = connexion.getCn().prepareStatement(req);
                ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                professeurs.add(rs.getString("professeur"));
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return professeurs;
    }

    public List<Object[]> getTravauxEtudiantsParProfesseur(String professeur) {
        List<Object[]> resultats = new ArrayList<>();
        String req = "SELECT e.nom, e.prenom, tr.titre "
                + "FROM EncadrementRecherche er "
                + "JOIN Etudiant e ON er.etudiant_id = e.id "
                + "JOIN TravailRecherche tr ON er.travail_id = tr.id "
                + "WHERE er.professeur = ?";

        try (PreparedStatement ps = connexion.getCn().prepareStatement(req)) {
            ps.setString(1, professeur);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Object[] ligne = {rs.getString("nom"), rs.getString("prenom"), rs.getString("titre")};
                resultats.add(ligne);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return resultats;
    }

    public CategoryDataset createDataset() {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        try (Connection connection = connexion.getCn(); // Utilisez la connexion de Connexion
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery("SELECT professeur, COUNT(*) AS nombre_travaux FROM EncadrementRecherche GROUP BY professeur")) {

            while (resultSet.next()) {
                String professeur = resultSet.getString("professeur");
                int nombreTravaux = resultSet.getInt("nombre_travaux");
                dataset.addValue(nombreTravaux, "Travaux", professeur);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return dataset;
    }
}
