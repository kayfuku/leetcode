// 
// Author: 
// Date  : December 25, 2019

package leetcode;

public class SwapNodesInPairs {
	// fields and classes here. 
	//private int count;

	public SwapNodesInPairs() {
		// Initialization here. 
		//this.count = 0;

	}


	// O(N) time, O(1) space. 
	// Author: @godayaldivya + kei
	// Date  : December 25, 2019
	public ListNode swapPairs(ListNode head) {
		// Dummy node acts as the prevNode for the head node
		// of the list and hence stores pointer to the head node.
		ListNode dummy = new ListNode(-1);
		dummy.next = head;
		ListNode curr = head;
		ListNode prev = dummy;

		while ((curr != null) && (curr.next != null)) {

			// Nodes to be swapped
			ListNode first = curr;
			ListNode second = curr.next;

			// Swap. 
			prev.next = second;
			first.next = second.next;
			second.next = first;

			// Reinitialize the curr and prev for next swap. 
			prev = first;
			curr = first.next; // jump
		}

		// Return the new list.
		return dummy.next;
	}







	// For testing. 
	public static void main(String[] args) {
		SwapNodesInPairs solution = new SwapNodesInPairs();

		// Test arguments. 
		// int num = 24;
		// int target = 2;
		// solution.getInt(num, target);



	}

}















