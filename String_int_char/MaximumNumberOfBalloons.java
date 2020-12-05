//
// Author:
// Date  : December 4, 2020

package leetcode;

public class MaximumNumberOfBalloons {
  // fields and classes here.
  // private int count;

  public MaximumNumberOfBalloons() {
    // Initialization here.
    // this.count = 0;

  }

  // Division is used sometimes when you want to count something.
  // Author: + kei
  // Date : December 4, 2020
  public int maxNumberOfBalloons(String text) {
    int[] countsText = new int[26];
    for (char c : text.toCharArray()) {
      countsText[c - 'a']++;
    }
    String ins = "balloon";
    int[] countsIns = new int[26];
    for (char c : ins.toCharArray()) {
      countsIns[c - 'a']++;
    }

    int ret = Integer.MAX_VALUE;
    for (char c : ins.toCharArray()) {
      // Use division.
      ret = Math.min(ret, countsText[c - 'a'] / countsIns[c - 'a']);
    }

    return ret;
  }

  // For testing.
  @SuppressWarnings("unused")
  public static void main(String[] args) {
    MaximumNumberOfBalloons solution = new MaximumNumberOfBalloons();

    // Test arguments.
    // int num = 24;
    // int target = 2;
    // solution.getInt(num, target);

  }

}
