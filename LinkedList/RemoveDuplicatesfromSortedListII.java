//
// Author:
// Date : June 19, 2019

package leetcode;


public class RemoveDuplicatesfromSortedListII {
  // fields here.
  // private int count;

  public RemoveDuplicatesfromSortedListII() {
    // Initialization here.
    // this.count = 0;
  }


  // Two pointers
  // To delete a node in a linked list, I need pointer right before the node.
  // I don't know how many dups in the list, so I use another pointer to find
  // the last node of the duplicate nodes.
  //
  // O(N) time, O(1) space.
  // https://leetcode.com/problems/remove-duplicates-from-sorted-list-ii/discuss/28364/Java-simple-and-clean-code-with-comment
  // Author: jinwu + kei (AC)
  // Date : July 9, 2019
  public ListNode deleteDuplicates(ListNode head) {
    if (head == null || head.next == null) {
      return head;
    }
    // corner: null, 1, only dups, ends with dups,
    // no dups, starts with dups => ok.

    // Use two pointers.
    // 'slow' - track the node before the duplicate nodes,
    // 'fast' - to find the last node of dups.
    ListNode dummy = new ListNode(0);
    dummy.next = head;

    // Basically, 'slow' is one node behind the 'fast'.
    ListNode fast = head, slow = dummy;

    // We need to process the last node in the list.
    // So, fast != null, not fast.next != null.
    // And if the list ends with dups, then fast will get null
    // because after deleting, fast gets the next node of the dups.
    while (fast != null) {
      // Check the dups to see the fast node and next node.
      // When there are dups, move 'fast' until it
      // finds the last node of the dups.
      // When 'fast' reaches the last node in the list,
      // fast.next is null, so we add fast.next != null to avoid
      // fast.next.val causes null pointer exception.
      while (fast.next != null && fast.val == fast.next.val) {
        fast = fast.next;
      }

      // Check the existence of dups.
      // Don't forget to check what if the 'fast' reaches the last node in the list.
      if (slow.next != fast) {
        // Duplicates detected because 'fast' made extra moves.
        // Delete the dups. If the 'fast' is the last node, then it's null,
        // which is fine.
        slow.next = fast.next;
        // Reset the 'fast' position.
        fast = slow.next;
      } else {
        // No dup.
        // Move forward both pointers.
        slow = slow.next;
        fast = fast.next;
      }
    }

    return dummy.next;
  }



  // Accepted.
  // O(N) time, O(1) space.
  // Author: kei
  // Date : June 19, 2019
  public ListNode deleteDuplicatesKei(ListNode head) {
    if (head == null || head.next == null) {
      return head;
    }
    ListNode dummy = new ListNode(0);
    dummy.next = head;
    ListNode p = dummy, q = head.next;
    while (q != null) {
      if (q.val == p.next.val) {
        // Find the different value node.
        // Be careful about null pointer exception.
        while (q != null && q.val == p.next.val) {
          q = q.next;
        }
        // Remove all of the duplicate nodes.
        p.next = q;
        // NG!
        // p = q;
        if (q != null) {
          q = q.next;
        }
      } else {
        p = p.next;
        q = q.next;
      }
    }

    return dummy.next;
  }


  // Review
  public ListNode deleteDuplicatesR(ListNode head) {
    ListNode dummy = new ListNode(0);
    dummy.next = head;

    ListNode slow = dummy, fast = head;

    while (fast != null) {
      while (fast.next != null && fast.val == fast.next.val) {
        fast = fast.next;
      }
      if (slow.next != fast) {
        slow.next = fast.next;
        fast = slow.next;
      } else {
        slow = slow.next;
        fast = fast.next;
      }
    }

    return dummy.next;
  }



  // other classes here.


  // For testing.
  public static void main(String[] args) {
    RemoveDuplicatesfromSortedListII solution = new RemoveDuplicatesfromSortedListII();

    // Test arguments.
    // int num = 24;
    // int target = 2;
    // solution.getInt(num, target);

    // Linked List.
    MyLinkedList list = new MyLinkedList();
    list.add(1);
    list.add(2);
    list.add(3);
    list.add(3);
    list.add(4);
    list.add(4);
    list.add(5);
    System.out.println(list.toString()); // [ 1 2 3 3 4 4 5 ]

    ListNode node = solution.deleteDuplicatesKei(list.head);
    while (node != null) {
      System.out.print(node.val);
      node = node.next;
    }
    // 125


  }

}


