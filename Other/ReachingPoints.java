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


  // 3. Work Backwards
  // Author: @awice + kei (TLE)
  // Date : March 21, 2020
  public boolean reachingPoints2(int sx, int sy, int tx, int ty) {
    while (tx >= sx && ty >= sy) {
      if (sx == tx && sy == ty) {
        return true;
      }
      if (tx > ty) {
        tx -= ty;
      } else {
        ty -= tx;
      }
    }

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


