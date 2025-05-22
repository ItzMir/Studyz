import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class App extends Application {
    private static final double ASPECT_RATIO = 16.0 / 9.0;
    private boolean isResizing = false;

    @Override
    public void start(Stage primaryStage) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/Page 1.fxml"));
            Scene scene = new Scene(root, 1280, 720);

            primaryStage.setScene(scene);
            primaryStage.setFullScreen(false);
            scene.setOnKeyPressed(event -> {
                if (event.getCode().toString().equalsIgnoreCase("F11")) {
                    primaryStage.setFullScreen(!primaryStage.isFullScreen());
                }
            });

            primaryStage.fullScreenProperty().addListener((_, _, isNowFullScreen) -> {
                if (!isNowFullScreen) {
                }
            });
            primaryStage.setMinWidth(400);
            primaryStage.setMinHeight(400 / ASPECT_RATIO);

            primaryStage.setOnShown(_ -> {
                
                primaryStage.widthProperty().addListener((_, _, newVal) -> {
                    if (!isResizing && !primaryStage.isFullScreen()) {
                        isResizing = true;
                        double targetHeight = newVal.doubleValue() / ASPECT_RATIO;
            
                        if (Math.abs(primaryStage.getHeight() - targetHeight) > 10) {
                            primaryStage.setHeight(targetHeight);
                        }
                        isResizing = false;
                    }
                });

                primaryStage.heightProperty().addListener((_, _, newVal) -> {
                    if (!isResizing && !primaryStage.isFullScreen()) {
                        isResizing = true;
                        double targetWidth = newVal.doubleValue() * ASPECT_RATIO;
                        if (Math.abs(primaryStage.getWidth() - targetWidth) > 10) {
                            primaryStage.setWidth(targetWidth);
                        }
                        isResizing = false;
                    }
                });
            });

            primaryStage.show();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}