//
// Author:
// Date : February 2, 2020

package leetcode;

import java.util.ArrayList;
import java.util.Collections;

public class DiagonalTraverse {
  // fields and classes here.
  // private int count;

  public DiagonalTraverse() {
    // Initialization here.
    // this.count = 0;

  }


  // There is another solution.
  // Author: @sachinmalhotra1993 + kei
  // Date : February 2, 2020
  public int[] findDiagonalOrder(int[][] matrix) {
    // Check for empty matrices
    if (matrix == null || matrix.length == 0) {
      return new int[0];
    }

    // Variables to track the size of the matrix
    int N = matrix.length;
    int M = matrix[0].length;

    // N * M is the number of the given 2d array.
    int[] result = new int[N * M];
    int k = 0;
    ArrayList<Integer> intermediate = new ArrayList<Integer>();

    // We have to go over all the elements in the first
    // row and the last column to cover all possible diagonals
    // å å å
    // a a å
    // a a å
    // num å: N + M -1
    for (int d = 0; d < N + M - 1; d++) {

      // Clear the intermediate array every time we start
      // to process another diagonal
      intermediate.clear();

      // We need to figure out the "head" of this diagonal
      // The elements in the first row and the last column
      // are the respective heads.
      int r = d < M ? 0 : d - M + 1;
      int c = d < M ? d : M - 1; // M - 1: the last column

      // Iterate until one of the indices goes out of scope
      // Take note of the index math to go down the diagonal
      while (r < N && c > -1) {

        intermediate.add(matrix[r][c]);
        ++r;
        --c;
      }

      // Reverse even numbered diagonals. The
      // article says we have to reverse odd
      // numbered articles but here, the numbering
      // is starting from 0. :P
      if (d % 2 == 0) {
        Collections.reverse(intermediate);
      }

      // Put the elements in intermediate array in to the result.
      for (int i = 0; i < intermediate.size(); i++) {
        result[k] = intermediate.get(i);
        k++;
      }
    }

    return result;
  }



  // For testing.
  public static void main(String[] args) {
    DiagonalTraverse solution = new DiagonalTraverse();

    // Test arguments.
    // int num = 24;
    // int target = 2;
    // solution.getInt(num, target);



  }

}


