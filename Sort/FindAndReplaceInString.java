package leetcode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class FindAndReplaceInString {
  // fields and classes here.
  // private int count;

  public FindAndReplaceInString() {
    // Initialization here.
    // this.count = 0;

  }

  // Author: lee215 + kei
  // Date : October 5, 2021
  public String findReplaceString(String s, int[] indices, String[] sources, String[] targets) {
    // By saving pairs of the array index and the elem of indices, we can use
    // sources and targets in the sorted order later.
    List<int[]> sortedIndexes = new ArrayList<>();
    for (int i = 0; i < indices.length; i++) {
      sortedIndexes.add(new int[] { indices[i], i });
    }
    // Sort indices in ascending order.
    Collections.sort(sortedIndexes, (pair1, pair2) -> pair1[0] - pair2[0]);

    StringBuilder sb = new StringBuilder();
    int startIndex = 0;
    for (int[] ind : sortedIndexes) {
      int i = ind[0], j = ind[1];
      String src = sources[j], trg = targets[j];
      if (s.substring(i, i + src.length()).equals(src)) {
        sb.append(s.substring(startIndex, i));
        sb.append(trg);
        startIndex = i + src.length();
      }
    }
    if (startIndex != s.length()) {
      sb.append(s.substring(startIndex));
    }

    return sb.toString();
  }

  // For testing.
  @SuppressWarnings("unused")
  public static void main(String[] args) {
    FindAndReplaceInString solution = new FindAndReplaceInString();

    // Test arguments.
    // int num = 24;
    // int target = 2;
    // System.out.println(solution.getInt(num, target));
    String s = "vmokgggqzp";
    int[] indices = { 3, 5, 1 };
    String[] sources = { "kg", "ggq", "mo" };
    String[] targets = { "s", "so", "bfr" };
    System.out.println(solution.findReplaceString(s, indices, sources, targets));

    System.out.println("\ndone.");
  }

}
