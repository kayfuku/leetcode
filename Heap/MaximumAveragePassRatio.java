package leetcode;

import java.util.PriorityQueue;

public class MaximumAveragePassRatio {
  // fields and classes here.
  // private int count;

  public MaximumAveragePassRatio() {
    // Initialization here.
    // this.count = 0;

  }

  // Max average => Max sum
  // We don't know which term difference '(pass + x) / (total + x) - pass / total'
  // is maximum and how many x should be.
  // Let a max heap to find a term that provides a maximum difference
  // if one extra student is added to the term. Then, add one extra
  // student to the term. And then let the heap to find such a term again.
  // Repeat it until no more extra students.
  //
  // Author: uwi + kei
  // Date : March 14, 2021
  public double maxAverageRatio(int[][] classes, int extraStudents) {
    // To find which term difference is maximum when one is added to numerator and
    // denominator.
    PriorityQueue<double[]> maxHeap = new PriorityQueue<>( //
        (x, y) -> -Double.compare(x[2], y[2]));
    for (int[] c : classes) {
      maxHeap.add(new double[] { //
          c[0], c[1], (double) (c[0] + 1) / (c[1] + 1) - (double) c[0] / c[1] });
    }
    // Add each extra student one by one to the term that gives maximum difference.
    // Check the maximum difference every time.
    for (int z = 0; z < extraStudents; z++) {
      // Take out the current term that gives maximum difference.
      double[] cur = maxHeap.poll();
      cur[0]++;
      cur[1]++;
      cur[2] = (double) (cur[0] + 1) / (cur[1] + 1) - (double) cur[0] / cur[1];
      maxHeap.add(cur);
    }
    double ans = 0;
    for (double[] o : maxHeap) {
      ans += o[0] / o[1];
    }
    return ans / classes.length;
  }

  // For testing.
  @SuppressWarnings("unused")
  public static void main(String[] args) {
    MaximumAveragePassRatio solution = new MaximumAveragePassRatio();

    // Test arguments.
    // int num = 24;
    // int target = 2;
    // System.out.println(solution.getInt(num, target));

    System.out.println("\ndone.");
  }

}
