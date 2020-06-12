//
// Author:
// Date : June 9, 2020

package leetcode;

public class LowestCommonAncestorOfBinarySearchTree {
  // fields and classes here.
  // private int count;

  public LowestCommonAncestorOfBinarySearchTree() {
    // Initialization here.
    // this.count = 0;

  }


  // 1. Recursive
  // O(N) time, O(N) space
  // Author: LeetCode + kei
  // Date : June 9, 2020
  public TreeNode lowestCommonAncestor2(TreeNode node, TreeNode p, TreeNode q) {
    int pVal = p.val;
    int qVal = q.val;

    if (pVal > node.val && qVal > node.val) {
      // Both p and q are greater than the current node.
      return lowestCommonAncestor2(node.right, p, q);
    } else if (pVal < node.val && qVal < node.val) {
      // Both p and q are lesser than the current node.
      return lowestCommonAncestor2(node.left, p, q);
    } else {
      // We have found the split point, i.e. the LCA node.
      return node;
    }
  }



  // 2. Iterative
  // O(N) time, O(1) space
  // Author: LeetCode + kei
  // Date : June 9, 2020
  public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
    int pVal = p.val;
    int qVal = q.val;

    // Start from the root node of the tree.
    TreeNode node = root;
    // Traverse the tree
    while (node != null) {
      if (pVal > node.val && qVal > node.val) {
        // Both p and q are greater than the current node.
        node = node.right;
      } else if (pVal < node.val && qVal < node.val) {
        // Both p and q are lesser than the current node.
        node = node.left;
      } else {
        // pVal == node.val || qVal == node.val ||
        // pVal < node.val && qVal > node.val ||
        // pVal > node.val && qVal < node.val
        // We have found the split point, i.e. the LCA node.
        return node;
      }
    }

    return null;
  }



  // For testing.
  @SuppressWarnings("unused")
  public static void main(String[] args) {
    LowestCommonAncestorOfBinarySearchTree solution = new LowestCommonAncestorOfBinarySearchTree();

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


