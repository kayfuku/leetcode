//
// Author:
// Date : July 5, 2019

package leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CoinChange {
  // fields here.
  // private int count;

  public CoinChange() {
    // Initialization here.
    // this.count = 0;
  }

  // 1. Recursion (Top down Dynamic Programming)
  // ref. CombinationSum.java
  //
  // Input: coins = [1,2,5], amount = 11
  // Output: 3
  // Explanation: 11 = 5 + 5 + 1
  //
  // Author: kei (AC)
  // Date : August 24, 2019, October 21, 2020
  public int coinChange(int[] coins, int amount) {
    Arrays.sort(coins);
    List<Integer> list = new ArrayList<>();
    // Note that in this case, where we use 'start',
    // we can't just create a memo that only has 'rem' as a key and
    // 'number of coins' as a value because just using 'rem' does not identify the
    // structure of recursion tree. In other words, 'rem' and 'start' identify the
    // structure of recursion tree.
    // K: rem, V: Map<K: start, V: number of coins>
    Map<Integer, Map<Integer, Integer>> memo = new HashMap<>();
    return coinChange(0, coins, amount, memo, 0, list);
  }

  // public static List<List<Integer>> ret = new ArrayList<>();

  private int coinChange(int start, int[] coins, int rem, Map<Integer, Map<Integer, Integer>> memo, int level,
      List<Integer> list) {
    if (rem == 0) {
      // ret.add(new ArrayList<>(list));
      return 0;
    }

    if (memo.containsKey(rem) && memo.get(rem).containsKey(start)) {
      return memo.get(rem).get(start);
    }

    int min = Integer.MAX_VALUE;
    for (int i = start; i < coins.length && coins[i] <= rem; i++) {
      // list.add(coins[i]);
      int res = coinChange(i, coins, rem - coins[i], memo, level + 1, list);
      if (res != -1) {
        min = Math.min(min, res);
      }
      // list.remove(list.size() - 1);
    }

    if (!memo.containsKey(rem)) {
      memo.put(rem, new HashMap<>());
    }
    if (min == Integer.MAX_VALUE) {
      // All branches return -1;
      memo.get(rem).put(start, -1);
    } else {
      memo.get(rem).put(start, min + 1);
    }
    return memo.get(rem).get(start);
  }

  // 1. Recursion (Top down Dynamic Programming), faster than above.
  // Author: leetcode + kei (AC)
  // Date : August 24, 2019, October 21, 2020
  public int coinChangeSol(int[] coins, int amount) {
    if (amount < 1) {
      return 0;
    }
    return coinChangeSol(coins, amount, new HashMap<>(), new ArrayList<>());
  }

  private int coinChangeSol(int[] coins, int rem, Map<Integer, Integer> memo, List<Integer> list) {
    if (rem < 0) {
      return -1;
    }
    if (rem == 0) {
      // ret.add(new ArrayList<>(list));
      return 0;
    }
    if (memo.containsKey(rem)) {
      return memo.get(rem);
    }

    int min = Integer.MAX_VALUE;
    for (int coin : coins) {
      // list.add(coin);
      if (coin > rem) {
        continue;
      }
      int res = coinChangeSol(coins, rem - coin, memo, list);
      if (res != -1) {
        min = Math.min(min, res);
      }
      // list.remove(list.size() - 1);
    }

    if (min == Integer.MAX_VALUE) {
      // All branches return -1;
      memo.put(rem, -1);
    } else {
      memo.put(rem, min + 1);
    }
    return memo.get(rem);
  }

  // 3. Dynamic Programming (Bottom Up)
  // I'm going to find the smallest number of coins required for amount 'a'.
  // When I pick up any coin d_i, the remaining amount is 'a' - d_i.
  // Then I have a smaller subproblem, and then think about the remaining amount
  // after using the first coin.
  //
  // Let dp[a] store the smallest number of coins required for amount 'a', then
  // the dp[a] looks like the following.
  //
  // Base case : dp[0] = 0
  // Recursive case: dp[a] = min(dp[a], dp[a - d_i] + 1)
  //
  // O(Amount * numCoins) time. O(Amount) space.
  //
  // Author: @elmirap + kei (AC)
  // Date : August 24, 2019
  public int coinChange3(int[] coins, int amount) {
    if (coins == null || coins.length == 0) {
      return 0;
    }

    // To find min, fill the dp with 'init'.
    // dp[a]: the smallest number of coins required for amount 'a'.
    int init = amount + 1;
    int[] dp = new int[amount + 1];
    Arrays.fill(dp, init);

    dp[0] = 0;
    for (int a = 1; a <= amount; a++) {
      // Find the smallest number of coins required for amount 'a'.
      for (int i = 0; i < coins.length; i++) {
        // If coins[i] > a, no need to consider that coin.
        if (coins[i] <= a) {
          // Check the number of coins when I use coins[i], and find min.
          dp[a] = Math.min(dp[a], dp[a - coins[i]] + 1);
        }
      }
    }

    return (dp[amount] == init) ? -1 : dp[amount];
  }

  // For testing.
  public static void main(String[] args) {
    CoinChange solution = new CoinChange();

    // Test arguments.
    // int num = 24;
    // int target = 2;
    // solution.getInt(num, target);

    int[] coins = new int[] { 1, 2, 5 };
    int amount = 11;
    // System.out.println(solution.coinChange(coins, amount)); // 3
    // System.out.println(ret);

    // coins = new int[] { 195, 265, 404, 396 };
    // amount = 3239;
    // System.out.println(solution.coinChange(coins, amount)); // 3

    coins = new int[] { 484, 395, 346, 103, 329 };
    amount = 4259;
    System.out.println(solution.coinChange(coins, amount)); // 12? (Expected: 11)
    // System.out.println(ret);

    // coins = new int[] { 1, 4, 6 };
    // amount = 8;
    // System.out.println(solution.coinChange3(coins, amount)); // 2

    // coins = new int[] { 2 };
    // amount = 3;
    // System.out.println(solution.coinChange3(coins, amount)); // -1

  }

}
