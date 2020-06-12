//
// Author:
// Date : June 6, 2020

package leetcode;

import java.util.ArrayDeque;

public class DeleteNodeInBST {
  // fields and classes here.
  // private int count;

  public DeleteNodeInBST() {
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



  // Author: LeetCode + kei
  // Date : June 6, 2020
  public TreeNode deleteNode(TreeNode root, int key) {
    if (root == null) {
      return root;
    }

    if (root.val == key) {
      // No child node or One child node
      // Replace the current node with null or its child node.
      if (root.left == null) {
        return root.right;
      }
      if (root.right == null) {
        return root.left;
      }

      // Two children nodes
      // Replace the current node with its inorder successor.
      TreeNode inorderSuccessor = getInorderSuccessor(root);
      // Copy the value.
      root.val = inorderSuccessor.val;
      // Delete the inorder successor in the right subtree.
      root.right = deleteNode(root.right, inorderSuccessor.val);
      return root;
    }
    if (key < root.val) {
      root.left = deleteNode(root.left, key);
    } else {
      root.right = deleteNode(root.right, key);
    }

    return root;
  }

  // Find inorder successor in BST in the case that the root has two children nodes.
  // The inorder successor is the leftmost child in root's right subtree.
  private TreeNode getInorderSuccessor(TreeNode root) {
    // It is guaranteed that the root has a right child.
    TreeNode cur = root.right;
    while (cur != null && cur.left != null) {
      cur = cur.left;
    }
    return cur;
  }


  // NG!
  // Author: kei (WA)
  // Date : June 6, 2020
  public TreeNode deleteNodeNG(TreeNode root, int key) {
    if (root == null) {
      return null;
    }

    if (key == root.val) {
      if (root.left == null && root.right == null) {
        // No child node
        return null;
      } else if (root.left == null || root.right == null) {
        // One child node
        return (root.left == null) ? root.left : root.right;
      } else {
        // Two children nodes
        TreeNode inorderSucessor = getInorderSuccessor(root, root);
        deleteNode(root, inorderSucessor.val);
        return inorderSucessor;
      }
    }

    if (key < root.val) {
      root.left = deleteNode(root.left, key);
    } else {
      root.right = deleteNode(root.right, key);
    }

    return root;
  }

  public TreeNode getInorderSuccessor(TreeNode root, TreeNode p) {
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
    DeleteNodeInBST solution = new DeleteNodeInBST();

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


