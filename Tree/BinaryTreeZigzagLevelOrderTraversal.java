// 
// Author: 
// Date  : June 20, 2019

package leetcode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class BinaryTreeZigzagLevelOrderTraversal {
	// fields here.
	// private int count;

	public BinaryTreeZigzagLevelOrderTraversal() {
		// Initialization here.
		// this.count = 0;
	}

	// 1. BFS.
	// Depending on the level, add the value to the head or tail of the list.
	// O(N) time, where N is the total number of nodes, because we visit every node.
	// O(N) space, because of the size of the queue.
	public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
		List<List<Integer>> ret = new LinkedList<>();
		if (root == null) {
			return ret;
		}

		Queue<TreeNode> queue = new LinkedList<>();
		queue.offer(root);
		int level = 0;
		while (!queue.isEmpty()) {
			List<Integer> valsL = new ArrayList<>();
			int size = queue.size();
			for (int i = 0; i < size; i++) {
				TreeNode node = queue.poll();

				// Do Zigzag.
				if (level % 2 == 0) {
					// level is even. Add the value to the tail of the list.
					valsL.add(node.val);
				} else {
					// level is odd. Add the value to the head of the list.
					valsL.add(0, node.val);
				}

				if (node.left != null) {
					queue.offer(node.left);
				}
				if (node.right != null) {
					queue.offer(node.right);
				}
			}
			ret.add(valsL);
			level++;
		}

		return ret;
	}

	// 2. DFS, recursive.
	// Basically, do the DFS level order traversal.
	// Depending on the number of the level, add the value to the head or tail of
	// the list.
	// O(N) time, where N is the total number of nodes, because we visit every node.
	// O(logN) space, because of the recursion stack.
	public List<List<Integer>> zigzagLevelOrderDfs(TreeNode root) {
		List<List<Integer>> ret = new ArrayList<>();
		if (root == null) {
			return ret;
		}
		zigzagLevelOrderDfsHelper(root, 0, ret);
		return ret;
	}

	private void zigzagLevelOrderDfsHelper(TreeNode node, int level, List<List<Integer>> ret) {
		if (node == null) {
			return;
		}

		if (ret.size() == level) {
			ret.add(new ArrayList<Integer>());
		}

		// Do Zigzag.
		if (level % 2 == 0) {
			ret.get(level).add(node.val);
		} else {
			ret.get(level).add(0, node.val);
		}

		zigzagLevelOrderDfsHelper(node.left, level + 1, ret);
		zigzagLevelOrderDfsHelper(node.right, level + 1, ret);
	}

	// Review.
	public List<List<Integer>> zigzagLevelOrderBfsR(TreeNode root) {
		List<List<Integer>> ret = new ArrayList<>();
		// corner.
		if (root == null) {
			return ret;
		}

		Queue<TreeNode> queue = new LinkedList<>();
		queue.offer(root);
		int level = 0;
		while (!queue.isEmpty()) {
			List<Integer> valsL = new ArrayList<>();
			int size = queue.size();
			for (int i = 0; i < size; i++) {
				TreeNode node = queue.poll();

				if (level % 2 == 0) {
					valsL.add(node.val);
				} else {
					valsL.add(0, node.val);
				}

				if (node.left != null) {
					queue.offer(node.left);
				}
				if (node.right != null) {
					queue.offer(node.right);
				}
			}

			ret.add(valsL);
			level++;
		}

		return ret;
	}

	// Review. DFS.
	public List<List<Integer>> zigzagLevelOrderDfsR(TreeNode root) {
		List<List<Integer>> ret = new ArrayList<>();
		if (root == null) {
			return ret;
		}
		zigzagLevelOrder(root, 0, ret);
		return ret;
	}

	private void zigzagLevelOrder(TreeNode node, int level, List<List<Integer>> ret) {
		if (node == null) {
			return;
		}

		if (ret.size() == level) {
			ret.add(new ArrayList<>());
		}
		if (level % 2 == 0) {
			ret.get(level).add(node.val);
		} else {
			ret.get(level).add(0, node.val);
		}

		zigzagLevelOrder(node.left, level + 1, ret);
		zigzagLevelOrder(node.right, level + 1, ret);
	}

	// For testing.
	public static void main(String[] args) {
		BinaryTreeZigzagLevelOrderTraversal solution = new BinaryTreeZigzagLevelOrderTraversal();

		// Test arguments.
		// int num = 24;
		// int target = 2;
		// solution.getInt(num, target);

		// Binary Tree
		// 1
		// / \
		// 9 20
		// / \
		// 15 7
		BinaryTree binaryTree = new BinaryTree();
		TreeNode n1 = new TreeNode(3);
		TreeNode n2 = new TreeNode(20);
		TreeNode n3 = new TreeNode(15);
		TreeNode n4 = new TreeNode(9);
		TreeNode n5 = new TreeNode(7);

		binaryTree.root = n1;
		n1.left = n4;
		n1.right = n2;
		n2.left = n3;
		n2.right = n5;

		List<List<Integer>> ret = solution.zigzagLevelOrder(binaryTree.root);
		System.out.println(ret); // [[3], [20, 9], [15, 7]]

	}

}
