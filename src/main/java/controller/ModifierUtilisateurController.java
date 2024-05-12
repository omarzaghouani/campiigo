package controller;

import entities.User;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.util.StringConverter;
import services.UserService;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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
    private ChoiceBox<String> userRole;
    @FXML
    private TableView<User> tableview;
    private int userId;
    // Initialize method

    @FXML
    public void initialize() {
        // Set up the StringConverter
        userRole.setConverter(new StringConverter<String>() {
            public String toString(String object) {
                return object == null ? "" : object; // Convert string to string
            }

            public String fromString(String string) {
                // No conversion needed for string
                return string;
            }
        });

        // Add items to the ChoiceBox
        /*userRole.setItems(FXCollections.observableArrayList(
                Arrays.stream(UserRole.values())
                        .filter(role -> role != entities.UserRole.role)
                        .map(UserRole::name) // Convert enum to string
                        .collect(Collectors.toList())
        ));*/
        userRole.setItems(FXCollections.observableArrayList(
                "[\"ROLE_ADMIN\"]",
                "[\"ROLE_USER\"]"
        ));

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
        String nouveauUserRole = userRole.getSelectionModel().getSelectedItem();

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
        if (UserService.estUtilisateurUnique(userId, nouveauEmail)) {
            messagesErreur.add("Un utilisateur avec cet email n'existe pas.");
        }



        if (!messagesErreur.isEmpty()) {
            String messageErreur = String.join("\n", messagesErreur);
            showAlert(messageErreur, Alert.AlertType.ERROR);
            return;
        }
        // (int id, String email, String roles, String password, String nom, String prenom, int numerodetelephone, String photo_d)

        // Créer un nouvel utilisateur avec les valeurs modifiées
      //  User utilisateurModifie = new User(userId, nouveauNom, nouveauPrenom, nouveauUserRole, nouveauNumeroDeTelephone, nouveauEmail, nouveauMotDePasse);
        User utilisateurModifie = new User(userId,nouveauEmail,nouveauUserRole,nouveauMotDePasse,nouveauNom,nouveauPrenom, nouveauNumeroDeTelephone,"" );

        System.out.println(utilisateurModifie);
        // Appeler le service pour mettre à jour l'utilisateur
        UserService userService = new UserService();
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
            User selectedUser = tableview.getSelectionModel().getSelectedItem();

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
    public void initData(User selectedUser) {
        if (selectedUser != null) {
            userId = selectedUser.getId();
            Nom.setText(selectedUser.getNom());
            Prenom.setText(selectedUser.getPrenom());
            Email.setText(selectedUser.getEmail());
            MotDePasse.setText(selectedUser.getPassword());
            NumeroDeTelephone.setText(String.valueOf(selectedUser.getNumerodetelephone()));
           // userRole.setValue(String.valueOf(UserRole.valueOf(selectedUser.getRoles())));
            userRole.setValue(selectedUser.getRoles());

        }
    }


}
