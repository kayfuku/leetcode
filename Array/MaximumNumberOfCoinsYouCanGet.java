//
// Author:
// Date : August ?, 2020

package leetcode;

import java.util.Arrays;

public class MaximumNumberOfCoinsYouCanGet {
  // fields and classes here.
  // private int count;

  public MaximumNumberOfCoinsYouCanGet() {
    // Initialization here.
    // this.count = 0;

  }


  // Author: kei (AC)
  // Date : August 25, 2020
  public int maxCoins(int[] piles) {
    Arrays.sort(piles);
    int coins = 0;
    int i = 0;
    int j = piles.length - 1;
    while (i < j) {
      j--;
      coins += piles[j];

      i++;
      j--;
    }

    return coins;
  }



  // For testing.
  @SuppressWarnings("unused")
  public static void main(String[] args) {
    MaximumNumberOfCoinsYouCanGet solution = new MaximumNumberOfCoinsYouCanGet();

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


