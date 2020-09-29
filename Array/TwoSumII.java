//
// Author:
// Date  : September 28, 2020

package leetcode;

public class TwoSumII {
  // fields and classes here.
  // private int count;

  public TwoSumII() {
    // Initialization here.
    // this.count = 0;

  }

  // We use two indexes, initially pointing to the first and last element
  // respectively. Compare the sum of these two elements with target. If the sum
  // is equal to target, we found the exactly only solution. If it is less than
  // target, we increase the smaller index by one. If it is greater than target,
  // we decrease the larger index by one. Move the indexes and repeat the
  // comparison until the solution is found.
  // O(N) time, O(1) space
  // Author: leetcode + kei
  // Date : September 28, 2020
  public int[] twoSum(int[] numbers, int target) {
    int left = 0, right = numbers.length - 1;
    while (left < right) {
      int sum = numbers[left] + numbers[right];
      if (sum == target) {
        // Convert for 1-indexed.
        return new int[] { left + 1, right + 1 };
      }
      if (sum < target) {
        left++;
      } else {
        right--;
      }
    }

    return new int[] { -1, -1 };
  }

  // For testing.
  @SuppressWarnings("unused")
  public static void main(String[] args) {
    Solution solution = new Solution();

    // Test arguments.
    // int num = 24;
    // int target = 2;
    // solution.getInt(num, target);

  }

}
