package Servers;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;

public class ClientHandler implements Runnable{
    public static ArrayList<ClientHandler> clientHandlers = new ArrayList<>();
    private Socket socket;
    private BufferedReader bufferedReader;
    private BufferedWriter bufferedWriter;
    private String clientUsername;

    public ClientHandler(Socket socket) {
        try {
            this.socket = socket;
            this.bufferedWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            this.bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            clientHandlers.add(this);
        } catch (IOException e) {
            closeEverything(socket, bufferedReader, bufferedWriter);
        }

    }

    public void SendMessageToClient(String MessageToClient) {
        for (ClientHandler clientHandler : clientHandlers) {
            try {
                clientHandler.bufferedWriter.write(MessageToClient);
                clientHandler.bufferedWriter.newLine();
                clientHandler.bufferedWriter.flush();
            } catch(IOException e) {
                closeEverything(socket, bufferedReader, bufferedWriter);
            }
        }
    }

    @Override
    public void run() {
        String MessageToClient;
        while (socket.isConnected()) {
            try {
                MessageToClient = bufferedReader.readLine();
                SendMessageToClient(MessageToClient);
            } catch(IOException e) {
                e.printStackTrace();
                System.out.println("Error receiving from the client");
                closeEverything(socket, bufferedReader, bufferedWriter);
                break;
            }
        }
    }

    public void removeClientHandler() {
        clientHandlers.remove(this);
        SendMessageToClient("SERVER: " + clientUsername + "has left the chatroom");
    }

    public void closeEverything(Socket socket, BufferedReader bufferedReader, BufferedWriter bufferedWriter) {
        removeClientHandler();
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
