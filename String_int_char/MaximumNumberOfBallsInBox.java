package leetcode;

import java.util.HashMap;
import java.util.Map;

public class MaximumNumberOfBallsInBox {
  // fields and classes here.
  // private int count;

  public MaximumNumberOfBallsInBox() {
    // Initialization here.
    // this.count = 0;

  }

  // 1.
  // Author: int65536 + cszclin + kei
  // Date : February 1, 2021
  public int countBalls(int lowLimit, int highLimit) {
    // Counting with array is faster than with map.
    int[] f = new int[9 * 5 + 1]; // 99999
    // Map<Integer, Integer> counts = new HashMap<>();

    int max = 0;
    for (int i = lowLimit; i <= highLimit; i++) {
      int sum = 0;
      for (int n = i; n > 0; n /= 10) {
        sum += n % 10;
      }
      f[sum]++;
      max = Math.max(max, f[sum]);

      // counts.put(sum, counts.getOrDefault(sum, 0) + 1);
      // max = Math.max(max, counts.get(sum));
    }

    return max;
  }

  // For testing.
  @SuppressWarnings("unused")
  public static void main(String[] args) {
    MaximumNumberOfBallsInBox solution = new MaximumNumberOfBallsInBox();

    // Test arguments.
    // int num = 24;
    // int target = 2;
    // System.out.println(solution.getInt(num, target));

    System.out.println("\ndone.");
  }

}
