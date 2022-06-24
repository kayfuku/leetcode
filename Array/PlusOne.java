//
// Author:
// Date : February 1, 2020

package leetcode;

public class PlusOne {
  // fields and classes here.
  // private int count;

  public PlusOne() {
    // Initialization here.
    // this.count = 0;

  }

  // Author: diaa + kei
  // Date : February 1, 2020
  public int[] plusOne(int[] digits) {
    int n = digits.length;
    // Iterate from tail.
    for (int i = n - 1; i >= 0; i--) {
      if (digits[i] < 9) {
        digits[i]++;
        return digits;
      }
      // Carry over
      digits[i] = 0;
    }

    // Only if those above does not work.
    // Prepare one bigger new array.
    int[] newNumber = new int[n + 1];
    newNumber[0] = 1;

    return newNumber;
  }

  // For testing.
  @SuppressWarnings("unused")
  public static void main(String[] args) {
    PlusOne solution = new PlusOne();

    // Test arguments.
    // int num = 24;
    // int target = 2;
    // solution.getInt(num, target);

  }

}
