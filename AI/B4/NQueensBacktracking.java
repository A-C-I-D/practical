import java.util.*;

public class NQueensBacktracking {
    static int N;

    static boolean isSafe(int[][] board, int row, int col) {
        // Check row
        for (int i = 0; i < col; i++) {
            if (board[row][i] == 1) return false;
        }
        // Check upper diagonal
        for (int i = row, j = col; i >= 0 && j >= 0; i--, j--) {
            if (board[i][j] == 1) return false;
        }
        // Check lower diagonal
        for (int i = row, j = col; i < N && j >= 0; i++, j--) {
            if (board[i][j] == 1) return false;
        }
        return true;
    }

    static boolean solveNQueens(int[][] board, int col) {
        if (col >= N) return true;

        for (int i = 0; i < N; i++) {
            if (isSafe(board, i, col)) {
                board[i][col] = 1;
                if (solveNQueens(board, col + 1)) return true;
                board[i][col] = 0; // Backtrack
            }
        }
        return false;
    }

    static void printBoard(int[][] board) {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter N for N-Queens: ");
        N = sc.nextInt();
        int[][] board = new int[N][N];

        if (solveNQueens(board, 0)) {
            System.out.println("Solution exists:");
            printBoard(board);
        } else {
            System.out.println("No solution exists.");
        }
    }
}