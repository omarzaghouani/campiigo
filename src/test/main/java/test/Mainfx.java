package test;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import controller.ajouterutilisateurController;

import java.io.IOException;

public class Mainfx extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/ajouterutilisateur.fxml"));
            Parent root = loader.load();

            // Accédez au contrôleur si nécessaire
            ajouterutilisateurController controller = loader.getController();
            // Vous pouvez maintenant appeler des méthodes sur le contrôleur si nécessaire

            Scene scene = new Scene(root);
            primaryStage.setTitle("Ajouter Utilisateur");
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
