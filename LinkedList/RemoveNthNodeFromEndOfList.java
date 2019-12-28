// 
// Author: 
// Date  : May 25, 2019

package leetcode;


public class RemoveNthNodeFromEndOfList {

	// Some fields here. 
	//	private int count;

	public RemoveNthNodeFromEndOfList() {
		// Initialization here. 
		//		count = 0;
	}

	// Main logic here. Copy the signature of the method in LeetCode. 
	public ListNode removeNthFromEnd(ListNode head, int n) {
		if (head == null) {
			return null;
		}
		// Add dummy node to the head node to simplify some corner cases. 
		ListNode dummy = new ListNode(0);
		dummy.next = head;
		// In the case where deleting the head node, p should point to dummy first. 
		ListNode p = dummy;
		ListNode q = dummy;
		// Advance q by n + 1 steps so that p will point to the previous node of the node to be deleted 
		// after q arrives at the end. D-1-2-3-4-5-null  n:2
		for (int i = 1; i <= n + 1; i++) {
			q = q.next;
		}

		while (q != null) {
			p = p.next;
			q = q.next;
		}
		// Delete the Nth node. 
		p.next = p.next.next;

		return dummy.next;
	}


	// Review 
	public ListNode removeNthFromEndR(ListNode head, int n) {
		// corner
		if (head == null) {
			return null;
		}

		ListNode dummy = new ListNode(0);
		dummy.next = head;
		ListNode p = dummy, q = dummy;

		for (int i = 0; i < n; i++) {
			q = q.next;
		}

		while (q.next != null) {
			p = p.next;
			q = q.next;
		}

		p.next = p.next.next;

		return dummy.next;
	}



	// For testing. 
	public static void main(String[] args) {
		RemoveNthNodeFromEndOfList solution = new RemoveNthNodeFromEndOfList();

		// Test arguments. 
		//	    int num = 24;
		//	    int target = 2;
		//	    solution.getInt(num, target);



	}

}



















