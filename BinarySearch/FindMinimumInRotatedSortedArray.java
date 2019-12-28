// 
// Author: 
// Date  : June 11, 2019

package leetcode;

public class FindMinimumInRotatedSortedArray {
	// fields here. 
	//	private int count;

	public FindMinimumInRotatedSortedArray() {
		// Initialization here. 
		//		this.count = 0;
	}



	// Binary Search. 
	// When I cut the array in half, if I can detect which subarray the target lies in, I can use Binary Search.
	// In this problem, the target is the valley.
	// The valley lies in the left subarray when nums[mid] is less than nums[0]. 
	// 
	// Find the valley using two adjacent elements, and the smaller is the minimum. 
	// The valley is either on the left subarray or right subarray when I cut the array in half. 
	// O(logN) time, O(1) space. 
	// Author: @godayaldivya + kei
	// Date  : August 10, 2019 
	public int findMin(int[] nums) {
		// corner
		if (nums.length == 1) {
			return nums[0];
		}
		// Not rotated. 
		if (nums[0] < nums[nums.length - 1]) {
			return nums[0];
		}

		// Binary Search. 
		int left = 0;
		int right = nums.length - 1;
		//		int right = nums.length; // NG! (R = M version)
		while (left <= right) {
			//		while (left < right) { // NG! (R = M version)
			int mid = left + (right - left) / 2;

			// Find the valley. 
			// nums[mid + 1] could cause array index out of bound when nums[mid] is the last element. 
			// However, for this problem, when nums[mid] is the second to the last, it returns and 
			// nums[mid] never reaches to the last element. 
			// R = M version is NG because nums[mid] skips the second to the last and hits the last 
			// element depending on the case, in which case, nums[mid + 1] causes array index out of bound. 
			if (nums[mid] > nums[mid + 1]) {
				return nums[mid + 1];
			}
			// nums[mid - 1] could cause array index out of bound when the target is the first element or 
			// the second element. 
			// However, for this problem, the code above returns when mid is equal to 0. 
			// To be exact, when you use R = M - 1 version, mid is approaching index 0 as the following: 
			// 1. 2 -> 0 -> 1
			// 2. 3 -> 1 -> 0 
			// ex) 1. [ 5, 1, 2, 3, 4 ], 2. [ 6, 1, 2, 3, 4, 5]
			// For case 1, the code above returns when mid is equal to 0, and nums[-1] never occurs. 
			// For case 2, the code below returns when mid is equal to 1, and nums[-1] never occurs. 
			// 
			// Be careful!
			// Not rotated array can cause nums[-1] here for both of the two cases above because 
			// the code above does not return when mid is equal to 0, which causes index out of bound here. 
			// So, eliminate this case in advance. 
			if (nums[mid - 1] > nums[mid]) {
				return nums[mid];
			}

			// If the mid does not hit the valley, then keep searching. 
			// I don't know why nums[mid] > nums[0] is ok in the LeetCode solution yet. 
			// (No need to explore any further)
			if (nums[mid] > nums[left]) {
				// The min is in the right subarray. 
				left = mid + 1;
			} else {
				// The min is in the left subarray. 
				right = mid - 1;
				//				right = mid; // NG! (R = M version)
			}
		}

		return -1;
	}


	// Review
	public int findMinR(int[] nums) {
		if (nums.length == 1) {
			return nums[0];
		}

		if (nums[0] < nums[nums.length - 1]) {
			return nums[0];
		}

		// Binary Search. R = M - 1
		int left = 0;
		int right = nums.length - 1;
		while (left <= right) {
			int mid = left + (right - left) / 2;

			if (nums[mid] > nums[mid + 1]) {
				return nums[mid + 1];
			}
			if (nums[mid - 1] > nums[mid]) {
				return nums[mid];
			}

			if (nums[mid] < nums[0]) {
				right = mid - 1;
			} else {
				left = mid + 1;
			}
		}

		return -1;
	}



	// For testing. 
	public static void main(String[] args) {
		FindMinimumInRotatedSortedArray solution = new FindMinimumInRotatedSortedArray();

		// Test arguments. 
		//	    int num = 24;
		//	    int target = 2;
		//	    solution.getInt(num, target);

		int[] nums = new int[]{ 3, 4, 5, 1, 2 };
		System.out.println(solution.findMin(nums)); // 1
		nums = new int[]{ 4, 5, 6, 7, 0, 1, 2 };
		System.out.println(solution.findMin(nums)); // 0
		nums = new int[]{ 7, 1, 2, 4, 5, 6 };
		System.out.println(solution.findMin(nums)); // 1
		nums = new int[]{ 1, 2, 4, 5, 6 };
		System.out.println(solution.findMin(nums)); // 1
		nums = new int[]{ 7 };
		System.out.println(solution.findMin(nums)); // 7
		nums = new int[]{ 7, 2 };
		System.out.println(solution.findMin(nums)); // 2 (R = M version => Index out of bound!)
		nums = new int[]{ 2, 3, 4, 5, 1 };
		System.out.println(solution.findMin(nums)); // 1 (R = M version => Index out of bound!)
		nums = new int[]{ 4, 5, 1, 2, 3 };
		System.out.println(solution.findMin(nums)); // 1
		nums = new int[]{ 5, 1, 2, 3, 4 };
		System.out.println(solution.findMin(nums)); // 1





	}

}















