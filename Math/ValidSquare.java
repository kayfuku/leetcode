package leetcode;

import java.util.Arrays;

public class ValidSquare {
  // fields and classes here.
  // private int count;

  public ValidSquare() {
    // Initialization here.
    // this.count = 0;

  }

  // Author: leetcode + kei
  // Date : October 26, 2021
  public double dist(int[] p1, int[] p2) {
    return (p2[1] - p1[1]) * (p2[1] - p1[1]) + (p2[0] - p1[0]) * (p2[0] - p1[0]);
  }

  public boolean validSquare(int[] p1, int[] p2, int[] p3, int[] p4) {
    // Put the input arrays in an array to sort them.
    int[][] p = { p1, p2, p3, p4 };
    // Sort based on x coordinates, and if they tie, then sort based on y
    // coordinates.
    Arrays.sort(p, (l1, l2) -> l2[0] == l1[0] ? l1[1] - l2[1] : l1[0] - l2[0]);
    // Check all four side lengths are equal and two diagonal lengths are equal
    // and no overlap points.
    return dist(p1, p2) > 0 && //
        dist(p1, p3) > 0 && //
        dist(p1, p4) > 0 && //
        dist(p[0], p[1]) == dist(p[1], p[3]) && //
        dist(p[1], p[3]) == dist(p[3], p[2]) && //
        dist(p[3], p[2]) == dist(p[2], p[0]) && //
        dist(p[0], p[3]) == dist(p[1], p[2]);
  }

  // Author: leetcode + kei
  // Date : October 26, 2021
  public boolean validSquare2(int[] p1, int[] p2, int[] p3, int[] p4) {
    int[][] p = { p1, p2, p3, p4 };
    return checkAllPermutations(p, 0);
  }

  boolean checkAllPermutations(int[][] p, int start) {
    if (start == 4) {
      return check(p[0], p[1], p[2], p[3]);
    }

    boolean res = false;
    for (int i = start; i < 4; i++) {
      swap(p, start, i);
      if (checkAllPermutations(p, start + 1)) {
        return true;
      }
      swap(p, start, i);
    }
    return res;
  }

  public boolean check(int[] p1, int[] p2, int[] p3, int[] p4) {
    // Check all four side lengths are equal and two diagonal lengths are equal
    // and no overlap points. Only checking dist(p1, p2) > 0 does not suffice.
    // ex. p1: (0, 0), p2 (1, 1), p3: (0, 0), p4 (1, 1)
    return dist(p1, p2) > 0 && //
        dist(p1, p3) > 0 && //
        dist(p1, p4) > 0 && //
        dist(p1, p2) == dist(p2, p3) && //
        dist(p2, p3) == dist(p3, p4) && //
        dist(p3, p4) == dist(p4, p1) && //
        dist(p1, p3) == dist(p2, p4);
  }

  // public double dist(int[] p1, int[] p2) {
  // // Return distance between two points.
  // return (p2[1] - p1[1]) * (p2[1] - p1[1]) + (p2[0] - p1[0]) * (p2[0] - p1[0]);
  // }

  public void swap(int[][] p, int x, int y) {
    int[] temp = p[x];
    p[x] = p[y];
    p[y] = temp;
  }

  // For testing.
  @SuppressWarnings("unused")
  public static void main(String[] args) {
    Solution solution = new Solution();

    // Test arguments.
    // int num = 24;
    // int target = 2;
    // System.out.println(solution.getInt(num, target));

    System.out.println("\ndone.");
  }

}
