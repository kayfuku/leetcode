//
// Author:
// Date : June 22, 2020

package leetcode;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeSet;

public class AvoidFloodInTheCity {
  // fields and classes here.
  // private int count;

  public AvoidFloodInTheCity() {
    // Initialization here.
    // this.count = 0;

  }


  // Author: uwi + kei
  // Date : June 22, 2020
  public int[] avoidFlood(int[] rains) {
    // K: lake, V: last index of the lake
    Map<Integer, Integer> lakeToIdx = new HashMap<>();
    int n = rains.length;
    int[] ret = new int[n];
    TreeSet<Integer> dries = new TreeSet<>();

    for (int i = 0; i < n; i++) {
      if (rains[i] > 0) {
        int lake = rains[i];
        ret[i] = -1;
        if (lakeToIdx.containsKey(lake)) {
          // Flood warning! Check the last index.
          int last = lakeToIdx.get(lake);
          // TreeSet.higher(x): Return minimum greater than x from the set.
          // Return null if it does not exist. O(logN) time
          Integer idxDry = dries.higher(last);
          if (idxDry == null) {
            // Return empty array.
            return new int[0];
          }
          // Replace the 1 that was assigned before with the lake.
          ret[idxDry] = lake;
          dries.remove(idxDry);
        }
        lakeToIdx.put(rains[i], i);

      } else {
        // rains[i] == 0
        // Stock up the index of zeros in the Binary Search Tree.
        dries.add(i);
        // ret[i] might be overwritten later.
        ret[i] = 1;
      }
    }

    return ret;
  }



  // For testing.
  @SuppressWarnings("unused")
  public static void main(String[] args) {
    AvoidFloodInTheCity solution = new AvoidFloodInTheCity();

    // Test arguments.
    // int num = 24;
    // int target = 2;
    // solution.getInt(num, target);



  }



  public void dummyMethod() {



  }



  public void dummyMethod2() {



  }



}


