package leetcode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

public class TopKFrequentWords {
  // fields and classes here.
  // private int count;

  public TopKFrequentWords() {
    // Initialization here.
    // this.count = 0;

  }

  // Heap
  // O(NlogK) time, O(N) space
  // Author: + kei
  // Date : December 6, 2020
  public List<String> topKFrequent(String[] words, int k) {
    Map<String, Integer> map = new HashMap<>();
    for (String s : words) {
      map.put(s, map.getOrDefault(s, 0) + 1);
    }

    PriorityQueue<String> minHeap = new PriorityQueue<>(
        // Min-Heap. If it ties, then arrange in reverse lexicographic order because
        // we will reverse it to fulfill the requirement before returning.
        (a, b) -> (map.get(a) != map.get(b)) ? map.get(a) - map.get(b) : b.compareTo(a));

    for (String s : map.keySet()) {
      minHeap.offer(s);
      if (minHeap.size() > k) {
        minHeap.poll();
      }
    }

    // This is the trickiest part!
    // "Your answer should be sorted by frequency from highest to lowest. If two
    // words have the same frequency, then the word with the lower alphabetical
    // order comes first."

    // 1. ArrayList
    List<String> res = new ArrayList<>(k);
    while (!minHeap.isEmpty()) {
      // Add it to the tail.
      res.add(minHeap.poll());
    }
    // NG! The next line sorts it in reverse lexicographic order, which disrupts the
    // arranged order in Min-Heap.
    // res.sort(Collections.reverseOrder());
    Collections.reverse(res);

    // 2. LinkedList
    // List<String> res = new LinkedList<>();
    // while (!minHeap.isEmpty()) {
    // // Add it to the head, which reverses the arranged order in Min-Heap.
    // res.add(0, minHeap.poll());
    // }

    // System.out.println(res);

    return res;
  }

  // For testing.
  @SuppressWarnings("unused")
  public static void main(String[] args) {
    TopKFrequentWords solution = new TopKFrequentWords();

    // Test arguments.
    // int num = 24;
    // int target = 2;
    // solution.getInt(num, target);
    String[] words = { "the", "day", "is", "sunny", "the", "the", "the", "sunny", "is", "is" };
    int k = 4;
    System.out.println(solution.topKFrequent(words, k));

  }

}
