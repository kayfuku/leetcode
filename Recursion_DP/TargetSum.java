//
// Author:
// Date : January 8, 2020

package leetcode;

import java.util.HashMap;
import java.util.Map;

public class TargetSum {
  // fields and classes here.
  // private int count;

  public TargetSum() {
    // Initialization here.
    // this.count = 0;

  }

  // 1. Recursion. No need to review.
  // O(2^N) time, where N is the nums length.
  // O(N) space, The depth of the recursion tree can go up to n.
  // Author: kei (AC, but slow)
  // Date : January 8, 2020
  public int findTargetSumWays(int[] nums, int S) {
    if (nums == null || nums.length == 0) {
      return 0;
    }
    return findTargetSumWays(0, S, nums);
  }

  private int findTargetSumWays(int i, int remain, int[] nums) {
    if (i == nums.length) {
      if (remain == 0) {
        return 1;
      }
      return 0;
    }

    int countLeft = findTargetSumWays(i + 1, remain + nums[i], nums);
    int countRight = findTargetSumWays(i + 1, remain - nums[i], nums);

    return countLeft + countRight;
  }

  // 2. Memoization. No need to review.
  // Author: kei (Time Limit Exceeded, why??)
  // Date : January 8, 2020

  // index, remain, count
  Map<Integer, Map<Integer, Integer>> memo = new HashMap<>();

  public int findTargetSumWays2(int[] nums, int S) {
    if (nums == null || nums.length == 0) {
      return 0;
    }

    return findTargetSumWays2(0, S, nums);
  }

  private int findTargetSumWays2(int i, int remain, int[] nums) {
    if (i == nums.length) {
      if (remain == 0) {
        return 1;
      }
      return 0;
    }

    if (memo.containsKey(i) && memo.get(i).containsKey(remain)) {
      return memo.get(i).get(remain);
    }

    int countLeft = findTargetSumWays2(i + 1, remain + nums[i], nums);
    int countRight = findTargetSumWays2(i + 1, remain - nums[i], nums);
    int count = countLeft + countRight;

    Map<Integer, Integer> remainToCount = new HashMap<>();
    remainToCount.put(remain, count);
    memo.put(i, remainToCount);

    return count;
  }

  // 3. DP
  // Author: @mkn1921 + kei (AC)
  // Date : June 21, 2022
  public int findTargetSumWays4(int[] nums, int s) {
    Map<Integer, Integer> dp = new HashMap<>();
    dp.put(0, 1);
    for (int num : nums) {
      Map<Integer, Integer> dp2 = new HashMap<>();
      for (int tempSum : dp.keySet()) {
        int key1 = tempSum + num;
        dp2.put(key1, dp2.getOrDefault(key1, 0) + dp.get(tempSum));
        int key2 = tempSum - num;
        dp2.put(key2, dp2.getOrDefault(key2, 0) + dp.get(tempSum));
      }
      dp = dp2;
    }
    return dp.getOrDefault(s, 0);
  }

  // 3. Dynamic Programming
  // Author: @vinod23 + kei (AC)
  // Date : January 22, 2020
  public int findTargetSumWays3(int[] nums, int S) {
    if (S > 1000) {
      return 0;
    }
    // Since the sum can range from -1000 to +1000, we need to add an offset of 1000
    // to the sum indices (column number) to map all the sums obtained to positive
    // range only (0 ~
    // 2000).
    // dp holds the number of ways that the sum_i + nums[i] can occur upto i.
    int[] dp = new int[2001];
    dp[nums[0] + 1000] = 1;
    // Note that it is '+=', not just '=' because nums[0] could be 0 and there is
    // already 1 added
    // above in this case.
    dp[-nums[0] + 1000] += 1;

    // Traverse nums.
    for (int i = 1; i < nums.length; i++) {
      // Create another copy of array dp because we traverse previous dp and based on
      // that dp, we
      // count up in the sum ± nums[i] equivalent in 'next' array.
      int[] next = new int[2001];
      // Check all the counts in the previous array dp.
      for (int sum = -1000; sum <= 1000; sum++) {
        if (dp[sum + 1000] > 0) {
          // dp[sum + 1000] > 0 means at least there is a way to sum upto 'sum + 1000'.
          // Propagate the counts so far to the sum == sum ± nums[i] equivalent.
          next[sum + nums[i] + 1000] += dp[sum + 1000];
          next[sum - nums[i] + 1000] += dp[sum + 1000];
        }
      }

      // All info needed is in the 'next' array. This should be 'dp' array.
      dp = next;
    }

    return dp[S + 1000];
  }

  // For testing.
  public static void main(String[] args) {
    TargetSum solution = new TargetSum();

    // Test arguments.
    // int num = 24;
    // int target = 2;
    // solution.getInt(num, target);

    // int[] nums = new int[] { 5, 19, 48, 39, 14, 5, 39, 32, 5, 46, 11, 30, 1, 20,
    // 36, 15, 21, 6,
    // 15, 2 };
    int[] nums = new int[] { 5, 19, 48 };
    int S = 39;
    System.out.println(solution.findTargetSumWays2(nums, S));

  }

}
