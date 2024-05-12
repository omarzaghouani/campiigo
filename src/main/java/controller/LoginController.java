package controller;

import entities.Session;
import entities.utilisateur;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import services.utilisateurServices;
import utils.EmailUser;

import java.io.IOException;
import java.sql.SQLException;

public class LoginController {

    public Label resetPasswordLabel;
    @FXML
    private TextField emailTextField;

    @FXML
    private PasswordField passwordTextField;

    @FXML
    private Button loginButton;

    // Méthode pour gérer l'action du bouton de connexion


    private void redirectToAdminInterface(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/AfficherUser.fxml"));
        Parent adminParent = loader.load();
        Scene adminScene = new Scene(adminParent);

        // Get the stage information
        Stage window = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();

        // Set the new scene onto the stage
        window.setScene(adminScene);
        window.show();
    }

    private void redirectToCampOwnerInterface(ActionEvent actionEvent, utilisateur user) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/HomeClient.fxml"));
        Parent adminParent = loader.load();
        HomeClientController controller = loader.getController();
        controller.setUser(user); // Injecter l'utilisateur après le chargement du FXML

        Scene adminScene = new Scene(adminParent);

        // Get the stage information
        Stage window = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();

        // Set the new scene onto the stage
        window.setScene(adminScene);
        window.show();
    }

    private void redirectToSimpleUserInterface(ActionEvent actionEvent, utilisateur user) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/HomeClient.fxml"));
        Parent adminParent = loader.load();
        HomeClientController controller = loader.getController();
        controller.setUser(user); // Injecter l'utilisateur après le chargement du FXML

        Scene adminScene = new Scene(adminParent);

        // Get the stage information
        Stage window = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();

        // Set the new scene onto the stage
        window.setScene(adminScene);
        window.show();
    }

    @FXML
    public void GoToInterface(ActionEvent event) throws IOException {
        utilisateurServices us = new utilisateurServices();
        utilisateur user = us.authentifier(emailTextField.getText(), passwordTextField.getText());

        if (user != null) {
            // Créer une session avec l'utilisateur authentifié
            if (user != null && user.getPhoto_d() != null) {
                // L'utilisateur a été authentifié avec succès
                // Charger l'image de profil de l'utilisateur


                // Stocker l'utilisateur dans la session
                Session session = Session.getInstance();
                session.setUser(user);
                Session.getInstance();
                switch (user.getRole()) {
                    case Admin:
                        // Redirect to the admin interface
                        redirectToAdminInterface(event);
                        break;
                    case Camp_Owner:
                        // Redirect to the camp owner interface
                        redirectToCampOwnerInterface(event, user); // Passer l'utilisateur authentifié
                        break;
                    case Simple_User:
                        // Redirect to the simple user interface
                        redirectToSimpleUserInterface(event, user); // Passer l'utilisateur authentifié
                        break;
                    default:
                        // Handle other roles or unknown roles
                        break;
                }
            } else {
                // Handle authentication failure or null role
                showAlert("Authentication failed. Please check your credentials.", Alert.AlertType.ERROR);
            }
        }
    }


    // Méthodes pour afficher des alertes
    // ...

    public void resetPassword(MouseEvent event) throws SQLException {
        String email = emailTextField.getText();
        if (email.isEmpty()) {
            showAlert("Veuillez entrer votre adresse e-mail.", Alert.AlertType.WARNING);
            return;
        }

        // Générer un mot de passe par défaut
        String defaultPassword = generateDefaultPassword();
        utilisateurServices us = new utilisateurServices();
        utilisateur user = utilisateurServices.getUtilisateurByEmail(email); // Assurez-vous d'avoir cette méthode
        if (user != null) {
            user.setMotDePasse(defaultPassword); // Mettre à jour le mot de passe de l'utilisateur
            us.modifier(user); // Mettre à jour l'utilisateur dans la base de données
        } else {
            showAlert("L'utilisateur avec l'adresse e-mail " + email + " n'a pas été trouvé.", Alert.AlertType.ERROR);
        }


        // Envoyer un e-mail avec le mot de passe par défaut
        String subject = "Réinitialisation de votre mot de passe";
        String message = "Votre mot de passe a été réinitialisé. Voici votre nouveau mot de passe : " + defaultPassword;
        EmailUser.sendEmailU(email, subject, message);

        showAlert("Un e-mail avec votre nouveau mot de passe a été envoyé à " + email + ".", Alert.AlertType.INFORMATION);

        // Après l'envoi de l'e-mail

    }


    private void showAlert(String message, Alert.AlertType alertType) {
        Alert alert = new Alert(alertType);
        alert.setContentText(message);
        alert.showAndWait();
    }


    private String generateDefaultPassword() {
// Générer un mot de passe aléatoire
        String alphabet = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        int passwordLength = 10; // Longueur du mot de passe
        StringBuilder password = new StringBuilder(passwordLength);
        for (int i = 0; i < passwordLength; i++) {
            int index = (int) (alphabet.length() * Math.random());
            password.append(alphabet.charAt(index));
        }
        return password.toString();
    }
}

