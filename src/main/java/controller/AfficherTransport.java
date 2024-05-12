package controller;

import entities.Transport;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import services.TransportService;
import javafx.scene.control.Label;
import java.io.IOException;
import java.util.List;
import java.util.Random;

public class AfficherTransport {

    @FXML
    private Label captchaLabel;

    @FXML
    private Button button_stripe;
    @FXML
    private Button buttontransport;

    @FXML
    private Button buttontranspoteur;

    @FXML
    private Button buttonvehicule;
    @FXML
    private TextField captchaTextField;
    @FXML
    private Button captcha_button;
    @FXML

    public TextField txtnum_ch_supp;
    @FXML
    public Button button_supp;
    @FXML
    public Button button_ajouter;
    @FXML
    public Button buttonmodifier;
    @FXML
    public Button buttonafficher;
    @FXML
    private TableColumn<?, ?> colcout;

    @FXML
    private TableColumn<?, ?> colda;

    @FXML
    private TableColumn<?, ?> coldd;

    @FXML
    private TableColumn<?, ?> colnum_ch;

    @FXML
    private TableColumn<?, ?> colnum_t;

    @FXML
    private TableColumn<?, ?> colnum_v;
    @FXML
    private TableView<Transport> tableview;
    @FXML
    private TextField rechercheParnum_ch;
    @FXML
    private Button button_stat;
    private final TransportService TransportService =new TransportService();


    public void initialize() {
        // Initialise les colonnes du TableView

        colnum_t.setCellValueFactory(new PropertyValueFactory<>("num_t"));
        colnum_ch.setCellValueFactory(new PropertyValueFactory<>("num_ch"));
        coldd.setCellValueFactory(new PropertyValueFactory<>("dd"));
        colda.setCellValueFactory(new PropertyValueFactory<>("da"));
        colnum_v.setCellValueFactory(new PropertyValueFactory<>("num_v"));
        colcout.setCellValueFactory(new PropertyValueFactory<>("cout"));

        rechercheParnum_ch.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.trim().isEmpty()) {
                recherchertransport(Integer.parseInt(newValue.trim()));
            }else { loadTansport();}
        });
        // Charge les données dans le TableView à partir du service
        loadTansport();

    }


    private void loadTansport() {
        List<Transport> transports=TransportService.readAll();
        ObservableList<Transport> observableList = FXCollections.observableArrayList(transports);
        tableview.setItems(observableList);
    }

    private void recherchertransport(int num_ch) {
        TransportService t = new TransportService();
        List<Transport> resultatRecherche = t.rechercheParnum_ch(num_ch);
        ObservableList<Transport> listeResultat = FXCollections.observableArrayList(resultatRecherche);
        tableview .setItems(listeResultat);
    }
    @FXML
    public void supprimertransport(ActionEvent actionEvent) {
        try {
            int num_ch = Integer.parseInt(txtnum_ch_supp.getText());
            TransportService ts = new TransportService();
            Transport t = ts.readBynum_ch(num_ch);
            if (t != null) {
                ts.delete(t);
            } else {
                System.out.println("Le transport avec num_ch" + num_ch + " n'existe pas.");
            }
        } catch (NumberFormatException e) {
            // Gérer l'exception si la conversion en nombre échoue
            e.printStackTrace();
        }
    }
    @FXML
    void GoToUpdate(ActionEvent event) {
        try {
            // Load the Event.fxml file
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Updatetransport.fxml"));
            javafx.scene.Parent root = loader.load();

            // Show the AfficherUser.fxml scene
            Stage stage = (Stage) buttonmodifier.getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    @FXML
    void statransport(ActionEvent event) {
        try {

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/statran.fxml"));
            javafx.scene.Parent root = loader.load();


            Stage stage = (Stage) button_stat.getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @FXML
    void stripetransport(ActionEvent event) {
        try {

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/stripe.fxml"));
            javafx.scene.Parent root = loader.load();


            Stage stage = (Stage) button_stripe.getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    @FXML
    void GoToAdd(ActionEvent event) {
        try {
            // Load the Event.fxml file
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/transport.fxml"));
            javafx.scene.Parent root = loader.load();

            // Show the AfficherUser.fxml scene
            Stage stage = (Stage) button_ajouter.getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @FXML
    void GoTotransport(ActionEvent event) {

            try {
                // Load the Event.fxml file
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/transport.fxml"));
                javafx.scene.Parent root = loader.load();

                // Show the AfficherUser.fxml scene
                Stage stage = (Stage) buttontransport.getScene().getWindow();
                Scene scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }



    @FXML
    void GoTotranspoteur(ActionEvent event) {
        try {
            // Load the Event.fxml file
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/ajoutertranspoteur.fxml"));
            javafx.scene.Parent root = loader.load();

            // Show the AfficherUser.fxml scene
            Stage stage = (Stage) buttontranspoteur.getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }



    }

    @FXML
    void GoTovehicule(ActionEvent event) {
        try {
            // Load the Event.fxml file
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/AjouterVehicule.fxml"));
            javafx.scene.Parent root = loader.load();

            // Show the AfficherUser.fxml scene
            Stage stage = (Stage) buttonvehicule.getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }



    }
    private String generateCaptcha(int length) {
        StringBuilder captcha = new StringBuilder();
        Random random = new Random();
        // Caractères possibles pour le captcha
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        for (int i = 0; i < length; i++) {
            // Sélection aléatoire d'un caractère dans la chaîne de caractères
            captcha.append(characters.charAt(random.nextInt(characters.length())));
        }
        return captcha.toString();
    }

    // Méthode pour initialiser et afficher le captcha
    public void initializet() {
        // Générer un captcha de 5 caractères
        String captchaText = generateCaptcha(5);
        // Afficher le captcha dans le label
        captchaLabel.setText(captchaText);
    }

    // Méthode pour vérifier si le captcha saisi correspond au captcha affiché
    @FXML
    void verifyCaptcha() {
        // Récupérer le captcha saisi par l'utilisateur
        String enteredCaptcha = captchaTextField.getText();
        // Récupérer le captcha affiché
        String displayedCaptcha = captchaLabel.getText();

        // Vérifier si le captcha saisi correspond au captcha affiché
        if (enteredCaptcha.equals(displayedCaptcha)) {
            System.out.println("Captcha correct !");
            // Ajouter ici le code à exécuter si le captcha est correct
        } else {
            System.out.println("Captcha incorrect !");
            // Ajouter ici le code à exécuter si le captcha est incorrect
        }
    }
}
