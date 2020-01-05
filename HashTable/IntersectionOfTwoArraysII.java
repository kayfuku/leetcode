// 
// Author: 
// Date  : January 2, 2020

package leetcode;

import java.util.Arrays;
import java.util.HashMap;

public class IntersectionOfTwoArraysII {
	// fields and classes here. 
	//private int count;

	public IntersectionOfTwoArraysII() {
		// Initialization here. 
		//this.count = 0;

	}


	// Author: votrubac + sankitgupta + kei
	// Date  : January 2, 2020
	public int[] intersect(int[] nums1, int[] nums2) {
		// Use the smaller size array.
		if (nums1.length > nums2.length) {
			return intersect(nums2, nums1);
		}
		// Count the num of numbers. 
		HashMap<Integer, Integer> m = new HashMap<>();
		for (int n : nums1) {
			m.put(n, m.getOrDefault(n, 0) + 1);
		}
		
		int k = 0;
		for (int n : nums2) {
			int count = m.getOrDefault(n, 0);
			if (count > 0) {
				nums1[k] = n;
				k++;
				m.put(n, count - 1);
			}
		}
		
		// copyOfRange() returns array of size k - 0. 
		return Arrays.copyOfRange(nums1, 0, k);
	}







	// For testing. 
	public static void main(String[] args) {
		IntersectionOfTwoArraysII solution = new IntersectionOfTwoArraysII();

		// Test arguments. 
		// int num = 24;
		// int target = 2;
		// solution.getInt(num, target);



	}

}















