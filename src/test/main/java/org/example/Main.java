package org.example;

import entities.UserRole;
import entities.utilisateur;
import services.utilisateurServices;
import utils.DataSource;

import java.sql.SQLException;
import java.util.List;

import static java.lang.Enum.*;

public class Main {
    public static void main(String[] args, Class<UserRole> UserRole) {
        // Création d'une instance de DataSource pour la connexion à la base de données
        DataSource ds1 = DataSource.getInstance();

        // Création d'une instance de utilisateurServices
        utilisateurServices ps = new utilisateurServices() {
            @Override
            public int getIdFromObject(Object o) {
                return 0;
            }

            @Override
            public int getId(utilisateur utilisateur) {
                return utilisateur.getId();
            }
        };

        // Création d'un nouvel utilisateur à ajouter dans la base de données
        utilisateur newUser = new utilisateur(84, "chedly", "Hriga", entities.UserRole.Simple_user, 123456789, "Hariga.doe@example.com","nnnnn");

        try {
            // Appel de la fonction ajouter pour ajouter le nouvel utilisateur
            ps.ajouter(newUser);

            // Affichage des utilisateurs après l'ajout
            System.out.println("Utilisateurs après l'ajout :");
            afficherUtilisateurs(ps.afficher());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }



    // Méthode utilitaire pour afficher la liste des utilisateurs
    private static void afficherUtilisateurs(List<utilisateur> utilisateurs) {
        for (utilisateur user : utilisateurs) {
            System.out.println(user);
        }
    }
}
