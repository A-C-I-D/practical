import java.util.*;

public class GraphColoringBnB {
    static int V;
    static List<Integer>[] adj;
    static int[] color;
    static int chromaticNumber;

    static boolean isSafe(int v, int c) {
        for (int u : adj[v]) {
            if (color[u] == c) return false;
        }
        return true;
    }

    static boolean graphColoringUtil(int v, int m) {
        if (v == V) return true;

        for (int c = 1; c <= m; c++) {
            if (isSafe(v, c)) {
                color[v] = c;
                if (graphColoringUtil(v + 1, m)) return true;
                color[v] = 0; // Backtrack
            }
        }
        return false;
    }

    static boolean canColorWithKColors(int k) {
        Arrays.fill(color, 0);
        return graphColoringUtil(0, k);
    }

    static int findChromaticNumber() {
        int low = 1, high = V;
        chromaticNumber = V;
        while (low <= high) {
            int mid = (low + high) / 2;
            if (canColorWithKColors(mid)) {
                chromaticNumber = mid;
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        return chromaticNumber;
    }

    static void printSolution() {
        System.out.println("Minimum colors needed: " + chromaticNumber);
        for (int i = 0; i < V; i++) {
            System.out.println("Vertex " + i + " -> Color " + color[i]);
        }
    }

    @SuppressWarnings("unchecked")
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter number of vertices: ");
        V = sc.nextInt();
        System.out.print("Enter number of edges: ");
        int E = sc.nextInt();
        adj = new ArrayList[V];
        for (int i = 0; i < V; i++) adj[i] = new ArrayList<>();
        color = new int[V];

        System.out.println("Enter edges (u v):");
        for (int i = 0; i < E; i++) {
            int u = sc.nextInt();
            int v = sc.nextInt();
            adj[u].add(v);
            adj[v].add(u);
        }

        findChromaticNumber();
        // Color with minimum colors
        canColorWithKColors(chromaticNumber);
        printSolution();
    }
}