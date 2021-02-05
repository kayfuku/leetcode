package leetcode;

import java.util.ArrayDeque;
import java.util.Queue;

public class RottingOranges {
  // fields and classes here.
  // private int count;

  public RottingOranges() {
    // Initialization here.
    // this.count = 0;

  }

  // 1. BFS with multiple starts
  // O(MN) time and space, where the grid size is M x N.
  // Author: leetcode + kei
  // Date : February 4, 2021
  public int orangesRotting(int[][] grid) {
    if (grid == null || grid.length == 0) {
      return 0;
    }
    int R = grid.length, C = grid[0].length;

    // 1). Build the initial set of rotten oranges.
    int freshOranges = 0;
    Queue<int[]> queue = new ArrayDeque<>();
    for (int r = 0; r < R; r++) {
      for (int c = 0; c < C; c++) {
        if (grid[r][c] == 2) {
          queue.offer(new int[] { r, c });
        } else if (grid[r][c] == 1) {
          freshOranges++;
        }
      }
    }
    // Corner case! Don't forget this!
    // This is necessary because minutes starts with -1 and if there is no
    // rotten orage and fresh orange in the first place, then it should return
    // 0, not -1 according to the problem statement.
    if (queue.isEmpty()) {
      // In the case where [[0]].
      return freshOranges == 0 ? 0 : -1;
    }

    // 2). Start the rotting process via BFS.
    // 0: up, 1: right, 2: down, 3: left
    int[][] directions = { { -1, 0 }, { 0, 1 }, { 1, 0 }, { 0, -1 } };
    int minutes = -1;
    while (!queue.isEmpty()) {
      int size = queue.size();
      for (int i = 0; i < size; i++) {
        int[] p = queue.poll();
        int row = p[0];
        int col = p[1];
        for (int[] d : directions) {
          int nr = row + d[0];
          int nc = col + d[1];
          if (nr >= 0 && nr < R && nc >= 0 && nc < C) {
            if (grid[nr][nc] == 1) {
              queue.offer(new int[] { nr, nc });
              // This orange will be contaminated.
              // This serves as in-place visited.
              grid[nr][nc] = 2;
              freshOranges--;
            }
          }
        }
      }
      minutes++;
    }

    // Return elapsed minutes if no fresh orange left.
    return freshOranges == 0 ? minutes : -1;
  }

  // 2. In-place BFS (not reviewed yet)
  // Author: leetcode + kei
  // Date : February 4, 2021

  // run the rotting process, by marking the rotten oranges with the timestamp
  public boolean runRottingProcess(int timestamp, int[][] grid, int R, int C) {
    int[][] directions = { { -1, 0 }, { 0, 1 }, { 1, 0 }, { 0, -1 } };
    // flag to indicate if the rotting process should be continued
    boolean toBeContinued = false;
    for (int r = 0; r < R; r++) {
      for (int c = 0; c < C; c++) {
        if (grid[r][c] == timestamp) {
          // current contaminated cell
          for (int[] d : directions) {
            int nr = r + d[0];
            int nc = c + d[1];
            if (nr >= 0 && nr < R && nc >= 0 && nc < C) {
              if (grid[nr][nc] == 1) {
                // this fresh orange would be contaminated next
                grid[nr][nc] = timestamp + 1;
                toBeContinued = true;
              }
            }
          }
        }
      }
    }
    return toBeContinued;
  }

  public int orangesRotting2(int[][] grid) {
    int R = grid.length, C = grid[0].length;
    int timestamp = 2;
    while (runRottingProcess(timestamp, grid, R, C)) {
      timestamp++;
    }

    // end of process, to check if there are still fresh oranges left
    for (int[] r : grid) {
      for (int cell : r) {
        // still got a fresh orange left
        if (cell == 1) {
          return -1;
        }
      }
    }

    // return elapsed minutes if no fresh orange left
    return timestamp - 2;
  }

  // For testing.
  @SuppressWarnings("unused")
  public static void main(String[] args) {
    RottingOranges solution = new RottingOranges();

    // Test arguments.
    // int num = 24;
    // int target = 2;
    // System.out.println(solution.getInt(num, target));

    System.out.println("\ndone.");
  }

}
