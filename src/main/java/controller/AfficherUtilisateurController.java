package controller;

import entities.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import services.UserService;
import utils.DataSource;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
public class AfficherUtilisateurController implements Initializable {
    public ImageView logout;
    public Button centre_button;
    public Button activite_button;
    public Button button_transport;
    public ChoiceBox searchCriteriaChoiceBox;
    public Button searchButton;
    private Connection connexion;
    private Statement stm;

    public AfficherUtilisateurController() {
        // Constructeur sans arguments
    }
    @FXML
    private Button boutonStatistiques;

    public void onBoutonStatistiquesClique() throws SQLException {
        // Récupérer la liste des utilisateurs à afficher dans le graphique
        ObservableList<User> utilisateurs = getUtilisateur();

        // Afficher la fenêtre des statistiques
        StatistiquesFenetre.afficherStatistiques(utilisateurs);
    }
    @FXML
    private TextField searchValueTextField;


    @FXML
    private Button AjouterButton;

    @FXML
    private Button ModifierButton;

    @FXML
    private Button SupprimerButton;

    @FXML
    private Button UserButton;

    @FXML
    private TableColumn<User, ?> emailcolumn;

    @FXML
    private TableColumn<User, ?> idcolumn;

    @FXML
    private TableColumn<User, ?> motdepassecolumn;

    @FXML
    private TableColumn<User, ?> nomcolumn;

    @FXML
    private TableColumn<User, ?> numerodetelephonecolumn;

    @FXML
    private TableColumn<User, ?> prenomcolumn;

    @FXML
    private TableColumn<User, ?> rolecolumn;

    @FXML
    private TableView<User> tableview;


    public <connection> AfficherUtilisateurController(connection connexion) {
        this.connexion = DataSource.getInstance().getCnx();
    }

    @FXML
    void GoToTransport(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/transport.fxml"));
            Parent root = loader.load();

            Stage stage = (Stage) button_transport.getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void GoToActivite(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/ajouteractivite.fxml"));
            Parent root = loader.load();

            Stage stage = (Stage) activite_button.getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void GoToCentre(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/ajouter.fxml"));
            Parent root = loader.load();

            Stage stage = (Stage) centre_button.getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Bouton Ajouter
    @FXML
    void GoToAjoutU(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/AjouterUser.fxml"));
            Parent root = loader.load();

            Stage stage = (Stage) AjouterButton.getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Bouton Modifier
    @FXML
    public void GoToModifierU(ActionEvent actionEvent) throws SQLException, IOException {
        // Récupérer l'utilisateur sélectionné depuis le TableView
        User selectedUser = tableview.getSelectionModel().getSelectedItem();

        // Vérifier si un utilisateur est sélectionné dans l'interface AfficherUser
        if (selectedUser != null) {
            try {
                // Chemin correct vers votre fichier ModifierUtilisateur.fxml
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/ModifierUser.fxml"));
                Parent root = loader.load();

                // Passer l'utilisateur sélectionné au ModifierUtilisateurController
                ModifierUtilisateurController modifierController = loader.getController();
                modifierController.initData(selectedUser);

                Stage stage = new Stage();
                Scene scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            showAlert("Veuillez sélectionner un utilisateur !.", Alert.AlertType.ERROR);
        }
    }

    // Afficher une boîte de dialogue
    private void showAlert(String s, Alert.AlertType alertType) {
        Alert alert = new Alert(alertType);
        alert.setHeaderText(null);
        alert.setContentText(s);
        alert.showAndWait();
    }

    // Bouton User
    @FXML
    void GoToUser(ActionEvent event) {
        // Vous pouvez implémenter la logique pour le bouton UserButton ici
    }

    // Initialiser la méthode
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            // Initialiser la connexion ici
            initialiserConnexion();

            // Appeler la méthode afficherUser()
            afficherUser();
        } catch (SQLException e) {
            Logger.getLogger(AfficherUtilisateurController.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    private void initialiserConnexion() throws SQLException {
        // Charger le pilote JDBC
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace(); // Ou gérez l'exception de la manière qui vous convient
        }

        // Mettez à jour les informations de connexion avec votre URL, nom d'utilisateur et mot de passe
        String url = "jdbc:mysql://localhost:3306/campigo";
        String utilisateur = "root";
        String motDePasse = "";

        // Établir la connexion
        connexion = DriverManager.getConnection(url, utilisateur, motDePasse);

        // Autres initialisations si nécessaire
    }

    // Récupérer la liste des utilisateurs
    public ObservableList<User> getUtilisateur() throws SQLException {
        ObservableList<User> user = FXCollections.observableArrayList();
        String req = "SELECT * FROM user";
        stm = connexion.createStatement();
        ResultSet rst = stm.executeQuery(req);
        while (rst.next()) {
            User c = new User(rst.getInt("id"));
            c.setNom(rst.getString("nom"));
            c.setPrenom(rst.getString("prenom"));
         //   String role = String.valueOf(UserRole.valueOf(rst.getString("roles")));
            c.setRoles(rst.getString("roles"));
            c.setNumerodetelephone(rst.getInt("numerodetelephone"));
            c.setEmail(rst.getString("email"));
            c.setPassword(rst.getString("password"));

            user.add(c);
        }
        return user;
    }

    // Afficher la liste des utilisateurs dans le TableView
    public void afficherUser() throws SQLException {
        ObservableList<User> list = getUtilisateur();

        idcolumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        nomcolumn.setCellValueFactory(new PropertyValueFactory<>("nom"));
        prenomcolumn.setCellValueFactory(new PropertyValueFactory<>("prenom"));
        rolecolumn.setCellValueFactory(new PropertyValueFactory<>("roles"));
        numerodetelephonecolumn.setCellValueFactory(new PropertyValueFactory<>("numerodetelephone"));
        emailcolumn.setCellValueFactory(new PropertyValueFactory<>("email"));
        motdepassecolumn.setCellValueFactory(new PropertyValueFactory<>("password"));

        tableview.setItems(list);
    }

    // Définir le texte de la colonne Nom
    public void setNomColumn(String Nom) {
        nomcolumn.setText(Nom);
    }

    @FXML
    void SupprimerU(ActionEvent event) {
        // Récupérer l'utilisateur sélectionné depuis le TableView
        User selectedUser = tableview.getSelectionModel().getSelectedItem();

        // Vérifier si un utilisateur est sélectionné
        if (selectedUser != null) {
            // Supprimer l'utilisateur de la liste de données
            ObservableList<User> users = tableview.getItems();
            users.remove(selectedUser);
        } else {
            showAlert("Veuillez sélectionner un utilisateur à supprimer.", Alert.AlertType.ERROR);
        }
    }

    public void handleSearchButtonAction(ActionEvent actionEvent) {
        String searchCriteria = (String) searchCriteriaChoiceBox.getValue();
        String searchValue = searchValueTextField.getText();

        ObservableList<User> users = FXCollections.observableArrayList();
        switch (searchCriteria) {
            case "Nom":
                users = FXCollections.observableArrayList(UserService.rechercherUtilisateurs(searchValue, null, null, null));
                break;
            case "Prenom":
                users = FXCollections.observableArrayList(UserService.rechercherUtilisateurs(null, searchValue, null, null));
                break;
            case "Email":
                users = FXCollections.observableArrayList(UserService.rechercherUtilisateurs(null, null, searchValue, null));
                break;
            case "Role":
                // Assuming you have a method to convert the role string to UserRole enum
               // UserRole role = entities.UserRole.valueOf(searchValue.toUpperCase());

                users = FXCollections.observableArrayList(UserService.rechercherUtilisateurs(null, null, null, searchValue));
                break;
        }

        tableview.setItems(users);
    }
}



