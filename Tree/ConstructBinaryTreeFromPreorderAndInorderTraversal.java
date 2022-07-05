//
// Author:
// Date : June 22, 2019

package leetcode;

import java.util.HashMap;
import java.util.Map;

public class ConstructBinaryTreeFromPreorderAndInorderTraversal {
  // fields here.
  // private int count;

  public ConstructBinaryTreeFromPreorderAndInorderTraversal() {
    // Initialization here.
    // this.count = 0;
  }

  // Pre-order always starts from root, but for in-order, every node could be a
  // root node.
  // If I find which is the root in in-order, then I can learn that every node to
  // the left of
  // the root is in the left subtree because that is the property of in-order.
  // In conclusion, use preorder to find the next root node, and use inorder
  // to figure out which nodes are in the left subtree or right subtree.
  // Author: LeetCode + kei
  // Data : June 22, 2019
  // O(N) time, where N is the total number of nodes.
  // O(logN) space for balanced, O(N) space for not balanced.

  int preRoot;
  int[] preorder;
  int[] inorder;
  Map<Integer, Integer> inMap;

  public TreeNode buildTree(int[] preorder, int[] inorder) {
    this.preorder = preorder;
    this.inorder = inorder;
    // The leftmost elem in the postorder is always the root node.
    preRoot = 0;

    // To find a root in in-order.
    // K: element in inorder array, V: index of the element
    inMap = new HashMap<>();
    for (int i = 0; i < inorder.length; i++) {
      inMap.put(inorder[i], i);
    }

    return buildTree(0, inorder.length - 1);
  }

  private TreeNode buildTree(int inStart, int inEnd) {
    // Note that we have to keep going when inStart == inEnd (just one elem).
    if (inStart > inEnd) {
      return null;
    }

    // Get the root node value using preorder.
    TreeNode node = new TreeNode(preorder[preRoot]);
    // Get the index of the root node in inorder.
    int inRoot = inMap.get(node.val);

    // Points to the next root node.
    preRoot++;
    // Note that left subtree first, then right subtree
    // because preRoot points to the root node in this order.
    // Build left subtree.
    node.left = buildTree(inStart, inRoot - 1);
    // Build right subtree.
    node.right = buildTree(inRoot + 1, inEnd);

    return node;
  }

  // For testing.
  @SuppressWarnings("unused")
  public static void main(String[] args) {
    ConstructBinaryTreeFromPreorderAndInorderTraversal solution = new ConstructBinaryTreeFromPreorderAndInorderTraversal();

    // Test arguments.
    // int num = 24;
    // int target = 2;
    // solution.getInt(num, target);

  }

}
