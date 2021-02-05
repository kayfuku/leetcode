package leetcode;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

public class RestoreArrayFromAdjacentPairs {
  // fields and classes here.
  // private int count;

  public RestoreArrayFromAdjacentPairs() {
    // Initialization here.
    // this.count = 0;

  }

  // Author: SaveVMK + kei
  // Date : February 1, 2021
  public int[] restoreArray(int[][] adjacentPairs) {
    Map<Integer, Set<Integer>> g = new HashMap<>();
    int n = adjacentPairs.length + 1;
    int[] ans = new int[n];
    // Create a graph with adjacent list.
    for (int[] ap : adjacentPairs) {
      int u = ap[0];
      int v = ap[1];
      if (!g.containsKey(u)) {
        g.put(u, new HashSet<>());
      }
      g.get(u).add(v);
      if (!g.containsKey(v)) {
        g.put(v, new HashSet<>());
      }
      g.get(v).add(u);
    }

    // Find the start node for traversal.
    int first = -1;
    for (int k : g.keySet()) {
      if (g.get(k).size() == 1) {
        first = k;
      }
    }

    // Build the original array using BFS.
    Queue<Integer> q = new LinkedList<>();
    Set<Integer> visited = new HashSet<>();

    q.add(first);
    visited.add(first);
    int i = 0;
    while (!q.isEmpty()) {
      int num = q.poll();
      ans[i] = num;
      i++;
      for (int v : g.get(num)) {
        if (visited.contains(v)) {
          continue;
        }
        q.add(v);
        visited.add(v);
      }
    }

    // // Build the original array.
    // ans[0] = first;
    // for (int i = 1; i < n; i++) {
    // // Take one out of the set.
    // ans[i] = g.get(ans[i - 1]).iterator().next();
    // // Remove the previous node.
    // g.get(ans[i]).remove(ans[i - 1]);
    // }

    return ans;
  }

  // For testing.
  @SuppressWarnings("unused")
  public static void main(String[] args) {
    RestoreArrayFromAdjacentPairs solution = new RestoreArrayFromAdjacentPairs();

    // Test arguments.
    // int num = 24;
    // int target = 2;
    // System.out.println(solution.getInt(num, target));

    System.out.println("\ndone.");
  }

}
