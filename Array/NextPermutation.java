// 
// Author: 
// Date  : May 27, 2019

package leetcode;

public class NextPermutation {

	// Some fields here.
	// private int count;

	public NextPermutation() {
		// Initialization here.
		// count = 0;
	}

	// O(N) time, O(1) space.
	public void nextPermutation(int[] nums) {
		if (nums.length == 0 || nums.length == 1) {
			return;
		}
		// Find i from the end, where nums[i] < nums[i + 1].
		int i = nums.length - 2;
		while (i >= 0 && nums[i] >= nums[i + 1]) {
			i--;
		}
		// nums[i] < nums[i + 1]

		// Note that i could be -1.
		if (i >= 0) {
			// Find j from the end, where nums[j] is next larger than nums[i].
			int j = nums.length - 1;
			// Step A.
			while (j >= 0 && nums[j] <= nums[i]) {
				j--;
			}
			// nums[j] is next larger than nums[i].

			swap(nums, i, j);
		}

		// Sort in an ascending order, but it only takes O(N) because it is already
		// sorted in an descending order and swapping doesn't change the
		// order because of the way of the step A (j is approaching from the right
		// side). So just reverse it.
		reverse(nums, i + 1);
	}

	private void reverse(int[] nums, int start) {
		int i = start, j = nums.length - 1;
		while (i < j) {
			swap(nums, i, j);
			i++;
			j--;
		}
	}

	private void swap(int[] nums, int v1, int v2) {
		int temp = nums[v1];
		nums[v1] = nums[v2];
		nums[v2] = temp;
	}

	// Review
	public void nextPermutationR(int[] nums) {
		int i = nums.length - 2;
		while (i >= 0 && nums[i] >= nums[i + 1]) {
			i--;
		}
		// nums[i] < nums[i + 1] or -1

		if (i != -1) {
			int j = nums.length - 1;
			while (j >= 0 && nums[j] <= nums[i]) {
				j--;
			}
			// nums[j] is next larger than nums[i].

			swapR(nums, i, j);
		}

		reverseR(nums, i + 1);
	}

	private void swapR(int[] nums, int i, int j) {
		int temp = nums[i];
		nums[i] = nums[j];
		nums[j] = temp;
	}

	private void reverseR(int[] nums, int i) {
		int right = nums.length - 1;
		int left = i;
		while (left < right) {
			swapR(nums, left, right);
			left++;
			right--;
		}
	}

	// For testing.
	@SuppressWarnings("unused")
	public static void main(String[] args) {
		NextPermutation solution = new NextPermutation();

		// Test arguments.
		// int num = 24;
		// int target = 2;
		// solution.getInt(num, target);

	}

}
