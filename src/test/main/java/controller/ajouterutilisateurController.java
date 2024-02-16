package controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class ajouterutilisateurController {

    @FXML
    private TextField nomField;

    @FXML
    private TextField prenomField;

    @FXML
    private Button ajouterUtilisateurButton;

    // Ajoutez d'autres champs pour les autres attributs de l'utilisateur si nécessaire

    @FXML
    private void initialize() {
        // Vous pouvez ajouter des initialisations ici si nécessaire
    }

    @FXML
    private void handleAjouterUtilisateur() {
        String nom = nomField.getText();
        String prenom = prenomField.getText();

        // Ajoutez le code pour ajouter l'utilisateur à votre base de données
        // Utilisez votre service d'utilisateur ou toute autre logique appropriée
        // Exemple : utilisateurService.ajouter(new Utilisateur(nom, prenom, ...));

        // Réinitialisez les champs après l'ajout
        nomField.clear();
        prenomField.clear();
        // Réinitialisez les autres champs si nécessaire
    }
}
