//
// Author:
// Date  : September 18, 2020

package leetcode;

import java.util.ArrayList;
import java.util.List;

public class Combinations {
  // fields and classes here.
  // private int count;

  public Combinations() {
    // Initialization here.
    // this.count = 0;

  }

  // O(k•nCk)) time, O(nCk) space.
  // Author: kei (AC)
  // Date : September 18, 2020
  int n, k;
  List<List<Integer>> ret = new ArrayList<>();

  public List<List<Integer>> combine(int n, int k) {
    this.n = n;
    this.k = k;
    List<Integer> comb = new ArrayList<>();
    helper(comb, 1, 0);
    return ret;
  }

  private void helper(List<Integer> comb, int start, int count) {
    if (count == k) {
      // Create a new list of comb and then add it to the ret!
      ret.add(new ArrayList<>(comb));
      // NG! If you do this, then the comb in the ret also lose its elements
      // when backtracking.
      // ret.add(comb);
      return;
    }

    for (int i = start; i <= n; i++) {
      comb.add(i);
      count++;
      // If you pass the reference 'comb', then you need to remove what was added
      // after coming back from the recursive call.
      helper(comb, i + 1, count);
      comb.remove(comb.size() - 1);
      count--;
    }
  }

  // For testing.
  @SuppressWarnings("unused")
  public static void main(String[] args) {
    Combinations solution = new Combinations();

    // Test arguments.
    // int num = 24;
    // int target = 2;
    // solution.getInt(num, target);

    int n = 4;
    int k = 2;
    System.out.println(solution.combine(n, k));

  }

}
