package controller;

import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.Charge;
import com.stripe.model.Token;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class stripe {

    // Fields for JavaFX controls
    @FXML private TextField montantText;
    @FXML private TextField numeroTel;
    @FXML private TextField cardNumberField;
    @FXML
    private TextField expMonthField;
    @FXML
    private TextField expYearField;
    @FXML private TextField cvcField;

    // Stripe API key (Move this to a more secure location, like environment variables)
    private static final String STRIPE_SECRET_KEY = "your_stripe_secret_key";

    // Initialize Stripe API with secret key
    static {
        Stripe.apiKey = STRIPE_SECRET_KEY;
    }

    // Process payment method
    @FXML
    public void processPayment() {
        try {
            // Validate user input
            if (!validateInput()) {
                System.out.println("Invalid input!");
                return;
            }

            // Extract payment information from text fields
            String cardNumber = cardNumberField.getText();
            int expMonth = Integer.parseInt(expMonthField.getText());
            int expYear = Integer.parseInt(expYearField.getText());
            String cvc = cvcField.getText();
            int amount = Integer.parseInt(montantText.getText());

            // Create token for the card
            Map<String, Object> tokenParams = new HashMap<>();
            Map<String, Object> cardParams = new HashMap<>();
            cardParams.put("number", cardNumber);
            cardParams.put("exp_month", expMonth);
            cardParams.put("exp_year", expYear);
            cardParams.put("cvc", cvc);
            tokenParams.put("card", cardParams);
            Token token = Token.create(tokenParams);

            // Create charge with the token and other parameters
            Map<String, Object> chargeParams = new HashMap<>();
            chargeParams.put("amount", amount * 100); // Amount in cents
            chargeParams.put("currency", "eur");
            chargeParams.put("source", token.getId());
            chargeParams.put("description", "Payment for order");

            Charge charge = Charge.create(chargeParams);

            // Payment successful
            sendEmail("omarzaghouani01@gmail.com");
            System.out.println("Payment successful.");
        } catch (NumberFormatException e) {
            System.out.println("Invalid number format: " + e.getMessage());
        } catch (StripeException e) {
            System.out.println("Stripe exception occurred: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Unexpected error occurred: " + e.getMessage());
        }
    }

    // Validate user input
    private boolean validateInput() {
        // You can implement validation logic here
        // For example, check if the fields are not empty, validate card number, etc.
        return true; // Placeholder, implement actual validation
    }

    // Method to send email notification
    private void sendEmail(String recipient) {
        // Set up properties for JavaMail
        Properties properties = new Properties();
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.port", "587");

        // Create session
        Session session = Session.getInstance(properties, new javax.mail.Authenticator() {
            protected javax.mail.PasswordAuthentication getPasswordAuthentication() {
                return new javax.mail.PasswordAuthentication("your_email@gmail.com", "your_password");
            }
        });

        // Create message
        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("your_email@gmail.com"));
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(recipient));
            message.setSubject("Payment Confirmation");
            message.setText("Your payment has been successfully processed.");

            // Send message
            Transport.send(message);
            System.out.println("Email notification sent successfully.");
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
    public boolean controleDeSaisie() {
        boolean monthYear = false;
        if (testPattern("[0-9][0-9]", expMonthField.getText()) && testPattern("[0-9][0-9]", expYearField.getText())) {
            int expMonth = Integer.parseInt(expMonthField.getText());
            int expYear = Integer.parseInt(expYearField.getText());
            if (expMonth > 0 && expMonth <= 12 && expYear > 22)
                monthYear = true;
        }

        return testPattern("[0-9]+", montantText.getText()) &&
                testPattern("[0-9]{16}", cardNumberField.getText()) &&
                testPattern("[0-9]{3}", cvcField.getText()) &&
                testPattern("[0-9]{16}", numeroTel.getText()) &&
                monthYear;
    }

    public boolean testPattern(String regex, String input) {
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(input);

        return matcher.matches(); // Changed to matches() for complete match
    }

    public void sendSMS(int montant,String numeroTel) throws IOException {
        String endpointUrl = "https://api.twilio.com/2010-04-01/Accounts/AC35f3e6bdc59a86fd5d3763e8d3e093a6/Messages.json";
        String numeroTelephone="+216"+numeroTel;
        Map<String, String> parameters = new HashMap<>();
        parameters.put("To", "+21626834008");
        parameters.put("From", "+21652257398");
        parameters.put("Body", "paiement reussi de "+montant+" €");

        URL url = new URL(endpointUrl);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("POST");
        connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
        String username = "AC35f3e6bdc59a86fd5d3763e8d3e093a6";
        String password = "b98f6255b040afaa8779af5c687286dd";
        String encodedCredentials = Base64.getEncoder().encodeToString((username + ":" + password).getBytes());

        connection.setRequestProperty("Authorization", "Basic " + encodedCredentials);
        connection.setDoOutput(true);
        connection.setDoOutput(true);

        String requestBody = encodeFormData(parameters);
        OutputStream outputStream = connection.getOutputStream();
        byte[] requestBodyBytes = requestBody.getBytes(StandardCharsets.UTF_8);
        outputStream.write(requestBodyBytes);
        outputStream.close();

        int responseCode = connection.getResponseCode();
        System.out.println("Response Code : " + responseCode);
    }
    private static String encodeFormData(Map<String, String> parameters) throws UnsupportedEncodingException {
        StringBuilder sb = new StringBuilder();

        for (Map.Entry<String, String> entry : parameters.entrySet()) {
            if (sb.length() > 0) {
                sb.append("&");
            }
            sb.append(URLEncoder.encode(entry.getKey(), "utf-8"));
            sb.append("=");
            sb.append(URLEncoder.encode(entry.getValue(), "utf-8"));
        }

        return sb.toString();
    }

}