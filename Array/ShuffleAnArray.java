package leetcode;

import java.util.Random;

public class ShuffleAnArray {
  // fields and classes here.
  // private int count;

  // Fisher-Yates Algorithm
  // Author: + kei
  // Date : October 29, 2021
  private int[] array;
  private int[] original;

  Random rand = new Random();

  public ShuffleAnArray() {
  }

  public ShuffleAnArray(int[] nums) {
    array = nums;
    original = nums.clone();
  }

  public int[] shuffle() {
    for (int i = 0; i < array.length; i++) {
      // Swap i with j including i.
      int j = randRange(i, array.length);
      swapAt(i, j);
    }
    return array;
  }

  private void swapAt(int i, int j) {
    int temp = array[i];
    array[i] = array[j];
    array[j] = temp;
  }

  private int randRange(int min, int max) {
    // Pick up a random index between min and the elem right before max.
    return rand.nextInt(max - min) + min;
  }

  public int[] reset() {
    array = original;
    original = original.clone();
    return original;
  }

  /**
   * Your Solution object will be instantiated and called as such: Solution obj =
   * new Solution(nums); int[] param_1 = obj.reset(); int[] param_2 =
   * obj.shuffle();
   */

  // For testing.
  @SuppressWarnings("unused")
  public static void main(String[] args) {
    ShuffleAnArray solution = new ShuffleAnArray();

    // Test arguments.
    // int num = 24;
    // int target = 2;
    // System.out.println(solution.getInt(num, target));

    System.out.println("\ndone.");
  }

}
