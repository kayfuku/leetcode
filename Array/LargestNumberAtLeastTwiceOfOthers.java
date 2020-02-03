//
// Author:
// Date : February 1, 2020

package leetcode;

public class LargestNumberAtLeastTwiceOfOthers {
  // fields and classes here.
  // private int count;

  public LargestNumberAtLeastTwiceOfOthers() {
    // Initialization here.
    // this.count = 0;

  }


  // Author: @awice + kei
  // Date : February 1, 2020
  public int dominantIndex(int[] nums) {
    // Find max index. We do not even need to keep track of max.
    int maxIndex = 0;
    for (int i = 0; i < nums.length; i++) {
      if (nums[i] > nums[maxIndex]) {
        maxIndex = i;
      }
    }

    // Iterate again and check if there is a counter evidence.
    for (int i = 0; i < nums.length; i++) {
      if (maxIndex != i && nums[maxIndex] < 2 * nums[i]) {
        return -1;
      }
    }

    return maxIndex;
  }



  // For testing.
  public static void main(String[] args) {
    LargestNumberAtLeastTwiceOfOthers solution = new LargestNumberAtLeastTwiceOfOthers();

    // Test arguments.
    // int num = 24;
    // int target = 2;
    // solution.getInt(num, target);



  }

}


