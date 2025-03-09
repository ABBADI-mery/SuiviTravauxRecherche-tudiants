/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

/**
 *
 * @author pc
 */
import beans.TravailRecherche;
import connexion.Connexion;
import dao.IDao;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TravailRechercheService implements IDao<TravailRecherche> {

    private Connexion connexion;

    public TravailRechercheService() {
        connexion = Connexion.getInstance();
    }

    @Override
    public boolean create(TravailRecherche o) {
        String req = "INSERT INTO TravailRecherche (id, titre, description, dateDebut, dateFin) VALUES (null, ?, ?, ?, ?)";
        try {
            PreparedStatement ps = connexion.getCn().prepareStatement(req);
            ps.setString(1, o.getTitre());
            ps.setString(2, o.getDescription());
            ps.setDate(3, new java.sql.Date(o.getDateDebut().getTime()));
            ps.setDate(4, new java.sql.Date(o.getDateFin().getTime()));
            ps.executeUpdate();
            return true;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return false;
    }

    @Override
    public boolean delete(TravailRecherche o) {
        String req = "DELETE FROM TravailRecherche WHERE id = ?";
        try {
            PreparedStatement ps = connexion.getCn().prepareStatement(req);
            ps.setInt(1, o.getId());
            ps.executeUpdate();
            return true;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return false;
    }

    @Override
    public boolean update(TravailRecherche o) {
        String req = "UPDATE TravailRecherche SET titre = ?, description = ?, dateDebut = ?, dateFin = ? WHERE id = ?";
        try {
            PreparedStatement ps = connexion.getCn().prepareStatement(req);
            ps.setString(1, o.getTitre());
            ps.setString(2, o.getDescription());
            ps.setDate(3, new java.sql.Date(o.getDateDebut().getTime()));
            ps.setDate(4, new java.sql.Date(o.getDateFin().getTime()));
            ps.setInt(5, o.getId());
            ps.executeUpdate();
            return true;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return false;
    }

    @Override
    public TravailRecherche findById(int id) {
        String req = "SELECT * FROM TravailRecherche WHERE id = ?";
        try {
            PreparedStatement ps = connexion.getCn().prepareStatement(req);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new TravailRecherche(rs.getInt("id"), rs.getString("titre"), rs.getString("description"),
                        rs.getDate("dateDebut"), rs.getDate("dateFin"));
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return null;
    }

    @Override
    public List<TravailRecherche> findAll() {
        List<TravailRecherche> travaux = new ArrayList<>();
        String req = "SELECT * FROM TravailRecherche";
        try {
            PreparedStatement ps = connexion.getCn().prepareStatement(req);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                travaux.add(new TravailRecherche(rs.getInt("id"), rs.getString("titre"), rs.getString("description"),
                        rs.getDate("dateDebut"), rs.getDate("dateFin")));
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return travaux;
    }
}
