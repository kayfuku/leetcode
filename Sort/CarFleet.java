package leetcode;

import java.util.Arrays;
import java.util.Collections;
import java.util.Map;
import java.util.TreeMap;

public class CarFleet {
  // fields and classes here.
  // private int count;

  public CarFleet() {
    // Initialization here.
    // this.count = 0;

  }

  // 1. Array
  // Author: lee215 + kei
  // Date : November 5, 2021
  public int carFleet(int target, int[] pos, int[] speed) {
    int N = pos.length;
    // [position, time to target]
    double[][] cars = new double[N][2];
    for (int i = 0; i < N; i++) {
      cars[i] = new double[] { pos[i], (double) (target - pos[i]) / speed[i] };
    }

    // Sort by start positions in ascending order.
    Arrays.sort(cars, (a, b) -> Double.compare(a[0], b[0]));

    int res = 0;
    double cur = 0;
    // Check their speed from the car that is closest to the target.
    // If another car needs more time, then it will be the new slowest
    // car, and becomes the new lead of a car fleet.
    // Check the number of max updates.
    for (int i = N - 1; i >= 0; i--) {
      if (cars[i][1] > cur) {
        cur = cars[i][1];
        res++;
      }
    }

    return res;
  }

  // 2. TreeMap
  // Author: lee215 + kei
  // Date : November 5, 2021
  public int carFleet2(int target, int[] pos, int[] speed) {
    // K: position, V: time
    // reverseOrder() allows us to get a larger key first. left > mid > right
    Map<Integer, Double> m = new TreeMap<>(Collections.reverseOrder());
    for (int i = 0; i < pos.length; i++) {
      m.put(pos[i], (double) (target - pos[i]) / speed[i]);
    }
    int res = 0;
    double cur = 0;
    for (double time : m.values()) {
      if (time > cur) {
        cur = time;
        res++;
      }
    }
    return res;
  }

  // For testing.
  @SuppressWarnings("unused")
  public static void main(String[] args) {
    CarFleet solution = new CarFleet();

    // Test arguments.
    // int num = 24;
    // int target = 2;
    // System.out.println(solution.getInt(num, target));
    int target = 10;
    int[] pos = { 9, 5, 2 };
    int[] speed = { 5, 3, 1 };
    System.out.println(solution.carFleet(target, pos, speed));

    Map<Integer, Double> m = new TreeMap<>(Collections.reverseOrder());
    for (int i = 0; i < speed.length; i++) {
      m.put(pos[i], (double) (target - pos[i]) / speed[i]);
    }
    System.out.println(m.values());

    System.out.println("\ndone.");
  }

}
