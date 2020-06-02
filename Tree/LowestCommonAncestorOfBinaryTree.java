//
// Author:
// Date : May 30, 2020

package leetcode;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.Stack;

public class LowestCommonAncestorOfBinaryTree {
  // fields and classes here.
  // private int count;

  public LowestCommonAncestorOfBinaryTree() {
    // Initialization here.
    // this.count = 0;

  }


  // 1. Recursive
  // Author: LeetCode + kei
  // Date : May 30, 2020

  private TreeNode lcaNode;

  public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
    // Traverse the tree
    this.recurseTree(root, p, q);
    return this.lcaNode;
  }

  private boolean recurseTree(TreeNode node, TreeNode p, TreeNode q) {
    if (node == null) {
      return false;
    }

    // Post-order traversal
    // Left Recursion. If left recursion returns True, set left = 1 else 0.
    int left = this.recurseTree(node.left, p, q) ? 1 : 0;
    // Right Recursion
    int right = this.recurseTree(node.right, p, q) ? 1 : 0;
    // If the current node is one of p or q
    int mid = (node == p || node == q) ? 1 : 0;

    // If any two of the flags, left, right or mid, become True
    if (mid + left + right >= 2) {
      this.lcaNode = node;
    }

    // Return true if any one of the three boolean values is True.
    return mid + left + right > 0;
  }


  // 2. Iterative using parent pointers.
  public TreeNode lowestCommonAncestor2(TreeNode root, TreeNode p, TreeNode q) {
    // Stack for tree traversal
    Deque<TreeNode> stack = new ArrayDeque<>();

    // HashMap for parent pointers
    // K: node, V: parent node
    Map<TreeNode, TreeNode> parent = new HashMap<>();
    // The parent node of the root is null.
    parent.put(root, null);

    stack.push(root);

    // Populate the parent map.
    // Iterate until we find both the nodes p and q
    while (!parent.containsKey(p) || !parent.containsKey(q)) {
      TreeNode node = stack.pop();

      if (node.left != null) {
        // While traversing the tree, keep saving the parent pointers.
        parent.put(node.left, node);
        stack.push(node.left);
      }
      if (node.right != null) {
        // While traversing the tree, keep saving the parent pointers.
        parent.put(node.right, node);
        stack.push(node.right);
      }
    }

    // Ancestors set for node p.
    Set<TreeNode> ancestorsForP = new HashSet<>();

    // Put all ancestors for node p including p itself in the set using parent pointers.
    while (p != null) {
      ancestorsForP.add(p);
      // Move up.
      p = parent.get(p);
    }

    // The first ancestor of q which appears in
    // p's ancestor set is their lowest common ancestor.
    while (!ancestorsForP.contains(q)) {
      q = parent.get(q);
    }

    return q;
  }


  // Not review yet.
  // 3. Iterative without parent pointers
  // O(N) time, O(N) space

  // Three static flags to keep track of post-order traversal.

  // Both left and right traversal pending for a node.
  // Indicates the nodes children are yet to be traversed.
  private static int BOTH_PENDING = 2;

  // Left traversal done.
  private static int LEFT_DONE = 1;

  // Both left and right traversal done for a node.
  // Indicates the node can be popped off the stack.
  private static int BOTH_DONE = 0;

  public TreeNode lowestCommonAncestor3(TreeNode root, TreeNode p, TreeNode q) {

    Stack<PairGen<TreeNode, Integer>> stack = new Stack<PairGen<TreeNode, Integer>>();

    // Initialize the stack with the root node.
    stack.push(new PairGen<TreeNode, Integer>(root, LowestCommonAncestorOfBinaryTree.BOTH_PENDING));

    // This flag is set when either one of p or q is found.
    boolean oneNodeFound = false;

    // This is used to keep track of the LCA.
    TreeNode LCA = null;

    // Child node
    TreeNode childNode = null;

    // We do a post order traversal of the binary tree using stack
    while (!stack.isEmpty()) {

      PairGen<TreeNode, Integer> top = stack.peek();
      TreeNode parentNode = top.getKey();
      int parentState = top.getValue();

      // If the parent_state is not equal to BOTH_DONE,
      // this means the parent_node can't be popped off yet.
      if (parentState != LowestCommonAncestorOfBinaryTree.BOTH_DONE) {

        // If both child traversals are pending
        if (parentState == LowestCommonAncestorOfBinaryTree.BOTH_PENDING) {

          // Check if the current parent_node is either p or q.
          if (parentNode == p || parentNode == q) {

            // If one_node_found was set already, this means we have found
            // both the nodes.
            if (oneNodeFound) {
              return LCA;
            } else {
              // Otherwise, set one_node_found to True,
              // to mark one of p and q is found.
              oneNodeFound = true;

              // Save the current top element of stack as the LCA.
              LCA = stack.peek().getKey();
            }
          }

          // If both pending, traverse the left child first
          childNode = parentNode.left;
        } else {
          // traverse right child
          childNode = parentNode.right;
        }

        // Update the node state at the top of the stack
        // Since we have visited one more child.
        stack.pop();
        stack.push(new PairGen<TreeNode, Integer>(parentNode, parentState - 1));

        // Add the child node to the stack for traversal.
        if (childNode != null) {
          stack.push(new PairGen<TreeNode, Integer>(childNode,
              LowestCommonAncestorOfBinaryTree.BOTH_PENDING));
        }

      } else {

        // If the parent_state of the node is both done,
        // the top node could be popped off the stack.
        // Update the LCA node to be the next top node.
        if (LCA == stack.pop().getKey() && oneNodeFound) {
          LCA = stack.peek().getKey();
        }

      }
    }

    return null;
  }



  // For testing.
  @SuppressWarnings("unused")
  public static void main(String[] args) {
    LowestCommonAncestorOfBinaryTree solution = new LowestCommonAncestorOfBinaryTree();

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


