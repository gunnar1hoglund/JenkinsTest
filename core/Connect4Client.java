package core;

import javafx.application.Application;
import javafx.stage.Stage;
import ui.Connect4GUI;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * @author gunnarhoglund
 * @version 0.1
 * this class handles sending and receiving game information
 * with the server code
 */
public class Connect4Client extends Application {

    private DataInputStream fromServer;
    private DataOutputStream toServer;
    private final String host = "localhost";

    public static void main(String[] args) {
        Connect4Client client = new Connect4Client();
        client.connectToServer();
        Application.launch(Connect4GUI.class, args);
    }

    /**
     * This method attempts to connect to the game server
     *
     */
    private void connectToServer() {
        try {
            Socket socket = new Socket(host, 8000);

            // Create an input stream to receive data from the server
            fromServer = new DataInputStream(socket.getInputStream());

            // Create an output stream to send data to the server
            toServer = new DataOutputStream(socket.getOutputStream());


        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            int message = fromServer.readInt();
            System.out.println("Connected to Server. Code " + message);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        launch();
    }
}
