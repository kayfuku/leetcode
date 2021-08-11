package leetcode;

public class MergeSortedArray {
  // fields and classes here.
  // private int count;

  public MergeSortedArray() {
    // Initialization here.
    // this.count = 0;

  }

  // O(m + n) time, O(1) space
  // Author: leetcode + kei
  // Date : August 10, 2021
  public void merge(int[] nums1, int m, int[] nums2, int n) {
    // Set p1 and p2 to point to the end of their respective arrays.
    int p1 = m - 1;
    int p2 = n - 1;

    // And move p backwards through the array, each time writing
    // the smallest value pointed at by p1 or p2.
    for (int p = m + n - 1; p >= 0; p--) {
      if (p2 < 0) {
        // nums2 finished.
        // The remaining is already filled.
        break;
      }
      if (p1 >= 0 && nums1[p1] > nums2[p2]) {
        nums1[p] = nums1[p1--];
      } else {
        nums1[p] = nums2[p2--];
      }
    }
  }

  // For testing.
  @SuppressWarnings("unused")
  public static void main(String[] args) {
    MergeSortedArray solution = new MergeSortedArray();

    // Test arguments.
    // int num = 24;
    // int target = 2;
    // System.out.println(solution.getInt(num, target));

    System.out.println("\ndone.");
  }

}
