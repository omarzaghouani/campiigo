package controller;
import entites.Transport;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import service.TransportService;
import utils.DataSource;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.ResourceBundle;
import javafx.scene.control.TextField;
import javafx.scene.control.DatePicker;
import java.time.LocalDate;

public class AfficherTransport {
    @FXML
    private Button buttontransport;

    @FXML
    private Button buttontranspoteur;

    @FXML
    private Button buttonvehicule;

    public TextField txtnum_ch_supp;
    public Button button_supp;
    public Button button_ajouter;
    public Button buttonmodifier;
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
    private final TransportService TransportService =new TransportService();


    public void initialize() {
        // Initialise les colonnes du TableView

        colnum_t.setCellValueFactory(new PropertyValueFactory<>("num_t"));
        colnum_ch.setCellValueFactory(new PropertyValueFactory<>("num_ch"));
        coldd.setCellValueFactory(new PropertyValueFactory<>("dd"));
        colda.setCellValueFactory(new PropertyValueFactory<>("da"));
        colnum_v.setCellValueFactory(new PropertyValueFactory<>("num_v"));
        colcout.setCellValueFactory(new PropertyValueFactory<>("cout"));

        // Charge les données dans le TableView à partir du service
        loadTansport();
    }

    private void loadTansport() {
        List<Transport> transports=TransportService.readAll();
        ObservableList<Transport> observableList = FXCollections.observableArrayList(transports);
        tableview.setItems(observableList);
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
    void GoTotranspoteur(ActionEvent event) {
        try {
            // Load the Event.fxml file
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/ajoutertranspteur.fxml"));
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


}
