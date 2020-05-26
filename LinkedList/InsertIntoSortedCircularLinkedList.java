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

    public Node() {}

    public Node(int val) {
      this.val = val;
    }

    public Node(int val, Node next) {
      this.val = val;
      this.next = next;
    }
  }


  // Author: DarKzzl + kei
  // Date : May 25, 2020
  public Node insert(Node head, int insertVal) {
    if (head == null) {
      Node curr = new Node(insertVal);
      curr.next = curr;
      return curr;
    }
    Node curr = head.next;
    Node prev = head;
    boolean found = false;

    while (curr != head) {
      int next = curr.val;
      int pre = prev.val;
      if (insertVal == pre || (insertVal <= next && insertVal > pre) /* min < iv < max */
          || (next < pre && insertVal > pre) /* iv > max */
          || (next < pre && insertVal < next) /* iv < min */ ) {
        found = true;
        Node node = new Node(insertVal);
        node.next = curr;
        prev.next = node;
        break;
      }

      prev = curr;
      curr = curr.next;
    }

    // Every node in the list is the same value.
    // insertVal is less than or greater than that value.
    if (!found) {
      Node node = new Node(insertVal);
      node.next = curr;
      prev.next = node;
    }

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


