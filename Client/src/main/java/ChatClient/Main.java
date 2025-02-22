package ChatClient;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    //This is an abandoned class that will directly start a chat window
    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("ChatView.fxml"));
        primaryStage.setTitle("Client");
        primaryStage.setScene(new Scene(root, 470, 370));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}