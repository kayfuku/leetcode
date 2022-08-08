//
// Author:
// Date  : September 30, 2020

package leetcode;

import java.util.HashSet;
import java.util.Set;

public class FindDuplicateNumber {
  // fields and classes here.
  // private int count;

  public FindDuplicateNumber() {
    // Initialization here.
    // this.count = 0;

  }

  // 1. Set
  // O(N) time, O(N) space
  // Author: kei
  // Date : September 30, 2020
  public int findDuplicate(int[] nums) {
    Set<Integer> set = new HashSet<>();
    for (int n : nums) {
      if (set.contains(n)) {
        return n;
      }
      set.add(n);
    }

    return -1;
  }

  // 2. If Binary Search must be used (No need to review, too skewed)
  // O(logN) time, O(1) space (The sorting line ignored)
  // Author: kei
  // Date : September 30, 2020
  public int findDuplicate2(int[] nums) {
    int left = 1;
    int right = nums.length - 1;
    while (left <= right) {
      int mid = left + (right - left) / 2;
      int count = 0;
      for (int e : nums) {
        if (e <= mid) {
          count++;
        }
      }
      if (count <= mid) {
        // The ans will not be less than or equal to mid.
        // Search right.
        // The ans will be [mid + 1, right] inclusive at the later rounds.
        left = mid + 1;
      } else {
        // count > mid
        // The ans will be less than or equal to mid.
        // Search left.
        // The ans will be [left, mid] inclusive at the later rounds.
        right = mid - 1;
      }
    }

    // The ans is left after getting out of loop.
    return left;
  }

  // For testing.
  @SuppressWarnings("unused")
  public static void main(String[] args) {
    TwoSumII solution = new TwoSumII();

    // Test arguments.
    // int num = 24;
    // int target = 2;
    // solution.getInt(num, target);

  }

}
