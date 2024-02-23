package controller;

import entites.Transport;
import entites.Transpoteur;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import service.TransportService;
import service.TranspoteurService;
import javafx.scene.control.Button;

import java.io.IOException;
import java.time.LocalDate;

public class Updatetranspoteur {
    @FXML
    private Button button_ajouter;

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
    private DatePicker dnId;

    @FXML
    private TextField email;

    @FXML
    private TextField nom;

    @FXML
    private TextField num_ch;

    @FXML
    private TextField num_t;

    @FXML
    private TextField numtel;

    @FXML
    private TextField prenom;
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
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Updatetransport.fxml"));
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
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/ajoutertranspteur.fxml"));
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

    @FXML
    void GoToview(ActionEvent event) {
        try {
            // Load the Event.fxml file
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Affichiertranspoteur.fxml"));
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
    void modifiertranspotuer(ActionEvent event) {
        try {
            // Retrieve values from the text fields and date pickers


            LocalDate datenn = dnId.getValue();
            String emaill = email.getText();
            String nomm = nom.getText();
            int num_tt = Integer.parseInt(num_t.getText());
            int num_c = Integer.parseInt(num_ch.getText());
            int numtell = Integer.parseInt(numtel.getText());
            String prenomm = prenom.getText();
            Transpoteur tr = new Transpoteur(num_c,nomm,prenomm,numtell,emaill,datenn,num_tt);
            TranspoteurService psr = new TranspoteurService();
            psr.updatet(tr);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Transpoteur modfier");
            alert.setHeaderText(null);
            alert.setContentText("Le transpoteur a été modfier avec succès!");
            alert.showAndWait();

            clearFields();

        } catch (NumberFormatException e) {
            // Handle invalid input (e.g., non-numeric values)
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur de saisie");
            alert.setHeaderText(null);
            alert.setContentText("Veuillez saisir des valeurs numériques valides!");
            alert.showAndWait();
        }


        // Perform validation and processing
        // For example, you might check if the values are not empty and if the dates are valid

        // Update transport information in your application's data model or database
        // For example, you might call a method to update the transport information in your data model
    }


    private void clearFields() {
        num_ch.clear();
        nom.clear();
        prenom.clear();
        numtel.clear();
        email.clear();
        dnId.setValue(null);

        num_t.clear();

        //num_t.clear();
    }

    }


