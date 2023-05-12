package ChatClient;

import Servers.ClientHandler;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
import java.sql.*;
import java.io.IOException;

public class DataBaseUtils {
    public static String Username;
    //public static ClientHandler clientHandler;
    public static void changeLRScene(ActionEvent event, String fxmlFile, String title, String username) {
        Parent root = null;

        if (username != null) {
            try {
                FXMLLoader fxmlLoader = new FXMLLoader(DataBaseUtils.class.getResource(fxmlFile));
                root = fxmlLoader.load();
                ClientController clientController = fxmlLoader.getController();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else {
            try {
                root = FXMLLoader.load(DataBaseUtils.class.getResource(fxmlFile));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
        stage.setTitle(title);
        stage.setScene(new Scene(root, 352, 430));
        stage.show();
    }

    public static void changeChatScene(ActionEvent event, String fxmlFile, String title, String username) {
        Parent root = null;
        if (username != null) {
            try {
                FXMLLoader fxmlLoader = new FXMLLoader(DataBaseUtils.class.getResource(fxmlFile));
                root = fxmlLoader.load();
                ClientController clientController = fxmlLoader.getController();
                ClientController.setUser(username);
                //ClientController.setNickname(nickname);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else {
            try {
                root = FXMLLoader.load(DataBaseUtils.class.getResource(fxmlFile));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
        stage.setTitle(title);
        stage.setScene(new Scene(root, 677, 396));
        stage.show();
    }

    public static void changeProfileScene(ActionEvent event, String fxmlFile, String username) {
        Parent root = null;
        try {
            root = FXMLLoader.load(DataBaseUtils.class.getResource("SetProfileUI.fxml"));
            SetProfileController.setUser(username);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
        stage.setTitle("Set your profile picture");
        stage.setScene(new Scene(root, 400, 365));
        stage.show();
    }

    public static void register(ActionEvent Event, String username, String password, String passwordVri) {
        Connection connection = null;
        PreparedStatement PSInsert = null;
        PreparedStatement PSCheckUserExist = null;
        ResultSet resultSet = null;
        String status = "Online";
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:41001/multichatfx", "root", "ctOssystemv.3" );
            PSCheckUserExist = connection.prepareStatement("SELECT * FROM users WHERE Username = ?");
            PSCheckUserExist.setString(1, username);
            resultSet = PSCheckUserExist.executeQuery();

            if (resultSet.isBeforeFirst()) {
                System.out.println("User already exists.");
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("This username has been used");
                alert.show();
            }
            else if (!password.equals(passwordVri)) {
                System.out.println(password);
                System.out.println(passwordVri);
                System.out.println("Passwords do not match.");
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("Passwords do not match.");
                alert.show();
            }
            else {
                Username = username;
                PSInsert = connection.prepareStatement("INSERT INTO users (Username, Password, Status) VALUES(?, ?, ?)");
                PSInsert.setString(1, username);
                PSInsert.setString(2, password);
                PSInsert.setString(3, status);
                PSInsert.executeUpdate();
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setContentText("Registered successfully.");
                alert.show();
                changeProfileScene(Event, "SetProfileUI.fxml", username);
                //clientHandler.SendMessageToClient("SERVER: " + username + " has entered the chat!");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (PSCheckUserExist != null) {
                try {
                    PSCheckUserExist.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (PSInsert != null) {
                try {
                    PSInsert.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void LogInUser(ActionEvent Event, String username, String password) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        PreparedStatement PSUpdate = null;
        ResultSet resultSet = null;
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:41001/multichatfx", "root", "ctOssystemv.3" );
            preparedStatement = connection.prepareStatement("SELECT Password, Status, Nickname FROM users WHERE Username = ?");
            preparedStatement.setString(1, username);
            resultSet = preparedStatement.executeQuery();
            if (!resultSet.isBeforeFirst()){
                System.out.println("User is not found in this database.");
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("Provided credentials are incorrect.");
                alert.show();
            }
            else {
                while (resultSet.next()) {
                    String retrievePassword = resultSet.getString("Password");
                    String retrieveStatus = resultSet.getString("Status");
                    String retrieveNickname = resultSet.getString("Nickname");
                    if (retrievePassword.equals(password)) {
                        if (retrieveStatus.equals("Offline")){
                            PSUpdate = connection.prepareStatement("UPDATE users SET Status = ? WHERE Username = ?");
                            PSUpdate.setString(1, "Online");
                            PSUpdate.setString(2, username);
                            ClientController.setNickname(retrieveNickname);
                            PSUpdate.executeUpdate();
                            changeChatScene(Event, "ChatView.fxml","Chat", username);
                            //clientHandler.SendMessageToClient("SERVER: " + username + " has entered the chat!");
                        }
                        else {
                            System.out.println("User is already online.");
                            Alert alert = new Alert(Alert.AlertType.ERROR);
                            alert.setContentText("User is already online.");
                            alert.show();
                        }
                    }
                    else {
                        System.out.println("Password does not match.");
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setContentText("The provided credentials are incorrect.");
                        alert.show();
                    }
                }
            }
        } catch (SQLException e) {
             e.printStackTrace();
        } finally {
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
