package core;

import java.util.Random;

/**
 * @author Gunnar Hoglund
 * @version 0.1
 */

/**
 * This class contains the logic to implement a computer player in the connect4
 * game
 */
public class Connect4ComputerPlayer {

    // stores the last column a piece was placed
    int lastMove;

    /**
     * Constructor
     */
    public Connect4ComputerPlayer() {
    }

    /**
     * 
     * @param gameBoard - this takes in a 2d array of integers that represents the
     *                  game board
     * @return int - this returns an int that represents the computer players input
     */
    public int makeMove(int[][] gameBoard) {

        int move = 0;
        Random rand = new Random();

        if (lastMove == 0) {

            move = rand.nextInt(7) + 1;
            lastMove = move;
        } else {

            // X = 1
            // O = 2
            if (lastMove + 1 < 7) {
                move = lastMove + 1;
            } else if (lastMove - 1 > 1) {
                move = lastMove - 1;
            } else {
                move = rand.nextInt(7) + 1;
            }

        }

        lastMove = move;

        return move;
    }

}