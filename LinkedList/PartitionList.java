// 
// Author: 
// Date  : May 30, 2019

package leetcode;

public class PartitionList {

	// fields here. 
	//	private int count;

	public PartitionList() {
		// Initialization here. 
		//		count = 0;
	}

	// other classes here.

	// O(N) time, O(1) space. 
	public ListNode partition(ListNode head, int x) {
		// Create two lists. One is for elements less than x, and 
		// the other is for elements greater than or equal to x. 
		ListNode headBefore = new ListNode(0);
		ListNode currBefore = headBefore;
		ListNode headAfter = new ListNode(0);
		ListNode currAfter = headAfter;
		ListNode curr = head;

		// TODO corner cases. 
		// It turns out that nothing needed here because 
		// if head is null or has just one node, the main process 
		// takes care of these cases. 

		while (curr != null) {
			if (curr.val < x) {
				currBefore.next = curr;
				currBefore = currBefore.next;
			} else {
				currAfter.next = curr;
				currAfter = currAfter.next;
			}
			curr = curr.next;    		
		}
		// Don't forget this! Imagine while the nodes move. 
		currAfter.next = null;

		// Combine the two lists. 
		currBefore.next = headAfter.next;

		return headBefore.next;

		// No need this. 
		//    	head = beforeHead.next;
		//    	return head;
	}

	// Review 
	public ListNode partitionR(ListNode head, int x) {
		// corner. No need here. 

		ListNode hS = new ListNode(0);
		ListNode cS = hS;
		ListNode hB = new ListNode(0);
		ListNode cB = hB;
		ListNode cur = head;

		while (cur != null) {
			if (cur.val < x) {
				cS.next = cur;
				cS = cS.next;
			} else {
				cB.next = cur;
				cB = cB.next;
				cur = cur.next;
			}
		}

		cB.next = null;
		cS.next = hB.next;
		return hS.next;
	}

	// For testing. 
	public static void main(String[] args) {
		PartitionList solution = new PartitionList();

		// Test arguments. 
		//	    int num = 24;
		//	    int target = 2;
		//	    solution.getInt(num, target);



	}

}















