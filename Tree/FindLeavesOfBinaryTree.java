package leetcode;

import java.util.ArrayList;
import java.util.List;

public class FindLeavesOfBinaryTree {
  // fields and classes here.
  // private int count;

  public FindLeavesOfBinaryTree() {
    // Initialization here.
    // this.count = 0;

  }

  // It's a little like a BFS with recursion.
  // Author: leetcode + kei
  // Date : October 25, 2021

  // (height, val)
  private List<List<Integer>> solution;

  public List<List<Integer>> findLeaves(TreeNode root) {
    this.solution = new ArrayList<>();
    getHeight(root);
    return solution;
  }

  private int getHeight(TreeNode root) {
    // return -1 for null nodes
    if (root == null) {
      return -1;
    }

    // first calculate the height of the left and right children
    int leftHeight = getHeight(root.left);
    int rightHeight = getHeight(root.right);

    int currHeight = Math.max(leftHeight, rightHeight) + 1;

    if (solution.size() == currHeight) {
      // The first time that we put a node in that height in solution.
      solution.add(new ArrayList<>());
    }

    solution.get(currHeight).add(root.val);

    return currHeight;
  }

  // For testing.
  @SuppressWarnings("unused")
  public static void main(String[] args) {
    FindLeavesOfBinaryTree solution = new FindLeavesOfBinaryTree();

    // Test arguments.
    // int num = 24;
    // int target = 2;
    // System.out.println(solution.getInt(num, target));

    System.out.println("\ndone.");
  }

}
