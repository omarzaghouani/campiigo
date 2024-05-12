package test;

import controller.AfficherUtilisateurController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import services.UserService;
import utils.DataSource;

import java.io.IOException;
import java.sql.Connection;

public class Mainfx extends Application {
    public static void main(String[] args) {
        // Initialize utilisateurServices before launching the JavaFX application
        UserService.initialize();

        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        try {
            // Initialize the connection to the database from DataSource
            Connection connexion = DataSource.getInstance().getCnx();

            // Create an instance of AfficherUtilisateurController passing the connection
            AfficherUtilisateurController controller = new AfficherUtilisateurController(connexion);

            // Load the FXML file using the controller you just created
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/LoginUser.fxml"));
            Parent root = loader.load();

            Scene scene = new Scene(root);

            primaryStage.setTitle("Votre Application");
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
