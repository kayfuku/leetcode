package leetcode;

public class CountSquareSubmatricesWithAllOnes {
  // fields and classes here.
  // private int count;

  public CountSquareSubmatricesWithAllOnes() {
    // Initialization here.
    // this.count = 0;

  }

  // Author: kei (AC)
  // Date : October 4, 2021
  public int countSquares(int[][] matrix) {
    if (matrix == null || matrix.length == 0) {
      return 0;
    }
    int R = matrix.length;
    int C = matrix[0].length;

    int count = 0;
    int[][] dp = new int[R + 1][C + 1];
    for (int r = 1; r < dp.length; r++) {
      for (int c = 1; c < dp[0].length; c++) {
        if (matrix[r - 1][c - 1] == 0) {
          continue;
        }
        dp[r][c] = Math.min(Math.min(dp[r - 1][c], dp[r][c - 1]), dp[r - 1][c - 1]) + 1;
        count += dp[r][c];
      }
    }

    return count;
  }

  // For testing.
  @SuppressWarnings("unused")
  public static void main(String[] args) {
    CountSquareSubmatricesWithAllOnes solution = new CountSquareSubmatricesWithAllOnes();

    // Test arguments.
    // int num = 24;
    // int target = 2;
    // System.out.println(solution.getInt(num, target));
    int[][] matrix = { { 0, 1, 1, 1 }, { 1, 1, 1, 1 }, { 0, 1, 1, 1 } };
    System.out.println(solution.countSquares(matrix));

    System.out.println("\ndone.");
  }

}
