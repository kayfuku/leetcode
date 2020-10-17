//
// Author:
// Date  : October 15, 2020

package leetcode;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class JewelsAndStones {
  // fields and classes here.
  // private int count;

  public JewelsAndStones() {
    // Initialization here.
    // this.count = 0;

  }

  // O(M + N) time, where M is the length of J and N is the length of S.
  // O(M) space.
  // Author: kei
  // Date : October 15, 2020
  public int numJewelsInStones(String J, String S) {
    Set<Character> setJ = new HashSet<>();
    for (char c : J.toCharArray()) {
      setJ.add(c);
    }
    int count = 0;
    for (char c : S.toCharArray()) {
      if (setJ.contains(c)) {
        count++;
      }
    }

    return count;
  }

  // For testing.
  @SuppressWarnings("unused")
  public static void main(String[] args) {
    JewelsAndStones solution = new JewelsAndStones();

    // Test arguments.
    // int num = 24;
    // int target = 2;
    // solution.getInt(num, target);

  }

}
