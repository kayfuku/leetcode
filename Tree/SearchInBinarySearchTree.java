//
// Author:
// Date : June 6, 2020

package leetcode;

public class SearchInBinarySearchTree {
  // fields and classes here.
  // private int count;

  public SearchInBinarySearchTree() {
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
  public TreeNode searchBST(TreeNode root, int val) {
    if (root == null) {
      return null;
    }

    if (val == root.val) {
      return root;
    }
    // Note that you don't need to traverse other branch if you
    // choose one. That's why we choose one of left or right.
    // Be careful about the <.
    if (val < root.val) {
      return searchBST(root.left, val);
    } else {
      return searchBST(root.right, val);
    }
  }


  // 2. Iterative
  // O(H) time, O(1) space, where H is the height of the tree.
  // Author: kei (AC)
  // Date : June 6, 2020
  public TreeNode searchBSTIter(TreeNode root, int val) {
    TreeNode node = root;
    while (node != null) {
      if (val == node.val) {
        return node;
      }

      if (val < node.val) {
        node = node.left;
      } else {
        node = node.right;
      }
    }

    return null;
  }



  // For testing.
  @SuppressWarnings("unused")
  public static void main(String[] args) {
    SearchInBinarySearchTree solution = new SearchInBinarySearchTree();

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
    TreeNode tn1 = solution.new TreeNode(6);
    TreeNode tn2 = solution.new TreeNode(3);
    TreeNode tn3 = solution.new TreeNode(1);
    TreeNode tn4 = solution.new TreeNode(4);
    TreeNode tn5 = solution.new TreeNode(12);
    TreeNode tn6 = solution.new TreeNode(9);
    TreeNode tn7 = solution.new TreeNode(14);
    tn1.left = tn2;
    tn1.right = tn5;
    tn2.left = tn3;
    tn2.right = tn4;
    tn5.left = tn6;
    tn5.right = tn7;

    int val = 2;
    solution.searchBST(tn1, val);



  }



  public void dummyMethod() {



  }



  public void dummyMethod2() {



  }



}


