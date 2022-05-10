//
// Author:
// Date : January 3, 2020

package leetcode;

import java.util.LinkedList;
import java.util.List;

public class BinaryTreePreorderTraversal {
  // fields and classes here.
  // private int count;

  public BinaryTreePreorderTraversal() {
    // Initialization here.
    // this.count = 0;

  }

  // Recursive.
  // Author: kei (AC)
  // Date : January 3, 2020
  public List<Integer> preorderTraversal(TreeNode node) {
    List<Integer> ret = new LinkedList<>();
    preorderTraversal(node, ret);
    return ret;
  }

  public void preorderTraversal(TreeNode node, List<Integer> ret) {
    if (node == null) {
      return;
    }

    // Pre-order traversal.
    ret.add(node.val);
    preorderTraversal(node.left, ret);
    preorderTraversal(node.right, ret);
  }

  // Iterative.
  // Author: kei (AC)
  // Date : January 3, 2020
  public List<Integer> preorderTraversal2(TreeNode root) {
    List<Integer> ret = new LinkedList<>();
    if (root == null) {
      return ret;
    }

    LinkedList<TreeNode> stack = new LinkedList<>();
    stack.push(root);

    while (!stack.isEmpty()) {
      TreeNode node = stack.poll();
      ret.add(node.val);

      // Add all the children of the node to stack.
      // Note that right child is first, then left for pre-order traversal.
      // When popped, it is left child first.
      if (node.right != null) {
        stack.push(node.right);
      }
      if (node.left != null) {
        stack.push(node.left);
      }
    } // end while (...)

    return ret;
  }

  // For testing.
  @SuppressWarnings("unused")
  public static void main(String[] args) {
    BinaryTreePreorderTraversal solution = new BinaryTreePreorderTraversal();

    // Test arguments.
    // int num = 24;
    // int target = 2;
    // solution.getInt(num, target);

  }

}
