// 
// Author: 
// Date  : June 3, 2019

package leetcode;

import java.util.ArrayDeque;

public class SameTree {
	// fields here. 
	//	private int count;

	public SameTree() {
		// Initialization here. 
		//		this.count = 0;
	}

	// other classes here. 

	// 1. Recursion. 
	// O(N) time, where N is the total number of nodes in the tree, 
	// since we visit each node exactly once.
	// O(logN) space in the best case of completely balanced tree, and 
	// O(N) space in the worst case of completely unbalanced tree, to keep a recursion stack. 
	public boolean isSameTree(TreeNode p, TreeNode q) {
		if (p == null && q == null) {
			return true;
		}

		// Avoid null pointer exception for the following.
		if (p == null || q == null) {
			// Either of them is null.  
			return false;
		}

		if (p.val != q.val) {
			return false;
		}

		return isSameTree(p.left, q.left) && 
			   isSameTree(p.right, q.right);
	}

	// 2. Iteration. 
	// O(N) time, where N is the total number of nodes in the tree, 
	// since we visit each node exactly once.
	// O(logN) space in the best case of completely balanced tree, and 
	// O(N) space in the in the worst case of completely unbalanced tree, to keep a deque. 
	public boolean isSameTree2(TreeNode p, TreeNode q) {
		if (p == null && q == null) { 
			return true;
		}
		if (!check(p, q)) { 
			return false;
		}

		// Init deques, which serve as queues. 
		ArrayDeque<TreeNode> deqP = new ArrayDeque<TreeNode>();
		ArrayDeque<TreeNode> deqQ = new ArrayDeque<TreeNode>();
		deqP.addLast(p);
		deqQ.addLast(q);

		while (!deqP.isEmpty()) {
			// Poll. 
			p = deqP.removeFirst();
			q = deqQ.removeFirst();

			if (!check(p, q)) {
				return false;
			}

			// Add child nodes.  
			// In Java, nulls are not allowed in Deque. 
			if (!check(p.left, q.left)) { 
				return false;
			}
			if (p.left != null) {
				// Neither p.left nor q.left are null. 
				deqP.addLast(p.left);
				deqQ.addLast(q.left);
			}
			if (!check(p.right, q.right)) { 
				return false;
			}
			if (p.right != null) {
				// Neither p.right nor q.right are null. 
				deqP.addLast(p.right);
				deqQ.addLast(q.right);
			}		
		}

		return true;
	}

	public boolean check(TreeNode p, TreeNode q) {
		// p and q are null. 
		if (p == null && q == null) { 
			return true;
		}
		// Either p or q is null. 
		if (p == null || q == null) { 
			return false;
		}
		if (p.val != q.val) { 
			return false;
		}

		return true;
	}




	// Review 
	public boolean isSameTreeR(TreeNode p, TreeNode q) {
		if (p == null && q == null) {
			return true;
		}


		if (p == null || q == null) {
			return false;
		}

		if (p.val != q.val) {
			return false;
		}


		return isSameTree(p.left, q.left) &&
				isSameTree(p.right, q.right);
	}




	// For testing. 
	public static void main(String[] args) {
		SameTree solution = new SameTree();

		// Test arguments. 
		//	    int num = 24;
		//	    int target = 2;
		//	    solution.getInt(num, target);

		// Binary Tree
		//   1
		//  / \
		// 2   3
		TreeNode p1 = new TreeNode(1);
		TreeNode p2 = new TreeNode(2);
		TreeNode p3 = new TreeNode(3);
		p1.right = p3;
		p1.left = p2;

		// Binary Tree
		//   1
		//  / \
		// 2   3
		TreeNode q1 = new TreeNode(1);
		TreeNode q2 = new TreeNode(2);
		TreeNode q3 = new TreeNode(3);
		q1.right = q3;
		q1.left = q2;


		TreeNode p = p1;
		TreeNode q = q1;
		System.out.println(solution.isSameTree2(p, q));



	}

}















