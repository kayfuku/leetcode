//
// Author:
// Date  : October 31, 2020

package leetcode;

import java.util.Arrays;

public class ArrayPartitionI {
  // fields and classes here.
  // private int count;

  public ArrayPartitionI() {
    // Initialization here.
    // this.count = 0;

  }

  // O(N) time. O(N) space.
  // Author: leetcode + kei
  // Date : October 31, 2020
  public int arrayPairSum(int[] nums) {
    Arrays.sort(nums);
    int sum = 0;
    for (int i = 0; i < nums.length; i += 2) {
      sum += nums[i];
    }
    return sum;
  }

  // O(N) time. O(N) space.
  // Author: leetcode + kei
  // Date : October 31, 2020
  public int arrayPairSum2(int[] nums) {
    int[] arr = new int[20001];
    int lim = 10000;
    // Count.
    for (int num : nums) {
      arr[num + lim]++;
    }
    int d = 0, sum = 0;
    for (int i = -10000; i <= 10000; i++) {
      sum += (arr[i + lim] + 1 - d) / 2 * i;
      d = (2 + arr[i + lim] - d) % 2;
    }
    return sum;
  }

  // For testing.
  @SuppressWarnings("unused")
  public static void main(String[] args) {
    ArrayPartitionI solution = new ArrayPartitionI();

    // Test arguments.
    // int num = 24;
    // int target = 2;
    // solution.getInt(num, target);

  }

}
