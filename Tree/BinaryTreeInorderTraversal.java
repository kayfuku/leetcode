//
// Author:
// Date : June 1, 2019

package leetcode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

public class BinaryTreeInorderTraversal {
  // fields here.
  // private int count;

  public BinaryTreeInorderTraversal() {
    // Initialization here.
    // this.count = 0;
  }


  // 1. Inorder traversal recursive version.
  // O(N) time, O(N) space, where N is the total number of nodes.
  public List<Integer> inorderTraversal(TreeNode root) {
    List<Integer> list = new ArrayList<>();
    inorderTraversal(root, list);
    return list;
  }

  private void inorderTraversal(TreeNode node, List<Integer> list) {
    if (node == null) {
      return;
    }

    // In-order traversal
    inorderTraversal(node.left, list);
    // Do something.
    list.add(node.val);
    inorderTraversal(node.right, list);
  }



  // Iterative.
  //
  // 2. Inorder traversal iterative version.
  // Push the nodes to the stack until the left child is null.
  // Then pop the node from the stack until the right child is not null.
  // If the right child is not null, then
  // push the nodes of the right subtree to the stack until
  // the left child is null.
  // O(N) time, O(N) space, where N is the total number of nodes.
  // Author: LeetCode + kei
  // Date : June 1, 2019
  public List<Integer> inorderTraversalR2(TreeNode root) {
    // corner. root: null, 1 node. => ok.
    List<Integer> list = new ArrayList<>();
    TreeNode cur = root;
    Stack<TreeNode> stack = new Stack<>();
    // If the stack is not empty, there should be a mid node to visit.
    // Or if the tree only has a right subtree, then the stack is going to be
    // empty at the root node, but the root node has a right child, so
    // I have to keep traversing (cur != null).
    // Unlike normal DFS, no need to push the root node in the stack before the while loop.
    while (!stack.isEmpty() || cur != null) {
      // 1. Keep pushing left child until it gets to null.
      // 3. Push the right child in the stack.
      while (cur != null) {
        stack.push(cur);
        cur = cur.left;
      }

      // 2. If it gets to null of left subtree, then pop the node from the
      // stack. That is the node to be printed or processed now.
      // Then, check the right node.
      // If the right child is null, go back to the previous node (pop the node again),
      // which is mid node.
      // If the right child is not null, then go to step 3 and 1 again.
      cur = stack.pop();
      // Do something. After that, check the right child.
      list.add(cur.val);
      cur = cur.right;
    }

    return list;
  }


  // 2. Inorder traversal iterative version.
  // Push the nodes to the stack until the left child is null.
  // Then pop the node from the stack until the right child is not null.
  // If the right child is not null, then
  // push the nodes of the right subtree to the stack until
  // the left child is null.
  // O(N) time, O(N) space, where N is the total number of nodes.
  public List<Integer> inorderTraversal2(TreeNode root) {
    TreeNode cur = root;
    LinkedList<TreeNode> stack = new LinkedList<>();
    List<Integer> ans = new ArrayList<>();
    while (cur != null || !stack.isEmpty()) {

      while (cur != null) {
        stack.push(cur);
        cur = cur.left;
      }

      cur = stack.pop();
      ans.add(cur.val);
      cur = cur.right;
    }

    return ans;
  }


  // Review
  // Recursive.
  public List<Integer> inorderTraversalR(TreeNode root) {
    List<Integer> list = new ArrayList<>();
    // corner
    inorderTraversalR(root, list);
    return list;
  }

  private void inorderTraversalR(TreeNode node, List<Integer> list) {
    if (node == null) {
      return;
    }

    inorderTraversalR(node.left, list);
    list.add(node.val);
    inorderTraversalR(node.right, list);
  }



  // For testing.
  public static void main(String[] args) {
    BinaryTreeInorderTraversal solution = new BinaryTreeInorderTraversal();

    // Test arguments.
    // int num = 24;
    // int target = 2;
    // solution.getInt(num, target);

    // Binary Tree
    // 1
    // \
    // 2
    // /
    // 3
    BinaryTree binaryTree = new BinaryTree();
    TreeNode n1 = new TreeNode(1);
    TreeNode n2 = new TreeNode(2);
    TreeNode n3 = new TreeNode(3);

    binaryTree.root = n1;
    n1.setRight(n2);
    n2.setLeft(n3);

    List<Integer> ret = solution.inorderTraversal(binaryTree.root);
    System.out.println(ret.toString()); // [1, 3, 2]
    List<Integer> ret2 = solution.inorderTraversal2(binaryTree.root);
    System.out.println(ret2.toString()); // [1, 3, 2]



  }

}


