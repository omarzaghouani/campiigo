package test;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Mainfx extends Application {

    @Override
    public void start(Stage primaryStage) {
        try {
            // Load the FXML file from the resources/Main directory
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/AfficherUser.fxml"));
            Parent root = loader.load();

            // Set up the stage
            primaryStage.setTitle("afficher Utilisateur");
            primaryStage.setScene(new Scene(root, 800, 600));

            // Show the stage
            primaryStage.show();

            // Hack to access the protected field in Lighting


        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
