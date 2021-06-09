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
  // I'm gonna use DFS to check if the night can move from (x, y) to the origin.
  //
  // The time complexity of the algorithm is proportional to the total number of
  // invocations of the recursive function.
  // O(|xy|) time and space because we use memoization based on the x, y
  // coordinate and in the worst case, there are |xy| cells between the target
  // and the origin.
  //
  // Author: leetcode + kei
  // Date : May 28, 2021

  // For memoization
  // K: x, y coordinate, V: minimum number of steps to the x, y
  private Map<String, Integer> memo = new HashMap<>();

  public int minKnightMoves(int x, int y) {
    // Because of the symmetry of the board, we only need to focus on the first
    // quadrant. Don't forget to take the absolute value.
    return dfs(Math.abs(x), Math.abs(y));
  }

  private int dfs(int x, int y) {
    // We can use two variables as one key.
    String key = x + "," + y;

    // Memoization
    if (memo.containsKey(key)) {
      return memo.get(key);
    }

    // if (x + y == 0) {
    if (x == 0 && y == 0) {
      // Base case 1
      return 0;

    } else if (x + y == 2) {
      // Base case 2
      // We need to take care of this case because there could be infinite loop
      // around the origin.
      return 2;

    } else {
      // Since the knight move and the board are symmetry,
      // we only need to focus on the first quadrant.
      int steps = 1 + Math.min( //
          // Also, we only need to consider two moves because of the symmetry of the
          // allowed move, which is one left two down, or two left one down.
          dfs(Math.abs(x - 1), Math.abs(y - 2)), //
          dfs(Math.abs(x - 2), Math.abs(y - 1)));

      // Memoization
      memo.put(key, steps);

      return steps;
    }
  }

  // Review

  // K: x y coordinate, V: steps
  Map<String, Integer> memoR = new HashMap<>();

  public int minKnightMovesR(int x, int y) {
    return dfsR(Math.abs(x), Math.abs(y));
  }

  int dfsR(int x, int y) {
    String key = "" + x + "," + y;

    int steps = 0;

    if (memoR.containsKey(key)) {
      return memoR.get(key);
    }

    if (x == 0 && y == 0) {
      return 0;
    } else if (x + y == 2) {
      return 2;
    } else {
      steps = 1 + Math.min( //
          dfsR(Math.abs(x - 2), Math.abs(y - 1)), //
          dfsR(Math.abs(x - 1), Math.abs(y - 2)));
      memoR.put(key, steps);
      return steps;
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
