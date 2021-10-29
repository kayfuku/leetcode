//
// Author:
// Date : June ?, 2020

package leetcode;

public class ShuffleTheArray {
  // fields and classes here.
  // private int count;

  public ShuffleTheArray() {
    // Initialization here.
    // this.count = 0;

  }

  // Author: JOHNKRAM + kei
  // Date : June 9, 2020
  public int[] shuffle(int[] nums, int n) {
    // ArrayList is a little tricky to convert to array in Java,
    // so we use array in the first place.
    int[] ret = new int[n * 2];
    int j = 0;
    for (int i = 0; i < n; i++) {
      ret[j] = nums[i];
      j++;
      ret[j] = nums[i + n];
      j++;
    }

    return ret;
  }

  // For testing.
  @SuppressWarnings("unused")
  public static void main(String[] args) {
    ShuffleTheArray solution = new ShuffleTheArray();

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
