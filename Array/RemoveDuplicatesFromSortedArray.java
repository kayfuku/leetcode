// 
// Author: 
// Date  : May 27, 2019

package leetcode;

public class RemoveDuplicatesFromSortedArray {

	// Some fields here.
	// private int count;

	public RemoveDuplicatesFromSortedArray() {
		// Initialization here.
		// count = 0;
	}

	// O(n) time, where n is the length of the array. Each i and j traverses at most
	// n steps.
	// O(1) space.
	// Author: leetcode + kei
	// Date : May 27, 2019, November 6, 2020
	public int removeDuplicates(int[] nums) {
		if (nums.length <= 1) {
			return nums.length;
		}
		// Assert that nums has at least 2 elems.

		// i points at the last position of the desired list.
		int i = 0;
		for (int j = 1; j < nums.length; j++) {
			// Since nums is sorted, duplicates are next to each other.
			if (nums[j] != nums[i]) {
				// Note that before assigning nums[j], move i first.
				i++;
				nums[i] = nums[j];
			}
		}

		return i + 1;
	}

	// Review. Accepted.
	public int removeDuplicatesR(int[] nums) {
		// corner
		if (nums == null || nums.length == 0) {
			return 0;
		}

		// p is slower, q is faster.
		int p = 0, q = 0;
		while (q < nums.length) {
			if (nums[p] == nums[q]) {
				q++;
			} else {
				p++;
				nums[p] = nums[q];
			}
		}

		return p + 1;
	}

	// For testing.
	public static void main(String[] args) {
		RemoveDuplicatesFromSortedArray solution = new RemoveDuplicatesFromSortedArray();

		// Test arguments.
		// int num = 24;
		// int target = 2;
		// solution.getInt(num, target);

		int[] nums = new int[] { 0, 0, 1, 1, 1, 2, 2, 3, 3, 4 };
		int ret = solution.removeDuplicates(nums);
		System.out.println(ret);
		System.out.println();
		for (int i = 0; i < ret; i++) {
			System.out.println(nums[i]);
		}

	}

}
