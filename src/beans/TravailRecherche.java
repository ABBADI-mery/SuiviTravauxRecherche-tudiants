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
import java.util.Date;

public class TravailRecherche {

    private int id;
    private String titre;
    private String description;
    private Date dateDebut;
    private Date dateFin;

    public TravailRecherche(int id, String titre, String description, Date dateDebut, Date dateFin) {
        this.id = id;
        this.titre = titre;
        this.description = description;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
    }

    public TravailRecherche(String titre, String description, Date dateDebut, Date dateFin) {
        this.titre = titre;
        this.description = description;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getDateDebut() {
        return dateDebut;
    }

    public void setDateDebut(Date dateDebut) {
        this.dateDebut = dateDebut;
    }

    public Date getDateFin() {
        return dateFin;
    }

    public void setDateFin(Date dateFin) {
        this.dateFin = dateFin;
    }

    @Override
    public String toString() {
        return String.valueOf(id);
    }

}
