//
// Author:
// Date : June ?, 2020

package leetcode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LeastNumberOfUniqueIntegersAfterKRemovals {
  // fields and classes here.
  // private int count;

  public LeastNumberOfUniqueIntegersAfterKRemovals() {
    // Initialization here.
    // this.count = 0;

  }


  // Author: awice + kei
  // Date : June 19, 2020
  public int findLeastNumOfUniqueInts(int[] arr, int k) {
    Map<Integer, Integer> count = new HashMap<Integer, Integer>();
    for (int n : arr) {
      count.put(n, count.getOrDefault(n, 0) + 1);
    }
    List<Integer> list = new ArrayList<Integer>(count.values());
    Collections.sort(list);
    int ans = list.size();
    for (int x : list) {
      if (x <= k) {
        k -= x;
        ans -= 1;
      }
    }

    return ans;
  }



  // For testing.
  @SuppressWarnings("unused")
  public static void main(String[] args) {
    LeastNumberOfUniqueIntegersAfterKRemovals solution =
        new LeastNumberOfUniqueIntegersAfterKRemovals();

    // Test arguments.
    // int num = 24;
    // int target = 2;
    // solution.getInt(num, target);

    int[] arr = {5, 5, 4};
    int k = 1;
    System.out.println(solution.findLeastNumOfUniqueInts(arr, k));



  }



  public void dummyMethod() {



  }



  public void dummyMethod2() {



  }



}


