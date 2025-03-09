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
import beans.Etudiant;
import services.EtudiantService;
import services.TravailRechercheService;

public class Test {

    public static void main(String[] args) {

        TravailRechercheService trs = new TravailRechercheService();
        EtudiantService es = new EtudiantService();

        //TravailRecherche travail1 = new TravailRecherche("IA et Big Data", "Recherche sur l'intelligence artificielle", new Date(), new Date());
        // TravailRecherche travail2 = new TravailRecherche("Réseaux et Sécurité", "Analyse des attaques cybernétiques", new Date(), new Date());
        // trs.create(travail1);
        // trs.create(travail2);
        Etudiant etudiant1 = new Etudiant("Amine", "Bennani", "amine.bennani@example.com");
        Etudiant etudiant2 = new Etudiant("Sofia", "El Idrissi", "sofia.elidrissi@example.com");
        es.create(etudiant1);
        es.create(etudiant2);

        //es.delete(es.findById(1)); 
        //trs.delete(trs.findById(3));
    }
}
