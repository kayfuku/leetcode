//
// Author:
// Date  : September 15, 2020

package leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class MinCostToConnectAllPoints {
  // fields and classes here.
  // private int count;

  public MinCostToConnectAllPoints() {
    // Initialization here.
    // this.count = 0;

  }

  // Kruskal's algorithm.
  // Author: uwi + xiaowuc1 + kei
  // Date : September 15, 2020
  public int minCostConnectPoints(int[][] points) {
    int ret = 0;
    int n = points.length;

    List<Edge> edges = new ArrayList<>();
    for (int i = 0; i < n; i++) {
      for (int j = i + 1; j < n; j++) {
        int cost = Math.abs(points[i][0] - points[j][0]) + Math.abs(points[i][1] - points[j][1]);
        edges.add(new Edge(cost, i, j));
      }
    }

    // Sort by weights in non-descending order.
    Collections.sort(edges, (e1, e2) -> e1.weight - e2.weight);

    UF uf = new UF(n);
    // We only need n - 1 edges to create a minimum spanning tree.
    int need = n - 1;
    for (Edge e : edges) {
      int cost = e.weight;
      int x = e.x;
      int y = e.y;
      if (uf.unite(x, y)) {
        ret += cost;
        need--;
        if (need == 0) {
          break;
        }
      }
    }

    return ret;
  }

  class Edge {
    public int weight;
    public int x;
    public int y;

    public Edge(int w, int x, int y) {
      this.weight = w;
      this.x = x;
      this.y = y;
    }
  }

  // Union Find (Disjoint Set)
  class UF {
    public int[] par;

    public UF(int n) {
      par = new int[n];
      Arrays.fill(par, -1);
    }

    // Find root.
    public int find(int x) {
      if (par[x] < 0) {
        return x;
      }
      return find(par[x]);
    }

    // Merge the two trees.
    // Return true if they are not in the same tree.
    public boolean unite(int x, int y) {
      x = find(x);
      y = find(y);
      if (x != y) {
        // Merge tree y into x.
        par[y] = x;
      }
      return x != y;
    }

    // Check if the two nodes belong to the same tree.
    public boolean same(int x, int y) {
      return find(x) == find(y);
    }
  }

  // For testing.
  @SuppressWarnings("unused")
  public static void main(String[] args) {
    MinCostToConnectAllPoints solution = new MinCostToConnectAllPoints();

    // Test arguments.
    // int num = 24;
    // int target = 2;
    // solution.getInt(num, target);

  }

}
