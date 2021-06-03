package leetcode;

import java.util.LinkedList;
import java.util.Queue;

// ** Change the name to HitCounter. 
public class DesignHitCounter {

  // 1. Queue (There is more efficient solution using Deque with pairs)
  // Author: leetcode + kei
  // Date : June 2, 2021

  private Queue<Integer> hits;

  /** Initialize your data structure here. */
  // ** Change the name to HitCounter.
  public DesignHitCounter() {
    this.hits = new LinkedList<>();
  }

  /**
   * O(1) time
   * 
   * @param timestamp - The current timestamp (in seconds granularity).
   */
  public void hit(int timestamp) {
    this.hits.add(timestamp);
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
   * total number of timestamps encountered throughout is NN, the overall time
   * taken by getHits method is O(N)O(N). This results in an amortized time
   * complexity of O(1) for a single call to getHits method.
   * 
   * @param timestamp - The current timestamp (in seconds granularity).
   */
  public int getHits(int timestamp) {
    while (!this.hits.isEmpty()) {
      // Be careful of the condition.
      // We want to count Hits between timestamp - 300 + 1 and timestamp inclusive
      if (timestamp - this.hits.peek() >= 300) {
        this.hits.poll();
      } else {
        // The queue contains the only timestamps we want.
        break;
      }
    }

    // The queue may contain two or more of the same timestamps in it.
    return this.hits.size();
  }

  /**
   * Your HitCounter object will be instantiated and called as such: HitCounter
   * obj = new HitCounter(); obj.hit(timestamp); int param_2 =
   * obj.getHits(timestamp);
   */

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
