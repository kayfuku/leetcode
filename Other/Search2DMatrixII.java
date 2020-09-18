//
// Author:
// Date  : September 16, 2020

package leetcode;

public class Search2DMatrixII {
  // fields and classes here.
  // private int count;

  public Search2DMatrixII() {
    // Initialization here.
    // this.count = 0;

  }


  // Author: @emptyset + kei
  // Date  : September 16, 2020
  public boolean searchMatrix(int[][] matrix, int target) {
    // Start our "pointer" in the bottom-left. 
    int row = matrix.length - 1;
    int col = 0;

    while (row >= 0 && col < matrix[0].length) {
      if (matrix[row][col] > target) {
        row--;
      } else if (matrix[row][col] < target) {
        col++;
      } else { 
        // Found it. 
        return true;
      }
    }

    return false;
  }



  // For testing.
  @SuppressWarnings("unused")
  public static void main(String[] args) {
    Search2DMatrixII solution = new Search2DMatrixII();

    // Test arguments.
    // int num = 24;
    // int target = 2;
    // solution.getInt(num, target);



  }




}


