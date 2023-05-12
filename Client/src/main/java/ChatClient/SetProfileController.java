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

public class SetProfileController implements Initializable {

    @FXML
    private Button btn_chat;
    @FXML
    private TextField tf_nickname;
    private static String Username;

    public static void setUser(String username) {
        Username = username;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        btn_chat.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                String Nickname = tf_nickname.getText();
                Connection connection = null;
                PreparedStatement PSUpdate = null;
                try {
                    connection = DriverManager.getConnection("jdbc:mysql://localhost:41001/multichatfx", "root", "ctOssystemv.3" );
                    PSUpdate = connection.prepareStatement("UPDATE users SET Nickname = ? WHERE Username = ?");
                    PSUpdate.setString(1, Nickname);
                    PSUpdate.setString(2, Username);
                    PSUpdate.executeUpdate();
                    ClientController.setNickname(Nickname);
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setContentText("Nickname has been set successfully.");
                    alert.show();
                    changeChatScene(actionEvent, "ChatView.fxml", "Chat", Username);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        });

    }
}
