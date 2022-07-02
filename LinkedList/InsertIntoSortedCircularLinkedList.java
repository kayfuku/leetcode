//
// Author:
// Date : May 25, 2020

package leetcode;

public class InsertIntoSortedCircularLinkedList {
  // fields and classes here.
  // private int count;

  public InsertIntoSortedCircularLinkedList() {
    // Initialization here.
    // this.count = 0;

  }

  class Node {
    public int val;
    public Node next;

    public Node() {
    }

    public Node(int val) {
      this.val = val;
    }

    public Node(int val, Node next) {
      this.val = val;
      this.next = next;
    }
  }

  // Author: leetcode + kei
  // Date : May 25, 2020, July 2, 2022
  public Node insert(Node head, int insertVal) {
    if (head == null) {
      Node newNode = new Node(insertVal);
      newNode.next = newNode;
      return newNode;
    }
    Node curr = head.next;
    Node prev = head;
    // Note that you cannnot put curr != head.next here to check the last
    // interval between the head and the tail. Insert it later.
    while (curr != head) {
      int next = curr.val;
      int pre = prev.val;
      if ((insertVal <= next && insertVal >= pre) /* min <= iv <= max */
          || (pre > next && insertVal > pre) /* iv > max */
          || (pre > next && insertVal < next) /* iv < min */ ) {
        Node newNode = new Node(insertVal, curr);
        prev.next = newNode;
        return head;
      }

      prev = curr;
      curr = curr.next;
    }

    // The insertVal is in the last interval between the head and the tail
    // whatever the value is.
    // The value could be prev <= insertVal <= next if the head is not min or
    // max in the list.
    // The value could be a new min or max if the head is min in the list.
    Node newNode = new Node(insertVal, curr);
    prev.next = newNode;

    return head;
  }

  // For testing.
  @SuppressWarnings("unused")
  public static void main(String[] args) {
    InsertIntoSortedCircularLinkedList solution = new InsertIntoSortedCircularLinkedList();

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
