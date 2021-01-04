// 
// Author: 
// Date  : July 4, 2019

package leetcode;

public class UniquePaths {
	// fields here.
	// private int count;

	public UniquePaths() {
		// Initialization here.
		// this.count = 0;
	}

	// 1. Math
	// https://leetcode.com/problems/unique-paths/discuss/23129/0ms-Java-10-line-code
	// n!/(p!q!r!...)
	// ((m - 1) + (n - 1))!/(m - 1)!(n - 1)!
	// e.g. 6!/(4!2!)
	// 6 * 5
	// 4 * 3 * 2 * 1
	// 2 * 1
	//
	// O(m) or O(n) time. O(1) space.
	public int uniquePaths2(int m, int n) {
		int smaller = (m > n) ? n - 1 : m - 1;
		int numerator = m + n - 2;
		long result = 1;
		// Divide by the smaller number first so that the 'result' is divisible?
		for (int p = 1; p <= smaller; p++) {
			// for (int p = smaller; p >= 1; p--) { // NG!
			result *= numerator;
			numerator--;
			result /= p;
		}

		return (int) result;
	}

	// 2. Dynamic Programming.
	// The number of ways to reach [i, j] is (the num of ways to reach [i, j - 1]) +
	// (the num of ways to reach [i - 1, j]).
	// https://leetcode.com/problems/unique-paths/discuss/22980/Clean-and-simple-DP-java
	// O(mn) time & space.
	public int uniquePaths(int m, int n) {
		int[][] dp = new int[n][m];
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				if (i == 0 || j == 0) {
					// The first row and the first column should be all 1.
					dp[i][j] = 1;
				} else {
					// Other squares are the sum of the value in the square above and the value
					// in the square to the left.
					dp[i][j] = dp[i][j - 1] + dp[i - 1][j];
				}
			}
		}

		return dp[n - 1][m - 1];
	}

	// Review.
	public int uniquePathsR(int m, int n) {
		if (n == 0 || m == 0) {
			return 0;
		}

		int[][] dp = new int[n][m];
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				if (i == 0 || j == 0) {
					dp[i][j] = 1;
				} else {
					dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
				}
			}
		}

		return dp[n - 1][m - 1];
	}

	// For testing.
	public static void main(String[] args) {
		UniquePaths solution = new UniquePaths();

		// Test arguments.
		// int num = 24;
		// int target = 2;
		// solution.getInt(num, target);

	}

}
