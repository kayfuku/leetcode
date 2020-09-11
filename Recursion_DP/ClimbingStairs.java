// 
// Author: 
// Date  : May 30, 2019

package leetcode;


public class ClimbingStairs {

	// fields here. 
	//	private int count;


	public ClimbingStairs() {
		// Initialization here. 
		//		count = 0;
	}

	// other classes here. 

	
	// 1. Recursion. (TLE)
	// O(2^N) time, because of 2 recursion calls and n recursion stack. 
	// O(N) space.  
	public int climbStairs(int n) {
		return climbStairs(0, n);
	}
	private int climbStairs(int i, int n) {
		if (i > n) {
			return 0;
		}
		if (i == n) {
			return 1;
		}

		return climbStairs(i + 1, n) + climbStairs(i + 2, n);
	}

	// 2. Recursion with Memoization (Top Down Dynamic Programming). Accepted. 
	// O(N) time, O(N) space. 
	public int climbStairs2(int n) {
		int[] memo = new int[n + 1];
		return climbStairs2(0, n, memo);    	
	}
	private int climbStairs2(int i, int n, int[] memo) {
		if (i > n) {
			return 0;
		}
		if (i == n) {
			return 1;
		}

		if (memo[i] > 0) {
			return memo[i];
		}

		memo[i] = climbStairs2(i + 1, n, memo) + climbStairs2(i + 2, n, memo);

		return memo[i];
	}

	// 3. Bottom Up Dynamic Programming. 
	// The total number of ways to reach i-th is equal to the sum of 
	// the ways of reaching (i-1)-th step and the ways of reaching (i-2)-th step.
	// O(N) time, O(N) space. 
	public int climbStairs3(int n) {
		if (n <= 1) {
			return 1;
		}

		// The reason n + 1 is just match the index.
		int[] dp = new int[n + 1];
		dp[1] = 1;
		dp[2] = 2;

		// n >= 3
		for (int i = 3; i <= n; i++) {
			// It's the num of ways, so no need to add 1. 
			// Just pass on the num to the current.  
			dp[i] = dp[i - 1] + dp[i - 2];
		}

		return dp[n];
	}

	// 4. Iterative Fibonacci Number. 
	// If n is 1, the answer is 1. If n is 2, the answer is 2. 
	// O(N) time, O(1) space. 
	public int climbStairs4(int n) {
		if (n <= 1) {
			return 1;
		}
		if (n == 2) {
			return 2;
		}

		int secondToLast = 1;
		int last = 2;
		int next = 0;
		// You have to be able to explain why this continuing condition works!!
		// If n is 1, the answer is 1. If n is 2, the answer is 2. 
		// If n is 3, then we calculate using last two results. 
		for (int i = 3; i <= n; i++) {
			next = secondToLast + last;
			secondToLast = last;
			last = next;
		}

		return next;    	
	}

	// 4. Iterative Fibonacci Number.
	// Author: LeetCode
	public int climbStairs5(int n) {
		if (n == 1) {
			return 1;
		}
		int first = 1;
		int second = 2;
		for (int i = 3; i <= n; i++) {
			int third = first + second;
			first = second;
			second = third;
		}
		return second;
	}





	// For testing. 
	public static void main(String[] args) {
		ClimbingStairs solution = new ClimbingStairs();

		// Test arguments. 
		//	    int num = 24;
		//	    int target = 2;
		//	    solution.getInt(num, target);

		int n = 5;
		System.out.println(solution.climbStairs4(n)); // 8

		n = 2;
		System.out.println(solution.climbStairs4(n)); // 2


	}

}















