//
// Author:
// Date : February 11, 2020

package leetcode;

public class PaintHouse {
  // fields and classes here.
  // private int count;

  public PaintHouse() {
    // Initialization here.
    // this.count = 0;

  }


  // 4. Dynamic Programming. Next one is better solution.
  // we say that this problem has an optimal substructure. This means that
  // the optimal cost for each subproblem is constructed from the optimal cost of
  // subproblems below it.
  // Problems that have optimal substructure can be solved with greedy algorithms.
  // If they also have overlapping subproblems, then they can be solved with
  // dynamic programming algorithms.
  //
  // We cannot paint two adjacent houses in the same color, which means
  // We need to consider at least two houses at the same time to find minimum total cost.
  // In the i-th row, we just need to look at the cumsum in the previous row.
  // dp[i][0] = dp[i] + min(dp[i - 1][1], dp[i - 1][2])
  //
  // O(N) time, O(1) space.
  // Author: hai_dee + kei (AC)
  // Date : February 11, 2020
  public int minCost(int[][] costs) {
    int len = costs.length;
    if (costs == null || len == 0) {
      return 0;
    }
    // corner len 1 => ok (skip the loop)

    for (int n = 0; n + 1 < costs.length; n++) {
      // Total cost of painting the n-th house red.
      costs[n + 1][0] += Math.min(costs[n][1], costs[n][2]);
      // Total cost of painting the n-th house green.
      costs[n + 1][1] += Math.min(costs[n][0], costs[n][2]);
      // Total cost of painting the n-th house blue.
      costs[n + 1][2] += Math.min(costs[n][0], costs[n][1]);
    }

    // Take minimum of the three in the last row.
    return Math.min(Math.min(costs[len - 1][0], costs[len - 1][1]), costs[len - 1][2]);
  }


  // Better space complexity.
  // We just need two rows to accumulate the sum.
  // O(N) time, O(1) space.
  // Author: bitbleach + hai_dee + kei (AC)
  // Date : February 13, 2020
  public int minCost2(int[][] costs) {
    if (costs == null || costs.length == 0) {
      return 0;
    }

    int[] currRow = costs[0];

    for (int n = 0; n + 1 < costs.length; n++) {

      int[] nextRow = costs[n + 1];

      // Total cost of painting the n-th house red.
      nextRow[0] += Math.min(currRow[1], currRow[2]);
      // Total cost of painting the n-th house green.
      nextRow[1] += Math.min(currRow[0], currRow[2]);
      // Total cost of painting the n-th house blue.
      nextRow[2] += Math.min(currRow[0], currRow[1]);

      currRow = nextRow;
    }

    return Math.min(Math.min(currRow[0], currRow[1]), currRow[2]);
  }



  // For testing.
  public static void main(String[] args) {
    PaintHouse solution = new PaintHouse();

    // Test arguments.
    // int num = 24;
    // int target = 2;
    // solution.getInt(num, target);



  }

}


