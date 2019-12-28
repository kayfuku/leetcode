// 
// Author: 
// Date  : 

package leetcode;

public class FindSmallestLetterGreaterThanTarget {
	// fields and classes here. 
	//private int count;

	public FindSmallestLetterGreaterThanTarget() {
		// Initialization here. 
		//this.count = 0;
		
	}

	
	// Binary Search. R=M-1 Next Int. 
	// O(logN) time. 
	// Author: @awice + kei (AC)
	// Date  : August 12, 2019
    public char nextGreatestLetter(char[] letters, char target) {
    	int left = 0;
    	int right = letters.length - 1;
    	while (left <= right) {
			int mid = left + (right - left) / 2;
			
			if (letters[mid] > target) {
				right = mid - 1;
			} else {
				left = mid + 1;
			}
		}

		return letters[left % letters.length];
	}



	
	
	

	// For testing. 
	public static void main(String[] args) {
		FindSmallestLetterGreaterThanTarget solution = new FindSmallestLetterGreaterThanTarget();

		// Test arguments. 
		// int num = 24;
		// int target = 2;
		// solution.getInt(num, target);



	}

}















