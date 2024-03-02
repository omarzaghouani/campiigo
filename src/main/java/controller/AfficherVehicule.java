package controller;


import entites.Vehicule;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import service.VehiculeService;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class AfficherVehicule {
    @FXML
    private ImageView imagevi;
    @FXML
    private Button buuton_im;
    @FXML
    private Button button_sta;
    @FXML
    private Button button_ajouter;

    @FXML
    private Button button_supp;

    @FXML
    private Button buttonafficher;

    @FXML
    private Button buttonmodifier;

    @FXML
    private Button buttontransport;

    @FXML
    private Button buttontranspoteur;

    @FXML
    private Button buttonvehicule;

    @FXML
    private TableColumn<?, ?> colcapacite;

    @FXML
    private TableColumn<?, ?> colnum_ch;

    @FXML
    private TableColumn<?, ?> colnum_v;

    @FXML
    private TableColumn<?, ?> colprixuni;

    @FXML
    private TableColumn<?, ?> coltype;

    @FXML
    private TableView<Vehicule> tableview;

    @FXML
    private TextField txtnum_ch_supp;
    private final VehiculeService  VehiculeService =new VehiculeService() ;
    private File selectedImageFile;

    public void initialize() {
        // Initialise les colonnes du TableView

        colnum_v.setCellValueFactory(new PropertyValueFactory<>("num_v"));
        coltype.setCellValueFactory(new PropertyValueFactory<>("type"));
        colcapacite.setCellValueFactory(new PropertyValueFactory<>("capacite"));
        colprixuni.setCellValueFactory(new PropertyValueFactory<>("prixuni"));
        colnum_ch.setCellValueFactory(new PropertyValueFactory<>("num_ch"));

        // Charge les données dans le TableView à partir du service
        loadVehicule();
    }
    private void loadVehicule() {
        List<Vehicule> vehicules= VehiculeService.readAll();
        ObservableList<Vehicule> observableList = FXCollections.observableArrayList(vehicules);
        tableview.setItems(observableList);
    }


    @FXML
    void supprimervehicule(ActionEvent event) {
        try {
            int num_v = Integer.parseInt(txtnum_ch_supp.getText());
            VehiculeService psv = new VehiculeService() {
            };
            Vehicule v = psv.readBynum_v(num_v);
            if (v != null) {
                psv.delete(v);
            } else {
                System.out.println("Le vehicule avec num_v" + num_v + " n'existe pas.");
            }
        } catch (NumberFormatException e) {
            // Gérer l'exception si la conversion en nombre échoue
            e.printStackTrace();
        }
    }

    @FXML
    void GoToAdd(ActionEvent event) {
        try {
            // Load the Event.fxml file
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/AjouterVehicule.fxml"));
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

    private PDFGenerator pdfGenerator = new PDFGenerator();

    @FXML
    void generatePDF(ActionEvent event) {
        Vehicule vehicule = tableview.getSelectionModel().getSelectedItem();
        if (vehicule != null) {
            PDFGenerator pdfGenerator = new PDFGenerator(); // Créez une instance de PDFGenerator
            String filePath = "C:\\Users\\omarz\\OneDrive\\Bureau\\xampp\\htdocs\\connexioncapmping3a16\\pdf\\fichier.pdf"; // Spécifiez le chemin du fichier PDF à créer
            pdfGenerator.generateVehiculePDF(vehicule, filePath, "C:/Users/omarz/OneDrive/Bureau/xampp/htdocs/connexioncapmping3a16/image/lm.png");
            System.out.println("PDF généré avec succès !");
        } else {
            System.out.println("Sélectionnez un vehicule avant de générer le PDF.");
        }
    }


    @FXML
    void GoToUpdate(ActionEvent event) {
        try {
            // Load the Event.fxml file
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Updatevehicule.fxml"));
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
    void stave(ActionEvent event) {
        try {
            // Load the Event.fxml file
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/chart.fxml"));
            javafx.scene.Parent root = loader.load();

            // Show the AfficherUser.fxml scene
            Stage stage = (Stage) button_sta.getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
    @FXML
    void ajouterImage(ActionEvent event) {
        // Get the source node of the event
        Node sourceNode = (Node) event.getSource();
        if (sourceNode != null && sourceNode.getScene() != null) {
            Scene scene = sourceNode.getScene();

            // Create a FileChooser to select an image file
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Choisir une image");
            fileChooser.getExtensionFilters().addAll(
                    new FileChooser.ExtensionFilter("Images", "*.png", "*.jpg", "*.jpeg", "*.gif")
            );

            // Show the file chooser dialog and get the selected file
            selectedImageFile = fileChooser.showOpenDialog(scene.getWindow());

            // If a file is selected, load it into the ImageView
            if (selectedImageFile != null) {
                try {
                    // Load the selected image into an Image object
                    Image image = new Image(selectedImageFile.toURI().toString());

                    // Set the Image object to the ImageView
                    imagevi.setImage(image);

                    // Here, you can also process the selected image file further if needed

                } catch (Exception e) {
                    System.out.println("Failed to load the selected image.");
                    e.printStackTrace();
                }
            }
        } else {
            System.out.println("Source node or scene not found.");
        }
    }

}
