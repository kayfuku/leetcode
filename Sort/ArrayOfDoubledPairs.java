package leetcode;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class ArrayOfDoubledPairs {
  // fields and classes here.
  // private int count;

  public ArrayOfDoubledPairs() {
    // Initialization here.
    // this.count = 0;

  }

  // Author: leetcode + kei
  // Date : October 27, 2021
  public boolean canReorderDoubled(int[] A) {
    // count[x] = the number of occurrences of x in A
    Map<Integer, Integer> count = new HashMap<>();
    for (int x : A) {
      count.put(x, count.getOrDefault(x, 0) + 1);
    }

    // Note that we need to convert type int[] to Integer[] to sort in
    // some special way!!
    Integer[] B = new Integer[A.length];
    for (int i = 0; i < B.length; i++) {
      B[i] = A[i];
    }
    Arrays.sort(B, (a, b) -> Math.abs(a) - Math.abs(b));

    for (int x : B) {
      // If this can't be consumed, skip
      if (count.get(x) == 0) {
        continue;
      }
      // If this doesn't have a doubled partner, the answer is false
      if (count.get(x) > count.getOrDefault(2 * x, 0)) {
        return false;
      }
      count.put(x, count.get(x) - 1);
      count.put(2 * x, count.get(2 * x) - 1);
    }

    // If we have written everything, the answer is true
    return true;
  }

  // O(NlogN) time and O(N) space
  // Author: kei (AC)
  // Date : October 27, 2021
  public boolean canReorderDoubled2(int[] arr) {
    TreeMap<Integer, Integer> countsPos = new TreeMap<>();
    TreeMap<Integer, Integer> countsNeg = new TreeMap<>();
    for (int num : arr) {
      if (num < 0) {
        countsNeg.put(-num, countsNeg.getOrDefault(-num, 0) + 1);
      } else {
        countsPos.put(num, countsPos.getOrDefault(num, 0) + 1);
      }
    }

    return check(countsNeg) && check(countsPos);
  }

  private boolean check(TreeMap<Integer, Integer> counts) {
    for (int i : counts.keySet()) {
      if (counts.get(i) == 0) {
        continue;
      }
      if (counts.get(i * 2) == null || counts.get(i) > counts.get(i * 2)) {
        return false;
      }
      counts.put(i * 2, counts.get(i * 2) - counts.get(i));
    }

    return true;
  }

  // For testing.
  @SuppressWarnings("unused")
  public static void main(String[] args) {
    ArrayOfDoubledPairs solution = new ArrayOfDoubledPairs();

    // Test arguments.
    // int num = 24;
    // int target = 2;
    // System.out.println(solution.getInt(num, target));
    int[] arr = { 4, -2, -4, 2 };
    System.out.println(solution.canReorderDoubled(arr));

    System.out.println("\ndone.");
  }

}
