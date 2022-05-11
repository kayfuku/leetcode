// 
// Author: 
// Date  : August 8, 2019

package leetcode;

public class BinarySearch {
	// fields and classes here.
	// private int count;

	public BinarySearch() {
		// Initialization here.
		// this.count = 0;

	}

	//
	public int search(int[] nums, int target) {
		int left = 0;
		int right = nums.length - 1;

		while (left <= right) {
			int mid = left + (right - left) / 2;

			if (nums[mid] == target) {
				return mid;
			}

			if (nums[mid] > target) {
				right = mid - 1;
			} else {
				left = mid + 1;
			}
		}

		return -1;
	}

	// For testing.
	@SuppressWarnings("unused")
	public static void main(String[] args) {
		BinarySearch solution = new BinarySearch();

		// Test arguments.
		// int num = 24;
		// int target = 2;
		// solution.getInt(num, target);

	}

}
