// 
// Author: 
// Date  : January 4, 2020

package leetcode;

public class CountUnivalueSubtrees {
	// fields and classes here. 
	//private int count;

	public CountUnivalueSubtrees() {
		// Initialization here. 
		//this.count = 0;

	}


	// Author: alwinpeng + kei
	// Date  : January 4, 2020
	int count = 0;
	public int countUnivalSubtrees(TreeNode root) {
		isUnivalue(root, 0);
		return count;
	}
	private boolean isUnivalue(TreeNode node, int val) {
		// Considered a valid subtree
		if (node == null) {
			return true;
		}

		// Postorder (Bottom-up) because we need to check the both left and right subtrees. 
		// Check if node.left and node.right are univalue subtrees of value node.val
		// note that || short circuits but | does not. Both sides of the or 
		// get evaluated with | so we explore all possible routes. 
		if (!isUnivalue(node.left, node.val) | !isUnivalue(node.right, node.val)) {
			return false;
		}
		// Assert that this is a valid subtree. 

		count++;

		// At this point we know that this node is a univalue subtree of value node.val
		// pass a boolean indicating if this is a valid subtree for the parent node. 
		return node.val == val;
	}
	







	// For testing. 
	public static void main(String[] args) {
		CountUnivalueSubtrees solution = new CountUnivalueSubtrees();

		// Test arguments. 
		// int num = 24;
		// int target = 2;
		// solution.getInt(num, target);



	}

}















