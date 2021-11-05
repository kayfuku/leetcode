// 
// Author: 
// Date  : July 13, 2019

package leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CombinationSum {
	// fields and classes here.
	// private int count;

	public CombinationSum() {
		// Initialization here.
		// this.count = 0;
	}

	// Accepted.
	// https://leetcode.com/problems/subsets/discuss/27281/A-general-approach-to-backtracking-questions-in-Java-(Subsets-Permutations-Combination-Sum-Palindrome-Partitioning)
	// Author: issac3 + luckygxf + kei
	// Date : July 13, 2019
	private List<List<Integer>> ans = new ArrayList<>();

	public List<List<Integer>> combinationSum(int[] candidates, int target) {
		// Sorting is necessary because, with nums[i] <= remain in the for loop,
		// we do not have to check the rest of the candidates,
		// which makes it much faster.
		Arrays.sort(candidates);

		List<Integer> tempList = new ArrayList<>();
		backtrack(tempList, candidates, 0, target);

		return ans;
	}

	private void backtrack(List<Integer> tempList, int[] nums, int start, int remain) {
		// Not necessary. If you write '&& nums[i] <= remain' in the for loop.
		// if (remain < 0) {
		// return;
		// }
		if (remain == 0) {
			// Pick the fruit!
			ans.add(new ArrayList<>(tempList));
			return;
		}

		// i starts from 'start' because the elements prior to the 'start' index has
		// been already considered including duplicates.
		// i does not start from 0 because we consider each element in order.
		// In other words, one sorted order of the elements is good enough for
		// combinations.
		// If the nums[i] > remain, no need to proceed because nums array is sorted.
		// Do not make a mistake in the condition! Not num[i] > remain.
		for (int i = start; i < nums.length && nums[i] <= remain; i++) {
			tempList.add(nums[i]);
			// Pass in 'i' because we can reuse same elements.
			// Not 'i + 1'!
			backtrack(tempList, nums, i, remain - nums[i]);
			tempList.remove(tempList.size() - 1);
		}
	}

	// // NG! Without 'start', it gets wrong answers.
	// public List<List<Integer>> combinationSum2(int[] candidates, int target) {
	// List<List<Integer>> ans = new ArrayList<>();
	// // Sorting is necessary because, with nums[i] <= remain in the for loop,
	// // we do not have to check the rest of the candidates,
	// // which makes it much faster.
	// Arrays.sort(candidates);

	// List<Integer> tempList = new ArrayList<>();
	// backtrack2(ans, tempList, candidates, target);

	// return ans;
	// }

	// private void backtrack2(List<List<Integer>> ans, List<Integer> tempList,
	// int[] nums, int remain) {
	// // Not necessary. If you write '&& nums[i] <= remain' in the for loop.
	// // if (remain < 0) {
	// // return;
	// // }
	// if (remain == 0) {
	// // Pick the fruit!
	// ans.add(new ArrayList<>(tempList));
	// return;
	// }

	// // i starts from 'start' because the elements prior to the 'start' index has
	// // been
	// // already considered.
	// // In other words, one sorted order of the elements is good enough for
	// // combinations.
	// // If the nums[i] > remain, no need to proceed because nums array is sorted.
	// // Do not make a mistake in the condition! Not num[i] > remain.
	// for (int i = 0; i < nums.length && nums[i] <= remain; i++) {
	// tempList.add(nums[i]);
	// // Pass in 'i' because we can reuse same elements.
	// // Not 'i + 1'!
	// backtrack2(ans, tempList, nums, remain - nums[i]);
	// tempList.remove(tempList.size() - 1);
	// }
	// }

	// Review.
	private List<List<Integer>> ret = new ArrayList<>();

	public List<List<Integer>> combinationSumR(int[] candidates, int target) {
		Arrays.sort(candidates);
		List<Integer> tempList = new ArrayList<>();
		backtrackR(candidates, 0, tempList, target);
		return ret;
	}

	private void backtrackR(int[] nums, int start, List<Integer> tempList, int remainder) {
		if (remainder == 0) {
			ret.add(new ArrayList<>(tempList));
			return;
		}

		for (int i = start; i < nums.length && nums[i] <= remainder; i++) {
			tempList.add(nums[i]);
			backtrackR(nums, i, tempList, remainder - nums[i]);
			tempList.remove(tempList.size() - 1);
		}
	}

	// For testing.
	public static void main(String[] args) {
		CombinationSum solution = new CombinationSum();

		// Test arguments.
		// int num = 24;
		// int target = 2;
		// solution.getInt(num, target);
		int[] nums = new int[] { 2, 3, 5 };
		int target = 8;
		System.out.println(solution.combinationSum(nums, target)); // [[2, 2, 2, 2], [2, 3, 3], [3, 5]]

		nums = new int[] { 8, 7, 4, 3 };
		target = 11;
		System.out.println(solution.combinationSum(nums, target)); // [[3, 4, 4], [3, 8], [4, 7]]

	}

}
