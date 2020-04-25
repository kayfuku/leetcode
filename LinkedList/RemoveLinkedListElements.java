//
// Author:
// Date : April 14, 2020

package leetcode;

public class RemoveLinkedListElements {
  // fields and classes here.
  // private int count;

  public RemoveLinkedListElements() {
    // Initialization here.
    // this.count = 0;

  }


  // Author: @liaison and @andvary + kei
  // Date : April 14, 2020
  public ListNode removeElements(ListNode head, int val) {
    ListNode dummy = new ListNode(0);
    // Don't forget this! Also, not dummy = head;
    dummy.next = head;
    ListNode prev = dummy, curr = head;

    while (curr != null) {
      if (curr.val == val) {
        // curr needs to be deleted.
        // The previous node is needed.
        prev.next = curr.next;
        // prev still points to previous node of the next node
        // so prev does not need to move.
      } else {
        // curr does not need to be deleted.
        // The previous node is not needed. Move forward.
        prev = curr;
      }

      curr = curr.next;
    }

    return dummy.next;
  }


  // NG!
  // Author: kei (WA)
  // Date : April 14, 2020
  public ListNode removeElementsNG(ListNode head, int val) {
    ListNode dummy = new ListNode(0);
    // Don't forget this! Also, not dummy = head;
    dummy.next = head;
    ListNode prev = dummy, curr = head;

    while (curr != null) {
      if (curr.val == val) {
        prev.next = curr.next;
      }

      prev = curr;
      curr = curr.next;
    }

    return dummy.next;
  }



  // For testing.
  @SuppressWarnings("unused")
  public static void main(String[] args) {
    RemoveLinkedListElements solution = new RemoveLinkedListElements();

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


