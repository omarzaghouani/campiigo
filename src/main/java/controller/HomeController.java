package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.MenuButton;
import javafx.scene.control.Tooltip;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;

public class HomeController {

    public Button LogIn;
    @FXML
    private MenuButton aboutus;

    @FXML
    private MenuButton contactus;

    @FXML
    private MenuButton ourservices;

    @FXML
    private Text text1;

    @FXML
    private Text text2;
    @FXML
    private Button SignUp;

    @FXML
    private void initialize() {
        setButtonTooltip(aboutus, "Campigo a pour objectif de rationaliser \nla gestion des centres de camping en automatisant \nplusieurs processus, vous offrant ainsi une expérience\n plus fluide et agréable en tant que gestionnaires ou même campeurs.");
        setButtonTooltip(ourservices, "Gestion des Utilisateurs \n Gestion des Centres \n Gestion des Installations \n Gestion des Emplacements de camping \n Gestion des Equipements \n Gestion de Transport");
        setButtonTooltip(contactus, "Notre Numero : 54224709\nFacebook: Campigo\nInstagram: Campigo");

        // Ajouter d'autres initialisations ici...
    }

    private void setButtonTooltip(MenuButton button, String text) {
        Tooltip tooltip = new Tooltip(text);
        button.setTooltip(tooltip);
        button.setOnMouseEntered(event -> handleMouseEntered(button, tooltip));
        button.setOnMouseExited(event -> handleMouseExited(button, tooltip));
    }

    private void handleMouseEntered(MenuButton button, Tooltip tooltip) {
        tooltip.show(button, button.localToScreen(0, 0).getX() + button.getWidth(), button.localToScreen(0, 0).getY());
    }

    private void handleMouseExited(MenuButton button, Tooltip tooltip) {
        tooltip.hide();
    }

    // Méthode pour gérer l'événement onMouseEntered pour contactus
    @FXML
    private void handleContactEntered() {
        handleMouseEntered(contactus, contactus.getTooltip());
    }

    // Méthode pour gérer l'événement onMouseExited pour contactus
    @FXML
    private void handleContctExited() {
        handleMouseExited(contactus, contactus.getTooltip());
    }

    // Méthode pour gérer l'événement onMouseEntered pour ourservices
    @FXML
    private void handleServicesEntered() {
        handleMouseEntered(ourservices, ourservices.getTooltip());
    }

    // Méthode pour gérer l'événement onMouseExited pour ourservices
    @FXML
    private void handleServicesExited() {
        handleMouseExited(ourservices, ourservices.getTooltip());
    }

    public void handleaboutusExited(MouseEvent event) {
    }

    public void handleaboutusEntered(ActionEvent actionEvent) {
    }

   

    public void GoSignup(ActionEvent actionEvent) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/AjouterUser.fxml"));
            Parent root = loader.load();
            ;
            Stage stage = (Stage) SignUp.getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void GoToLogIn(ActionEvent actionEvent) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/LoginUser.fxml"));
            Parent root = loader.load();
            
            Stage stage = (Stage) LogIn.getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

