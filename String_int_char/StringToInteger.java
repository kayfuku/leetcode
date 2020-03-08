//
// Author:
// Date : July 21, 2019

package leetcode;

public class StringToInteger {
  // fields and classes here.
  // private int count;

  public StringToInteger() {
    // Initialization here.
    // this.count = 0;
  }


  // Basically, iterating through the 'str', and if it is not valid, then
  // return 0 immediately, otherwise return the value.
  // I'm going to use pointer like variable.
  // O(N) time, where N is the input string length.
  // O(1) space.
  // Author: jinwu + kei
  // Date : July 21, 2019
  public int myAtoi(String str) {
    // Common phrase to handle user input strings.
    // This also removes the input that is only white spaces.
    str = str.trim();
    if (str.isEmpty()) {
      // ex: "", " ", "\t"
      return 0;
    }
    // Assert that str does not contain leading and trailing whitespace.

    int sign = 1, i = 0;

    // Handle the case with a sign.
    if (str.charAt(0) == '+' || str.charAt(0) == '-') {
      sign = (str.charAt(0) == '-') ? -1 : 1;
      if (str.length() < 2) {
        // Only a sign.
        return 0;
      }
      // Advance the pointer.
      i++;
    }
    // If the input is like "-a", then the following code handles it. (n = 0)

    // 'n' Handles with the numbers.
    int n = 0;
    // long n = 0; // 2. Use long type.
    while (i < str.length()) {
      // If there is a non number character, then stop it.
      // ex: "4193 with words" should be returned 4193.
      if (Character.isDigit(str.charAt(i))) {
        // Convert character digit to int.
        int d = str.charAt(i) - '0';

        // Handle integer overflow issue before the line 'n = n * 10 + d;'.
        if (n > (Integer.MAX_VALUE - d) / 10) {
          // Which means if I add d to n and multiple it by 10, then
          // it is going to be an integer overflow.
          // I need to handle integer overflow like this because I only
          // have 32 bits. After n becomes Integer.MAX_VALUE, it is too late.
          // Or I have to use long type instead.
          // Note that d must be positive when you use the condition above.
          n = (sign == -1) ? Integer.MIN_VALUE : Integer.MAX_VALUE;
          return n;
        }

        // This could overflow, we need to handle it before this.
        n = n * 10 + d;

        // 2. Use long type. It is easier to understand, but needs more space.
        // n = n * 10 + d;
        //
        // // Detect the integer overflow.
        // if (n > Integer.MAX_VALUE) {
        // n = (sign == -1) ? Integer.MIN_VALUE : Integer.MAX_VALUE;
        // return (int) n;
        // }
      } else {
        break;
      }

      i++;
    }

    return sign * n;
    // 2. Use long type.
    // return sign * (int) n;
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


