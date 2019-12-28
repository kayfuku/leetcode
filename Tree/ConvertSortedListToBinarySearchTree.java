// 
// Author: 
// Date  : June 3, 2019

package leetcode;

import java.util.ArrayList;
import java.util.List;

public class ConvertSortedListToBinarySearchTree {
	// fields here. 
	//	private int count;

	public ConvertSortedListToBinarySearchTree() {
		// Initialization here. 
		//		this.count = 0;

		values = new ArrayList<>();
	}

	// other classes here. 


	// The middle element of the given list would form the root of the binary search tree. 
	// All the elements to the left of the middle element would form the left subtree recursively. 
	// Similarly, all the elements to the right of the middle element will form the right subtree 
	// of the binary search tree. This would ensure the height balance required because 
	// the size of the left and right subarrays never differ by more than one. 


	// 1. Recursion. 
	private ListNode getMiddleNode(ListNode head) {		
		ListNode slower = head, faster = head;
		// For cutting the list. 
		ListNode prev = null;

		// faster moves two nodes at a time. 
		// Continuing condition for faster pointer that moves two nodes at a time. 
		while (faster != null && faster.next != null) {
			prev = slower;
			slower = slower.next;
			faster = faster.next.next;
		}

		// Cut the link from left sublist. 
		if (prev != null) {
			prev.next = null;
		}

		return slower;
	}
	// O(NlogN) time, O(N) time for each of logN recursion stacks. 
	// O(logN) space. 
	public TreeNode sortedListToBST(ListNode head) {
		if (head == null) {
			return null;
		}

		// Find mid node. O(N) time. 
		ListNode mid = getMiddleNode(head);
		TreeNode node = new TreeNode(mid.val);

		// Don't forget the Base case here. Just one node. 
		// No need to recurse any further. 
		if (mid == head) {
			// There is neither left subtree nor right subtree. 
			return node;
		}

		// Recurse left sublist. 
		node.left = sortedListToBST(head);

		// Recurse right sublist. 
		node.right = sortedListToBST(mid.next);

		return node;
	}


	// 2. Recursion + Conversion to Array. 
	// Time Complexity O(N): The time complexity comes down to just O(N) now since 
	// we convert the linked list to an array initially and then we convert the array 
	// into a BST. Accessing the middle element now takes O(1) time and hence 
	// the time complexity comes down.
	// Space Complexity O(N): Since we used extra space to bring down the time complexity, 
	// the space complexity now goes up to O(N) as opposed to just O(logN) 
	// in the previous solution. This is due to the array we construct initially.

	// Initialized in the constructor. 
	// Use arraylist because you don't know the input list size. 
	List<Integer> values; 

	// O(N) time. 
	private void convertToArray(ListNode node) {
		while (node != null) {
			values.add(node.val);
			node = node.next;
		}
	}

	public TreeNode sortedListToBST2(ListNode head) {
		if (head == null) {
			return null;
		}

		convertToArray(head);

		return sortedListToBST2(0, values.size() - 1);    	
	}
	// O(N) time, since we visit every node. 
	private TreeNode sortedListToBST2(int left, int right) {
		if (left > right) {
			return null;
		}

		int mid = (left + right) / 2;

		TreeNode node = new TreeNode(values.get(mid));

		// It could be accepted without this. 
		if (left == right) {
			return node;
		}

		node.left = sortedListToBST2(left, mid - 1);
		node.right = sortedListToBST2(mid + 1, right);

		return node;
	}


	// 3. In-order Simulation. **Leave it for later**




	// Review
	// Recursive. 
	public TreeNode sortedListToBSTRecurR(ListNode head) {
		// corner 
		if (head == null) {
			return null;
		}

		// get the mid elem
		ListNode mid = getMid(head);

		// create root node 
		TreeNode node = new TreeNode(mid.val);

		if (mid == head) {
			return node;
		}

		// left
		node.left = sortedListToBSTRecurR(head);

		// right 	
		node.right = sortedListToBSTRecurR(mid.next);

		return node;
	}
	private ListNode getMid(ListNode head) {
		ListNode slow = head, fast = head;
		ListNode prev = null;

		while (fast != null && fast.next != null) {
			prev = slow;
			slow = slow.next;
			fast = fast.next.next;
		}

		if (prev != null) {
			prev.next = null;
		}

		return slow;
	}

	// Review 
	// Iterative. Accepted. 
	public TreeNode sortedListToBSTRecurR2(ListNode head) {
		// corner. 

		// convert list to array 
		List<Integer> values = new ArrayList<>();

		convertToArray(head, values);

		// recurse
		return sortedListToBSTRecurR2(values, 0, values.size() - 1);
	}
	private void convertToArray(ListNode head, List<Integer> values) {
		ListNode cur = head;
		while (cur != null) {
			values.add(cur.val);
			cur = cur.next;
		}
	}
	private TreeNode sortedListToBSTRecurR2(List<Integer> values, int left, int right) {
		if (left > right) {
			return null;
		}

		int mid = left + (right - left) / 2;
		TreeNode node = new TreeNode(values.get(mid));

		node.left = sortedListToBSTRecurR2(values, left, mid - 1);
		node.right = sortedListToBSTRecurR2(values, mid + 1, right);

		return node;	
	}




	// For testing. 
	public static void main(String[] args) {
		ConvertSortedListToBinarySearchTree solution = new ConvertSortedListToBinarySearchTree();

		// Test arguments. 
		//	    int num = 24;
		//	    int target = 2;
		//	    solution.getInt(num, target);


		// Linked List. 
		MyLinkedList list = new MyLinkedList();
		list.add(7);
		list.add(9);
		list.add(2);
		list.add(10);
		list.add(1);
		list.add(8);
		list.add(6);
		System.out.println(list.toString()); // [ 7 9 2 10 1 8 6 ]

		TreeNode ret = solution.sortedListToBST2(list.head);



	}

}















