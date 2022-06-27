//
// Author:
// Date : February 2, 2020

package leetcode;

import java.math.BigInteger;

public class AddBinary {
  // fields and classes here.
  // private int count;

  public AddBinary() {
    // Initialization here.
    // this.count = 0;

  }

  // 1. Bit by bit approach
  // Author: @liaison and @andvary + kei
  // Date : February 4, 2020
  public String addBinary(String a, String b) {
    int n = a.length(), m = b.length();
    if (n < m) {
      // Make longer one first.
      return addBinary(b, a);
    }
    // Assert that a is longer than or equal to b.

    StringBuilder ans = new StringBuilder();
    int sum = 0, j = m - 1;
    for (int i = n - 1; i >= 0; i--) {
      if (a.charAt(i) == '1') {
        sum++;
      }
      // j could be negative, so take care of it.
      if (j > -1 && b.charAt(j) == '1') {
        sum++;
      }
      j--;

      // Check the LSB in binary number.
      if (sum % 2 == 1) {
        ans.append('1');
      } else {
        ans.append('0');
      }

      // Shift to the right. (Cut the LSB)
      sum /= 2;
    }

    if (sum == 1) {
      ans.append('1');
    }
    ans.reverse();

    return ans.toString();
  }

  // 2. Bit manipulation approach
  // Author: @liaison and @andvary + kei
  // Date : February 4, 2020
  public String addBinary2(String a, String b) {
    // Btw, BigInteger can represent up to 2^500M.
    BigInteger x = new BigInteger(a, 2);
    BigInteger y = new BigInteger(b, 2);
    BigInteger zero = new BigInteger("0", 2);
    BigInteger carry, answer;

    while (y.compareTo(zero) != 0) {
      answer = x.xor(y);
      carry = x.and(y).shiftLeft(1);
      x = answer;
      y = carry;
    }

    return x.toString(2);
  }

  // For testing.
  @SuppressWarnings("unused")
  public static void main(String[] args) {
    AddBinary solution = new AddBinary();

    // Test arguments.
    // int num = 24;
    // int target = 2;
    // solution.getInt(num, target);

  }

}
