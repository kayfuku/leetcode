//
// Author:
// Date : June 11 , 2020

package leetcode;

public class BalancedBinaryTree {
  // fields and classes here.
  // private int count;

  public BalancedBinaryTree() {
    // Initialization here.
    // this.count = 0;

  }

  public class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode() {}

    TreeNode(int val) {
      this.val = val;
    }

    TreeNode(int val, TreeNode left, TreeNode right) {
      this.val = val;
      this.left = left;
      this.right = right;
    }
  }

  // 1. Top-down recursion
  // Note that just checking if the height of left and right subtrees is not enough.
  // The left and right subtrees must be balanced.
  // Author: LeetCode + kei
  // Date : June 11, 2020

  // Recursively obtain the height of a tree.
  private int getHeight(TreeNode node) {
    if (node == null) {
      // An empty tree has height -1.
      return -1;
    }

    return 1 + Math.max(getHeight(node.left), getHeight(node.right));
  }

  public boolean isBalanced(TreeNode node) {
    if (node == null) {
      // An empty tree satisfies the definition of a balanced tree.
      return true;
    }

    // Check if subtrees have height within 1, if it's not, return false immediately.
    if (Math.abs(getHeight(node.left) - getHeight(node.right)) > 1) {
      return false;
    }

    // Check if the subtrees are balanced.
    return isBalanced(node.left) && isBalanced(node.right);
  }


  // 2. Bottom-up recursion (Not reviewed yet)
  // Return whether or not the tree at root is balanced while also storing
  // the tree's height in a reference variable.
  // Author: LeetCode + kei
  // Date : June 11, 2020

  // Utility class to store information from recursive calls
  final class TreeInfo {
    public final int height;
    public final boolean balanced;

    public TreeInfo(int height, boolean balanced) {
      this.height = height;
      this.balanced = balanced;
    }
  }

  private TreeInfo isBalancedTreeHelper(TreeNode root) {
    // An empty tree is balanced and has height = -1
    if (root == null) {
      return new TreeInfo(-1, true);
    }

    // Check subtrees to see if they are balanced.
    TreeInfo left = isBalancedTreeHelper(root.left);
    if (!left.balanced) {
      return new TreeInfo(-1, false);
    }
    TreeInfo right = isBalancedTreeHelper(root.right);
    if (!right.balanced) {
      return new TreeInfo(-1, false);
    }

    // Use the height obtained from the recursive calls to
    // determine if the current node is also balanced.
    if (Math.abs(left.height - right.height) < 2) {
      return new TreeInfo(Math.max(left.height, right.height) + 1, true);
    }
    return new TreeInfo(-1, false);
  }

  public boolean isBalancedB(TreeNode root) {
    return isBalancedTreeHelper(root).balanced;
  }



  // For testing.
  @SuppressWarnings("unused")
  public static void main(String[] args) {
    BalancedBinaryTree solution = new BalancedBinaryTree();

    // Test arguments.
    // int num = 24;
    // int target = 2;
    // solution.getInt(num, target);



  }



  public void dummyMethod() {



  }



  public void dummyMethod2() {



  }



}


