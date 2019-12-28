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

	// 2. Iteration. ** TODO **
	// O(N) time, where N is the total number of nodes in the tree, 
	// since we visit each node exactly once.
	// O(logN) space in the best case of completely balanced tree, and 
	// O(N) space in the in the worst case of completely unbalanced tree, to keep a deque. 
	public boolean check(TreeNode p, TreeNode q) {
		// p and q are null
		if (p == null && q == null) { 
			return true;
		}
		// Either p or q is null. 
		// Not using short circuit evaluation, we can guarantee that 
		// neither p nor q is null after check(). 
		if (q == null) { 
			return false;
		}
		if (p == null) {
			return false;
		}
		if (p.val != q.val) { 
			return false;
		}

		return true;
	}

	public boolean isSameTree2(TreeNode p, TreeNode q) {
		if (p == null && q == null) { 
			return true;
		}
		if (!check(p, q)) { 
			return false;
		}

		// Init deques.
		ArrayDeque<TreeNode> deqP = new ArrayDeque<TreeNode>();
		ArrayDeque<TreeNode> deqQ = new ArrayDeque<TreeNode>();
		deqP.addLast(p);
		deqQ.addLast(q);

		while (!deqP.isEmpty()) {
			p = deqP.removeFirst();
			q = deqQ.removeFirst();

			if (!check(p, q)) return false;

			// in Java nulls are not allowed in Deque
			if (!check(p.left, q.left)) { 
				return false;
			}
			deqP.addLast(p.left);
			deqQ.addLast(q.left);

			if (!check(p.right, q.right)) { 
				return false;
			}
			deqP.addLast(p.right);
			deqQ.addLast(q.right);			
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



	}

}















