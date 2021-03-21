package leetcode;

public class MaximumAscendingSubarraySum {
  // fields and classes here.
  // private int count;

  public MaximumAscendingSubarraySum() {
    // Initialization here.
    // this.count = 0;

  }

  // Author: A_Le_K + kei (AC)
  // Date : March 20, 2021
  public int maxAscendingSum(int[] nums) {
    int max = nums[0];
    int sum = max;
    for (int i = 1; i < nums.length; i++) {
      if (nums[i - 1] < nums[i]) {
        sum += nums[i];
      } else {
        sum = nums[i];
      }
      max = Math.max(max, sum);
    }

    return max;
  }

  // For testing.
  @SuppressWarnings("unused")
  public static void main(String[] args) {
    MaximumAscendingSubarraySum solution = new MaximumAscendingSubarraySum();

    // Test arguments.
    // int num = 24;
    // int target = 2;
    // System.out.println(solution.getInt(num, target));

    System.out.println("\ndone.");
  }

}
