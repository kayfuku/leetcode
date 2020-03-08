//
// Author:
// Date : July 30, 2019

package leetcode;

import java.util.HashMap;
import java.util.Map;

public class FruitIntoBaskets {
  // fields and classes here.
  // private int count;

  public FruitIntoBaskets() {
    // Initialization here.
    // this.count = 0;
  }


  // 2. Sliding Window. (Two Pointers)
  // To find longest continuous characters on a specific condition.
  // i: points to the beginning of the valid subarray.
  // j: points to the end of the valid subarray.
  // j moves forward as long as the subarray [i, j] is valid.
  // If it becomes invalid, then fix it by moving i forward.
  // O(N) time, where N is the input array length.
  // O(1) space because map will not go beyond 3.
  // Author: @awice + kei
  // Date : July 30, 2019
  public int totalFruit(int[] tree) {
    int max = 0, i = 0;
    // K: elem, V: count of elem
    Map<Integer, Integer> map = new HashMap<>();
    for (int j = 0; j < tree.length; j++) {
      // Count the elem.
      map.put(tree[j], map.getOrDefault(tree[j], 0) + 1);
      // We're only allowed to have two kinds of elems.
      while (map.size() >= 3) {
        // Invalid! Fix it.
        // While moving i forward, decrement the count of that elem and remove it
        // if count is 0. Keep iterating until map.size() == 2.
        map.put(tree[i], map.get(tree[i]) - 1);
        if (map.get(tree[i]) == 0) {
          map.remove(tree[i]);
        }
        i++;
      }

      // Put the valid list length in the max accumulator.
      // j - i + 1 is the length of a list between i and j inclusive.
      max = Math.max(max, j - i + 1);
    }

    return max;
  }



  // For testing.
  public static void main(String[] args) {
    FruitIntoBaskets solution = new FruitIntoBaskets();

    // Test arguments.
    // int num = 24;
    // int target = 2;
    // solution.getInt(num, target);



  }

}


