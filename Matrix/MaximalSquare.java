package leetcode;

public class MaximalSquare {
  // fields and classes here.
  // private int count;

  public MaximalSquare() {
    // Initialization here.
    // this.count = 0;

  }

  // 1. Dynamic Programming
  // O(MN) time and space.
  // Author: leetcode + kei
  // Date : February 3, 2021
  public int maximalSquare(char[][] matrix) {
    if (matrix == null || matrix.length == 0) {
      return 0;
    }
    int R = matrix.length;
    int C = matrix[0].length;

    int[][] dp = new int[R + 1][C + 1];
    int maxsqlen = 0;
    for (int i = 1; i <= R; i++) {
      for (int j = 1; j <= C; j++) {
        if (matrix[i - 1][j - 1] == '1') {
          // Take a min amoung left, up, and upper left.
          dp[i][j] = Math.min(Math.min(dp[i][j - 1], dp[i - 1][j]), dp[i - 1][j - 1]) + 1;
          maxsqlen = Math.max(maxsqlen, dp[i][j]);
        }
      }
    }
    // Looking at the restriction of m and n, 1 <= m, n <= 300,
    // maxsqlen does not have to be type long.
    return maxsqlen * maxsqlen;
  }

  // 2. Well, that's good, but no need to implement in the interview.
  // Just let the interview know that you know a better solution like this.
  // Author: leetcode + kei
  // Date : February 3, 2021
  public int maximalSquare2(char[][] matrix) {
    if (matrix == null || matrix.length == 0) {
      return 0;
    }
    int R = matrix.length;
    int C = matrix[0].length;

    int[] dp = new int[C + 1];
    int maxsqlen = 0, prev = 0;
    for (int i = 1; i <= R; i++) {
      for (int j = 1; j <= C; j++) {
        int temp = dp[j];
        if (matrix[i - 1][j - 1] == '1') {
          // |a|b|
          // |c|d|
          // a: prev, b: dp[j] (right hand), c: dp[j - 1], d: dp[j] (left hand)
          dp[j] = Math.min(Math.min(dp[j - 1], prev), dp[j]) + 1;
          maxsqlen = Math.max(maxsqlen, dp[j]);
        } else {
          dp[j] = 0;
        }
        prev = temp;
      }
    }
    return maxsqlen * maxsqlen;
  }

  // For testing.
  @SuppressWarnings("unused")
  public static void main(String[] args) {
    MaximalSquare solution = new MaximalSquare();

    // Test arguments.
    // int num = 24;
    // int target = 2;
    // System.out.println(solution.getInt(num, target));

    System.out.println("\ndone.");
  }

}
