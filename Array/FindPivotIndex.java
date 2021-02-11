//
// Author:
// Date : February 1, 2020

package leetcode;

public class FindPivotIndex {
  // fields and classes here.
  // private int count;

  public FindPivotIndex() {
    // Initialization here.
    // this.count = 0;

  }

  // Let's say we knew S as the sum of the numbers, and we are at index i. If we
  // knew the sum of numbers leftsum that are to the left of index i, then
  // the other sum to the right of the index would just be S - nums[i] - leftsum.
  // No need to have cumuSum array.
  // O(N) time, O(1) space.
  // Author: @awice + kei
  // Date : February 1, 2020
  public int pivotIndex(int[] nums) {
    int sum = 0;
    // Get total sum.
    for (int x : nums) {
      sum += x;
    }
    // Iterate the array nums.
    int leftsum = 0;
    for (int i = 0; i < nums.length; i++) {
      if (leftsum == sum - leftsum - nums[i]) {
        return i;
      }
      leftsum += nums[i];
    }

    return -1;
  }

  @SuppressWarnings("unused")
  // For testing.
  public static void main(String[] args) {
    FindPivotIndex solution = new FindPivotIndex();

    // Test arguments.
    // int num = 24;
    // int target = 2;
    // solution.getInt(num, target);

  }

}
