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
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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
}
