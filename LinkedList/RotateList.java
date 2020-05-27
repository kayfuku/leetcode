//
// Author:
// Date : May 26, 2020

package leetcode;

public class RotateList {
  // fields and classes here.
  // private int count;

  public RotateList() {
    // Initialization here.
    // this.count = 0;

  }

  public class ListNode {
    int val;
    ListNode next;

    ListNode() {}

    ListNode(int val) {
      this.val = val;
    }

    ListNode(int val, ListNode next) {
      this.val = val;
      this.next = next;
    }
  }


  // Author: LeetCode + kei
  // Date : May 26, 2020
  public ListNode rotateRight(ListNode head, int k) {
    if (head == null) {
      return null;
    }
    if (head.next == null) {
      // Just one node
      return head;
    }

    // Close the linked list into the ring.
    ListNode oldTail = head;
    int n = 1;
    while (oldTail.next != null) {
      oldTail = oldTail.next;
      n++;
    }
    // Assert that n is the list size.
    oldTail.next = head;

    // Find the new tail node.
    // Rotate 2 => The last two nodes move to the head, which means
    // The 3rd node to the last is the new tail node.
    // The new tail node is (n - 1 - k) edge away from head.
    // We can actually replace k by k % n in order to consider the case
    // where k >= n.
    // So, the new tail node is (n - 1 - (k % n)) edge away from head.
    // n - 1 is # edges in the list. k % n is the distance between
    // the last node and the new tail node.
    // If the k is the multiples of n, then the rotated list is the same as
    // the original list. k % n is always the number of rotation places
    // smaller than n.
    ListNode newTail = head;
    for (int i = 0; i < n - 1 - (k % n); i++) {
      newTail = newTail.next;
    }
    ListNode newHead = newTail.next;

    // Break the ring.
    newTail.next = null;

    return newHead;
  }



  // For testing.
  @SuppressWarnings("unused")
  public static void main(String[] args) {
    RotateList solution = new RotateList();

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


