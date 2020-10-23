// 
// Author: 
// Date  : July 11, 2019

package leetcode;

import java.util.ArrayList;
import java.util.List;

public class Subsets {
	// fields and classes here.
	// private int count;

	public Subsets() {
		// Initialization here.
		// this.count = 0;
	}

	// Backtracking.
	// https://leetcode.com/problems/subsets/discuss/27281/A-general-approach-to-backtracking-questions-in-Java-(Subsets-Permutations-Combination-Sum-Palindrome-Partitioning)
	// O(N + (N - 1) + (N - 2) + ...)) = O(N^2) time, O(N^2) space.
	// Author: issac3 (LeetCode) + kei
	// Date : July 11, 2019
	public List<List<Integer>> subsets(int[] nums) {
		List<List<Integer>> ans = new ArrayList<>();
		// Arrays.sort(nums); // Not necessary.

		List<Integer> tempList = new ArrayList<>();
		backtrack(ans, tempList, nums, 0);

		return ans;
	}

	private void backtrack(List<List<Integer>> ans, List<Integer> tempList, int[] nums, int start) {
		// Unlike permutations, we add tempList to the result list
		// regardless of the length of tempList.
		// Make sure we have to create a new list for the result
		// because tempList is always changing throughout the recursion!
		ans.add(new ArrayList<>(tempList));

		// For each level, the size grows by one.
		// Prior to the 'start' index, those elements has been already used,
		// so start from 'start', which is the next possible element.
		// Unlike permutations, the tempList does not need to start with
		// every possible element because the order of the elements does not matter for
		// combinations/subsets.
		// What matters is what is in the tempList.
		for (int i = start; i < nums.length; i++) {
			tempList.add(nums[i]);
			// tempList has common prefix.
			// Start from (i+1)-th element and add it in the next recursion stack.
			backtrack(ans, tempList, nums, i + 1);
			// When we come back from lower stack, we undo the adding.
			tempList.remove(tempList.size() - 1);
		}
		// After i gets to the last element, the method returns (Base Case).
	}

	// Review.
	public List<List<Integer>> subsetsR(int[] nums) {
		List<List<Integer>> ret = new ArrayList<>();
		List<Integer> tempList = new ArrayList<>();

		subsetsR(tempList, nums, ret, 0);
		return ret;
	}

	private void subsetsR(List<Integer> tempList, int[] nums, List<List<Integer>> ret, int start) {
		ret.add(new ArrayList<>(tempList));

		for (int i = start; i < nums.length; i++) {
			tempList.add(nums[i]);
			subsetsR(tempList, nums, ret, i + 1);
			tempList.remove(tempList.size() - 1);
		}
	}

	// For testing.
	public static void main(String[] args) {
		Subsets solution = new Subsets();

		// Test arguments.
		// int num = 24;
		// int target = 2;
		// solution.getInt(num, target);
		int[] nums = new int[] { 1, 2, 3 };
		System.out.println(solution.subsets(nums));

	}

}
