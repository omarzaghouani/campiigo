package controller;

import com.github.sarxos.webcam.Webcam;
import entities.ImageSingleton;
import entities.Session;
import entities.User;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import services.UserService;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class ProfileController implements Initializable {

    @FXML
    public ImageView logout;
    @FXML
    public ImageView profileImageView; // Changé pour être une instance non statique
    @FXML
    public Button captureButton;
    @FXML
    public Button validerButton;
    @FXML
    public Button homeClient;
    @FXML
    private TextField userNameText;
    @FXML
    private TextField userEmailText;
    @FXML
    private TextField userNumTel;
    User CurrentUser;

    private File selectedFile; // Field to store the selected file


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        User user = Session.getInstance().getUser();
        CurrentUser = user;
        if (user != null) {
            // Log des données de l'utilisateur pour vérifier qu'elles sont correctement récupérées
            System.out.println("Utilisateur connecté : " + user.getNom() + " " + user.getPrenom());
            System.out.println("Email : " + user.getEmail());
            System.out.println("Role : " + user.getRoles().toString());

           // userNameText.setText(user.getNom() + " " + user.getPrenom());
            userNameText.setText(user.getNom());
            userEmailText.setText(user.getEmail());
            userNumTel.setText(String.valueOf(user.getNumerodetelephone()));

            //   userRoleText.setText(user.getRoles());
        } else {
            System.out.println("Aucun utilisateur connecté.");
            // Gérer le cas où aucun utilisateur n'est connecté
        }

        captureButton.setOnAction(this::capturePhoto);
        Image capturedImage = ImageSingleton.getInstance().getCapturedImage();
        if (capturedImage != null) {
            profileImageView.setImage(capturedImage);
        }
        // Supposons que vous avez une méthode capturePhoto qui retourne une Image

    }

  /*  @FXML
    private Image capturePhoto(ActionEvent actionEvent) {
        // Sélectionnez la première webcam disponible
        Webcam webcam = Webcam.getWebcams().get(0);
        if (webcam == null) {
            // Gérer le cas où aucune webcam n'est disponible
            System.err.println("Aucune webcam disponible.");
            return null;
        }
        webcam.open();

        Image fxImage = null;
        try {
            // Capturez une image
            BufferedImage image = webcam.getImage();

            // Convertissez l'image AWT en Image JavaFX
            fxImage = SwingFXUtils.toFXImage(image, null);

            // Définissez l'image sur le ImageView
            profileImageView.setImage(fxImage);

            // Enregistrez l'image dans la base de données et mettez à jour l'utilisateur
            UserService us = new UserService();
            User user = Session.getInstance().getUser();
            if (user != null) {
                // Convertissez l'image JavaFX en byte[] pour l'enregistrement dans la base de données
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                ImageIO.write(SwingFXUtils.fromFXImage(fxImage, null), "jpg", baos);
                byte[] imageBytes = baos.toByteArray();

                // Mettre à jour l'utilisateur avec la nouvelle photo
                user.setPhoto_d(Arrays.toString(imageBytes));
                us.modifier(user); // Mettre à jour l'utilisateur dans la base de données

                // Mettre à jour l'image affichée dans l'interface
                Image capturedImage = fxImage;
                ImageSingleton.getInstance().setCapturedImage(capturedImage);
            }
        } catch (Exception e) {
            // Gérer les exceptions liées à la capture d'image
            e.printStackTrace();
        } finally {
            // N'oubliez pas de fermer la webcam après utilisation
            webcam.close();
        }
        return fxImage;
    }
*/

    @FXML
    private String capturePhoto(ActionEvent actionEvent) {
        // Specify the directory where you want to save the image
        String directory = "C:/Users/DELL/Desktop/chayma/";

        // Sélectionnez la première webcam disponible
        Webcam webcam = Webcam.getWebcams().get(0);
        if (webcam == null) {
            // Gérer le cas où aucune webcam n'est disponible
            System.err.println("Aucune webcam disponible.");
            return null;
        }
        webcam.open();

        String imagePath = null;
        try {
            // Capturez une image
            BufferedImage image = webcam.getImage();

            // Generate a unique filename for the image
            String filename = "captured_image_" + System.currentTimeMillis() + ".jpg";

            // Combine the directory and filename to get the full path
            imagePath = directory + filename;

            // Write the image data to a file
            File outputFile = new File(imagePath);
            ImageIO.write(image, "jpg", outputFile);

            // Définissez l'image sur le ImageView
            Image fxImage = SwingFXUtils.toFXImage(image, null);
            profileImageView.setImage(fxImage);

            // Enregistrez l'image dans la base de données et mettez à jour l'utilisateur
            UserService us = new UserService();
            User user = Session.getInstance().getUser();
            if (user != null) {
                // Mettre à jour l'utilisateur avec le chemin de l'image
                user.setPhoto_d(imagePath);
                us.modifier(user); // Mettre à jour l'utilisateur dans la base de données
            }
        } catch (Exception e) {
            // Gérer les exceptions liées à la capture d'image
            e.printStackTrace();
        } finally {
            // N'oubliez pas de fermer la webcam après utilisation
            webcam.close();
        }
        return imagePath;
    }
    public void setUser(User user) {
        // Appeler la méthode pour initialiser l'interface utilisateur avec les informations de l'utilisateur
        initialize(null, null);
    }

    public void EditProfile(ActionEvent actionEvent) throws IOException, SQLException {
        String numeroDeTelephoneText = userNumTel.getText();
        String email = userEmailText.getText();
        String name = userNameText.getText();
        if (name.isEmpty() || email.isEmpty() || numeroDeTelephoneText.isEmpty()) {
            showAlert("Veuillez remplir tous les champs.", Alert.AlertType.ERROR);
            return;
        }
        if (!isValidEmail(email)) {
            showAlert("Veuillez entrer une adresse email valide.", Alert.AlertType.ERROR);
            return;
        }

        // Validate phone number format (8 digits)
        if (!isValidPhoneNumber(numeroDeTelephoneText)) {
            showAlert("Veuillez entrer un numéro de téléphone valide (8 chiffres).", Alert.AlertType.ERROR);
            return;
        }
        int num = Integer.parseInt(numeroDeTelephoneText);
        CurrentUser.setEmail(email);
        CurrentUser.setNom(name);
        CurrentUser.setNumerodetelephone(num);
        UserService us = new UserService();
        us.modifier(CurrentUser);
        Session.getInstance().setUser(CurrentUser);

        showAlert("Utilisateur Modifié avec succès", Alert.AlertType.INFORMATION);
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/HomeClient.fxml"));

        Parent loginParent = loader.load();
        HomeClientController homeController = loader.getController();
        homeController.setUser(CurrentUser);
        Scene loginScene = new Scene(loginParent);

        // Get the stage information
        Stage window = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();

        // Set the new scene onto the stage
        window.setScene(loginScene);
        window.show();


    }


    private void showAlert(String message, Alert.AlertType alertType) {
        Alert alert = new Alert(alertType);
        alert.setContentText(message);
        alert.showAndWait();
    }

    private boolean isValidEmail(String email) {
        // You can use a more sophisticated email validation regex if needed
        return email.matches("[a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}");
    }

    // Validate phone number format (8 digits)
    private boolean isValidPhoneNumber(String phoneNumber) {
        return phoneNumber.matches("\\d{8}");
    }


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
        // Implémenter la logique pour déconnecter l'utilisateur
    }


    public void validerPhoto(ActionEvent actionEvent) {
        // Supposons que vous avez une méthode captureImageFromWebcam qui retourne une Image
        Image capturedImage = profileImageView.getImage();
        ImageSingleton.getInstance().setCapturedImage(capturedImage);

        // Mettre à jour l'ImageView dans Profile.fxml
        profileImageView.setImage(capturedImage);

    }

    public void returnToHomeC(ActionEvent actionEvent) throws IOException {
        // Implémenter la logique pour modifier le profil
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/HomeClient.fxml"));
        Parent root = loader.load();

        // Obtenir la scène actuelle
        Stage stage = (Stage) homeClient.getScene().getWindow();

        // Créer une nouvelle scène avec le fichier FXML chargé
        Scene profileScene = new Scene(root);

        // Définir la nouvelle scène sur le stage
        stage.setScene(profileScene);
        stage.show();

    }

}
