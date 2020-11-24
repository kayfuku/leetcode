// 
// Author: 
// Date  : May 27, 2019

package leetcode;

public class RemoveElement {

	// Some fields here.
	// private int count;

	public RemoveElement() {
		// Initialization here.
		// count = 0;
	}

	// 1. Two pointers. Efficient if many elements to remove are in the array,
	// because the assignment occurs fewer times.
	// O(n) time, O(1) space.
	public int removeElement(int[] nums, int val) {
		// i points at the position for next addition.
		int i = 0;
		// Iterate through the original array.
		for (int j = 0; j < nums.length; j++) {
			if (nums[j] != val) {
				nums[i] = nums[j];
				i++;
			}
		}
		// Elements to the left of i are the desired list.
		return i;
	}

	// 2. Two pointers. More efficient if elements to remove are rare.
	// O(n) time, O(1) space.
	// Time complexity : O(n). Both i and n traverse at most n steps. In this
	// approach, the number of assignment operation is equal to the number of
	// elements to remove.
	// So it is more efficient if elements to remove are rare.
	// Space complexity : O(1).
	public int removeElement2(int[] nums, int val) {
		int n = nums.length;
		int i = 0;
		while (i < n) {
			if (nums[i] != val) {
				i++;
			} else {
				// Put the last element to the i-th element.
				// No need to swap!
				nums[i] = nums[n - 1];
				// Reduce the nums length.
				// i must not move because we have to check the new value.
				n--;
			}
		}

		return n;
	}

	// n = nums.length is better!
	public int removeElementKei2(int[] nums, int val) {
		int n = nums.length - 1;
		int i = 0;
		while (i <= n) {
			if (nums[i] == val) {
				// No need to swap!
				// int temp = nums[n];
				// nums[n] = nums[i];
				// nums[i] = temp;

				nums[i] = nums[n];
				n--;
			} else {
				i++;
			}
		}

		return n + 1;
	}

	// Think about the corner cases first! <= Not really.
	public int removeElementKei(int[] nums, int val) {
		if (nums.length == 0) {
			return 0;
		}
		int i = -1;
		for (int j = 0; j < nums.length; j++) {
			if (nums[j] != val) {
				i++;
				nums[i] = nums[j];
			}
		}

		return i + 1;
	}

	// Review. Accepted. The elements to remove are not rare.
	// Author: kei
	// Date : June 19, 2019
	// nums: 2 2 2 3, val: 3
	// p q
	// O(N) time, p and q traverse at most 2N.
	// O(1) space.
	public int removeElementR(int[] nums, int val) {
		// corner
		if (nums == null) {
			return 0;
		}

		int p = -1, q = 0;
		while (q < nums.length) {
			if (nums[q] != val) {
				p++;
				nums[p] = nums[q];
			}
			q++;
		}

		return p + 1;
	}

	// Review.
	// The elements to remove are rare.
	// O(N) time, because p and n traverse at most N steps.
	// In this approach, the number of assignment is equal to the number of elements
	// to remove.
	// So it is more efficient if the elements to remove are rare.
	// O(1) space.
	public int removeElementR2(int[] nums, int val) {
		// p gets 0!
		int p = 0, n = nums.length;

		while (p < n) {
			if (nums[p] == val) {
				nums[p] = nums[n - 1];
				n--;
			} else {
				// This is in the else!
				p++;
			}
		}

		return n;
	}

	// For testing.
	public static void main(String[] args) {
		RemoveElement solution = new RemoveElement();

		// Test arguments.
		// int num = 24;
		// int target = 2;
		// solution.getInt(num, target);

	}

}
