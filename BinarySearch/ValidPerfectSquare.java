// 
// Author: 
// Date  : 

package leetcode;

public class ValidPerfectSquare {
	// fields and classes here. 
	//private int count;

	public ValidPerfectSquare() {
		// Initialization here. 
		//this.count = 0;

	}


	// https://leetcode.com/problems/valid-perfect-square/discuss/83902/Java-Three-Solutions-135..-SequenceBinary-SearchNewton


	// 1. Math property. 
	// A square number is 1 + 3 + 5 + 7 + ... 
	// O(sqrt(N)) time. 
	// Author: coolguy + kei (AC)
	// Date  : August 12, 2019
	public boolean isPerfectSquare(int num) {
		if (num < 1) return false;
		for (int i = 1; num > 0; i += 2)
			num -= i;
		return num == 0;
	}


	// 2. Binary Search. 
	// O(logN) time, O(1) space. 
	// Author: coolguy + kei (AC)
	// Date  : August 12, 2019
	public boolean isPerfectSquare2(int num) {
		if (num <= 0) {
			return false;
		}

		// Type is long. 
		long left = 0, right = num;
		while (left <= right) {
			// Type is long because otherwise 'mid * mid' would cause a problem. 
			long mid = left + (right - left) / 2;
			if (mid * mid == num) {
				return true;
			}

			if (mid * mid > num) {
				right = mid - 1;
			} else {
				left = mid + 1;
			}
		}

		return false;
	}


	// 3. Newton Method. 
	// Find square root of num and square it
    // square == num ? true : false;
	// O(1) time. 
	// Author: bayleaf + kei (AC)
	// Date  : August 12, 2019
	public boolean isPerfectSquare3(int num) { 	    
	    long t = num;
	    while (t * t > num) {
	        t = (t + num / t) / 2;
	    }
	    return t * t == num;
	}






	// For testing. 
	public static void main(String[] args) {
		ValidPerfectSquare solution = new ValidPerfectSquare();

		// Test arguments. 
		// int num = 24;
		// int target = 2;
		// solution.getInt(num, target);
		int num = 5;
		System.out.println(solution.isPerfectSquare(num));



	}

}















