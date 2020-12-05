//
// Author:
// Date  : December 4, 2020

package leetcode;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class MaximumLengthOfConcatenatedStringWithUniqueCharacters {
  // fields and classes here.
  // private int count;

  public MaximumLengthOfConcatenatedStringWithUniqueCharacters() {
    // Initialization here.
    // this.count = 0;

  }

  // Author: misters + kei
  // Date : December 4, 2020
  private int res = 0;

  public int maxLength(List<String> arr) {
    if (arr == null || arr.size() == 0) {
      return 0;
    }
    bt(arr, "", 0);
    return res;
  }

  private void bt(List<String> arr, String pre, int start) {
    // NG! because we still need to check the length when start gets to arr.size(),
    // where pre just being added the last string.
    // if (start == arr.size()) {
    // return;
    // }

    if (isUniqueChars(pre)) {
      res = Math.max(res, pre.length());
    } else {
      return;
    }

    for (int i = start; i < arr.size(); i++) {
      // Pass in i + 1 because we only need to consider subsequence.
      bt(arr, pre + arr.get(i), i + 1);
    }
  }

  private boolean isUniqueChars(String s) {
    Set<Character> set = new HashSet<>();
    for (char c : s.toCharArray()) {
      if (set.contains(c)) {
        return false;
      }
      set.add(c);
    }
    return true;
  }

  // For testing.
  @SuppressWarnings("unused")
  public static void main(String[] args) {
    MaximumLengthOfConcatenatedStringWithUniqueCharacters solution = new MaximumLengthOfConcatenatedStringWithUniqueCharacters();

    // Test arguments.
    // int num = 24;
    // int target = 2;
    // solution.getInt(num, target);

  }

}
