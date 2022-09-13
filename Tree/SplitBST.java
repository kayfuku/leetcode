// 
// Author: 
// Date  : July 10, 2019

package leetcode;

public class SplitBST {
	// fields and classes here.
	// private int count;

	public SplitBST() {
		// Initialization here.
		// this.count = 0;
	}

	// First, I need to traverse the tree to find where is V.
	// The input of the recursion stack is the root node of BST.
	// The output of the recursion stack is the root nodes of left subtree and right
	// subtree.
	//
	// Return subtree array, where
	// subtrees[0] is always a root node of the subtree that has all the nodes
	// smaller than or equal to V.
	// subtrees[1] is always a root node of the subtree that has all the nodes
	// bigger than V.
	//
	// In other words,
	// subtrees[0] belongs to the smaller group.
	// subtrees[1] belongs to the bigger group.
	//
	// O(H) time and space, where H is the height of the input tree.
	public TreeNode[] splitBST(TreeNode node, int V) {
		if (node == null) {
			return new TreeNode[] { null, null };
		}

		// For each recursion stack, we create a new output array.
		// Note that the initial values are { null, null }.
		TreeNode[] subtrees = new TreeNode[2];

		if (node.val <= V) {
			// In this case, node.left subtree is fine. No need to do something for the
			// current left subtree.
			// But the node.right subtree might need some modifications.
			// It does not matter whether node.right is smaller or bigger than V. The next
			// recursion stack
			// will determine this.
			subtrees = splitBST(node.right, V);
			// subtrees[0] is always a tree that has all the nodes smaller than or equal to
			// V.
			node.right = subtrees[0];
			// Output this node as smaller part.
			subtrees[0] = node;
		} else {
			// node.val > V
			// In this case, node.right subtree is fine. No need to do something for the
			// current right subtree.
			// But the node.left subtree might need some modifications.
			subtrees = splitBST(node.left, V);
			// subtrees[1] is always a tree that has all the nodes bigger than V.
			node.left = subtrees[1];
			// Output this node as bigger part.
			subtrees[1] = node;
		}

		return subtrees;
	}

	// Review.
	public TreeNode[] splitBstR(TreeNode node, int V) {
		if (node == null) {
			return new TreeNode[] { null, null };
		}

		TreeNode[] subtrees = new TreeNode[2];

		if (node.val <= V) {
			subtrees = splitBstR(node.right, V);
			node.right = subtrees[0];
			subtrees[0] = node;
		} else {
			subtrees = splitBstR(node.left, V);
			node.left = subtrees[1];
			subtrees[1] = node;
		}

		return subtrees;
	}

	// For testing.
	@SuppressWarnings("unused")
	public static void main(String[] args) {
		SplitBST solution = new SplitBST();

		// Test arguments.
		// int num = 24;
		// int target = 2;
		// solution.getInt(num, target);

	}

}
