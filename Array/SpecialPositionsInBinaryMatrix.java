//
// Author:
// Date  : September ?, 2020

package leetcode;

import java.util.HashSet;
import java.util.Set;

public class SpecialPositionsInBinaryMatrix {
  // fields and classes here.
  // private int count;

  public SpecialPositionsInBinaryMatrix() {
    // Initialization here.
    // this.count = 0;

  }


  // Author: uwi + kei
  // Date  : September 14, 2020
  public int numSpecial(int[][] mat) {
    int n = mat.length, m = mat[0].length;
    int ret = 0;
    for (int i = 0; i < n; i++) {
      for (int j = 0; j < m; j++) {
        if (mat[i][j] == 1) {
          int s = 0;
          for (int k = 0; k < n; k++) {
            s += mat[k][j];
          }
          for (int k = 0; k < m; k++) {
            s += mat[i][k];
          }
          if (s == 2) {
            ret++;
          }
        }
      }
    }
    
    return ret;
  }


  // Author: kei (AC)
  // Date  : September 12, 2020
  public int numSpecial2(int[][] mat) {
    Set<Integer> cSet = new HashSet<>();
    int R = mat.length;
    int C = mat[0].length;
    for (int i = 0; i < R; i++) {
      int sum = 0;
      int c = -1;
      for (int j = 0; j < C; j++) {
        if (mat[i][j] == 1) {
          c = j;
        }
        sum += mat[i][j];
      }
      if (sum == 1) {
        cSet.add(c);
      }
    }
    int count = 0;
    for (int c : cSet) {
      int sum = 0;
      for (int i = 0; i < R; i++) {
        sum += mat[i][c];
      }
      if (sum == 1) {
        count++;
      }
    }

    return count;
  }




  // For testing.
  @SuppressWarnings("unused")
  public static void main(String[] args) {
    CountUnhappyFriends solution = new CountUnhappyFriends();

    // Test arguments.
    // int num = 24;
    // int target = 2;
    // solution.getInt(num, target);



  }




}


