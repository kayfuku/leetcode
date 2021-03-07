package leetcode;

import java.util.*;

public class ClosestDessertCost {
  // fields and classes here.
  // private int count;

  public ClosestDessertCost() {
    // Initialization here.
    // this.count = 0;

  }

  // Author: zerotrac2 + kei
  // Date : March 6, 2021
  private int ans = -1;

  public int closestCost(int[] baseCosts, int[] toppingCosts, int target) {
    int n = baseCosts.length;
    for (int i = 0; i < n; i++) {
      dfs(baseCosts[i], toppingCosts, 0, target);
    }
    return ans;
  }

  private void dfs(int cost, int[] toppingCosts, int j, int target) {
    if (j == toppingCosts.length) {
      // Now we considered all the toppings.
      if (ans == -1 || //
          Math.abs(cost - target) < Math.abs(ans - target) || //
          (Math.abs(cost - target) == Math.abs(ans - target) && cost < ans)) {
        ans = cost;
      }
      return;
    }
    // no topping
    dfs(cost, toppingCosts, j + 1, target);
    // one topping of this kind
    dfs(cost + toppingCosts[j], toppingCosts, j + 1, target);
    // two toppings of this kind
    dfs(cost + toppingCosts[j] * 2, toppingCosts, j + 1, target);
  }

  // Author: uwi + kei
  // Date : February 28, 2021
  public int closestCost2(int[] baseCosts, int[] toppingCosts, int target) {
    // Looking at the restrictions,
    //
    // m == toppingCosts.length
    // 1 <= n, m <= 10
    // 1 <= baseCosts[i], toppingCosts[i] <= 10^4
    // 1 <= target <= 10^4
    //
    // The maximum toppings cost we need to think about is 20000 because the target
    // is at most 10000.
    // i: sum of toppings cost
    // dp[i]: True if that case exists.
    boolean[] dp = new boolean[20000];
    dp[0] = true;
    for (int t : toppingCosts) {
      for (int i = 19999; i >= 0; i--) {
        if (dp[i]) {
          if (i + t < 20000) {
            dp[i + t] = true;
          }
          if (i + 2 * t < 20000) {
            dp[i + 2 * t] = true;
          }
        }
      }
    }

    int best = Integer.MAX_VALUE;
    for (int b : baseCosts) {
      for (int i = 0; i < 20000; i++) {
        if (dp[i]) {
          int v = b + i;
          if (Math.abs(v - target) < Math.abs(best - target)
              || Math.abs(v - target) == Math.abs(best - target) && v < best) {
            best = v;
          }
        }
      }
    }

    return best;
  }

  // Well, creating a set is good, but it could be more efficient.
  // Author: kei (AC)
  // Date : February 28, 2021
  public int closestCost3(int[] baseCosts, int[] toppingCosts, int target) {
    Set<Integer> toppingSet = new HashSet<>();
    for (int i = 0; i < toppingCosts.length; i++) {
      getSet(i, toppingCosts, 0, toppingSet);
    }

    int diff = 0;
    int min = Integer.MAX_VALUE;
    int ans = 0;
    for (int i = 0; i < baseCosts.length; i++) {
      int b = baseCosts[i];
      for (int t : toppingSet) {
        int sum = b + t;
        diff = Math.abs(sum - target);
        if (diff < min) {
          min = diff;
          ans = sum;
        } else if (diff == min) {
          ans = Math.min(ans, sum);
        }
      }
    }

    return ans;
  }

  private void getSet(int i, int[] toppingCosts, int sum, Set<Integer> toppingSet) {
    if (i == toppingCosts.length) {
      toppingSet.add(sum);
      return;
    }
    for (int j = 0; j <= 2; j++) {
      // NG!
      // sum += toppingCosts[i] * j;
      getSet(i + 1, toppingCosts, sum + toppingCosts[i] * j, toppingSet);
    }
  }

  // For testing.
  @SuppressWarnings("unused")
  public static void main(String[] args) {
    ClosestDessertCost solution = new ClosestDessertCost();

    // Test arguments.
    // int num = 24;
    // int target = 2;
    // System.out.println(solution.getInt(num, target));

    // int[] base = { 2, 3 };
    // int[] topp = { 4, 5, 100 };
    // int target = 18;

    int[] base = { 3, 10 };
    int[] topp = { 2, 5 };
    int target = 9;

    System.out.println(solution.closestCost2(base, topp, target));

    System.out.println("\ndone.");
  }

}
