// 
// Author: 
// Date  : 

package leetcode;

public class LowestCommonAncestorOfDeepestLeaves {
	// fields and classes here. 
	//private int count;

	public LowestCommonAncestorOfDeepestLeaves() {
		// Initialization here. 
		//this.count = 0;

	}





	// Author: 
	// Date  : August 31, 2019
	public TreeNode lcaDeepestLeaves(TreeNode root) {
		return lcaDeepestLeaves(root, 0);
	}
	private TreeNode lcaDeepestLeaves(TreeNode node, int depth) {
		if (node.left == null && node.right == null) {
			// Leaf
			return node;			
		}





		return node;
	}



	// Using Pair that has tree node and the depth. 
	// https://leetcode.com/problems/lowest-common-ancestor-of-deepest-leaves/discuss/357549/Easy-efficient-Java-O(n)-solution
	// Author: schaynika + kei
	// Date  : August 31, 2019
	class Pair {
		int depth;
		TreeNode node;
		public Pair(TreeNode node, int depth){
			this.node = node;
			this.depth = depth;
		}
	}
	public TreeNode lcaDeepestLeaves2(TreeNode root) {
		Pair p = helper(root);
		return p.node;
	}
	public Pair helper(TreeNode node){
		if (node == null){
			return new Pair(null, 0);
		}

		
		// Inorder traversal. 
		Pair leftRet = helper(node.left);
		Pair rightRet = helper(node.right);

		if (leftRet.depth == rightRet.depth){
			// The depths of left and right subtree are the same. 
			// Return the node. 
			return new Pair(node, leftRet.depth + 1);
		}
		
		// Return the node with deeper depth. 
		if (leftRet.depth < rightRet.depth){
			return new Pair(rightRet.node, rightRet.depth + 1);
		}
		// l.depth > r.depth
		return new Pair(leftRet.node, leftRet.depth + 1); 
	}


	// For testing. 
	public static void main(String[] args) {
		LowestCommonAncestorOfDeepestLeaves solution = new LowestCommonAncestorOfDeepestLeaves();

		// Test arguments. 
		// int num = 24;
		// int target = 2;
		// solution.getInt(num, target);

		// Binary Tree
		//     1
		//    / \
		//   2   3 
		//  /
		// 4
//		BinaryTree binaryTree = new BinaryTree();
//		TreeNode n1 = new TreeNode(1);
//		TreeNode n2 = new TreeNode(2);
//		TreeNode n3 = new TreeNode(3);
//		TreeNode n4 = new TreeNode(4);
//		binaryTree.root = n1;
//		n1.right = n3;
//		n1.left = n2;
//		n2.left = n4;
//		
//		System.out.println(solution.lcaDeepestLeaves2(n1)); // 4


		// Binary Tree
		//     1
		//    / \
		//   2   3 
		//      / \
		//     4   5
		//    / \
		//   6   7
//		BinaryTree binaryTree = new BinaryTree();
//		TreeNode n1 = new TreeNode(1);
//		TreeNode n2 = new TreeNode(2);
//		TreeNode n3 = new TreeNode(3);
//		TreeNode n4 = new TreeNode(4);
//		TreeNode n5 = new TreeNode(5);
//		TreeNode n6 = new TreeNode(6);
//		TreeNode n7 = new TreeNode(7);
//		binaryTree.root = n1;
//		n1.right = n3;
//		n1.left = n2;
//		n3.left = n4;
//		n3.right = n5;
//		n4.left = n6;
//		n4.right = n7;
//      		
//		System.out.println(solution.lcaDeepestLeaves2(n1)); // 4
		
		
		// Binary Tree
		//     1
		//    / \
		//   2   3 
		//  /     \
		// 4       5
		BinaryTree binaryTree = new BinaryTree();
		TreeNode n1 = new TreeNode(1);
		TreeNode n2 = new TreeNode(2);
		TreeNode n3 = new TreeNode(3);
		TreeNode n4 = new TreeNode(4);
		TreeNode n5 = new TreeNode(5);
		binaryTree.root = n1;
		n1.right = n3;
		n1.left = n2;
		n2.left = n4;
		n3.right = n5;
		
		System.out.println(solution.lcaDeepestLeaves2(n1)); // 1






	}

}















