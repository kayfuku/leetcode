//
// Author:
// Date : January 29, 2020

package leetcode;

public class ReverseString {
  // fields and classes here.
  // private int count;

  public ReverseString() {
    // Initialization here.
    // this.count = 0;

  }


  // 1. Recursion
  // O(N) time, O(N) space.
  // Author: @liaison and @andvary + kei
  // Date : January 29, 2020
  public void reverseString(char[] s) {
    helper(s, 0, s.length - 1);
  }

  public void helper(char[] s, int left, int right) {
    if (left >= right) {
      return;
    }
    // Swap left and right.
    char tmp = s[left];
    s[left] = s[right];
    s[right] = tmp;

    left++;
    right--;
    helper(s, left, right);
  }


  // 2. Iteration.
  // O(N) time, O(1) space.
  // Author: @liaison and @andvary + kei
  // Date : January 29, 2020
  public void reverseString2(char[] s) {
    int left = 0, right = s.length - 1;
    while (left < right) {
      // Swap left and right.
      char tmp = s[left];
      s[left] = s[right];
      s[right] = tmp;

      left++;
      right--;
    }
  }



  // No In-place!
  // Author: kei
  // Date : January 29, 2020
  public void reverseStringNG(char[] s) {
    if (s == null) {
      return;
    }
    reverseString(0, s);
  }

  private void reverseString(int i, char[] s) {
    if (i >= s.length) {
      return;
    }

    reverseString(i + 1, s);
    System.out.println(s[i]);
  }


  // For testing.
  public static void main(String[] args) {
    ReverseString solution = new ReverseString();

    // Test arguments.
    // int num = 24;
    // int target = 2;
    // solution.getInt(num, target);
    char[] s = new char[] {'h', 'e', 'l', 'l', 'o'};
    solution.reverseString(s);



  }

}


