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

public class AjouterActiviteController {
    public Button centre_button;
    public Button ajoutactivite;
    public TextField texttype;
    public TextArea textdescription;
    public TextField txtnom_activite;
    public TextField txtid_activite;
    public Button button_modifier;
    public Button button_view;
    public TextField textid_centre;
    public Button button_utilisateur;

    int id_centre;
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
            Stage stage = (Stage) centre_button.getScene().getWindow();
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
            Stage stage = (Stage) button_view.getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    @FXML
    void GoToModifierActivite(ActionEvent event) {
        try {
            // Load the Event.fxml file
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/modifieractivite.fxml"));
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
    public void ajouteractivite(ActionEvent actionEvent) {
        try {
            int id_activite = Integer.parseInt(txtid_activite.getText());
            String nom_activite = txtnom_activite.getText();
            String description = textdescription.getText();
            String type = texttype.getText();
            int id_centre = Integer.parseInt(textid_centre.getText());

                //int id_activite = Integer.parseInt(txtid_activite);
                if (id_activite <= 0) {
                    showAlert("L'ID doit être un entier positif.","L'ID doit être un entier positif.","L'ID doit être un entier positif.");
                    return;
                }

                else {


                    // Obtenez l'objet Centre correspondant à l'id_centre
                    CentreService centreService = new CentreService(); // Suppose que vous avez un service pour manipuler les centres
                    Centre centre = centreService.getCentreById(id_centre);

                    // Créez l'objet Activite avec l'objet Centre obtenu
                    Activite a = new Activite(id_activite, nom_activite, description, type, centre);

                    // Ajoutez l'activité en utilisant le service ActiviteService
                    ActiviteService as = new ActiviteService();
                    as.add(a);
                }
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
