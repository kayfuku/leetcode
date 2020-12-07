// 
// Author: 
// Date  : July 8, 2019

package leetcode;

public class SearchInRotatedSortedArray {
	// fields here.
	// private int count;

	public SearchInRotatedSortedArray() {
		// Initialization here.
		// this.count = 0;
	}

	// Binary Search. Accepted.
	//
	// "The idea is that when rotating the array, there must be one half of
	// the array that is still in sorted order.
	// For example, 6 7 1 2 3 4 5, the order is disrupted from the point between 7
	// and 1. So when doing binary search, we can make a judgement that which part
	// is ordered and whether the target is in that range, if yes, continue the
	// search in that half, if not continue in the other half." -- by flyinghx61
	// https://leetcode.com/problems/search-in-rotated-sorted-array/discuss/14472/Java-AC-Solution-using-once-binary-search
	//
	//
	// When I cut the array in half, if I can detect which subarray the target lies
	// in, I can use Binary Search.
	// I need to think about the three cases:
	// Case 1. The drop lies in the right subarray.
	// Case 2. The drop lies in the left subarray.
	// Case 3. Not rotated.
	// Then, for each case, think about which subarray the target lies in
	// on what conditions.
	//
	// I can see if it is Case 1 or 2 by looking at nums[left] and nums[mid].
	// if nums[left] <= nums[mid], then it is Case 1, otherwise Case 2.
	// For Case 1, if nums[left] <= target < nums[mid], then the target lies in the
	// left subarray.
	// For Case 2, if nums[mid] < target <= nums[right], then the target lies in the
	// right subarray.
	// And those logic can also take care of the Case 3.
	//
	// O(logN) time, O(1) space.
	// Author: flyinghx61 + kei
	// Date : July 8, 2019
	public int search(int[] nums, int target) {
		if (nums == null || nums.length == 0) {
			return -1;
		}

		int left = 0;
		int right = nums.length - 1;

		while (left <= right) {
			// We can avoid the integer overflow using other version, but according to
			// this problem statement, this is ok.
			int mid = (left + right) / 2;

			// Check the mid first.
			if (nums[mid] == target) {
				return mid;
			}

			// I need to think carefully about which subarray the target lies in
			// on what conditions.

			// Note that '<=' is important, not '<'!
			// If you use '<' here, then it will cause a problem
			// when subarray has just one element, in which case, 'mid' is equal to 'left'.
			// Test the case where nums: [3, 1], t:1
			if (nums[left] <= nums[mid]) {
				// Case 1 and Case 3.
				// The drop lies in the right subarray.
				// The left subarray is ordered including just one element.
				if (nums[left] <= target && target < nums[mid]) {
					// The target lies in the ordered subarray.
					// Search on the left subarray.
					right = mid - 1;
				} else {
					// Search on the right subarray.
					left = mid + 1;
				}
			} else {
				// Case 2.
				// nums[left] > nums[mid]
				// The drop lies in the left subarray.
				// The right subarray is ordered including just one element.
				if (nums[mid] < target && target <= nums[right]) {
					// The target lies in the ordered subarray.
					// Search on the right subarray.
					left = mid + 1;
				} else {
					// Search on the left subarray.
					right = mid - 1;
				}
			}

		}

		return -1;
	}

	// NG!!
	public int searchF(int[] nums, int target) {
		int left = 0;
		int right = nums.length - 1;

		while (left <= right) {
			int mid = left + (right - left) / 2;

			if (nums[mid] == target) {
				return mid;
			}

			// This is NG because it is not guaranteed that the target should be
			// in the left subarray even if this condition is met.
			// Think about the case nums[mid] <= nums[left]. The target is always
			// in the right subarray? No.
			// target >= nums[left], not '>'. The target can be nums[left].
			if (nums[mid] > nums[left] && target >= nums[left] && target < nums[mid]) {
				right = mid - 1;
			} else {
				left = mid + 1;
			}
		}

		return -1;
	}

	// Review. Accepted.
	public int searchR(int[] nums, int target) {
		// corner.
		if (nums == null || nums.length == 0) {
			return -1;
		}

		int left = 0;
		int right = nums.length - 1;

		while (left <= right) {
			int mid = left + (right - left) / 2;
			if (nums[mid] == target) {
				return mid;
			}

			if (nums[mid] < nums[left]) {
				if (nums[mid] < target && target <= nums[right]) {
					left = mid + 1;
				} else {
					right = mid - 1;
				}
			} else {
				if (nums[left] <= target && target < nums[mid]) {
					right = mid - 1;
				} else {
					left = mid + 1;
				}
			}
		}

		return -1;
	}

	// For testing.
	public static void main(String[] args) {
		SearchInRotatedSortedArray solution = new SearchInRotatedSortedArray();

		// Test arguments.
		// int num = 24;
		// int target = 2;
		// solution.getInt(num, target);

		int[] nums = new int[] { 3, 4, 5, 6, 7, 8, 0, 1, 2 };
		System.out.println(solution.search(nums, 1)); // 7
		nums = new int[] { 3, 4, 5, 6, 7, 8, 0, 1, 2 };
		System.out.println(solution.search(nums, 4)); // 1
		nums = new int[] { 3, 1 };
		System.out.println(solution.searchR(nums, 1)); // 1

	}

}
