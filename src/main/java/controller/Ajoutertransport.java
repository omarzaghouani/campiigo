package controller;

import entites.Transport;

import entites.Vehicule;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.TextField;
import javafx.scene.control.TableColumn;
import javafx.stage.Stage;
import service.TransportService;
import service.VehiculeService;
import java.util.List;
import java.net.URL;
import java.time.LocalDate;

import java.io.IOException;
import java.util.ResourceBundle;

public class Ajoutertransport {
    @FXML
    private ChoiceBox<Integer> idvehicule;
    @FXML
    public Button button_ajouter;
    @FXML
    public Button button_modifier;
    @FXML
    public Button button_afficher;
    @FXML
    public Button buttontransport;
    @FXML
    public Button buttontranspoteur;
    @FXML
    public Button buttonvehicule;
    @FXML
    private TextField cout;

    @FXML
    private DatePicker daId;

    @FXML
    private DatePicker ddId;

    @FXML
    private TextField num_ch;

    @FXML
    private TextField num_v;
    @FXML
    private TableView<Transport> tableview;
    @FXML
    private TableColumn<?, ?> colcapacite;

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
    private TableColumn<?, ?> coltype;



    @FXML
    private TableColumn<?, ?> coutcout;

    @FXML
    private TableView<Integer> vehiculeTableView;

    private final TransportService transportService = new TransportService();
    private final VehiculeService vehiculeService = new VehiculeService();

    public void initialize(URL url, ResourceBundle resourceBundle) {
         VehiculeService num_v= new VehiculeService();
    populateVehiculeTableView(vehiculeTableView);
    populateVehiculeTableView(vehiculeTableView);
        initTableColumns();
        loadTransport();
        populateVehiculeTableView(vehiculeTableView);

    }

    private void initTableColumns() {
        // Transport table columns
        colnum_ch.setCellValueFactory(new PropertyValueFactory<>("num_ch"));
        colda.setCellValueFactory(new PropertyValueFactory<>("da"));
        coldd.setCellValueFactory(new PropertyValueFactory<>("dd"));
        colnum_v.setCellValueFactory(new PropertyValueFactory<>("num_v"));
        coutcout.setCellValueFactory(new PropertyValueFactory<>("cout"));

        // Vehicule table columns
        colcapacite.setCellValueFactory(new PropertyValueFactory<>("capacite"));
        colda.setCellValueFactory(new PropertyValueFactory<>("da"));
        coldd.setCellValueFactory(new PropertyValueFactory<>("dd"));
        colnum_ch.setCellValueFactory(new PropertyValueFactory<>("num_ch"));
        colnum_t.setCellValueFactory(new PropertyValueFactory<>("num_t"));
        colnum_v.setCellValueFactory(new PropertyValueFactory<>("num_v"));
        coltype.setCellValueFactory(new PropertyValueFactory<>("type"));
        coutcout.setCellValueFactory(new PropertyValueFactory<>("cout"));
    }


    private void loadTransport() {
        /*VehiculeService vehiculeService = new VehiculeService();
        List<Vehicule>  vehicules = vehiculeService.readAll();
        ObservableList<Vehicule> observableList = FXCollections.observableArrayList();
        tableview.setItems(observableList);*/
        List<Transport> transports = transportService.readAll();
        ObservableList<Transport> transportList = FXCollections.observableArrayList(transports);
        tableview.setItems(transportList);
    }
    public void populateVehiculeTableView(TableView<Integer> tableview) {
        VehiculeService vehiculelist = new VehiculeService();
        List<Vehicule> vehicules = vehiculelist.readAll();
        ObservableList<Integer> num_v = FXCollections.observableArrayList();

        for (Vehicule num : vehicules) {
            num_v.add(num.getNum_v());
            System.out.println("Added vehicle ID: " + num.getNum_v());

            vehiculeTableView.setItems(num_v);

        /*colcapacite.setCellValueFactory(new PropertyValueFactory<>("capacite"));
        colda.setCellValueFactory(new PropertyValueFactory<>("da"));
        coldd.setCellValueFactory(new PropertyValueFactory<>("dd"));
        colnum_ch.setCellValueFactory(new PropertyValueFactory<>("num_ch"));
        colnum_t.setCellValueFactory(new PropertyValueFactory<>("num_t"));
        colnum_v.setCellValueFactory(new PropertyValueFactory<>("num_v"));
        coltype.setCellValueFactory(new PropertyValueFactory<>("type"));
        coutcout.setCellValueFactory(new PropertyValueFactory<>("cout"));

        vehiculeTableView.getItems().clear();
        vehiculeTableView.getItems().addAll(vehicules);*/

        }

    /*public void populatenum_vComboBox() {

        VehiculeService v = new VehiculeService();
        List<Vehicule> vehicules = null;

        vehicules = v.readAll();
        // Print the size of vehicules list for debugging
        System.out.println("Number of vehicles retrieved: " + vehicules.size());

        ObservableList<Integer> num_v = FXCollections.observableArrayList();

        for (Vehicule num : vehicules) {
            num_v.add(num.getNum_v());
            System.out.println("Added vehicle ID: " + num.getNum_v());

        }

       idvehicule .setItems(num_v);
    }
*/
    }


    @FXML
    void GoToAfficher(ActionEvent event) {
        try {
            // Load the Event.fxml file
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/AfficherTransport.fxml"));
            javafx.scene.Parent root = loader.load();

            // Show the AfficherUser.fxml scene
            Stage stage = (Stage) button_afficher.getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
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
            Stage stage = (Stage) button_modifier.getScene().getWindow();
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

    /*
    @FXML
    private void initialize() {
        // Initialise le TextField
        txtnum_t = new TextField();
    }
    @FXML
    private void initialize1() {
        // Initialise le TextField
        txtnum_ch = new TextField();
    }
    @FXML
    private void initialize2() {
        // Initialise le TextField
        txtdd = new TextField();
    }
    @FXML
    private void initialize3() {
        // Initialise le TextField
        txtdf = new TextField();
    }
    @FXML
    private void initialize4() {
        // Initialise le TextField
        txtnum_v = new TextField();
    }
    @FXML
    private void initialize5() {
        // Initialise le TextField
        txtcout = new TextField();
    }
*//*
    @FXML
    void ajoutertransport(ActionEvent event) {
        try {
            int num_t = Integer.parseInt(txtnum_t.getText());
            int num_ch = Integer.parseInt(txtnum_ch.getText());
            int dd = Integer.parseInt(txtdd.getText());
            int df = Integer.parseInt(txtdf.getText());
            int num_v = Integer.parseInt(txtnum_v.getText());
            int cout = Integer.parseInt(txtcout.getText());

            // Utilisez les valeurs numériques récupérées
        } catch (NumberFormatException e) {
            // Gérez les cas où une conversion en entier échoue
            System.out.println("Une des entrées n'est pas un nombre valide.");
        } catch (NullPointerException e) {
            // Gérez les cas où un TextField est null
            System.out.println("Un des champs de texte est null.");
        }
    }*/




    // String prenom =txtprenom.getText();
        /*String numText = txtnum_t.getText();
        if (numText != null && !numText.isEmpty()) {
            num_t = Integer.parseInt(numText);
            // Utilisez num_t comme vous le souhaitez
        } else {
            // Gérez le cas où le texte dans le TextField est vide ou null
        }*/
        /*String numText = txtnum_t.getText();
        if (numText != null && !numText.isEmpty()) {
            try {
                 num_t = Integer.parseInt(numText);
                // Utilisez num_t comme vous le souhaitez
            } catch (NumberFormatException e) {
                // Gérez le cas où l'entrée n'est pas un entier valide
                System.out.println("L'entrée n'est pas un entier valide.");
            }
        } else {
            // Gérez le cas où l'entrée est vide
            System.out.println("Le champ de texte est vide.");
        }*/



    @FXML
    public void handleButtonClick() {
        // Example method to handle button click
        int num_c= Integer.parseInt(num_ch.getText()); // Parse text to int if necessary
        System.out.println("Number entered: " + num_ch);
        // Now you can use 'num_ch' as an integer value
    }



    @FXML
    void Ajoutertransport(ActionEvent event) {
        boolean message ;
        try {
            int num_c = Integer.parseInt(num_ch.getText());
            LocalDate dd = ddId.getValue();
            LocalDate da = daId.getValue();
            int num_ve = Integer.parseInt(num_v.getText());
            int coutt = Integer.parseInt(cout.getText());
            /*Integer selectvehiculeid =idvehicule.getValue();
            if(selectvehiculeid ==null){
                System.out.println("error:no vehicule selected");
                return;
            }
            VehiculeService vs=new VehiculeService();
            Vehicule selectvehicule=vs.readBynum_v(selectvehiculeid);*/
            Transport t = new Transport(num_c, dd, da, num_ve, coutt);
            TransportService ps = new TransportService();
           message = ps.add(t);
           if (!message ) {
               // Display success message to the user
               Alert alert = new Alert(Alert.AlertType.ERROR);
               alert.setTitle("Transport non ajouté");
               alert.setHeaderText(null);
               alert.setContentText("Le transport faild!");
               alert.showAndWait();

               // Clear input fields after adding transport
               clearFields();
           }
           else {


               // Display success message to the user
               Alert alert = new Alert(Alert.AlertType.INFORMATION);
               alert.setTitle("Transport ajouté");
               alert.setHeaderText(null);
               alert.setContentText("Le transport a été ajouté avec succès!");
               alert.showAndWait();

               // Clear input fields after adding transport
               clearFields();
           }

        } catch (NumberFormatException e) {
            // Handle invalid input (e.g., non-numeric values)
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur de saisie");
            alert.setHeaderText(null);
            alert.setContentText("Veuillez saisir des valeurs numériques valides!");
            alert.showAndWait();
        }
    }

    // Helper method to clear input fields after adding transport
    private void clearFields() {
        num_ch.clear();
        ddId.setValue(null);
        daId.setValue(null);
        num_v.clear();
        cout.clear();
    }}



/*Image image=new Image("D:`/xampp/htdocs/connexioncapmping3a16/lm.png");
   ImageView Im*/