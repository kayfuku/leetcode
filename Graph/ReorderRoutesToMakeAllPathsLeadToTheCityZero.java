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


  // Author: beet + kei
  // Date : June 1, 2020
  public int minReorder(int n, int[][] connections) {
    // Adjacent List Graph
    @SuppressWarnings("unchecked")
    List<int[]>[] g = new List[n];
    for (int[] e : connections) {
      int s = e[0], t = e[1];
      // Build undirected graph to do BFS later.
      // 0/1 indicates the direction of the edge, and sum of 1s is going to be
      // the number of edges that we need to change direction when
      // traversing the graph using BFS from node 0.
      if (g[s] == null) {
        g[s] = new ArrayList<>();
      }
      g[s].add(new int[] {t, 1});
      if (g[t] == null) {
        g[t] = new ArrayList<>();
      }
      g[t].add(new int[] {s, 0});
    }

    // BFS
    int ans = 0;
    Set<Integer> visited = new HashSet<>();
    Queue<Integer> q = new LinkedList<>();

    visited.add(0);
    q.add(0);
    while (!q.isEmpty()) {
      int v = q.poll();
      for (int[] e : g[v]) {
        int u = e[0], c = e[1];
        if (visited.contains(u)) {
          continue;
        }
        ans += c;
        visited.add(u);
        q.add(u);
      }
    }

    return ans;
  }


  // For testing.
  @SuppressWarnings("unused")
  public static void main(String[] args) {
    ReorderRoutesToMakeAllPathsLeadToTheCityZero solution =
        new ReorderRoutesToMakeAllPathsLeadToTheCityZero();

    // Test arguments.
    // int num = 24;
    // int target = 2;
    // solution.getInt(num, target);



  }



  public void dummyMethod() {



  }



  public void dummyMethod2() {



  }



}


