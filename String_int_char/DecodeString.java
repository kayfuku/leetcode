//
// Author:
// Date : June 11, 2020

package leetcode;

import java.util.ArrayDeque;
import java.util.Deque;

public class DecodeString {
  // fields and classes here.
  // private int count;

  public DecodeString() {
    // Initialization here.
    // this.count = 0;

  }


  // Author: @sampsonchan + kei
  // Date : June 11, 2020
  public String decodeString(String s) {
    Deque<Integer> intStack = new ArrayDeque<>();
    Deque<StringBuilder> strStack = new ArrayDeque<>();
    StringBuilder sb = new StringBuilder();
    int k = 0;
    for (char ch : s.toCharArray()) {
      if (Character.isDigit(ch)) {
        k = k * 10 + ch - '0';
      } else if (ch == '[') {
        // Push when seeing opening bracket.
        // Save k for this bracket and the characters before this bracket.
        intStack.push(k);
        strStack.push(sb);
        // Start to save the characters inside this bracket.
        sb = new StringBuilder();
        k = 0;
      } else if (ch == ']') {
        // Pop when seeing closing bracket.
        // Take out the characters inside the brackets.
        // Note that tmp gets the copy of StringBuilder of sb, not a reference to sb.
        StringBuilder tmp = sb;
        // Take out the characters before the brackets.
        sb = strStack.pop();
        // Append tmp k times.
        for (k = intStack.pop(); k > 0; --k) {
          sb.append(tmp);
        }
      } else {
        sb.append(ch);
      }
    }

    return sb.toString();
  }



  // For testing.
  @SuppressWarnings("unused")
  public static void main(String[] args) {
    DecodeString solution = new DecodeString();

    // Test arguments.
    // int num = 24;
    // int target = 2;
    // solution.getInt(num, target);



  }



  public void dummyMethod() {



  }



  public void dummyMethod2() {



  }



}


