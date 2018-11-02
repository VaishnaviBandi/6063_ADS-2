import java.util.Scanner;
class Bipartite {
    private boolean[] marked;
    private boolean[] color;
    private boolean flag = false;
    private int[] edgeTo;
    private Stack<Integer> stack;
    Bipartite(final Graph graph) {
        flag = true;
        marked = new boolean[graph.vertices()];
        color = new boolean[graph.vertices()];
        edgeTo = new int[graph.vertices()];
        for (int i = 0; i < graph.vertices(); i++) {
            if (!marked[i]) {
                dfs(graph, i);
            }
        }
    }
    private void dfs(final Graph graph, final int vertex) {
        marked[vertex] = true;
        for (int each : graph.adj(vertex)) {
            if (stack != null) {
                return;
            }
            if (!marked[each]) {
                color[each] = !color[vertex];
                edgeTo[each] = vertex;
                dfs(graph, each);
            } else if (color[each] == color[vertex]) {
                flag = false;
                stack = new Stack<Integer>();
                stack.push(each);
                for (int j = vertex; j != each; j = edgeTo[j]) {
                    stack.push(j);
                }
                stack.push(each);
            }
        }
    }
    public boolean isBipartite() {
        return flag;
    }
}
final class Solution {
    private Solution() {
    }
    public static void main(final String[] args) {
        Scanner scan = new Scanner(System.in);
        Graph obj = new Graph(scan);
        Bipartite bipartiteObj = new Bipartite(obj);
        if (bipartiteObj.isBipartite()) {
            System.out.println("Graph is bipartite");
        } else {
            System.out.println("Graph is not a bipartite");
        }
    }
}