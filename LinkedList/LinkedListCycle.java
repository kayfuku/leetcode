//
// Author:
// Date : June 11, 2019

package leetcode;

import java.util.HashSet;
import java.util.Set;

public class LinkedListCycle {
  // fields here.
  // private int count;

  public LinkedListCycle() {
    // Initialization here.
    // this.count = 0;
  }

  // other classes here.

  // Using hash table.
  // O(N) time, O(N) space, where N is the total number of nodes in the list.
  // Author: LeetCode + kei
  // Date : June 11, 2019
  public boolean hasCycle(ListNode head) {
    Set<ListNode> set = new HashSet<>();
    ListNode node = head;
    while (node != null) {
      if (set.contains(node)) {
        return true;
      }
      set.add(node);
      node = node.next;
    }

    return false;
  }

  // Two pointers.
  // The fast pointer moves two nodes at a time while the slow pointer moves one node.
  // If the list has a cycle, then fast pointer catches up with the slow pointer at some point,
  // otherwise the fast pointer gets to the last null.
  // O(N) time, O(1) space.

  // https://leetcode.com/problems/linked-list-cycle/solution/
  // Time complexity : O(n). Let us denote n as the total number of nodes in the linked list.
  // To analyze its time complexity, we consider the following two cases separately.
  // List has no cycle:
  // The fast pointer reaches the end first and the run time depends on the list's length, which is
  // O(n).
  //
  // List has a cycle:
  // We break down the movement of the slow pointer into two steps, the non-cyclic part and the
  // cyclic part:
  // The slow pointer takes "non-cyclic length" steps to enter the cycle. At this point,
  // the fast pointer has already reached the cycle.
  // \text{Number of iterations} = \text{non-cyclic length} = Number of iterations=non-cyclic
  // length=N
  //
  // Both pointers are now in the cycle. Consider two runners running in a cycle -
  // the fast runner moves 2 steps while the slow runner moves 1 steps at a time.
  // Since the speed difference is 1, it takes \dfrac{\text{distance between the 2
  // runners}}{\text{difference of speed}}
  // difference of speed distance between the 2 runners loops for the fast runner to catch up with
  // the slow runner.
  // As the distance is at most "\text{cyclic length K}cyclic length K" and the speed difference is
  // 1,
  // we conclude that \text{Number of iterations} = \text{almost}Number of iterations=almost
  // "\text{cyclic length K}cyclic length K".
  //
  // Therefore, the worst case time complexity is O(N+K), which is O(n).

  // (Alternative solution?)
  // Let X be the length from head to the start point of cycle, and
  // let Y be the length of the cycle. We know slow moves t, while fast moves 2t. They meet at K
  // where
  // is the length from the start point of the cycle.
  // Then we have :
  // t = X + nY + K
  // 2t = X + mY + K
  //
  // Then we get, t = (n - m)Y
  // The time complexity depends on the cycle length. The cycle length is at most N, where N is the
  // total number of nodes, which means O(N) time complexity.

  // Space complexity : O(1). We only use two nodes (slow and fast) so the space complexity is O(1).
  public boolean hasCycle2(ListNode head) {
    // corner. (In fact, no need to put extra corner case handling.)
    // if (head == null || head.next == null) {
    // return false;
    // }
    // Assert that two or more nodes w/wo cycle, or one node w/ cycle.

    ListNode slow = head, fast = head;

    // Continuing condition for fast pointer that moves two nodes at a time.
    // Stop moving if the 'fast' gets to null or 'fast.next' is null.
    while (fast != null && fast.next != null) {
      fast = fast.next.next;
      slow = slow.next;
      // Fast pointer catches up with the slow pointer.
      if (slow == fast) {
        return true;
      }
    }

    return false;
  }

  // Review. Accepted.
  public boolean hasCycleR(ListNode head) {
    // corner. head: null, 1 w/ cyc, 1 w/o cyc, 2 w/ cyc, 2 w/o cyc => ok.

    ListNode fast = head, slow = head;
    while (fast != null && fast.next != null) {
      fast = fast.next.next;
      slow = slow.next;
      if (fast == slow) {
        return true;
      }
    }

    return false;
  }


  // Review
  public boolean hasCycleR2(ListNode head) {
    Set<ListNode> set = new HashSet<>();
    ListNode node = head;
    while (node != null) {
      if (set.contains(node)) {
        return true;
      } else {
        set.add(node);
      }

      node = node.next;
    }

    return false;
  }

  // Review
  public boolean hasCycleR22(ListNode head) {
    ListNode slow = head, fast = head;
    while (fast != null && fast.next != null) {
      fast = fast.next.next;
      slow = slow.next;
      if (slow == fast) {
        return true;
      }
    }

    return false;
  }



  // For testing.
  public static void main(String[] args) {
    LinkedListCycle solution = new LinkedListCycle();

    // Test arguments.
    // int num = 24;
    // int target = 2;
    // solution.getInt(num, target);



  }

}


