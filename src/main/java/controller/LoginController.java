package controller;

import entities.utilisateur;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import services.utilisateurServices;

import java.io.IOException;

public class LoginController {

    @FXML
    private TextField emailTextField;

    @FXML
    private PasswordField passwordTextField;

    @FXML
    private Button loginButton;

    // Méthode pour gérer l'action du bouton de connexion
    @FXML
    private void loginButtonAction(ActionEvent event) throws IOException {

    }

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

    private void redirectToCampOwnerInterface(ActionEvent actionEvent) throws IOException {
        // Code to redirect to the camp owner interface (e.g., load a new FXML)
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Home.fxml"));
        Parent adminParent = loader.load();
        Scene adminScene = new Scene(adminParent);

        // Get the stage information
        Stage window = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();

        // Set the new scene onto the stage
        window.setScene(adminScene);
        window.show();
    }

    private void redirectToSimpleUserInterface(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/AjouterUser.fxml"));
        Parent adminParent = loader.load();
        Scene adminScene = new Scene(adminParent);

        // Get the stage information
        Stage window = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();

        // Set the new scene onto the stage
        window.setScene(adminScene);
        window.show();
        // Code to redirect to the simple user interface (e.g., load a new FXML)
    }




    public void GoToInterface(ActionEvent event) throws IOException {
        utilisateurServices us = new utilisateurServices();
        utilisateur user = us.authentifier(emailTextField.getText(), passwordTextField.getText());

        if (user != null && user.getRole() != null) {
            switch (user.getRole()) {
                case Admin:
                    // Redirect to the admin interface
                    redirectToAdminInterface(event);
                    break;
                case Camp_Owner:
                    // Redirect to the camp owner interface
                    redirectToCampOwnerInterface(event);
                    break;
                case Simple_User:
                    // Redirect to the simple user interface
                    redirectToSimpleUserInterface(event);
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
private void showAlert(String message, Alert.AlertType alertType) {
    Alert alert = new Alert(alertType);
    alert.setContentText(message);
    alert.showAndWait();
}
}
