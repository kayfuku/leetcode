// 
// Author: 
// Date  : July 5, 2019

package leetcode;

public class HouseRobber {
	// fields here. 
	//	private int count;

	public HouseRobber() {
		// Initialization here. 
		//		this.count = 0;
	}

	// 1. Dynamic Programming. O(N) space.  
	// nums: 1 2 3 1
	//   dp: 1 2 4 4
	// O(N) time, O(N) space. 
	// Author: kei (AC)
	// Date  : August 13, 2019
	public int rob(int[] nums) {
		// corner
		if (nums == null || nums.length == 0) {
			return 0;
		}
		if (nums.length == 1) {
			return nums[0];
		}

		int n = nums.length;
		int[] dp = new int[n];
		dp[0] = nums[0];
		dp[1] = Math.max(nums[0], nums[1]);	
		for (int i = 2; i < n; i++) {
			dp[i] = Math.max(dp[i - 2] + nums[i], dp[i - 1]);
		}

		return dp[n - 1];
	}


	// 2. Dynamic Programming. O(1) space. 
	// 
	// Recurrence formula: 
	// dp[i] = max(dp[i - 2] + nums[i], dp[i - 1])
	//
	// At each step, you only need the previous two maximum value, two variables 
	// are enough. 
	// O(N) time, O(1) space. 
	public int rob2(int[] nums) {
		// corner: nums.len, 1, 2 => ok. 
		int prevMax = 0;
		int currMax = 0;
		for (int x : nums) {
			int temp = currMax;
			currMax = Math.max(prevMax + x, currMax);
			prevMax = temp;
		}

		return currMax;
	}


	// Another way to solve this problem. Accepted. No need to review. 
	// https://leetcode.com/problems/house-robber-ii/discuss/59934/Simple-AC-solution-in-Java-in-O(n)-with-explanation
	// O(N) time, O(1) space. 
	// Author: lx223 (LeetCode)
	// Date  : July 5, 2019
	public int rob3(int[] num) {
		int include = 0, exclude = 0;
		for (int j = 0; j <= num.length - 1; j++) {
			int i = include, e = exclude;
			include = e + num[j];
			exclude = Math.max(e, i);
		}
		return Math.max(include, exclude);
	}


	// Dynamic Programming. Accepted. ** Review why NG? No need to review. 
	// O(N^2) time, O(1) space. 
	// Author: kei
	// Date  : July 5, 2019
	public int robK(int[] nums) {
		if (nums.length == 0) {
			return 0;
		}

		int[] dp = new int[nums.length];
		int max = nums[0];
		for (int i = 0; i < nums.length; i++) {
			int localMax = nums[i];
			for (int j = 0; j < i - 1; j++) {
				// Since nums[i] is non-negative??
				localMax = Math.max(localMax, dp[j] + nums[i]);
			}
			dp[i] = localMax;
			max = Math.max(max, dp[i]);
		}

		return max;
	}



	// Review. 
	// nums: 1 2 3 1
	//   dp: 1 2 4 4
	// O(N) time, O(N) space. 
	// Author: kei (AC)
	// Date  : August 13, 2019
	public int robR(int[] nums) {
		// corner
		if (nums == null || nums.length == 0) {
			return 0;
		}
		if (nums.length == 1) {
			return nums[0];
		}

		int n = nums.length;
		int[] dp = new int[n];
		dp[0] = nums[0];
		dp[1] = Math.max(nums[0], nums[1]);	
		for (int i = 2; i < n; i++) {
			dp[i] = Math.max(dp[i - 2] + nums[i], dp[i - 1]);
		}

		return dp[n - 1];
	}


	// DP, O(1) space. 
	// i   :   0 1 2 3 
	// nums:   1 2 3 1
	// cur : 0 1 2 4 4 => 4
	// temp:   0 1 2 4
	// pre : 0 0 1 2 4
	public int robR2(int[] nums) {
		// corner: nums.len:0, nums.len:1 => ok
		if (nums == null) {
			return 0;
		}

		int curMax = 0;
		int preMax = 0;
		for (int i = 0; i < nums.length; i++) {
			int temp = curMax;
			curMax = Math.max(preMax + nums[i], curMax);	
			preMax = temp;
		}

		return curMax;
	}




	// For testing. 
	public static void main(String[] args) {
		HouseRobber solution = new HouseRobber();

		// Test arguments. 
		// int num = 24;
		// int target = 2;
		// solution.getInt(num, target);



	}

}















