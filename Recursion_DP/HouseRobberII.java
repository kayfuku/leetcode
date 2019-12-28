// 
// Author: 
// Date  : July 5, 2019

package leetcode;

public class HouseRobberII {
	// fields here. 
	//	private int count;

	public HouseRobberII() {
		// Initialization here. 
		//		this.count = 0;
	}


	// If you use the first elem, then you cannot use the last elem. 
	// Similarly, if you use the last elem, then you cannot use the first elem. 
	// We have a solution to get a max from an array. 
	// Get the max of the array except the first one and the max of the array except 
	// the last one. And take the larger one. 
	// 
	// O(N) time, O(1) space. 
	public int rob(int[] nums) {
		if (nums.length == 1) {
			return nums[0];
		}

		return Math.max(robI(nums, 0, nums.length - 2), robI(nums, 1, nums.length - 1));
	}
	public int robI(int[] nums, int start, int end) {
		// corner: nums.len, 1, 2 => ok. 
		int prevMax = 0;
		int currMax = 0;
		// Be careful with '<=', not '<'!
		for (int i = start; i <= end; i++) {
			int temp = currMax;
			currMax = Math.max(prevMax + nums[i], currMax);
			prevMax = temp;
		}

		return currMax;
	}


	// Review. 
	public int robR(int[] nums) {
		// corner 
		if (nums == null || nums.length == 0) {
			return 0;
		}
		if (nums.length == 1) {
			return nums[0];
		}

		return Math.max(robIR(nums, 0, nums.length - 2), robIR(nums, 1, nums.length - 1));
	}

	private int robIR(int[] nums, int start, int end) {
		int preMax = 0;
		int curMax = 0;
		for (int i = start; i <= end; i++) {
			int temp = curMax;
			curMax = Math.max(preMax + nums[i], curMax);
			preMax = temp;
		}

		return curMax;
	}





	// For testing. 
	public static void main(String[] args) {
		HouseRobberII solution = new HouseRobberII();

		// Test arguments. 
		// int num = 24;
		// int target = 2;
		// solution.getInt(num, target);



	}

}















