//
// Author:
// Date : January 4, 2020

package leetcode;

import java.util.LinkedList;
import java.util.List;

public class BinaryTreePostorderTraversal {
  // fields and classes here.
  // private int count;

  public BinaryTreePostorderTraversal() {
    // Initialization here.
    // this.count = 0;

  }

  // Recursive.
  // Author: kei (AC)
  // Date : January 4, 2020
  public List<Integer> postorderTraversal(TreeNode root) {
    List<Integer> ret = new LinkedList<>();
    postorderTraversal(root, ret);
    return ret;
  }

  public void postorderTraversal(TreeNode node, List<Integer> ret) {
    if (node == null) {
      return;
    }

    postorderTraversal(node.left, ret);
    postorderTraversal(node.right, ret);
    ret.add(node.val);
  }


  // Iterative.
  // Author: @liaison and @andvary + kei (AC)
  // Date : January 4, 2020
  public List<Integer> postorderTraversal2(TreeNode root) {
    LinkedList<Integer> ret = new LinkedList<>();
    if (root == null) {
      return ret;
    }

    // Postorder is kind of reversed-right-first-preorder.
    // Note that the type stack is LinkedList, not List!
    LinkedList<TreeNode> stack = new LinkedList<>();
    stack.push(root);
    while (!stack.isEmpty()) {
      TreeNode node = stack.pop();
      // This is addFirst(), not add(), to reverse the result order.
      ret.addFirst(node.val);

      // Right-first-preorder.
      // Push left first so that you can pop right first.
      if (node.left != null) {
        stack.push(node.left);
      }
      if (node.right != null) {
        stack.push(node.right);
      }
    }

    return ret;
  }



  // For testing.
  public static void main(String[] args) {
    BinaryTreePostorderTraversal solution = new BinaryTreePostorderTraversal();

    // Test arguments.
    // int num = 24;
    // int target = 2;
    // solution.getInt(num, target);



  }

}


