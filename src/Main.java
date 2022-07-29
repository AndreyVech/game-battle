import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.scene.Scene;

import java.io.IOException;

public class Main extends Application {
//public class Main{
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage)  {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("GameStart.fxml"));
            Parent root = loader.load();
            primaryStage.setTitle("Fantasy Battle");
            primaryStage.setScene(new Scene(root));

            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
