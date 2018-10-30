/**graphimplementation.**/
final class Matrix {
    private final int vertices;
    private int edges;
    private int[][] adj;
    private String[] matrices;
    private int size;
     Matrix(final int vert) {
        if (vert < 0) {
            throw new IllegalArgumentException(
            "Too few vertices");
        }
        this.vertices = vert;
        this.edges = 0;
        this.adj = new int[vert][vert];
        this.matrices = new String[vert];
        this.size = 0;
    }
      public int vertex() {
        return vertices;
      }
    public void addEdge(final int v, final int w) {
    if (v == w) {
        System.out.println(vertices + " vertices, " + edges + " edges");
        System.out.println("No edges");
        return;
    }
        if (adj[v][w] == 0) {
            edges++;
        }
        adj[v][w] = 1;
        adj[w][v] = 1;
    }
    public void addmat(final String s) {
        matrices[size++] = s;
    }
 public String toString() {
        StringBuilder s = new StringBuilder();
        s.append(vertices + " vertices, " + edges + " edges" + "\n");
        for (int v = 0; v < vertices; v++) {
            for (int w : adj[v]) {
                if (w == 1) {
                s.append(1 + " ");
            } else {
                s.append(0 + " ");
            }
            }
            s.append("\n");
        }
        return s.toString();
    }

}
