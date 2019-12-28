// 
// Author: 
// Date  : 

package leetcode;

public class SearchInSortedArrayOfUnknownSize {
	// fields and classes here. 
	//private int count;

	public SearchInSortedArrayOfUnknownSize() {
		// Initialization here. 
		//this.count = 0;

	}


	// To use binary search, we need to find the search space defined by left and right. 
	// Find right by moving right exponentially. Once right is found, left is previous right. 
	// Then do binary search.
	// Author: touchdown + kei
	// Date  : August 12, 2019
//	public int search(ArrayReader reader, int target) {
//		int right = 1;
//		// Be careful with '<', not '>'! Continuing condition. 
//		while (reader.get(right) < target) {
//			right = right << 1;
//		}
//		int left = right >> 1;
//
//		// Binary Search. 
//		// R=M-1 ver. because 'right' can be the index of 'target'. 
//		while (left <= right) {
//			int mid = left + (right - left) / 2;
//			if (reader.get(mid) == target) {
//				return mid;
//			}
//			
//			if (reader.get(mid) > target) {
//				right = mid - 1;
//			} else {
//				left = mid + 1;
//			}
//		}
//
//		return -1;
//	}







	// For testing. 
	public static void main(String[] args) {
		SearchInSortedArrayOfUnknownSize solution = new SearchInSortedArrayOfUnknownSize();

		// Test arguments. 
		// int num = 24;
		// int target = 2;
		// solution.getInt(num, target);



	}

}















