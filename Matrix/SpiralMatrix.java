//
// Author:
// Date : May 28, 2019

package leetcode;

import java.util.ArrayList;
import java.util.List;


public class SpiralMatrix {

  // Some fields here.
  // private int count;


  public SpiralMatrix() {
    // Initialization here.
    // count = 0;
  }

  // O(N) time, O(N) space, where N is the total number of elements in the matrix.
  public List<Integer> spiralOrder(int[][] matrix) {
    List<Integer> ans = new ArrayList<>();
    // Check empty matrix.
    if (matrix == null || matrix.length == 0) {
      return ans;
    }
    int R = matrix.length, C = matrix[0].length;

    // These dr and dc defines the distance and direction of advancing cursor.
    // Right, Down, Left, Up.
    int[] dr = {0, 1, 0, -1};
    int[] dc = {1, 0, -1, 0};

    int di = 0; // First, set di to 0 since the first move direction is to the right.
    int currR = 0;
    int currC = 0;
    boolean[][] visited = new boolean[R][C];
    for (int i = 0; i < R * C; i++) {
      ans.add(matrix[currR][currC]);
      visited[currR][currC] = true;

      // Get the next position and check if it is valid.
      int nextR = currR + dr[di];
      int nextC = currC + dc[di];
      if (nextR >= 0 && nextR < R && nextC >= 0 && nextC < C && !visited[nextR][nextC]) {
        // Valid.
        currR = nextR;
        currC = nextC;
      } else {
        // Invalid.
        // Change di, i.e., change the direction.
        di = (di + 1) % 4;
        currR += dr[di];
        currC += dc[di];
      }
    }

    return ans;
  }


  // 2. Layer-by-layer. No need.
  public List<Integer> spiralOrder2(int[][] matrix) {
    List<Integer> ans = new ArrayList<>();


    return ans;
  }



  // For testing.
  public static void main(String[] args) {
    SpiralMatrix solution = new SpiralMatrix();

    // Test arguments.
    // int num = 24;
    // int target = 2;
    // solution.getInt(num, target);



  }

}


