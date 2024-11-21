import java.util.*;

public class WaterJugProblem {
    static void solve(int jug1, int jug2, int target) {
        // To store visited states
        Set<String> visited = new HashSet<>();
        // Stack for DFS
        Stack<int[]> stack = new Stack<>();
        stack.push(new int[]{0, 0}); // Initial state

        // List to store the path
        List<int[]> path = new ArrayList<>();

        while (!stack.isEmpty()) {
            int[] state = stack.pop();
            int x = state[0], y = state[1];

            // If target is reached
            if (x == target || y == target) {
                path.add(state); // Add the final state to the path
                for (int[] p : path) {
                    System.out.println("(" + p[0] + ", " + p[1] + ")");
                }
                return;
            }

            // Check if state is already visited
            String key = x + "," + y;
            if (visited.contains(key)) {
                continue;
            }
            visited.add(key);

            // Add current state to the path
            path.add(new int[]{x, y});

            // Add possible moves to the stack
            stack.push(new int[]{jug1, y}); // Fill Jug 1
            stack.push(new int[]{x, jug2}); // Fill Jug 2
            stack.push(new int[]{0, y});    // Empty Jug 1
            stack.push(new int[]{x, 0});    // Empty Jug 2
            stack.push(new int[]{Math.min(x + y, jug1), Math.max(0, x + y - jug1)}); // Pour Jug 2 -> Jug 1
            stack.push(new int[]{Math.max(0, x + y - jug2), Math.min(x + y, jug2)}); // Pour Jug 1 -> Jug 2
        }

        // If no solution
        System.out.println("No Solution");
    }

    public static void main(String[] args) {
        int jug1 = 4, jug2 = 3, target = 2;
        System.out.println("Path from (0, 0) to the target:");
        solve(jug1, jug2, target);
    }
}