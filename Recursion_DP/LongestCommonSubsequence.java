// 
// Author: 
// Date  : 

package leetcode;

public class LongestCommonSubsequence {
	// fields and classes here.
	// private int count;

	public LongestCommonSubsequence() {
		// Initialization here.
		// this.count = 0;

	}

	// Dynamic Programming.
	//
	// https://leetcode.com/problems/longest-common-subsequence/discuss/351689/Java-Two-DP-codes-of-O(mn)-and-O(min(m-n))-spaces-w-picture-and-analysis
	//
	// To find a longest common subsequence of two strings, we can use 2d matrix to
	// store the longest common subsequence length.
	//
	// Iterating through the 2d array, I compare the two sequences, one is the
	// sequence from the beginning to the i-th character of the first string and the
	// other is the sequence from the beginning to the j-th character of the second
	// string.
	//
	// For each iteration, I check if the i-th character of the first sequence and
	// the j-th character of the second sequence are the same.
	//
	// If the characters are the same, then the LCS contains that character.
	// If not the same, then the length of the LCS is
	// the larger one of whichever the length of the LCS we get by dropping the last
	// character of the first sequence or the last character of the second sequence.
	//
	// In other words:
	// If the characters are not the same, then take a larger value of previous row
	// or previous column because 2d array 'dp' already stores the LCS so far.
	// If the characters are the same, then add 1 to the LCS that is one step before
	// of both sequences.
	//
	// Written in equation,
	//
	// Case 1: dp[i][j] = max(dp[i][j - 1], dp[i - 1][j]) (if x_i ≠ y_j)
	// Case 2: dp[i][j] = dp[i - 1][j - 1] + 1 (if x_i = y_j)
	//
	// s1 = "abcde", s2 = "ace"
	// dp:
	// * j 0 a c e
	// i * * * * *
	// 0 * 0 0 0 0
	// a * 0 1 1 1
	// b * 0 1 1 1
	// c * 0 1 2 2
	// d * 0 1 2 2
	// e * 0 1 2 3
	//
	// O(MN) time and space, where M is s1 length and N is s2 length.
	//
	// Author: rock + kei
	// Date : August 23, 2019, May 28, 2021
	public int longestCommonSubsequence(String s1, String s2) {
		int m = s1.length();
		int n = s2.length();
		// To keep track of the longest common subsequence length.
		// We're gonna use the first row and column with all 0s.
		int[][] dp = new int[m + 1][n + 1];

		// The first row and column is 0.
		// StringBuilder lcs = new StringBuilder(); // To return the LCS
		for (int i = 1; i < m + 1; i++) {
			for (int j = 1; j < n + 1; j++) {
				// Be careful of index minus one to access original arrays.
				if (s1.charAt(i - 1) == s2.charAt(j - 1)) {
					// The characters are the same. This character is included in the LCS.
					// Add one to the previous result because we found a new valid subsequence.
					dp[i][j] = dp[i - 1][j - 1] + 1;
					// lcs.append(s1.charAt(i - 1)); // To return the LCS
				} else {
					// Take maximum of the results of either case where adding one character to s1
					// or s2.
					// Just inherit the previous result. No plus one.
					dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
				}
			}
		}
		// System.out.println(lcs);

		// Return the most bottom right value in the 2d array.
		return dp[m][n];
	}

	// Review.
	// Author: kei (AC)
	// Date : June 9, 2021
	public int longestCommonSubsequenceR(String s1, String s2) {
		int m = s1.length();
		int n = s2.length();
		int[][] dp = new int[m + 1][n + 1];

		int max = 0;
		for (int i = 1; i < m + 1; i++) {
			for (int j = 1; j < n + 1; j++) {
				if (s1.charAt(i - 1) == s2.charAt(j - 1)) {
					dp[i][j] = dp[i - 1][j - 1] + 1;
					max = Math.max(max, dp[i][j]);
				} else {
					dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
				}
			}
		}

		return max;
	}

	// For testing.
	public static void main(String[] args) {
		LongestCommonSubsequence solution = new LongestCommonSubsequence();

		// Test arguments.
		// int num = 24;
		// int target = 2;
		// solution.getInt(num, target);
		String text1 = "abcde";
		String text2 = "ace";
		System.out.println(solution.longestCommonSubsequence(text1, text2));

	}

}
