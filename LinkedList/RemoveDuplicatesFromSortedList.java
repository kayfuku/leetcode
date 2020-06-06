//
// Author:
// Date : May 30, 2019

package leetcode;


public class RemoveDuplicatesFromSortedList {

  // fields here.
  // private int count;


  public RemoveDuplicatesFromSortedList() {
    // Initialization here.
    // count = 0;
  }

  // other classes here.

  // 1 One pointer
  // O(N) time, O(1) space.
  public ListNode deleteDuplicates(ListNode head) {
    if (head == null || head.next == null) {
      return head;
    }
    // Now the list has two or more nodes.
    ListNode cur = head;
    // Check the dups to see the current node and next node.
    // Once the pointer gets to the last node, we don't need to process anymore.
    // That's why cur.next != null.
    while (cur.next != null) {
      // Check the next node, and if it's the same, then delete it.
      if (cur.val == cur.next.val) {
        // To delete a node in a list, I just need a pointer before the node.
        cur.next = cur.next.next;
        // In this case, no need to move forward the pointer.
      } else {
        cur = cur.next;
      }
    }

    return head;
  }

  // 2 Two pointers
  // O(N) time, O(1) space.
  public ListNode deleteDuplicatesKei(ListNode head) {
    if (head == null || head.next == null) {
      return head;
    }

    ListNode p = head, q = head.next;

    while (q != null) {
      if (p.val == q.val) {
        // Delete the duplicate.
        p.next = q.next;
      } else {
        p = p.next;
      }
      q = q.next;
    }

    return head;
  }

  // Review. Accepted.
  public ListNode deleteDuplicatesR(ListNode head) {
    // corner
    if (head == null) {
      return null;
    }

    ListNode cur = head;
    // I'm checking cur.next.next, so I should not go in the loop
    // when the pointer refers to the last node.
    while (cur.next != null) {
      if (cur.val == cur.next.val) {
        // To delete a node in a list, I just need a pointer before the node.
        cur.next = cur.next.next;
        // when deleting a node, the cur should not move to the next
        // because we don't know how many duplicates there are.
      } else {
        cur = cur.next;
      }
    }

    return head;
  }


  // Review
  public ListNode deleteDuplicatesR2(ListNode head) {
    ListNode cur = head;
    while (cur != null && cur.next != null) {
      if (cur.val == cur.next.val) {
        cur.next = cur.next.next;
      } else {
        cur = cur.next;
      }
    }

    return head;
  }



  // For testing.
  public static void main(String[] args) {
    RemoveDuplicatesFromSortedList solution = new RemoveDuplicatesFromSortedList();

    // Test arguments.
    // int num = 24;
    // int target = 2;
    // solution.getInt(num, target);



  }

}


