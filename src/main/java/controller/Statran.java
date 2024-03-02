package controller;

import entites.Transport;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.PieChart;
import service.TransportService;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
public class Statran implements Initializable {

    public PieChart transportPieChart;
    @FXML
    private PieChart Orgpiechart;

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


}
