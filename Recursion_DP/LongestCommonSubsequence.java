// 
// Author: 
// Date  : 

package leetcode;

public class LongestCommonSubsequence {
	// fields and classes here. 
	//private int count;

	public LongestCommonSubsequence() {
		// Initialization here. 
		//this.count = 0;

	}


	
	// Dynamic Programming. 
	// https://leetcode.com/problems/longest-common-subsequence/discuss/351689/Java-Two-DP-codes-of-O(mn)-and-O(min(m-n))-spaces-w-picture-and-analysis
	// 2d array dp stores the longest common sequence (LCS) so far. 
	// Iterating through the 2d array, I compare the two sequences, one is the sequence until i-th character
	// of the first string and the other is the sequence until j-th character of the second string. 
	// For each iteration, I check if the i-th character of the first sequence and j-th character of 
	// the second sequence are the same. 
	// 
	// If two sequences end in the same character, the LCS contains that character. 
	// If two sequences have different last character, the length of the LCS is either the length of the 
	// LCS we get by dropping the last character of the first sequence or the last character of the second 
	// sequence. I take whichever is larger. 
	// 
	// In other words:
	// If the characters are not the same, then take a larger value of above or left because 
	// dp already stores the LCS so far.
	// If the characters are the same, then add 1 to the LCS that is one step before of both sequences. 
	// 
	// Case 1: dp[i][j] = max(dp[i][j - 1], dp[i - 1][j]) (if x_i ≠ y_j)
	// Case 2: dp[i][j] = dp[i - 1][j - 1] + 1            (if x_i = y_j)
	// 
	// dp:  j 0 a c e
	//    i 
	//    0   0 0 0 0 
	//    a   0 1 1 1
	//    b   0 1 1 1 
	//    c   0 1 2 2
	//    d   0 1 2 2
	//    e   0 1 2 3
	// 
	// Author: rock + kei (AC)
	// Date  : August 23, 2019
	public int longestCommonSubsequence(String s1, String s2) {
		int m = s1.length();
		int n = s2.length();
		// I need additional row and column. 
		int[][] dp = new int[m + 1][n + 1];
		
		// The first row and column is 0. 
		for (int i = 1; i < m + 1; i++) {
			for (int j = 1; j < n + 1; j++) {
				// Index of the strings is 0 to len - 1;
				if (s1.charAt(i - 1) == s2.charAt(j - 1)) {
					// The characters are the same. 
					dp[i][j] = 1 + dp[i - 1][j - 1];
				} else {
					// 
					dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
				}
			}
		}
			
		// Return the bottom right value. 
		return dp[m][n];
	}







	// For testing. 
	public static void main(String[] args) {
		LongestCommonSubsequence solution = new LongestCommonSubsequence();

		// Test arguments. 
		// int num = 24;
		// int target = 2;
		// solution.getInt(num, target);



	}

}















