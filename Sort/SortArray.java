//
// Author:
// Date  : September 12, 2020

package leetcode;

import java.util.Arrays;

public class SortArray {
  // fields and classes here.
  // private int count;

  public SortArray() {
    // Initialization here.
    // this.count = 0;

  }


  // Insertion Sort
  // Author: kei (AC)
  // Date  : September 12, 2020
  public int[] sortArray(int[] nums) {
    // Note that i starts from 1, not 0. 
    for (int i = 1; i < nums.length; i++) {
      int j = i;
      // nums[j - 1] is fine because j starts from 1. 
      while (j > 0 && nums[j - 1] > nums[j]) {
        swap(nums, j, j - 1);
        j--;
      }
    }

    return nums;
  }

  void swap(int[] nums, int i, int j) {
    int temp = nums[i];
    nums[i] = nums[j];
    nums[j] = temp;
  }

  // Quicksort (Worst case: O(N^2) (Java 8, 11))
  // Author: kei (AC)
  // Date  : September 12, 2020
  public int[] sortArray2(int[] nums) {
    Arrays.sort(nums);
    return nums;
  }

  


  // For testing.
  @SuppressWarnings("unused")
  public static void main(String[] args) {
    SortArray solution = new SortArray();

    // Test arguments.
    // int num = 24;
    // int target = 2;
    // solution.getInt(num, target);



  }




}


