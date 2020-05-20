//
// Author:
// Date : April 15, 2020

package leetcode;

import java.util.ArrayList;
import java.util.List;

public class PalindromeLinkedList {
  // fields and classes here.
  // private int count;

  public PalindromeLinkedList() {
    // Initialization here.
    // this.count = 0;

  }


  // Approach 1: Copy the list into array and use two pointers.
  // O(N) time, O(N) space
  // Author: LeetCode + kei
  // Date : May 20, 2020
  public boolean isPalindrome1(ListNode head) {
    List<Integer> vals = new ArrayList<>();

    // Convert LinkedList into ArrayList.
    ListNode curr = head;
    while (curr != null) {
      vals.add(curr.val);
      curr = curr.next;
    }

    // Use two-pointer technique to check for palindrome.
    int front = 0;
    int back = vals.size() - 1;
    while (front < back) {
      // Note that we must use ! .equals instead of !=
      // because we are comparing Integer, not int. <= Well, auto-boxing might work.
      if (!vals.get(front).equals(vals.get(back))) {
        return false;
      }
      front++;
      back--;
    }
    return true;
  }


  // Approach 3: Reverse Second Half In-place
  // O(N) time, O(1) space
  // Author: LeetCode + kei
  // Date : May 20, 2020
  public boolean isPalindrome(ListNode head) {
    if (head == null) {
      return true;
    }

    // Find the end of first half and reverse second half.
    ListNode firstHalfEnd = getEndOfFirstHalf(head);
    ListNode secondHalfStart = reverseList(firstHalfEnd.next);

    // Check whether or not there is a palindrome.
    ListNode p1 = head;
    ListNode p2 = secondHalfStart;
    boolean result = true;
    while (p2 != null) {
      if (p1.val != p2.val) {
        // Not return immediately because we need to restore the original list
        // before the return.
        result = false;
      }
      p1 = p1.next;
      p2 = p2.next;
    }

    // While you don't need to restore the list to pass the test cases,
    // it is still good programming practice because the function could be
    // a part of a bigger program that doesn't want the Linked List broken.
    firstHalfEnd.next = reverseList(secondHalfStart);

    return result;
  }



  private ListNode reverseList(ListNode head) {
    ListNode prev = null;
    ListNode curr = head;
    while (curr != null) {
      ListNode temp = curr.next;
      curr.next = prev;
      prev = curr;
      curr = temp;
    }

    return prev;
  }


  private ListNode getEndOfFirstHalf(ListNode head) {
    ListNode fast = head;
    ListNode slow = head;
    while (fast.next != null && fast.next.next != null) {
      fast = fast.next.next;
      slow = slow.next;
    }

    return slow;
  }


  // For testing.
  @SuppressWarnings("unused")
  public static void main(String[] args) {
    PalindromeLinkedList solution = new PalindromeLinkedList();

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


