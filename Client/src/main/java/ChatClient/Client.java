package ChatClient;
import javafx.scene.layout.VBox;

import java.io.*;
import java.net.Socket;


public class Client {

    private Socket socket;
    private BufferedWriter bufferedWriter;
    private BufferedReader bufferedReader;


    public Client(Socket socket) {
        try {
            this.socket = socket;
            this.bufferedWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            this.bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        } catch (IOException e) {
            System.out.println("Error creating client.");
            closeEverything(socket, bufferedReader, bufferedWriter);
        }
    }

    public void sendMessageToServer(String messageToServer) {
        try {
            bufferedWriter.write(messageToServer);
            bufferedWriter.newLine();
            bufferedWriter.flush();
        } catch(IOException e) {
            e.printStackTrace();
            System.out.println("Error sending message to Server.");
            closeEverything(socket, bufferedReader, bufferedWriter);
        }
    }


    public void receiveMessageFromServer(VBox vBox) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (socket.isConnected()) {
                    try {
                        String msgFromClient = bufferedReader.readLine();
                        ClientController.addLabel(msgFromClient,vBox);
                    } catch (IOException e) {
                        e.printStackTrace();
                        System.out.println("Error receiving message from the server");
                        closeEverything(socket, bufferedReader, bufferedWriter);
                        break;
                    }
                }
            }
        }).start();
    }

    public void closeEverything(Socket socket, BufferedReader bufferedReader, BufferedWriter bufferedWriter) {
        try {
            if (bufferedReader != null) {
                bufferedReader.close();
            }
            if (bufferedWriter != null) {
                bufferedWriter.close();
            }
            if (socket != null) {
                socket.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
