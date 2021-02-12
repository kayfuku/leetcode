//
// Author:
// Date : July 15, 2019

package leetcode;

import java.util.Arrays;
import java.util.PriorityQueue;

public class MeetingRoomsII {
  // fields and classes here.
  // private int count;

  public MeetingRoomsII() {
    // Initialization here.
    // this.count = 0;
  }

  // 1. Using Min-Heap. (This is good enough)
  // First, sort by the start time, and then
  // Put it in the Min-Heap to know the earliest end time of the ongoing meetings.
  // O(NlogN) time, since the for-loop iterates N times, and for each iteration,
  // peek() takes O(1) time, poll() takes O(logN) time, and offer() takes O(logN)
  // time.
  // O(N) space, in the worst case, all the N meetings are in the heap.
  // Author: leetcode + kei
  // Date : July 15, 2019, January 2, 2021
  public int minMeetingRooms(int[][] intervals) {
    // corner: len 0 => ok.
    if (intervals == null || intervals.length == 0) {
      return 0;
    }

    // O(NlogN) time.
    // Arrays.sort(intervals, new Comparator<int[]>() {
    // @Override
    // public int compare(int[] o1, int[] o2) {
    // // Sort by start time in an ascending order.
    // return o1[0] - o2[0];
    // }
    // });
    Arrays.sort(intervals, (a1, a2) -> a1[0] - a2[0]);

    // Use min heap to check the earliest end time of any meetings ongoing so that
    // we don't have to use another room. In other words, If the current meeting
    // start time is later than the earliest end time, we can use that room.
    // PriorityQueue<Integer> minHeap = new PriorityQueue<>(new
    // Comparator<Integer>() {
    // @Override
    // public int compare(Integer o1, Integer o2) {
    // // Min-Heap to sort by end time.
    // return o1 - o2;
    // }
    // });
    PriorityQueue<Integer> minHeap = new PriorityQueue<>((n1, n2) -> n1 - n2);

    // Put the end time of the first meeting in the heap.
    minHeap.offer(intervals[0][1]);
    for (int i = 1; i < intervals.length; i++) {
      if (intervals[i][0] >= minHeap.peek()) {
        // The start time is later than or equal to the earliest end time.
        // No overlapped. Let's use that room.
        // Delete the old one (the meeting has finished), and
        // update the earliest end time in the heap.
        // The heap size (the number of rooms needed) does not change for this
        // iteration.
        minHeap.poll();
      }
      // Add the end time of the current meeting to the heap.
      minHeap.offer(intervals[i][1]);
    }

    // The heap size is the number of the meeting rooms needed.
    // Can you imagine why the heap size is the maximum number of overlapped
    // meetings?
    // This is the most important point.
    // Even if a meeting finishes, the meeting will not be deleted unless we add a
    // new meeting in substitution for the meeting. Thus, the maximum number of
    // overlapped meetings is persistent.
    return minHeap.size();
  }

  // 2. Chronological Ordering.
  // low?

  @SuppressWarnings("unused")
  // For testing.
  public static void main(String[] args) {
    MeetingRoomsII solution = new MeetingRoomsII();

    // Test arguments.
    // int num = 24;
    // int target = 2;
    // solution.getInt(num, target);

  }

}
