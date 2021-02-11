//
// Author:
// Date : March 31, 2020

package leetcode;

import java.util.LinkedList;

public class InsertInterval {
  // fields and classes here.
  // private int count;

  public InsertInterval() {
    // Initialization here.
    // this.count = 0;

  }

  // O(N) time, O(N) space.
  // Author: @liaison and @andvary + kei
  // Date : April 1, 2020
  public int[][] insert(int[][] intervals, int[] newInterval) {
    // Init data.
    int newStart = newInterval[0], newEnd = newInterval[1];
    int idx = 0, n = intervals.length;
    // LinkedList has a getLast().
    LinkedList<int[]> output = new LinkedList<int[]>();

    // By the way, we could use binary search to find overlapping area, but
    // after inserting the new interval, we have to shift the rest of arrays,
    // which takes O(N) after all.

    // Add all intervals starting before newInterval.
    while (idx < n && intervals[idx][0] < newStart) {
      output.add(intervals[idx]);
      idx++;
    }

    // Add newInterval.
    // Check if the end of the last is less than newStart.
    // LinkedList.getLast() throws exception if the list is empty.
    if (output.isEmpty() || output.getLast()[1] < newStart) {
      // There is no overlap, just add the interval.
      output.add(newInterval);
    } else {
      // There is an overlap, merge with the last interval.
      output.getLast()[1] = Math.max(output.getLast()[1], newEnd);
    }

    // Add next intervals, merge with newInterval if needed.
    while (idx < n) {
      int[] interval = intervals[idx];
      int start = interval[0], end = interval[1];
      if (output.getLast()[1] < start) {
        // There is no overlap, just add an interval.
        output.add(interval);
      } else {
        // There is an overlap, merge with the last interval.
        output.getLast()[1] = Math.max(output.getLast()[1], end);
      }
      idx++;
    }

    return output.toArray(new int[output.size()][2]);
  }

  // For testing.
  @SuppressWarnings("unused")
  public static void main(String[] args) {
    InsertInterval solution = new InsertInterval();

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
