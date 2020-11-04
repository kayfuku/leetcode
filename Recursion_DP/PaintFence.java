// 
// Author: 
// Date  : July 4, 2019

package leetcode;

public class PaintFence {
	// fields here.
	// private int count;

	public PaintFence() {
		// Initialization here.
		// this.count = 0;
	}

	// Dynamic Programming (Bottom Up)
	//
	// Counting ways.
	// The count can be determined based only on the last two counts.
	// => Bottom up dynamic programming
	//
	// If you are painting the ith post, you have two options:
	//
	// 1. make it different color as i-1th post
	// 2. make it same color as i-1th post (if you are allowed!)
	//
	// simply add these for your answer:
	//
	// num_ways(i) = num_ways_diff(i) + num_ways_same(i)
	//
	// Now just think of how to calculate each of those functions.
	// The first one is easy. If you are painting the ith post, how many ways can
	// you paint it to make it different from the i-1 th post? k-1.
	//
	// num_ways_diff(i) = num_ways(i-1) * (k-1)
	//
	// The second one is hard, but not so hard when you think about it.
	// If you are painting the ith post, how many ways can you paint it to make it
	// the same as the i-1th post? At first, we should think the answer is 1 -- it
	// must be whatever color the last one was.
	//
	// num_ways_same(i) = num_ways(i-1) * 1
	//
	// But no! This will fail in the cases where painting the last post the same
	// results in three adjacent posts of the same color! We need to consider ONLY
	// the cases where the last two colors were different. But we can do that!
	//
	// num_ways_diff(i-1) <- all the cases where the i-1th and i-2th are different.
	//
	// THESE are the cases where can just plop the same color to the end, and no
	// longer worry about causing three in a row to be the same.
	//
	// num_ways_same(i) = num_ways_diff(i-1) * 1 <= Point!
	//
	// We sum these for our answer, like I said before:
	//
	// num_ways(i)
	// = num_ways_diff(i) + num_ways_same(i)
	// = num_ways(i-1) * (k-1) + num_ways_diff(i-1)
	//
	// We know how to compute num_ways_diff, so we can substitute:
	// num_ways(i) = num_ways(i-1) * (k-1) + num_ways(i-2) * (k-1)
	//
	// We can even simplify a little more:
	//
	// num_ways(i) = (num_ways(i-1) + num_ways(i-2)) * (k-1)
	//
	// As a note, trying to intuitively understand that last line is impossible. If
	// you think you understand it intuitively, you are fooling yourself. Only the
	// original equation makes intuitive sense.
	// Once you have this, the code is trivial (but overall, this problem is not an
	// easy problem, despite the
	// leetcode tag!):
	// https://leetcode.com/problems/paint-fence/discuss/178010/The-only-solution-you-need-to-read
	//
	// O(N) time. O(1) space.
	//
	// Author: nm2812 + kei
	// Date : July 4, 2019, October 22, 2020
	public int numWays(int n, int k) {
		if (n == 0) {
			// There are no posts, there are no ways to paint them.
			return 0;
		}
		if (n == 1) {
			// There is only one post, there are k ways to paint it
			return k;
		}
		if (n == 2) {
			// There are only two posts, you can't make a triplet, so you
			// are free to paint however you want.
			// first post, k options. second post, k options
			return k * k;
		}

		int dp[] = new int[n + 1];
		dp[0] = 0;
		dp[1] = k;
		dp[2] = k * k;
		for (int i = 3; i <= n; i++) {
			// the recursive formula that we derived
			dp[i] = (dp[i - 1] + dp[i - 2]) * (k - 1);
		}
		return dp[n];
	}

	// R3
	public int numWaysR3(int n, int k) {
		if (n == 0) {
			return 0;
		}
		if (n == 1) {
			return k;
		}
		if (n == 2) {
			return k * k;
		}

		int[] dp = new int[n + 1];
		dp[0] = 0;
		dp[1] = k;
		dp[2] = k * k;
		for (int i = 3; i < n; i++) {
			dp[i] = (dp[i - 1] + dp[i - 2]) * (k - 1);
		}

		return dp[n];
	}

	// For testing.
	public static void main(String[] args) {
		PaintFence solution = new PaintFence();

		// Test arguments.
		// int num = 24;
		// int target = 2;
		// solution.getInt(num, target);

	}

}
