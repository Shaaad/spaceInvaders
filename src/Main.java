import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/view/sample.fxml"));
        primaryStage.setTitle("Space Invaders");
        primaryStage.setScene(new Scene(root, 300, 350));
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    @Override
    public void stop() {
        System.exit(-1);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
