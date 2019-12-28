// 9. Palindrome Number

// Determine whether an integer is a palindrome. An integer is a palindrome when it reads the same backward as forward.

// Example 1:

// Input: 121
// Output: true
// Example 2:

// Input: -121
// Output: false
// Explanation: From left to right, it reads -121. From right to left, it becomes 121-. Therefore it is not a palindrome.
// Example 3:

// Input: 10
// Output: false
// Explanation: Reads 01 from right to left. Therefore it is not a palindrome.
// Follow up:

// Coud you solve it without converting the integer to a string?

// Author: LeetCode + kei
// Date  : January 15, 2019

package whiteboard;

public class Solution {

    // Solution 1
    // O(1) space. 
    public static boolean isPalindrome(int x) {
        
        if (x < 0 || (x % 10 == 0 && x != 0)) {
            return false;
        }
        
        int rev = 0;
        
        while (x > rev) {
            rev = rev * 10 + x % 10;
            x /= 10;
        }
        
        return x == rev || x == rev / 10;       
    }

    // Solution 2
    // O(n) space, where n is number of digits. 
    public static boolean isPalindrome(int x) {
        
        String xStr = Integer.toString(x);
        char[] chars = xStr.toCharArray();
        
        int i = 0;
        int j = chars.length - 1;
        while (i < j) {
            if (chars[i] != chars[j]) {
                return false;
            }
            i++;
            j--;
        }
        
        return true;
    }

    public static void main(String[] args) {
        
        isPalindrome(-121);



    }

}



















