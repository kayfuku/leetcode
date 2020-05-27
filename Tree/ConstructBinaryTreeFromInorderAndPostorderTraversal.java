//
// Author:
// Date : May 26, 2020

package leetcode;

import java.util.HashMap;

public class ConstructBinaryTreeFromInorderAndPostorderTraversal {
  // fields and classes here.
  // private int count;

  public ConstructBinaryTreeFromInorderAndPostorderTraversal() {
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


  // Use postorder to find the next root node, and use inorder
  // to figure out which nodes are in the left subtree or right subtree.
  // Author: LeetCode + kei
  // Date : May 26, 2020

  int postRoot;
  int[] postorder;
  int[] inorder;
  // K: element in inorder array, V: index of the element
  HashMap<Integer, Integer> inMap = new HashMap<Integer, Integer>();

  public TreeNode buildTree(int[] inorder, int[] postorder) {
    this.postorder = postorder;
    this.inorder = inorder;
    // The rightmost elem in the postorder is always the root node.
    postRoot = postorder.length - 1;

    // To find a root in in-order.
    // K: element in inorder array, V: index of the element
    for (int i = 0; i < inorder.length; i++) {
      inMap.put(inorder[i], i);
    }

    return helper(0, inorder.length - 1);
  }

  public TreeNode helper(int inLeft, int inRight) {
    // Note that we have to keep going when inStart == inEnd (just one elem).
    if (inLeft > inRight) {
      return null;
    }

    // Get the root node value.
    TreeNode node = new TreeNode(postorder[postRoot]);
    // Get the index of the root node in inorder.
    int inRoot = inMap.get(node.val);

    // Points to the next root node.
    postRoot--;
    // Note that right subtree first, then left subtree
    // because postRoot points to the root node in this order.
    // Build right subtree.
    node.right = helper(inRoot + 1, inRight);
    // Build left subtree.
    node.left = helper(inLeft, inRoot - 1);

    return node;
  }



  // For testing.
  @SuppressWarnings("unused")
  public static void main(String[] args) {
    ConstructBinaryTreeFromInorderAndPostorderTraversal solution =
        new ConstructBinaryTreeFromInorderAndPostorderTraversal();

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


