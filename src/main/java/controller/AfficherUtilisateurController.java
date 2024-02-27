package controller;

import entities.UserRole;
import entities.utilisateur;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import utils.DataSource;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AfficherUtilisateurController implements Initializable {
        public ImageView logout;
        private Connection connexion;
        private Statement stm;

        public AfficherUtilisateurController() {
                // Constructeur sans arguments
        }


        @FXML
        private Button AjouterButton;

        @FXML
        private Button ModifierButton;

        @FXML
        private Button SupprimerButton;

        @FXML
        private Button UserButton;

        @FXML
        private TableColumn<utilisateur, ?> emailcolumn;

        @FXML
        private TableColumn<utilisateur, ?> idcolumn;

        @FXML
        private TableColumn<utilisateur, ?> motdepassecolumn;

        @FXML
        private TableColumn<utilisateur, ?> nomcolumn;

        @FXML
        private TableColumn<utilisateur, ?> numerodetelephonecolumn;

        @FXML
        private TableColumn<utilisateur, ?> prenomcolumn;

        @FXML
        private TableColumn<utilisateur, ?> rolecolumn;

        @FXML
        private TableView<utilisateur> tableview;



        public <connection> AfficherUtilisateurController(connection connexion) {
                this.connexion = DataSource.getInstance().getCnx();
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
                utilisateur selectedUser = tableview.getSelectionModel().getSelectedItem();

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
        public ObservableList<utilisateur> getUtilisateur() throws SQLException {
                ObservableList<utilisateur> user = FXCollections.observableArrayList();
                String req = "SELECT * FROM utilisateur";
                stm = connexion.createStatement();
                ResultSet rst = stm.executeQuery(req);
                while (rst.next()) {
                        utilisateur c = new utilisateur(rst.getInt("id"));
                        c.setNom(rst.getString("nom"));
                        c.setPrenom(rst.getString("Prenom"));
                        UserRole role = UserRole.valueOf(rst.getString("role"));
                        c.setRole(role);
                        c.setNumeroDeTelephone(rst.getInt("NumeroDeTelephone"));
                        c.setEmail(rst.getString("Email"));
                        c.setMotDePasse(rst.getString("MotDePasse"));

                        user.add(c);
                }
                return user;
        }

        // Afficher la liste des utilisateurs dans le TableView
        public void afficherUser() throws SQLException {
                ObservableList<utilisateur> list = getUtilisateur();

                idcolumn.setCellValueFactory(new PropertyValueFactory<>("id"));
                nomcolumn.setCellValueFactory(new PropertyValueFactory<>("nom"));
                prenomcolumn.setCellValueFactory(new PropertyValueFactory<>("prenom"));
                rolecolumn.setCellValueFactory(new PropertyValueFactory<>("role"));
                numerodetelephonecolumn.setCellValueFactory(new PropertyValueFactory<>("numeroDeTelephone"));
                emailcolumn.setCellValueFactory(new PropertyValueFactory<>("email"));
                motdepassecolumn.setCellValueFactory(new PropertyValueFactory<>("motDePasse"));

                tableview.setItems(list);
        }
        // Définir le texte de la colonne Nom
        public void setNomColumn(String Nom) {
                nomcolumn.setText(Nom);
        }

        @FXML
        void SupprimerU(ActionEvent event) {
                // Récupérer l'utilisateur sélectionné depuis le TableView
                utilisateur selectedUser = tableview.getSelectionModel().getSelectedItem();

                // Vérifier si un utilisateur est sélectionné
                if (selectedUser != null) {
                        // Supprimer l'utilisateur de la liste de données
                        ObservableList<utilisateur> users = tableview.getItems();
                        users.remove(selectedUser);
                } else {
                        showAlert("Veuillez sélectionner un utilisateur à supprimer.", Alert.AlertType.ERROR);
                }
        }
}
