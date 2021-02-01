package leetcode;

import java.util.Arrays;

public class SquaresOfSortedArray {
  // fields and classes here.
  // private int count;

  public SquaresOfSortedArray() {
    // Initialization here.
    // this.count = 0;

  }

  // 1. Straightforward
  // O(NlogN) time.
  // Python: list.sort() is implemented with the Timsort algorithm. O(N) space.
  // Java: Arrays.sort() is implemented as a variant of quicksort algorithm.
  // O(logN) space.
  // Author: leetcode + kei
  // Date : December 26, 2020
  public int[] sortedSquares2(int[] A) {
    int N = A.length;
    int[] ans = new int[N];
    for (int i = 0; i < N; i++) {
      ans[i] = A[i] * A[i];
    }
    Arrays.sort(ans);

    return ans;
  }

  // 2. Two pointers
  // O(N) time, O(N) space
  // Author: leetcode + kei
  // Date : January 30, 2020
  public int[] sortedSquares(int[] nums) {
    int n = nums.length;
    int[] result = new int[n];
    int left = 0;
    int right = n - 1;

    for (int i = n - 1; i >= 0; i--) {
      int square;
      if (Math.abs(nums[left]) < Math.abs(nums[right])) {
        square = nums[right] * nums[right];
        right--;
      } else {
        square = nums[left] * nums[left];
        left++;
      }
      result[i] = square;
    }
    return result;
  }

  // // Author: kei (MLE)
  // // Date : December 26, 2020
  // public int[] sortedSquaresMLE(int[] nums) {
  // int[] counts = new int[100000001];
  // for (int n : nums) {
  // counts[n * n]++;
  // }
  // int[] ans = new int[nums.length];
  // int p = 0;
  // for (int i = 0; i < counts.length; i++) {
  // while (counts[i] > 0) {
  // ans[p] = i;
  // p++;
  // counts[i]--;
  // }
  // }

  // return ans;
  // }

  // For testing.
  @SuppressWarnings("unused")
  public static void main(String[] args) {
    SquaresOfSortedArray solution = new SquaresOfSortedArray();

    // Test arguments.
    // int num = 24;
    // int target = 2;
    // solution.getInt(num, target);

  }

}
