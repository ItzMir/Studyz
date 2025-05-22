package Controller;

import javafx.fxml.FXML;
import javafx.scene.layout.Pane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.scene.transform.Scale;

public class Page1 {
    @FXML private Pane pane1;
    @FXML private MediaView backgroundVideo1;
    @FXML private Pane responsivePane;
    @FXML private MediaPlayer mediaPlayer;

    @FXML
    public void initialize() {
        try {
            var resource = getClass().getResource("/Resources/Page1_Background.mp4");
            if (resource == null) throw new Exception("Video not found.");
            var media = new Media(resource.toExternalForm());
            mediaPlayer = new MediaPlayer(media);
            backgroundVideo1.setMediaPlayer(mediaPlayer);
            backgroundVideo1.fitWidthProperty().bind(pane1.widthProperty());
            backgroundVideo1.fitHeightProperty().bind(pane1.heightProperty());

            mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
            mediaPlayer.setAutoPlay(true);
            mediaPlayer.setOnReady(() -> {
                backgroundVideo1.setVisible(true);
                mediaPlayer.play();
            });
            double originalWidth = 1280;
            double originalHeight = 720;

            Scale scale = new Scale();
            scale.xProperty().bind(pane1.widthProperty().divide(originalWidth));
            scale.yProperty().bind(pane1.heightProperty().divide(originalHeight));
            responsivePane.getTransforms().add(scale);

            // Maintain relative position
            responsivePane.layoutXProperty().bind(pane1.widthProperty().multiply(751.0 / originalWidth));
            responsivePane.layoutYProperty().bind(pane1.heightProperty().multiply(167.0 / originalHeight));

        } catch (Exception e) {
            System.err.println("Error loading video: " + e.getMessage());
        }
    }

    public void shutdown() {
        if (mediaPlayer != null) {
            mediaPlayer.stop();
            mediaPlayer.dispose();
        }
    }
}
