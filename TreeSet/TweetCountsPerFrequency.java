package leetcode;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class TweetCountsPerFrequency {
  // fields and classes here.
  // private int count;

  // Author: frankkkkk + kei
  // Date : February 8, 2021

  // K: tweetName, V: TreeMap<K: time, V: count>
  private Map<String, TreeMap<Integer, Integer>> map;

  public TweetCountsPerFrequency() {
    map = new HashMap<>();
  }

  public void recordTweet(String tweetName, int time) {
    if (!map.containsKey(tweetName)) {
      map.put(tweetName, new TreeMap<>());
    }
    TreeMap<Integer, Integer> tweetMap = map.get(tweetName);
    // TreeMap sorts times internally so that we can access to any specific range
    // in the keys quickly in O(logN) time.
    tweetMap.put(time, tweetMap.getOrDefault(time, 0) + 1);
  }

  public List<Integer> getTweetCountsPerFrequency(String freq, String tweetName, int startTime, int endTime) {
    if (!map.containsKey(tweetName)) {
      return null;
    }

    int interval = 0, size = 0;
    if (freq.equals("minute")) {
      interval = 60;
    } else if (freq.equals("hour")) {
      interval = 3600;
    } else {
      interval = 86400;
    }

    // Use buckets to handle intervals.
    size = ((endTime - startTime) / interval) + 1;
    int[] buckets = new int[size];
    TreeMap<Integer, Integer> tweetMap = map.get(tweetName);
    // TreeMap.subMap(s, e) returns a submap from key s inclusive to key e
    // exclusive. We want the count at 'endTime', so e = endTime + 1.
    for (Map.Entry<Integer, Integer> entry : tweetMap.subMap(startTime, endTime + 1).entrySet()) {
      int time = entry.getKey();
      int count = entry.getValue();
      // Get bucket index that contains 'time'.
      int index = (time - startTime) / interval;
      buckets[index] += count;
    }

    List<Integer> res = new LinkedList<>();
    for (int num : buckets) {
      res.add(num);
    }

    return res;
  }

  // // Your TweetCounts object will be instantiated and called as such:
  // TweetCounts
  // obj=new TweetCounts();obj.recordTweet(tweetName,time); //
  // List<Integer> param_2 = obj.getTweetCountsPerFrequency(freq, tweetName,
  // startTime, endTime); //

  // For testing.
  @SuppressWarnings("unused")
  public static void main(String[] args) {
    TweetCountsPerFrequency solution = new TweetCountsPerFrequency();

    // Test arguments.
    // int num = 24;
    // int target = 2;
    // System.out.println(solution.getInt(num, target));

    System.out.println("\ndone.");
  }

}
