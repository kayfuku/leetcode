package leetcode;

import java.util.Map;
import java.util.TreeMap;

public class FindOriginalArrayFromDoubledArray {
  // fields and classes here.
  // private int count;

  public FindOriginalArrayFromDoubledArray() {
    // Initialization here.
    // this.count = 0;

  }

  // Author: lee215 + kei
  // Date : October 22, 2021
  public int[] findOriginalArray(int[] A) {
    int n = A.length, i = 0;
    if (n % 2 == 1) {
      return new int[0];
    }
    int[] res = new int[n / 2];
    Map<Integer, Integer> count = new TreeMap<>();
    for (int a : A) {
      count.put(a, count.getOrDefault(a, 0) + 1);
    }
    for (int x : count.keySet()) {
      if (count.get(x) == 0) {
        continue;
      }
      if (count.get(x * 2) == null || count.get(x) > count.get(x * 2)) {
        return new int[0];
      }
      count.put(x * 2, count.get(x * 2) - count.get(x));
      for (int j = 0; j < count.get(x); j++) {
        res[i] = x;
        i++;
      }
    }
    return res;
  }

  // NG!
  // Author: + kei
  // Date : October 22, 2021
  public int[] findOriginalArrayNG(int[] changed) {
    if (changed.length % 2 == 1) {
      return new int[0];
    }
    TreeMap<Integer, Integer> counts = new TreeMap<>();
    for (int num : changed) {
      counts.put(num, counts.getOrDefault(num, 0) + 1);
    }
    int[] res = new int[changed.length / 2];
    int i = 0;
    if (counts.containsKey(0)) {
      if (counts.get(0) % 2 == 1) {
        return new int[0];
      } else {
        while (i < counts.get(0) / 2) {
          res[i] = 0;
          i++;
        }
      }
    }
    Integer p1 = counts.firstKey(), p2 = p1 * 2;
    while (p2 != null) {
      counts.put(p2, counts.get(p2) - counts.get(p1));
      while (counts.get(p1) > 0) {
        res[i] = p1;
        i++;
        counts.put(p1, counts.get(p1) - 1);
      }
      p1 = counts.higherKey(p1);
      p2 = counts.higherKey(p2);
    }
    while (p1 != null) {
      if (counts.get(p1) != 0) {
        return new int[0];
      }
      p1 = counts.higherKey(p1);
    }
    return res;
  }

  // For testing.
  @SuppressWarnings("unused")
  public static void main(String[] args) {
    FindOriginalArrayFromDoubledArray solution = new FindOriginalArrayFromDoubledArray();

    // Test arguments.
    // int num = 24;
    // int target = 2;
    // System.out.println(solution.getInt(num, target));
    int[] A = { 1, 3, 4, 2, 6, 8 };
    System.out.println(solution.findOriginalArray(A));

    System.out.println("\ndone.");
  }

}
