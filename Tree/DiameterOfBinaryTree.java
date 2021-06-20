package leetcode;

public class DiameterOfBinaryTree {
  // fields and classes here.
  // private int count;

  public DiameterOfBinaryTree() {
    // Initialization here.
    // this.count = 0;

  }

  // DFS
  // For each node, we need to keep track of the sum of the longest path of the
  // left subtree and the right subtree.
  //
  // Author: leetcode + kei
  // Date : June 19, 2021
  private int diameter;

  public int diameterOfBinaryTree(TreeNode root) {
    diameter = 0;
    longestPath(root);
    return diameter;
  }

  // Return the length of the longest path of either left or right subtree.
  private int longestPath(TreeNode node) {
    if (node == null) {
      return 0;
    }
    // Recursively find the longest path in both left child and right child.
    // Think about only one node, which has no child node.
    int leftPath = longestPath(node.left);
    int rightPath = longestPath(node.right);

    // The longest path in a tree is the sum of longest path of the left subtree and
    // the longest path of the right subtree.
    // Update the diameter if leftPath plus rightPath is larger.
    diameter = Math.max(diameter, leftPath + rightPath);

    // Return the longest one between leftPath and rightPath.
    // Remember to add 1 for the path connecting the node and its parent.
    // Think about only one node.
    return Math.max(leftPath, rightPath) + 1;
  }

  // For testing.
  @SuppressWarnings("unused")
  public static void main(String[] args) {
    DiameterOfBinaryTree solution = new DiameterOfBinaryTree();

    // Test arguments.
    // int num = 24;
    // int target = 2;
    // System.out.println(solution.getInt(num, target));

    System.out.println("\ndone.");
  }

}
