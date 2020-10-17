//
// Author:
// Date  : October 15, 2020

package leetcode;

import java.util.HashMap;
import java.util.Map;

public class FourSumII {
  // fields and classes here.
  // private int count;

  public FourSumII() {
    // Initialization here.
    // this.count = 0;

  }

  // Author: leetcode + kei
  // Date : October 15, 2020
  public int fourSumCount(int[] A, int[] B, int[] C, int[] D) {
    int count = 0;
    Map<Integer, Integer> map = new HashMap<>();
    for (int a : A) {
      for (int b : B) {
        map.put(a + b, map.getOrDefault(a + b, 0) + 1);
      }
    }
    for (int c : C) {
      for (int d : D) {
        count += map.getOrDefault(-(c + d), 0);
      }
    }

    return count;
  }

  // For testing.
  @SuppressWarnings("unused")
  public static void main(String[] args) {
    FourSum solution = new FourSum();

    // Test arguments.
    // int num = 24;
    // int target = 2;
    // solution.getInt(num, target);

  }

}
