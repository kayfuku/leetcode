//
// Author:
// Date : December 25, 2019

package leetcode;

public class MergeTwoSortedLists {
  // fields and classes here.
  // private int count;

  public MergeTwoSortedLists() {
    // Initialization here.
    // this.count = 0;

  }


  // O(N + M) time, O(1) space.
  // Author: LeetCode + kei
  // Date : December 25, 2019
  public ListNode mergeTwoLists(ListNode p1, ListNode p2) {
    // Maintain an unchanging reference to node ahead of the return node.
    ListNode dummy = new ListNode(-1);
    ListNode prev = dummy;

    // Use pointer for each list.
    // p1 points to the list1 and p2 points to the list2.
    // Compare the smallest nodes in the lists.
    // If either p1 or p2 is null, then no need to compare anymore.
    while (p1 != null && p2 != null) {
      // Take the smaller one and advance the pointer of the list.
      if (p1.val <= p2.val) {
        prev.next = p1;
        p1 = p1.next;
      } else {
        prev.next = p2;
        p2 = p2.next;
      }
      prev = prev.next;
    }

    // Exactly one of p1 and p2 can be non-null at this point, so connect
    // the non-null list to the end of the merged list.
    prev.next = (p1 == null) ? p2 : p1;

    return dummy.next;
  }



  // For testing.
  public static void main(String[] args) {
    MergeTwoSortedLists solution = new MergeTwoSortedLists();

    // Test arguments.
    // int num = 24;
    // int target = 2;
    // solution.getInt(num, target);



  }

}


