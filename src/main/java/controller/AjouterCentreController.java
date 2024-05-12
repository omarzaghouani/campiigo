package controller;

import entities.Centre;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import services.CentreService;

import java.io.IOException;

public class AjouterCentreController {
    public Button button_mod;
    public Button activite_button;
    public Button button_mail;
    public Button button_utilisateur;
    public WebView mapWebView;
    @FXML
    private TextField txtid_centre;

    @FXML
    private TextField txtnom_centre;

    @FXML
    private TextField txtville;

    @FXML
    private TextField txtprix_centre;

    @FXML
    private TextField txtnum_tel;

    @FXML
    private TextField txtnbre_activite;

    @FXML
    private Button AjouterButton;

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
    void GoToMail(ActionEvent event) {
        try {
            // Load the Event.fxml file
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Captcha.fxml"));
            javafx.scene.Parent root = loader.load();

            // Show the AfficherUser.fxml scene
            Stage stage = (Stage) button_mail.getScene().getWindow();
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
            Stage stage = (Stage) activite_button.getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @FXML
    void GoToAjoutU(ActionEvent event) {
        try {
            // Load the Event.fxml file
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/View.fxml"));
            javafx.scene.Parent root = loader.load();

            // Show the AfficherUser.fxml scene
            Stage stage = (Stage) AjouterButton.getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    @FXML
    void GoToMod(ActionEvent event) {
        try {
            // Load the Event.fxml file
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/modifier.fxml"));
            javafx.scene.Parent root = loader.load();

            // Show the AfficherUser.fxml scene
            Stage stage = (Stage) button_mod.getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @FXML
    public void ajoutercentre(ActionEvent actionEvent) {
        try {
            int id_centre = Integer.parseInt(txtid_centre.getText());
            String nom_centre = txtnom_centre.getText();
            int num_tel = Integer.parseInt(txtnum_tel.getText());
            String mail = txtprix_centre.getText().trim();
            String ville = txtville.getText();
            int nbre_activite = Integer.parseInt(txtnbre_activite.getText());
            // Vérification de la longueur du numéro de téléphone
            if (String.valueOf(num_tel).length() != 8) {
                showAlert("Erreur", "Longueur du numéro de téléphone incorrecte.", "Le numéro de téléphone doit avoir une longueur de 8 chiffres.");
                return;
            }
            String regex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";

            // Vérification de l'email
            if (mail.matches(regex)) {
                Centre c = new Centre(id_centre, nom_centre, ville, mail, num_tel, nbre_activite);
                CentreService cs = new CentreService();
                cs.add(c);
            } else {
                // Email invalide
                showAlert("Adresse email invalide !","Adresse email invalide !","Adresse email invalide !");
            }
            // Vérifier si le nom du centre existe déjà dans la base de données
            CentreService centreService = new CentreService();
            if (centreService.checkIfCentreExists(nom_centre)) {
                // Afficher une fenêtre d'alerte si le nom du centre existe déjà
                showAlert("Erreur", "Le nom du centre existe déjà.", "Veuillez saisir un nom de centre différent.");
            } else {
                Centre c = new Centre(id_centre, nom_centre, ville, mail, num_tel, nbre_activite);
                CentreService cs = new CentreService();
                cs.add(c);
            }
        } catch (NumberFormatException e) {
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


    private void loadMap(String clubName, String governorate, String city) {
        WebEngine webEngine = mapWebView.getEngine();

        // Generate HTML content with the correct map URL
        String htmlContent = generateMapHtml(clubName, governorate, city);

        // Load the HTML content into the WebView
        webEngine.loadContent(htmlContent);
    }

    private String generateMapHtml(String clubName, String governorate, String city) {
        // Construct the map URL based on the club name, governorate, and city
        String mapUrl = "https://maps.google.com/maps?q=" +
                clubName.replace(" ", "%20") + ",%20" +
                governorate.replace(" ", "%20") + ",%20" +
                city.replace(" ", "%20") + "&t=k&z=16&output=embed";

        // Generate HTML content with the correct map URL
        return "<!DOCTYPE html>\n" +
                "<html>\n" +
                "<head>\n" +
                "    <title>Google Maps Example</title>\n" +
                "    <style>\n" +
                "        /* Adjust the size and position of the map */\n" +
                "        #mapouter {\n" +
                "            position: relative;\n" +
                "            text-align: right;\n" +
                "            height: 500px; /* Adjust the height as needed */\n" +
                "            width: 500px; /* Adjust the width as needed */\n" +
                "        }\n" +
                "\n" +
                "        #gmap_canvas2 {\n" +
                "            overflow: hidden;\n" +
                "            background: none !important;\n" +
                "            height: 500px; /* Adjust the height as needed */\n" +
                "            width: 500px; /* Adjust the width as needed */\n" +
                "        }\n" +
                "\n" +
                "        #gmap_canvas {\n" +
                "            width: 100%;\n" +
                "            height: 100%;\n" +
                "            border: 0;\n" +
                "            margin: 0;\n" +
                "            padding: 0;\n" +
                "        }\n" +
                "    </style>\n" +
                "</head>\n" +
                "<body>\n" +
                "<div id=\"mapouter\">\n" +
                "    <div id=\"gmap_canvas2\">\n" +
                "        <iframe id=\"gmap_canvas\"\n" +
                "                src=\"" + mapUrl + "\" frameborder=\"0\" scrolling=\"no\" marginheight=\"0\" marginwidth=\"0\"></iframe>\n" +
                "    </div>\n" +
                "</div>\n" +
                "</body>\n" +
                "</html>";
    }





}
