import java.util.Scanner;
/**
 * Class for solution.
 */
public final class Solution {
    /**
     * Constructs the object.
     */
    private Solution() {

    }
    /**
     * main method.
     * Time complexity O(E)
     * E is number of edges.
     * for graph query
     * V for printing graph.
     * for directed path query.
     * E+V for dijkstra's constructor.
     * for via paths query.
     * 2(V+E) for two dijkstra's constructor
     * 2V for pathTo method.
     *
     *
     * @param      args  The arguments
     */
    public static void main(final String[] args) {
        Scanner scan = new Scanner(System.in);
        int vertices = Integer.parseInt(scan.nextLine());
        int edges = Integer.parseInt(scan.nextLine());
        EdgeWeightedGraph graph = new EdgeWeightedGraph(vertices);
        for (int i = 0; i < edges; i++) {
            String[] edgeValues = scan.nextLine().split(" ");
            graph.addEdge(new Edge(
                              Integer.parseInt(edgeValues[0]),
                              Integer.parseInt(edgeValues[1]),
                              Double.parseDouble(edgeValues[2])));
        }
        String caseToGo = scan.nextLine();
        switch (caseToGo) {
        case "Graph":
            System.out.println(graph);
            break;

        case "DirectedPaths":
        // Handle the case of DirectedPaths, where two integers are given.
            // First is the source and second is the destination.
            // If the path exists print the distance between them.
            // Other wise print "No Path Found."
            String[] path = scan.nextLine().split(" ");
            DijkstraSP obj = new DijkstraSP(
                graph, Integer.parseInt(path[0]));
            if (obj.hasPathTo(Integer.parseInt(path[1]))) {
                System.out.println(
                    obj.distTo(Integer.parseInt(path[1])));
            } else {
                System.out.println("No Path Found.");
            }
            break;

        case "ViaPaths":
        // Handle the case of ViaPaths, where three integers are given.
            // First is the source and second
        //  is the via is the one where path should pass throuh.
            // third is the destination.
            // If the path exists print the distance between them.
            // Other wise print "No Path Found."
            String[] viaPath = scan.nextLine().split(" ");
            DijkstraSP obj1 = new DijkstraSP(
                graph, Integer.parseInt(viaPath[0]));
            DijkstraSP obj2 = new DijkstraSP(
                graph, Integer.parseInt(viaPath[1]));
            String str = "";
            if (obj1.hasPathTo(Integer.parseInt(
                                   viaPath[1])) && obj2.hasPathTo(
                        Integer.parseInt(viaPath[2]))) {
                System.out.println(obj1.distTo(
                                       Integer.parseInt(
                                           viaPath[1])) + obj2.distTo(
                                       Integer.parseInt(viaPath[2])));
                System.out.println(obj1.pathTo(
                                       Integer.parseInt(
                                           viaPath[1])) + "" + obj2.pathTo(
                                       Integer.parseInt(
                                           viaPath[2])) + viaPath[2]);
            } else {
                System.out.println("No Path Found.");
            }

            break;

        default:
            break;
        }

    }
}




