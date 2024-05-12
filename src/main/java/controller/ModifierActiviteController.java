package controller;

import entities.Activite;
import entities.Centre;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import services.ActiviteService;
import services.CentreService;

import java.io.IOException;

public class ModifierActiviteController {
    public TextField txt_id_activite;
    public TextField txt_nom_activite;
    public TextField texttype;
    public TextArea txtdescription;
    public Button button_activite_mod;
    public Button button_centre;
    public Button view_button;
    public Button ajouter_button;
    public TextField txtid_centre;

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
            Stage stage = (Stage) button_centre.getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    @FXML
    void GoToAjouterActivite(ActionEvent event) {
        try {
            // Load the Event.fxml file
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/ajouteractivite.fxml"));
            javafx.scene.Parent root = loader.load();

            // Show the AfficherUser.fxml scene
            Stage stage = (Stage) view_button.getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    @FXML
    void GoToViewActivite(ActionEvent event) {
        try {
            // Load the Event.fxml file
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/viewactivite.fxml"));
            javafx.scene.Parent root = loader.load();

            // Show the AfficherUser.fxml scene
            Stage stage = (Stage) ajouter_button.getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    @FXML
    public void modifieractivite(ActionEvent actionEvent) {
        try {
            int id_activite = Integer.parseInt(txt_id_activite.getText());
            String nom_activite = txt_nom_activite.getText();
            String description = txtdescription.getText();
            String type = texttype.getText();
            int id_centre = Integer.parseInt(txtid_centre.getText());
            CentreService centreService = new CentreService(); // Suppose que vous avez un service pour manipuler les centres
            Centre centre = centreService.getCentreById(id_centre);
            if (id_activite <= 0) {
                showAlert("L'ID doit être un entier positif.","L'ID doit être un entier positif.","L'ID doit être un entier positif.");
                return;
            }

            // Créez l'objet Activite avec l'objet Centre obtenu
            Activite a = new Activite(id_activite, nom_activite, description, type, centre);

            // Ajoutez l'activité en utilisant le service ActiviteService
            ActiviteService as = new ActiviteService();
            as.update(a);
        } catch (NumberFormatException e) {
            // Gérer l'exception si la conversion en nombre échoue
            e.printStackTrace();
        }
    }
    private void showAlert(String title, String headerText, String contentText) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(headerText);
        alert.setContentText(contentText);
        alert.showAndWait();
    }

}


