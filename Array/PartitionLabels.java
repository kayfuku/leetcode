package leetcode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PartitionLabels {
  // fields and classes here.
  // private int count;

  public PartitionLabels() {
    // Initialization here.
    // this.count = 0;

  }

  // Better solution.
  // O(N) time, O(1) space.
  // Author: leetcode + kei
  // Date : February 1, 2021
  public List<Integer> partitionLabels(String S) {
    // Save the last index of each character.
    int[] last = new int[26];
    for (int i = 0; i < S.length(); i++) {
      last[S.charAt(i) - 'a'] = i;
    }

    int start = 0, end = 0;
    List<Integer> ans = new ArrayList<>();
    for (int i = 0; i < S.length(); i++) {
      // Extend the end of the partition.
      end = Math.max(end, last[S.charAt(i) - 'a']);
      if (i == end) {
        // i reached the end of the partition.
        // 'end - start + 1' is equal to the length of the partition.
        ans.add(end - start + 1);
        start = i + 1;
      }
    }
    return ans;
  }

  // O(N + KlogK) time, where N is the length of S and K is the number of unique
  // characters in S.
  // O(K) space.
  // Author: kei (AC)
  // Date : February 1, 2021
  public List<Integer> partitionLabels2(String S) {
    Map<Character, int[]> map = new HashMap<>();
    for (int i = 0; i < S.length(); i++) {
      char c = S.charAt(i);
      if (!map.containsKey(c)) {
        map.put(c, new int[] { i, i });
      } else {
        map.get(c)[1] = i;
      }
    }

    // Need container to sort elements.
    List<int[]> intvls = new ArrayList<>(map.values());
    Collections.sort(intvls, (a, b) -> a[0] - b[0]);

    int start = 0, maxEnd = 0;
    List<Integer> ans = new ArrayList<>();
    for (int i = 0; i < intvls.size(); i++) {
      int s = intvls.get(i)[0];
      int e = intvls.get(i)[1];
      if (i == 0 || s < maxEnd) {
        maxEnd = Math.max(maxEnd, e);
      } else {
        ans.add(maxEnd - start + 1);
        start = s;
        maxEnd = e;
      }
    }
    ans.add(maxEnd - start + 1);

    return ans;
  }

  // For testing.
  @SuppressWarnings("unused")
  public static void main(String[] args) {
    PartitionLabels solution = new PartitionLabels();

    // Test arguments.
    // int num = 24;
    // int target = 2;
    // System.out.println(solution.getInt(num, target));
    String S = "ababcbacadefegdehijhklij";
    System.out.println(solution.partitionLabels(S));

    System.out.println("\ndone.");
  }

}
