// 
// Author: 
// Date  : 

package leetcode;

import java.util.ArrayList;
import java.util.List;

public class FindKClosestElements {
	// fields and classes here. 
	//private int count;

	public FindKClosestElements() {
		// Initialization here. 
		//this.count = 0;
		
	}

	
	// The list we want is [A1, A2, ... x, ... Ak] (len: k). 
	// Using Binary Search, we're going to find A1. 
	// 1. Pick up a 'mid' and check if the condition is met. 
	//    'mid' is our attempt to find A1. 
	// 2. The elements can be duplicates, so we use Continuous Binary Search. 
	//    If we use R=M-1 ver. Binary Search, 'idx' is not necessary for this problem. 
	// 
	// Author: StefanPochmann + kei
	// Date  : August 12, 2019
	public List<Integer> findClosestElements(int[] arr, int k, int x) {
    	int left = 0;
    	// For this problem, our target is the leftmost index, 
    	// so 'idx' is not necessary (no need to return -1). 
    	// R=M-1 ver. len - 1. 
    	// right = arr.length - k; causes index out of bound because 
    	// 'mid + k' is out of bound when 'mid' is 'arr.length - k'. 
    	// 'mid' can be 'right' inside the loop and should not be 'arr.length - k'. 
		int right = arr.length - k - 1;
		
		while (left <= right) {
			int mid = left + (right - left) / 2;
			
			if (x - arr[mid] <= arr[mid + k] - x) {
				// Search on the left. 
				right = mid - 1;
			} else {
				// Search on the right.
				left = mid + 1;
			}
		}
		// The leftmost index is 'left'. 
		
		List<Integer> ret = new ArrayList<>();
		for (int i = left; i < left + k; i++) {
			ret.add(arr[i]);
		}
		
		return ret;
	}
	
	
	
	

	// For testing. 
	public static void main(String[] args) {
		FindKClosestElements solution = new FindKClosestElements();

		// Test arguments. 
		// int num = 24;
		// int target = 2;
		// solution.getInt(num, target);
		int[] arr = new int[]{ 0, 0, 1, 2, 3, 3, 4, 7, 7, 8 };
		int k = 3;
		int x = 5;
		System.out.println(solution.findClosestElements(arr, k, x));
		


	}

}















