package leetcode;

import java.util.LinkedList;
import java.util.Queue;

public class NumberOfProvinces {
  // fields and classes here.
  // private int count;

  public NumberOfProvinces() {
    // Initialization here.
    // this.count = 0;

  }

  // 1. DFS, Adjacent Matrix graph
  // Author: + kei
  // Date : February 3, 2021

  int VISITED = 1;

  public int findCircleNum(int[][] M) {
    if (M == null || M.length == 0) {
      return 0;
    }
    int N = M.length;
    int count = 0;

    // Adjacent Matrix graph traversal
    int[] visited = new int[N];
    for (int i = 0; i < N; i++) {
      if (visited[i] == VISITED) {
        continue;
      }
      dfs(M, visited, i);
      count++;
    }

    return count;
  }

  void dfs(int[][] M, int[] visited, int i) {
    for (int j = 0; j < M.length; j++) {
      if (M[i][j] == 1 && visited[j] != VISITED) {
        visited[j] = VISITED;
        dfs(M, visited, j);
      }
    }
  }

  // 2. BFS, Adjacent Matrix graph
  // Author: leetcode + kei
  // Date : February 3, 2021
  public int findCircleNumBFS(int[][] M) {
    int[] visited = new int[M.length];
    int count = 0;
    for (int i = 0; i < M.length; i++) {
      if (visited[i] == VISITED) {
        continue;
      }
      bfs(M, visited, i);
      count++;
    }
    return count;
  }

  private void bfs(int[][] M, int[] visited, int i) {
    Queue<Integer> queue = new LinkedList<>();
    queue.add(i);
    while (!queue.isEmpty()) {
      int n = queue.poll();
      for (int j = 0; j < M.length; j++) {
        if (M[n][j] == 1 && visited[j] != VISITED) {
          queue.add(j);
          visited[j] = VISITED;
        }
      }
    }
  }

  // For testing.
  @SuppressWarnings("unused")
  public static void main(String[] args) {
    NumberOfProvinces solution = new NumberOfProvinces();

    // Test arguments.
    // int num = 24;
    // int target = 2;
    // System.out.println(solution.getInt(num, target));

    System.out.println("\ndone.");
  }

}
