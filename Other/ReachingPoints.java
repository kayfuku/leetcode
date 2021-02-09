//
// Author:
// Date : March 21, 2020

package leetcode;

public class ReachingPoints {
  // fields and classes here.
  // private int count;

  public ReachingPoints() {
    // Initialization here.
    // this.count = 0;

  }

  // 3. Work Backwards. Take a look at this first, then look at next solution.
  // Author: @awice + kei (TLE)
  // Date : March 21, 2020
  public boolean reachingPoints2(int sx, int sy, int tx, int ty) {
    while (tx >= sx && ty >= sy) {
      if (sx == tx && sy == ty) {
        // Just by subtracting tx or ty from tx or ty reached to sx and sy.
        return true;
      }
      if (tx > ty) {
        tx -= ty;
      } else {
        ty -= tx;
      }
    }
    // tx < sx or ty < sy means there is no way to reach from s to t.
    return false;
  }

  // 4. Work Backwards variant
  // Author: @awice + kei (AC)
  // Date : March 21, 2020
  public boolean reachingPoints(int sx, int sy, int tx, int ty) {
    while (tx >= sx && ty >= sy) {
      if (tx == ty) {
        break;
      }
      if (tx > ty) {
        if (ty > sy) {
          // Repeating subtracting ty from tx until it cannot could be done by modulo.
          tx %= ty;
        } else {
          // ty <= sy
          // Check if sx = abs(tx - n * ty)
          // In other words, check if tx can reach to sx backwards.
          return (tx - sx) % ty == 0;
        }
      } else {
        // tx < ty
        if (tx > sx) {
          // Repeating subtracting tx from ty until it cannot could be done by modulo.
          ty %= tx;
        } else {
          // tx <= sx
          // Check if sy = abs(ty - n * tx)
          return (ty - sy) % tx == 0;
        }
      }
    }

    // if ty < sy or tx < sx, then we cannot reach the target anyway because
    // sx, sy are positive number and cannot reduce themselves.
    return tx == sx && ty == sy;
  }

  // For testing.
  @SuppressWarnings("unused")
  public static void main(String[] args) {
    ReachingPoints solution = new ReachingPoints();

    // Test arguments.
    // int num = 24;
    // int target = 2;
    // solution.getInt(num, target);

  }

}
