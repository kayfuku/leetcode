package leetcode;

public class MaximumLengthOfRepeatedSubarray {
  // fields and classes here.
  // private int count;

  public MaximumLengthOfRepeatedSubarray() {
    // Initialization here.
    // this.count = 0;

  }

  // 3. DP
  // O(MN) time and space, where M is num1 length and N is num2 length.
  // Author: leetcode + kei
  // Date : June 1, 2021
  public int findLength(int[] num1, int[] num2) {
    int m = num1.length;
    int n = num2.length;
    int maxlen = 0;
    int[][] dp = new int[m + 1][n + 1];
    for (int i = 1; i < m + 1; i++) {
      for (int j = 1; j < n + 1; j++) {
        // Be careful of index minus one to access original arrays.
        if (num1[i - 1] == num2[j - 1]) {
          // Add one to the immediate result.
          dp[i][j] = dp[i - 1][j - 1] + 1;
          maxlen = Math.max(maxlen, dp[i][j]);
        }
        // Do nothing if the characters are not the same because we are interested in
        // subarray, not subsequence.
        // Compare to LongestCommonSubsequence.java.
      }
    }
    return maxlen;
  }

  // For testing.
  @SuppressWarnings("unused")
  public static void main(String[] args) {
    MaximumLengthOfRepeatedSubarray solution = new MaximumLengthOfRepeatedSubarray();

    // Test arguments.
    // int num = 24;
    // int target = 2;
    // System.out.println(solution.getInt(num, target));

    System.out.println("\ndone.");
  }

}
