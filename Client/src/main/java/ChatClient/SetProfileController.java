package ChatClient;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;

import java.net.URL;
import java.util.Date;
import java.util.ResourceBundle;

import static ChatClient.DataBaseUtils.changeChatScene;

public class SetProfileController implements Initializable {

    @FXML
    private Button btn_chat;
    @FXML
    private ChoiceBox head;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        btn_chat.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                String username = DataBaseUtils.Username;
                changeChatScene(actionEvent, "ChatView.fxml", "Chat", username);
            }
        });

    }
}
