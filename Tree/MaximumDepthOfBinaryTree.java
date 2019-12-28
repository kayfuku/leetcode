// 
// Author: 
// Date  : June 20, 2019

package leetcode;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class MaximumDepthOfBinaryTree {
	// fields here. 
	//	private int count;

	public MaximumDepthOfBinaryTree() {
		// Initialization here. 
		//		this.count = 0;
	}

	// 1. Recursive (DFS). 
	// The simplest solution. 
	// For each recursion stack, I get the height of the left subtree and the right subtree, and 
	// take a max of them. And add it to 1, and then return it. 
	// O(N) time, we visit every node once. 
	// O(logN) space if it's balanced, O(N) if it's not balanced, because of the recursion stack. 
	public int maxDepth(TreeNode node) {
		if (node == null) {
			return 0;
		}

		// Post-order traversal. 
		int leftDepth = maxDepth(node.left);
		int rightDepth = maxDepth(node.right);
		int maxDep = Math.max(leftDepth, rightDepth) + 1;

		return maxDep;
	}

	// 2. Iterative (DFS). 
	// I'm going to use two stacks to traverse the tree by DFS with the node and level. 
	// And then keep track of the max of the level. 
	// O(N) time, where N is the total num of nodes, we visit every node once. 
	// O(logN) space if it's balanced, O(N) if it's not balanced, because of the stack. 
	public int maxDepthIterDfs(TreeNode root) {
		if (root == null) {
			return 0;
		}

		Stack<TreeNode> stackNode = new Stack<>();
		Stack<Integer> stackDep = new Stack<>();

		stackNode.push(root);
		stackDep.push(1);

		int maxDep = 0;
		while (!stackNode.isEmpty()) {
			TreeNode node = stackNode.pop();
			int curDep = stackDep.pop();

			maxDep = Math.max(maxDep, curDep);

			if (node.right != null) {
				stackNode.push(node.right);
				stackDep.push(curDep + 1);
			}
			if (node.left != null) {
				stackNode.push(node.left);
				stackDep.push(curDep + 1);
			}
		}

		return maxDep;
	}

	// 3. Iterative (BFS). Level-order. 
	// BFS works as well. 
	// 
	// O(N) time, where N is the total num of nodes, we visit every node once. 
	// O(N) space, because of the size of the queue. 
	public int maxDepthIterBfs(TreeNode root) {
		if (root == null) {
			return 0;
		}

		Queue<TreeNode> queue = new LinkedList<>();
		queue.offer(root);

		// No need to keep track of max. 
		int depth = 0;
		while (!queue.isEmpty()) {
			depth++;

			int levelSize = queue.size();
			for (int i = 0; i < levelSize; i++) {
				TreeNode node = queue.poll();

				// If there is no child node any more, then 
				// the current depth is the max depth. 
				if (node.left != null) {
					queue.offer(node.left);
				}
				if (node.right != null) {
					queue.offer(node.right);
				}
			}
		}

		return depth;
	}


	// Review 
	public int maxDepthR(TreeNode node) {
		if (node == null) {
			return 0;
		}

		int heightL = maxDepth(node.left);
		int heightR = maxDepth(node.right);
		int max = Math.max(heightL, heightR) + 1;

		return max;
	}

	// Review 
	public int maxDepthDfsIterR(TreeNode root) {
		// corner. 
		if (root == null) {
			return 0;
		}

		Stack<TreeNode> stackNode = new Stack<>();
		Stack<Integer> stackL = new Stack<>();

		stackNode.push(root);
		stackL.push(1);
		int maxLevel = 0;
		while (!stackNode.isEmpty()) {
			TreeNode node = stackNode.pop();
			int curL = stackL.pop();

			maxLevel = Math.max(maxLevel, curL);

			if (node.left != null) {
				stackNode.push(node.left);
				stackL.push(curL + 1);
			}
			if (node.right != null) {
				stackNode.push(node.right);
				stackL.push(curL + 1);
			}
		}

		return maxLevel;	
	} 


	// Review 
	public int maxDepthIterBfsR(TreeNode root) {
		// corner. 
		if (root == null) {
			return 0;
		}

		Queue<TreeNode> queue = new LinkedList<>();
		queue.add(root);	

		int level = 0;
		while (!queue.isEmpty()) {
			level++;		
			int size = queue.size();
			for (int i = 0; i < size; i++) {
				TreeNode node = queue.poll();
				if (node.left != null) {
					queue.add(node.left);
				}
				if (node.right != null) {
					queue.add(node.right);
				}	
			}	
		}

		return level;	
	}






	// For testing. 
	public static void main(String[] args) {
		MaximumDepthOfBinaryTree solution = new MaximumDepthOfBinaryTree();

		// Test arguments. 
		//	    int num = 24;
		//	    int target = 2;
		//	    solution.getInt(num, target);



	}

}















