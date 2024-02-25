package controller;

import entites.Vehicule;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import service.VehiculeService;
import javafx.scene.image.Image;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class AjouterVehicule implements Initializable {
    @FXML
    private TextField capacite;

    @FXML
    private TextField num_ch;

    @FXML
    private TextField num_v;

    @FXML
    private TextField prixuni;


    @FXML
    private ChoiceBox<String> type;
    private String[] vehicule = {"van famiial", "van horizon", "van aventure", "van voyage", "bus confort", "bus", "voiture4*4"};
    @FXML
    private Button button_afficher;

    @FXML
    private Button button_ajouter;

    @FXML
    private Button button_modifier;

    @FXML
    private Button buttontransport;

    @FXML
    private Button buttontranspoteur;

    @FXML
    private Button buttonvehicule;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        type.getItems().addAll(vehicule);
    }
    /*public AjouterVehicule(TextField capacite, ChoiceBox<String> type, TextField numCh, TextField numV, TextField prixuni) {
        this.capacite = capacite;
        this.type = type;
        this.num_ch = numCh;
       this.num_v = numV;
        this.prixuni = prixuni;
    }
*/

    // Helper method to clear input fields after adding transport
  /*  private void clearFields() {
       num_v.clear();


        capacite.clear();
        num_ch.clear();
        prixuni.clear();
    }*/

    @FXML
    void AjouterVehicule(ActionEvent actionEvent) {
        boolean message;
        try {
            int cpt = Integer.parseInt(capacite.getText());
            int num_ve = Integer.parseInt(num_v.getText());
            int prixunii = Integer.parseInt(prixuni.getText());
            int num = Integer.parseInt(num_ch.getText());

            String selectedType = type.getValue();
            if (selectedType == null || selectedType.isEmpty()) {
                // Display an error message to the user indicating that the type must be selected
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Erreur de saisie");
                alert.setHeaderText(null);
                alert.setContentText("Veuillez sélectionner un type de véhicule!");
                alert.showAndWait();
                return; // Exit the method without adding the vehicle
            }

            Vehicule v = new Vehicule(num_ve, selectedType, cpt, prixunii, num);
            VehiculeService psv = new VehiculeService();
            message = psv.add(v);

            if (!message ) {
                // Display success message to the user
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("vehicule non ajouté");
                alert.setHeaderText(null);
                alert.setContentText("vehicule faild!");
                alert.showAndWait();

                // Clear input fields after adding transport

            }
            else {


                // Display success message to the user
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("vehicule ajouté");
                alert.setHeaderText(null);
                alert.setContentText("vehicule a été ajouté avec succès!");
                alert.showAndWait();

                // Clear input fields after adding transport

            }

        } catch (NumberFormatException e) {
            // Handle invalid input (e.g., non-numeric values)
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur de saisie");
            alert.setHeaderText(null);
            alert.setContentText("Veuillez saisir des valeurs numériques valides!");
            alert.showAndWait();
        }
    }


    @FXML
        void GoTovehicule (ActionEvent event){
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
        void GoToAdd (ActionEvent event){
            try {
                // Load the Event.fxml file
                FXMLLoader loader = new FXMLLoader(getClass().getResource("AjouterVehicule.fxml"));
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
        void GoToUpdate (ActionEvent event){
            try {
                // Load the Event.fxml file
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/Updatevehicule.fxml"));
                javafx.scene.Parent root = loader.load();

                // Show the AfficherUser.fxml scene
                Stage stage = (Stage) button_modifier.getScene().getWindow();
                Scene scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }

        @FXML
        void GoTotranspoteur (ActionEvent event){
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
    void GoToAfficher(ActionEvent event) {
        try {
            // Load the Event.fxml file
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/AffichierVehicule.fxml"));
            javafx.scene.Parent root = loader.load();

            Stage stage = (Stage) button_afficher.getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();//
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




