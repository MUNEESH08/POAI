import java.util.*;

public class SimpleDFS {
    private List<List<Integer>> adjacencyList; // Adjacency list representation

    public SimpleDFS(int vertices) {
        adjacencyList = new ArrayList<>();
        for (int i = 0; i < vertices; i++) {
            adjacencyList.add(new ArrayList<>());
        }
    }

    public void addEdge(int source, int destination) {
        adjacencyList.get(source).add(destination); // Add edge
        adjacencyList.get(destination).add(source); // For undirected graph
    }

    public void dfs(int startVertex) {
        boolean[] visited = new boolean[adjacencyList.size()];
        System.out.println("DFS starting from vertex " + startVertex + ":");
        dfsUtil(startVertex, visited);
    }

    private void dfsUtil(int vertex, boolean[] visited) {
        visited[vertex] = true;
        System.out.print(vertex + " ");

        for (int neighbor : adjacencyList.get(vertex)) {
            if (!visited[neighbor]) {
                dfsUtil(neighbor, visited);
            }
        }
    }

    public static void main(String[] args) {
        SimpleDFS graph = new SimpleDFS(6); // Create a graph with 6 vertices
        graph.addEdge(0, 1);
        graph.addEdge(0, 2);
        graph.addEdge(1, 3);
        graph.addEdge(1, 4);
        graph.addEdge(2, 5);

        graph.dfs(0); // Start DFS from vertex 0
    }
}