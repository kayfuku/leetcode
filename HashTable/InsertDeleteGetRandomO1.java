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

  // To get random data, we need an array to store data.
  // To remove data in O(1) time, we need to copy the last element
  // to the element to be deleted, then delete the last element.
  // Since we first need to know the index of the element to be deleted,
  // we need a map to keep track of the indices of them.
  //
  // Author: leetcode + kei
  // Date : October 16, 2020
  class RandomizedSet {

    List<Integer> list;
    // K: val, V: index
    Map<Integer, Integer> map;
    Random rand = new Random();

    /** Initialize your data structure here. */
    public RandomizedSet() {
      list = new ArrayList<Integer>();
      map = new HashMap<Integer, Integer>();
    }

    /**
     * Inserts a value to the set. Returns true if the set did not already contain
     * the specified element.
     * O(1) time.
     */
    public boolean insert(int val) {
      if (map.containsKey(val)) {
        return false;
      }

      // Add it at the tail in the array.
      list.add(val);
      // We need to keep track of indices of vals using map in order to delete
      // them in O(1) time.
      // We need to be careful about putting the index in the map because the
      // size changes once adding it.
      // list.size() - 1 indicates the last index of the array.
      map.put(val, list.size() - 1);

      return true;
    }

    /**
     * Removes a value from the set. Returns true if the set contained the specified
     * element. O(1) time.
     */
    public boolean remove(int val) {
      if (!map.containsKey(val)) {
        return false;
      }

      // Copy the last element to the val to be deleted in the list and then
      // delete the last element later.
      // If you remove without moving the val to the last element, then shifting will
      // occur, which takes O(N) time.
      int lastElement = list.get(list.size() - 1);
      // This is exactly why we need a map for this problem. When we know the element
      // to be deleted but don't know the index, we cannot copy over to it in O(1)
      // time.
      int idx = map.get(val);
      list.set(idx, lastElement);
      // Update the index of the copied element.
      map.put(lastElement, idx);

      // Delete the last element.
      list.remove(list.size() - 1);
      map.remove(val);
      return true;
    }

    // Get a random element from the set.
    // You have to use array in order to be able to randomly pick up one element.
    // That's why you have to use an array, not a set.
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
