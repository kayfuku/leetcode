// 
// Author: 
// Date  : July 1, 2019

package leetcode;

public class SearchInsertPosition {
	// fields here.
	// private int count;

	public SearchInsertPosition() {
		// Initialization here.
		// this.count = 0;
	}

	// Binary Search.
	// O(logN) time, where N is the total number of elements in the input array.
	// O(1) space.
	public int searchInsert(int[] nums, int target) {
		int left = 0, right = nums.length - 1;
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

		return left;
	}

	// Review.
	public int searchInsertR(int[] nums, int target) {
		// corner.
		if (nums == null || nums.length == 0) {
			return 0;
		}

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

		return left;
	}

	// For testing.
	@SuppressWarnings("unused")
	public static void main(String[] args) {
		SearchInsertPosition solution = new SearchInsertPosition();

		// Test arguments.
		// int num = 24;
		// int target = 2;
		// solution.getInt(num, target);

	}

}
