package tictactoe.bll;

import java.util.Arrays;

/**
 * The GameBoardTwoPlayer class is the mandatory implementation for the TicTacToe assignment.
 * It is used for games where there are two human players.
 */
public class GameBoardTwoPlayer implements IGameModel {

    public int currentPlayer = 0;
    private int[][] gameGrid;
    private int movesLeft = -9;
    private int winner;

    public GameBoardTwoPlayer() {
        winner = 2;
        gameGrid = new int[3][3];

    }

    /**
     * Returns 0 for player 0, 1 for player 1.
     *
     * @return int Id of the next player.
     */
    @Override
    public int getNextPlayer() {

        return currentPlayer == 0 ? 0 : 1;
    }

    /**
     * Attempts to let the current player play at the given coordinates. It the
     * attempt is successful the current player has ended his turn and it is the
     * next players turn.
     *
     * @param col column to place a marker in.
     * @param row row to place a marker in.
     * @return true if the move is accepted, otherwise false. If gameOver ==
     * true this method will always return false.
     */

    @Override
    public boolean play(int col, int row) {

        if (gameGrid[row][col] == -1 && !isGameOver()) {
            gameGrid[row][col] = currentPlayer;
            currentPlayer = currentPlayer == 0 ? 1 : 0;
            movesLeft++;
            return true;
        } else {
            return false;
        }
    }

    /**
     * Tells us if the game has ended either by draw or by meeting the winning
     * condition.
     *
     * @return true if the game is over, else it will retun false.
     */
    @Override
    public boolean isGameOver() {
        return movesLeft == 0 || winner < 2;
    }

    /**
     * Gets the id of the winner, -1 if its a draw.
     *
     * @return int id of winner, or -1 if draw.
     */
    @Override
    public int getWinner() {

        int m = 0;
        int n = 0;
        // Checks all rows from the top, for a winner. (Horizontally)
        for (int i = 0; i < 3; i++) {
            if (gameGrid[m][i] == 1 && gameGrid[m + 1][i] == 1 && gameGrid[m + 2][i] == 1) {
                winner = 1;
            } else if (gameGrid[m][i] == 0 && gameGrid[m + 1][i] == 0 && gameGrid[m + 2][i] == 0) {
                winner = 0;
                // Checks all columns from the left, for a winner. (Vertically)
            } else if (gameGrid[i][m] == 1 && gameGrid[i][m + 1] == 1 && gameGrid[i][m + 2] == 1) {
                winner = 1;
            } else if (gameGrid[i][m] == 0 && gameGrid[i][m + 1] == 0 && gameGrid[i][m + 2] == 0) {
                winner = 0;
            }
        }
        // Checks diagonally from the left, for a winner. (Vertically)
        if (gameGrid[m][n] == 1 && gameGrid[m + 1][n + 1] == 1 && gameGrid[m + 2][n + 2] == 1) {
            winner = 1;
        } else if (gameGrid[m][n] == 0 && gameGrid[m + 1][n + 1] == 0 && gameGrid[m + 2][n + 2] == 0) {
            winner = 0;
            // Check diagonally from the right, for a winner. (Vertically)
        } else if (gameGrid[m][n + 2] == 1 && gameGrid[m + 1][n + 1] == 1 && gameGrid[m + 2][n] == 1) {
            winner = 1;
        } else if (gameGrid[m][n + 2] == 0 && gameGrid[m + 1][n + 1] == 0 && gameGrid[m + 2][n] == 0) {
            winner = 0;
        }
        return winner;
    }

    /**
     * Resets the game to a new game state.
     */
    @Override
    public void newGame() {
        movesLeft = -9;
        winner = 2;
        resetBoard();

    }
    @Override
    public void resetBoard() {
        for (int i = 0; i<3; i++){
            for(int j = 0; j<3; j++){
                gameGrid[i][j] = -1;
            }
        }
    }
}