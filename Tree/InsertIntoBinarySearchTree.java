//
// Author:
// Date : June 6, 2020

package leetcode;

public class InsertIntoBinarySearchTree {
  // fields and classes here.
  // private int count;

  public InsertIntoBinarySearchTree() {
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


  // 1. Recursive
  // O(H) time, O(H) space, where H is the height of the tree.
  // Author: kei (AC)
  // Date : June 6, 2020
  public TreeNode insertIntoBST(TreeNode root, int val) {
    if (root == null) {
      return new TreeNode(val);
    }

    if (val < root.val) {
      root.left = insertIntoBST(root.left, val);
    } else {
      root.right = insertIntoBST(root.right, val);
    }

    return root;
  }


  // 2. Iterative
  // O(H) time, O(1) space, where H is the height of the tree.
  // Author: LeetCode + kei (AC)
  // Date : June 6, 2020
  public TreeNode insertIntoBSTIter(TreeNode root, int val) {
    TreeNode node = root;
    while (node != null) {
      if (val < node.val) {
        if (node.left == null) {
          node.left = new TreeNode(val);
          // Don't forget this!
          return root;
        }
        node = node.left;
      } else {
        if (node.right == null) {
          node.right = new TreeNode(val);
          // Don't forget this!
          return root;
        }
        node = node.right;
      }
    }

    // Don't forget this!
    return new TreeNode(val);
  }



  // For testing.
  @SuppressWarnings("unused")
  public static void main(String[] args) {
    InsertIntoBinarySearchTree solution = new InsertIntoBinarySearchTree();

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


