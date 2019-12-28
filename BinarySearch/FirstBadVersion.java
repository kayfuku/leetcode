// 
// Author: 
// Date  : 

package leetcode;

public class FirstBadVersion {
	// fields and classes here. 
	//private int count;

	public FirstBadVersion() {
		// Initialization here. 
		//this.count = 0;
		
	}

	
	// R=M version. 
	// https://leetcode.com/problems/first-bad-version/solution/
	// O(logN) time, O(1) space. 
	// Author: kei
	// Date  : August 8, 2019
    public int firstBadVersion(int n) {
    	int left = 1;
    	// This looks arr.length - 1, but it works because for this problem  
    	// there is no case where x is not in the list and would be the last element. 
    	int right = n;
//    	int right = n + 1; // NG. n: Integer.MAX_VALUE. 
    	
    	while (left < right) {
			int mid = left + (right - left) / 2;
			
			// isBadVersion() is an imaginary API. 
//			if (isBadVersion(mid)) {
			if (true) { // Temp. 
				right = mid;
			} else {
				left = mid + 1;
			}
		}

		return left;
	}



	
	
	

	// For testing. 
	public static void main(String[] args) {
		FirstBadVersion solution = new FirstBadVersion();

		// Test arguments. 
		// int num = 24;
		// int target = 2;
		// solution.getInt(num, target);



	}

}















