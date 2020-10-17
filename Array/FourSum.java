//
// Author:
// Date  : October 16, 2020

package leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FourSum {
  // fields and classes here.
  // private int count;

  public FourSum() {
    // Initialization here.
    // this.count = 0;

  }

  // Author: leetcode + kei
  // Date : October 16, 2020
  public List<List<Integer>> fourSum(int[] nums, int target) {
    Arrays.sort(nums);
    // We can implement k - 2 loops using a recursion.
    return kSum(nums, target, 0, 4);
  }

  public List<List<Integer>> kSum(int[] nums, int target, int start, int k) {
    List<List<Integer>> res = new ArrayList<>();
    if (start == nums.length || nums[start] * k > target || target > nums[nums.length - 1] * k) {
      return res;
    }
    if (k == 2) {
      return twoSum(nums, target, start);
    }
    for (int i = start; i < nums.length; i++) {
      if (i == start || nums[i - 1] != nums[i]) {
        for (List<Integer> set : kSum(nums, target - nums[i], i + 1, k - 1)) {
          res.add(new ArrayList<>(Arrays.asList(nums[i])));
          res.get(res.size() - 1).addAll(set);
        }
      }
    }

    return res;
  }

  public List<List<Integer>> twoSum(int[] nums, int target, int start) {
    List<List<Integer>> res = new ArrayList<>();
    int lo = start, hi = nums.length - 1;
    while (lo < hi) {
      int sum = nums[lo] + nums[hi];
      if (sum < target || (lo > start && nums[lo] == nums[lo - 1]))
        ++lo;
      else if (sum > target || (hi < nums.length - 1 && nums[hi] == nums[hi + 1]))
        --hi;
      else
        res.add(Arrays.asList(nums[lo++], nums[hi--]));
    }
    return res;
  }

  // For testing.
  @SuppressWarnings("unused")
  public static void main(String[] args) {
    FourSum solution = new FourSum();

    // Test arguments.
    // int num = 24;
    // int target = 2;
    // solution.getInt(num, target);

  }

}
