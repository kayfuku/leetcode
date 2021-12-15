package leetcode;

import java.util.HashMap;
import java.util.Map;

public class LongestArithmeticSubsequenceOfGivenDifference {
  // fields and classes here.
  // private int count;

  public LongestArithmeticSubsequenceOfGivenDifference() {
    // Initialization here.
    // this.count = 0;

  }

  // Author: evil_zone321 + kei
  // Date : December 14, 2021
  public int longestSubsequence(int[] arr, int difference) {
    // K: num, V: longest length of subsequence that uses this num so far
    Map<Integer, Integer> dp = new HashMap<>();
    for (int i = 0; i < arr.length; i++) {
      dp.put(arr[i], dp.getOrDefault(arr[i] - difference, 0) + 1);
    }
    int longest = 0;
    for (int num : dp.values()) {
      longest = Math.max(longest, num);
    }
    return longest;
  }

  // For testing.
  @SuppressWarnings("unused")
  public static void main(String[] args) {
    LongestArithmeticSubsequenceOfGivenDifference solution = new LongestArithmeticSubsequenceOfGivenDifference();

    // Test arguments.
    // int num = 24;
    // int target = 2;
    // System.out.println(solution.getInt(num, target));

    System.out.println("\ndone.");
  }

}
