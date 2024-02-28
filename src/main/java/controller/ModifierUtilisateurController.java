package controller;

import entities.UserRole;
import entities.utilisateur;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.util.StringConverter;
import services.utilisateurServices;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class ModifierUtilisateurController {

    @FXML
    private TextField Email;

    @FXML
    private PasswordField MotDePasse;

    @FXML
    private TextField Nom;

    @FXML
    private TextField NumeroDeTelephone;

    @FXML
    private TextField Prenom;

    @FXML
    private Button saveButton;

    @FXML
    private ChoiceBox<UserRole> userRole;
    @FXML
    private TableView<utilisateur> tableview;
    private int userId;
    // Initialize method

    @FXML
    public void initialize() {
        // Set up the StringConverter
        userRole.setConverter(new StringConverter<UserRole>() {
            public String toString(UserRole object) {
                return object == null ? "" : object.name(); // Convert enum to string
            }

            public UserRole fromString(String string) {
                // Convert string back to enum
                return UserRole.valueOf(string);
            }
        });

        // Add items to the ChoiceBox
        userRole.setItems(FXCollections.observableArrayList(
                        Arrays.stream(UserRole.values())
                                .filter(role -> role != entities.UserRole.role  )
                                .collect(Collectors.toList())
                )
        );
}
    // SaveButton action
    @FXML
    public void GoToAfficherU(ActionEvent actionEvent) throws SQLException, IOException {
        List<String> messagesErreur = new ArrayList<>();

        // Récupérer l'ID de l'utilisateur sélectionné

        // Récupérer les nouvelles valeurs depuis les champs de texte
        String nouveauNom = Nom.getText();
        String nouveauPrenom = Prenom.getText();
        String nouveauEmail = Email.getText();
        String nouveauMotDePasse = MotDePasse.getText();
        String nouveauNumeroDeTelephoneText = NumeroDeTelephone.getText();
        UserRole nouveauUserRole = userRole.getSelectionModel().getSelectedItem();

        // Effectuer les contrôles de saisie
        if (nouveauNom.isEmpty() || nouveauPrenom.isEmpty() || nouveauEmail.isEmpty() || nouveauMotDePasse.isEmpty() || nouveauNumeroDeTelephoneText.isEmpty() || nouveauUserRole == null) {
            messagesErreur.add("Veuillez remplir tous les champs.");
        }
        if (!isValidPhoneNumber(nouveauNumeroDeTelephoneText)){
            messagesErreur.add("Veillez entrer  8 chiffres.");
        }

        // Convertir le numéro de téléphone en int
        int nouveauNumeroDeTelephone=0;
        try {
            nouveauNumeroDeTelephone = Integer.parseInt(nouveauNumeroDeTelephoneText);
        } catch (NumberFormatException e) {
            messagesErreur.add("Veuillez entrer un numéro de téléphone valide.");
        }

        if(!isValidEmail(nouveauEmail)){
            messagesErreur.add("Vérifiez le format de votre Email.");
        }
        // Vérifier l'unicité de l'utilisateur
        if (utilisateurServices.estUtilisateurUnique(userId, nouveauEmail)) {
            messagesErreur.add("Un utilisateur avec cet email n'existe pas.");
        }



        if (!messagesErreur.isEmpty()) {
            String messageErreur = String.join("\n", messagesErreur);
            showAlert(messageErreur, Alert.AlertType.ERROR);
            return;
        }

        // Créer un nouvel utilisateur avec les valeurs modifiées
        utilisateur utilisateurModifie = new utilisateur(userId, nouveauNom, nouveauPrenom, nouveauUserRole, nouveauNumeroDeTelephone, nouveauEmail, nouveauMotDePasse);
        System.out.println(utilisateurModifie);
        // Appeler le service pour mettre à jour l'utilisateur
        utilisateurServices userService = new utilisateurServices();
        userService.modifier(utilisateurModifie);

        // Afficher un message de succès
        showAlert("Utilisateur modifié avec succès", Alert.AlertType.INFORMATION);

        // Rediriger vers l'affichage des utilisateurs
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/AfficherUser.fxml"));
        Parent root = loader.load();

        Stage stage = (Stage) saveButton.getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    // Récupérer l'ID de l'utilisateur sélectionné
    private int getSelectedUserId() {
        if (tableview != null) {
            utilisateur selectedUser = tableview.getSelectionModel().getSelectedItem();

            // Vérifier que l'utilisateur n'est pas nul
            if (selectedUser != null) {
                return selectedUser.getId();
            } else {
                // Gérer le cas où aucun utilisateur n'est sélectionné
                showAlert("Veuillez sélectionner un utilisateur.", Alert.AlertType.ERROR);
                return -1; // Ou une valeur qui représente l'absence d'ID valide
            }
        } else {
            return -1; // Si le TableView n'est pas initialisé
        }
    }

    // Validation d'email
    private boolean isValidEmail(String nouveauEmail) {
        return nouveauEmail.matches("[a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}");
    }

    // Validation du format du numéro de téléphone (8 chiffres)
    private boolean isValidPhoneNumber(String phoneNumber) {
        return phoneNumber.matches("\\d{8}");
    }

    // Afficher une boîte de dialogue
    private void showAlert(String s, Alert.AlertType alertType) {
        Alert alert = new Alert(alertType);
        alert.setHeaderText(null);
        alert.setContentText(s);
        alert.showAndWait();
    }

    // Initialiser les champs avec les données de l'utilisateur sélectionné
    public void initData(utilisateur selectedUser) {
        if (selectedUser != null) {
            userId = selectedUser.getId();
            Nom.setText(selectedUser.getNom());
            Prenom.setText(selectedUser.getPrenom());
            Email.setText(selectedUser.getEmail());
            MotDePasse.setText(selectedUser.getMotDePasse());
            NumeroDeTelephone.setText(String.valueOf(selectedUser.getNumeroDeTelephone()));
            userRole.setValue(selectedUser.getRole());
        }
    }


}
