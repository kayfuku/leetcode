package leetcode;

import java.util.PriorityQueue;

public class MergeKSortedLists {
  // fields and classes here.
  // private int count;

  public MergeKSortedLists() {
    // Initialization here.
    // this.count = 0;

  }

  // 4. Priority Queue (Good for interview)
  // We're gonna use a min heap, which is a kind of priority queue and allows us
  // to put items in the queue and take out a minimum value item in O(1) time.
  // First off, put all the head nodes in the heap. Then, take out the minimum
  // node and append that node to the final list. Then, check the next node in
  // that list, and if there is a next node, then put the next node in the heap.
  // Repeat that while the heap is not empty. Then, return the final list.
  //
  // O(NlogK) time, where N is total number of nodes and K is the number of lists.
  // To find minimum value node, it takes O(logK) time and there are N nodes in
  // the final list.
  // O(N) space because of the final list.
  // Author: reeclappl + kei
  // Date : June 4, 2021
  public ListNode mergeKLists(ListNode[] lists) {
    if (lists == null || lists.length == 0) {
      return null;
    }

    // Min Heap
    PriorityQueue<ListNode> minHeap = new PriorityQueue<>(lists.length, //
        (ListNode o1, ListNode o2) -> o1.val - o2.val);

    ListNode dummy = new ListNode(0);
    ListNode tail = dummy;

    // Put the head nodes in the heap.
    for (ListNode node : lists) {
      if (node != null) {
        minHeap.offer(node);
      }
    }

    while (!minHeap.isEmpty()) {
      // Take the minimum val node.
      ListNode node = minHeap.poll();
      // Connect it to the final list.
      tail.next = node;
      tail = node;

      if (node.next != null) {
        // Put the next node in that list in the heap.
        minHeap.offer(node.next);
      }
    }

    return dummy.next;
  }

  // 5. Divide and Conquer (No good for interview)
  // O(NlogK) time because the number of sorted lists almost reduces by half.
  // O(logK) space
  // Author: mouqi123 + kei
  // Date : June 3, 2021
  public ListNode mergeKLists2(ListNode[] lists) {
    if (lists == null || lists.length == 0) {
      return null;
    }
    return partition(lists, 0, lists.length - 1);
  }

  public ListNode partition(ListNode[] lists, int s, int e) {
    if (s == e) {
      return lists[s];
    }

    int m = (s + e) / 2;
    ListNode l1 = partition(lists, s, m);
    ListNode l2 = partition(lists, m + 1, e);

    return merge(l1, l2);
  }

  // Compare two head nodes of the two lists.
  public ListNode merge(ListNode l1, ListNode l2) {
    // If either of l1 or l2 is end of list, then return the other.
    if (l1 == null) {
      return l2;
    }
    if (l2 == null) {
      return l1;
    }
    // Return the smaller one.
    if (l1.val <= l2.val) {
      // l1 is smaller. Return it
      // Compare the next node of l1 with l2.
      ListNode nextL1 = l1.next;
      l1.next = merge(nextL1, l2);
      return l1;
    } else {
      // l2 is smaller.
      // Compare l1 with the next node of l2.
      ListNode nextL2 = l2.next;
      l2.next = merge(l1, nextL2);
      return l2;
    }
  }

  /**
   * Definition for singly-linked list. public class ListNode { int val; ListNode
   * next; ListNode() {} ListNode(int val) { this.val = val; } ListNode(int val,
   * ListNode next) { this.val = val; this.next = next; } }
   */

  // For testing.
  @SuppressWarnings("unused")
  public static void main(String[] args) {
    MergeKSortedLists solution = new MergeKSortedLists();

    // Test arguments.
    // int num = 24;
    // int target = 2;
    // System.out.println(solution.getInt(num, target));

    System.out.println("\ndone.");
  }

}
