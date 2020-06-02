//
// Author:
// Date : May 31, 2020

package leetcode;

import java.util.Arrays;

public class MaximumAreaOfPieceOfCakeAfterHorizontalAndVerticalCuts {
  // fields and classes here.
  // private int count;

  public MaximumAreaOfPieceOfCakeAfterHorizontalAndVerticalCuts() {
    // Initialization here.
    // this.count = 0;

  }


  // Author: uwi + kei
  // Date : May 31, 2020
  public int maxArea(int h, int w, int[] horizontalCuts, int[] verticalCuts) {
    Arrays.sort(horizontalCuts);
    Arrays.sort(verticalCuts);
    int hmax = Math.max(horizontalCuts[0], h - horizontalCuts[horizontalCuts.length - 1]);
    for (int i = 0; i + 1 < horizontalCuts.length; i++) {
      hmax = Math.max(hmax, horizontalCuts[i + 1] - horizontalCuts[i]);
    }
    int vmax = Math.max(verticalCuts[0], w - verticalCuts[verticalCuts.length - 1]);
    for (int i = 0; i + 1 < verticalCuts.length; i++) {
      vmax = Math.max(vmax, verticalCuts[i + 1] - verticalCuts[i]);
    }
    return (int) ((long) hmax * vmax % 1000000007);
  }



  // For testing.
  @SuppressWarnings("unused")
  public static void main(String[] args) {
    MaximumAreaOfPieceOfCakeAfterHorizontalAndVerticalCuts solution =
        new MaximumAreaOfPieceOfCakeAfterHorizontalAndVerticalCuts();

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


