//
// Author:
// Date : May 31, 2020

package leetcode;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;

public class ReorderRoutesToMakeAllPathsLeadToTheCityZero {
  // fields and classes here.
  // private int count;

  public ReorderRoutesToMakeAllPathsLeadToTheCityZero() {
    // Initialization here.
    // this.count = 0;

  }

  // 1. BFS
  // Author: beet + kei
  // Date : June 1, 2020
  @SuppressWarnings("unchecked")
  public int minReorder(int n, int[][] connections) {
    // Build undirected graph to do BFS later.
    // Adjacency List Graph
    // Store the source node in an array of list, and
    // store the target node and the direction in an array of int.
    List<int[]>[] g = new List[n];
    for (int[] e : connections) {
      // Get source and target.
      int s = e[0], t = e[1];
      // 0/1 indicates the direction of the edge, and the sum of 1s is going to be
      // the number of edges that we need to change direction when
      // traversing the graph using BFS from node 0.
      if (g[s] == null) {
        g[s] = new ArrayList<>();
      }
      g[s].add(new int[] { t, 1 });
      // Store the other way.
      if (g[t] == null) {
        g[t] = new ArrayList<>();
      }
      g[t].add(new int[] { s, 0 });
    }

    // BFS
    int ans = 0;
    Queue<Integer> q = new LinkedList<>();
    // This is necessary during the BFS because even if the network is a tree,
    // the data structure that we use is an adjacency list (graph) that
    // considers two nodes adjacent. Otherwise, we would go back to the previous
    // node.
    Set<Integer> visited = new HashSet<>();

    q.add(0);
    visited.add(0);
    while (!q.isEmpty()) {
      int u = q.poll();
      for (int[] e : g[u]) {
        int v = e[0], d = e[1];
        if (visited.contains(v)) {
          continue;
        }
        ans += d;
        q.add(v);
        visited.add(v);
      }
    }

    return ans;
  }

  // 2. DFS
  // Author: kei (AC)
  // Date : October 9, 2020
  @SuppressWarnings("unchecked")
  public int minReorderR3(int n, int[][] connections) {
    // Graph
    List<int[]>[] g = new List[n];
    for (int[] e : connections) {
      int s = e[0];
      int t = e[1];
      if (g[s] == null) {
        g[s] = new ArrayList<>();
      }
      g[s].add(new int[] { t, 1 });
      if (g[t] == null) {
        g[t] = new ArrayList<>();
      }
      g[t].add(new int[] { s, 0 });
    }

    // DFS
    Set<Integer> visited = new HashSet<>();
    int count = dfs(g, visited, 0);

    return count;
  }

  int dfs(List<int[]>[] g, Set<Integer> visited, int c) {
    int cnt = 0;
    visited.add(c);
    for (int[] e : g[c]) {
      int t = e[0];
      int d = e[1];
      if (!visited.contains(t)) {
        cnt += d + dfs(g, visited, t);
      }
    }

    return cnt;
  }

  // For testing.
  @SuppressWarnings("unused")
  public static void main(String[] args) {
    ReorderRoutesToMakeAllPathsLeadToTheCityZero solution = new ReorderRoutesToMakeAllPathsLeadToTheCityZero();

    // Test arguments.
    // int num = 24;
    // int target = 2;
    // solution.getInt(num, target);

  }

}
