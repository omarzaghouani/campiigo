package controller;

import entities.Centre;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import services.CentreService;

import java.io.IOException;

public class ModifierCentreController {
    public TextField txtid_centre_mod;
    public TextField txtnom_centre_mod;
    public TextField txtville_mod;
    public TextField txtnum_tel_mod;
    public TextField txtprix_centre_mod;
    public TextField txtnb_activite_mod;
    public Button button_ajout;
    public Button button_view;
    public Button buttonactivite;

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
    void GoToActivite(ActionEvent event) {
        try {
            // Load the Event.fxml file
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/ajouteractivite.fxml"));
            javafx.scene.Parent root = loader.load();

            // Show the AfficherUser.fxml scene
            Stage stage = (Stage) buttonactivite.getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @FXML
    void GoToView(ActionEvent event) {
        try {
            // Load the Event.fxml file
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/View.fxml"));
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
    void GoToAjout(ActionEvent event) {
        try {
            // Load the Event.fxml file
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/ajouter.fxml"));
            javafx.scene.Parent root = loader.load();

            // Show the AfficherUser.fxml scene
            Stage stage = (Stage) button_ajout.getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    @FXML
    public void modifier(ActionEvent actionEvent) {
        try {
            int id_centre = Integer.parseInt(txtid_centre_mod.getText());
            String nom_centre = txtnom_centre_mod.getText();
            int num_tel = Integer.parseInt(txtnum_tel_mod.getText());
            String mail = txtprix_centre_mod.getText();
            String ville = txtville_mod.getText();
            int nbre_activite = Integer.parseInt(txtnb_activite_mod.getText());


            String regex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
            // Vérification de l'email
            if (mail.matches(regex)) {
                Centre c = new Centre(id_centre, nom_centre, ville, mail, num_tel, nbre_activite);
                CentreService cs = new CentreService();
                cs.update(c);
            } else {
                // Email invalide
                showAlert("Adresse email invalide !","Adresse email invalide !","Adresse email invalide !");
            }

            if (String.valueOf(num_tel).length() != 8) {
                showAlert("Erreur", "Longueur du numéro de téléphone incorrecte.", "Le numéro de téléphone doit avoir une longueur de 8 chiffres.");
                return; // Arrêter l'exécution de la méthode si la longueur est incorrecte
            }
            CentreService centreService = new CentreService();
            if (centreService.checkIfCentreExists(nom_centre)) {
                // Afficher une fenêtre d'alerte si le nom du centre existe déjà
                showAlert("Erreur", "Le nom du centre existe déjà.", "Veuillez saisir un nom de centre différent.");
            }
            Centre c = new Centre(id_centre, nom_centre, ville, mail, num_tel, nbre_activite);
            CentreService cs = new CentreService();
            cs.update(c);


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
