// 
// Author: 
// Date  : January 2, 2020

package leetcode;

import java.util.Arrays;
import java.util.HashMap;

public class IntersectionOfTwoArraysII {
	// fields and classes here.
	// private int count;

	public IntersectionOfTwoArraysII() {
		// Initialization here.
		// this.count = 0;

	}

	// 1. Using HashMap to count the numbers
	// Author: votrubac + sankitgupta + kei
	// Date : January 2, 2020
	public int[] intersect(int[] nums1, int[] nums2) {
		// Use the smaller size array.
		if (nums1.length > nums2.length) {
			return intersect(nums2, nums1);
		}
		// N is the total num of elems in nums1. (N < M)
		// M is the total num of elems in nums2. (N < M)

		// Count the num of numbers.
		HashMap<Integer, Integer> counts1 = new HashMap<>();
		// O(N) time
		for (int n : nums1) {
			counts1.put(n, counts1.getOrDefault(n, 0) + 1);
		}

		int k = 0;
		// O(M) time
		for (int n : nums2) {
			int count = counts1.getOrDefault(n, 0);
			if (count > 0) {
				// Use nums1 to save some space.
				nums1[k] = n;
				k++;
				// Decrement.
				counts1.put(n, count - 1);
			}
		}

		// copyOfRange() returns array of size k - 0.
		return Arrays.copyOfRange(nums1, 0, k);
	}

	// 2. If the arrays are sorted, this could be the first solution.
	// O(N + M) time, O(1) space (The first two sorting line omitted)
	// Author: leetcode + kei
	// Date : September 25, 2020
	public int[] intersect2(int[] nums1, int[] nums2) {
		Arrays.sort(nums1);
		Arrays.sort(nums2);

		int i = 0, j = 0, k = 0;
		// Keep going until either pointer reaches the end.
		while (i < nums1.length && j < nums2.length) {
			// Move forward a pointer that points to the smaller number.
			if (nums1[i] < nums2[j]) {
				i++;
			} else if (nums1[i] > nums2[j]) {
				j++;
			} else {
				// nums1[i] == nums2[j]
				// Move forward both pointers.
				// This way, we can return duplicates.
				// Use nums1 array to save space. Move forward k after assignment.
				nums1[k] = nums1[i];
				k++;
				i++;
				j++;
			}
		}

		// copyOfRange() returns array of size k - 0.
		return Arrays.copyOfRange(nums1, 0, k);
	}

	// 3. Using Binary Search
	// If the arrays are sorted, this is a solution.
	// O(NlogM) time (N < M), O(1) space (The two sorting line omitted)
	// Author: leetcode + kei
	// Date : September 28, 2020
	public int[] intersect3(int[] nums1, int[] nums2) {
		if (nums1.length > nums2.length) {
			return intersect3(nums2, nums1);
		}

		Arrays.sort(nums1);
		Arrays.sort(nums2);

		// nums1.len <= nums2.len

		int k = 0;
		int idx = 0;
		int left = 0;
		// O(NlogM) time (N < M)
		for (int n : nums1) {
			// O(logM) time
			// Binary Search Leftmost because there are duplicates.
			idx = binarySearchLeftmost(nums2, left, nums2.length - 1, n);
			if (idx == -1) {
				continue;
			}
			// Found n in nums2.
			nums1[k] = n;
			k++;
			left = idx + 1;
		}

		return Arrays.copyOfRange(nums1, 0, k);
	}

	private int binarySearchLeftmost(int[] nums2, int left, int right, int n) {
		int index = -1;

		while (left <= right) {
			int mid = left + (right - left) / 2;
			if (nums2[mid] == n) {
				index = mid;
			}

			if (nums2[mid] >= n) {
				right = mid - 1;
			} else {
				left = mid + 1;
			}
		}

		return index;
	}

	// For testing.
	public static void main(String[] args) {
		IntersectionOfTwoArraysII solution = new IntersectionOfTwoArraysII();

		// Test arguments.
		// int num = 24;
		// int target = 2;
		// solution.getInt(num, target);
		// int[] nums1 = { 1, 2, 2, 1 };
		int[] nums1 = { -2147483648, 1, 2, 3 };
		// int[] nums2 = { 2, 2 };
		// int[] nums2 = { 1, 1 };
		int[] nums2 = { 1, -2147483648, -2147483648 };
		System.out.println(solution.intersect3(nums1, nums2));

	}

}
