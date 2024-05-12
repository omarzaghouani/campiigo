package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.FlowPane;
import javafx.scene.text.Text;
import services.TransportService;

import java.net.URL;
import java.time.LocalDate;
import java.time.YearMonth;
import java.time.ZonedDateTime;
import java.util.ResourceBundle;

public class calendrierController implements Initializable {

    ZonedDateTime dateFocus;
    ZonedDateTime today;
    @FXML
    private javafx.scene.control.Button showCalendarButton;


    @FXML
    private Text year;

    @FXML
    private Text month;

    @FXML
    private FlowPane calendar;

    private TransportService transportService; // Service to handle reservation data access

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        dateFocus = ZonedDateTime.now();
        today = ZonedDateTime.now();
        transportService = new TransportService();
        drawCalendar();

    }

    @FXML
    void backOneMonth(ActionEvent event) {
        dateFocus = dateFocus.minusMonths(1);
        calendar.getChildren().clear();
        drawCalendar();

    }

    @FXML
    void forwardOneMonth(ActionEvent event) {
        dateFocus = dateFocus.plusMonths(1);
        calendar.getChildren().clear();
        drawCalendar();
    }

    private void drawCalendar() {
        year.setText(String.valueOf(dateFocus.getYear()));
        month.setText(String.valueOf(dateFocus.getMonth()));

        YearMonth yearMonth = YearMonth.of(dateFocus.getYear(), dateFocus.getMonthValue());
        int daysInMonth = yearMonth.lengthOfMonth(); // Get the number of days in the month

        // Calculate the first day of the week and the last day of the week
        LocalDate firstDayOfMonth = yearMonth.atDay(1);
        LocalDate lastDayOfMonth = yearMonth.atEndOfMonth();

        double calendarWidth = calendar.getPrefWidth();
        double calendarHeight = calendar.getPrefHeight();
        double strokeWidth = 1;
        double spacingH = calendar.getHgap();
        double spacingV = calendar.getVgap();

        // List of reservations for a given month
      /*  List<Transport> transports = TransportService.readAll(firstDayOfMonth, lastDayOfMonth);

        Map reservationMap = new HashMap<>();
        for (Transport transport : transports) {
            LocalDate startDate = transport.getDd();
            LocalDate endDate = transport.getDa();
            LocalDate date = startDate;
            while (!date.isAfter(endDate)) {
                ((HashMap<?, ?>) reservationMap).computeIfAbsent(date, k -> new ArrayList<>()).add(transport);
                date = date.plusDays(1);
            }
        }

        int numRows = (int) Math.ceil((double) (daysInMonth + firstDayOfMonth.getDayOfWeek().getValue() - 1) / 7);

        for (int i = 0; i < numRows; i++) {
            for (int j = 0; j < 7; j++) {
                StackPane stackPane = new StackPane();

                Rectangle rectangle = new Rectangle();
                rectangle.setFill(Color.TRANSPARENT);
                rectangle.setStroke(Color.BLACK);
                rectangle.setStrokeWidth(strokeWidth);
                double rectangleWidth = (calendarWidth / 7) - strokeWidth - spacingH;
                rectangle.setWidth(rectangleWidth);
                double rectangleHeight = (calendarHeight / numRows) - strokeWidth - spacingV;
                rectangle.setHeight(rectangleHeight);
                stackPane.getChildren().add(rectangle);

                int dayOfMonth = j + i * 7 + 1 - firstDayOfMonth.getDayOfWeek().getValue() + 1; // Calculate the current day of the month
                if (dayOfMonth > 0 && dayOfMonth <= daysInMonth) { // Check if the day is valid for this month
                    Text dayText = new Text(String.valueOf(dayOfMonth));
                    stackPane.getChildren().add(dayText);

                    LocalDate currentDate = yearMonth.atDay(dayOfMonth);
                    List<Transport> reservationsForDate = reservationMap.get(currentDate);
                    if (reservationsForDate != null && !reservationsForDate.isEmpty()) {
                        // If reservations exist for this day, display the data of the first reservation
                        Transport firstReservation = reservationsForDate.get(0);
                        Text reservationText = new Text("Transport: " + firstReservation.getNum_t());
                        Rectangle reservationBox = new Rectangle(rectangleWidth, rectangleHeight / 3);
                        reservationBox.setFill(Color.LIGHTGRAY);
                        stackPane.getChildren().addAll(reservationBox, reservationText);

                        // Add an event handler to display the reservation details on click
                        stackPane.setOnMouseClicked(e -> showReservationDetails(firstReservation));
                    }
                }

                calendar.getChildren().add(stackPane);
            }
        }
    }

    // Method to show reservation details
    private void showReservationDetails(Transport transport) {
        // Construct the content of the dialog box with all car information
        String detailsContent = "Transport ID: " + transport.getNum_t() + "\n" +
                "Departure Date: " + transport.getDd() + "\n" +
                "Arrival Date: " + transport.getDa() + "\n" +
                "Vehicle ID: " + transport.getNum_v() + "\n" +
                "Cost: " + transport.getCout();

        // Display the details in an alert dialog box
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Reservation Details");
        alert.setHeaderText("Details of Transport #" + transport.getNum_t());
        alert.setContentText(detailsContent);
        alert.showAndWait();
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
*/
    }
}
