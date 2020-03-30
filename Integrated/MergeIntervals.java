//
// Author:
// Date : May 29, 2019

package leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;


public class MergeIntervals {

  // Some fields here.
  // private int count;


  public MergeIntervals() {
    // Initialization here.
    // count = 0;
  }


  // Time complexity : O(NlogN)
  // Other than the sort invocation, we do a simple linear scan of the list,
  // so the runtime is dominated by the O(NlgN) complexity of sorting.
  // Space complexity : O(1) (or O(N))
  // If we can sort intervals in place, we do not need more than constant additional space.
  // Otherwise, we must allocate linear space to store a copy of intervals and sort that.

  private class Interval {
    int start;
    int end;
  }

  private class IntervalComparator implements Comparator<Interval> {
    @Override
    // Sort by start time.
    public int compare(Interval a, Interval b) {
      // return (a.start.compareTo(b.start); // NG! primitive type does not have compareTo().
      // When the order of args (o1, o2) and the return o1 - o2, the elements are sorted
      // in ascending order.
      return a.start - b.start;
    }
  }

  public int[][] merge(int[][] intervals) {
    if (intervals.length == 0) {
      // A new empty 2D array.
      return new int[][] {};
    }

    // Preprocess.
    LinkedList<Interval> intvls = new LinkedList<>();
    for (int i = 0; i < intervals.length; i++) {
      Interval interval = new Interval();
      interval.start = intervals[i][0];
      interval.end = intervals[i][1];
      intvls.add(interval);
    }

    // Sort by start time in ascending order. (Custom Sort)
    Collections.sort(intvls, new IntervalComparator());

    // Note that the type is LinkedList<Interval>, not List<Interval> because
    // we want to use getLast().
    LinkedList<Interval> merged = new LinkedList<>();
    // foreach guarantees the order in which the element was inserted.
    for (Interval interval : intvls) {
      if (merged.isEmpty() || merged.getLast().end < interval.start) {
        // Not overlapped.
        merged.add(interval);
      } else {
        // Overlapped case. Merge them.
        // Here is the point!
        // Just update the end of the last interval by taking the larger end.
        // Remember the elements we are inserting are sorted by start time.
        // We don't have to care about the start time.
        merged.getLast().end = Math.max(merged.getLast().end, interval.end);
      }
    }

    // Post-process.
    // Form the 2d array as the answer.
    int[][] ans = new int[merged.size()][2];
    for (int i = 0; i < merged.size(); i++) {
      ans[i][0] = merged.get(i).start;
      ans[i][1] = merged.get(i).end;
    }

    return ans;
  }


  // Review. The above is better.
  public int[][] mergeR(int[][] intervals) {
    // corner
    if (intervals.length == 0) {
      return new int[][] {};
    }

    ArrayList<IntervalR> intrvls = new ArrayList<>();
    for (int i = 0; i < intervals.length; i++) {
      IntervalR intrvl = new IntervalR();
      intrvl.start = intervals[i][0];
      intrvl.end = intervals[i][1];
      intrvls.add(intrvl);
    }

    // Sort by start time.
    Collections.sort(intrvls, new IntervalComparatorR());
    System.out.println(intrvls.toString());

    LinkedList<IntervalR> merged = new LinkedList<>();
    merged.add(intrvls.get(0));
    int maxEnd = intrvls.get(0).end;
    for (int i = 1; i < intrvls.size(); i++) {
      IntervalR intrvl = intrvls.get(i);
      if (intrvl.start > merged.getLast().end) {
        // No need to merge.
        merged.add(intrvl);
        maxEnd = intrvl.end;
      } else {
        // Merge.
        if (intrvl.end > maxEnd) {
          maxEnd = intrvl.end;
          merged.getLast().end = maxEnd;
        }
      }
    }

    int[][] ret = new int[merged.size()][2];
    for (int i = 0; i < ret.length; i++) {
      ret[i][0] = merged.get(i).start;
      ret[i][1] = merged.get(i).end;
    }

    return ret;
  }

  class IntervalR {
    int start;
    int end;

    public String toString() {
      return "[ " + String.valueOf(start) + ", " + String.valueOf(end) + " ]";
    }
  }

  class IntervalComparatorR implements Comparator<IntervalR> {
    @Override
    public int compare(IntervalR o1, IntervalR o2) {
      return o1.start - o2.start;
    }
  }



  // For testing.
  public static void main(String[] args) {
    MergeIntervals solution = new MergeIntervals();

    // Test arguments.
    // int num = 24;
    // int target = 2;
    // solution.getInt(num, target);

    // int[][] nums = new int[][]{ { 2, 3 },
    // { 1, 4 } };
    // int[][] ans = solution.merge(nums);
    // for (int[] is : ans) {
    // System.out.println(Arrays.toString(is)); // [1, 4]
    // }

    int[][] nums2 = new int[][] {{1, 3}, {2, 6}, {8, 10}, {15, 18}};
    int[][] ans2 = solution.merge(nums2);
    for (int[] is : ans2) {
      System.out.println(Arrays.toString(is));
    }
    // [1, 6]
    // [8, 10]
    // [15, 18]

    nums2 = new int[][] {{1, 4}, {0, 2}, {3, 5}};
    ans2 = solution.mergeR(nums2);
    for (int[] is : ans2) {
      System.out.println(Arrays.toString(is)); // [0, 5]
    }

    nums2 = new int[][] {{4, 5}, {2, 4}, {4, 6}, {3, 4}, {0, 0}, {1, 1}, {3, 5}, {2, 2}};
    ans2 = solution.mergeR(nums2);
    for (int[] is : ans2) {
      System.out.println(Arrays.toString(is)); //
    }


  }

}


