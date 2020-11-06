//
// Author:
// Date : July 10, 2019

package leetcode;

import java.util.Arrays;

public class MinimumSizeSubarraySum {
  // fields and classes here.
  // private int count;

  public MinimumSizeSubarraySum() {
    // Initialization here.
    // this.count = 0;
  }

  // 4. Sliding Window. Accepted.
  // O(N) time, O(1) space.
  // Author: @abhinavbansal0 + kei
  // Date : July 10, 2019, November 5, 2020
  public int minSubArrayLen(int s, int[] nums) {
    if (nums == null || nums.length == 0) {
      return 0;
    }

    Integer ans = null;
    int sum = 0;
    int left = 0;

    for (int right = 0; right < nums.length; right++) {
      sum += nums[right];
      // Check to see how we can minimize the length.
      // Note that if nums has 0s and s is 0, which is not the case for this problem,
      // then this is not going to work.
      while (sum >= s) {
        // Now it satisfies the condition, keep that length.
        int len = right - left + 1;
        // Update the min.
        ans = (ans == null) ? len : Math.min(ans, len);
        // Reduce the length further.
        sum -= nums[left];
        left++;
      }
    }

    return (ans == null) ? 0 : ans;
  }

  // Original solution.
  // I think this solution has a problem with such input as,
  // s = Integer.MAX_VALUE
  // nums.length = Integer.MAX_VALUE
  // All elements are 1.
  // It returns 0 for that input.
  public int minSubArrayLenO(int s, int[] nums) {
    Integer ans = Integer.MAX_VALUE;
    // Integer ans = 100;
    int sum = 0;
    int left = 0;

    for (int i = 0; i < nums.length; i++) {
      sum += nums[i];
      while (sum >= s) {
        ans = Math.min(ans, i - left + 1);
        sum -= nums[left];
        left++;
      }
    }

    // return (ans != 100) ? ans : 0;
    return (ans != Integer.MAX_VALUE) ? ans : 0;
  }

  // For testing.
  public static void main(String[] args) {
    MinimumSizeSubarraySum solution = new MinimumSizeSubarraySum();

    // Test arguments.
    // int num = 24;
    // int target = 2;
    // solution.getInt(num, target);

    int[] nums = new int[] { 2, 3, 1, 2, 4, 3 };
    int s = 7;
    System.out.println(solution.minSubArrayLen(s, nums)); // 2

    int[] numsLooong = new int[100];
    // int[] numsLooong = new int[Integer.MAX_VALUE];
    Arrays.fill(numsLooong, 1);
    int sBiiig = 100;
    // int sBiiig = Integer.MAX_VALUE;
    System.out.println(solution.minSubArrayLen(sBiiig, numsLooong)); // 100

  }

}
