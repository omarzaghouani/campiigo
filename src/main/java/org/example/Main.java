package org.example;

import entities.UserRole;
import entities.utilisateur;
import services.utilisateurServices;

import java.sql.SQLException;

public class Main {
    public static void main(String[] args) {
        try {
            // 1. Créez une instance de utilisateurServices
            utilisateurServices userService = new utilisateurServices();

            // 2. Créez une instance de utilisateur avec des valeurs appropriées
            utilisateur newUser = new utilisateur("John", "Doe", UserRole.Admin, 123456789, "john.doe@example.com", "password123");

            // 3. Appelez la méthode ajouter avec l'instance de utilisateur
            userService.ajouter(newUser);

            System.out.println("Utilisateur ajouté avec succès. ID généré : " + newUser.getId());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        // Votre logique principale reste inchangée

    }
}
