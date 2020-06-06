//
// Author:
// Date : June 5, 2020

package leetcode;

import java.util.ArrayDeque;

public class InorderSuccessorInBST {
  // fields and classes here.
  // private int count;

  public InorderSuccessorInBST() {
    // Initialization here.
    // this.count = 0;

  }

  public class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode(int x) {
      val = x;
    }
  }


  // Author: LeetCode + kei
  // Date : June 5, 2020
  public TreeNode inorderSuccessor(TreeNode root, TreeNode p) {
    // The node has a right child.
    // The successor is somewhere lower in the right subtree.
    // successor: one step right and then left till you can
    if (p.right != null) {
      p = p.right;
      while (p.left != null) {
        p = p.left;
      }
      return p;
    }

    // The node does not have a right child.
    // The successor is somewhere upper in the tree.
    ArrayDeque<TreeNode> stack = new ArrayDeque<>();
    TreeNode prev = null;

    // inorder traversal : left -> node -> right
    TreeNode node = root;
    while (!stack.isEmpty() || node != null) {
      // 1. Go left till you can.
      while (node != null) {
        stack.push(node);
        node = node.left;
      }

      // 2. All logic around the node
      node = stack.pop();
      // If the previous node was equal to p,
      // then the current node is its successor.
      if (prev == p) {
        return node;
      }
      prev = node;

      // 3. Go one step right.
      node = node.right;
    }

    // There is no successor.
    return null;
  }



  // For testing.
  @SuppressWarnings("unused")
  public static void main(String[] args) {
    InorderSuccessorInBST solution = new InorderSuccessorInBST();

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


