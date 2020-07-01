package core;

// imports
import java.io.*;
import java.net.*;
import java.util.Date;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;
import core.Connect4;


/**
 * Handles the implementation of a server side connect4 game. This class runs all of the game logic
 * of the game and returns an updated gameBoard to the player(s)
 * @author gunnarhoglund
 * @version 0.1
 *
 */
public class Connect4Server {

    static final int port = 8000;
    private static OutputStream out;
    DataInputStream in;
    static ServerSocket server;
    Socket socket;


    public static void main(String[] args) throws IOException {

        // creating a new thread for the game seesion
        new Thread( () -> {
            try{
                // create server socket on port 8000
                server = new ServerSocket(port);
                System.out.println("Socket opened on port " + port);

                while(true) {
                    // Connecting to player1
                    Socket player1 = server.accept();

                    new DataOutputStream(
                            player1.getOutputStream()).writeInt(1);

                    Socket player2 = server.accept();

                    new DataOutputStream(
                            player2.getOutputStream()).writeInt(2);
                }
            }
            catch(IOException ex) {
                ex.printStackTrace();
            }
        }).start();
    }

    /**
     * This class handles the game loop
     * sending data back and forth between the client
     */
    class HandleASession implements Runnable {
        private Socket player1;
        private Socket player2;
        private int player1Num = 1;
        private int player2Num = 2;

        private final int invalidMove = 400;

        private DataInputStream fromPlayer1;
        private DataOutputStream toPlayer1;
        private DataInputStream fromPlayer2;
        private DataOutputStream toPlayer2;

        private boolean continueToPlay = true;
        Connect4 obj = new Connect4();

        @Override
        public void run() {
            try {
                // Create data input and output streams
                DataInputStream fromPlayer1 = new DataInputStream(player1.getInputStream());
                DataOutputStream toPlayer1 = new DataOutputStream(player1.getOutputStream());
                DataInputStream fromPlayer2 = new DataInputStream(player2.getInputStream());
                DataOutputStream toPlayer2 = new DataOutputStream(player2.getOutputStream());

                // signaling client to start
                toPlayer1.writeInt(1);

                boolean done = false;

                // game loop
                while(true) {

                    // attempts to get a move from the player until it
                    // gets a valid move
                    while(!done) {
                        try {
                            int column = fromPlayer1.readInt(); // getting player 1 input
                            obj.setMove(column, player1Num);
                            done = true;
                        } catch (columnFullException e) {
                            toPlayer1.writeInt(invalidMove);
                        }
                    }

                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}