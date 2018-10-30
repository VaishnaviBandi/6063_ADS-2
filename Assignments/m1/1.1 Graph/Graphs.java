/**graphimplementation.**/
interface Graph {
    int vertex();
    int edge();
    void addEdge(int v, int w);
}
public class Graphs implements Graph {
    private final int vertices;
    private int edges;
    private int size;
    private Bag<Integer>[] adj;
    private String[] vertex;
     public Graphs(final int vert) {
        if (vert < 0) {
        throw new IllegalArgumentException(
            "Number of vertices must be nonnegative");
    }
        this.vertices = vert;
        this.edges = 0;
        adj = (Bag<Integer>[]) new Bag[vert];
        vertex = new String[vert];
        for (int v = 0; v < vert; v++) {
            adj[v] = new Bag<Integer>();
        }
        size = 0;

    }
     public int vertex() {
        return vertices;
    }
     public int edge() {
        return edges;
    }
    public void addEdge(final int v, final int w) {
    if (v == w) {
        System.out.println(vertices + " vertices, " + edges + " edges");
        System.out.println("No edges");
        return;
    }
        edges++;
        adj[v].add(w);
       adj[w].add(v);
    }
    public void addvertices(final String s) {
        vertex[size++] = s;

    }
     public String toString() {
        StringBuilder s = new StringBuilder();
        s.append(vertices + " vertices, " + edges + " edges" + "\n");
        for (int v = 0; v < vertices; v++) {
            s.append(vertex[v] + ": ");
            for (int w : adj[v]) {
                s.append(vertex[w] + " ");
            }
            s.append("\n");
        }
        return s.toString();
    }

}