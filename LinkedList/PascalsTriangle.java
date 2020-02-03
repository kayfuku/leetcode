//
// Author:
// Date : June 4, 2019

package leetcode;

import java.util.ArrayList;
import java.util.List;

public class PascalsTriangle {
  // fields here.
  // private int count;

  public PascalsTriangle() {
    // Initialization here.
    // this.count = 0;
  }


  // I prefer this to the next one.
  // Author: kei

  // Output:
  // [
  // [1],
  // [1,1],
  // [1,2,1],
  // [1,3,3,1],
  // [1,4,6,4,1]
  // ]
  // O(N^2) time: The outer loop runs numRows times, but for each iteration of the outer loop,
  // the inner loop runs parent size times. Therefore, the total number of update operation is,
  // according to Gauss' formula, N * (N + 1) / 2.
  // O(N^2) space: We need the list that stores all the numbers.
  public List<List<Integer>> generateKei(int numRows) {
    List<List<Integer>> ans = new ArrayList<>();
    if (numRows == 0) {
      return ans;
    }

    List<Integer> row = new ArrayList<>();
    row.add(1);
    ans.add(row);
    for (int i = 1; i < numRows; i++) {
      List<Integer> parent = row;
      // Create next row using its parent row.
      row = new ArrayList<>();
      row.add(1);
      for (int j = 1; j < parent.size(); j++) {
        row.add(parent.get(j - 1) + parent.get(j));
      }
      row.add(1);
      ans.add(row);
    }

    return ans;
  }


  // Author: LeetCode
  public List<List<Integer>> generate(int numRows) {
    List<List<Integer>> triangle = new ArrayList<List<Integer>>();

    // First base case; if user requests zero rows, they get zero rows.
    if (numRows == 0) {
      return triangle;
    }

    // Second base case; first row is always [1].
    triangle.add(new ArrayList<>());
    triangle.get(0).add(1);

    for (int rowNum = 1; rowNum < numRows; rowNum++) {
      List<Integer> row = new ArrayList<>();
      List<Integer> prevRow = triangle.get(rowNum - 1);

      // The first row element is always 1.
      row.add(1);

      // Each triangle element (other than the first and last of each row)
      // is equal to the sum of the elements above-and-to-the-left and
      // above-and-to-the-right.
      for (int j = 1; j < rowNum; j++) {
        row.add(prevRow.get(j - 1) + prevRow.get(j));
      }

      // The last row element is always 1.
      row.add(1);

      triangle.add(row);
    }

    return triangle;
  }


  // For testing.
  public static void main(String[] args) {
    PascalsTriangle solution = new PascalsTriangle();

    // Test arguments.
    // int num = 24;
    // int target = 2;
    // solution.getInt(num, target);

    int numRows = 5;
    List<List<Integer>> ret = solution.generate(numRows);
    System.out.println(ret.toString());


  }

}


