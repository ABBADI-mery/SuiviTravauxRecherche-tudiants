/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

/**
 *
 * @author pc
 */
public class EncadrementRecherche {

    private TravailRecherche travail_id;
    private Etudiant etudiant_id;
    private String professeur;

    public EncadrementRecherche(TravailRecherche travail_id, Etudiant etudiant_id, String professeur) {
        this.travail_id = travail_id;
        this.etudiant_id = etudiant_id;
        this.professeur = professeur;
    }

    public TravailRecherche getTravail_id() {
        return travail_id;
    }

    public void setTravail_id(TravailRecherche travail_id) {
        this.travail_id = travail_id;
    }

    public Etudiant getEtudiant_id() {
        return etudiant_id;
    }

    public void setEtudiant_id(Etudiant etudiant_id) {
        this.etudiant_id = etudiant_id;
    }

    public String getProfesseur() {
        return professeur;
    }

    public void setProfesseur(String professeur) {
        this.professeur = professeur;
    }

}
