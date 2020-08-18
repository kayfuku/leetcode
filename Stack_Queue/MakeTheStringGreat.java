//
// Author:
// Date : August 17, 2020

package leetcode;

public class MakeTheStringGreat {
  // fields and classes here.
  // private int count;

  public MakeTheStringGreat() {
    // Initialization here.
    // this.count = 0;

  }


  // Author: JOHNKRAM + kei
  // Date : August 17, 2020
  public String makeGood(String s) {
    StringBuilder stack = new StringBuilder();
    for (char c : s.toCharArray()) {
      // stack.charAt(stack.length() - 1): peek()
      if (stack.length() == 0 || Math.abs(c - stack.charAt(stack.length() - 1)) != 'a' - 'A') {
        // push()
        stack.append(c);
      } else {
        // pop()
        stack.deleteCharAt(stack.length() - 1);
      }
    }

    return stack.toString();
  }



  // For testing.
  @SuppressWarnings("unused")
  public static void main(String[] args) {
    MakeTheStringGreat solution = new MakeTheStringGreat();

    // Test arguments.
    // int num = 24;
    // int target = 2;
    // solution.getInt(num, target);

    // System.out.println('a' - 'A'); // 32

    String s = "leEeetcode";
    System.out.println(solution.makeGood(s));



  }



  public void dummyMethod() {



  }



  public void dummyMethod2() {



  }



}


