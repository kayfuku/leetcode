//
// Author:
// Date : January 6, 2020

package leetcode;

import java.util.LinkedList;

public class MovingAverageFromDataStream {
  // fields and classes here.
  // private int count;


  // 1. Deque
  // Streaming size is unknown, so you should think about two cases.
  // Streaming size is way smaller or larger than the window size.
  //
  // O(1) time, O(N) space, where N is the size of the moving window.
  // Author: @liaison and @andvary + kei
  // Date : January 6, 2020
  int winSize, winSum = 0, count = 0;
  LinkedList<Integer> queue = new LinkedList<>();

  public MovingAverageFromDataStream(int size) {
    this.winSize = size;
  }

  public double next(int val) {
    count++;
    // Add to the last.
    queue.add(val);
    int tail = 0;
    if (count > winSize) {
      // Remove from the head.
      tail = (int) queue.poll();
    }
    winSum = winSum - tail + val;

    // Streaming size can be smaller or larger than the window size.
    // * 1.0 keeps it type double.
    return winSum * 1.0 / Math.min(winSize, count);
  }

  /**
   * Your MovingAverage object will be instantiated and called as such: MovingAverage obj = new
   * MovingAverage(size); double param_1 = obj.next(val);
   */


  // 2. Circular queue version (See below)



  // For testing.
  public static void main(String[] args) {
    MovingAverageFromDataStream solution = new MovingAverageFromDataStream(0);

    // Test arguments.
    // int num = 24;
    // int target = 2;
    // solution.getInt(num, target);



  }

}



// 2. Circular queue version
// O(1) time, O(N) space, where N is the size of the moving window.
// Author: @liaison and @andvary + kei
// Date : January 6, 2020
class MovingAverageFromDataStream2 {
  // Only one pointer is needed.
  int winSize, head = 0, winSum = 0, count = 0;
  int[] queue;

  public MovingAverageFromDataStream2(int size) {
    this.winSize = size;
    queue = new int[size];
  }

  public double next(int val) {
    count++;
    int tail = (head + 1) % winSize;
    // If 'count' is smaller than 'winSize', queue[tail] is 0.
    winSum = winSum - queue[tail] + val;
    // Move on to the next head. If the count >= winSize, then
    // automatically remove the oldest val.
    head = (head + 1) % winSize;
    queue[head] = val;

    return winSum * 1.0 / Math.min(winSize, count);
  }



}


