//
// Author:
// Date : March 11, 2020

package leetcode;

import java.util.HashSet;
import java.util.Set;

public class KDiffPairsInAnArray {
  // fields and classes here.
  // private int count;

  public KDiffPairsInAnArray() {
    // Initialization here.
    // this.count = 0;

  }


  // Author: alexander + NoAnyLove + kei
  // Date : March 12, 2020
  public int findPairs(int[] nums, int k) {
    if (nums == null || nums.length == 0 || k < 0) {
      return 0;
    }

    // In this way, you can handle duplicate pairs and
    // k = 0 case.
    // 'seen' acts as the hash table in the Two Sum problem
    // in order to find the other half.
    Set<Integer> seen = new HashSet<Integer>();
    // Save the pairs as unique pairs by putting in the smaller one.
    Set<Integer> smallerSet = new HashSet<Integer>();
    for (int i = 0; i < nums.length; i++) {
      // Check if there is the other half below and above.
      if (seen.contains(nums[i] - k)) {
        // Found the counterpart. 
        // The other half of the pair already exists.
        // Save the smaller one.
        smallerSet.add(nums[i] - k);
      }
      if (seen.contains(nums[i] + k)) {
        // Another half of the pair already exists.
        // Save the smaller one because if you save the larger one, then
        // there is no way to distinguish two distinct pairs just by
        // looking at the element in the set. For example,
        // 3 in the set could mean (1, 3) or (3, 5).
        smallerSet.add(nums[i]);
      }

      // The candidates that can form pairs.
      seen.add(nums[i]);
    }

    // Count the number of unique pairs.
    return smallerSet.size();
  }


  // For testing.
  @SuppressWarnings("unused")
  public static void main(String[] args) {
    KDiffPairsInAnArray solution = new KDiffPairsInAnArray();

    // Test arguments.
    // int num = 24;
    // int target = 2;
    // solution.getInt(num, target);

    int[] nums = {1, 3, 1, 5, 4};
    int res = solution.findPairs(nums, 0);
    System.out.println(res);


  }

}


