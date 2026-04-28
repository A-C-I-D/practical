import java.util.*;

public class PrimsMST {
    static final int INF = Integer.MAX_VALUE;

    public static void primsMST(int[][] graph, int V, List<String> vertexNames) {
        int[] parent = new int[V];
        int[] key = new int[V];
        boolean[] mstSet = new boolean[V];

        Arrays.fill(key, INF);
        Arrays.fill(parent, -1);
        key[0] = 0;

        for (int count = 0; count < V - 1; count++) {
            int u = minKey(key, mstSet, V);
            mstSet[u] = true;

            for (int v = 0; v < V; v++) {
                if (graph[u][v] != 0 && !mstSet[v] && graph[u][v] < key[v]) {
                    parent[v] = u;
                    key[v] = graph[u][v];
                }
            }
        }

        printMST(parent, graph, V, vertexNames);
    }

    static int minKey(int[] key, boolean[] mstSet, int V) {
        int min = INF, minIndex = -1;
        for (int v = 0; v < V; v++) {
            if (!mstSet[v] && key[v] < min) {
                min = key[v];
                minIndex = v;
            }
        }
        return minIndex;
    }

    static void printMST(int[] parent, int[][] graph, int V, List<String> vertexNames) {
        System.out.println("Edge \tWeight");
        int totalWeight = 0;
        for (int i = 1; i < V; i++) {
            System.out.println(vertexNames.get(parent[i]) + " - " + vertexNames.get(i) + "\t" + graph[i][parent[i]]);
            totalWeight += graph[i][parent[i]];
        }
        System.out.println("Total weight of MST: " + totalWeight);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter number of edges: ");
        int E = sc.nextInt();
        Map<String, Integer> vertexMap = new HashMap<>();
        List<String> vertexNames = new ArrayList<>();
        int[][] graph = new int[100][100]; // Assume max 100 vertices

        System.out.println("Enter edges (vertex1 vertex2 weight):");
        for (int i = 0; i < E; i++) {
            String u = sc.next();
            String v = sc.next();
            int w = sc.nextInt();
            if (!vertexMap.containsKey(u)) {
                vertexMap.put(u, vertexNames.size());
                vertexNames.add(u);
            }
            if (!vertexMap.containsKey(v)) {
                vertexMap.put(v, vertexNames.size());
                vertexNames.add(v);
            }
            int ui = vertexMap.get(u);
            int vi = vertexMap.get(v);
            graph[ui][vi] = w;
            graph[vi][ui] = w; // Undirected
        }

        int V = vertexNames.size();
        primsMST(graph, V, vertexNames);
    }
}