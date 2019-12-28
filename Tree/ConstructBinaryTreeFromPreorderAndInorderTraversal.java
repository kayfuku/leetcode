// 
// Author: 
// Date  : June 22, 2019

package leetcode;

import java.util.HashMap;
import java.util.Map;

public class ConstructBinaryTreeFromPreorderAndInorderTraversal {
	// fields here. 
	//	private int count;

	public ConstructBinaryTreeFromPreorderAndInorderTraversal() {
		// Initialization here. 
		//		this.count = 0;
	}

	// Pre-order always starts from root, but for in-order, every node could be a root node. 
	// If I find which is the root in in-order, then I get to know every node to the left of 
	// the root is in the left subtree because that is the property of in-order. 
	// Author: yavinci
	// Data  : June 22, 2019
	// O(N) time, where N is the total number of nodes. 
	// O(logN) space for balanced, O(N) space for not balanced. 

	int[] preorder;
	int[] inorder;
	Map<Integer, Integer> inMap;

	public TreeNode buildTree(int[] preorder, int[] inorder) {
		this.preorder = preorder;
		this.inorder = inorder;

		// To find a root in in-order. 
		// K: element in inorder array, V: index of the element
		inMap = new HashMap<>();
		for (int i = 0; i < inorder.length; i++) {
			inMap.put(inorder[i], i);
		}

		TreeNode root = buildTree(0, preorder.length - 1, 0, inorder.length - 1);
		return root;
	}
	private TreeNode buildTree(int preStart, int preEnd, int inStart, int inEnd) {
		// Note that we have to keep going when preStart == preEnd (just one elem). 
		if (preStart > preEnd || inStart > inEnd) {
			return null;
		}

		// Get the root node value using preorder.
		TreeNode root = new TreeNode(preorder[preStart]);
		// Get the index of the root node in inorder. 
		int inRoot = inMap.get(root.val);
		// Get the number of nodes of the left-subtree in inorder. 
		int numsLeft = inRoot - inStart;

		// Build left subtree. 
		root.left = buildTree(preStart + 1, preStart + numsLeft, inStart, inRoot - 1);
		// Build right subtree. 
		root.right = buildTree(preStart + numsLeft + 1, preEnd, inRoot + 1, inEnd);

		return root;
	}


	// Review. 
//	int[] inorder;
//	int[] preorder;
//	Map<Integer, Integer> inMap;

	public TreeNode buildTreeR(int[] preorder, int[] inorder) {
		this.preorder = preorder;
		this.inorder = inorder;
		inMap = new HashMap<>();
		for (int i = 0; i < inorder.length; i++) {
			inMap.put(inorder[i], i);
		}

		return buildTreeR(0, preorder.length - 1, 0, inorder.length - 1);
	}
	private TreeNode buildTreeR(int preL, int preR, int inL, int inR) {
		if (preL > preR || inL > inR) {
			return null;
		}

		TreeNode root = new TreeNode(preorder[preL]);
		int inRoot = inMap.get(preorder[preL]);
		int numsL = inRoot - inL;
//		int numsL = inRoot + 1; // NG! Imagine every possible subarrays!

		root.left = buildTreeR(preL + 1, preL + numsL, inL, inRoot - 1);
		root.right = buildTreeR(preL + numsL + 1, preR, inRoot + 1, inR);

		return root;
	}







	// For testing. 
	public static void main(String[] args) {
		ConstructBinaryTreeFromPreorderAndInorderTraversal solution = new ConstructBinaryTreeFromPreorderAndInorderTraversal();

		// Test arguments. 
		//	    int num = 24;
		//	    int target = 2;
		//	    solution.getInt(num, target);



	}

}















