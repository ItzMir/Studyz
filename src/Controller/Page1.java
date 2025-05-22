package Controller;

import javafx.beans.binding.Bindings;
import javafx.beans.binding.DoubleBinding;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;

import java.net.URL;
import java.util.ResourceBundle;

public class Page1 implements Initializable {

    @FXML
    private MediaView backgroundVideo1;

    @FXML
    private AnchorPane pane1;

    @FXML
    private Pane responsivePane;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        playBackgroundVideo();
        setupResponsiveScaling();
    }

    private void playBackgroundVideo() {
        URL videoUrl = getClass().getResource("/Assets/LoginBG.mp4");
        if (videoUrl == null) {
            System.err.println("Video file not found: /Assets/LoginBG.mp4");
            return;
        }
        String videoPath = videoUrl.toExternalForm();
        Media media = new Media(videoPath);
        MediaPlayer mediaPlayer = new MediaPlayer(media);
        backgroundVideo1.setMediaPlayer(mediaPlayer);
        mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
        mediaPlayer.setMute(true);
        mediaPlayer.play();
    }

    private void setupResponsiveScaling() {
        // Bind width and height ratios for scaling
        DoubleBinding scaleX = Bindings.createDoubleBinding(() ->
                        pane1.getWidth() / 1280.0,
                pane1.widthProperty());
        DoubleBinding scaleY = Bindings.createDoubleBinding(() ->
                        pane1.getHeight() / 720.0,
                pane1.heightProperty());

        responsivePane.scaleXProperty().bind(scaleX);
        responsivePane.scaleYProperty().bind(scaleY);

        // Bind layout to center the pane
        responsivePane.layoutXProperty().bind(
                pane1.widthProperty().subtract(responsivePane.widthProperty().multiply(scaleX)).divide(2)
        );
        responsivePane.layoutYProperty().bind(
                pane1.heightProperty().subtract(responsivePane.heightProperty().multiply(scaleY)).divide(2)
        );
    }
}
