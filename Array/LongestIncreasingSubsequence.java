// 
// Author: 
// Date  : July 4, 2019

package leetcode;

import java.util.Arrays;

public class LongestIncreasingSubsequence {
	// fields here. 
//	private int count;
	
	public LongestIncreasingSubsequence() {
		// Initialization here. 
//		this.count = 0;
	}
		
	// 1. Recursion. Time Limit Exceeded. 
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
    
    
    // 3. Dynamic Programming. (Bottom Up DP) Accepted. 
    // The longest increasing subsequence up to the i-th index is independent of 
    // the elements coming later on. 
    // 
    // So, this problem seems to have optimal substructure, and subproblems are 
    // overlapping and independent. It does not seem to have greedy choice property. 
    // So, I think I can use Dynamic Programming. 
    // 
    // dp[i] = max(dp[j]) + 1 (0 ≤ j < i)
    // LIS = max(dp[i]) (0 ≤ i < n)
    // 
    // O(N^2) time, O(N) space. 
    public int lengthOfLIS3(int[] nums) {
    	if (nums.length == 0) {
			return 0;
		}
    	
    	int[] dp = new int[nums.length];
    	dp[0] = 1;
    	
    	int longest = 1; // Not 0!
    	for (int i = 1; i < dp.length; i++) {
    		// For up to i-th elem, find a max. 
			int maxVal = 1;
    		for (int j = 0; j < i; j++) {
				if (nums[i] > nums[j]) {
					// Since nums[i] meets the requirement (increasing order), 
					// we count up the longest length so far, (dp[j] + 1)
					// and take larger value. 
					maxVal = Math.max(maxVal, dp[j] + 1);
				}
			}
    		dp[i] = maxVal;
    		
    		longest = Math.max(longest, dp[i]);
		}
    	
    	return longest;
    }
    
    // 4. Dynamic Programming with Binary Search. 
    // Need to review again. 
    // O(NlogN) time, where N is the total number of elements in nums, 
    // because Binary Search takes O(logN) time and is called N times.
    // O(N) space. dp array of size N is used. 
    public int lengthOfLIS4(int[] nums) {
    	int[] dp = new int[nums.length];
    	int len = 0;
    	for (int num : nums) {
    		// binarySearch(array, start, end, target)
			int i = Arrays.binarySearch(dp, 0, len, num);
			if (i < 0) {
				// Get the index where the element would be. 
				i = -(i + 1);
			}
			dp[i] = num;
			if (i == len) {
				// 'num' would be the last element, which is the 
				// largest in the 'dp'. Increase the length. 
				// 'len' serves as keeping track of max length as well. 
				len++;
			}	
		}
    	
    	return len;
    }



    
    
    
    

	
	
	// other classes here. 

    
    // For testing. 
	public static void main(String[] args) {
	    LongestIncreasingSubsequence solution = new LongestIncreasingSubsequence();
	    
	    // Test arguments. 
	    // int num = 24;
	    // int target = 2;
	    // solution.getInt(num, target);
	    
	    
	    
	}

}















