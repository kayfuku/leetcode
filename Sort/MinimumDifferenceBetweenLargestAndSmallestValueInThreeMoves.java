package leetcode;

import java.util.Arrays;

public class MinimumDifferenceBetweenLargestAndSmallestValueInThreeMoves {
  // fields and classes here.
  // private int count;

  public MinimumDifferenceBetweenLargestAndSmallestValueInThreeMoves() {
    // Initialization here.
    // this.count = 0;

  }

  // Author: lee215 + kei
  // Date : September 15, 2021
  public int minDifference(int[] A) {
    int n = A.length, res = Integer.MAX_VALUE;
    if (n <= 4) {
      return 0;
    }
    Arrays.sort(A);
    for (int i = 0; i < 4; i++) {
      res = Math.min(res, A[n - 4 + i] - A[i]);
    }
    return res;
  }

  // For testing.
  @SuppressWarnings("unused")
  public static void main(String[] args) {
    MinimumDifferenceBetweenLargestAndSmallestValueInThreeMoves solution = new MinimumDifferenceBetweenLargestAndSmallestValueInThreeMoves();

    // Test arguments.
    // int num = 24;
    // int target = 2;
    // System.out.println(solution.getInt(num, target));

    System.out.println("\ndone.");
  }

}
