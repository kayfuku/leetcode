//
// Author:
// Date : May 31, 2020

package leetcode;

import java.util.Arrays;

public class MaximumProductOfTwoElementsInArray {
  // fields and classes here.
  // private int count;

  public MaximumProductOfTwoElementsInArray() {
    // Initialization here.
    // this.count = 0;

  }


  // Author: uwi
  // Date : May 31, 2020
  public int maxProduct(int[] nums) {
    Arrays.sort(nums);
    return (nums[nums.length - 1] - 1) * (nums[nums.length - 2] - 1);
  }



  // For testing.
  @SuppressWarnings("unused")
  public static void main(String[] args) {
    MaximumProductOfTwoElementsInArray solution = new MaximumProductOfTwoElementsInArray();

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


