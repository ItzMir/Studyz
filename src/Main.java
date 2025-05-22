import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) {
        try {
            // Load FXML
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/Page1.fxml"));
            Parent root = loader.load();

            // Create and set scene
            Scene scene = new Scene(root);

            primaryStage.setTitle("Responsive UI with Video Background");
            primaryStage.setScene(scene);
            primaryStage.setResizable(true); // Make window resizable for responsiveness
            primaryStage.show();

        } catch (Exception e) {
            System.err.println("Error loading FXML: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
