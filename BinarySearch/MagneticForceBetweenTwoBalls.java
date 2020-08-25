//
// Author:
// Date : August ?, 2020

package leetcode;

import java.util.Arrays;

public class MagneticForceBetweenTwoBalls {
  // fields and classes here.
  // private int count;

  public MagneticForceBetweenTwoBalls() {
    // Initialization here.
    // this.count = 0;

  }


  // Author: AH Tech + luo122174088 + kei
  // Date : August 24, 2020
  public int maxDistance(int[] position, int m) {
    Arrays.sort(position);
    int left = 1;
    int right = position[position.length - 1];

    while (left <= right) {
      int mid = (left + right) / 2;

      if (countBalls(mid, position, m) >= m) {
        left = mid + 1;
      } else {
        right = mid - 1;
      }
    }

    return left - 1;
  }

  private int countBalls(int mid, int[] position, int m) {
    int count = 1;
    int prev = position[0];
    for (int i = 1; i < position.length && count <= m; i++) {
      if (position[i] - prev >= mid) {
        count++;
        prev = position[i];
      }
    }

    return count;
  }


  // For testing.
  @SuppressWarnings("unused")
  public static void main(String[] args) {
    MagneticForceBetweenTwoBalls solution = new MagneticForceBetweenTwoBalls();

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


