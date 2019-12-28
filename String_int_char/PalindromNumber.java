// 
// Author: 
// Date  : May 21, 2019

package leetcode;


public class PalindromNumber {

	
	// Time complexity : O(log_10(x)). We divided the input by 10 for every iteration, 
	// so the time complexity is O(log_{10}(n)).
	// Space complexity : O(1).
	public static boolean isPalindrome(int x) {
		// Special cases:
		// As discussed above, when x < 0, x is not a palindrome.
		// Also if the last digit of the number is 0, in order to be a palindrome,
		// the first digit of the number also needs to be 0.
		// Only 0 satisfy this property.
		if (x < 0 || (x % 10 == 0 && x != 0)) {
			return false;
		}

		int rev = 0;
		
		// The first half should be the same as the second half.  
		while (x > rev) {
			rev = rev * 10 + x % 10;
			x /= 10;    		
		}

		// When the length is an odd number, we can get rid of the middle digit by revertedNumber/10
		// For example when the input is 12321, at the end of the while loop we get x = 12, revertedNumber = 123,
		// since the middle digit doesn't matter in palidrome(it will always equal to itself), we can simply get rid of it.
		return x == rev || x == rev / 10;
	}


	// Review
	public static boolean isPalindromeR(int x) {
		int rev = 0;

		if (x < 0 || (x != 0 && x % 10 == 0)) {
			return false;
		}

		// The first half should be the same as the second half.  
		while (x > rev) {
			int v = x % 10;
			x /= 10;
			rev = rev * 10 + v;
		}

		return x == rev || x == rev / 10;
	}



	public static void main(String[] args) {



	}

}



















