//
// Author:
// Date : August ?, 2020

package leetcode;

import java.util.HashMap;
import java.util.Map;

public class MinimumNumberOfDaysToEatNOranges {
  // fields and classes here.
  // private int count;

  public MinimumNumberOfDaysToEatNOranges() {
    // Initialization here.
    // this.count = 0;

  }

  // Author: hitonanode + kei
  // Date : August 20, 2020
  Map<Integer, Integer> memo = new HashMap<>();

  public int minDays(int n) {
    return dfs(n);
  }

  int dfs(int n) {
    if (n <= 2) {
      return n;
    }

    if (memo.containsKey(n)) {
      return memo.get(n);
    }

    int ret = Integer.MAX_VALUE;

    ret = 1 + Math.min(n % 2 + dfs(n / 2), n % 3 + dfs(n / 3));

    memo.put(n, ret);

    return ret;
  }



  // // NG
  // // Author: kei (MLE)
  // // Date : August 20, 2020
  // Map<Integer, Integer> memo = new HashMap<>();
  //
  // public int minDays(int n) {
  // return dfs(n);
  // }
  //
  // int dfs(int n) {
  // if (n <= 2) {
  // return n;
  // }
  //
  // if (memo.containsKey(n)) {
  // return memo.get(n);
  // }
  //
  // int ret = Integer.MAX_VALUE;
  //
  // if (n % 3 == 0) {
  // ret = Math.min(ret, dfs(n / 3) + 1);
  // }
  // if (n % 2 == 0) {
  // ret = Math.min(ret, dfs(n / 2) + 1);
  // }
  // ret = Math.min(ret, dfs(n - 1) + 1);
  //
  // memo.put(n, ret);
  //
  // return ret;
  // }



  // For testing.
  @SuppressWarnings("unused")
  public static void main(String[] args) {
    MinimumNumberOfDaysToEatNOranges solution = new MinimumNumberOfDaysToEatNOranges();

    // Test arguments.
    // int num = 24;
    // int target = 2;
    // solution.getInt(num, target);



  }



  public void dummyMethod() {



  }



  public void dummyMethod2() {



  }



}


