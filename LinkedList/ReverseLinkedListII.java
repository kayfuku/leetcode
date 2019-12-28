// 
// Author: 
// Date  : May 30, 2019

package leetcode;

public class ReverseLinkedListII {
	// fields here. 
	//	private int count;

	public ReverseLinkedListII() {
		// Initialization here. 
		//		this.count = 0;
	}

	// other classes here. 


	// 1. Iterative Link Reversal. 
	// O(N) time, O(1) space. 
	public ListNode reverseBetween2(ListNode head, int m, int n) {

		// TODO done: head: null, 1, 2 node. 
		if (head == null) {
			return null;
		}

		ListNode prev = null;
		ListNode cur = head;

		// Move prev and cur forward at the same rate until cur gets to m. 
		// m > 1 because cur needs to get to the node m. 
		while (m > 1) {
			prev = cur;
			cur = cur.next;
			n--; // Don't forget this!
			m--;
		}
		// cur got to m. 

		// To connect the reversed sublist afterwards. 
		ListNode con = prev, tail = cur;

		// Reverse nodes. 
		ListNode third = null;
		// n > 0 because it is good that prev gets to node n and 
		// cur gets to the next node. 
		while (n > 0) {
			third = cur.next;
			cur.next = prev;
			prev = cur;
			cur = third;
			n--;
		}

		// Think about the corner cases where 
		// only 1 node, 2 nodes, m == n, m == n == 1, etc. 
		// Don't forget 2 nodes list with m == n == 1 case!
		if (con != null) {
			con.next = prev;
		} else {
			// con: null, m: 1
			head = prev;
		}
		tail.next = cur;

		return head;    	
	}


	// 2. Recursion. ** Leave this later **
	// O(N) time, O(N) space. 
	
	// Object level variables since we need the changes
	// to persist across recursive calls and Java is pass by value.
	private boolean stop;
	private ListNode left;

	public void recurseAndReverse(ListNode right, int m, int n) {

		// base case. Don't proceed any further
		if (n == 0) {
			return;
		}

		// Keep moving left pointer to the right until we reach the proper node
		// from where the reversal is to start.
		if (m > 1) {
			this.left = this.left.next;
		}

		// Recurse with m and n reduced.
		// Let's move 'right' according to 'n' like this. 
		this.recurseAndReverse(right.next, m - 1, n - 1);


		// Backtracking. 

		// In case both the pointers cross each other or become equal, we
		// stop i.e. don't swap data any further. We are done reversing at this
		// point.
		if (this.left == right || right.next == this.left) {
			this.stop = true;            
		}

		// Until the boolean stop is false, swap data between the two pointers
		if (!this.stop) {
			int t = this.left.val;
			this.left.val = right.val;
			right.val = t;

			// Move left one step to the right.
			// The right pointer moves one step back via backtracking.
			this.left = this.left.next;
		}
	}

	public ListNode reverseBetween(ListNode head, int m, int n) {
		this.left = head;
		this.stop = false;
		this.recurseAndReverse(head, m, n);
		return head;
	}




	// For testing. 
	public static void main(String[] args) {
		ReverseLinkedListII solution = new ReverseLinkedListII();

		// Test arguments. 
		//	    int num = 24;
		//	    int target = 2;
		//	    solution.getInt(num, target);

		MyLinkedList list = new MyLinkedList();
		list.add(7);
		list.add(9);
		list.add(2);
		list.add(10);
		list.add(1);
		list.add(8);
		list.add(6);
		System.out.println(list.toString()); // [ 7 9 2 10 1 8 6 ]

		solution.reverseBetween(list.head, 3, 6);
		System.out.println(list.toString()); // [ 7 9 8 1 10 2 6 ]


		MyLinkedList list2 = new MyLinkedList();
		list2.add(1);
		list2.add(2);
		System.out.println(list2.toString()); // [ 1 2 ]

		solution.reverseBetween(list2.head, 1, 1);
		System.out.println(list2.toString()); // [ 1 2 ]




	}

}















