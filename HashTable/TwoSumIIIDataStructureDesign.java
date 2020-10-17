//
// Author:
// Date  : October ?, 2020

package leetcode;

import java.util.HashMap;
import java.util.Map;

public class TwoSumIIIDataStructureDesign {
  // fields and classes here.
  // private int count;

  // Author: leetcode + kei
  // Date : October ?, 2020
  private HashMap<Integer, Integer> counts;

  /** Initialize your data structure here. */
  public TwoSumIIIDataStructureDesign() {
    this.counts = new HashMap<Integer, Integer>();
  }

  /** Add the number to an internal data structure.. */
  public void add(int number) {
    this.counts.put(number, counts.getOrDefault(number, 0) + 1);
  }

  /** Find if there exists any pair of numbers which sum is equal to the value. */
  public boolean find(int value) {
    for (Map.Entry<Integer, Integer> entry : this.counts.entrySet()) {
      int complement = value - entry.getKey();
      if (complement != entry.getKey()) {
        if (this.counts.containsKey(complement)) {
          return true;
        }
      } else {
        if (entry.getValue() > 1) {
          return true;
        }
      }
    }
    return false;
  }

  // For testing.
  @SuppressWarnings("unused")
  public static void main(String[] args) {
    TwoSumIIIDataStructureDesign solution = new TwoSumIIIDataStructureDesign();

    // Test arguments.
    // int num = 24;
    // int target = 2;
    // solution.getInt(num, target);

  }

}
