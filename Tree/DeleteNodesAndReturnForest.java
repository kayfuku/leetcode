package leetcode;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class DeleteNodesAndReturnForest {
  // fields and classes here.
  // private int count;

  public DeleteNodesAndReturnForest() {
    // Initialization here.
    // this.count = 0;

  }

  // Author: lee215 + kei
  // Date : October 11, 2021
  Set<Integer> toDeleteSet;
  List<TreeNode> res;

  public List<TreeNode> delNodes(TreeNode root, int[] toDelete) {
    toDeleteSet = new HashSet<>();
    res = new ArrayList<>();
    for (int i : toDelete) {
      toDeleteSet.add(i);
    }
    helper(root, true);
    return res;
  }

  private TreeNode helper(TreeNode node, boolean isRoot) {
    if (node == null) {
      return null;
    }
    boolean deleted = toDeleteSet.contains(node.val);
    // We just need to add root nodes.
    if (isRoot && !deleted) {
      res.add(node);
    }
    // If the node is deleted, then child nodes become root nodes.
    node.left = helper(node.left, deleted);
    node.right = helper(node.right, deleted);

    return deleted ? null : node;
  }

  // For testing.
  @SuppressWarnings("unused")
  public static void main(String[] args) {
    DeleteNodesAndReturnForest solution = new DeleteNodesAndReturnForest();

    // Test arguments.
    // int num = 24;
    // int target = 2;
    // System.out.println(solution.getInt(num, target));
    TreeNode n1 = new TreeNode(1);
    TreeNode n2 = new TreeNode(2);
    TreeNode n3 = new TreeNode(3);
    TreeNode n4 = new TreeNode(4);
    TreeNode n5 = new TreeNode(5);
    TreeNode n6 = new TreeNode(6);
    TreeNode n7 = new TreeNode(7);
    n1.left = n2;
    n1.right = n3;
    n2.left = n4;
    n2.right = n5;
    n3.left = n6;
    n3.right = n7;
    int[] toDelete = { 3, 5 };
    List<TreeNode> res = solution.delNodes(n1, toDelete);
    for (TreeNode treeNode : res) {
      System.out.print(treeNode.val + " ");
    } // 1 6 7

    System.out.println("\ndone.");
  }

}
