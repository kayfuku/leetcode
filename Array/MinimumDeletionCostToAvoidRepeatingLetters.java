//
// Author:
// Date  : September 8, 2020

package leetcode;

public class MinimumDeletionCostToAvoidRepeatingLetters {
  // fields and classes here.
  // private int count;

  public MinimumDeletionCostToAvoidRepeatingLetters() {
    // Initialization here.
    // this.count = 0;

  }

  // This is better than the next.
  // Author: lee215 + kei
  // Date : December 4, 2020
  public int minCost(String s, int[] cost) {
    if (s.length() < 2) {
      return 0;
    }

    int res = 0, maxCost = 0, sumCost = 0, n = s.length();
    for (int i = 0; i < n; i++) {
      // Skip the first elem.
      if (i > 0 && s.charAt(i) != s.charAt(i - 1)) {
        // The cost so far has been confirmed.
        res += sumCost - maxCost;
        sumCost = 0;
        maxCost = 0;
      }
      // Cost must be considered from the first elem.
      sumCost += cost[i];
      maxCost = Math.max(maxCost, cost[i]);
    }
    // Don't forget this!
    res += sumCost - maxCost;

    return res;
  }

  // Author: uwi + kei
  // Date : September 8, 2020
  public int minCost2(String S, int[] cost) {
    // This is important to simplify the code.
    char[] s = S.toCharArray();

    // We just have to subtract max cost among a group of characters (including
    // len 1) from total cost.
    // Calculate total cost.
    int total = 0;
    for (int v : cost) {
      total += v;
    }

    // Initialize pre to any character other than lowercase English letters.
    char pre = '0';
    int max = 0;
    // Iterate through two arrays using foreach and pointer.
    int p = 0;
    for (char c : s) {
      if (c != pre) {
        // max confirmed.
        // Subtract max from total.
        total -= max;
        // Reset max.
        max = 0;
        pre = c;
      }
      max = Math.max(max, cost[p]);
      p++;
    }
    // Don't forget this.
    total -= max;

    return total;
  }

  // For testing.
  @SuppressWarnings("unused")
  public static void main(String[] args) {
    MinimumDeletionCostToAvoidRepeatingLetters solution = new MinimumDeletionCostToAvoidRepeatingLetters();

    // Test arguments.
    // int num = 24;
    // int target = 2;
    // solution.getInt(num, target);

    String s = "aabaa";
    int[] cost = { 1, 2, 3, 4, 1 };
    System.out.println(solution.minCost(s, cost));

  }

}
