// 
// Author: 
// Date  : June 3, 2019

package leetcode;

import java.util.LinkedList;
import java.util.Queue;

public class SymmetricTree {
	// fields here. 
	//	private int count;

	public SymmetricTree() {
		// Initialization here. 
		//		this.count = 0;
	}

	// other classes here. 

	// 1. Recursive. 
	// O(N) time, where N is the total number of nodes in the tree, 
	// since we visit each node. 
	// O(logN) space to keep a recursion stack if the tree is balanced. 
	// O(N) space if the tree is not balanced. 
	public boolean isSymmetric(TreeNode root) {
		if (root == null) {
			return true;
		}        
		// A tree is symmetric if the left subtree is a mirror reflection 
		// of the right subtree.
		return isMirror(root.left, root.right);
	}
	private boolean isMirror(TreeNode t1, TreeNode t2) {
		// Let's start with this. 
		if (t1 == null && t2 == null) {
			return true;
		}
		if (t1 == null || t2 == null) {
			// Either t1 or t2 is null. 
			return false;
		}
		if (t1.val != t2.val) {
			return false;
		}
		// Now that t1.val == t2.val, we have to check the subtrees. 
		
		// Traverse left subtree first for t1 and right subtree first for t2. 
		return isMirror(t1.left, t2.right) && 
				isMirror(t1.right, t2.left);
	}

	// 2. Iterative. 
	// O(N) time, since we visit each node. 
	// O(N) space for queue. 
	public boolean isSymmetric2(TreeNode root) {
		if (root == null) {
			return true;
		}
		// BFS. 
		LinkedList<TreeNode> queue = new LinkedList<>();
		queue.add(root.left);
		queue.add(root.right);

		TreeNode t1, t2;
		while (!queue.isEmpty()) {
			// Poll two nodes at the same time to compare them. 
			t1 = queue.poll();
			t2 = queue.poll();

			if (t1 == null && t2 == null) {
				// Not return true. Just no need to add children nodes. 
				// You still need ot check other branches. 
				continue;
			}
			if (t1 == null || t2 == null) {
				// Only one of the two nodes is null. 
				return false;
			}
			if (t1.val != t2.val) {
				return false;
			}

			// Add all children nodes to the queue. 
			// The two nodes that should be compared with each other 
			// must be inserted in a row. 
			// Note that you need to even enqueue nulls to check the 
			// tree structures. 
			queue.add(t1.left);
			queue.add(t2.right);
			// Next pair to be compared. 
			queue.add(t1.right);
			queue.add(t2.left);
		}

		return true;
	}


	// Review
	// Recursive. 
	public boolean isSymmetricR(TreeNode root) {
		// corner
		if (root == null) {
			return true;
		}

		return isMirrorR(root.left, root.right);
	}
	private boolean isMirrorR(TreeNode t1, TreeNode t2) {
		if (t1 == null && t2 == null) {
			return true;
		}

		if (t1 == null || t2 == null) {
			return false;
		}
		if (t1.val != t2.val) {
			return false;
		}

		boolean bO = isMirrorR(t1.left, t2.right);
		boolean bI = isMirrorR(t1.right, t2.left);

		return bO && bI;
	}

	// Review
	// Iterative. 
	public boolean isSymmetricIterR(TreeNode root) {
		// corner
		if (root == null) {
			return true;
		}

		Queue<TreeNode> queue = new LinkedList<>();
		queue.add(root.left);
		queue.add(root.right);
		while (!queue.isEmpty()) {
			TreeNode t1 = queue.poll();
			TreeNode t2 = queue.poll();

			if (t1 == null && t2 == null) {
				continue;
//				return true; // NG!
			}
			if (t1 == null || t2 == null) {
				return false;
			}
			if (t1.val != t2.val) {
				return false;
			}

			queue.offer(t1.left);
			queue.offer(t2.right);

			queue.offer(t1.right);
			queue.offer(t2.left);
		}

		return true;
	}




	// For testing. 
	public static void main(String[] args) {
		SymmetricTree solution = new SymmetricTree();

		// Test arguments. 
		//	    int num = 24;
		//	    int target = 2;
		//	    solution.getInt(num, target);



	}

}















