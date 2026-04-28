import java.util.*;

public class EightPuzzleAStar {
    static class State implements Comparable<State> {
        int[][] board;
        int g, h, f;
        State parent;
        String move;

        State(int[][] board, int g, State parent, String move) {
            this.board = new int[3][3];
            for (int i = 0; i < 3; i++) {
                System.arraycopy(board[i], 0, this.board[i], 0, 3);
            }
            this.g = g;
            this.h = calculateHeuristic(this.board);
            this.f = this.g + this.h;
            this.parent = parent;
            this.move = move;
        }

        @Override
        public int compareTo(State other) {
            return Integer.compare(this.f, other.f);
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj)
                return true;
            if (obj == null || getClass() != obj.getClass())
                return false;
            State state = (State) obj;
            return Arrays.deepEquals(this.board, state.board);
        }

        @Override
        public int hashCode() {
            return Arrays.deepHashCode(this.board);
        }
    }

    static int[][] goal = { { 1, 2, 3 }, { 4, 5, 6 }, { 7, 8, 0 } };

    static int calculateHeuristic(int[][] board) {
        int h = 0;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j] != 0) {
                    int targetI = (board[i][j] - 1) / 3;
                    int targetJ = (board[i][j] - 1) % 3;
                    h += Math.abs(i - targetI) + Math.abs(j - targetJ);
                }
            }
        }
        return h;
    }

    static List<State> getNeighbors(State state) {
        List<State> neighbors = new ArrayList<>();
        int[][] board = state.board;
        int blankI = -1, blankJ = -1;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j] == 0) {
                    blankI = i;
                    blankJ = j;
                    break;
                }
            }
        }
        int[] di = { -1, 1, 0, 0 };
        int[] dj = { 0, 0, -1, 1 };
        String[] moves = { "Up", "Down", "Left", "Right" };
        for (int k = 0; k < 4; k++) {
            int ni = blankI + di[k];
            int nj = blankJ + dj[k];
            if (ni >= 0 && ni < 3 && nj >= 0 && nj < 3) {
                int[][] newBoard = new int[3][3];
                for (int i = 0; i < 3; i++) {
                    System.arraycopy(board[i], 0, newBoard[i], 0, 3);
                }
                newBoard[blankI][blankJ] = newBoard[ni][nj];
                newBoard[ni][nj] = 0;
                neighbors.add(new State(newBoard, state.g + 1, state, moves[k]));
            }
        }
        return neighbors;
    }

    static State aStar(int[][] initialBoard) {
        PriorityQueue<State> open = new PriorityQueue<>();
        Set<State> closed = new HashSet<>();
        State start = new State(initialBoard, 0, null, "");
        open.add(start);
        while (!open.isEmpty()) {
            State current = open.poll();
            if (Arrays.deepEquals(current.board, goal)) {
                return current;
            }
            closed.add(current);
            for (State neighbor : getNeighbors(current)) {
                if (!closed.contains(neighbor)) {
                    open.add(neighbor);
                }
            }
        }
        return null; // No solution
    }

    static void printBoard(int[][] board) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int[][] initialBoard = new int[3][3];
        System.out.println("Enter the initial board (9 numbers, 0 for blank):");
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                initialBoard[i][j] = sc.nextInt();
            }
        }
        System.out.println("Initial Board:");
        printBoard(initialBoard);
        State goalState = aStar(initialBoard);
        if (goalState != null) {
            List<State> path = new ArrayList<>();
            State current = goalState;
            while (current != null) {
                path.add(0, current);
                current = current.parent;
            }
            System.out.println("Solution found with " + (path.size() - 1) + " moves:");
            for (int i = 0; i < path.size(); i++) {
                if (i > 0) {
                    System.out.println("Move: " + path.get(i).move);
                }
                printBoard(path.get(i).board);
                System.out.println();
            }
        } else {
            System.out.println("No solution found.");
        }
    }
}