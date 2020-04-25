//
// Author:
// Date : June 11, 2019

package leetcode;

public class IntersectionOfTwoLinkedLists {
  // fields here.
  // private int count;

  public IntersectionOfTwoLinkedLists() {
    // Initialization here.
    // this.count = 0;
  }

  // other classes here.

  // 1. Brute Force.
  // For each node in list A, traverse the entire list B and check if any node in list B coincides
  // with the node.
  // O(mn) time, where m is one list length, and n is the other list length.
  // O(1) space.


  // 2. Hash Table.
  // Traverse list A and store the address to each node in a hash set.
  // Then check every node in list B from the head: if the node appears in the hash set, then the
  // node is the intersection node.
  // O(m + n) time, where m is one list length, and n is the other list length.
  // O(m) or O(n) space.


  // 3. Two Pointers.
  // This solution is amazing!
  // https://leetcode.com/problems/intersection-of-two-linked-lists/solution/
  // O(m + n) time, O(1) space.
  // Author: jianchao-li + kei (AC)
  // Date : June 11, 2019, April 11, 2020
  public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
    ListNode pa = headA, pb = headB;
    // We use two pointers. One is to traverse list A first, and the other is to
    // traverse list B first. Then when a pointer reaches the end of a list,
    // it starts to traverse the other list from the head.
    // That way, they can meet at the intersection.
    while (pa != null || pb != null) {
      if (pa == pb) {
        return pa;
      }
      pa = (pa != null) ? pa.next : headB;
      pb = (pb != null) ? pb.next : headA;
      // NG! Check for just one node.
      // if (pa == pb) {
      // return pa;
      // }
    }
    // If there is no intersection, p and q get null at the same time, and it
    // returns null.
    return null;
  }


  // Review
  public ListNode getIntersectionNodeR(ListNode headA, ListNode headB) {
    ListNode pA = headA, pB = headB;
    while (pA != pB) {
      pA = (pA != null) ? pA.next : headB;
      pB = (pB != null) ? pB.next : headA;
    }

    return pA;
  }



  // For testing.
  public static void main(String[] args) {
    IntersectionOfTwoLinkedLists solution = new IntersectionOfTwoLinkedLists();

    // Test arguments.
    // int num = 24;
    // int target = 2;
    // solution.getInt(num, target);

    ListNode n1 = new ListNode(4);
    ListNode n2 = new ListNode(1);
    ListNode n3 = new ListNode(8);
    ListNode n4 = new ListNode(4);
    ListNode n5 = new ListNode(5);
    ListNode n6 = new ListNode(5);
    ListNode n7 = new ListNode(0);
    ListNode n8 = new ListNode(1);

    /**
     * Two linked lists that are intersected. 4 - 1 \ 8 - 4 - 5 / 5 - 0 - 1
     */
    ListNode listA = n1;
    n1.add(n2);
    n2.add(n3);
    n3.add(n4);
    n4.add(n5);

    ListNode listB = n6;
    n6.add(n7);
    n7.add(n8);
    n8.add(n3);

    ListNode node = solution.getIntersectionNode(listA, listB);
    System.out.println(node.val); // 8

    /**
     * Two linked lists that are not intersected. 4 - 1 - 8
     * 
     * 0 - 1
     */
    listA = n1;
    n1.add(n2);
    n2.add(n3);
    n3.next = null;

    listB = n7;
    n7.add(n8);
    n8.next = null;

    node = solution.getIntersectionNode(listA, listB);
    System.out.println((node != null) ? node.val : null); // null


  }

}


