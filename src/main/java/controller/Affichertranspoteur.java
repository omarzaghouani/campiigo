package controller;

import entites.Transpoteur;
import entites.Vehicule;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import service.TranspoteurService;
import service.VehiculeService;
import javafx.scene.control.TableColumn;

import java.io.IOException;
import java.util.List;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
public class Affichertranspoteur {

    @FXML
    private Button button_ajouter;

    @FXML
    private Button button_supp;

    @FXML
    private Button buttonafficher;

    @FXML
    private Button buttonmodifier;

    @FXML
    private Button buttontransport;

    @FXML
    private Button buttontranspoteur;

    @FXML
    private Button buttonvehicule;

    @FXML
    private TableColumn<?, ?> coldaten;

    @FXML
    private TableColumn<?, ?> colemail;

    @FXML
    private TableColumn<?, ?> colnom;

    @FXML
    private TableColumn<?, ?> colnum_ch;

    @FXML
    private TableColumn<?, ?> colnum_t;

    @FXML
    private TableColumn<?, ?> colnumtel;

    @FXML
    private TableColumn<?, ?> colprenom;

    @FXML
    private TableView<Transpoteur> tableview;

    @FXML
    private TextField txtnum_ch_supp;
    private final TranspoteurService TranspoteurService=new TranspoteurService() ;

    public void initialize() {
        // Initialise les colonnes du TableView

        colnum_ch.setCellValueFactory(new PropertyValueFactory<>("num_ch"));
        colnom.setCellValueFactory(new PropertyValueFactory<>("nom"));
        colprenom.setCellValueFactory(new PropertyValueFactory<>("prenom"));
        colnumtel.setCellValueFactory(new PropertyValueFactory<>("numtel"));
        colemail.setCellValueFactory(new PropertyValueFactory<>("email"));
        coldaten.setCellValueFactory(new PropertyValueFactory<>("daten"));
        colnum_t.setCellValueFactory(new PropertyValueFactory<>("num_t"));

        // Charge les données dans le TableView à partir du service
        loadtranspoteur();
    }
    private void loadtranspoteur() {
        List<Transpoteur> transpoteurs= TranspoteurService.readAll();
        ObservableList<Transpoteur> observableList = FXCollections.observableArrayList(transpoteurs);
        tableview.setItems(observableList);
    }

    @FXML
    void supprimertransport(ActionEvent event) {
        try {
            int num_ch = Integer.parseInt(txtnum_ch_supp.getText());
            TranspoteurService psr = new TranspoteurService() {
            };
            Transpoteur tr = psr.readBynum_ch(num_ch);
            if (tr != null) {
                psr.deletet(tr);

            } else {
                System.out.println("Le transpoteur avec num_ch" + num_ch + " n'existe pas.");
            }
        } catch (NumberFormatException e) {
            // Gérer l'exception si la conversion en nombre échoue
            e.printStackTrace();
        }
    }
    @FXML
    void GoToAdd(ActionEvent event) {
        try {
            // Load the Event.fxml file
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/ajoutertranspoteur.fxml"));
            javafx.scene.Parent root = loader.load();

            // Show the AfficherUser.fxml scene
            Stage stage = (Stage) button_ajouter.getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @FXML
    void GoToUpdate(ActionEvent event) {
        try {
            // Load the Event.fxml file
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/UpdateTranspoteur.fxml"));
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
    void GoTotransport(ActionEvent event) {
        try {
            // Load the Event.fxml file
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/transport.fxml"));
            javafx.scene.Parent root = loader.load();

            // Show the AfficherUser.fxml scene
            Stage stage = (Stage) buttontransport.getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }



    @FXML
    void GoTotranspoteur(ActionEvent event) {
        try {
            // Load the Event.fxml file
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/ajoutertranspoteur.fxml"));
            javafx.scene.Parent root = loader.load();

            // Show the AfficherUser.fxml scene
            Stage stage = (Stage) buttontranspoteur.getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @FXML
    void GoToview(ActionEvent event) {
        try {
            // Load the Event.fxml file
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Affichertranspoteur.fxml"));
            javafx.scene.Parent root = loader.load();

            // Show the AfficherUser.fxml scene
            Stage stage = (Stage) buttonafficher.getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @FXML
    void GoTovehicule(ActionEvent event) {
        try {
            // Load the Event.fxml file
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/AjouterVehicule.fxml"));
            javafx.scene.Parent root = loader.load();

            // Show the AfficherUser.fxml scene
            Stage stage = (Stage) buttonvehicule.getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



}
