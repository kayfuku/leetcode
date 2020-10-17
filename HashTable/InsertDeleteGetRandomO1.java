//
// Author:
// Date  : October 16, 2020

package leetcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class InsertDeleteGetRandomO1 {
  // fields and classes here.
  // private int count;

  public InsertDeleteGetRandomO1() {
    // Initialization here.
    // this.count = 0;

  }

  // Author: + kei
  // Date : October 16, 2020
  class RandomizedSet {
    // K: val, V: index
    Map<Integer, Integer> dict;
    List<Integer> list;
    Random rand = new Random();

    /** Initialize your data structure here. */
    public RandomizedSet() {
      dict = new HashMap();
      list = new ArrayList();
    }

    /**
     * Inserts a value to the set. Returns true if the set did not already contain
     * the specified element.
     */
    public boolean insert(int val) {
      if (dict.containsKey(val)) {
        return false;
      }
      // We can add index like this.
      dict.put(val, list.size());
      list.add(list.size(), val);
      return true;
    }

    /**
     * Removes a value from the set. Returns true if the set contained the specified
     * element. O(1) time.
     */
    public boolean remove(int val) {
      if (!dict.containsKey(val)) {
        return false;
      }

      // If you remove without moving the val to the last element, then shifting will
      // occur, which takes O(N) time.
      // Move the last element to the idx of val.
      int lastElement = list.get(list.size() - 1);
      int idx = dict.get(val);
      list.set(idx, lastElement);
      dict.put(lastElement, idx);

      // Delete the last element.
      list.remove(list.size() - 1);
      dict.remove(val);
      return true;
    }

    // Get a random element from the set.
    // You have to use list in order to be able to randomly pick up one element.
    // That's why you have to use a list, not a set.
    // Then, when you implement remove() that only takes O(1) time, you have to
    // use a hash table to do lookup in O(1) time.
    public int getRandom() {
      return list.get(rand.nextInt(list.size()));
    }
  }

  // For testing.
  @SuppressWarnings("unused")
  public static void main(String[] args) {
    InsertDeleteGetRandomO1 solution = new InsertDeleteGetRandomO1();

    // Test arguments.
    // int num = 24;
    // int target = 2;
    // solution.getInt(num, target);

  }

}
