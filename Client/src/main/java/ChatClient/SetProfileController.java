package ChatClient;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;

import java.net.URL;
import java.sql.*;
import java.util.Date;
import java.util.ResourceBundle;

import static ChatClient.DataBaseUtils.changeChatScene;

//Control Profile setting system's graphical user interface
public class SetProfileController implements Initializable {

    @FXML
    private Button btn_chat;
    @FXML
    private TextField tf_nickname;
    private static String Username;

    //Method to get username so that nickname can be stored into corresponding account
    public static void setUser(String username) {
        Username = username;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        //Method that handle the click
        btn_chat.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                String Nickname = tf_nickname.getText();
                Connection connection = null;
                PreparedStatement PSUpdate = null;
                try {
                    //Set up connection with the database
                    connection = DriverManager.getConnection("jdbc:mysql://localhost:41001/multichatfx", "root", "ctOssystemv.3" );
                    PSUpdate = connection.prepareStatement("UPDATE users SET Nickname = ? WHERE Username = ?");
                    PSUpdate.setString(1, Nickname); //Update the nickname of corresponding user
                    PSUpdate.setString(2, Username);
                    PSUpdate.executeUpdate();
                    ClientController.setNickname(Nickname);
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setContentText("Nickname has been set successfully.");
                    alert.show();
                    //Change to chat view by calling the changeChatScene method
                    changeChatScene(actionEvent, "ChatView.fxml", "Chat", Username);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        });

    }
}
