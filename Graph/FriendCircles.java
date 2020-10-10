//
// Author:
// Date : March 12, 2020

package leetcode;

public class FriendCircles {
  // fields and classes here.
  // private int count;

  public FriendCircles() {
    // Initialization here.
    // this.count = 0;

  }

  // 1. DFS
  // O(N^2) time, O(N) space
  // Author: @vinod23 + kei
  // Date : March 12, 2020
  public int findCircleNum(int[][] M) {
    // M is the Adjacency Matrix of a graph.
    // M.length is the number of nodes in the graph.
    int[] visited = new int[M.length];

    // Count the number of start nodes.
    int count = 0;
    for (int i = 0; i < M.length; i++) {
      if (visited[i] == 0) {
        // Do DFS starting from i-th node.
        // The DFS comes back if it traverses all the connected components.
        dfs(M, visited, i);
        count++;
      }
    }

    return count;
  }

  public void dfs(int[][] M, int[] visited, int i) {
    // Iterate i-th node's neighbors.
    for (int j = 0; j < M.length; j++) {
      if (M[i][j] == 1 && visited[j] == 0) {
        // j-th node is connected to i-th node, and j-th node has not been visited yet.
        visited[j] = 1;
        // Check the next.
        dfs(M, visited, j);
      }
    }
  }

  // Review 3
  public int findCircleNumR3(int[][] M) {
    if (M == null || M.length == 0) {
      return 0;
    }
    int N = M.length;
    int count = 0;
    int[] p = new int[N];
    for (int i = 0; i < N; i++) {
      if (p[i] != 1) {
        dfsR3(M, p, i);
        count++;
      }
    }

    return count;
  }

  void dfsR3(int[][] M, int[] p, int i) {
    for (int j = 0; j < M.length; j++) {
      if (M[i][j] == 1 && p[j] == 0) {
        p[j] = 1;
        dfsR3(M, p, j);
      }
    }
  }

  // For testing.
  @SuppressWarnings("unused")
  public static void main(String[] args) {
    FriendCircles solution = new FriendCircles();

    // Test arguments.
    // int num = 24;
    // int target = 2;
    // solution.getInt(num, target);

  }

}
