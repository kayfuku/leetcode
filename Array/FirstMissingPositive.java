//
// Author:
// Date  : December 4, 2020

package leetcode;

import java.util.HashSet;
import java.util.Set;

public class FirstMissingPositive {
  // fields and classes here.
  // private int count;

  public FirstMissingPositive() {
    // Initialization here.
    // this.count = 0;

  }

  // O(N) time, O(N) space
  // Author: kei (AC)
  // Date : December 4, 2020
  public int firstMissingPositive(int[] nums) {
    if (nums == null || nums.length == 0) {
      return 1;
    }
    Set<Integer> set = new HashSet<>();
    for (int n : nums) {
      set.add(n);
    }
    int len = nums.length;
    for (int i = 1; i <= len; i++) {
      if (!set.contains(i)) {
        return i;
      }
    }

    return len + 1;
  }

  // For testing.
  @SuppressWarnings("unused")
  public static void main(String[] args) {
    FirstMissingPositive solution = new FirstMissingPositive();

    // Test arguments.
    // int num = 24;
    // int target = 2;
    // solution.getInt(num, target);

  }

}
