//
// Author:
// Date : January 3, 2020

package leetcode;

import java.util.HashSet;
import java.util.Set;

public class ContainsDuplicateII {
  // fields and classes here.
  // private int count;

  public ContainsDuplicateII() {
    // Initialization here.
    // this.count = 0;

  }

  // "absolute difference between i and j must be at least k"
  // => Just iterate through the array and use sliding window of size k.
  // O(N) time, O(min(n, k))
  // Author: LeetCode + kei
  // Date : January 3, 2020
  public boolean containsNearbyDuplicate(int[] nums, int k) {
    Set<Integer> set = new HashSet<>();
    for (int i = 0; i < nums.length; i++) {
      if (set.contains(nums[i])) {
        return true;
      }

      set.add(nums[i]);
      // Keep the size of the set to be k.
      // We need the set of size k at the time when we check the membership above.
      if (set.size() > k) {
        // Remove the oldest one.
        set.remove(nums[i - k]);
      }
    }

    return false;
  }

  // For testing.
  @SuppressWarnings("unused")
  public static void main(String[] args) {
    ContainsDuplicateII solution = new ContainsDuplicateII();

    // Test arguments.
    // int num = 24;
    // int target = 2;
    // solution.getInt(num, target);

  }

}
