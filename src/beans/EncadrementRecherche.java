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

    private TravailRecherche travail;
    private Etudiant etudiant;
    private String professeur;

    public EncadrementRecherche(TravailRecherche travail, Etudiant etudiant, String professeur) {
        this.travail = travail;
        this.etudiant = etudiant;
        this.professeur = professeur;
    }

    public TravailRecherche getTravail() {
        return travail;
    }

    public void setTravail(TravailRecherche travail) {
        this.travail = travail;
    }

    public Etudiant getEtudiant() {
        return etudiant;
    }

    public void setEtudiant(Etudiant etudiant) {
        this.etudiant = etudiant;
    }

    public String getProfesseur() {
        return professeur;
    }

    public void setProfesseur(String professeur) {
        this.professeur = professeur;
    }
}
