package leetcode;

import java.util.Arrays;
import java.util.PriorityQueue;

public class SingleThreadedCPU {
  // fields and classes here.
  // private int count;

  public SingleThreadedCPU() {
    // Initialization here.
    // this.count = 0;

  }

  // Author: korney_a + kei
  // Date : December 1, 2021
  public int[] getOrder(int[][] tasks) {
    int n = tasks.length;
    // [index, enqueueTime, processingTime]
    int[][] extTasks = new int[n][3];
    for (int i = 0; i < n; i++) {
      extTasks[i][0] = i;
      extTasks[i][1] = tasks[i][0];
      extTasks[i][2] = tasks[i][1];
    }
    // Sort by enqueueTime.
    Arrays.sort(extTasks, (a, b) -> a[1] - b[1]);
    PriorityQueue<int[]> minHeap = new PriorityQueue<>(
        (a, b) -> a[2] == b[2] ? a[0] - b[0] : a[2] - b[2]);
    int time = 0;
    int[] ans = new int[n];
    int timeIdx = 0;
    int ansIdx = 0;
    // Note that for loop does not work because incrementing ansIdx when
    // minHeap is empty fails.
    while (ansIdx < n) {
      while (timeIdx < n && extTasks[timeIdx][1] <= time) {
        minHeap.offer(extTasks[timeIdx]);
        timeIdx++;
      }
      if (minHeap.isEmpty()) {
        // no available tasks
        // Set time to the next enqueueTime.
        time = extTasks[timeIdx][1];
        continue;
      }
      int[] task = minHeap.poll();
      // Add task index to answer list.
      ans[ansIdx] = task[0];
      ansIdx++;
      time += task[2];
    }

    return ans;
  }

  // For testing.
  @SuppressWarnings("unused")
  public static void main(String[] args) {
    SingleThreadedCPU solution = new SingleThreadedCPU();

    // Test arguments.
    // int num = 24;
    // int target = 2;
    // System.out.println(solution.getInt(num, target));
    int[][] tasks = { { 1, 2 }, { 2, 4 }, { 3, 2 }, { 4, 1 } };
    System.out.println(solution.getOrder(tasks));

    System.out.println("\ndone.");
  }

}
