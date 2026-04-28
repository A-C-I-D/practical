import java.util.*;

public class GraphTraversal {
    static void depthFirstSearch(List<List<Integer>> graph, int current, boolean[] visited) {
        visited[current] = true;
        System.out.print(current + " ");
        for (int nextNode : graph.get(current)) {
            if (!visited[nextNode]) {
                depthFirstSearch(graph, nextNode, visited);
            }
        }
    }

    static void performBFS(List<List<Integer>> adjList, int source) {
        boolean[] isVisited = new boolean[adjList.size()];
        Queue<Integer> nodes = new ArrayDeque<>();
        StringBuilder result = new StringBuilder();
        nodes.offer(source);
        isVisited[source] = true;
        while (!nodes.isEmpty()) {
            int current = nodes.poll();
            result.append(current).append(" ");
            for (int next : adjList.get(current)) {
                if (!isVisited[next]) {
                    isVisited[next] = true;
                    nodes.offer(next);
                }
            }
        }
        System.out.println("Breadth First Search: " + result);
    }

    static List<List<Integer>> buildGraph(int vertices, int edges, Scanner input) {
        List<List<Integer>> graph = new ArrayList<>();
        for (int i = 0; i < vertices; i++) {
            graph.add(new ArrayList<>());
        }
        System.out.println("Enter edges:");
        for (int i = 0; i < edges; i++) {
            int source = input.nextInt();
            int destination = input.nextInt();
            graph.get(source).add(destination);
            graph.get(destination).add(source);
        }
        return graph;
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.println("Graph Traversal Menu:");
        System.out.println("1. Depth First Search (DFS)");
        System.out.println("2. Breadth First Search (BFS)");
        System.out.print("Enter your choice (1 or 2): ");
        int choice = input.nextInt();
        System.out.print("Enter total vertices: ");
        int vertices = input.nextInt();
        System.out.print("Enter total edges: ");
        int edges = input.nextInt();
        List<List<Integer>> graph = buildGraph(vertices, edges, input);
        System.out.print("Enter source vertex: ");
        int start = input.nextInt();
        switch (choice) {
            case 1:
                boolean[] visited = new boolean[vertices];
                System.out.print("Depth First Search: ");
                depthFirstSearch(graph, start, visited);
                break;
            case 2:
                performBFS(graph, start);
                break;
            default:
                System.out.println("Invalid choice!");
        }
    }
}