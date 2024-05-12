package controller;

import entities.Activite;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import services.ActiviteService;

import java.io.IOException;
import java.util.List;

public class ViewActiviteController {
    @FXML
    public TableColumn colid_activite;
    @FXML
    public TableColumn colnom_activite;
    @FXML
    public TableColumn coldescription;
    @FXML
    public TableColumn coltype;
    @FXML
    public TableView tableview;
    private final ActiviteService ActiviteService = new ActiviteService();
    public TextField textsupp_activite;
    public Button button_supp_activite;
    public Button buttoncentre;
    public Button buttonactivite;
    public Button butttonview;
    public Button buttonajouter;
    public Button buttonmodifier;
    public TableColumn colid_centre;

    public Button button_utilisateur;




    @FXML
    void GoToUtilisateur(ActionEvent event) {
        try {
            // Load the Event.fxml file
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/AfficherUser.fxml"));
            javafx.scene.Parent root = loader.load();

            // Show the AfficherUser.fxml scene
            Stage stage = (Stage) button_utilisateur.getScene().getWindow();
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
            // Load the Event.fxml file
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/ajouter.fxml"));
            javafx.scene.Parent root = loader.load();

            // Show the AfficherUser.fxml scene
            Stage stage = (Stage) buttoncentre.getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    @FXML
    void GoTomodifier(ActionEvent event) {
        try {
            // Load the Event.fxml file
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/modifieractivite.fxml"));
            javafx.scene.Parent root = loader.load();

            // Show the AfficherUser.fxml scene
            Stage stage = (Stage) buttonmodifier.getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    @FXML
    void GoToAjouter(ActionEvent event) {
        try {
            // Load the Event.fxml file
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/ajouteractivite.fxml"));
            javafx.scene.Parent root = loader.load();

            // Show the AfficherUser.fxml scene
            Stage stage = (Stage) buttonajouter.getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void initialize() {
        // Initialise les colonnes du TableView
        colid_activite.setCellValueFactory(new PropertyValueFactory<>("id_activite"));
        colnom_activite.setCellValueFactory(new PropertyValueFactory<>("nom_activite"));
        coldescription.setCellValueFactory(new PropertyValueFactory<>("description"));
        coltype.setCellValueFactory(new PropertyValueFactory<>("type"));
        colid_centre.setCellValueFactory(new PropertyValueFactory<>("id_centre"));
        // Charge les données dans le TableView à partir du service
        loadActivite();
    }
    public void loadActivite() {
        List<Activite> activites = ActiviteService.readAll();
        ObservableList<Activite> observableList = FXCollections.observableArrayList(activites);
        tableview.setItems(observableList);
    }
    @FXML
    public void supprimeractivite(ActionEvent actionEvent) {
        try {
            int id_activite = Integer.parseInt(textsupp_activite.getText());
            ActiviteService as = new ActiviteService();
            Activite c = as.readById(id_activite);
            if (c != null) {
                as.delete(c);
                try {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/viewactivite.fxml"));
                    javafx.scene.Parent root = loader.load();

                    Stage stage = (Stage) button_supp_activite.getScene().getWindow();
                    Scene scene = new Scene(root);
                    stage.setScene(scene);
                    stage.show();
                }catch (IOException e) {
                    e.printStackTrace();
                }
            } else {
                System.out.println("L'activite avec l'ID " + id_activite + " n'existe pas.");
            }
        } catch (NumberFormatException e) {
            // Gérer l'exception si la conversion en nombre échoue
            e.printStackTrace();
        }
    }



}