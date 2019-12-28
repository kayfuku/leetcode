// 7. Given a 32-bit signed integer, reverse digits of an integer.
// Example 1:
// Input: 123
// Output: 321
// Example 2:
// Input: -123
// Output: -321
// Example 3:
// Input: 120
// Output: 21
// Author: alvin19940815 + kei
// Date  : January 15, 2019

package whiteboard;

public class Solution {

    public int reverse(int x) {
        
        long rev = 0;
        int sign = 1;
        
        if (x > Integer.MAX_VALUE || x < Integer.MIN_VALUE) {
            return 0;
        }
        if (x < 0) {
            sign = -1;
            x *= sign;
        }
        
        while (x != 0) {
            int pop = x % 10;
            x /= 10;
            rev = rev * 10 + pop;
        }
        
        if (rev > Integer.MAX_VALUE || rev < Integer.MIN_VALUE) {
            return 0;
        }
        
        return (int) rev * sign;
    }

    public static void main(String[] args) {

        

    }

}



// Bad code. 
// public int reverse(int x) {
//    
//    String xStr = String.valueOf(x);
//    int ans = 0;
//    char[] xChars = xStr.toCharArray();
//    for (int i = 0; i < xChars.length; i++) {
//        ans += Character.getNumericValue(xChars[i]) * Math.pow(10, i);
//    }
//    
//    return rev;
// }




















