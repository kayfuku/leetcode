//
// Author:
// Date : May 21, 2019

package leetcode;



public class ReverseInteger {

  // Time Complexity: O(log(x)). There are roughly log_10(x) digits in x.
  // Space Complexity: O(1).
  public static int reverse(int x) {
    int rev = 0;
    while (x != 0) {
      int pop = x % 10;
      x /= 10;
      if (rev > Integer.MAX_VALUE / 10 || (rev == Integer.MAX_VALUE / 10 && pop > 7)) {
        return 0;
      }
      if (rev < Integer.MIN_VALUE / 10 || (rev == Integer.MIN_VALUE / 10 && pop < -8)) {
        return 0;
      }
      rev = rev * 10 + pop;
    }

    return rev;
  }


  public int reverse2(int x) {
    // Integers pop and reverse pattern.
    // Use type long instead of int for overflow.
    long rev = 0;
    while (x != 0) {
      int pop = x % 10;
      x /= 10;
      rev = rev * 10 + pop;
    }

    // In case of overflow.
    if (rev > Integer.MAX_VALUE || rev < Integer.MIN_VALUE) {
      return 0;
    }

    // Convert back to int.
    return (int) rev;
  }


  public static void main(String[] args) {
    ReverseInteger solution = new ReverseInteger();

    int num = 123;
    int rev = solution.reverse2(num);
    System.out.println(rev);



  }

}


