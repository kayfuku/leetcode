// 
// Author: 
// Date  : July 21, 2019

package leetcode;

public class StringToInteger {
	// fields and classes here. 
	//private int count;

	public StringToInteger() {
		// Initialization here. 
		//this.count = 0;
	}

	
	// Basically, iterating through the 'str', and if it is not valid, then 
	// return 0 immediately, otherwise return the value. 
	// I'm going to use pointer like variable. 
	// O(N) time, where N is the input string length. 
	// O(1) space. 
	// Author: jinwu
	// Date  : July 21, 2019
    public int myAtoi(String str) {
    	// Common phrase to handle user input strings. 
    	// This also removes the input that is only white spaces. 
    	str = str.trim();
    	if (str.isEmpty()) {
    		// ex: "", " ", "	"
			return 0;
		}

    	int sign = 1, i = 0;
    	
    	// Handle the case with a sign. 
    	if (str.charAt(0) == '+' || str.charAt(0) == '-') {
			sign = (str.charAt(0) == '-') ? -1 : 1;
			if (str.length() < 2 || !Character.isDigit(str.charAt(1))) {
				// Only a sign or sign plus no digit character. ex: "-", "-w", or "- "
				return 0;				
			}
    		// Advance the pointer. 
    		i++;
		}
    	// Assert that it is a valid whole number. 
    	// Therefore return some value from now on. 
    	
    	// Handle with the numbers. 
    	int n = 0;
//    	long n = 0; // 2. Use long type. 
    	while (i < str.length()) {
			if (Character.isDigit(str.charAt(i))) {
				// Convert character digit to int. 
				int d = str.charAt(i) - '0';
				
				if (n > (Integer.MAX_VALUE - d) / 10) {
					// Which means if I add d to n and divide it by 10, then 
					// it is going to be an integer overflow. 
					// I need to handle integer overflow like this because I only 
					// have 32 bits. After n becomes Integer.MAX_VALUE, it is too late. 
					// Or I have to use long type instead. 
					n = (sign == -1) ? Integer.MIN_VALUE : Integer.MAX_VALUE;
					return n;
				}
				
				n = n * 10 + d;
				
				// 2. Use long type. It is easier to understand, but needs more space. 
//				n = n * 10 + d;
//				
//				// Detect the integer overflow. 
//				if (n > Integer.MAX_VALUE) {
//					n = (sign == -1) ? Integer.MIN_VALUE : Integer.MAX_VALUE;
//					return (int) n;
//				}
			} else {
				break;
			}
    		
    		i++;   		
		}
    	
		return sign * n;
		// 2. Use long type. 
//		return sign * (int) n;
	}

    
    
	// For testing. 
	public static void main(String[] args) {
		StringToInteger solution = new StringToInteger();

		// Test arguments. 
		// int num = 24;
		// int target = 2;
		// solution.getInt(num, target);
		
		String str = "-91283472332";
		System.out.println(solution.myAtoi(str)); // -2147483648



	}

}















