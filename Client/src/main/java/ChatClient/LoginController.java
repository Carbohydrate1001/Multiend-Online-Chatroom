package ChatClient;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class LoginController implements Initializable {

    @FXML
    private Button btn_login;
    @FXML
    private Button btn_register;
    @FXML
    private TextField tf_username;
    @FXML
    private PasswordField pf_password;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        btn_login.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                DataBaseUtils.LogInUser(actionEvent, tf_username.getText(), pf_password.getText());
            }
        });

        btn_register.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                DataBaseUtils.changeLRScene(actionEvent, "RegisterUI.fxml", "Register", null);
            }
        });
    }
}
