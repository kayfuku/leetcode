//
// Author:
// Date : August 18, 2020

package leetcode;

public class FindKthBitInNthBinaryString {
  // fields and classes here.
  // private int count;

  public FindKthBitInNthBinaryString() {
    // Initialization here.
    // this.count = 0;

  }


  // Author: _-owo-_ + kei
  // Date : August 18, 2020
  public char findKthBit(int n, int k) {
    StringBuilder sb = new StringBuilder();
    sb.append('0');
    for (int i = 0; i < n - 1; i++) {
      StringBuilder surfix = helper(sb);
      sb.append('1');
      sb.append(surfix);
    }

    return sb.charAt(k - 1);
  }

  StringBuilder helper(StringBuilder sb) {
    StringBuilder sbNew = new StringBuilder();
    // Inverse.
    for (int i = 0; i < sb.length(); i++) {
      if (sb.charAt(i) == '1') {
        sbNew.append('0');
      } else {
        sbNew.append('1');
      }
    }

    return sbNew.reverse();
  }



  // For testing.
  @SuppressWarnings("unused")
  public static void main(String[] args) {
    FindKthBitInNthBinaryString solution = new FindKthBitInNthBinaryString();

    // Test arguments.
    // int num = 24;
    // int target = 2;
    // solution.getInt(num, target);

    int n = 2;
    int k = 3;
    System.out.println(solution.findKthBit(n, k));



  }



  public void dummyMethod() {



  }



  public void dummyMethod2() {



  }



}


