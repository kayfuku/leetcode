//
// Author:
// Date : July 15, 2019

package leetcode;

import java.util.Arrays;
import java.util.Comparator;

public class MeetingRooms {
  // fields and classes here.
  // private int count;

  public MeetingRooms() {
    // Initialization here.
    // this.count = 0;
  }

  // Sort the meetings by starting time. Then, go through the meetings one by one, and
  // make sure that each meeting ends before the next one starts.
  // Author: jiangyucara + kei
  // Date : July 15, 2019
  public boolean canAttendMeetings(int[][] intervals) {
    // corner: len:0, 1 => ok.
    if (intervals == null || intervals.length == 0 || intervals.length == 1) {
      return true;
    }

    // Use Arrays.sort() to sort an array of primitive types.
    // Use Comparator<int[]>.
    Arrays.sort(intervals, new Comparator<int[]>() {
      @Override
      public int compare(int[] o1, int[] o2) {
        // Sort by the first elem (start time) in ascending order.
        return o1[0] - o2[0];
      }
    });

    for (int i = 1; i < intervals.length; i++) {
      if (intervals[i][0] < intervals[i - 1][1]) {
        // The start time is earlier than the end time of the previous meeting.
        return false;
      }
    }

    return true;
  }



  // For testing.
  public static void main(String[] args) {
    MeetingRooms solution = new MeetingRooms();

    // Test arguments.
    // int num = 24;
    // int target = 2;
    // solution.getInt(num, target);



  }

}


