package leetcode;

import java.util.ArrayList;
import java.util.List;

public class StepByStepDirectionsFromBinaryTreeNodeToAnother {
  // fields and classes here.
  // private int count;

  public StepByStepDirectionsFromBinaryTreeNodeToAnother() {
    // Initialization here.
    // this.count = 0;

  }

  // Author: iceco + kei
  // Date : February 5, 2022
  public String getDirections(TreeNode root, int startValue, int destValue) {
    // Find LCA.
    TreeNode ancestor = findLowestCommonAncestor(root, startValue, destValue);

    List<String> toStart = new ArrayList<>();
    getDirection(ancestor, startValue, toStart);
    List<String> toDest = new ArrayList<>();
    getDirection(ancestor, destValue, toDest);

    StringBuilder sb = new StringBuilder();
    for (int i = 0; i < toStart.size(); i++) {
      sb.append("U");
    }
    for (String s : toDest) {
      sb.append(s);
    }
    return sb.toString();
  }

  // This is great!
  // Find Lowest Common Ancestor (LCA)
  private TreeNode findLowestCommonAncestor(TreeNode node, int p, int q) {
    if (node == null || node.val == p || node.val == q) {
      // No need to further explore this branch because
      // if the node is p or q, then the node can be the LCA even if there is
      // the other node in this brach.
      return node;
    }

    // Check child nodes.
    TreeNode left = findLowestCommonAncestor(node.left, p, q);
    TreeNode right = findLowestCommonAncestor(node.right, p, q);
    if (left != null && right != null) {
      // Return the node when both child nodes are not null.
      return node;
    }

    // Return not-null child node, and
    // return null if both nodes are null.
    return left != null ? left : right;
  }

  private boolean getDirection(TreeNode ancestor, int value, List<String> steps) {
    if (ancestor == null) {
      return false;
    }
    if (ancestor.val == value) {
      return true;
    }
    steps.add("L");
    if (getDirection(ancestor.left, value, steps)) {
      return true;
    }
    steps.remove(steps.size() - 1);
    steps.add("R");
    if (getDirection(ancestor.right, value, steps)) {
      return true;
    }
    steps.remove(steps.size() - 1);
    return false;
  }

  // For testing.
  @SuppressWarnings("unused")
  public static void main(String[] args) {
    StepByStepDirectionsFromBinaryTreeNodeToAnother solution = new StepByStepDirectionsFromBinaryTreeNodeToAnother();

    // Test arguments.
    // int num = 24;
    // int target = 2;
    // System.out.println(solution.getInt(num, target));

    System.out.println("\ndone.");
  }

}
