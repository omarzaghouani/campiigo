package controller;

import entities.Session;
import entities.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.Stage;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

public class HomeClientController {
    @FXML
    public Button profileButton;
    @FXML
    public ImageView profileImage;
    public Button uploadButton;
    public MediaView mediaView;
    @FXML
    private ImageView logout;
    @FXML
    public  TextField userNameText;
    @FXML
    public  TextField userEmailText;
    @FXML
    public   TextField userNumTel;
    User user;

    @FXML
    private void GoToProfile() throws IOException {
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
    }
    // Gérer l'exc








        // Initialisation de l'ImageView  ici si nécessaire
        // Initialisation de l'ImageView  ici si nécessaire


        public void initialize (URL location, ResourceBundle resources){

                // Assurez-vous que user est non null avant d'appeler loadUserProfileInfo
                if (user != null) {
                    loadUserProfileInfo();
                }

            // Initialisation de l'ImageView ici si nécessaire
            logout.setOnMouseClicked(this::logoutAction);
            profileButton.setOnAction(event -> {
                try {
                    GoToProfile();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            });

            // Récupérer l'image de profil de l'utilisateur
            Image capturedImage = getUserProfileImage(user.getId());
            if (capturedImage != null) {
                profileImage.setImage(capturedImage);
            }

            // Assurez-vous que user est non null avant d'appeler loadUserProfileInfo
            if (user != null) {
                loadUserProfileInfo();
            }
        }

    @FXML
    private void logoutAction(MouseEvent event) {
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

    private void loadUserProfileImage() {
        if (user != null && user.getPhoto_d() != null) {
            try {
                File file = new File(user.getPhoto_d());
                if (file.exists()) {
                    Image image = new Image(file.toURI().toString());
                    profileImage.setImage(image);
                } else {
                    System.err.println("Le fichier de l'image de profil n'existe pas : " + user.getPhoto_d());
                }
            } catch (Exception e) {
                e.printStackTrace();
                System.err.println("Erreur lors du chargement de l'image de profil : " + e.getMessage());
            }
        }
    }

    private Image getUserProfileImage(int userId) {
        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/campigo", "root", "")) {
            String query = "SELECT photo_d FROM utilisateur WHERE id = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setInt(1, userId);
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    if (resultSet.next()) {
                        byte[] imageBytes = resultSet.getBytes("photo_d");
                        return new Image(new ByteArrayInputStream(imageBytes));
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Erreur lors de la récupération de l'image de profil : " + e.getMessage());
        }
        return null;
    }



    public void handleUploadButtonAction(ActionEvent actionEvent) {

        try {
            // Chemin vers le fichier vidéo dans les ressources de votre projet
            String videoPath = "/Vidéo noire_1.mp4";
            // Charger le fichier vidéo
            URL videoUrl = getClass().getResource(videoPath);
            if (videoUrl == null) {
                System.err.println("Le fichier vidéo " + videoPath + " n'a pas été trouvé.");
                return; // ou gérer l'erreur d'une autre manière
            }

            // Créer un MediaPlayer pour le fichier vidéo
            Media media = new Media(videoUrl.toURI().toString());
            MediaPlayer mediaPlayer = new MediaPlayer(media);

            // Associer le MediaPlayer au MediaView
            mediaView.setMediaPlayer(mediaPlayer);

            // Commencer à jouer le vidéo
            mediaPlayer.play();
        } catch (URISyntaxException e) {
            e.printStackTrace();
            // Gérer l'exception URISyntaxException
        }
    }
    public void setUser(User user) {
        this.user = user;
        loadUserProfileImage();


    }

    private void loadUserProfileInfo() {
        if (user != null) {
            userNameText.setText(user.getNom() );
            userEmailText.setText(user.getEmail());
            userNumTel.setText(String.valueOf(user.getNumerodetelephone()));
            loadUserProfileImage();

        }
    }
}
