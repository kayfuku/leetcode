//
// Author:
// Date : April 15, 2020

package leetcode;

public class OddEvenLinkedList {
  // fields and classes here.
  // private int count;

  public OddEvenLinkedList() {
    // Initialization here.
    // this.count = 0;

  }



  // It is good to traverse the list by two pointers, odd and even.
  // The odd is a tail of the odd list. The even is a tail of the even list.
  // So we can easily append the even list at the end of the odd list.
  //
  // Author: LeetCode + kei (AC)
  // Date : April 15, 2020
  public ListNode oddEvenList(ListNode head) {
    if (head == null) {
      return null;
    }
    ListNode odd = head, even = head.next, evenHead = even;
    while (even != null && even.next != null) {
      odd.next = even.next;
      odd = odd.next;
      even.next = odd.next;
      even = even.next;
    }
    odd.next = evenHead;
    return head;
  }


  // Author: kei, the first solution (AC)
  // Date : April 15, 2020
  public ListNode oddEvenList2(ListNode head) {
    if (head == null || head.next == null || head.next.next == null) {
      return head;
    }
    // The list has more than two nodes.
    ListNode prev = head, curr = head.next;
    ListNode headEven = curr;

    int idx = 2;
    while (curr != null) {
      // Skip current node.
      prev.next = curr.next;
      if (prev.next == null) {
        if (idx % 2 == 1) {
          // curr is odd node.
          // Append the even list to the end of odd list.
          curr.next = headEven;
        } else {
          // curr is even node.
          // Append the even list to the end of odd list.
          prev.next = headEven;
        }
        break;
      }

      prev = curr;
      curr = curr.next;
      idx++;
    }

    return head;
  }



  // For testing.
  @SuppressWarnings("unused")
  public static void main(String[] args) {
    OddEvenLinkedList solution = new OddEvenLinkedList();

    // Test arguments.
    // int num = 24;
    // int target = 2;
    // solution.getInt(num, target);
    ListNode n1 = new ListNode(1);
    ListNode n2 = new ListNode(2);
    ListNode n3 = new ListNode(3);
    ListNode n4 = new ListNode(4);
    ListNode n5 = new ListNode(5);

    n1.next = n2;
    n2.next = n3;
    n3.next = n4;
    n4.next = n5;

    solution.oddEvenList(n1);

    ListNode n = n1;
    while (n != null) {
      System.out.println(n.val);
      n = n.next;
    }



  }



  public void dummyMethod() {



  }



  public void dummyMethod2() {



  }



}


