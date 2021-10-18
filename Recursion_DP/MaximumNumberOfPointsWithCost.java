package leetcode;

public class MaximumNumberOfPointsWithCost {
  // fields and classes here.
  // private int count;

  public MaximumNumberOfPointsWithCost() {
    // Initialization here.
    // this.count = 0;

  }

  // Author: + kei
  // Date : October 13, 2021
  public long maxPoints(int[][] points) {
    int m = points.length, n = points[0].length;
    long[] dp = new long[n];
    for (int i = 0; i < m; i++) {
      for (int j = 0; j < n; j++) {
        dp[j] += points[i][j];
      }
      for (int j = 1; j < n; j++) {
        dp[j] = Math.max(dp[j], dp[j - 1] - 1);
      }
      for (int j = n - 2; j >= 0; j--) {
        dp[j] = Math.max(dp[j], dp[j + 1] - 1);
      }
    }
    long ans = 0;
    for (int i = 0; i < n; i++) {
      ans = Math.max(ans, dp[i]);
    }
    return ans;
  }

  // // NG!
  // // Author: + kei
  // // Date : October 13, 2021
  // public long maxPointsNG(int[][] points) {
  // int max = 0, maxCol = 0;
  // for (int c = 0; c < points[0].length; c++) {
  // if (points[0][c] > max) {
  // max = points[0][c];
  // maxCol = c;
  // }
  // }
  // int prevCol = maxCol;
  // long res = max;
  // for (int r = 1; r < points.length; r++) {
  // max = 0;
  // maxCol = 0;
  // for (int c = 0; c < points[0].length; c++) {
  // if (points[r][c] - Math.abs(prevCol - c) > max) {
  // max = points[r][c] - Math.abs(prevCol - c);
  // maxCol = c;
  // }
  // }
  // res += max;
  // prevCol = maxCol;
  // }

  // return res;
  // }

  // For testing.
  @SuppressWarnings("unused")
  public static void main(String[] args) {
    MaximumNumberOfPointsWithCost solution = new MaximumNumberOfPointsWithCost();

    // Test arguments.
    // int num = 24;
    // int target = 2;
    // System.out.println(solution.getInt(num, target));
    int[][] points = { { 1, 5 }, { 2, 3 }, { 4, 2 } };
    System.out.println(solution.maxPoints(points));

    System.out.println("\ndone.");
  }

}
