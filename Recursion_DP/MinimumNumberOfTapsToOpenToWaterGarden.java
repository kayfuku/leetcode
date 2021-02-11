//
// Author:
// Date : March 25, 2020

package leetcode;

import java.util.Arrays;

public class MinimumNumberOfTapsToOpenToWaterGarden {
  // fields and classes here.
  // private int count;

  public MinimumNumberOfTapsToOpenToWaterGarden() {
    // Initialization here.
    // this.count = 0;

  }

  // DP
  // Author: lee215 + kei (AC)
  // Date : March 26, 2020
  public int minTaps(int n, int[] A) {
    // dp[i] is the minimum number of taps to water [0, i].
    int[] dp = new int[n + 1];
    // Initialize dp[i] with n + 2, which is greater than max n + 1.
    Arrays.fill(dp, n + 2);
    // We need no tap to water nothing.
    dp[0] = 0;
    // Iterate through the taps from the leftmost.
    // Find the leftmost point of garden to water with tap i.
    // Find the rightmost point of garden to water with tap i.
    // We can water [leftmost, rightmost] with one tap i,
    // and water [0, leftmost] with dp[leftmost] taps.
    for (int i = 0; i <= n; i++) {
      // Iterate from leftmost: i - A[i] to rightmost: i + A[i].
      // Note that even though the tap has a wide range, we do not know
      // how far we should use with that tap in terms of
      // finding minimum number of taps.
      // Take care of the boundaries.
      int leftmost = Math.max(i - A[i], 0);
      int rightmost = Math.min(i + A[i], n);
      for (int j = leftmost; j <= rightmost; j++) {
        // The minimum number of taps to water [0, i] is the smaller of
        // dp[j] or dp[leftmost] plus the current tap.
        dp[j] = Math.min(dp[j], dp[leftmost] + 1);
      }
    }

    return (dp[n] < n + 2) ? dp[n] : -1;
  }

  // For testing.
  @SuppressWarnings("unused")
  public static void main(String[] args) {
    MinimumNumberOfTapsToOpenToWaterGarden solution = new MinimumNumberOfTapsToOpenToWaterGarden();

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
