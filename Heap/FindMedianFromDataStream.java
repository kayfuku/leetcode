package leetcode;

import java.util.PriorityQueue;

public class FindMedianFromDataStream {
  // fields and classes here.
  // private int count;

  public FindMedianFromDataStream() {
    // Initialization here.
    // this.count = 0;

    minHeap = new PriorityQueue<>((a, b) -> a - b);
    maxHeap = new PriorityQueue<>((a, b) -> b - a);
  }

  // Two Heaps
  // Author: leetcode + kei
  // Date : February 11, 2021

  PriorityQueue<Integer> minHeap; // max heap
  PriorityQueue<Integer> maxHeap; // min heap

  // Adds a number into the data structure.
  void addNum(int num) {
    // Add to min heap.
    minHeap.offer(num);

    // Balancing step
    maxHeap.offer(minHeap.poll());

    // Maintain size property.
    // Keep the min heap size one bigger than or equal to the max heap size.
    if (maxHeap.size() > minHeap.size()) {
      minHeap.offer(maxHeap.poll());
    }
  }

  // Returns the median of current data stream.
  double findMedian() {
    if (minHeap.size() > maxHeap.size()) {
      // The min heap size is one bigger than max heap, which means
      // the minimum of the min heap is the median.
      return minHeap.peek();
    }
    // The min heap size and the max heap size are the same.
    return ((double) minHeap.peek() + maxHeap.peek()) * 0.5;
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
