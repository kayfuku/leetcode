// 
// Author: 
// Date  : May 28, 2019
// Note  : https://leetcode.com/problems/jump-game/solution/
// This is a dynamic programming question. Usually, solving and 
// fully understanding a dynamic programming problem is a 4 step process:
//  1. Start with the recursive backtracking solution
//  2. Optimize by using a memoization table (top-down dynamic programming)
//  3. Remove the need for recursion (bottom-up dynamic programming)
//  4. Apply final tricks to reduce the time / memory complexity
// All solutions presented below produce the correct result, but they differ in run time and memory requirements.

package leetcode;


public class JumpGame {

	// Some fields here. 
	//	private int count;


	public JumpGame() {
		// Initialization here. 
		//		count = 0;
	}

	// 1. Backtracking. 
	// O(2^N) time, O(N) space. Time Limit Exceeded. 
	public boolean canJump(int[] nums) {
		if (nums.length == 1) {
			return true;
		}
		return canJump(nums, 0);
	}
	private boolean canJump(int[] nums, int currIdx) {
		if (currIdx == nums.length - 1) {
			return true;
		}

		int maxJump = nums[currIdx];
		for (int jump = 1; jump <= maxJump; jump++) {
			int nextIdx = currIdx + jump;
			if (nextIdx < nums.length /* avoid index out of bound */ && 
					canJump(nums, nextIdx)) {
				return true;
			}
		}

		return false;
	}

	// 2. Dynamic Programming Top-Down (Memoizationb). Time Limit Exceeded. 
	// O(n^2) time, O(n) space. 
	boolean[] memo;

	public boolean canJump2(int[] nums) {
		if (nums.length == 1) {
			return true;
		}
		memo = new boolean[nums.length];
		memo[nums.length - 1] = true;

		return canJump2(nums, 0);    
	}
	private boolean canJump2(int[] nums, int currIdx) {
		if (currIdx == nums.length - 1) {
			return true;
		}

		if (memo[currIdx]) {
			return true;
		}

		int maxJump = nums[currIdx];
		for (int jump = maxJump; jump >= 1; jump--) {
			int nextIdx = currIdx + jump;
			if (nextIdx < nums.length /* avoid index out of bound */ && 
					canJump(nums, nextIdx)) {
				memo[currIdx] = true;
				return true;
			}
		}

		return false;
	}

	// 3. Dynamic Programming Bottom-Up. 
	// O(n^2) time. For every element in the array, say i, 
	// we are looking at the next nums[i] elements to its right aiming to 
	// find a GOOD index. nums[i] can be at most n, where n is the nums length.
	// O(n) space. 
	public boolean canJump3(int[] nums) {
		if (nums.length == 1) {
			return true;
		}
		boolean[] memo = new boolean[nums.length];
		memo[nums.length - 1] = true;

		for (int i = nums.length - 2; i >= 0; i--) {
			// Avoid index out of bound. 
			int nextPosMax = Math.min(i + nums[i], nums.length - 1); 
			// Check if the index is GOOD or BAD. 
			for (int nextPos = i + 1; nextPos <= nextPosMax; nextPos++) {
				if (memo[nextPos]) {
					memo[i] = true;
					break;
				}
			}
		}

		return memo[0];
	}

	// 4. Greedy. 
	// O(N) time, we traverse nums once. 
	// O(1) space, no extra space is used. 
	public boolean canJump4(int[] nums) {
		if (nums.length == 1) {
			return true;
		}

		// Start from the last element. Iterating right-to-left, for each position, 
		// we check if there is an element that reaches a GOOD index:
		// (currPosition + nums[currPosition] >= leftmostGoodIndex). 
		int goodIdx = nums.length - 1;
		for (int i = nums.length - 2; i >= 0; i--) {
			if (i + nums[i] >= goodIdx) {
				// index i itself is GOOD index. 
				goodIdx = i;
			}
		}
		// If the first index is a GOOD, then return true. 
		return goodIdx == 0;
	}

	// Review 
	public boolean canJumpR(int[] nums) {
		if (nums == null || nums.length == 0) {
			return false;
		}
		if (nums.length == 1) {
			return true;
		}

		int goodIdx = nums.length - 1;
		for (int i = nums.length - 2; i >= 0; i--) {
			if (i + nums[i] >= goodIdx) {
				goodIdx = i;
			}
		}

		return goodIdx == 0;	
	}






	// For testing. 
	public static void main(String[] args) {
		JumpGame solution = new JumpGame();

		// Test arguments. 
		//	    int num = 24;
		//	    int target = 2;
		//	    solution.getInt(num, target);

		int[] nums = new int[]{ 2, 0, 0 };
		int[] nums2 = new int[]{ 2, 3, 1, 1, 4 };
		int[] nums3 = new int[]{ 3, 2, 1, 0, 4 };
		System.out.println(solution.canJump(nums)); // true
		System.out.println(solution.canJump2(nums)); // true
		System.out.println(solution.canJump2(nums2)); // true
		System.out.println(solution.canJump2(nums3)); // false
		System.out.println(solution.canJump3(nums2)); // true
		System.out.println(solution.canJump4(nums2)); // true


	}

}















