// 
// Author: 
// Date  : June 24, 2019

package leetcode;

import googlecodejam.FashionPolice;

import java.util.Stack;

import javax.swing.RootPaneContainer;

public class PathSum {
	// fields here. 
	//	private int count;

	public PathSum() {
		// Initialization here. 
		//		this.count = 0;
	}

	// 1. Recursive. 
	// I'm going to traverse the tree using DFS because I need to check the root-to-leaf path. 
	// The input of the recursion stack is a root node and the remaining value. 
	// Every time I go into the next recursion, I subtract node.val from the sum. 
	// If I find a leaf node, then check if the remaining value is equal to 0. 
	// The output is true if there is such a path. 
	// 
	// O(N) time, O(logN) space for balanced, O(N) space for not balanced. 
	public boolean hasPathSum(TreeNode node, int remain) {
		if (node == null) {
			// There is no path. 
			return false;
		}
		
		remain -= node.val;
		if (node.left == null && node.right == null) {
			// There is no child node, which means node is a leaf. 
			return remain == 0;
		}
		// Either left or right child is not null. Keep going. 

		// Check the left subtree and right subtree. 
		boolean hasPathSumL = hasPathSum(node.left, remain);
		boolean hasPathSumR = hasPathSum(node.right, remain);

		return hasPathSumL || hasPathSumR;
	}
	
	
	// 2. Iterative. 
	// Accepted, but slow. 
	// O(N) time, O(logN) space for balanced, O(N) space for not balanced. 
	public boolean hasPathSumIter(TreeNode root, int sum) {
		// corner. 
		if (root == null) {
			return false;
		}
		
		Stack<TreeNode> stackNode = new Stack<>();
		Stack<Integer> stackValue = new Stack<>();
		
		stackNode.push(root);
		stackValue.push(sum);
		
		while (!stackNode.isEmpty()) {
			TreeNode node = stackNode.pop();
			int remain = stackValue.pop();
			
			remain -= node.val;
			if (node.left == null && node.right == null && remain == 0) {
				// node is a leaf, and remain is equal to 0. 
				return true;
			}
			// Keep going. 
			
			if (node.left != null) {
				stackNode.push(node.left);
				stackValue.push(remain);
			}
			if (node.right != null) {
				stackNode.push(node.right);
				stackValue.push(remain);
			}
		}
		
		return false;
	}

	
	
	
	


	// Review. 
	public boolean hasPathSumR(TreeNode node, int remain) {
		if (node == null) {
			return false;
		}

		if (node.left == null && node.right == null) {
			return node.val == remain;
		}

		boolean left = hasPathSum(node.left, remain - node.val);
		boolean right = hasPathSum(node.right, remain - node.val);

		return left || right;
	}




	// For testing. 
	public static void main(String[] args) {
		PathSum solution = new PathSum();

		// Test arguments. 
		//	    int num = 24;
		//	    int target = 2;
		//	    solution.getInt(num, target);



	}

}















