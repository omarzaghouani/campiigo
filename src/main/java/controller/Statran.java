package controller;

import entities.Transport;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.stage.Stage;
import services.TransportService;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
public class Statran implements Initializable {

    public PieChart transportPieChart;
    @FXML
    private PieChart Orgpiechart;
    @FXML
    private javafx.scene.control.Button showCalendarButton;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        TransportService transportService = new TransportService();
        List<Transport> transports = transportService.readAll();
        for (Transport t : transports) {
            Orgpiechart.getData().add(new PieChart.Data("Transport " + t.getNum_t(), t.getCout()));
        }

        Orgpiechart.setTitle("Transportation Data");
        Orgpiechart.setLegendVisible(true);
        Orgpiechart.setLabelsVisible(false);
    }
    @FXML
    void showCalendar(ActionEvent event) {
        try {

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/transport.fxml"));
            javafx.scene.Parent root = loader.load();


            Stage stage = (Stage) showCalendarButton.getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}





