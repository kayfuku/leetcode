package leetcode;

import java.util.PriorityQueue;

public class ReorganizeString {
  // fields and classes here.
  // private int count;

  public ReorganizeString() {
    // Initialization here.
    // this.count = 0;

  }

  // 1. This is the best.
  // Author: fangbiyi + kei
  // Date : December 5, 2020
  public String reorganizeString(String S) {
    // Count.
    int[] counts = new int[26];
    for (int i = 0; i < S.length(); i++) {
      counts[S.charAt(i) - 'a']++;
    }
    // Find the most frequent char.
    int max = 0, maxIdx = 0;
    for (int i = 0; i < counts.length; i++) {
      if (counts[i] > max) {
        max = counts[i];
        maxIdx = i;
      }
    }
    // Check if it's an impossible case.
    if (max > (S.length() + 1) / 2) {
      return "";
    }
    // Put the most frequent char at even indices.
    char[] res = new char[S.length()];
    int idx = 0;
    // Until the count gets to 0.
    while (counts[maxIdx] > 0) {
      res[idx] = (char) (maxIdx + 'a');
      idx += 2;
      counts[maxIdx]--;
    }
    // Put the rest chars into the array.
    for (int i = 0; i < counts.length; i++) {
      while (counts[i] > 0) {
        if (idx >= res.length) {
          // idx reached the out of bound.
          // Get back to index 1.
          idx = 1;
        }
        res[idx] = (char) (i + 'a');
        idx += 2;
        counts[i]--;
      }
    }
    // Convert char array to String.
    return String.valueOf(res);
  }

  // 2. Greedy with Heap. No need to review.
  // Author: leetcode + kei
  // Date : December 5, 2020
  class MultiChar {
    int count;
    char letter;

    MultiChar(int ct, char ch) {
      count = ct;
      letter = ch;
    }
  }

  public String reorganizeString2(String S) {
    int N = S.length();
    int[] count = new int[26];
    for (char c : S.toCharArray()) {
      count[c - 'a']++;
    }
    // Create a Max-Heap.
    PriorityQueue<MultiChar> minHeap = new PriorityQueue<>(
        // Descending order by count, if it's tie, then lexicographically.
        (a, b) -> a.count != b.count ? b.count - a.count : a.letter - b.letter);

    // Populate the heap.
    for (int i = 0; i < 26; i++) {
      if (count[i] == 0) {
        continue;
      }
      if (count[i] > (N + 1) / 2) {
        return "";
      }
      minHeap.add(new MultiChar(count[i], (char) ('a' + i)));
    }

    StringBuilder ans = new StringBuilder();
    while (minHeap.size() >= 2) {
      // Take out the two largest.
      MultiChar mc1 = minHeap.poll();
      MultiChar mc2 = minHeap.poll();
      /*
       * This code turns out to be superfluous, but explains what is happening if
       * (ans.length() == 0 || mc1.letter != ans.charAt(ans.length() - 1)) {
       * ans.append(mc1.letter); ans.append(mc2.letter); } else {
       * ans.append(mc2.letter); ans.append(mc1.letter); }
       */
      ans.append(mc1.letter);
      ans.append(mc2.letter);
      mc1.count--;
      if (mc1.count > 0) {
        minHeap.add(mc1);
      }
      mc2.count--;
      if (mc2.count > 0) {
        minHeap.add(mc2);
      }
    }

    if (minHeap.size() > 0) {
      ans.append(minHeap.poll().letter);
    }

    return ans.toString();
  }

  // // NG!
  // // Author: + kei
  // // Date : December 5, 2020
  // public String reorganizeString(String S) {
  // char[] chars = S.toCharArray();
  // int[] counts = new int[26];
  // for (char c : chars) {
  // counts[c - 'a']++;
  // }
  // int max = 0, maxIdx = -1;
  // for (int i = 0; i < counts.length; i++) {
  // if (counts[i] > max) {
  // max = counts[i];
  // maxIdx = i;
  // }
  // }
  // if (max <= (S.length() - 1) / 2 + 1) {
  // return createString(counts, maxIdx, S.length());
  // }
  // return "";
  // }

  // private String createString(int[] counts, int maxIdx, int n) {
  // StringBuilder ans = new StringBuilder();
  // char maxChar = (char) (maxIdx + 'a');
  // for (int i = 0; i < n; i++) {
  // ans.append(maxChar);

  // }

  // return ans.toString();
  // }

  // For testing.
  @SuppressWarnings("unused")
  public static void main(String[] args) {
    ReorganizeString solution = new ReorganizeString();

    // Test arguments.
    // int num = 24;
    // int target = 2;
    // solution.getInt(num, target);

  }

}
