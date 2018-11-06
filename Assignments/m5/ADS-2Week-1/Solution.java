import java.util.Scanner;
import java.util.Arrays;
class PageRank {

	double[] pageRank;
	Digraph graph;
	double[] prevRank;
	Digraph revGraph;

	PageRank(final Digraph g) {
		
		graph = g;
		int vertices = graph.V();
		pageRank = new double[vertices];
		prevRank = new double[vertices];
		for (int i = 0; i < vertices; i++) {
			pageRank[i] = 1.0 / vertices;
		}
		for (int i = 0; i < vertices; i++) {
			if (graph.outdegree(i) == 0) {
				for (int j = 0; j < vertices; j++) {
					if (j != i) {
						graph.addEdge(i, j);
					}
				}
			}
		}
		for (int i = 0; i < 1000; i++) {
			for (int j = 0; j < vertices; j++) {
				getPR(j);
			}
			pageRank = Arrays.copyOf(prevRank, prevRank.length);
		}

	}

	public double getPR(final int vertex) {
		double sum = 0;
		if (graph.indegree(vertex) == 0) {
			prevRank[vertex] = 0;
			return prevRank[vertex];
		}
		for (int v : graph.reverse().adj(vertex)) {
			sum += (pageRank[v] / graph.outdegree(v));
		}
		prevRank[vertex] = sum;
		return prevRank[vertex];
	}

	public void add() {
		for (int i = 0; i < 1000; i++) {
			for (int j = 0; j < graph.V(); j++) {
				prevRank[j] = pageRank[j];
				pageRank[j] = getPR(j);
			}
		}
	}

	public String toString() {
		String str = "";
		for (int i = 0; i < graph.V(); i++) {
			str += i + " - " + pageRank[i] + "\n";
		}
		return str;
	}

}

class WebSearch {

}

public class Solution {

	public static void main(final String[] args) {

		Scanner scan = new Scanner(System.in);
		int vertices = Integer.parseInt(scan.nextLine());
		Digraph graph = new Digraph(vertices);
		for (int i = 0; i < vertices; i++) {
			String[] tokens = scan.nextLine().split(" ");
			for (int j = 1; j < tokens.length; j++) {
				graph.addEdge(Integer.parseInt(tokens[0]),
				              Integer.parseInt(tokens[j]));
			}
		}
		System.out.println(graph);
		PageRank pageObj = new PageRank(graph);
		System.out.println(pageObj.toString());
		String file = "WebContent.txt";

		// read the first line of the input to get the number of vertices

		// iterate count of vertices times
		// to read the adjacency list from std input
		// and build the graph


		// Create page rank object and pass the graph object to the constructor

		// print the page rank object

		// This part is only for the final test case

		// File path to the web content

		// instantiate web search object
		// and pass the page rank object and the file path to the constructor

		// read the search queries from std in
		// remove the q= prefix and extract the search word
		// pass the word to iAmFeelingLucky method of web search
		// print the return value of iAmFeelingLucky

	}
}