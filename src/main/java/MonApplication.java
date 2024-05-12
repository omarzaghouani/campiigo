

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.Stage;

import java.net.URL;

public class MonApplication extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        URL videoUrl = getClass().getResource("/Vidéo noire_1.mp4");
        Media media = new Media(((URL) videoUrl).toString());
        MediaPlayer mediaPlayer = new MediaPlayer(media);
        MediaView mediaView = new MediaView(mediaPlayer);

        Scene scene = new Scene(mediaView.getParent(), 800, 600);
        primaryStage.setScene(scene);
        primaryStage.show();

        mediaPlayer.play();
    }

    public static void main(String[] args) {
        launch(args);
    }
}