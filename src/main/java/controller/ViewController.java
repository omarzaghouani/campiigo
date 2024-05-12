package controller;

import entities.Centre;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import services.CentreService;

import java.io.IOException;
import java.util.Comparator;
import java.util.List;

public class ViewController {
    @FXML
    public Button button_Mod;
    public Button button_centre;
    public Button buttonactivite;
    public Button supprimerbouton;
    public TextField textNomCentre;
    public ChoiceBox<String> sortCriteriaChoiceBox;
    public Button generatePDFButton;
    public ChoiceBox<String> villeChoiceBox;
    public Button button_utilisateur;
    @FXML
    private TextField textid_centre;
    @FXML
    private Button ajouter;
    @FXML
    private TableView<Centre> tableview;

    @FXML
    private TableColumn<Centre, Integer> colid_centre;

    @FXML
    private TableColumn<Centre, String> colnom_centre;

    @FXML
    private TableColumn<Centre, String> colville;

    @FXML
    private TableColumn<Centre, String> colprix_centre;

    @FXML
    private TableColumn<Centre, String> colnum_tel;

    @FXML
    private TableColumn<Centre, Integer> colnbre_activite;


    private final CentreService centreService = new CentreService();


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
    void generatePDF(ActionEvent event) {
        Centre centre = tableview.getSelectionModel().getSelectedItem();
        if (centre != null) {
            String filePath = "C:\\Users\\DELL\\Downloads\\pdf/fichier.pdf";
            // Spécifiez le chemin du fichier PDF à créer
            // Appel à votre générateur de PDF ici
            // pdfGenerator.generateCentrePDF(centre, filePath);
            System.out.println("PDF généré avec succès !");
        } else {
            System.out.println("Sélectionnez un centre avant de générer le PDF.");
        }
    }

    @FXML
    void GoToAjout(ActionEvent event) {
        loadFXML("/ajouter.fxml");
    }

    @FXML
    void GoToActivite(ActionEvent event) {
        loadFXML("/ajouteractivite.fxml");
    }

    @FXML
    void GoToMod(ActionEvent event) {
        loadFXML("/modifier.fxml");
    }

    private void loadFXML(String fxmlPath) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlPath));
            javafx.scene.Parent root = loader.load();
            Stage stage = (Stage) ajouter.getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void initialize() {
        ObservableList<String> sortCriteria = FXCollections.observableArrayList("ID", "Nom", "Ville");
        sortCriteriaChoiceBox.setItems(sortCriteria);
        sortCriteriaChoiceBox.getSelectionModel().selectFirst();
        sortCriteriaChoiceBox.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                switch (newValue) {
                    case "ID":
                        sortByID();
                        break;
                    case "Nom":
                        sortByNom();
                        break;
                    case "Ville":
                        sortByVille();
                        break;
                }
            }
        });

        ObservableList<String> villeList = FXCollections.observableArrayList("jandouba", "beja", "benzart", "nabeul", "zaghouen");
        villeChoiceBox.setItems(villeList);
        villeChoiceBox.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                filterByVille(newValue);
            }
        });

        initializet();
    }

    private void initializet() {
        colid_centre.setCellValueFactory(new PropertyValueFactory<>("id_centre"));
        colnom_centre.setCellValueFactory(new PropertyValueFactory<>("nom_centre"));
        colville.setCellValueFactory(new PropertyValueFactory<>("ville"));
        colprix_centre.setCellValueFactory(new PropertyValueFactory<>("mail"));
        colnum_tel.setCellValueFactory(new PropertyValueFactory<>("num_tel"));
        colnbre_activite.setCellValueFactory(new PropertyValueFactory<>("nbre_activite"));
        loadCentres();
    }

    private void loadCentres() {
        List<Centre> centres = centreService.readAll();
        ObservableList<Centre> observableList = FXCollections.observableArrayList(centres);
        tableview.setItems(observableList);
    }

    @FXML
    public void supprimer(ActionEvent actionEvent) {
        try {
            int id_centre = Integer.parseInt(textid_centre.getText());
            CentreService cs = new CentreService();
            Centre c = cs.readById(id_centre);
            if (c != null) {
                cs.delete(c);
                loadFXML("/view.fxml");
            } else {
                System.out.println("Le centre avec l'ID " + id_centre + " n'existe pas.");
            }
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
    }

    private void sortByID() {
        ObservableList<Centre> centres = tableview.getItems();
        centres.sort(Comparator.comparingInt(Centre::getId_centre));
        tableview.setItems(centres);
    }

    private void sortByNom() {
        ObservableList<Centre> centres = tableview.getItems();
        centres.sort(Comparator.comparing(Centre::getNom_centre));
        tableview.setItems(centres);
    }

    private void sortByVille() {
        ObservableList<Centre> centres = tableview.getItems();
        centres.sort(Comparator.comparing(Centre::getVille));
        tableview.setItems(centres);
    }

    private void filterByVille(String ville) {
        ObservableList<Centre> centres = tableview.getItems();
        ObservableList<Centre> centresFiltres = FXCollections.observableArrayList();
        for (Centre centre : centres) {
            if (centre.getVille().equals(ville)) {
                centresFiltres.add(centre);
            }
        }
        tableview.setItems(centresFiltres);
    }
}
