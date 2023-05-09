module com.example.client {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens ChatClient to javafx.fxml;
    exports ChatClient;
}