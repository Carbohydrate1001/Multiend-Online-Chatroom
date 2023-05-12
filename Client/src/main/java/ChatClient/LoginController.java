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


//Control Login system's graphical user interface
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

        //Handle the button click
        btn_login.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                //Call the LogInUser method and pass the username and password that have been entered
                DataBaseUtils.LogInUser(actionEvent, tf_username.getText(), pf_password.getText());
            }
        });

        //Handle the button click
        btn_register.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                //Change login&register scene by calling the function changeLRScene
                DataBaseUtils.changeLRScene(actionEvent, "RegisterUI.fxml", "Register", null);
            }
        });
    }
}
