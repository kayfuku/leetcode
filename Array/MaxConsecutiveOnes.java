//
// Author:
// Date  : November 5, 2020

package leetcode;

public class MaxConsecutiveOnes {
  // fields and classes here.
  // private int count;

  public MaxConsecutiveOnes() {
    // Initialization here.
    // this.count = 0;

  }

  // O(N) time, O(1) space.
  // Author: leetcode + kei
  // Date : November 5, 2020
  public int findMaxConsecutiveOnes(int[] nums) {
    int count = 0;
    int maxCount = 0;
    for (int i = 0; i < nums.length; i++) {
      if (nums[i] == 1) {
        // Increment the count of 1's by one.
        count++;
      } else {
        // Find the maximum till now.
        maxCount = Math.max(maxCount, count);
        // Reset count of 1s.
        count = 0;
      }
    }
    // Make sure to check if the last one is the max.
    return Math.max(maxCount, count);
  }

  // For testing.
  @SuppressWarnings("unused")
  public static void main(String[] args) {
    MaxConsecutiveOnes solution = new MaxConsecutiveOnes();

    // Test arguments.
    // int num = 24;
    // int target = 2;
    // solution.getInt(num, target);

  }

}
