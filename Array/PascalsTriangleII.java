//
// Author:
// Date : February 1, 2020

package leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PascalsTriangleII {
  // fields and classes here.
  // private int count;

  public PascalsTriangleII() {
    // Initialization here.
    // this.count = 0;

  }

  // Hard! No need to review, right now.

  // [
  // [1],
  // [1,1],
  // [1,2,1],
  // [1,3,3,1],
  // [1,4,6,4,1]
  // ]
  // Author: (AC)
  // Date : February 1, 2020, November 5, 2020
  public List<Integer> getRow2(int rowIndex) {
    List<Integer> list = new ArrayList<>(rowIndex);
    if (rowIndex < 0) {
      return list;
    }

    for (int i = 0; i < rowIndex + 1; i++) {
      list.add(0, 1);
      for (int j = 1; j < list.size() - 1; j++) {
        list.set(j, list.get(j) + list.get(j + 1));
      }
    }
    return list;
  }

  // Author: (AC)
  // Date : February 1, 2020
  public List<Integer> getRow(int k) {
    // Note that this is Integer[], not int[] because it will be converted to a list
    // later.
    Integer[] arr = new Integer[k + 1];
    Arrays.fill(arr, 0);
    arr[0] = 1;

    for (int i = 1; i <= k; i++) {
      for (int j = i; j > 0; j--) {
        // You just have to add the upper left value to the current value.
        arr[j] = arr[j] + arr[j - 1];
      }
    }

    return Arrays.asList(arr);
  }

  // For testing.
  public static void main(String[] args) {
    PascalsTriangleII solution = new PascalsTriangleII();

    // Test arguments.
    // int num = 24;
    // int target = 2;
    // solution.getInt(num, target);

    int k = 2;
    System.out.println(solution.getRow(k));

  }

}
