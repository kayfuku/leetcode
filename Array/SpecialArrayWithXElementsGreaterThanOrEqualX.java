//
// Author:
// Date  : October ?, 2020

package leetcode;

import java.util.Arrays;

import googlecodejam.A;

public class SpecialArrayWithXElementsGreaterThanOrEqualX {
  // fields and classes here.
  // private int count;

  public SpecialArrayWithXElementsGreaterThanOrEqualX() {
    // Initialization here.
    // this.count = 0;

  }

  // 1.
  // O(N^2) time, O(1) space
  // Author: kei (AC)
  // Date : October 3, 2020
  public int specialArray(int[] nums) {
    for (int i = 1; i <= nums.length; i++) {
      int count = 0;
      for (int n : nums) {
        if (n >= i) {
          count++;
        }
      }
      if (count == i) {
        return i;
      }
    }

    return -1;
  }

  // 2. Counting sort
  // O(N) time, O(N) space
  // Author: LayCurse + kei
  // Date : October 4, 2020
  public int specialArray2(int[] nums) {
    int i;
    int[] cnt = new int[1001];
    for (i = 0; i < nums.length; i++) {
      cnt[nums[i]]++;
    }
    for (i = cnt.length - 1; i >= 1; i--) {
      cnt[i - 1] += cnt[i];
    }
    for (i = 0; i < 1001; i++) {
      if (cnt[i] == i) {
        return i;
      }
    }

    return -1;
  }

  // For testing.
  @SuppressWarnings("unused")
  public static void main(String[] args) {
    SpecialArrayWithXElementsGreaterThanOrEqualX solution = new SpecialArrayWithXElementsGreaterThanOrEqualX();

    // Test arguments.
    // int num = 24;
    // int target = 2;
    // solution.getInt(num, target);

  }

}
