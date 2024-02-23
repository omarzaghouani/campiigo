package controller;

import entites.Transport;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.event.ActionEvent;
import java.time.LocalDate;
import javafx.scene.control.*;
import javafx.stage.Stage;
import service.TransportService;


import java.io.IOException;
public class Updatetransport {
    @FXML
    private Button buttonafficher;

    @FXML
    private Button buttonajouter;

    @FXML
    private Button buttonmodifier;

    @FXML
    private Button buttontransport;

    @FXML
    private Button buttontranspoteur;

    @FXML
    private Button buttonvehicule;

    @FXML
    private TextField cout;

    @FXML
    private DatePicker daId;

    @FXML
    private DatePicker ddId;

    @FXML
    private TextField num_ch;

    @FXML
    private TextField num_v;

    @FXML
    void update(ActionEvent event) {
        try {
        // Retrieve values from the text fields and date pickers
        int coutt = Integer.parseInt(cout.getText());

        LocalDate dd = ddId.getValue();
        LocalDate da = daId.getValue();
        int num_c = Integer.parseInt(num_ch.getText());
        int num_ve = Integer.parseInt(num_v.getText());

        Transport t = new Transport(num_c,dd,da,num_ve,coutt);
        TransportService ps = new TransportService();
        ps.update(t);
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Transport modfier");
        alert.setHeaderText(null);
        alert.setContentText("Le transport a été modfier avec succès!");
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
            ddId.setValue(null);
            daId.setValue(null);
            num_v.clear();
            cout.clear();
            //num_t.clear();
        }
    @FXML
    void GoToAdd(ActionEvent event) {
        try {
            // Load the Event.fxml file
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/transport.fxml"));
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
    @FXML
    void GoToAfficher(ActionEvent event) {
        try {
            // Load the Event.fxml file
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/AfficherTransport.fxml"));
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
    void GoTotranspoteur(ActionEvent event) {
        try {
            // Load the Event.fxml file
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/ajoutertranspoteur.fxml"));
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

   }
