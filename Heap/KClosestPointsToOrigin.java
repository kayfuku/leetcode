package leetcode;

import java.util.PriorityQueue;

public class KClosestPointsToOrigin {
  // fields and classes here.
  // private int count;

  public KClosestPointsToOrigin() {
    // Initialization here.
    // this.count = 0;

  }

  // O(NlogK) time, O(K) space.
  // I know there is another solution that only takes O(N) time using quick-sort
  // like algorithm.
  // Author: + kei
  // Date : February 3, 2021
  public int[][] kClosest(int[][] points, int K) {
    // We need K smallest.
    // That's why we use maxHeap to eliminate the larger ones.
    PriorityQueue<int[]> maxHeap = new PriorityQueue<>(
        (a, b) -> (b[0] * b[0] + b[1] * b[1]) - (a[0] * a[0] + a[1] * a[1]));

    for (int[] p : points) {
      maxHeap.offer(p);
      if (maxHeap.size() > K) {
        maxHeap.poll();
      }
    }

    int[][] ans = new int[K][2];
    // Note that i < maxHeap.size() is not acceptable on the condition clause
    // because the size changes while iterating.
    for (int i = 0; i < ans.length; i++) {
      ans[i] = maxHeap.poll();
    }

    return ans;
  }

  // For testing.
  @SuppressWarnings("unused")
  public static void main(String[] args) {
    KClosestPointsToOrigin solution = new KClosestPointsToOrigin();

    // Test arguments.
    // int num = 24;
    // int target = 2;
    // System.out.println(solution.getInt(num, target));

    System.out.println("\ndone.");
  }

}
