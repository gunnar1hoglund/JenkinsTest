package ui;

import java.util.Scanner;

import core.Connect4;
import core.Connect4ComputerPlayer;
import core.columnFullException;

/**
 * @author Gunnar Hoglund
 * @version 0.1
 */

/**
 * This class handles the Console based ui output, as well as delivers user
 * input to the game logic class
 */
public class Connect4TextConsole {

	public static void main(String[] args) {

		Connect4 obj = new Connect4();
		Connect4TextConsole ui = new Connect4TextConsole();
		Connect4ComputerPlayer player = new Connect4ComputerPlayer();
		Scanner scanner = new Scanner(System.in);

		int counter = 0;
		boolean win = false;
		int playerX = 1;
		int playerO = 2;
		char gameType;

		ui.drawBoard(obj.getGameBoard());

		System.out.print(
				"Begin Game. Enter 'P' if you want to play against another player; enter 'C' to play against a computer. ");
		gameType = scanner.next().charAt(0);

		gameType = Character.toLowerCase(gameType);

		while (gameType != 'p' && gameType != 'c') {
			System.out.print("please choose a valid character");
			gameType = scanner.next().charAt(0);
		}

		// computer player
		if (gameType == 'c') {
			while (counter < 42 && win != true) {

				System.out.print("PlayerX - your turn. Choose column number from 1 - 7: ");
				int playerInput = scanner.nextInt();

				try {
					obj.setMove(playerInput, playerX);
				} catch (columnFullException e) {
					System.out.println("This column is full please choose another");
				}

				ui.drawBoard(obj.getGameBoard());
				if (obj.checkWin(playerX) == true) {
					System.out.println("PlayerX Wins!");
					break;
				}

				System.out.println("Computer player turn");

				try {
					obj.setMove(player.makeMove(obj.getGameBoard()), playerO);
				} catch (columnFullException e) {
					System.out.println("This column is full please choose another");
				}

				ui.drawBoard(obj.getGameBoard());
				if (obj.checkWin(playerO) == true) {
					System.out.println("PlayerO Wins!");
					break;
				}
			}
		} else {
			// two player
			while (counter < 42 && win != true) {

				System.out.print("PlayerX - your turn. Choose column number from 1 - 7: ");
				int playerInput = scanner.nextInt();

				try {
					obj.setMove(playerInput, playerX);
				} catch (columnFullException e) {
					System.out.println("This column is full please choose another");
				}

				ui.drawBoard(obj.getGameBoard());
				if (obj.checkWin(playerX) == true) {
					System.out.println("PlayerX Wins!");
					break;
				}

				System.out.print("PlayerO - your turn. Choose column number from 1 - 7: ");
				int playerInput1 = scanner.nextInt();

				try {
					obj.setMove(playerInput1, playerO);
				} catch (columnFullException e) {
					System.out.println("This column is full please choose another");
				}

				ui.drawBoard(obj.getGameBoard());
				if (obj.checkWin(playerO) == true) {
					System.out.println("PlayerO Wins!");
					break;
				}
			}
		}

		scanner.close();
	}

	// private int playerInput;

	/**
	 * Formats the gameboard and prints it to the console
	 * 
	 * @param int[][] - excepts a 2d array of integers
	 */
	public void drawBoard(int[][] gameBoard) {

		for (int i = 0; i < 6; i++) {
			for (int j = 0; j < 7; j++) {
				System.out.print('|');

				switch (gameBoard[i][j]) {
					case 0:
						System.out.print(' ');
						break;
					case 1:
						System.out.print('X');
						break;
					case 2:
						System.out.print('O');
						break;
					default:
						System.out.print(' ');
				}

				if (j == 6)
					System.out.print('|');

			}
			System.out.println();
		}
	}

}
