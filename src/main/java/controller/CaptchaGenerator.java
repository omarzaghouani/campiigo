package controller;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.Random;

public class CaptchaGenerator extends Application {
    private final String correctCaptcha = "OpenAI";

    private static final int IMAGE_WIDTH = 200;
    private static final int IMAGE_HEIGHT = 100;
    private static final String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";

    @Override
    public void start(Stage primaryStage) {
        ImageView captchaImageView = new ImageView();
        Button refreshButton = new Button("Refresh Captcha");
        refreshButton.setOnAction(event -> {
            String captchaText = generateCaptchaText();
            Image captchaImage = generateCaptchaImage(captchaText);
            captchaImageView.setImage(captchaImage);
        });

        String initialCaptchaText = generateCaptchaText();
        Image initialCaptchaImage = generateCaptchaImage(initialCaptchaText);
        captchaImageView.setImage(initialCaptchaImage);

        VBox root = new VBox(10);
        root.getChildren().addAll(captchaImageView, refreshButton);
        Scene scene = new Scene(root, 300, 200);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Captcha Generator");
        primaryStage.show();
    }

    private String generateCaptchaText() {
        Random random = new Random();
        StringBuilder captchaText = new StringBuilder();
        for (int i = 0; i < 6; i++) { // You can adjust the length of the captcha text
            int index = random.nextInt(CHARACTERS.length());
            captchaText.append(CHARACTERS.charAt(index));
        }
        return captchaText.toString();
    }

    private Image generateCaptchaImage(String captchaText) {
        // Here you would generate an image using captchaText, for example, using JavaFX Text objects
        // For simplicity, I'll just return a placeholder image
        // Replace this with your actual captcha image generation logic
        return new Image("https://via.placeholder.com/200x100?text=" + captchaText);
    }

    public static void main(String[] args) {
        launch(args);
    }
}

