package controller;

import javafx.scene.image.Image;
public class ImageSingleton {
    private static ImageSingleton instance;
    private Image capturedImage;

    private ImageSingleton() {}

    public static synchronized ImageSingleton getInstance() {
        if (instance == null) {
            instance = new ImageSingleton();
        }
        return instance;
    }

    public void setCapturedImage(Image image) {
        this.capturedImage = image;
    }

    public Image getCapturedImage() {
        return capturedImage;
    }
}
