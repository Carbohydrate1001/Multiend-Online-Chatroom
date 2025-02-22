package ChatClient;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

//Run this class to start a client
//Set multiple instances allowed
public class Launcher extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("LoginUI.fxml"));
        primaryStage.setTitle("Login");
        primaryStage.setScene(new Scene(root, 352, 430));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}