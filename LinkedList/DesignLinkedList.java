//
// Author:
// Date : April 8, 2020

package leetcode;

public class DesignLinkedList {
  // fields and classes here.
  // private int count;

  // 1. Singly Linked List
  // Author: @liaison and @andvary + kei
  // Date : April 8, 2020, May 21, 2020
  public class ListNode {
    int val;
    ListNode next;

    ListNode(int x) {
      val = x;
    }
  }

  class MyLinkedList {
    int size;
    ListNode dummyHead; // sentinel node as pseudo-head

    public MyLinkedList() {
      // Dummy head does not count toward the size.
      size = 0;
      dummyHead = new ListNode(0);
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

      ListNode curr = dummyHead;
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
      ListNode pred = dummyHead;
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
      ListNode pred = dummyHead;
      for (int i = 0; i < index; ++i) {
        pred = pred.next;
      }

      // Delete the node.
      pred.next = pred.next.next;
      size--;
    }

    public void printList() {
      ListNode curr = dummyHead;
      for (int i = 0; i < size; i++) {
        curr = curr.next;
        System.out.println(curr.val);
      }
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
    DoublyListNode dummyHead, dummyTail;

    public MyDoublyLinkedList() {
      size = 0;
      dummyHead = new DoublyListNode(0);
      dummyTail = new DoublyListNode(0);
      // Don't forget this!
      dummyHead.next = dummyTail;
      dummyTail.prev = dummyHead;
    }

    /**
     * Get the value of the index-th node in the linked list. If the index is invalid, return -1.
     */
    public int get(int index) {
      // if index is invalid
      if (index < 0 || index >= size) {
        return -1;
      }

      // Choose the fastest way: to move from the head
      // or to move from the tail.
      DoublyListNode curr = dummyHead;
      // index + 1 is the distance between head and index.
      // size - index is the distance between tail and index.
      if (index + 1 < size - index) {
        for (int i = 0; i < index + 1; i++) {
          curr = curr.next;
        }
      } else {
        // dummyTail is closer.
        curr = dummyTail;
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
      DoublyListNode pred = dummyHead, succ = dummyHead.next;

      size++;
      DoublyListNode newNode = new DoublyListNode(val);
      newNode.prev = pred;
      newNode.next = succ;
      pred.next = newNode;
      succ.prev = newNode;
    }

    /** Append a node of value val to the last element of the linked list. */
    public void addAtTail(int val) {
      DoublyListNode pred = dummyTail.prev, succ = dummyTail;

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

      // Find predecessor and successor of the node to be added
      // succ should be at 'index' before insertion.
      DoublyListNode pred, succ;
      // Use bidirectional search to perform faster.
      // index is the distance between dummyHead and prev.
      // size - index is the distance between dummyTail and index (succ).
      if (index < size - index) {
        // dummyHead is closer to the index.
        pred = dummyHead;
        // Move pred 'index' nodes forward.
        for (int i = 0; i < index; i++) {
          pred = pred.next;
        }
        // succ is the next node.
        succ = pred.next;

      } else {
        // dummyTail is closer to the index.
        succ = dummyTail;
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


    // Delete the index-th node in the linked list, if the index is valid.
    // Need predecessor and successor of the node to be deleted.
    public void deleteAtIndex(int index) {
      // if the index is invalid, do nothing
      if (index < 0 || index >= size) {
        return;
      }

      // Find predecessor and successor of the node to be deleted.
      DoublyListNode pred, succ;
      // Use bidirectional search to perform faster.
      // index is the distance between dummyHead and prev.
      // size - index is the distance between dummyTail and index (succ).
      if (index < size - index) {
        // dummyHead is closer to the index.
        pred = dummyHead;
        for (int i = 0; i < index; i++) {
          pred = pred.next;
        }
        // succ is the next node of the node to be deleted
        succ = pred.next.next;
      } else {
        succ = dummyTail;
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

    MyLinkedList list = solution.new MyLinkedList();
    list.addAtTail(0);
    list.addAtTail(1);
    list.addAtTail(2);
    // list.printList();
    // System.out.println(list.get(2));
    list.addAtHead(3);
    list.printList();
    list.deleteAtIndex(2);
    list.printList();



  }



  public void dummyMethod() {



  }



  public void dummyMethod2() {



  }



}


