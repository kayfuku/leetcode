// 
// Author: 
// Date  : June 12, 2019

package leetcode;

public class FindPeakElement {
	// fields here. 
	//	private int count;

	public FindPeakElement() {
		// Initialization here. 
		//		this.count = 0;
	}

	// other classes here. 

	// Binary Search. 
	// If the mid element lies in a descending sequence of numbers, 
	// or a local falling slope (found by comparing nums[i] to its right neighbour), 
	// it means that the peak will always lie towards the left of this element. 
	// Thus, we reduce the search space to the left of mid (including itself) and 
	// perform the same process on left subarray.
	// We use mid = right; version because the mid could be the peak and needs to be checked for  
	// another requirement, and since we need to check mid + 1 element, 
	// 'right' is initialized to nums.length - 1 to avoid index out of bound. 

	// O(logN) time, O(1) space. 
	public int findPeakElement(int[] nums) {
		int left = 0;
		// Use arr.length - 1 so that nums[mid + 1] does not cause index out of bound. 
		int right = nums.length - 1;
		//		int r = nums.length; // NG! index out of bound. 
		while (left < right) {
			int mid = left + (right - left) / 2;
			if (nums[mid] > nums[mid + 1]) { 
				// Search left subarray. 
				// The nums[mid] could be a peak. We need to include mid. 
				right = mid;
			} else {
				// Search right subarray. 
				left = mid + 1;
			}
		}
		// We need to verify how the mid moves in the cases where 
		// the target is the first, the last, the second, or the second to last element. 
		// If the 'left' gets 'mid + 1' and meets 'right' and we get out of the loop 
		// (in this case, the peak is the last elem of the subarray), then 
		// 'left' refers to the elem 'right' neighbor to the last 'mid', which is ok because 
		// 'nums[mid] > nums[mid + 1]' was not met, which means 'mid + 1' is the peak. 
		// If the 'right' gets 'mid' and meets 'left' and we get out of the loop 
		// (in this case, the peak is the first elem of the subarray), then
		// 'left' refers to the last 'mid', which is also ok because 
		// 'nums[mid] > nums[mid + 1]' was met, which means 'mid' is the peak. 
		return left;
	}


	// Review 
	public int findPeakElementR(int[] nums) {
		int left = 0;
		int right = nums.length - 1;

		while (left < right) {
			int mid = left + (right - left) / 2;

			if (nums[mid] > nums[mid + 1]) {
				// Search on left. 
				right = mid;	
			} else {
				// Search on right. 
				left = mid + 1;
			}
		}

		return left;
	}



	// For testing. 
	public static void main(String[] args) {
		FindPeakElement solution = new FindPeakElement();

		// Test arguments. 
		//	    int num = 24;
		//	    int target = 2;
		//	    solution.getInt(num, target);
		int[] nums = new int[]{ 1, 2, 1, 3, 5, 6, 4 };
		System.out.println(solution.findPeakElement(nums)); // 5
		nums = new int[]{ 1, 2, 3, 4, 5, 6, 8 };
		System.out.println(solution.findPeakElement(nums)); // 6
		nums = new int[]{ 2 };
		System.out.println(solution.findPeakElement(nums)); // 0


	}

}















