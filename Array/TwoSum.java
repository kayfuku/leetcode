//
// Author:
// Date : May 19, 2019

package leetcode;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class TwoSum {
  // fields here.
  // private int count;

  public TwoSum() {
    // Initialization here.
    // this.count = 0;
  }


  // O(N) time, O(N) space.
  // Author: LeetCode + kei
  // Date : June 4, 2020
  public int[] twoSum(int[] nums, int target) {
    Map<Integer, Integer> map = new HashMap<>();
    for (int i = 0; i < nums.length; i++) {
      if (map.containsKey(nums[i])) {
        return new int[] {map.get(nums[i]), i};
      }
      map.put(target - nums[i], i);
    }
    throw new IllegalArgumentException("not found.");
  }


  // Review. Accepted.
  public int[] twoSumReview(int[] nums, int target) {
    int[] ans = new int[2];
    Map<Integer, Integer> map = new HashMap<>();
    for (int i = 0; i < nums.length; i++) {
      if (map.containsKey(nums[i])) {
        ans[0] = map.get(nums[i]);
        ans[1] = i;
        return ans;
      }
      map.put(target - nums[i], i);
    }

    return null;
  }



  // For testing.
  public static void main(String[] args) {
    TwoSum solution = new TwoSum();

    // Test arguments.
    // int num = 24;
    // int target = 2;
    // solution.getInt(num, target);

    int[] nums = new int[] {2, 7, 11, 15};
    int target = 9;
    int[] ans = solution.twoSum(nums, target);
    System.out.println(Arrays.toString(ans)); // [0, 1]

    target = 22;
    ans = solution.twoSum(nums, target);
    System.out.println(Arrays.toString(ans)); // [1, 3]


  }

}


