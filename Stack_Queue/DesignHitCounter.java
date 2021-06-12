package leetcode;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Queue;

// ** Change the name to HitCounter. 
public class DesignHitCounter {

  // 1. Queue (Next one is better for interview)
  // Author: leetcode + kei
  // Date : June 2, 2021

  private Queue<Integer> q;

  /** Initialize your data structure here. */
  // ** Change the name to HitCounter.
  // public DesignHitCounter() {
  // this.q = new LinkedList<>();
  // }

  /**
   * O(1) time
   * 
   * @param timestamp - The current timestamp (in seconds granularity).
   */
  public void hit2(int timestamp) {
    this.q.add(timestamp);
  }

  /**
   * Assuming a total of n values present in the queue at a time and the total
   * number of timestamps encountered throughout is N. In the worst case scenario,
   * we might end up removing all the entries from the queue in getHits method if
   * the difference in timestamp is greater than or equal to 300. Hence in the
   * worst case, a "single" call to the getHits method can take O(n) time.
   * However, we must notice that each timestamp is processed only twice (first
   * while adding the timestamp in the queue in hit method and second while
   * removing the timestamp from the queue in the getHits method). Hence if the
   * total number of timestamps encountered throughout is N, the overall time
   * taken by getHits method is O(N). This results in an amortized time complexity
   * of O(1) for a single call to getHits method.
   * 
   * @param timestamp - The current timestamp (in seconds granularity).
   */
  public int getHits2(int timestamp) {
    while (!this.q.isEmpty()) {
      // Be careful of the condition.
      // We want to count Hits between timestamp - 300 + 1 and timestamp inclusive
      if (timestamp - this.q.peek() >= 300) {
        this.q.poll();
      } else {
        // The queue contains the only timestamps we want.
        break;
      }
    }

    // The queue may contain two or more of the same timestamps in it.
    return this.q.size();
  }

  // 2. Using Deque with Pairs (This is better for interview)
  // We'll keep timestamp and the count for the timestamp.
  // We'll use the "deque" data structure which allows us to insert and delete
  // values from both ends of the queue.
  //
  // Author: leetcode + kei
  // Date : June 7, 2021

  private int total;
  // int[]: [timestamp, count]
  private Deque<int[]> hits;

  // ** Change the name to HitCounter.
  public DesignHitCounter() {
    this.total = 0;
    this.hits = new ArrayDeque<>();
  }

  /**
   * Record a hit.
   * 
   * @param timestamp - The current timestamp (in seconds granularity).
   */
  public void hit(int timestamp) {
    // Be careful the queue is empty when you use the method.
    if (this.hits.isEmpty() || this.hits.getLast()[0] != timestamp) {
      // Insert the new timestamp with count = 1
      this.hits.add(new int[] { timestamp, 1 });
    } else {
      // The latetst timestamp in the deque is the same as the current timestamp.
      // Update the count of latest timestamp by incrementing the count by 1.
      this.hits.getLast()[1]++;
    }

    // Increment total
    this.total++;
  }

  /**
   * Return the number of hits in the past 5 minutes (300 seconds). O(N) time,
   * where N is the total number of hits because in the worst case, the deque can
   * have N timestamps. O(N) space
   * 
   * @param timestamp - The current timestamp (in seconds granularity).
   */
  public int getHits(int timestamp) {
    // Always think about what happens when the queue is empty.
    // Check if the difference between the current timestamp and the oldest
    // timestamp is greater than or equal to 300, if true, then it's out of
    // the time window.
    while (!this.hits.isEmpty() && timestamp - this.hits.getFirst()[0] >= 300) {
      // Subtract the count of the oldest timestamp from total.
      this.total -= this.hits.getFirst()[1];
      this.hits.removeFirst();
    }

    return this.total;
  }

  /**
   * Your HitCounter object will be instantiated and called as such: HitCounter
   * obj = new HitCounter(); obj.hit(timestamp); int param_2 =
   * obj.getHits(timestamp);
   */

  // Review
  class HitCounterR {

    private int count;
    // int[]: [timestamp, count]
    private Deque<int[]> queue;

    HitCounterR() {
      this.count = 0;
      this.queue = new ArrayDeque<>();
    }

    public void hit(int timestamp) {
      if (queue.isEmpty() || queue.getLast()[0] != timestamp) {
        queue.add(new int[] { timestamp, 1 });
      } else {
        queue.getLast()[1]++;
      }
      count++;
    }

    public int getHits(int timestamp) {
      while (!queue.isEmpty() && timestamp - queue.getFirst()[0] >= 300) {
        count -= queue.getFirst()[1];
        queue.poll();
      }

      return count;
    }
  }

  // // Review. NG!
  // class HitCounterR {

  // Deque<int[]> queue;
  // int count;

  // HitCounterR() {
  // queue = new ArrayDeque<>();
  // this.count = 0;
  // }

  // public void hit(int timestamp) {
  // // NG! What happens when the queue is empty?
  // if (queue.getLast()[0] == timestamp) {
  // queue.getLast()[1] += this.count;
  // } else {
  // queue.add(new int[] { timestamp, 1 });
  // }
  // this.count++;
  // }

  // public int getHits(int timestamp) {
  // // NG! Be aware of the order of subtraction.
  // // NG! What happens when the queue is empty?
  // while (queue.getFirst()[0] - timestamp >= 300) {
  // count -= queue.getFirst()[1];
  // queue.poll();
  // }
  // return this.count;
  // }

  // }

  // For testing.
  @SuppressWarnings("unused")
  public static void main(String[] args) {
    DesignHitCounter solution = new DesignHitCounter();

    // Test arguments.
    // int num = 24;
    // int target = 2;
    // System.out.println(solution.getInt(num, target));

    System.out.println("\ndone.");
  }

}
