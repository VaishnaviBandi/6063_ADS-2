import java.util.Scanner;
import java.util.HashMap;
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

class DijkstraSP {
    private Double[] distTo;

    private Edge[] edgeTo;

    private IndexMinPQ<Double> pq;

    private EdgeWeightedGraph graph;

    DijkstraSP(final EdgeWeightedGraph g,
               final int source) {
        distTo = new Double[g.vertices()];
        edgeTo = new Edge[g.vertices()];
        for (int i = 0; i < g.vertices(); i++) {
            distTo[i] = Double.POSITIVE_INFINITY;
        }
        distTo[source] = 0.0;
        pq = new IndexMinPQ<Double>(g.vertices());
        pq.insert(source, distTo[source]);
        while (!pq.isEmpty()) {
            int vertex = pq.delMin();
            for (Edge edge : g.adj(vertex)) {
                relax(edge, vertex);
            }
        }
    }

    private void relax(final Edge edge,
                       final int vertex) {
        int vertexTwo = edge.other(vertex);
        if (distTo[vertexTwo] > distTo[vertex] + edge.getWeight()) {
            distTo[vertexTwo] = distTo[vertex] + edge.getWeight();
            edgeTo[vertexTwo] = edge;
            if (pq.contains(vertexTwo)) {
                pq.decreaseKey(vertexTwo, distTo[vertexTwo]);
            } else {
                pq.insert(vertexTwo, distTo[vertexTwo]);
            }
        }
    }

    public double distTo(final int v) {
        return distTo[v];
    }

    public boolean hasPathTo(final int v) {
        return distTo[v] < Double.POSITIVE_INFINITY;
    }
    
    public Iterable<Edge> pathTo(final int v) {
        if (!hasPathTo(v)) {
            return null;
        }
        Stack<Edge> path = new Stack<Edge>();
        int x = v;
        for (Edge e = edgeTo[v]; e != null; e = edgeTo[x]) {
            path.push(e);
            x = e.other(x);
        }
        return path;
    }
    
    public double getDistance(final int vertex) {
        double sum = 0;
        for (Edge each : pathTo(vertex)) {
            sum += each.getWeight();
        }
        return sum;
    }
}

final class Solution {
    /**
     * Constructs the object.
     */
    private Solution() {

    }

    public static void main(final String[] args) {
        Scanner scan = new Scanner(System.in);
        HashMap<String, Integer> map = new HashMap<String, Integer>();
        String[] inputs = scan.nextLine().split(" ");
        int vertices = Integer.parseInt(inputs[0]);
        int edges = Integer.parseInt(inputs[1]);
        String[] cities = scan.nextLine().split(" ");
        for (int i = 0; i < vertices; i++) {
            map.put(cities[i], i);
        }
        Edge obj;
        EdgeWeightedGraph graph = new EdgeWeightedGraph(vertices);
        for (int i = 0; i < edges; i++) {
            String[] routes = scan.nextLine().split(" ");
            obj = new Edge(map.get(routes[0]), map.get(routes[1]),
                           Double.parseDouble(routes[2]));
            graph.addEdge(obj);
        }
        int numQueries = Integer.parseInt(scan.nextLine());
        DijkstraSP dObj;
        for (int i = 0; i < numQueries; i++) {
            String[] queries = scan.nextLine().split(" ");
            int source = map.get(queries[0]);
            dObj = new DijkstraSP(graph, source);
            System.out.println((int) dObj.getDistance(map.get(queries[1])));
        }

    }
}