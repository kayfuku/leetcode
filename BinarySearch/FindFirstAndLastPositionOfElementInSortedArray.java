// 
// Author: 
// Date  : May 27, 2019

package leetcode;

import java.util.Arrays;


public class FindFirstAndLastPositionOfElementInSortedArray {

	// Some fields here. 
	//	private int count;


	public FindFirstAndLastPositionOfElementInSortedArray() {
		// Initialization here. 
		//		count = 0;
	}

	// 1. Linear Search. 
	// O(n) time, O(1) space. 
	public int[] searchRangeLinear(int[] nums, int target) {
		int[] targetRange = new int[]{ -1, -1 };
		// find the index of the leftmost appearance of `target`.
		for (int i = 0; i < nums.length; i++) {
			if (nums[i] == target) {
				targetRange[0] = i;
				break;
			}
		}

		// if the last loop did not find any index, then there is no valid range
		// and we return [-1, -1].
		if (targetRange[0] == -1) {
			return targetRange;
		}

		// find the index of the rightmost appearance of `target` (by reverse
		// iteration). it is guaranteed to appear.
		for (int i = nums.length - 1; i >= 0; i--) {
			if (nums[i] == target) {
				targetRange[1] = i;
				break;
			}
		}

		return targetRange;
	}


	// 2. Binary Search. This is much better than below. 
	// Author: atwenbobu + kei (AC)
	// Date  : August 10, 2019
	public int[] searchRange(int[] nums, int target) {
		int[] result = new int[2];
		result[0] = findFirst(nums, target);
		result[1] = findLast(nums, target);
		return result;
	}
	private int findFirst(int[] nums, int target) {
		int idx = -1;
		int left = 0;
		int right = nums.length - 1;
		while (left <= right){
			int mid = (left + right) / 2;
			if (nums[mid] == target) {
				idx = mid;
			}

			if (nums[mid] >= target) {
				right = mid - 1;
			} else {
				left = mid + 1;
			}
		}
		return idx;
	}
	private int findLast(int[] nums, int target) {
		int idx = -1;
		int left = 0;
		int right = nums.length - 1;
		while (left <= right){
			int mid = (left + right) / 2;
			if (nums[mid] == target) {
				idx = mid;
			}
			
			if (nums[mid] <= target){
				left = mid + 1;
			} else {
				right = mid - 1;
			}
		}
		return idx;
	}



	// 2. Binary Search. No need to review!
	// O(log n) time, O(1) space. 
	public int[] searchRange2(int[] nums, int target) {
		int[] targetRange = new int[]{ -1, -1 };

		int leftIdx = searchLeftOrRightmost(nums, target, true);
		// We can search right sub-array first, and then left sub-array. 
		//    	int rightIdx = searchLeftOrRightmost(nums, target, false) - 1;

		// After this if statement, assert that `leftIdx` is within the array bounds and 
		// that `target` is actually in `nums`.
		if (leftIdx == nums.length || nums[leftIdx] != target) {
			return targetRange;
		}
		//    	if (rightIdx == nums.length || nums[rightIdx - 1] != target) {
		//			return targetRange;
		//		}


		targetRange[0] = leftIdx;
		targetRange[1] = searchLeftOrRightmost(nums, target, false) - 1;
		//    	targetRange[0] = searchLeftOrRightmost(nums, target, true);
		//    	targetRange[1] = rightIdx;


		return targetRange;
	}
	// Helper method. Binary Search. O(logN) time. 
	// Returns leftmost (or rightmost + 1) index at which `target` should be inserted in 
	// the sorted array `nums` via binary search.
	// To end up in the leftmost index, search left sub-array including mid by using right = mid; version. 
	// 
	// If target is in the array, then this returns leftmost index for searchleft-true, and 
	// (rightmost + 1) index for searchleft-false. 
	// If target is not in the array, then this returns the index at which the target would be, or nums.length. 
	private int searchLeftOrRightmost(int[] nums, int target, boolean searchLeft) {
		int left = 0;
		int right = nums.length;
		while (left < right) {
			int mid = (left + right) / 2;
			// Don't stop even though finding target. 
			if (nums[mid] == target && searchLeft) {
				right = mid;
			} else if (nums[mid] > target) {
				right = mid;
				// NG! If you use right = mid version, then stick with it! 
				// Otherwise, the eventual index might not be (rightmost + 1).
				//right = mid - 1;
			} else {
				// Search right if nums[mid] < target or nums[mid] == target when searching rightmost index. 
				left = mid + 1;
			}
		}

		// Not -1. Return the desired index or 
		// the index in which the target would be if it is not in the array. 
		return left;		
	}


	// Review
	// Sorted array and finding something reminds me of binary search. 
	// If mid is less than the target, then I can search in the right subarray. 
	// Don't stop even though finding the target.  
	// O(logN) time, O(1) space. 
	public int[] searchRangeR(int[] nums, int target) {
		int[] ret = new int[]{ -1, -1 }; 

		int leftmost = searchLeftmostOrRightmost(nums, target, true);
		if (leftmost == nums.length || nums[leftmost] != target) {
			// target is not in the array. 
			return ret;
		}

		ret[0] = leftmost;
		ret[1] = searchLeftmostOrRightmost(nums, target, false) - 1;

		return ret;
	}
	private int searchLeftmostOrRightmost(int[] nums, int target, boolean leftmost) {
		int left = 0;
		int right = nums.length;
		while (left < right) {
			int mid = left - (left - right) / 2;
			if (nums[mid] ==  target && leftmost) {
				right = mid;
			} else if (nums[mid] > target) {
				right = mid;
			} else {
				left = mid + 1;
			}
		}
		return left;
	}

	// ** check later. 
	// https://leetcode.com/problems/search-in-rotated-sorted-array/discuss/14472/Java-AC-Solution-using-once-binary-search

	//	Hi @tjbstuff, here is my simple technique (actually it is quite common!!). when performing search:
	//
	//	while (left < right-1) { ... if (target < nums[mid]) { right = mid; } if (target > nums[mid]) { left = mid; } ...}
	//	after that, you just need to do some simple post-processing:
	//
	//	if (nums[left] == target) { return left; }
	//	if (nums[right] == target) { return right; }
	//	return -1;
	//	
	//	Except for the most standard binary search, when coming to first/last occurance etc., this simple trick will make your life a lot easier.





	// For testing. 
	public static void main(String[] args) {
		FindFirstAndLastPositionOfElementInSortedArray solution = new FindFirstAndLastPositionOfElementInSortedArray();

		// Test arguments. 
		//	    int num = 24;
		//	    int target = 2;
		//	    solution.getInt(num, target);

		int[] nums = new int[]{ 5,7,7,8,8,10 };
		int[] ret = solution.searchRange(nums, 8);
		System.out.println(Arrays.toString(ret)); // [3, 4]
		ret = solution.searchRange(nums, 6);
		System.out.println(Arrays.toString(ret)); // [-1, -1]


	}

}















