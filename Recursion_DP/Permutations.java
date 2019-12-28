// 
// Author: 
// Date  : July 11, 2019

package leetcode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class Permutations {
	// fields and classes here. 
	//private int count;

	public Permutations() {
		// Initialization here. 
		//this.count = 0;
	}


	// This solution is the best.  
	// Backtracking. Accepted. 
	// https://leetcode.com/problems/subsets/discuss/27281/A-general-approach-to-backtracking-questions-in-Java-(Subsets-Permutations-Combination-Sum-Palindrome-Partitioning)
	// O(N!) time, O(N!) space, where N is the nums length. 
	// Author: issac3 (LeetCode) + kei
	// Date  : July 11, 2019
	public List<List<Integer>> permute2(int[] nums) {
		List<List<Integer>> ans = new ArrayList<>();
		// Arrays.sort(nums); // Not necessary

		List<Integer> tempList = new ArrayList<>();
		backtrack2(ans, tempList, nums);

		return ans;
	}
	private void backtrack2(List<List<Integer>> ans, List<Integer> tempList, int [] nums){
		if (tempList.size() == nums.length) {
			// Permutation needs the same length as the original array. 
			// Now the list gets long enough. Pick the fruit. 
			// Make sure we have to create a new list for the result 
			// because tempList is always changing throughout the recursion!
			ans.add(new ArrayList<>(tempList));
			return;
		}

		// For each level (recursion stack), the tempList size grows by one, and 
		// the tempList gets every possible element at each level. 
		// In other words, new element is added to the tempList. 
		// In other words, the sublist starts with every possible element. 
		for (int i = 0; i < nums.length; i++) { 
			if (tempList.contains(nums[i])) {
				// Element already exists, skip. 
				// I don't have to add the element that I've already added. 
				continue; 
			}
			tempList.add(nums[i]);
			backtrack2(ans, tempList, nums);
			// Undo the adding when we go back to the previous recursion stack. 
			tempList.remove(tempList.size() - 1);
		}
	}


	// Backtracking. 
	// O(?) time, https://leetcode.com/problems/permutations/solution/
	// O(N!) space, since it has to keep N! solutions. 
	// Author: @liaison and @andvary + kei
	// Date  : July 11, 2019
	public List<List<Integer>> permute(int[] nums) {
		List<List<Integer>> output = new LinkedList<>();

		// Convert nums into list since the output is a list of lists. 
		List<Integer> numsList = new ArrayList<Integer>();
		for (int num : nums) {
			numsList.add(num);
		}

		backtrack(numsList, 0, output);
		return output;
	}
	private void backtrack(List<Integer> numsList, int i, List<List<Integer>> output) {
		if (i == numsList.size()) {
			// All integers are used up. 
			output.add(new ArrayList<Integer>(numsList));
			return;
		}

		for (int j = i; j < numsList.size(); j++) {
			// Swap i-th and j-th. 
			Collections.swap(numsList, i, j);
			// Use next integers to complete the permutations. 
			backtrack(numsList, i + 1, output);
			// Backtrack. While going up the recursion stack, we undo the swap. 
			Collections.swap(numsList, i, j);    	
		}

	}


	// Accepted. 
	// Author: kei
	// Date  : July 11, 2019
	public List<List<Integer>> permuteK(int[] nums) {
		List<List<Integer>> output = new LinkedList<>();

		List<Integer> prefix = new ArrayList<>(); 
		backtrackK(nums, prefix, 0, output);
		return output;
	}
	private void backtrackK(int[] nums, List<Integer> prefix, int i, List<List<Integer>> output) {
		if (i == nums.length) {
			output.add(new ArrayList<Integer>(prefix));
			return;
		}

		// Neighbors. 
		for (int j = i; j < nums.length; j++) {	    	
			// Swap i-th and j-th, and hold it as a new array, so that when we come back, 
			// we can start from the common 'nums'. 
			// If we do not create new array, then when we come back, the array will be 
			// the last swapped one, which is wrong. 
			int[] newNums = swap(nums, i, j);

			// Separate the 'prefix' and 'newPrefix' so that when we come back, 
			// we can start from the common 'prefix'. 
			List<Integer> newPrefix = new ArrayList<>(prefix);
			newPrefix.add(newNums[i]);

			// Move on to the next digit.  
			backtrackK(newNums, newPrefix, i + 1, output);

			// No need to undo the swap because we save the original array. 
		}
	}
	private int[] swap(int[] nums, int i, int j) {
		int[] newNums = new int[nums.length];
		int temp = nums[i];
		nums[i] = nums[j];
		nums[j] = temp;
		System.arraycopy(nums, 0, newNums, 0, nums.length);
		return newNums;
	}


	// Review. 
	public List<List<Integer>> permuteR(int[] nums) {
		List<List<Integer>> res = new ArrayList<>();
		List<Integer> tempList = new ArrayList<>();
		permuteR(tempList, nums, res);
		return res;
	}
	private void permuteR(List<Integer> tempList, int[] nums, List<List<Integer>> res) {
		// base case
		if (tempList.size() == nums.length) {
			res.add(new ArrayList<>(tempList));
			return;
		}	

		for (int i = 0; i < nums.length; i++) {
			if (tempList.contains(nums[i])) {
				continue;
			}
			tempList.add(nums[i]);
			permuteR(tempList, nums, res);
			tempList.remove(tempList.size() - 1);
		}
	}


	// For testing. 
	public static void main(String[] args) {
		Permutations solution = new Permutations();

		// Test arguments. 
		// int num = 24;
		// int target = 2;
		// solution.getInt(num, target);

		int[] nums = new int[]{ 1, 2, 3 };
		System.out.println(solution.permuteK(nums));



	}

}















