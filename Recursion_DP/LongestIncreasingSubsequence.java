// 
// Author: 
// Date  : July 1, 2019

package leetcode;

import java.util.Arrays;

public class LongestIncreasingSubsequence {
	// fields here.
	// private int count;

	public LongestIncreasingSubsequence() {
		// Initialization here.
		// this.count = 0;
	}

	// 1. Recursion. Time Limit Exceeded.
	// Check all of the possible cases by Select/NotSelect technique.
	// O(2^N) time, where N is the total number of elements in the array.
	// O(N) space because of the recursion stack.
	public int lengthOfLIS(int[] nums) {
		return lengthOfLIS(nums, -1, 0);
	}

	private int lengthOfLIS(int[] nums, int prevIdx, int curIdx) {
		if (curIdx == nums.length) {
			return 0;
		}

		int taken = 0;
		if (prevIdx < 0 || nums[curIdx] > nums[prevIdx]) {
			taken = 1 + lengthOfLIS(nums, curIdx, curIdx + 1);
		}
		int notTaken = lengthOfLIS(nums, prevIdx, curIdx + 1);

		return Math.max(taken, notTaken);
	}

	// 2. Recursion with Memoization. (Top Down DP)
	// The result is the same if the input of the recursion stack is the same.
	// So using memo makes it faster.
	// O(n^2) time, O(n^2) space.
	public int lengthOfLIS2(int[] nums) {
		int[][] memo = new int[nums.length + 1][nums.length];
		for (int[] arr : memo) {
			Arrays.fill(arr, -1);
		}

		return lengthOfLIS2(nums, -1, 0, memo);
	}

	public int lengthOfLIS2(int[] nums, int prevIdx, int curIdx, int[][] memo) {
		if (curIdx == nums.length) {
			return 0;
		}

		if (memo[prevIdx + 1][curIdx] >= 0) {
			return memo[prevIdx + 1][curIdx];
		}

		int taken = 0;
		if (prevIdx < 0 || nums[prevIdx] < nums[curIdx]) {
			taken = 1 + lengthOfLIS2(nums, curIdx, curIdx + 1, memo);
		}
		int notTaken = lengthOfLIS2(nums, prevIdx, curIdx + 1, memo);

		memo[prevIdx + 1][curIdx] = Math.max(taken, notTaken);

		return memo[prevIdx + 1][curIdx];
	}

	// 3. Dynamic Programming. (Bottom Up) The review at the bottom is good, too.
	//
	// The longest increasing subsequence up to the i-th index is independent of
	// the elements coming later on.
	//
	// So, this problem seems to have optimal substructure, and subproblems are
	// overlapping and independent. (It does not seem to have greedy choice
	// property.)<= Need to check.
	//
	// So, I think I can use Dynamic Programming.
	// I store the max so far in the 'dp' array at the i-th element. And I can use
	// that array to get the max at the current index i.
	// For example, when the current index is 4, nums[i] is equal to 3.
	// And while iterating through the dp array from index 0 to right before i, if
	// nums[i] is bigger than the element that I am iterating in, then add 1 to the
	// max at that point, and keep track of the max.
	// Put the max in the dp array at the index i.
	// ===i: 0 1 2 3 4 5 6
	// nums:10 9 2 5 3 7 101
	//
	// ==dp: 1 1 1 2 2
	// dp[3] = dp[2] + 1
	//
	// dp[i] = max(dp[j] + 1) (0 ≤ j < i)
	// LIS = max(dp[i]) (0 ≤ i < n)
	//
	// O(N^2) time, O(N) space.
	//
	// Author: leetcode + kei
	// Date : July 1, 2019, October 22, 2020
	public int lengthOfLIS3(int[] nums) {
		if (nums == null || nums.length == 0) {
			return 0;
		}

		// To hold the max values (longest) so far (index 0 to i).
		// dp[i] = max(dp[j] + 1) (0 ≤ j < i)
		int[] dp = new int[nums.length];

		dp[0] = 1;
		// Since 'nums' is unsorted, we need to keep track of max through
		// the 'dp' array.
		int longest = 1;
		// Fill up the dp assuming that nums[i] is the last element of the sequence.
		// i starts from 1 because no need to check when the list is just one element.
		for (int i = 1; i < dp.length; i++) {
			// This must be 0, not Integer.MIN_VALUE!
			// You must think about the case not updating the maxVal.
			int maxVal = 0;
			// Find max iterating through until i.
			for (int j = 0; j < i; j++) {
				if (nums[j] < nums[i]) {
					// Since nums[i] meets the requirement (increasing order),
					// we can consider this is a candidate subsequence.
					// nums[j] is the last element of that sequence.
					// we count up the longest length so far (dp[j] + 1) later because
					// dp[j] holds the longest length at the point of j.
					// So, dp[j] + 1 is the length of that sequence plus nums[i].
					// We want the max among 0 <= j < i.
					maxVal = Math.max(maxVal, dp[j]);
				}
			}
			// Put the max in the dp.
			// maxVal + 1 is the maximum length of the sequence so far plus nums[i].
			dp[i] = maxVal + 1;

			// Max values among all dp[i] is the answer.
			longest = Math.max(longest, dp[i]);
		}

		return longest;
	}

	// 4. Dynamic Programming with Binary Search. No need to review.
	//
	// O(NlogN) time, where N is the total number of elements in nums,
	// because Binary Search takes O(logN) time and is called N times.
	// O(N) space. dp array of size N is used.
	public int lengthOfLIS4(int[] nums) {
		int[] dp = new int[nums.length];
		int len = 0;
		for (int num : nums) {
			// Using Binary Search to add the elements, we will get a list in ascending
			// order.
			// Find the index where 'num' could be in 'dp' array.
			// Arrays.binarySearch(array, start, end, target)
			// Arrays.binarySearch returns:
			// the index of the target if the target is in the list,
			// -('would-be-index') - 1 if the target is not in the list.
			int i = Arrays.binarySearch(dp, 0, len, num);
			if (i < 0) {
				// The target is not in the list.
				// Get the index where the element would be.
				i = -(i + 1);
			}
			dp[i] = num;

			if (i == len) {
				// 'num' would be the last element, which is the
				// largest in the 'dp'. Increment the length.
				// 'len' serves as keeping track of max length as well.
				len++;
			}
		}

		return len;
	}

	// Review.
	// nums:10 9 2 5 ...
	// ==dp: 1 1 1 2
	// longest: 1
	//
	// Author: kei (AC)
	// Date : August 13, 2019, October 22, 2020
	public int lengthOfLIS_R(int[] nums) {
		// corner
		if (nums == null || nums.length == 0) {
			return 0;
		}

		// For storing max so far.
		int[] dp = new int[nums.length];

		int longest = 0;
		for (int i = 0; i < dp.length; i++) {
			int maxVal = 1;
			for (int j = 0; j < i; j++) {
				if (nums[j] < nums[i]) {
					maxVal = Math.max(maxVal, dp[j] + 1);
				}
			}
			dp[i] = maxVal;
			longest = Math.max(longest, dp[i]);
		}

		return longest;
	}

	// R3
	public int lengthOfLISR3(int[] nums) {
		if (nums == null || nums.length == 0) {
			return 0;
		}
		int[] dp = new int[nums.length];
		int max = 0;
		for (int i = 0; i < nums.length; i++) {
			int maxValue = 1;
			for (int j = 0; j < i; j++) {
				if (nums[j] < nums[i]) {
					maxValue = Math.max(maxValue, dp[j] + 1);
				}
			}
			dp[i] = maxValue;
			max = Math.max(max, dp[i]);
		}

		return max;
	}

	// For testing.
	@SuppressWarnings("unused")
	public static void main(String[] args) {
		LongestIncreasingSubsequence solution = new LongestIncreasingSubsequence();

		// Test arguments.
		// int num = 24;
		// int target = 2;
		// solution.getInt(num, target);

	}

}
