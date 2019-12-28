// 
// Author: 
// Date  : July 13, 2019

package leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CombinationSum {
	// fields and classes here. 
	//private int count;

	public CombinationSum() {
		// Initialization here. 
		//this.count = 0;
	}

	// Accepted. 
	// https://leetcode.com/problems/subsets/discuss/27281/A-general-approach-to-backtracking-questions-in-Java-(Subsets-Permutations-Combination-Sum-Palindrome-Partitioning)
	// Author: issac3 + luckygxf + kei
	// Date  : July 13, 2019
	public List<List<Integer>> combinationSum(int[] candidates, int target) {
		List<List<Integer>> ans = new ArrayList<>();
		// Sorting is necessary because, with nums[i] <= remain in the for loop, 
		// we do not have to check the rest of the candidates, 
		// which makes it much faster. 
		Arrays.sort(candidates); 

		List<Integer> tempList = new ArrayList<>();
		backtrack(ans, tempList, candidates, 0, target);

		return ans;
	}
	private void backtrack(List<List<Integer>> ans, List<Integer> tempList, int[] nums, int start, int remain) {
		// Not necessary. If you write '&& nums[i] <= remain' in the for loop. 
//		if (remain < 0) {
//			return;
//		}
		if (remain == 0) {
			// Pick the fruit!
			ans.add(new ArrayList<>(tempList));
			return;
		}

		// i starts from 'start' because the elements prior to the 'start' index has been
		// already considered.  
		// In other words, one sorted order of the elements is good enough for combinations.
		// If the nums[i] > remain, no need to proceed because nums array is sorted.  
		// Do not make a mistake in the condition! Not num[i] > remain. 
		for (int i = start; i < nums.length && nums[i] <= remain; i++) {
			tempList.add(nums[i]);
			// Pass in 'i' because we can reuse same elements. 
			// Not 'i + 1'!
			backtrack(ans, tempList, nums, i, remain - nums[i]);
			tempList.remove(tempList.size() - 1);
		}
	}



	// Review. 
	public List<List<Integer>> combinationSumR(int[] candidates, int target) {
		List<List<Integer>> ret = new ArrayList<>();
		List<Integer> tempList = new ArrayList<>();
		Arrays.sort(candidates);

		combinationSumR(tempList, candidates, ret, 0, target);
		return ret;	
	}
	private void combinationSumR(List<Integer> tempList, int[] nums, List<List<Integer>> ret, int start, int remain) {
		// base case
		if (remain == 0) {
			ret.add(new ArrayList<>(tempList));
			return;
		}

		for (int i = start; i < nums.length && nums[i] <= remain; i++) {
			tempList.add(nums[i]);
			combinationSumR(tempList, nums, ret, i, remain - nums[i]);
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
		int[] nums = new int[]{ 2, 3, 5 };
		int target = 8;
		System.out.println(solution.combinationSum(nums, target)); // [[2, 2, 2, 2], [2, 3, 3], [3, 5]]

		nums = new int[]{ 8, 7, 4, 3 };
		target = 11;
		System.out.println(solution.combinationSum(nums, target)); // [[3, 4, 4], [3, 8], [4, 7]]


	}

}















