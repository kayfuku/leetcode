// 
// Author: 
// Date  : 

package leetcode;

public class GuessNumberHigherOrLower {
	// fields and classes here. 
	//private int count;

	public GuessNumberHigherOrLower() {
		// Initialization here. 
		//this.count = 0;
		
	}
	
	/* The guess API is defined in the parent class GuessGame.
	 * @param num, your guess
	 * @return -1 if my number is lower, 1 if my number is higher, otherwise return 0
	 *    int guess(int num);
	 */

	
	// Binary Search. 
	// Author: kei
	// Date  : August 8, 2019
    public int guessNumber(int n) {
    	int left = 0;
    	int right = n;
    	
    	while (left <= right) {
			int mid = left + (right - left) / 2;
			
			// Imaginary API. 
//			int ret = guess(mid);
			int ret = 0; // Just for preventing compiler error. 
			if (ret == 0) {
				return mid;
			}
			
			if (ret == -1) {
				right = mid - 1;
			} else {
				left = mid + 1;
			}
		}

		return left;
	}



	
	
	

	// For testing. 
	public static void main(String[] args) {
		GuessNumberHigherOrLower solution = new GuessNumberHigherOrLower();

		// Test arguments. 
		// int num = 24;
		// int target = 2;
		// solution.getInt(num, target);



	}

}















