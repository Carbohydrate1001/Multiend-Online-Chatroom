package Servers;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;


//Run this class to start the server
public class Server {
    private ServerSocket serverSocket;
    //private Socket socket;
    //private BufferedWriter bufferedWriter;
    //private BufferedReader bufferedReader;

    public Server(ServerSocket serverSocket) {
        this.serverSocket = serverSocket;
    }

    //Method to start the client and wait for connect
    public void StartServer() {

        try {

            while (!serverSocket.isClosed()) {

                Socket socket = serverSocket.accept();
                System.out.println("A new client has connected!");
                ClientHandler clientHandler = new ClientHandler(socket);
                //Call the clientHandler to start another thread
                Thread thread = new Thread(clientHandler);
                thread.start();
            }
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error connecting to client.");
        }
    }

    //Method to shut down the server
    public void closeServerSocket() {
        try{
            if (serverSocket != null) {
                serverSocket.close();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //Set the port and start server by calling methods
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(10010);
        Server server = new Server(serverSocket);
        server.StartServer();

    }

}