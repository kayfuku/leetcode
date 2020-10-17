//
// Author:
// Date : October 16, 2020

package leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ThreeSum {
  // fields and classes here.
  // private int count;

  public ThreeSum() {
    // Initialization here.
    // this.count = 0;

  }

  // Author: leetcode + kei
  // Date : October 16, 2020
  public List<List<Integer>> threeSum(int[] nums) {
    Arrays.sort(nums);
    List<List<Integer>> ret = new ArrayList<>();
    // If i > 0, then the other two have to be negative, but
    // it does not happen because it's sorted.
    for (int i = 0; i < nums.length && nums[i] <= 0; i++) {
      if (i == 0 || nums[i - 1] != nums[i]) {
        twoSum(nums, i, ret);
      }
    }

    return ret;
  }

  private void twoSum(int[] nums, int i, List<List<Integer>> ret) {
    int lo = i + 1;
    int hi = nums.length - 1;
    while (lo < hi) {
      int sum = nums[i] + nums[lo] + nums[hi];
      if (sum == 0) {
        ret.add(Arrays.asList(nums[i], nums[lo], nums[hi]));
        lo++;
        hi--;
        while (lo < hi && nums[lo - 1] == nums[lo]) {
          lo++;
        }
      } else if (sum > 0) {
        hi--;
      } else {
        lo++;
      }
    }
  }

  // For testing.
  @SuppressWarnings("unused")
  public static void main(String[] args) {
    ThreeSum solution = new ThreeSum();

    // Test arguments.
    // int num = 24;
    // int target = 2;
    // solution.getInt(num, target);

    int[] nums = { -1, 0, 1, 2, -1, -4 };
    System.out.println(solution.threeSum(nums));

  }

  public void dummyMethod() {

  }

  public void dummyMethod2() {

  }

}
