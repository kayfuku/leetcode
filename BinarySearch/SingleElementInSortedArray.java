package leetcode;

public class SingleElementInSortedArray {
  // fields and classes here.
  // private int count;

  public SingleElementInSortedArray() {
    // Initialization here.
    // this.count = 0;

  }

  // Binary Search
  // We can find the number by dividing and checking the length of the subarrays.
  // O(logN) time, O(1) space.
  // Author: leetcode + kei
  // Date : February 10, 2021
  public int singleNonDuplicate(int[] nums) {
    int left = 0;
    int right = nums.length - 1;
    // If you use this, then if statement in the middle is needed to avoid index
    // out of bound.
    // while (left <= right) {
    // We keep the subarray length odd and think about the subarray length 3 and 1.
    // ex: [1, 1, 2] or [2, 3, 3]
    // we don't need the iteration when the length is 1.
    while (left < right) {
      int mid = left + (right - left) / 2;
      // Get the length of right side to figure out which side the number is in.
      boolean halvesAreEven = (right - mid) % 2 == 0;
      // Now if you use nums[M + 1] or nums[M - 1], check the last moment.
      // if you use while (left < right), then this if statement is not necessary.
      // if (left == right) {
      // return nums[left];
      // }
      if (nums[mid + 1] == nums[mid]) {
        if (halvesAreEven) {
          // Search right. We need to keep the subarray length odd.
          left = mid + 2;
        } else {
          // Search left.
          right = mid - 1;
        }
      } else if (nums[mid - 1] == nums[mid]) {
        if (halvesAreEven) {
          // Search left.
          right = mid - 2;
        } else {
          // Search right.
          left = mid + 1;
        }
      } else {
        return nums[mid];
      }
    }

    return nums[left];
  }

  // For testing.
  @SuppressWarnings("unused")
  public static void main(String[] args) {
    SingleElementInSortedArray solution = new SingleElementInSortedArray();

    // Test arguments.
    // int num = 24;
    // int target = 2;
    // System.out.println(solution.getInt(num, target));

    System.out.println("\ndone.");
  }

}
