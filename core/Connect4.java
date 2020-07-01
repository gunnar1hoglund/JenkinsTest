package core;
/**
 * @author Gunnar Hoglund
 * @version 0.1
 */

/**
 * This class contains all the methods and data methods required to handle the
 * logic for the connect4 game
 */
public class Connect4 {

	private int[][] gameBoard;
	private int[] playerMove; /*
								 * stores the indices that the last player piece was added the first number is
								 * the row index, the second is the column index
								 */

	public Connect4() {
		gameBoard = new int[6][7];
		playerMove = new int[2];
	}

	/**
	 * 
	 * @return this method returns true if a win condition has been met returns
	 *         false otherwise
	 * @param whichPlayer - pass whichever players number you're checking to see if won
	 */
	public boolean checkWin(int whichPlayer) {

		int width = gameBoard[0].length - 1;
		int height = gameBoard.length - 1;

		// diagonal check
		for (int i = 3; i < width; i++) {
			for (int j = 0; j < height - 3; j++) {
				if (gameBoard[i][j] == whichPlayer && gameBoard[i - 1][j + 1] == whichPlayer
						&& gameBoard[i - 2][j + 2] == whichPlayer && gameBoard[i - 3][j + 3] == whichPlayer)
					return true;
			}
		}

		// diagonal check the other way
		for (int i = 3; i < width; i++) {
			for (int j = 3; j < height; j++) {
				if (gameBoard[i][j] == whichPlayer && gameBoard[i - 1][j - 1] == whichPlayer
						&& gameBoard[i - 2][j - 2] == whichPlayer && gameBoard[i - 3][j - 3] == whichPlayer)
					return true;
			}
		}

		// checking for four in a row vertically
		for (int i = 0; i < width - 3; i++) {
			for (int j = 0; j < height; j++) {
				if (gameBoard[i][j] == whichPlayer && gameBoard[i + 1][j] == whichPlayer
						&& gameBoard[i + 2][j] == whichPlayer && gameBoard[i + 3][j] == whichPlayer)
					return true;
			}
		}

		// horizontal
		for (int j = 0; j < width - 3; j++) {
			for (int i = 0; i < height; i++) {
				if (gameBoard[i][j] == whichPlayer && gameBoard[i][j + 1] == whichPlayer
						&& gameBoard[i][j + 2] == whichPlayer && gameBoard[i][j + 3] == whichPlayer)
					return true;
			}
		}

		return false;
	}

	/**
	 * this method should be called when a player makes a move, this handles the
	 * actual manipulation of the array and adds a new game piece @param gameBoard,
	 * accepts a 2d array of integers @exception
	 */
	public boolean setMove(int input, int whichPlayer) throws columnFullException {

		input -= 1;
		// System.out.println(gameBoard.length);
		int row = gameBoard.length;

		//
		do {
			row--;
		} while (gameBoard[row][input] != 0 && row != 0);

		if (row == 0) {
			throw new columnFullException();
		} else {
			gameBoard[row][input] = whichPlayer;
			playerMove[0] = row;
			playerMove[1] = input; // updating the last player move
			return true;
		}
	}

	/**
	 * getter for the gameBoard array
	 * 
	 * @return int[][] - returns a 2d array of integers
	 */
	public int[][] getGameBoard() {
		return gameBoard;
	}

	/**
	 * This method checks if a move is within the gameBoard and either returns true
	 * or false if the column a user chooses is valid, but is already filled with
	 * pieces an exception is thrown
	 * 
	 * @param input - int
	 * @exception columnFullException
	 */
	public boolean validMove(int input) throws columnFullException {

		boolean returnValue = false;

		// if the top most index in the array is not empty, it can be
		// assumed that the whole column is full and a new piece can not be added
		if (gameBoard[0][input] != 0) {
			throw new columnFullException();
		}

		if (input < 1 || input > 7) {
			returnValue = true;
		} else {
			returnValue = false;
		}

		return returnValue;
	}

}
