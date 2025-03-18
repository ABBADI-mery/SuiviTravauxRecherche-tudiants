/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

/**
 *
 * @author pc
 */
import beans.EncadrementRecherche;
import beans.Etudiant;
import beans.TravailRecherche;
import beans.User;
import java.util.Date;
import java.util.List;
import services.EncadrementRechercheService;
import services.EtudiantService;
import services.TravailRechercheService;
import services.UserService;

public class Test {

    public static void main(String[] args) {

         TravailRechercheService trs = new TravailRechercheService();
         EtudiantService es = new EtudiantService();
         EncadrementRechercheService ers = new EncadrementRechercheService();

         TravailRecherche travail1 = new TravailRecherche("IA et Big Data", "Recherche sur l'intelligence artificielle", new Date(), new Date());
         TravailRecherche travail2 = new TravailRecherche("Réseaux et Sécurité", "Analyse des attaques cybernétiques", new Date(), new Date());

         trs.create(travail1);
         trs.create(travail2);
         TravailRecherche travail = trs.findById(9);
         if (travail != null) {
         System.out.println("Le travail trouvé : " + travail.getTitre());
         }
         travail.setTitre("Orientation professionnelle");
         trs.update(travail);
         System.out.println("Le titre du travail mis à jour : " + trs.findById(7).getTitre());
         List<TravailRecherche> travails = trs.findAll();
         System.out.println(" La liste des travaux de recherche :");
         for (TravailRecherche t : travails) {
         System.out.println(t.getTitre() + "\t " + t.getDescription() + " \t " + t.getDateDebut() + "\t" + t.getDateFin());
         }
         trs.delete(trs.findById(3));
         Etudiant etudiant1 = new Etudiant("bendrouich", "hiba", "hiba.bendrouich@example.com");
         Etudiant etudiant2 = new Etudiant("enn", "fati", "fati.enn@example.com");

         es.create(etudiant1);
         es.create(etudiant2);
         Etudiant etudiant = es.findById(9);
         if (etudiant != null) {
         System.out.println("Étudiant trouvé : " + etudiant.getNom() + " " + etudiant.getPrenom());
         }
         etudiant.setPrenom("Meryam");
         es.update(etudiant);
         System.out.println("Le prénom de l'étudiant mis à jour : " + es.findById(13).getPrenom());
         List<Etudiant> etudiants = es.findAll();
         System.out.println(" La liste des étudiants :");
         for (Etudiant e : etudiants) {
         System.out.println(e.getNom() + " " + e.getPrenom() + " \t - " + e.getEmail());
         }
         EncadrementRecherche er1 = new EncadrementRecherche(trs.findById(8), es.findById(13), "mrLachgar");
         EncadrementRecherche er2 = new EncadrementRecherche(trs.findById(9), es.findById(14), "mrZahir");
         ers.create(er1);
         ers.create(er2);
         List<EncadrementRecherche> encadrements = ers.findAll();
         for (EncadrementRecherche er : encadrements) {
         System.out.println("Travail ID: " + er.getTravail_id().getId() + ", Etudiant ID: " + er.getEtudiant_id().getId() + ", Professeur: " + er.getProfesseur());
         }

         EncadrementRecherche encadrementSupprime = ers.findAll().get(1);
         ers.delete(encadrementSupprime);
         System.out.println("\nEncadrement supprimé : Travail ID " + encadrementSupprime.getTravail_id().getId()
         + " - Etudiant ID " + encadrementSupprime.getEtudiant_id().getId() + " - Professeur: " + encadrementSupprime.getProfesseur());

         System.out.println("\nEncadrements restants :");
         for (EncadrementRecherche er : ers.findAll()) {
         System.out.println("Travail ID: " + er.getTravail_id().getId() + ", Etudiant ID: " + er.getEtudiant_id().getId() + ", Professeur: " + er.getProfesseur());
         }
    }

}
