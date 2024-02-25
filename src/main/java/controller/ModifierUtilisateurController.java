package controller;

import entities.UserRole;
import entities.utilisateur;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import services.utilisateurServices;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Objects;

public class ModifierUtilisateurController {
    @FXML
    private TextField nomField;

    @FXML
    private TextField prenomField;

    @FXML
    private TextField numeroDeTelephoneField;

    @FXML
    private TextField emailField;

    @FXML
    private PasswordField motDePasseField;

    private int userId;
    @FXML
    private Button saveButton;
    public ModifierUtilisateurController() {
        // Constructeur par défaut sans paramètres
    }


    public ModifierUtilisateurController(TextField nomField, TextField prenomField, TextField numeroDeTelephoneField, TextField emailField, PasswordField motDePasseField) {
        this.nomField = nomField;
        this.prenomField = prenomField;
        this.numeroDeTelephoneField = numeroDeTelephoneField;
        this.emailField = emailField;
        this.motDePasseField = motDePasseField;
    }

    public void initialize(int userId) {
        this.userId = userId;
    }

    private void chargerDonneesUtilisateur(int userId) {
        utilisateur user = utilisateurServices.getUtilisateurById(userId);
        afficherDonneesUtilisateur(Objects.requireNonNull(user));
    }

    private void afficherDonneesUtilisateur(utilisateur user) {
        nomField.setText(user.getNom());
        prenomField.setText(user.getPrenom());
        numeroDeTelephoneField.setText(String.valueOf(user.getNumeroDeTelephone()));
        emailField.setText(user.getEmail());
        motDePasseField.setText(user.getMotDePasse());
    }

    @FXML
    private void sauvegarderModifications() throws SQLException {
        utilisateur utilisateurModifier = new utilisateur(
                userId,
                nomField.getText(),
                prenomField.getText(),
                Integer.parseInt(numeroDeTelephoneField.getText()),
                emailField.getText(),
                motDePasseField.getText(),
                UserRole.role
        );


    }
    @FXML
    void GoToAfficherU(ActionEvent event) {
        try {
            // Load the Event.fxml file
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/AfficherUser.fxml"));
            javafx.scene.Parent root = loader.load();

            // Show the AfficherUser.fxml scene
            Stage stage = (Stage) saveButton.getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
