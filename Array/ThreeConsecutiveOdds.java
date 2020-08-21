//
// Author:
// Date : August ?, 2020

package leetcode;

public class ThreeConsecutiveOdds {
  // fields and classes here.
  // private int count;

  public ThreeConsecutiveOdds() {
    // Initialization here.
    // this.count = 0;

  }


  // Author: xiaowuc + kei
  // Date : August 18, 2020
  public boolean threeConsecutiveOdds(int[] arr) {
    for (int i = 0; i + 2 < arr.length; i++) {
      if (arr[i] % 2 != 0 && arr[i + 1] % 2 != 0 && arr[i + 2] % 2 != 0) {
        return true;
      }
    }

    return false;
  }



  // For testing.
  @SuppressWarnings("unused")
  public static void main(String[] args) {
    ThreeConsecutiveOdds solution = new ThreeConsecutiveOdds();

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


