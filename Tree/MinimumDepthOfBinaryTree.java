// 
// Author: 
// Date  : June 20, 2019

package leetcode;

import java.util.LinkedList;
import java.util.Queue;

public class MinimumDepthOfBinaryTree {
	// fields here.
	// private int count;

	public MinimumDepthOfBinaryTree() {
		// Initialization here.
		// this.count = 0;
	}

	// 1. Recursive DFS.
	// O(N) time, we visit every node once.
	// O(logN) space if it's balanced, O(N) if it's not balanced, because of the
	// recursion stack.
	public int minDepth(TreeNode node) {
		// This is always necessary. Check the node itself first! Not the subtree.
		if (node == null) {
			return 0;
		}

		// Postorder because we need to check both left and right subtree
		// if it's null or not.
		int leftDepth = minDepth(node.left);
		int rightDepth = minDepth(node.right);

		// If node only has one child, then the depth is 2, not 1.
		// So we cannot just take minimum between left and right.
		// left ====| right ===| depth ====|
		// null (0) | not null | right + 1
		// not null | null (0) | left + 1
		// null (0) | null (0) | 1
		//
		if (node.left == null || node.right == null) {
			return leftDepth + rightDepth + 1;
		}
		int minDep = Math.min(leftDepth, rightDepth) + 1;

		return minDep;
	}

	// 1-2. Recursive DFS.
	// Easy to understand.
	// Author: kei (AC)
	// Date : October 11, 2020
	public int minDepth2(TreeNode node) {
		if (node == null) {
			return 0;
		}
		if (node.left == null && node.right == null) {
			return 1;
		}
		if (node.left == null) {
			return minDepth2(node.right) + 1;
		}
		if (node.right == null) {
			return minDepth2(node.left) + 1;
		}

		return Math.min(minDepth2(node.left), minDepth2(node.right)) + 1;
	}

	// 1-3. Recursive DFS.
	// Easy to understand.
	// Author: leetcode + kei (AC)
	// Date : October 11, 2020
	public int minDepth3(TreeNode node) {
		if (node == null) {
			return 0;
		}
		if (node.left == null && node.right == null) {
			return 1;
		}

		int minDepth = Integer.MAX_VALUE;
		if (node.left != null) {
			minDepth = Math.min(minDepth, minDepth3(node.left));
		}
		if (node.right != null) {
			minDepth = Math.min(minDepth, minDepth3(node.right));
		}

		return minDepth + 1;
	}

	// 1-4. Recursive DFS.
	// Easy to understand.
	// Author: kei (AC)
	// Date : October 11, 2020
	public int minDepth4(TreeNode node) {
		if (node == null) {
			return 0;
		}
		if (node.left == null && node.right == null) {
			return 1;
		}

		// Not initialized to 0!
		int left = Integer.MAX_VALUE;
		int right = Integer.MAX_VALUE;
		if (node.left != null) {
			left = minDepth4(node.left);
		}
		if (node.right != null) {
			right = minDepth4(node.right);
		}

		return Math.min(left, right) + 1;
	}

	// 2. Iterative DFS.
	// Author: kei (AC)
	// Date : October 11, 2020
	public int minDepthIterDfs(TreeNode root) {
		if (root == null) {
			return 0;
		}
		LinkedList<TreeNode> s1 = new LinkedList<>();
		LinkedList<Integer> s2 = new LinkedList<>();
		s1.push(root);
		s2.push(1);

		int minDepth = Integer.MAX_VALUE;
		while (!s1.isEmpty()) {
			TreeNode node = s1.pop();
			int depth = s2.pop();

			if (node.left == null && node.right == null) {
				minDepth = Math.min(minDepth, depth);
			}

			if (node.right != null) {
				s1.push(node.right);
				s2.push(depth + 1);
			}
			if (node.left != null) {
				s1.push(node.left);
				s2.push(depth + 1);
			}
		}

		return minDepth;
	}

	// 3. BFS (Level-order).
	// Find the closest leaf node.
	// O(N) time, where N is the total num of nodes, we visit every node once.
	// O(N) space, because of the size of the queue.
	public int minDepthBfs(TreeNode root) {
		if (root == null) {
			return 0;
		}

		Queue<TreeNode> queue = new LinkedList<>();
		queue.offer(root);

		// If the leftmost node has no child, then we can
		// return faster.
		int depth = 0;
		while (!queue.isEmpty()) {
			depth++;

			// For each level.
			int size = queue.size();
			for (int i = 0; i < size; i++) {
				TreeNode node = queue.poll();

				// If the node has no child node, then the current depth is
				// the minimum depth.
				if (node.left == null && node.right == null) {
					return depth;
				}

				// If either left or right child is not null, then we need to
				// keep going.
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
	public int minDepthR(TreeNode node) {
		if (node == null) {
			return 0;
		}

		int heightL = minDepth(node.left);
		int heightR = minDepth(node.right);

		// left right height
		// 1 null 0 not null right + 1
		// 2 not null null 0 left + 1
		// 3 null null 1
		if (node.left == null || node.right == null) {
			return heightL + heightR + 1;
		}
		int minH = Math.min(heightL, heightR) + 1;

		return minH;
	}

	// Review
	public int minDepthBfsR(TreeNode root) {
		// corner.
		if (root == null) {
			return 0;
		}

		Queue<TreeNode> queue = new LinkedList<>();
		queue.add(root);

		int depth = 0;
		while (!queue.isEmpty()) {
			depth++;

			int size = queue.size();
			for (int i = 0; i < size; i++) {
				TreeNode node = queue.poll();

				if (node.left == null && node.right == null) {
					return depth;
				}

				if (node.left != null) {
					queue.add(node.left);
				}
				if (node.right != null) {
					queue.add(node.right);
				}

			}
		}

		return depth;
	}

	// Review 3
	// Easy to understand.
	// Author: kei (AC)
	// Date : October 11, 2020
	public int minDepthR3(TreeNode node) {
		if (node == null) {
			return 0;
		}
		if (node.left == null && node.right == null) {
			return 1;
		}
		if (node.left == null) {
			return minDepthR3(node.right) + 1;
		}
		if (node.right == null) {
			return minDepthR3(node.left) + 1;
		}

		return Math.min(minDepthR3(node.left), minDepthR3(node.right)) + 1;
	}

	// For testing.
	public static void main(String[] args) {
		MinimumDepthOfBinaryTree solution = new MinimumDepthOfBinaryTree();

		// Test arguments.
		// int num = 24;
		// int target = 2;
		// solution.getInt(num, target);

		// Binary Tree
		// 1
		// / \
		// 5 2
		// /
		// 3
		BinaryTree binaryTree = new BinaryTree();
		TreeNode n1 = new TreeNode(1);
		TreeNode n2 = new TreeNode(2);
		TreeNode n3 = new TreeNode(3);
		TreeNode n5 = new TreeNode(5);
		binaryTree.root = n1;
		n1.left = n5;
		n1.right = n2;
		n2.left = n3;
		System.out.println(solution.minDepthBfs(binaryTree.root)); // 2

		// Binary Tree
		// 4
		// / \
		// null null
		BinaryTree binaryTree2 = new BinaryTree();
		TreeNode n4 = new TreeNode(4);
		binaryTree2.root = n4;
		System.out.println(solution.minDepthBfs(binaryTree2.root)); // 1

	}

}
