//
// Author:
// Date : March 27, 2020

package leetcode;

import java.util.Arrays;

public class VideoStitching {
  // fields and classes here.
  // private int count;

  public VideoStitching() {
    // Initialization here.
    // this.count = 0;

  }


  // Solution 1, Sort
  // O(NlogN) time, O(1)
  // Author: lee215 + kei (AC)
  // Date : March 27, 2020
  public int videoStitching2(int[][] clips, int T) {
    int count = 0;
    // Sort by start time in ascending order.
    Arrays.sort(clips, (a, b) -> a[0] - b[0]);
    int end = 0, currEnd = 0;
    int i = 0;
    while (end < T) {
      // Find possible max end time.
      // clips[i][0] <= end checks overlapping.
      // Check if there is end time later than current end time.
      while (i < clips.length && clips[i][0] <= end) {
        currEnd = Math.max(currEnd, clips[i][1]);
        i++;
      }
      if (end == currEnd) {
        // There is no end time later than current end time and
        // current end time is earlier than T.
        return -1;
      }
      end = currEnd;
      count++;
    }
    return count;
  }


  // Solution 2, DP
  // O(NT) time, O(T)
  // Author: lee215 + kei (AC)
  // Date : March 27, 2020
  public int videoStitching(int[][] clips, int T) {
    // dp[t] is the minimum number of clips to cover [0, t].
    int[] dp = new int[T + 1];
    // Fill with T + 1, which is greater than max T.
    // Why not -1? Because we use min accumulator later.
    // Why not Integer.MAX_VALUE? Because we add 1 before taking min. It's going to overflow.
    Arrays.fill(dp, T + 1);
    // No clip needed to cover [0, 0].
    dp[0] = 0;
    for (int t = 1; t <= T; t++) {
      // Iterate through all the clips that cover t. No need to sort clips.
      for (int[] currClip : clips) {
        if (currClip[0] <= t && t <= currClip[1]) {
          // We can cover [start time, t] with currClip, and cover [0, start time]
          // with dp[start time] clips.
          // Take the minimum between dp[t] and the one using currClip.
          dp[t] = Math.min(dp[t], dp[currClip[0]] + 1);
        }
      }
    }

    return (dp[T] == T + 1) ? -1 : dp[T];
  }



  // For testing.
  @SuppressWarnings("unused")
  public static void main(String[] args) {
    VideoStitching solution = new VideoStitching();

    // Test arguments.
    // int num = 24;
    // int target = 2;
    // solution.getInt(num, target);

    int[][] clips = {{0, 2}, {4, 6}, {8, 10}, {1, 9}, {1, 5}, {5, 9}};
    int T = 10;
    int ret = solution.videoStitching(clips, T);
    System.out.println(ret);



  }



  public void dummyMethod() {



  }



  public void dummyMethod2() {



  }



}


