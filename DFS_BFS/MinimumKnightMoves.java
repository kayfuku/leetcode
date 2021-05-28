package leetcode;

import java.util.HashMap;
import java.util.Map;

public class MinimumKnightMoves {
  // fields and classes here.
  // private int count;

  public MinimumKnightMoves() {
    // Initialization here.
    // this.count = 0;

  }

  // DFS
  // The time complexity of the algorithm is proportional to the total number of
  // invocations of the recursive function.
  // O(|xy|) time and space because we use memoization based on the x, y
  // coordinate and in the worst case, there are |xy| cells between the target
  // and the origin.
  // Author: leetcode + kei
  // Date : May 28, 2021

  // For memoization
  // K: x, y coordinate, V: minimum number of steps to the x, y
  private Map<String, Integer> memo = new HashMap<>();

  public int minKnightMoves(int x, int y) {
    // Symmetry of the board
    return dfs(Math.abs(x), Math.abs(y));
  }

  private int dfs(int x, int y) {
    // We can use two variables as one key.
    String key = x + "," + y;
    if (memo.containsKey(key)) {
      return memo.get(key);
    }

    if (x + y == 0) {
      // Base case 1
      return 0;
    } else if (x + y == 2) {
      // Base case 2
      // We need to take care of this case because the last a few steps are
      // out of the first quadrant.
      return 2;
    } else {
      // We only need to focus on the first quadrant.
      int ret = 1 + Math.min( //
          // Symmetry of the move
          dfs(Math.abs(x - 1), Math.abs(y - 2)), //
          dfs(Math.abs(x - 2), Math.abs(y - 1)));

      // Memoization
      memo.put(key, ret);
      return ret;
    }
  }

  // For testing.
  @SuppressWarnings("unused")
  public static void main(String[] args) {
    MinimumKnightMoves solution = new MinimumKnightMoves();

    // Test arguments.
    // int num = 24;
    // int target = 2;
    // System.out.println(solution.getInt(num, target));

    System.out.println("\ndone.");
  }

}
