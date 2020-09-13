//
// Author:
// Date  : September 11, 2020

package leetcode;

import java.util.LinkedList;
import java.util.List;

public class UniqueBinarySearchTreesII {
  // fields and classes here.
  // private int count;

  public UniqueBinarySearchTreesII() {
    // Initialization here.
    // this.count = 0;

  }


  // Author: leetcode + kei 
  // Date  : September 11, 2020
  public List<TreeNode> generateTrees(int n) {
    if (n == 0) {
      // Return empty list. 
      return new LinkedList<TreeNode>();
    }
    return helper(1, n);
  }

  public LinkedList<TreeNode> helper(int start, int end) {
    LinkedList<TreeNode> allTrees = new LinkedList<TreeNode>();
    if (start > end) {
      allTrees.add(null);
      return allTrees;
    }

    // Pick up a root. 
    for (int i = start; i <= end; i++) {
      // All possible left subtrees if i is chosen to be a root. 
      LinkedList<TreeNode> leftTrees = helper(start, i - 1);

      // All possible right subtrees if i is chosen to be a root. 
      LinkedList<TreeNode> rightTrees = helper(i + 1, end);

      // Connect left and right subtrees to the root i. 
      // Note that leftTree and/or rightTree could be null. 
      for (TreeNode leftTree : leftTrees) {
        for (TreeNode rightTree : rightTrees) {
          TreeNode currentTree = new TreeNode(i);
          currentTree.left = leftTree;
          currentTree.right = rightTree;
          allTrees.add(currentTree);
        }
      }
    }

    return allTrees;
  }


  


  



  // For testing.
  @SuppressWarnings("unused")
  public static void main(String[] args) {
    UniqueBinarySearchTreesII solution = new UniqueBinarySearchTreesII();

    // Test arguments.
    // int num = 24;
    // int target = 2;
    // solution.getInt(num, target);



  }




}


