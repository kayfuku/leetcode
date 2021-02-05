package leetcode;

public class SubtreeOfAnotherTree {
  // fields and classes here.
  // private int count;

  public SubtreeOfAnotherTree() {
    // Initialization here.
    // this.count = 0;

  }

  // Author: kei (AC)
  // Date : February 3, 2021
  public boolean isSubtree(TreeNode s, TreeNode t) {
    if (s == null && t == null) {
      return true;
    }
    if (s == null || t == null) {
      // Either one is null.
      return false;
    }

    if (isSameTree(s, t)) {
      return true;
    }

    // Traverse the tree s.
    return isSubtree(s.left, t) || isSubtree(s.right, t);
  }

  boolean isSameTree(TreeNode node, TreeNode t) {
    if (node == null && t == null) {
      return true;
    }
    if (node == null || t == null) {
      // Either one is null.
      return false;
    }

    if (node.val != t.val) {
      return false;
    }

    return isSameTree(node.left, t.left) && isSameTree(node.right, t.right);
  }

  /**
   * Definition for a binary tree node. public class TreeNode { int val; TreeNode
   * left; TreeNode right; TreeNode() {} TreeNode(int val) { this.val = val; }
   * TreeNode(int val, TreeNode left, TreeNode right) { this.val = val; this.left
   * = left; this.right = right; } }
   */

  // For testing.
  @SuppressWarnings("unused")
  public static void main(String[] args) {
    SubtreeOfAnotherTree solution = new SubtreeOfAnotherTree();

    // Test arguments.
    // int num = 24;
    // int target = 2;
    // System.out.println(solution.getInt(num, target));

    System.out.println("\ndone.");
  }

}
