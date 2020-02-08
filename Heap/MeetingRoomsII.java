//
// Author:
// Date : July 15, 2019

package leetcode;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

public class MeetingRoomsII {
  // fields and classes here.
  // private int count;

  public MeetingRoomsII() {
    // Initialization here.
    // this.count = 0;
  }


  // 1. Using Min-Heap.
  // First, sort by the start time, and then
  // Put it in the Min-Heap to know ongoing minimum of end time.
  // O(NlogN) time, since the for-loop iterates N times, and for each iteration,
  // peek() takes O(1) time, poll() takes O(logN) time, and offer() takes O(logN) time.
  // O(N) space, in the worst case, all the N meetings are in the heap.
  public int minMeetingRooms(int[][] intervals) {
    // corner: len 0 => ok.
    if (intervals.length == 0) {
      return 0;
    }

    // O(NlogN) time.
    Arrays.sort(intervals, new Comparator<int[]>() {
      @Override
      public int compare(int[] o1, int[] o2) {
        // Sort by start time in an ascending order.
        return o1[0] - o2[0];
      }
    });

    // Use min heap to check the earliest end time of any meetings ongoing so that
    // we don't have to use another room. In other words, If the current meeting
    // start time is larger than the earliest end time, we can use that room.
    PriorityQueue<Integer> minHeap = new PriorityQueue<>(new Comparator<Integer>() {
      @Override
      public int compare(Integer o1, Integer o2) {
        // Min-Heap to sort by end time.
        return o1 - o2;
      }
    });

    minHeap.offer(intervals[0][1]);
    for (int i = 1; i < intervals.length; i++) {
      if (intervals[i][0] >= minHeap.peek()) {
        // The start time is bigger than or equal to the earliest end time.
        // No overlapped. Let's use that room.
        // Delete the old one (the meeting has finished), and
        // update the earliest end time in the heap.
        // The heap size does not change.
        minHeap.poll();
      }
      // Add the end time of the current meeting to the heap.
      minHeap.offer(intervals[i][1]);
    }

    // The heap size is the number of the meeting rooms needed.
    return minHeap.size();
  }


  // 2. Chronological Ordering.
  // low?



  // For testing.
  public static void main(String[] args) {
    MeetingRoomsII solution = new MeetingRoomsII();

    // Test arguments.
    // int num = 24;
    // int target = 2;
    // solution.getInt(num, target);



  }

}


