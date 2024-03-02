package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Side;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.*;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import utils.DataSource;

import java.io.IOException;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class Chart implements Initializable {
    @FXML
    private Button retourner;
    int itemsMorning = 0;
    int itemsEvening = 0;
    @FXML
    private Pane pane;
    @FXML
    private Pane pane1;
    int itemsNight =0 ;
    @FXML
    private BarChart<?, ?> barChart;
    @FXML
    private PieChart piechart;
    private DataSource ConnexionJDBC;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        chart();
        voidChartTime();
        chartTime();
        AccompagnementChart();


    }

    public void chart () {
        String sql = "SELECT item.is_complete , COUNT(item.titre) FROM `item` GROUP by item.is_complete";
        XYChart.Series chartData = new XYChart.Series();
        try {
            PreparedStatement preparedStatement = ConnexionJDBC.getInstance().getCnx().prepareStatement(sql);
            ResultSet result = preparedStatement.executeQuery();
            while (result.next()) {
                chartData.getData().add(new  XYChart.Data<>(result.getString(1), result.getInt(2)));



            }
            barChart.getData().add(chartData);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public class Item {
        private LocalDateTime time;

        public LocalDateTime getTime() {
            return time;
        }

        public void setTime(LocalDateTime time) {
            this.time = time;
        }
    }
    public void voidChartTime (){

        PieChart.Data slice1 = new PieChart.Data("Matin", itemsMorning);
        PieChart.Data slice2 = new PieChart.Data("soir", itemsEvening);
        PieChart.Data slice3 = new PieChart.Data("nuit", itemsNight);


        piechart.setTitle(" Distribution des tasks ");

        // Customize the appearance of the pie chart

        piechart.getData().forEach(data -> {
            switch (data.getName()) {
                case "Matin":
                    data.getNode().setStyle("-fx-pie-color: #ff6961;");
                    break;
                case "soir":
                    data.getNode().setStyle("-fx-pie-color: #af77dd;");
                    break;
                case "nuit":
                    data.getNode().setStyle("-fx-pie-color: #aec6cf;");
                    break;
                default:
                    data.getNode().setStyle("-fx-pie-color: #9977dd;");

            }

        });



        // Create a pie chart and add the data to it

        piechart.getData().addAll(slice1, slice2, slice3);

        // Create a layout and add the pie chart to it


    }

    /*private void CalculateTime() {

        ArrayList<Item> items = new ArrayList<>();
        ItemService is = new ItemService();
        items = is.listerItems();
        LocalTime time1 = LocalTime.of(00, 00);
        LocalTime time2 = LocalTime.of(12, 00);
        LocalTime time3 = LocalTime.of(17, 00);
        LocalTime time4 = LocalTime.of(23, 59);

        for (Item item : items) {
            if (item.getTime().toLocalTime().compareTo(time1) > 0 && (item.getTime().toLocalTime().compareTo(time2) < 0)) {
                itemsMorning++;
            } else if (item.getTime().toLocalTime().compareTo(time2) > 0 && (item.getTime().toLocalTime().compareTo(time3) < 0)) {
                itemsEvening++;
            } else if (item.getTime().toLocalTime().compareTo(time3) > 0 && (item.getTime().toLocalTime().compareTo(time4) < 0)) {
                itemsNight++;
            }
        }
    }*/

    public void AccompagnementChart(){

        CategoryAxis xAxis = new CategoryAxis();
        NumberAxis yAxis = new NumberAxis();
        yAxis.setLabel("Value");

        // Create the stacked bar chart and set its properties
        StackedBarChart<String, Number> chart = new StackedBarChart<>(xAxis, yAxis);
        chart.setTitle("Stacked Bar Chart");
        chart.setLegendSide(Side.RIGHT);
        chart.setAnimated(false);

        // Get the data from the database
        List<String> users = new ArrayList<>();
        ObservableList<XYChart.Series<String, Number>> data = FXCollections.observableArrayList();

        String requete = "SELECT user.nom  as username , COUNT(accompagnement.id) as value1 , COUNT(accompagnement.id) as value2 FROM `accompagnement` JOIN user on accompagnement.user_pro_id=user.id JOIN tasks on tasks.id=accompagnement.task_id GROUP by accompagnement.user_pro_id;";




        try {
            PreparedStatement   stmt =  ConnexionJDBC.getInstance().getCnx().prepareStatement(requete)  ;
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                String user = rs.getString("username");
                if (!users.contains(user)) {
                    users.add(user);
                }

                XYChart.Series<String, Number> series = new XYChart.Series<>();
                series.setName(user);
                series.getData().add(new XYChart.Data<>("Value 1", rs.getInt("value1")));

                series.getData().add(new XYChart.Data<>("Value 2", rs.getInt("value2")));
                System.out.println("the datatatatta"+series);
                // series.getData().add(new XYChart.Data<>("Value 3", rs.getInt("value3")));
                data.add(series);
            }
            rs.close();
            stmt.close();
            // Set the categories on the x-axis
            xAxis.setCategories(FXCollections.observableArrayList(users));

            chart.setData(data);

            // Create a scene and display the chart
            pane1.getChildren().add(chart);


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public void chartTime(){
        try {

            String requete = "SELECT TIME_FORMAT(time, '%H:%i:%s') AS time_char , COUNT(*) as num FROM `item` GROUP BY time;";



            PreparedStatement   stmt =  ConnexionJDBC.getInstance().getCnx().prepareStatement(requete)  ;
            ResultSet rs = stmt.executeQuery();
            // Store the data in a list of XYChart.Data objects
            List<XYChart.Data<String, Number>> data = new ArrayList<>();
            while (rs.next()) {
                String time_char = rs.getString("time_char");
                int num = rs.getInt("num");
                XYChart.Data<String, Number> d = new XYChart.Data<>(time_char, num);
                data.add(d);}

            // Create the chart
            CategoryAxis xAxis = new CategoryAxis();
            NumberAxis yAxis = new NumberAxis();
            xAxis.setLabel("heure");
            yAxis.setLabel("nombre de taches ");
            LineChart<String, Number> lineChart = new LineChart<>(xAxis, yAxis);
            lineChart.setTitle("Analyse des taches % heure");
            XYChart.Series<String, Number> series = new XYChart.Series<>();
            series.getData().addAll(data);
            lineChart.getData().add(series);

            pane.getChildren().add(lineChart);



        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public class CalculateTime {

        private int itemsMorning;
        private int itemsEvening;
        private int itemsNight;

        public void calculateTime(ArrayList<Item> items) {
            // Initialize counters
            itemsMorning = 0;
            itemsEvening = 0;
            itemsNight = 0;

            // Define time ranges
            LocalTime time1 = LocalTime.of(0, 0);
            LocalTime time2 = LocalTime.of(12, 0);
            LocalTime time3 = LocalTime.of(17, 0);
            LocalTime time4 = LocalTime.of(23, 59);

            // Iterate through items and count based on time ranges
            for (Item item : items) {
                LocalTime itemTime = item.getTime().toLocalTime();
                if (itemTime.compareTo(time1) >= 0 && itemTime.compareTo(time2) < 0) {
                    itemsMorning++;
                } else if (itemTime.compareTo(time2) >= 0 && itemTime.compareTo(time3) < 0) {
                    itemsEvening++;
                } else if (itemTime.compareTo(time3) >= 0 && itemTime.compareTo(time4) < 0) {
                    itemsNight++;
                }
            }

            // Optionally, you can return or use the calculated values
            // For example, you could print them here
            System.out.println("Morning items: " + itemsMorning);
            System.out.println("Evening items: " + itemsEvening);
            System.out.println("Night items: " + itemsNight);
        }

        // Getter methods for calculated values if needed
        public int getItemsMorning() {
            return itemsMorning;
        }

        public int getItemsEvening() {
            return itemsEvening;
        }

        public int getItemsNight() {
            return itemsNight;
        }
    }

    @FXML
    public void switching() {

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Gui_taskAdmin.fxml"));
            Parent root = loader.load();
            Scene newScene = new Scene(root);
            Stage currentStage = (Stage) retourner.getScene().getWindow();
            currentStage.setScene(newScene);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }}
