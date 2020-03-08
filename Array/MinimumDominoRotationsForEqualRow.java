//
// Author:
// Date : December 15, 2019

package leetcode;

public class MinimumDominoRotationsForEqualRow {
  // fields and classes here.
  // private int count;

  public MinimumDominoRotationsForEqualRow() {
    // Initialization here.
    // this.count = 0;

  }


  // O(N) time, O(1) space.
  // Author: @liaison and @andvary + kei
  // Date : December 15, 2019
  public int minDominoRotations(int[] A, int[] B) {
    int n = A.length;
    // Try to check the first value in A, then try the first value in B if needed.
    int rotations = getNumRotations(A[0], A, B, n);
    // If one could make all elements in A or B equal to A[0]
    if (rotations != -1 || A[0] == B[0]) {
      return rotations;
    }
    // If one could make all elements in A or B equal to B[0]
    return getNumRotations(B[0], A, B, n);
  }

  // How many rotations should be done to have all elements in A equal to x
  // and to have all elements in B equal to x. Then, take the minimum.
  public int getNumRotations(int x, int[] A, int[] B, int n) {
    int rotationsA = 0, rotationsB = 0;
    for (int i = 0; i < n; i++) {
      if (A[i] != x && B[i] != x) {
        // Rotations coudn't be done.
        return -1;
      }
      if (A[i] != x) {
        // A[i] != x and B[i] == x
        rotationsA++;
      } else if (B[i] != x) {
        // A[i] == x and B[i] != x
        rotationsB++;
      }
    }
    // Min number of rotations to have all
    // elements equal to x in A or B
    return Math.min(rotationsA, rotationsB);
  }



  // For testing.
  public static void main(String[] args) {
    MinimumDominoRotationsForEqualRow solution = new MinimumDominoRotationsForEqualRow();

    // Test arguments.
    // int num = 24;
    // int target = 2;
    // solution.getInt(num, target);



  }

}


