import java.util.Scanner;
import java.io.File;
import java.util.Arrays;
class PageRank {
	PageRank(Digraph dg) {

	}

}

class WebSearch {
	WebSearch(PageRank rk, String name) {

	}

}

public class Solution {

	public static void main(String[] args) {
		// read the first line of the input to get the number of vertices

		// iterate count of vertices times
		// to read the adjacency list from std input
		// and build the graph


		// Create page rank object and pass the graph object to the constructor

		// print the page rank object

		// This part is only for the final test case

		// File path to the web content
		String file = "WebContent.txt";

		// instantiate web search object
		// and pass the page rank object and the file path to the constructor
		// read the search queries from std in
		// remove the q= prefix and extract the search word
		// pass the word to iAmFeelingLucky method of web search
		// print the return value of iAmFeelingLucky


		Scanner sc = new Scanner(System.in);
		int v = Integer.parseInt(sc.nextLine());
		int[][] graph = new int[v][v];
		int y, x;
		int z = v;
		int e = 0;
		while (v > 0) {
			v--;
			y = 0;
			x = 0;
			String[] line = sc.nextLine().split(" ");
			y = Integer.parseInt(line[0]);
			for (int i = 1; i < line.length; i++) {
				x = Integer.parseInt(line[i]);
				graph[y][x] = 1;
				e++;

			}
			System.out.println(z + " vertices, " + e + " edges ");
			for (int i = 0; i < x; i++) {
				System.out.print(i + ":");
				for (int j = z - 1; j >= 0; j--) {
					if (graph[i][j] == 1) {
						System.out.print(" " + j);
					}
				}
				System.out.println();
			}

		}
	}
}