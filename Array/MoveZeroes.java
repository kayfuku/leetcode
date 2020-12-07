// 
// Author: 
// Date  : July 15, 2019

package leetcode;

public class MoveZeroes {
	// fields and classes here.
	// private int count;

	public MoveZeroes() {
		// Initialization here.
		// this.count = 0;
	}

	// Two pointers.
	// 'slow' points to the position where non-zero element should be put.
	// 'cur' finds non-zero element in the array.
	// All elements before the 'slow' are non-zeroes.
	// All elements between the 'cur' and 'slow' are zeroes.
	// O(N) time, O(1) space.
	public void moveZeroes(int[] nums) {
		// 'slow' and 'cur' starts from 0.
		// If the nums[0] is non-zero, that's fine. That is a self copy.
		int slow = 0;
		for (int cur = 0; cur < nums.length; cur++) {
			if (nums[cur] != 0) {
				// We need to swap because 0 must not be deleted.
				swap(nums, slow, cur);
				slow++;
			}
		}
	}

	private void swap(int[] nums, int slow, int cur) {
		int temp = nums[slow];
		nums[slow] = nums[cur];
		nums[cur] = temp;
	}

	// For testing.
	public static void main(String[] args) {
		MoveZeroes solution = new MoveZeroes();

		// Test arguments.
		// int num = 24;
		// int target = 2;
		// solution.getInt(num, target);

	}

}
