package controller;

import entities.utilisateur;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;

public class HomeClientController {
    @FXML
    public Button profileButton;
    @FXML
    public ImageView profileImage;
    @FXML
    private ImageView logout;

    public void initialize() {
        // Initialisation de l'ImageView  ici si nécessaire
        utilisateur user = Session.getInstance().getUser();

        logout.setOnMouseClicked(this::logoutAction);
        profileButton.setOnAction(event -> GoToProfile());
        Image capturedImage = ImageSingleton.getInstance().getCapturedImage();
        if (capturedImage != null) {
            profileImage.setImage(capturedImage);
        }
    }
@FXML
    private void GoToProfile() {
        try {
            // Charger le fichier FXML de l'interface de profil
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Profile.fxml"));
            Parent root = loader.load();

            // Obtenir la scène actuelle
            Stage stage = (Stage) profileButton.getScene().getWindow();

            // Créer une nouvelle scène avec le fichier FXML chargé
            Scene profileScene = new Scene(root);

            // Définir la nouvelle scène sur le stage
            stage.setScene(profileScene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
            // Gérer l'exception, par exemple afficher une alerte
        }
    }



    @FXML
    public void logoutAction(MouseEvent event) {
        try {
                Session.getInstance().destroy();
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/LoginUser.fxml"));
                Parent loginRoot = loader.load();
                Scene loginScene = new Scene(loginRoot);
                Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
                window.setScene(loginScene);
                window.show();
            } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
