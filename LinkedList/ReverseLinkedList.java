//
// Author:
// Date : June 19, 2019

package leetcode;

public class ReverseLinkedList {
  // fields here.
  // private int count;

  public ReverseLinkedList() {
    // Initialization here.
    // this.count = 0;
  }

  // 1. Iterative. This one (iterative) or third one (recursive) is good for
  // interview.
  // O(N) time, O(1) space.
  public ListNode reverseList(ListNode head) {
    // corner: null, 1, => ok

    // This is going to be the last null.
    ListNode prev = null;
    ListNode cur = head;
    // The third pointer should be after the null check.
    // ListNode nextNode = cur.next; // NG!

    // We need to process the last node. That's why 'cur != null',
    // not 'cur.next != null'.
    while (cur != null) {
      // Save the pointer before cutting the link to cur.next so that
      // I can move forward the cur pointer later.
      ListNode nextNode = cur.next;

      // Reverse the link. Reverse the arrow.
      // Cut the link to refer to the previous node.
      cur.next = prev;

      // Move the pointers.
      prev = cur;
      cur = nextNode;
      // nextNode = cur.next; // NG! because cur could be null.
    }

    // prev now refers to the tail node, which is the head node of
    // the reversed list.
    return prev;
  }

  // 2. Recursive. Postorder. Next solution is the best.
  // O(N) time, O(N) space.
  public ListNode reverseList2(ListNode node) {
    // You can add node == null later when you explain the corner case.
    // node.next == null means when the node gets to the last node.
    if (node == null || node.next == null) {
      return node;
      // return null; // NG!
    }

    // It's like Post-order traversal because we cannot cut the link
    // before going further on the list.
    // First, we go all the way to the tail node. As we come back,
    // we process two nodes for each recursion stack.
    // The base case returns the tail node, which is the head node of
    // the list to be reversed.
    ListNode lastNode = reverseList2(node.next);

    // Reverse the link. This is the point!
    node.next.next = node;
    // Put a null lid on the node to avoid doubly linked list.
    node.next = null;

    // Return the tail node returned from the base case.
    // And the node has not been processed in the post-order traversal,
    // which means, return the node from the base case up until the
    // top of the recursion stack.
    return lastNode;
  }

  // 2-3. Recursive. Preorder. This is the best.
  // O(N) time, O(N) space, and tail recursion benefit
  // Author: kei (AC)
  // Date : September 4, 2020
  public ListNode reverseListRecur2(ListNode node) {
    ListNode prev = null;
    return preorder(prev, node);
  }

  private ListNode preorder(ListNode prev, ListNode curr) {
    // You can add node == null later when you explain the corner case.
    if (curr == null) {
      return prev;
    }

    ListNode nextNode = curr.next;
    // Reverse the link. This is the point!
    curr.next = prev;

    // Tail recursion benefit
    return preorder(curr, nextNode);
  }

  // 2-4. Recursive.
  // O(N) time, O(N) space.
  // Author: kei (AC)
  // Date : September 4, 2020
  ListNode prev = null;

  public ListNode reverseListRecur3(ListNode node) {
    preorder2(prev, node);
    return prev;
  }

  private void preorder2(ListNode p, ListNode curr) {
    if (curr == null) {
      return;
    }

    ListNode nextNode = curr.next;
    curr.next = p;

    prev = curr;
    curr = nextNode;
    preorder2(prev, curr);
  }

  // Review
  public ListNode reverseListR(ListNode head) {
    // corner: null, 1 => ok

    ListNode prev = null;
    ListNode cur = head;

    while (cur != null) {
      ListNode nextTemp = cur.next;
      cur.next = prev;

      prev = cur;
      cur = nextTemp;
    }

    return prev;
  }

  // Review
  public ListNode reverseListRecurR(ListNode node) {
    if (node == null || node.next == null) {
      return node;
    }

    ListNode lastNode = reverseListRecurR(node.next);
    node.next.next = node;
    node.next = null;

    return lastNode;
  }

  // For testing.
  public static void main(String[] args) {
    ReverseLinkedList solution = new ReverseLinkedList();

    // Test arguments.
    // int num = 24;
    // int target = 2;
    // solution.getInt(num, target);

    MyLinkedList list = new MyLinkedList();
    list.add(1);
    list.add(2);
    list.add(3);
    System.out.println(list.toString()); // [ 1 2 3 ]
    ListNode node = solution.reverseListRecur3(list.head);
    System.out.println(node); // 3

  }

}
