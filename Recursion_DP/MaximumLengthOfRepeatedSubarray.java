package leetcode;

public class MaximumLengthOfRepeatedSubarray {
  // fields and classes here.
  // private int count;

  public MaximumLengthOfRepeatedSubarray() {
    // Initialization here.
    // this.count = 0;

  }

  // 3. DP
  // To find a common subarray, we can use 2d matrix to store the common subarray
  // length.
  //
  // we put the first string vertically and the second string horizontally, and
  // iterate through the matrix from the top left to the bottom right, which means
  // that we append character one by one from the first character in the first
  // string when interating horizontally, and we append character one by one from
  // the first character in the second string when interating vertically.
  //
  // If we find the same character, then we add 1 to the value of the previous row
  // and the previous column and save it in the current square. What that means is
  // that if there is a common character, then the common subarray length
  // increases by one. Written in equation,
  //
  // dp[i][j] = dp[i - 1][j - 1] + 1 (if nums1[i] == nums2[j])
  //
  // Our answer will be,
  //
  // max(dp[i][j])
  //
  // nums1 = "abcba", nums2 = "cbadg"
  // dp:
  // * j 0 c b a d g
  // i * * * * * * *
  // 0 * 0 0 0 0 0 0
  // a * 0 0 0 1 0 0
  // b * 0 0 1 0 0 0
  // c * 0 1 0 0 0 0
  // b * 0 0 2 0 0 0
  // a * 0 0 0 3 0 0
  //
  // dp:
  // * j 0 a b a b a
  // i * * * * * * *
  // 0 * 0 0 0 0 0 0
  // a * 0 1 0 1 0 1
  // b * 0 0 2 0 2 0
  // a * 0 1 0 3 0 3
  // b * 0 0 2 0 4 0
  // a * 0 1 0 3 0 5
  //
  // O(MN) time and space, where M is nums1 length and N is nums2 length.
  // Author: leetcode + kei
  // Date : June 1, 2021
  public int findLength(int[] num1, int[] num2) {
    int m = num1.length;
    int n = num2.length;
    int maxlen = 0;
    int[][] dp = new int[m + 1][n + 1];
    //
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

    // If we need return the longest common subarray, then save the index as a
    // lastIdx when we find maxlen. Then, using the lastIdx and maxlen, we can slice
    // the num1 (or num2) to get the longest common subarray.
    // (leetcode_contest/IndeedPrep.java)

    return maxlen;
  }

  // Review
  public int findLengthR(int[] num1, int[] num2) {
    int m = num1.length;
    int n = num2.length;
    int[][] dp = new int[m + 1][n + 1];

    int max = 0;
    for (int i = 1; i < m + 1; i++) {
      for (int j = 1; j < n + 1; j++) {
        if (num1[i - 1] == num2[j - 1]) {
          dp[i][j] = dp[i - 1][j - 1] + 1;
          max = Math.max(max, dp[i][j]);
        }
      }
    }

    return max;
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
