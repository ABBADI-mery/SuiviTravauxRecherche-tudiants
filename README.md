# Suivi des Travaux de Recherche des Étudiants

## Description

Le Suivi des Travaux de Recherche des Étudiants est un système conçu pour simplifier la gestion et le suivi des travaux de recherche réalisés par les étudiants sous la supervision d'un professeur. Il offre une plateforme centralisée pour organiser les informations, suivre l'avancement et faciliter la communication entre les étudiants et les professeurs.

## Fonctionnalités

* **Ajouter un travail de recherche** : Les administrateurs ou les professeurs peuvent créer de nouveaux projets en spécifiant le titre, la description et la période (dates de début et de fin).
* **Associer un étudiant et un professeur à un travail de recherche** : Une fois un projet créé, il peut être attribué à un étudiant, et un professeur responsable est désigné pour l'encadrement.
* **Filtrer par professeur** : Un filtre permet d'afficher uniquement les travaux supervisés par un professeur spécifique, facilitant ainsi la gestion du suivi.
* **Rechercher un travail par titre** : Une fonctionnalité de recherche permet de trouver rapidement un travail en saisissant son titre ou une partie de celui-ci.

## Structure de la Base de Données

Le système repose sur trois tables principales :

1.  **TravailRecherche** : Informations sur les travaux de recherche (id, titre, description, dates de début et de fin).
2.  **Étudiant** : Informations sur les étudiants (id, nom, prénom, email).
3.  **EncadrementRecherche** : Lien entre les travaux, les étudiants et les professeurs (travail_id, etudiant_id, professeur).

### Schéma de la Base de Données

```sql
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