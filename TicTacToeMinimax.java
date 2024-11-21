import java.util.Scanner;

public class TicTacToeMinimax {
    static char[][] board = {
        { '_', '_', '_' },
        { '_', '_', '_' },
        { '_', '_', '_' }
    };

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Tic-Tac-Toe Game");
        printBoard();

        while (true) {
            // Player's move
            System.out.println("Enter your move (row and column): ");
            int row = sc.nextInt();
            int col = sc.nextInt();

            if (board[row][col] != '_') {
                System.out.println("Invalid move. Try again.");
                continue;
            }
            board[row][col] = 'O';

            // Check if the player wins
            if (isWinner('O')) {
                printBoard();
                System.out.println("You win!");
                break;
            }

            // Computer's move
            System.out.println("Computer's turn...");
            int[] bestMove = findBestMove();
            board[bestMove[0]][bestMove[1]] = 'X';

            // Check if the computer wins
            if (isWinner('X')) {
                printBoard();
                System.out.println("Computer wins!");
                break;
            }

            // Check if it's a draw
            if (isBoardFull()) {
                printBoard();
                System.out.println("It's a draw!");
                break;
            }

            printBoard();
        }
        sc.close();
    }

    // Minimax function
    static int minimax(boolean isMaximizing) {
        if (isWinner('X')) return 10;
        if (isWinner('O')) return -10;
        if (isBoardFull()) return 0;

        int bestScore = isMaximizing ? Integer.MIN_VALUE : Integer.MAX_VALUE;

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j] == '_') {
                    board[i][j] = isMaximizing ? 'X' : 'O';
                    int score = minimax(!isMaximizing);
                    board[i][j] = '_';
                    bestScore = isMaximizing
                        ? Math.max(score, bestScore)
                        : Math.min(score, bestScore);
                }
            }
        }
        return bestScore;
    }

    // Find the best move for the computer
    static int[] findBestMove() {
        int bestScore = Integer.MIN_VALUE;
        int[] bestMove = { -1, -1 };

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j] == '_') {
                    board[i][j] = 'X';
                    int moveScore = minimax(false);
                    board[i][j] = '_';

                    if (moveScore > bestScore) {
                        bestScore = moveScore;
                        bestMove = new int[] { i, j };
                    }
                }
            }
        }
        return bestMove;
    }

    // Check if a player has won
    static boolean isWinner(char player) {
        for (int i = 0; i < 3; i++) {
            if (board[i][0] == player && board[i][1] == player && board[i][2] == player)
                return true;
            if (board[0][i] == player && board[1][i] == player && board[2][i] == player)
                return true;
        }
        return (board[0][0] == player && board[1][1] == player && board[2][2] == player) ||
               (board[0][2] == player && board[1][1] == player && board[2][0] == player);
    }

    // Check if the board is full
    static boolean isBoardFull() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j] == '_') return false;
            }
        }
        return true;
    }

    // Print the current board state
    static void printBoard() {
        for (char[] row : board) {
            for (char cell : row) {
                System.out.print(cell + " ");
            }
            System.out.println();
        }
    }
}