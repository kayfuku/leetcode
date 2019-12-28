// 
// Author: 
// Date  : 

package leetcode;

public class SqrtX {
	// fields and classes here. 
	//private int count;

	public SqrtX() {
		// Initialization here. 
		//this.count = 0;

	}


	// Binary Search. This is better. 
	// O(lgX) = O(32) = O(1) time because type int is limited. 
	// Author: @liaison and @andvary + kei (AC)
	// Date  : August 31, 2019
	public int mySqrt(int x) {
		// If x is less than 1, it is corner case. 
		if (x < 2) {
			return x;
		}

		long squared;
		int guess, left = 1, right = x;
		while (left <= right) {
			guess = left + (right - left) / 2;
			squared = (long) guess * guess;
			if (squared == x) {
				return guess;
			}

			if (squared > x) {
				right = guess - 1;
			} else {
				left = guess + 1;
			}
		}

		return left - 1;
	}
	
	

	// No need to review. 
	// https://leetcode.com/explore/learn/card/binary-search/125/template-i/950/discuss/25198/3-JAVA-solutions-with-explanation
	// Look for the critical point: i * i <= x && (i + 1) * (i + 1) > x
	// A little trick is using i <= x / i for comparison, 
	// instead of i * i <= x, to avoid exceeding integer upper limit.
	// O(lgX) = O(32) = O(1) time. 
	// 
	// Author: Cheng_Zhang + kei (AC)
	// Date  : August 8, 2019
	public int mySqrt2(int x) {
		if (x <= 0) {
			return 0;
		}
		// Binary Search. 
		int left = 1, right = x;
		while (left <= right) {
			int mid = left + (right - left) / 2;
			// Be careful with inequalities. 
			if (mid <= x / mid && mid + 1 > x / (mid + 1)) {
				return mid;
			}

			if (mid > x / mid) {
				right = mid - 1;
			} else {
				left = mid + 1;
			}			
		}

		return left;
	}


	// For testing. 
	public static void main(String[] args) {
		SqrtX solution = new SqrtX();

		// Test arguments. 
		// int num = 24;
		// int target = 2;
		// solution.getInt(num, target);
		int x = 4;
		System.out.println(solution.mySqrt(x)); // 2
		x = 2;
		System.out.println(solution.mySqrt(x)); // 1
		x = 1;
		System.out.println(solution.mySqrt(x)); // 1
		x = 16;
		System.out.println(solution.mySqrt(x)); // 4
		x = 15;
		System.out.println(solution.mySqrt(x)); // 3



	}

}















