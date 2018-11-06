import java.util.Scanner;

final class Solution {
    
    private Solution() {

    }
    
    public static void main(final String[] args) {
        Scanner scan = new Scanner(System.in);
        int vertices = Integer.parseInt(scan.nextLine());
        int edges = Integer.parseInt(scan.nextLine());
        Edge edgeObj;
        EdgeWeightedGraph graph
            = new EdgeWeightedGraph(vertices);
        for (int i = 0; i < edges; i++) {
            String[] tokens = scan.nextLine().split(" ");
            edgeObj = new Edge(Integer.parseInt(tokens[0]),
                               Integer.parseInt(tokens[1]),
                               Double.parseDouble(tokens[2]));
            graph.addEdge(edgeObj);
        }
        MSTree mstObj = new MSTree(graph);
        double result = mstObj.total();
        System.out.format("%.5f", result);
    }
}

class Edge implements Comparable<Edge> {

    private int vertexOne;

    private int vertexTwo;

    private double weight;
    

    Edge(final int v, final int w, final double wei) {
        this.vertexOne = v;
        this.vertexTwo = w;
        this.weight = wei;
    }
    
    public int either() {
        return vertexOne;
    }
    
    public int other(final int v) {
        if (vertexOne == v) {
            return vertexTwo;
        }
        return vertexOne;
    }
    
    public int compareTo(final Edge that) {
        if (this.weight < that.weight) {
            return -1;
        } else if (this.weight > that.weight) {
            return 1;
        }
        return 0;
    }
    
    public double getWeight() {
        return this.weight;
    }
}

class EdgeWeightedGraph {
    
    private int vertices;
    
    private Bag<Edge>[] adj;
    
    EdgeWeightedGraph(final int v) {
        this.vertices = v;
        adj = new Bag[vertices];
        for (int i = 0; i < vertices; i++) {
            adj[i] = new Bag<Edge>();
        }
    }
    
    public void addEdge(final Edge e) {
        int first = e.either();
        int second = e.other(first);
        adj[first].add(e);
        adj[second].add(e);
    }
    
    public Iterable<Edge> edges() {
        Bag<Edge> list = new Bag<Edge>();
        for (int i = 0; i < vertices; i++) {
            for (Edge e : adj(i)) {
                list.add(e);
            }
        }
        return list;
    }

    public Iterable<Edge> adj(final int vertex) {
        return adj[vertex];
    }

    public int vertices() {
        return this.vertices;
    }

}

class MSTree {
    
    private EdgeWeightedGraph graph;
    
    private Queue<Edge> mst;
    
    MSTree(final EdgeWeightedGraph g) {
        graph = g;
        mst = new Queue<Edge>();
        MinPQ<Edge> pq = new MinPQ<Edge>();
        for (Edge edge : graph.edges()) {
            pq.insert(edge);
        }
        UF ufObj = new UF(graph.vertices());
        while (!pq.isEmpty()
                && mst.size() < graph.vertices() - 1) {
            Edge edge = pq.delMin();
            int vertexOne = edge.either();
            int vertexTwo = edge.other(vertexOne);
            if (!ufObj.connected(vertexOne, vertexTwo)) {
                ufObj.union(vertexOne, vertexTwo);
                mst.enqueue(edge);
            }
        }
    }
    
    public Iterable<Edge> edges() {
        return mst;
    }

    public double total() {
        double sum = 0.0;
        for (Edge e : edges()) {
            sum += e.getWeight();
        }
        return sum;
    }
}