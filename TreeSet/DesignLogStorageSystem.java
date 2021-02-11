//
// Author:
// Date : February 13, 2020

package leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class DesignLogStorageSystem {
  // fields and classes here.
  // private int count;

  // Using TreeMap
  //
  // Timestamp is a string that has the following format:
  // Year:Month:Day:Hour:Minute:Second,
  // for example, 2017:01:01:23:59:59. All domains are zero-padded decimal
  // numbers.
  //
  // Author: dreamchase + kei
  // Date : February 9, 2021
  public class LogSystem {

    private String min, max;
    // K: granularity, V: index of the end of the granularity
    private HashMap<String, Integer> map;
    // K: timestamp, V: a list of ids
    // We can use TreeMap.subMap() to retrieve in O(logN) time.
    // The reason we can directly put the string timestamp in the tree map is
    // because they are zero-padded decimal numbers. Otherwise, string numbers
    // are weird when comparing, for example, "33" < "4", but "33" > "04"
    private TreeMap<String, LinkedList<Integer>> logs;

    public LogSystem() {
      min = "2000:01:01:00:00:00";
      max = "2017:12:31:23:59:59";
      map = new HashMap<>();
      map.put("Year", 4);
      map.put("Month", 7);
      map.put("Day", 10);
      map.put("Hour", 13);
      map.put("Minute", 16);
      map.put("Second", 19);
      logs = new TreeMap<>();
    }

    public void put(int id, String timestamp) {
      if (!logs.containsKey(timestamp)) {
        logs.put(timestamp, new LinkedList<>());
      }
      logs.get(timestamp).add(id);
    }

    public List<Integer> retrieve(String s, String e, String gra) {
      // This is great! The patter of replacing a part of a string with another
      // substring.
      int index = map.get(gra);
      String start = s.substring(0, index) + min.substring(index);
      String end = e.substring(0, index) + max.substring(index);

      LinkedList<Integer> ans = new LinkedList<>();
      for (Map.Entry<String, LinkedList<Integer>> entry : //
      logs.subMap(start, true, end, true).entrySet()) {
        ans.addAll(entry.getValue());
      }
      return ans;
    }
  }

  // Your LogSystem object will be instantiated and called as such:
  //
  // LogSystem obj = new LogSystem();
  // obj.put(id, timestamp);
  // List<Integer> param_2 = obj.retrieve(start, end, granularity);

  // For testing.
  @SuppressWarnings("unused")
  public static void main(String[] args) {
    DesignLogStorageSystem solution = new DesignLogStorageSystem();

    // Test arguments.
    // int num = 24;
    // int target = 2;
    // solution.getInt(num, target);

  }

}
