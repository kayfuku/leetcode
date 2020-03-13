//
// Author:
// Date : March 13, 2020

package leetcode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class TimeBasedKeyValueStore {
  // fields and classes here.
  // private int count;


  // 1. HashMap + Binary Search
  // Author: @awice + kei
  // Date : March 13, 2020

  // K: key, V: list of Pair(K: timestamp, V: value)
  Map<String, List<PairGen<Integer, String>>> M;

  public TimeBasedKeyValueStore() {
    M = new HashMap<>();
  }

  // O(1) time
  public void set(String key, String value, int timestamp) {
    if (!M.containsKey(key)) {
      M.put(key, new ArrayList<PairGen<Integer, String>>());
    }

    M.get(key).add(new PairGen<>(timestamp, value));
  }

  // According to Note 3, the timestamps for all TimeMap.set operations are strictly increasing.
  // => You can use binary search effectively.
  // O(logN) time
  public String get(String key, int timestamp) {
    if (!M.containsKey(key)) {
      return "";
    }

    List<PairGen<Integer, String>> A = M.get(key);
    int i = Collections.binarySearch(A, new PairGen<Integer, String>(timestamp, ""),
        // The list is sorted by the key of PairGen (timestamp) in ascending order in advance.
        (a, b) -> Integer.compare(a.getKey(), b.getKey()));

    if (i >= 0) {
      // Key found.
      return A.get(i).getValue();
    }
    if (i == -1) {
      // Key not found. The key is smaller than any other element in the list.
      return "";
    }
    // Key not found. -(i + 1) is the insertion index, which is defined as the point at which
    // the key would be inserted into the list: the index of the first element greater than the key.
    return A.get(-(i + 1) - 1).getValue();
  }


  // Your TimeMap object will be instantiated and called as such:
  // TimeMap obj = new TimeMap();
  // obj.set(key,value,timestamp);
  // String param_2 = obj.get(key,timestamp);


  // 2. TreeMap
  // Author: @awice + kei
  // Date : March 13, 2020
  class TimeMap {
    // K: key, V: list of Pair(K: timestamp, V: value)
    Map<String, TreeMap<Integer, String>> M;

    public TimeMap() {
      M = new HashMap<>();
    }

    // O(logN) time
    public void set(String key, String value, int timestamp) {
      if (!M.containsKey(key)) {
        M.put(key, new TreeMap<>());
      }

      M.get(key).put(timestamp, value);
    }

    // O(logN) time
    public String get(String key, int timestamp) {
      if (!M.containsKey(key)) {
        return "";
      }

      TreeMap<Integer, String> tree = M.get(key);
      // floorKey() returns the greatest key less than or equal to the given key,
      // or null if there is no such key.
      Integer t = tree.floorKey(timestamp);
      return (t != null) ? tree.get(t) : "";
    }
  }



  // For testing.
  @SuppressWarnings("unused")
  public static void main(String[] args) {
    TimeBasedKeyValueStore solution = new TimeBasedKeyValueStore();

    // Test arguments.
    // int num = 24;
    // int target = 2;
    // solution.getInt(num, target);



  }

}


