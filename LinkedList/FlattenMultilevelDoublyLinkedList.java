//
// Author:
// Date : May 23, 2020

package leetcode;

import java.util.ArrayDeque;
import java.util.Deque;

public class FlattenMultilevelDoublyLinkedList {
  // fields and classes here.
  // private int count;

  public FlattenMultilevelDoublyLinkedList() {
    // Initialization here.
    // this.count = 0;

  }

  // Definition for a Node.
  class Node {
    public int val;
    public Node prev;
    public Node next;
    public Node child;

    public Node(int val, Node prev, Node next, Node child) {
      this.val = val;
      this.prev = prev;
      this.next = next;
      this.child = child;
    }
  };


  // 1. DFS Recursion
  // Author: LeetCode + kei
  // Date : May 23, 2020
  public Node flattenRecur(Node head) {
    if (head == null) {
      return head;
    }
    // Pseudo head to ensure the 'prev' pointer is never null
    Node dummyHead = new Node(0, null, head, null);

    flattenDFS(dummyHead, head);

    // Detach the dummy head from the real head.
    dummyHead.next.prev = null;

    return dummyHead.next;
  }

  /* Return the tail of the flatten list */
  public Node flattenDFS(Node prev, Node curr) {
    if (curr == null) {
      return prev;
    }
    // Link the two nodes together.
    curr.prev = prev;
    prev.next = curr;

    // curr.next would be tempered in the recursive function
    // curr.next is the entrance of the right subtree.
    Node tempNext = curr.next;

    // Traverse the left subtree first.
    // tail is the tail of the list to be returned, or the front node of DFS.
    // tail is needed in order to link the last node in the left subtree and the
    // entrance node of the right subtree one level above the recursion stack.
    Node tail = flattenDFS(curr, curr.child);
    curr.child = null;

    // Traverse the right subtree.
    return flattenDFS(tail, tempNext);
  }


  // 2. DFS Iteration
  // Author: LeetCode + kei
  // Date : May 23, 2020
  public Node flatten(Node head) {
    if (head == null) {
      return head;
    }

    Node dummyHead = new Node(0, null, head, null);
    Node curr, prev = dummyHead;

    Deque<Node> stack = new ArrayDeque<>();
    stack.push(head);

    while (!stack.isEmpty()) {
      curr = stack.pop();
      prev.next = curr;
      curr.prev = prev;

      // Push the child nodes into the stack.
      // Right first
      if (curr.next != null) {
        stack.push(curr.next);
      }
      // Left next
      if (curr.child != null) {
        stack.push(curr.child);
        // Don't forget to remove all child pointers.
        curr.child = null;
      }

      // Don't forget this.
      prev = curr;
    }

    // Detach the dummy head from the result.
    dummyHead.next.prev = null;

    return dummyHead.next;
  }



  // For testing.
  @SuppressWarnings("unused")
  public static void main(String[] args) {
    FlattenMultilevelDoublyLinkedList solution = new FlattenMultilevelDoublyLinkedList();

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


