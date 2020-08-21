//
// Author:
// Date : August 17, 2020

package leetcode;

public class MinimumOperationsToMakeArrayEqual {
  // fields and classes here.
  // private int count;

  public MinimumOperationsToMakeArrayEqual() {
    // Initialization here.
    // this.count = 0;

  }


  // Author: kei (AC)
  // Date : August 17, 2020
  public int minOperations(int n) {
    int k = n / 2;
    return (n % 2 != 0) ? k * (1 + k) : k * k;
  }



  // For testing.
  @SuppressWarnings("unused")
  public static void main(String[] args) {
    MinimumOperationsToMakeArrayEqual solution = new MinimumOperationsToMakeArrayEqual();

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


