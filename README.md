Suivi des Travaux de Recherche des Étudiants
Le Suivi des Travaux de Recherche des Étudiants est un système permettant de gérer et de suivre les travaux de recherche réalisés par les étudiants sous la supervision d'un professeur.

Fonctionnalités du Système:
1.Ajouter un travail de recherche

Un administrateur ou un professeur peut ajouter un nouveau travail de recherche en renseignant son titre, sa description et sa période (date de début et date de fin).

2.Associer un étudiant et un professeur à un travail de recherche

Une fois un travail ajouté, il peut être attribué à un étudiant, et un professeur responsable sera désigné pour l'encadrement.

3.Filtrer par professeur

Un filtre permet d’afficher uniquement les travaux supervisés par un professeur donné, ce qui facilite la gestion du suivi des recherches.

4.Rechercher un travail par titre

Une fonctionnalité de recherche permet de retrouver rapidement un travail de recherche en tapant son titre ou une partie du titre.
 
 
 Structure de la Base de Données:
 Ce système repose sur trois tables principales :

1.TravailRecherche : Cette table contient les informations sur les travaux de recherche, notamment l'id, le titre, la description, la date de début et la date de fin.
2.Étudiant : Cette table stocke les informations des étudiants, avec un id, un nom, un prénom et un email.
3.EncadrementRecherche : Cette table fait le lien entre les travaux de recherche, les étudiants et les professeurs.


CREATE TABLE etudiant (
    id INT(11) AUTO_INCREMENT PRIMARY KEY,
    nom VARCHAR(100) NOT NULL,
    prenom VARCHAR(255) NULL,
    email VARCHAR(255) NOT NULL UNIQUE
);
CREATE TABLE travailrecherche (
    id INT(11) AUTO_INCREMENT PRIMARY KEY,
    titre VARCHAR(255) NOT NULL,
    description TEXT NULL,
    dateDebut DATE NULL,
    dateFin DATE NULL
);
CREATE TABLE encadrementrecherche (
    travail_id INT(11) NOT NULL,
    etudiant_id INT(11) NOT NULL,
    professeur VARCHAR(255) NULL,
    PRIMARY KEY (travail_id, etudiant_id),
    FOREIGN KEY (travail_id) REFERENCES travailrecherche(id) ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY (etudiant_id) REFERENCES etudiant(id) ON DELETE CASCADE ON UPDATE CASCADE
);
