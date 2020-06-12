//
// Author:
// Date : June 10, 2020

package leetcode;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeSet;

public class ContainsDuplicateIII {
  // fields and classes here.
  // private int count;

  public ContainsDuplicateIII() {
    // Initialization here.
    // this.count = 0;

  }


  // 2. Binary Search Tree
  // O(NlogK) time, O(K) space
  // Author: LeetCode + kei
  // Date : June 10, 2020
  public boolean containsNearbyAlmostDuplicate2(int[] nums, int k, int t) {
    TreeSet<Integer> set = new TreeSet<>();
    for (int i = 0; i < nums.length; ++i) {
      // Find the minimum greater than or equal to nums[i].
      Integer s = set.ceiling(nums[i]);
      if (s != null && (long) s - nums[i] <= t) {
        return true;
      }

      // Find the maximum lesser than or equal to nums[i].
      Integer g = set.floor(nums[i]);
      if (g != null && (long) nums[i] - g <= t) {
        return true;
      }

      set.add(nums[i]);
      if (set.size() > k) {
        // Remove the oldest element.
        set.remove(nums[i - k]);
      }
    }
    return false;
  }


  // 3. Buckets (Not review yet)
  // O() time, O() space
  // Author: LeetCode + kei
  // Date : June 10, 2020

  // Get the ID of the bucket from element value x and bucket width w
  // In Java, `-3 / 5 = 0` and but we need `-3 / 5 = -1`.
  private long getID(long x, long w) {
    return x < 0 ? (x + 1) / w - 1 : x / w;
  }

  public boolean containsNearbyAlmostDuplicate(int[] nums, int k, int t) {
    if (t < 0) {
      return false;
    }
    Map<Long, Long> d = new HashMap<>();
    long w = (long) t + 1;
    for (int i = 0; i < nums.length; ++i) {
      long m = getID(nums[i], w);
      // Check if bucket m is empty, each bucket may contain at most one element.
      if (d.containsKey(m)) {
        return true;
      }
      // Check the neighbor buckets for almost duplicate.
      if (d.containsKey(m - 1) && Math.abs(nums[i] - d.get(m - 1)) < w) {
        return true;
      }
      if (d.containsKey(m + 1) && Math.abs(nums[i] - d.get(m + 1)) < w) {
        return true;
      }
      // Now bucket m is empty and no almost duplicate in neighbor buckets.
      d.put(m, (long) nums[i]);
      if (i >= k) {
        d.remove(getID(nums[i - k], w));
      }
    }

    return false;
  }



  // For testing.
  @SuppressWarnings("unused")
  public static void main(String[] args) {
    ContainsDuplicateIII solution = new ContainsDuplicateIII();

    // Test arguments.
    // int num = 24;
    // int target = 2;
    // solution.getInt(num, target);

    int[] nums = {-1, Integer.MAX_VALUE};
    int k = 1;
    System.out.println(solution.containsNearbyAlmostDuplicate(nums, k, Integer.MAX_VALUE));



  }



  public void dummyMethod() {



  }



  public void dummyMethod2() {



  }



}


