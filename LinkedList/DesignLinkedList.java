//
// Author:
// Date : April 8, 2020

package leetcode;

public class DesignLinkedList {
  // fields and classes here.
  // private int count;

  // 1. Singly Linked List
  // Author: @liaison and @andvary + kei
  // Date : April 8, 2020
  public class ListNode {
    int val;
    ListNode next;

    ListNode(int x) {
      val = x;
    }
  }

  class MyLinkedList {
    int size;
    ListNode head; // sentinel node as pseudo-head

    public MyLinkedList() {
      size = 0;
      head = new ListNode(0);
    }

    /**
     * Get the value of the index-th node in the linked list. If the index is invalid, return -1.
     * O(N) time, O(1) space
     */
    public int get(int index) {
      // if index is invalid, return -1.
      if (index < 0 || index >= size) {
        return -1;
      }

      ListNode curr = head;
      // index + 1 steps needed to move from sentinel node to wanted index
      for (int i = 0; i < index + 1; i++) {
        curr = curr.next;
      }
      return curr.val;
    }

    /**
     * Add a node of value val before the first element of the linked list. After the insertion, the
     * new node will be the first node of the linked list. O(1) time, O(1) space
     */
    public void addAtHead(int val) {
      addAtIndex(0, val);
    }

    /**
     * Append a node of value val to the last element of the linked list. O(N) time, O(1) space
     */
    public void addAtTail(int val) {
      addAtIndex(size, val);
    }

    /**
     * Add a node of value val before the index-th node in the linked list. If index equals to the
     * length of linked list, the node will be appended to the end of linked list. If index is
     * greater than the length, the node will not be inserted. O(N) time, O(1) space
     */
    public void addAtIndex(int index, int val) {
      // If index is greater than the length,
      // the node will not be inserted.
      if (index > size) {
        return;
      }

      // [so weird] If index is negative,
      // the node will be inserted at the head of the list.
      if (index < 0) {
        index = 0;
      }

      size++;
      // Find predecessor of the node to be added.
      ListNode pred = head;
      // Move 'pred' 'index' nodes forward.
      for (int i = 0; i < index; i++) {
        pred = pred.next;
      }

      // node to be added
      ListNode newNode = new ListNode(val);
      // insertion itself
      newNode.next = pred.next;
      pred.next = newNode;
    }

    /**
     * Delete the index-th node in the linked list, if the index is valid. O(N) time, O(1) space
     */
    public void deleteAtIndex(int index) {
      // If the index is invalid, do nothing.
      if (index < 0 || index >= size) {
        return;
      }

      // Find predecessor of the node to be deleted.
      ListNode pred = head;
      for (int i = 0; i < index; ++i) {
        pred = pred.next;
      }

      // Delete the node.
      pred.next = pred.next.next;
      size--;
    }
  }


  // 2. Doubly Linked List
  // Author: @liaison and @andvary + kei
  // Date : April 8, 2020
  public class DoublyListNode {
    int val;
    DoublyListNode next;
    DoublyListNode prev;

    DoublyListNode(int x) {
      val = x;
    }
  }

  // head - node1 - node2 - ... - tail
  class MyDoublyLinkedList {
    int size;
    // sentinel nodes as pseudo-head and pseudo-tail
    DoublyListNode head, tail;

    public MyDoublyLinkedList() {
      size = 0;
      head = new DoublyListNode(0);
      tail = new DoublyListNode(0);
      // Don't forget this!
      head.next = tail;
      tail.prev = head;
    }

    /**
     * Get the value of the index-th node in the linked list. If the index is invalid, return -1.
     */
    public int get(int index) {
      // if index is invalid
      if (index < 0 || index >= size) {
        return -1;
      }

      // choose the fastest way: to move from the head
      // or to move from the tail
      DoublyListNode curr = head;
      // index + 1 is the distance between head and index.
      // size - index is the distance between tail and index.
      if (index + 1 < size - index) {
        for (int i = 0; i < index + 1; i++) {
          curr = curr.next;
        }
      } else {
        curr = tail;
        for (int i = 0; i < size - index; i++) {
          curr = curr.prev;
        }
      }

      return curr.val;
    }

    /**
     * Add a node of value val before the first element of the linked list. After the insertion, the
     * new node will be the first node of the linked list.
     */
    public void addAtHead(int val) {
      DoublyListNode pred = head, succ = head.next;

      size++;
      DoublyListNode newNode = new DoublyListNode(val);
      newNode.prev = pred;
      newNode.next = succ;
      pred.next = newNode;
      succ.prev = newNode;
    }

    /** Append a node of value val to the last element of the linked list. */
    public void addAtTail(int val) {
      DoublyListNode succ = tail, pred = tail.prev;

      size++;
      DoublyListNode newNode = new DoublyListNode(val);
      newNode.prev = pred;
      newNode.next = succ;
      pred.next = newNode;
      succ.prev = newNode;
    }

    /**
     * Add a node of value val before the index-th node in the linked list. If index equals to the
     * length of linked list, the node will be appended to the end of linked list. If index is
     * greater than the length, the node will not be inserted.
     */
    public void addAtIndex(int index, int val) {
      // If index is greater than the length,
      // the node will not be inserted.
      if (index > size) {
        return;
      }

      // [so weird] If index is negative,
      // the node will be inserted at the head of the list.
      if (index < 0) {
        index = 0;
      }

      // find predecessor and successor of the node to be added
      // succ should be at 'index' before insertion.
      DoublyListNode pred, succ;
      // Use bidirectional search to perform faster.
      // index is the distance between head and prev.
      // size - index is the distance between tail and index (succ).
      if (index < size - index) {
        // head is closer to the index
        pred = head;
        // Move pred 'index' nodes forward.
        for (int i = 0; i < index; i++) {
          pred = pred.next;
        }
        // succ is the next node.
        succ = pred.next;

      } else {
        // tail is closer to the index.
        succ = tail;
        // Move succ 'size - index' nodes backward.
        for (int i = 0; i < size - index; i++) {
          succ = succ.prev;
        }
        // pred is the previous node.
        pred = succ.prev;
      }

      // insertion itself
      size++;
      DoublyListNode newNode = new DoublyListNode(val);
      newNode.prev = pred;
      newNode.next = succ;
      pred.next = newNode;
      succ.prev = newNode;
    }

    /** Delete the index-th node in the linked list, if the index is valid. */
    public void deleteAtIndex(int index) {
      // if the index is invalid, do nothing
      if (index < 0 || index >= size) {
        return;
      }

      // find predecessor and successor of the node to be deleted
      DoublyListNode pred, succ;
      // Use bidirectional search to perform faster.
      if (index < size - index) {
        // head is closer to the index
        pred = head;
        for (int i = 0; i < index; i++) {
          pred = pred.next;
        }
        // succ is the next node of the node to be deleted
        succ = pred.next.next;
      } else {
        succ = tail;
        for (int i = 0; i < size - index - 1; i++) {
          succ = succ.prev;
        }
        // prev is the previous node of the node to be deleted
        pred = succ.prev.prev;
      }

      // Delete the node.
      pred.next = succ;
      succ.prev = pred;
      size--;
    }



  }



  /**
   * Your MyLinkedList object will be instantiated and called as such: MyLinkedList obj = new
   * MyLinkedList(); int param_1 = obj.get(index); obj.addAtHead(val); obj.addAtTail(val);
   * obj.addAtIndex(index,val); obj.deleteAtIndex(index);
   */



  // For testing.
  @SuppressWarnings("unused")
  public static void main(String[] args) {
    DesignLinkedList solution = new DesignLinkedList();

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


