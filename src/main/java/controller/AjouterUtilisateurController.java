package controller;

import entities.UserRole;
import entities.utilisateur;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.util.StringConverter;
import services.utilisateurServices;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.stream.Collectors;

public class AjouterUtilisateurController {
    @FXML
    private TextField Email;

    @FXML
    private TextField MotDePasse;

    @FXML
    private TextField Nom;

    @FXML
    private TextField NumeroDeTelephone;

    @FXML
    private TextField Prenom;

    @FXML
    private Button ajouterUser; // Assurez-vous que le nom de la variable correspond à l'fx:id

    @FXML
    private ChoiceBox<UserRole> userRole;


    @FXML
    public void initialize() {
        // Set up the StringConverter
        userRole.setConverter(new StringConverter<UserRole>() {
            @Override
            public String toString(UserRole object) {
                return object == null ? "" : object.name(); // Convert enum to string
            }

            @Override
            public UserRole fromString(String string) {
                // Convert string back to enum
                return UserRole.valueOf(string);
            }
        });

        // Add items to the ChoiceBox
        userRole.setItems(FXCollections.observableArrayList(
                Arrays.stream(UserRole.values())
                        .filter(role -> role != entities.UserRole.Admin &&  role != entities.UserRole.role  )
                        .collect(Collectors.toList())
        )
        );
    }



    public void GoToLogin(javafx.event.ActionEvent actionEvent) throws SQLException, IOException {
        String nom = Nom.getText();
        String prenom = Prenom.getText();
        String email = Email.getText();
        String motDePasse = MotDePasse.getText();
        String numeroDeTelephoneText = NumeroDeTelephone.getText();
        if (nom.isEmpty() || prenom.isEmpty() || email.isEmpty() || motDePasse.isEmpty() || numeroDeTelephoneText.isEmpty()) {
            showAlert("Veuillez remplir tous les champs.", Alert.AlertType.ERROR);
            return;  // Stop execution if any field is empty
        }

        // Validate email format
        if (!isValidEmail(email)) {
            showAlert("Veuillez entrer une adresse email valide.", Alert.AlertType.ERROR);
            return;
        }

        // Validate phone number format (8 digits)
        if (!isValidPhoneNumber(numeroDeTelephoneText)) {
            showAlert("Veuillez entrer un numéro de téléphone valide (8 chiffres).", Alert.AlertType.ERROR);
            return;
        }

        int numeroDeTelephone = Integer.parseInt(numeroDeTelephoneText);

        // Assurez-vous que userRole est bien une instance de l'enum UserRole
        UserRole userRoles = userRole.getSelectionModel().getSelectedItem();


        utilisateur newUser = new utilisateur(nom, prenom, userRoles, numeroDeTelephone, email, motDePasse);

        // Appel du service pour ajouter l'utilisateur
        utilisateurServices userService = new utilisateurServices();
        userService.ajouter(newUser);
        showAlert("Utilisateur ajouté avec succès", Alert.AlertType.INFORMATION);

        // Load the LoginUser.fxml file
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/LoginUser.fxml"));
        Parent loginParent = loader.load();
        Scene loginScene = new Scene(loginParent);

        // Get the stage information
        Stage window = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();

        // Set the new scene onto the stage
        window.setScene(loginScene);
        window.show();

    }
    private void showAlert(String message, Alert.AlertType alertType) {
        Alert alert = new Alert(alertType);
        alert.setContentText(message);
        alert.showAndWait();
    }
    private boolean isValidEmail(String email) {
        // You can use a more sophisticated email validation regex if needed
        return email.matches("[a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}");
    }

    // Validate phone number format (8 digits)
    private boolean isValidPhoneNumber(String phoneNumber) {
        return phoneNumber.matches("\\d{8}");
    }
}



