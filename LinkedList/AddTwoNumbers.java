//
// Author:
// Date : May 19, 2019

package leetcode;


public class AddTwoNumbers {


  // O(max(m,n)) time. Assume that m and n represents the length of l1 and l2 respectively.
  // O(max(m,n)) space.
  public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
    ListNode dummyHead = new ListNode(0);
    ListNode p = l1, q = l2, curr = dummyHead;

    int carry = 0;
    // Either of the two lists can be shorter.
    // Keep going even if either of them finishes.
    while (p != null || q != null) {
      // p or q can be null.
      int x = (p != null) ? p.val : 0;
      int y = (q != null) ? q.val : 0;
      // Since carry is from previous iteration, we need to declare
      // carry out of the loop.
      int sum = x + y + carry;
      carry = sum / 10;
      // Create a new node for that digit.
      curr.next = new ListNode(sum % 10);
      // Don't forget to move forward all the pointers and null check.
      if (p != null) {
        p = p.next;
      }
      if (q != null) {
        q = q.next;
      }
      curr = curr.next;
    }
    // The last node can carry over.
    if (carry > 0) {
      curr.next = new ListNode(carry);
    }

    return dummyHead.next;
  }


  // Review
  public ListNode addTwoNumbersR(ListNode l1, ListNode l2) {
    ListNode dummy = new ListNode(0);
    ListNode p = l1, q = l2, cur = dummy;

    int carry = 0;
    while (p != null || q != null) {
      int v1 = (p != null) ? p.val : 0;
      int v2 = (q != null) ? q.val : 0;
      int sum = v1 + v2 + carry;
      carry = sum / 10;

      cur.next = new ListNode(sum % 10);

      if (p != null) {
        p = p.next;
      }
      if (q != null) {
        q = q.next;
      }

      cur = cur.next;
    }

    if (carry != 0) {
      cur.next = new ListNode(1);
    }

    return dummy.next;
  }


  // Review
  public ListNode addTwoNumbersR2(ListNode l1, ListNode l2) {
    // corner: both null, either 1 node, => ok

    ListNode p = l1, q = l2;
    ListNode dummy = new ListNode(0);
    ListNode cur = dummy;

    int carry = 0;
    while (p != null || q != null) {
      int x = (p != null) ? p.val : 0;
      int y = (q != null) ? q.val : 0;

      int sum = x + y + carry;
      carry = sum / 10;
      ListNode node = new ListNode(sum % 10);
      cur.next = node;

      if (p != null) {
        p = p.next;
      }
      if (q != null) {
        q = q.next;
      }
      cur = cur.next;
    }

    if (carry != 0) {
      cur.next = new ListNode(1);
    }

    return dummy.next;
  }



  public static void main(String[] args) {
    Solution solution = new Solution();



  }

}


