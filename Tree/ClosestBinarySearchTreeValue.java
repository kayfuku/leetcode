// 
// Author: 
// Date  : 

package leetcode;

public class ClosestBinarySearchTreeValue {
	// fields and classes here.
	// private int count;

	public ClosestBinarySearchTreeValue() {
		// Initialization here.
		// this.count = 0;

	}

	//
	// Author: @liaison and @andvary + kei
	// Date : August 12, 2019
	public int closestValue(TreeNode node, double target) {
		int val;
		int closest = node.val;
		while (node != null) {
			val = node.val;
			closest = (Math.abs(val - target) < Math.abs(closest - target)) ? val : closest;

			if (node.val > target) {
				node = node.left;
			} else {
				node = node.right;
			}
		}
		return closest;
	}

	// For testing.
	public static void main(String[] args) {
		ClosestBinarySearchTreeValue solution = new ClosestBinarySearchTreeValue();

		// Test arguments.
		// int num = 24;
		// int target = 2;
		// solution.getInt(num, target);

		// Binary Search Tree
		// 6
		// / \
		// 3 12
		// / \ / \
		// 1 4 9 14
		TreeNode tn1 = new TreeNode(6);
		TreeNode tn2 = new TreeNode(3);
		TreeNode tn3 = new TreeNode(1);
		TreeNode tn4 = new TreeNode(4);
		TreeNode tn5 = new TreeNode(12);
		TreeNode tn6 = new TreeNode(9);
		TreeNode tn7 = new TreeNode(14);
		tn1.left = tn2;
		tn1.right = tn5;
		tn2.left = tn3;
		tn2.right = tn4;
		tn5.left = tn6;
		tn5.right = tn7;

		double target = 5.7;
		System.out.println(solution.closestValue(tn1, target)); // 6
		target = 4.2;
		System.out.println(solution.closestValue(tn1, target)); // 4

	}

}
