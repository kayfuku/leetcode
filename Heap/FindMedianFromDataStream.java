package leetcode;

import java.util.PriorityQueue;

public class FindMedianFromDataStream {
  // fields and classes here.
  // private int count;

  // Two Heaps
  // To guarantee that there are two groups and the numbers in one group are
  // all less than any number in another group.
  //
  // Author: leetcode + kei
  // Date : February 11, 2021

  PriorityQueue<Integer> minHeap;
  PriorityQueue<Integer> maxHeap;

  public FindMedianFromDataStream() {
    minHeap = new PriorityQueue<>((a, b) -> a - b);
    maxHeap = new PriorityQueue<>((a, b) -> b - a);
  }

  // Adds a number into the data structure.
  // O(logN) time, O(N) space, where N is the number of items.
  void addNum(int num) {
    // First, add to min heap.
    minHeap.offer(num);

    // Taking out the minimum from the min heap and putting it in the max heap
    // makes it possible that the numbers in the max heap are all less than any
    // number in the min heap.
    // That means that the minimum of the min heap or the maximum of the max heap
    // could be median.
    maxHeap.offer(minHeap.poll());

    // Maintain size property.
    // Keep the min heap size one bigger than or equal to the max heap size.
    // If the size of the min heap is bigger by one, then minHeap.peek() is the
    // median.
    if (maxHeap.size() > minHeap.size()) {
      minHeap.offer(maxHeap.poll());
    }
  }

  // Returns the median of current data stream.
  // O(1) time
  double findMedian() {
    if (minHeap.size() > maxHeap.size()) {
      // The min heap size is one bigger than max heap, which means
      // the minimum of the min heap is the median.
      return minHeap.peek();
    }
    // The min heap size and the max heap size are the same.
    return ((double) minHeap.peek() + maxHeap.peek()) / 2;
  }

  /**
   * Your MedianFinder object will be instantiated and called as such:
   * MedianFinder obj = new MedianFinder(); obj.addNum(num); double param_2 =
   * obj.findMedian();
   */

  // For testing.
  @SuppressWarnings("unused")
  public static void main(String[] args) {
    FindMedianFromDataStream solution = new FindMedianFromDataStream();

    // Test arguments.
    // int num = 24;
    // int target = 2;
    // System.out.println(solution.getInt(num, target));

    System.out.println("\ndone.");
  }

}
