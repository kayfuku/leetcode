//
// Author:
// Date : February 13, 2020

package leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.TreeMap;

public class DesignLogStorageSystem {
  // fields and classes here.
  // private int count;


  // Approach 1
  // Timestamp is a string that has the following format: Year:Month:Day:Hour:Minute:Second,
  // for example, 2017:01:01:23:59:59. All domains are zero-padded decimal numbers.

  // Author: @vinod23 + kei
  // Date : February 13, 2020

  // [timestamp, id]
  ArrayList<long[]> list;

  public DesignLogStorageSystem() {
    list = new ArrayList<>();
  }

  // O(1) time
  public void put(int id, String timestamp) {
    // Convert an array of String to an array of int.
    int[] st = Arrays.stream(timestamp.split(":")).mapToInt(Integer::parseInt).toArray();
    list.add(new long[] {convert(st), id});
  }

  // Convert the given timestamp into a number. This can help because
  // retrieval of Logs lying within a current range can be very easily done
  // if the range to be used can be represented in the form of a single number.
  public long convert(int[] st) {
    // month
    st[1] = st[1] - (st[1] == 0 ? 0 : 1);
    // day
    st[2] = st[2] - (st[2] == 0 ? 0 : 1);
    // Convert it to seconds. It's good enough converting like this. (A month has 31 days)
    // '-1999L' to avoid overflow.
    return (st[0] - 1999L) * (31 * 12) * 24 * 60 * 60 + st[1] * 31 * 24 * 60 * 60
        + st[2] * 24 * 60 * 60 + st[3] * 60 * 60 + st[4] * 60 + st[5];
  }

  // O(N) time
  // ex. retrieve("2016:01:01:01:01:01","2017:01:01:23:00:00","Year");
  public List<Integer> retrieve(String s, String e, String gra) {
    ArrayList<Integer> res = new ArrayList<>();
    long start = granularity(s, gra, false);
    long end = granularity(e, gra, true);
    for (int i = 0; i < list.size(); i++) {
      // We can check like this because we converted timestamp before storing it.
      if (list.get(i)[0] >= start && list.get(i)[0] < end) {
        res.add((int) list.get(i)[1]);
      }
    }

    return res;
  }

  public long granularity(String s, String gra, boolean end) {
    HashMap<String, Integer> h = new HashMap<>();
    h.put("Year", 0);
    h.put("Month", 1);
    h.put("Day", 2);
    h.put("Hour", 3);
    h.put("Minute", 4);
    h.put("Second", 5);
    String[] res = new String[] {"1999", "00", "00", "00", "00", "00"};
    String[] st = s.split(":");
    for (int i = 0; i <= h.get(gra); i++) {
      res[i] = st[i];
    }
    int[] t = Arrays.stream(res).mapToInt(Integer::parseInt).toArray();
    // For example,
    // This input, retrieve("2016:01:01:01:01:01","2017:01:01:23:00:00","Year"),
    // should retrieve 2017:01:01:23:59:59, which means it should include timestamp
    // from "2017:01:01:23:00:01" to "2017:01:01:24:00:00" (exclusive)
    if (end) {
      t[h.get(gra)]++;
    }

    return convert(t);
  }


  // Your LogSystem object will be instantiated and called as such:
  //
  // LogSystem obj = new LogSystem();
  // obj.put(id, timestamp);
  // List<Integer> param_2 = obj.retrieve(start, end, granularity);


  // Approach 2 Better Retrieval
  // Author: @vinod23 + kei (AC)
  // Date : March 8, 2020
  public class LogSystem {
    // Make use of tailMap() on TreeMap.
    TreeMap<Long, Integer> map;

    public LogSystem() {
      map = new TreeMap<Long, Integer>();
    }

    // O(logN) time
    public void put(int id, String timestamp) {
      int[] st = Arrays.stream(timestamp.split(":")).mapToInt(Integer::parseInt).toArray();
      map.put(convert(st), id);
    }

    public long convert(int[] st) {
      st[1] = st[1] - (st[1] == 0 ? 0 : 1);
      st[2] = st[2] - (st[2] == 0 ? 0 : 1);
      return (st[0] - 1999L) * (31 * 12) * 24 * 60 * 60 + st[1] * 31 * 24 * 60 * 60
          + st[2] * 24 * 60 * 60 + st[3] * 60 * 60 + st[4] * 60 + st[5];
    }

    // O(m_start) time, m_start refers to the number of entries in the current set of
    // logs which have a timestamp greater than the current start value.
    public List<Integer> retrieve(String s, String e, String gra) {
      ArrayList<Integer> res = new ArrayList<>();
      long start = granularity(s, gra, false);
      long end = granularity(e, gra, true);
      // Just need a key set from start to end (exclusive).
      for (long key : map.subMap(start, end).keySet()) {
        if (key >= start && key < end) {
          res.add(map.get(key));
        }
      }

      return res;
    }

    public long granularity(String s, String gra, boolean end) {
      HashMap<String, Integer> h = new HashMap<>();
      h.put("Year", 0);
      h.put("Month", 1);
      h.put("Day", 2);
      h.put("Hour", 3);
      h.put("Minute", 4);
      h.put("Second", 5);
      String[] res = new String[] {"1999", "00", "00", "00", "00", "00"};
      String[] st = s.split(":");
      for (int i = 0; i <= h.get(gra); i++) {
        res[i] = st[i];
      }
      int[] t = Arrays.stream(res).mapToInt(Integer::parseInt).toArray();
      if (end) {
        t[h.get(gra)]++;
      }

      return convert(t);
    }
  }



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


