package leetcode;

import java.util.Arrays;

public class MinimizeMaximumPairSumInArray {
  // fields and classes here.
  // private int count;

  public MinimizeMaximumPairSumInArray() {
    // Initialization here.
    // this.count = 0;

  }

  // Author: lee215 + kei
  // Date : December 15, 2021
  public int minPairSum(int[] A) {
    Arrays.sort(A);
    int res = 0, n = A.length;
    for (int i = 0; i < n / 2; ++i)
      res = Math.max(res, A[i] + A[n - i - 1]);
    return res;
  }

  // Author: kei (AC)
  // Date : December 15, 2021
  public int minPairSum2(int[] nums) {
    Arrays.sort(nums);
    int i = 0, j = nums.length - 1;
    int max = 0;
    while (i < j) {
      max = Math.max(max, nums[i] + nums[j]);
      i++;
      j--;
    }

    return max;
  }

  // For testing.
  @SuppressWarnings("unused")
  public static void main(String[] args) {
    MinimizeMaximumPairSumInArray solution = new MinimizeMaximumPairSumInArray();

    // Test arguments.
    // int num = 24;
    // int target = 2;
    // System.out.println(solution.getInt(num, target));

    System.out.println("\ndone.");
  }

}
