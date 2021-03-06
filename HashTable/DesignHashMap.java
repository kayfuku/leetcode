package leetcode;

import java.util.ArrayList;
import java.util.List;

class DesignHashMap {

  // Author: leetcode + kei
  // Date : Feburuary 10, 2021

  // Amount of buckets
  private final int MAX_LEN = 100000;
  // Collision handling using an array of lists
  private List<PairGen<Integer, Integer>>[] map;

  /** Initialize your data structure here. */
  @SuppressWarnings("unchecked")
  public DesignHashMap() {
    map = (List<PairGen<Integer, Integer>>[]) new ArrayList[MAX_LEN];
  }

  /** Returns the corresponding bucket index. */
  private int getIndex(int key) {
    return key % MAX_LEN;
  }

  /**
   * Search the key in a specific bucket. Returns -1 if the key does not existed.
   */
  private int getPos(int key, int index) {
    // Each bucket contains a list.
    List<PairGen<Integer, Integer>> collisionList = map[index];
    if (collisionList == null) {
      // there is no list in this bucket.
      return -1;
    }
    // Iterate all the elements in the bucket to find the target key.
    for (int i = 0; i < collisionList.size(); i++) {
      if (collisionList.get(i).getKey() == key) {
        return i;
      }
    }
    return -1;
  }

  /**
   * Returns the value to which the specified key is mapped, or -1 if this map
   * contains no mapping for the key
   */
  public int get(int key) {
    int index = getIndex(key);
    int pos = getPos(key, index);
    if (pos < 0) {
      return -1;
    } else {
      return map[index].get(pos).getValue();
    }
  }

  /** value will always be positive. */
  public void put(int key, int value) {
    // Get the bucket index.
    int index = getIndex(key);
    // Check to see if there is a list in the bucket and the key in the list.
    int pos = getPos(key, index);
    if (pos < 0) {
      // key does not exist. Add new (key, value) pair to the tail of the list.
      if (map[index] == null) {
        map[index] = new ArrayList<>();
      }
      map[index].add(new PairGen<>(key, value));
    } else {
      // key exists.
      // Update the value at the index.
      // Note that this is ArrayList.set(), NOT add(index, elem) because add() inserts
      // the elem in that index and the other elems are shifted to the right, which is
      // not what we want here.
      map[index].set(pos, new PairGen<>(key, value));
    }
  }

  /**
   * Removes the mapping of the specified value key if this map contains a mapping
   * for the key.
   */
  public void remove(int key) {
    int index = getIndex(key);
    int pos = getPos(key, index);
    if (pos >= 0) {
      // O(N).
      // ArrayList.remove(index) removes the elem in that pos and shifts any
      // subsequent elements to the left, which takes O(N) time. To avoid this,
      // first, swap the element which we want to remove with the last element in the
      // bucket. Then remove the last element. This way, we successfully remove the
      // element in O(1) time.
      map[index].remove(pos);
    }
  }

  // Your MyHashMap object will be instantiated and called as such:
  // MyHashMap obj = new MyHashMap();
  // obj.put(key,value);
  // int param_2 = obj.get(key);
  // obj.remove(key);

  // For testing.
  @SuppressWarnings("unused")
  public static void main(String[] args) {
    DesignHashMap solution = new DesignHashMap();

    // Test arguments.
    // int num = 24;
    // int target = 2;
    // solution.getInt(num, target);

  }

}
