package leetcode;

import java.util.HashMap;
import java.util.Map;

public class DegreeOfArray {
  // fields and classes here.
  // private int count;

  public DegreeOfArray() {
    // Initialization here.
    // this.count = 0;

  }

  // One path
  // Author: lee215 + kei
  // Date : February 10, 2021
  public int findShortestSubArray(int[] A) {
    // We just need to count and first index for each item.
    Map<Integer, Integer> count = new HashMap<>();
    Map<Integer, Integer> first = new HashMap<>();
    // The length that we just need to care about
    int res = 0;
    // The maximum count of an item among all items
    int degree = 0;
    for (int i = 0; i < A.length; i++) {
      first.putIfAbsent(A[i], i);
      count.put(A[i], count.getOrDefault(A[i], 0) + 1);
      if (count.get(A[i]) > degree) {
        // Update the max.
        degree = count.get(A[i]);
        // Get the subarray length for this and update the minimum length, which should
        // be longer. This also means that we can throw away the length so far if the
        // max is updated.
        res = i - first.get(A[i]) + 1;
      } else if (count.get(A[i]) == degree) {
        // if it ties, then take the minimum length.
        res = Math.min(res, i - first.get(A[i]) + 1);
      }
    }

    return res;
  }

  // For testing.
  @SuppressWarnings("unused")
  public static void main(String[] args) {
    DegreeOfArray solution = new DegreeOfArray();

    // Test arguments.
    // int num = 24;
    // int target = 2;
    // System.out.println(solution.getInt(num, target));

    System.out.println("\ndone.");
  }

}
