package ChatClient;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;


//Control Register system's graphical user interface
public class RegisterController implements Initializable {

    @FXML
    private TextField tf_username;
    @FXML
    private TextField tf_password;
    @FXML
    private TextField tf_passwordVri;
    @FXML
    private Button btn_register;
    @FXML
    private Button btn_login;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        //Handle the button click
        btn_register.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                if (!tf_username.getText().trim().isEmpty() && !tf_password.getText().trim().isEmpty() && !tf_passwordVri.getText().trim().isEmpty()) {
                    //Check if all the information are filled
                    DataBaseUtils.register(actionEvent, tf_username.getText(), tf_password.getText(), tf_passwordVri.getText());
                }
                else {
                    System.out.println("Please fill in all your information");
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setContentText("Please fill in all you information.");
                    alert.show();
                }
            }
        });

        btn_login.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                //Change login&register scene by calling the function changeLRScene
                DataBaseUtils.changeLRScene(actionEvent,"LoginUI.fxml", null, null);
            }
        });
    }
}
