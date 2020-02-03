//
// Author:
// Date : December 25, 2019

package leetcode;

public class SwapNodesInPairs {
  // fields and classes here.
  // private int count;

  public SwapNodesInPairs() {
    // Initialization here.
    // this.count = 0;

  }



  // 1. Recursion
  // O(N) time, O(N) space.
  // Author: @godayaldivya + kei
  // Date : January 30, 2020
  public ListNode swapPairs(ListNode head) {
    // Corner case: num node 0=>ok, 1=>ok, 2=>ok

    // If the list has no node or has only one node left.
    if ((head == null) || (head.next == null)) {
      return head;
    }

    // Nodes to be swapped
    ListNode firstNode = head;
    ListNode secondNode = head.next;

    ListNode swappedList = swapPairs(secondNode.next);

    // Swapping
    // Every recursion call is responsible for swapping a pair of nodes.
    firstNode.next = swappedList;
    secondNode.next = firstNode;

    // Now the head is the second node.
    return secondNode;
  }



  // 2. Iterative
  // O(N) time, O(1) space.
  // Author: @godayaldivya + kei
  // Date : December 25, 2019
  public ListNode swapPairs2(ListNode head) {
    // Dummy node acts as the prevNode for the head node
    // of the list and hence stores pointer to the head node.
    ListNode dummy = new ListNode(-1);
    dummy.next = head;
    ListNode curr = head;
    ListNode prev = dummy;

    // There should be two nodes from the curr node to swap.
    while ((curr != null) && (curr.next != null)) {

      // Nodes to be swapped
      ListNode first = curr;
      ListNode second = curr.next;

      // Swap.
      prev.next = second;
      first.next = second.next;
      second.next = first;

      // Reinitialize the curr and prev for next swap.
      prev = first;
      curr = first.next; // jump
    }

    // Return the new list.
    return dummy.next;
  }



  // For testing.
  public static void main(String[] args) {
    SwapNodesInPairs solution = new SwapNodesInPairs();

    // Test arguments.
    // int num = 24;
    // int target = 2;
    // solution.getInt(num, target);



  }

}


