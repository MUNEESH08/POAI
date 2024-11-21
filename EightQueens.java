public class EightQueens {
    static void printboard(int board[][]) {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                System.out.print(board[i][j] == 1 ? " Q " : " * ");
            }
            System.out.println();
        }
    }

    static boolean isSafe(int board[][], int row, int col) {
        // Check the same column
        for (int i = 0; i < row; i++) {
            if (board[i][col] == 1) return false;
        }

        // Check the upper left diagonal
        for (int i = row, j = col; i >= 0 && j >= 0; i--, j--) {
            if (board[i][j] == 1) return false;
        }

        // Check the upper right diagonal
        for (int i = row, j = col; i >= 0 && j < 8; i--, j++) {
            if (board[i][j] == 1) return false;
        }

        return true;
    }

    static boolean solveNQueens(int board[][], int row) {
        if (row >= 8) return true;
        for (int col = 0; col < 8; col++) {
            if (isSafe(board, row, col)) {
                board[row][col] = 1; // Place the queen
                if (solveNQueens(board, row + 1)) return true;
                board[row][col] = 0; // Backtrack
            }
        }
        return false;
    }

    public static void main(String[] args) {
        int board[][] = new int[8][8];
        if (solveNQueens(board, 0))
            printboard(board);
        else
            System.out.print("No Solution Exists...");
    }
}