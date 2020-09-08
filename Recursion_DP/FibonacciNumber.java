//
// Author:
// Date : September 5, 2020

package leetcode;

import java.util.HashMap;
import java.util.Map;

public class FibonacciNumber {
  // fields and classes here.
  // private int count;

  public FibonacciNumber() {
    // Initialization here.
    // this.count = 0;

  }


  // 1. Recursion. 
  // Author: kei (AC)
  // Date : September 5, 2020
  public int fib(int N) {
    if (N == 0 || N == 1) {
      return N;
    }

    return fib(N - 1) + fib(N - 2);
  }


  // 2. Recursion + Memoization. 
  // Author: kei (AC)
  // Date : September 5, 2020
  Map<Integer, Integer> memo = new HashMap<>();
  public int fib2(int N) {
    if (N == 0 || N == 1) {
      memo.put(N, N);
      return N;
    }

    if (memo.containsKey(N)) {
      return memo.get(N);
    }

    int ret = fib2(N - 1) + fib2(N - 2);
    memo.put(N, ret);

    return ret;
  }


  // 3. Bottom up fibnacci. 
  // Author: kei (AC)
  // Date : September 5, 2020
  public static int iterativeFibonacci(int n) {
    if (n == 0 || n == 1) {
      return n;
    }
    // n >= 2
    int a = 1;
    int b = 1;
    int c = 1;
    for (int i = 3; i <= n; i++) {
      c = a + b;
      a = b;
      b = c;
    }

    return c;
  }


  // For testing.
  @SuppressWarnings("unused")
  public static void main(String[] args) {
    FibonacciNumber solution = new FibonacciNumber();

    // Test arguments.
    // int num = 24;
    // int target = 2;
    // solution.getInt(num, target);



  }




}


