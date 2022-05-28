// 
// Author: 
// Date  : June 24, 2019

package leetcode;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class IntersectionOfTwoArrays {
	// fields here.
	// private int count;

	public IntersectionOfTwoArrays() {
		// Initialization here.
		// this.count = 0;
	}

	// 1. Two Sets.
	// O(N + M) time, where N is nums1 length and M is nums2 length.
	// O(N + M) space, because in the worst case, all elements in the arrays are
	// different.
	// O(N) or O(M), whichever is bigger.
	public int[] intersection(int[] nums1, int[] nums2) {
		Set<Integer> set1 = new HashSet<>();
		for (Integer n : nums1) {
			set1.add(n);
		}
		Set<Integer> set2 = new HashSet<>();
		for (Integer m : nums2) {
			set2.add(m);
		}

		if (set1.size() < set2.size()) {
			return setIntersection(set1, set2);
		} else {
			return setIntersection(set2, set1);
		}
	}

	private int[] setIntersection(Set<Integer> set1, Set<Integer> set2) {
		int[] output = new int[set1.size()];
		int i = 0;
		for (Integer n : set1) {
			if (set2.contains(n)) {
				output[i] = n;
				i++;
			}
		}

		return Arrays.copyOf(output, i);
	}

	// 2. Built-in Set Intersection. retainAll()
	// O(N + M) time & space.
	public int[] intersection2(int[] nums1, int[] nums2) {
		Set<Integer> set1 = new HashSet<>();
		for (Integer n : nums1) {
			set1.add(n);
		}
		Set<Integer> set2 = new HashSet<>();
		for (Integer m : nums2) {
			set2.add(m);
		}

		// Retain only the elements in set1 that are contained in set2.
		// In other words, remove from set1 all of its elements that
		// are not contained in set2;
		set1.retainAll(set2);

		int[] output = new int[set1.size()];
		int i = 0;
		for (Integer n : set1) {
			if (set2.contains(n)) {
				output[i] = n;
				i++;
			}
		}

		return output;
	}

	// 3. Binary Search
	// If given array is sorted, this solution is better.
	// O(NlogM) time (N < M), O(1) space (The sorting line omitted)
	// Author: kei (AC)
	// Date : September 25, 2020
	public int[] intersection3(int[] nums1, int[] nums2) {
		// Use the smaller size array.
		if (nums1.length > nums2.length) {
			return intersection3(nums2, nums1);
		}

		Set<Integer> set = new HashSet<>();
		// O(MlogM) time (N < M)
		Arrays.sort(nums2);
		// O(NlogM) time (N < M)
		for (int n : nums1) {
			// O(logM) time (N < M)
			if (binarySearch(nums2, n)) {
				set.add(n);
			}
		}

		int[] ret = new int[set.size()];
		int i = 0;
		for (int n : set) {
			ret[i] = n;
			i++;
		}

		return ret;
	}

	private boolean binarySearch(int[] nums2, int n) {
		int left = 0;
		int right = nums2.length - 1;

		while (left <= right) {
			int mid = left + (right - left) / 2;

			if (nums2[mid] == n) {
				return true;
			}

			if (nums2[mid] > n) {
				right = mid - 1;
			} else {
				left = mid + 1;
			}
		}

		return false;
	}

	// Review. Accepted.
	// O(N + M) time, O(N) or O(M) space, whichever is bigger, space.
	// Author: kei (AC)
	// Date : July 15, 2019
	public int[] intersectionR(int[] nums1, int[] nums2) {
		if (nums1 == null || nums2 == null) {
			return new int[] {};
		}

		Set<Integer> set1 = new HashSet<>();
		// O(N) time.
		for (Integer n : nums1) {
			set1.add(n);
		}

		Set<Integer> ansSet = new HashSet<>();
		// O(M) time.
		for (int n : nums2) {
			if (set1.contains(n)) {
				ansSet.add(n);
			}
		}

		// Convert set to array.
		int[] ans = new int[ansSet.size()];
		int i = 0;
		for (int n : ansSet) {
			ans[i] = n;
			i++;
		}

		return ans;
	}

	// other classes here.

	// For testing.
	@SuppressWarnings("unused")
	public static void main(String[] args) {
		IntersectionOfTwoArrays solution = new IntersectionOfTwoArrays();

		// Test arguments.
		// int num = 24;
		// int target = 2;
		// solution.getInt(num, target);

	}

}
