// Constructor-only Tic Tac Toe
class Player {
    String name;
    char symbol;

    // Constructor to initialize player
    Player(String name, char symbol) {
        this.name = name;
        this.symbol = symbol;
        System.out.println(name + " is ready with symbol '" + symbol + "'");
    }
}

class TicTacToe {
    char[][] board;
    Player p1, p2;
    char winner = '-';

    // Constructor to initialize everything and run the game
    TicTacToe() {
        // Create players using constructor
        p1 = new Player("Player 1", 'X');
        p2 = new Player("Player 2", 'O');

        // Create 3x3 board
        board = new char[3][3];
        for (int i = 0; i < 3; i++)
            for (int j = 0; j < 3; j++)
                board[i][j] = '-';

        System.out.println("\n--- TIC TAC TOE BOARD ---");
        printBoard();

        // Simulated moves (only for demo — all inside constructor)
        board[0][0] = p1.symbol;
        board[0][1] = p2.symbol;
        board[1][1] = p1.symbol;
        board[1][0] = p2.symbol;
        board[2][2] = p1.symbol; // Player 1 wins diagonally

        System.out.println("\nAfter moves:");
        printBoard();

        // Check winner (simple logic inside constructor)
        if (checkWinner('X')) {
            winner = 'X';
            System.out.println("\n Win! " + p1.name + " wins!");
        } else if (checkWinner('O')) {
            winner = 'O';
            System.out.println("\n Win! " + p2.name + " wins!");
        } else {
            System.out.println("\n == It's a draw!");
        }
    }

    // Print board (small helper, not main game logic)
    void printBoard() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++)
                System.out.print(board[i][j] + " ");
            System.out.println();
        }
    }

    // Check if a player has won
    boolean checkWinner(char symbol) {
        // Rows and columns
        for (int i = 0; i < 3; i++) {
            if (board[i][0] == symbol && board[i][1] == symbol && board[i][2] == symbol)
                return true;
            if (board[0][i] == symbol && board[1][i] == symbol && board[2][i] == symbol)
                return true;
        }
        // Diagonals
        if (board[0][0] == symbol && board[1][1] == symbol && board[2][2] == symbol)
            return true;
        if (board[0][2] == symbol && board[1][1] == symbol && board[2][0] == symbol)
            return true;

        return false;
    }
}

public class Board {
    public static void main(String[] args) {
        new TicTacToe(); // Everything runs inside constructor
    }
}