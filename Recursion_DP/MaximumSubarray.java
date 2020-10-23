// 
// Author: 
// Date  : July 4, 2019

package leetcode;

import java.util.*;

public class MaximumSubarray {
	// fields here.
	// private int count;

	public MaximumSubarray() {
		// Initialization here.
		// this.count = 0;
	}

	// 1. Greedy Algorithm. (Also ref. Review below)
	//
	// The max up to i-th element is calculated by using the max up to the (i -
	// 1)-th element and i-th element. We just need to return the maximum sum.
	// Pick the locally optimal solution at each step, and that will lead to the
	// globally optimal solution. So, this problem seems to have optimal
	// substructure and also have a greedy choice property. So, I think I can use
	// Greedy Algorithm.
	//
	// Iterating through the array, for each iteration, I check if I can get bigger
	// sum when I add the current elem nums[i].
	//
	// curMaxSum_i = max(curMaxSum_i-1 + nums_i, nums_i)
	// maxSum = max(curMaxSum_i)
	//
	// O(N) time, where N is the total number of elements in the array, since it's
	// one pass along the array.
	// O(1) space since constant space is used.
	public int maxSubArray(int[] nums) {
		if (nums.length == 1) {
			return nums[0];
		}
		// For keeping track of contiguous subarray max.
		// Do not set it to 0 because the elements can be negative!
		// Do not set it to Integer.MIN_VALUE because curMaxSum + nums[i] could
		// overflow!
		int curMaxSum = nums[0];
		// For keeping track of global max.
		// Do not set it to 0 because the elements can be negative!
		// Do not set it to Integer.MIN_VALUE because if nums only has one elem
		// maxSum won't be updated, or because if the nums is [-1, -2],
		// then it returns -2, not -1.
		int maxSum = nums[0];
		int start = 0, end = 0; // optional
		// i starts from 1, not 0! because 'curMaxSum' is set to nums[0] and we add
		// nums[i] to it.
		for (int i = 1; i < nums.length; i++) {
			// Check if I can get bigger sum when I add the current elem nums[i].
			// 'curMaxSum + nums[i]' is for the sum of contiguous subarray.
			// 'nums[i]' is the restart of contiguous subarray because it is bigger than
			// the previous contiguous subarray.
			// curMaxSum = Math.max(curMaxSum + nums[i], nums[i]);
			if (nums[i] > curMaxSum + nums[i]) {
				curMaxSum = nums[i];
				start = i; // optional
			} else {
				curMaxSum = curMaxSum + nums[i];
			}

			// I can keep track of global sum here.
			// maxSum = Math.max(maxSum, curMaxSum);
			if (curMaxSum > maxSum) {
				maxSum = curMaxSum;
				end = i; // optional
			}
		}

		System.out.println("start: " + start + " end: " + end);
		return maxSum;
	}

	// 2. Divide and Conquer. No need to review.
	// ** Review
	// https://leetcode.com/problems/maximum-subarray/solution/
	// O(NlogN) time.
	// O(logN) space to keep the recursion stack.
	public int maxSubArray2(int[] nums) {

		return helper(nums, 0, nums.length - 1);
	}

	private int helper(int[] nums, int left, int right) {
		if (left == right) {
			return nums[left];
		}

		int mid = (left + right) / 2;

		int leftSum = helper(nums, left, mid);
		int rightSum = helper(nums, mid + 1, right);
		int crossSum = crossSum(nums, left, right, mid);

		return Math.max(Math.max(leftSum, rightSum), crossSum);
	}

	private int crossSum(int[] nums, int left, int right, int mid) {
		if (left == right) {
			return nums[left];
		}

		// From mid to left
		int leftSubsum = Integer.MIN_VALUE;
		int curSum = 0;
		for (int i = mid; i >= left; i--) {
			// for (int i = left; i <= mid; i++) { // NG! why??
			curSum += nums[i];
			leftSubsum = Math.max(leftSubsum, curSum);
		}

		// From mid + 1 to right
		int rightSubsum = Integer.MIN_VALUE;
		curSum = 0;
		for (int i = mid + 1; i <= right; i++) {
			curSum += nums[i];
			rightSubsum = Math.max(rightSubsum, curSum);
		}

		return leftSubsum + rightSubsum;
	}

	// Review.
	public int maxSubArrayR(int[] nums) {
		// corner
		if (nums == null) {
			return 0;
		}

		int maxSum = nums[0], contigSum = nums[0];
		int start = 0, end = 0;
		for (int i = 1; i < nums.length; i++) {
			contigSum += nums[i];
			if (nums[i] > contigSum) {
				contigSum = nums[i];
				start = i;
			}

			if (contigSum > maxSum) {
				maxSum = contigSum;
				end = i;
			}
		}

		return maxSum;
	}

	// For testing.
	public static void main(String[] args) {
		MaximumSubarray solution = new MaximumSubarray();

		// Test arguments.
		// int num = 24;
		// int target = 2;
		// solution.getInt(num, target);
		// int[] nums = new int[] { -2, 1, -3, 4, -1, 2, 1, -5, 4 };
		int[] nums = new int[] { -1, -2 };
		System.out.println(solution.maxSubArray(nums));

	}

}
