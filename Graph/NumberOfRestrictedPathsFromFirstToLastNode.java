package leetcode;

import java.util.Arrays;

public class NumberOfRestrictedPathsFromFirstToLastNode {
  // fields and classes here.
  // private int count;

  public NumberOfRestrictedPathsFromFirstToLastNode() {
    // Initialization here.
    // this.count = 0;

  }

  // Author: uwi + renascencepjw0510 + kei
  // Date : March 13, 2021
  public int countRestrictedPaths(int n, int[][] edges) {
    GraphWU g = new GraphWU(n);
    for (int[] e : edges) {
      g.addEgde(e[0], e[1], e[2]);
    }

    // Dijkstra
    // i: node index, dist[i]: the shortest distance to node n
    long[] dist = g.dijk(n);

    // TODO
    long[][] di = new long[n][];
    for (int i = 0; i < n; i++) {
      di[i] = new long[] { dist[i], i + 1 };
    }
    // Sort in descending order by the distance to node n.
    Arrays.sort(di, (x, y) -> Long.compare(y[0], x[0]));

    // Count the number of restricted path from node 1. node x => node y
    long[] counts = new long[n + 1];
    counts[1] = 1;
    final int D = 1000000007;
    for (long[] d : di) {
      int x = (int) d[1];
      for (Edge e : g.adjacencyList[x]) {
        int y = e.dest;
        if (dist[y] < dist[x]) {
          // counts[y] = (counts[y] + counts[x]) % D;
          counts[y] += counts[x];
          if (counts[y] >= D) {
            counts[y] -= D;
          }
        }
      }
    }

    return (int) counts[n];
  }

  // For testing.
  @SuppressWarnings("unused")
  public static void main(String[] args) {
    NumberOfRestrictedPathsFromFirstToLastNode solution = new NumberOfRestrictedPathsFromFirstToLastNode();

    // Test arguments.
    // int num = 24;
    // int target = 2;
    // System.out.println(solution.getInt(num, target));

    System.out.println("\ndone.");
  }

}
