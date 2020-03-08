//
// Author:
// Date : June 1, 2019

package leetcode;

import java.util.LinkedList;
import java.util.Stack;


public class ValidateBinarySearchTree {
  // fields here.
  // private int count;

  public ValidateBinarySearchTree() {
    // Initialization here.
    // this.count = 0;
  }



  // For this problem, BST is a special binary tree that all the descendants
  // to the left of a node are less than the node, and all the descendants
  // to the right of the node are greater than the node.
  // (left < mid < right)

  // 1. DFS. Lower/Upper Approach (Recursive).
  // While traversing the tree, I'm going to keep track of the minimum and maximum for the current
  // node.
  // When going down left subtree, I update the maximum with the current node value.
  // When going down right subtree, I update the minimum with the current node value.
  //
  // O(N) time, since we visit each node exactly once.
  // O(H) space (O(logN) for balanced, O(N) for not balanced), where H is the height of the input
  // tree,
  // because of the recursion stack.
  public boolean isValidBST(TreeNode root) {
    return isValidBST(root, null, null);

    // This does not work when node.val has Integer.MIN_VALUE or Integer.MAX_VALUE!
    // return isValidBST(root, Integer.MIN_VALUE, Integer.MAX_VALUE);
  }

  private boolean isValidBST(TreeNode node, Integer lower, Integer upper) {
    if (node == null) {
      // Return true. Think about just one node case.
      // If the left and right child is null, then it should return true.
      return true;
    }

    // Preorder/DFS traversal because I can check validity as soon as I get to the node.
    // Check validity.
    // null check lower, not node. Include equal.
    // If lower(upper) is null, then any number is ok, including -2147483648(2147483647).
    if (lower != null && node.val <= lower) {
      return false;
    }
    if (upper != null && node.val >= upper) {
      return false;
    }

    // Update upper by current node value when going down left subtree.
    // Update lower by current node value when going down right subtree.
    // If the left subtree and the right subtree are BST, then return true.
    return isValidBST(node.left, lower, node.val) && isValidBST(node.right, node.val, upper);
  }


  // 2. DFS. Lower/Upper Approach (Iterative).
  // O(N) time, since we visit each node exactly once.
  // O(H) space (O(logN) for balanced, O(N) for not balanced), where H is the height of the input
  // tree,
  // because of the recursion stack.
  LinkedList<TreeNode> stackNodes = new LinkedList<>();
  LinkedList<Integer> stackMin = new LinkedList<>(), stackMax = new LinkedList<>();

  private void update(TreeNode node, Integer lower, Integer upper) {
    stackNodes.push(node);
    stackMin.push(lower);
    stackMax.push(upper);
  }

  public boolean isValidBST2(TreeNode node) {
    if (node == null) {
      return true;
    }

    Integer lower = null, upper = null;
    // NG!
    // Integer min = Integer.MIN_VALUE, max = Integer.MAX_VALUE;

    // Preorder/DFS traversal because I can check validity as soon as I get to the node.
    // Push the state first.
    update(node, lower, upper);
    while (!stackNodes.isEmpty()) {
      // Get the state.
      node = stackNodes.poll();
      lower = stackMin.poll();
      upper = stackMax.poll();

      // Check validity.
      // null check lower, not node. Include equal.
      // If lower(upper) is null, then any number is ok, including -2147483648(2147483647).
      if (lower != null && node.val <= lower) {
        return false;
      }
      if (upper != null && node.val >= upper) {
        return false;
      }

      if (node.right != null) {
        update(node.right, node.val, upper);
      }
      if (node.left != null) {
        update(node.left, lower, node.val);
      }

    }

    return true;
  }


  // 3. Inorder Approach (Iterative).
  // Inorder traversal of BST gives nodes in non-decreasing order, in this problem,
  // an increasing order.
  // I need to store the previous value, so I'm going to use iterative inorder traversal.
  // Inorder traversal recursive might not be possible because we cannot store the
  // previous value when we go back to the previous recursion stack.
  // O(N) time, since we visit each node exactly once.
  // O(H) space (O(logN) for balanced, O(N) for not balanced), where H is the height of the input
  // tree,
  // because of the stack size.
  public boolean isValidBST3(TreeNode node) {
    LinkedList<TreeNode> stack = new LinkedList<>();
    Integer prev = null;

    // Inorder Traversal.
    while (!stack.isEmpty() || node != null) {
      while (node != null) {
        stack.push(node);
        node = node.left;
      }

      // Note that you do not need to create a new TreeNode here.
      node = stack.poll();
      // Check validity.
      // If prev is null, any number is ok including -2147483648.
      if (prev != null && node.val <= prev) {
        return false;
      }

      prev = node.val;
      node = node.right;
    }

    return true;
  }


  // Review
  // Recursive. Accepted.
  public boolean isValidBSTRecurR(TreeNode node) {
    // corner, node: null, 1 node => ok.

    // Pass in nulls in order to validate leftmost and rightmost node.
    return isValidBSTRecurR(node, null, null);
  }

  private boolean isValidBSTRecurR(TreeNode node, Integer lower, Integer upper) {
    if (node == null) {
      return true;
    }

    // Check validity.
    if (lower != null && node.val <= lower) {
      return false;
    }
    if (upper != null && node.val >= upper) {
      return false;
    }

    return isValidBSTRecurR(node.left, lower, node.val)
        && isValidBSTRecurR(node.right, node.val, upper);
  }


  // Review. DFS. Iterative. Accepted.
  Stack<TreeNode> stackNode = new Stack<>();
  Stack<Integer> stackLower = new Stack<>(), stackUpper = new Stack<>();

  private void pushState(TreeNode node, Integer lower, Integer upper) {
    stackNode.push(node);
    stackLower.push(lower);
    stackUpper.push(upper);
  }

  public boolean isValidBSTIterR(TreeNode root) {
    // corner.
    if (root == null) {
      return true;
    }

    pushState(root, null, null);
    while (!stackNode.isEmpty()) {
      TreeNode node = stackNode.pop();
      Integer lower = stackLower.pop();
      Integer upper = stackUpper.pop();

      // Check validity.
      if (lower != null && node.val <= lower) {
        return false;
      }

      if (upper != null && node.val >= upper) {
        return false;
      }


      if (node.right != null) {
        pushState(node.right, node.val, upper);
      }

      if (node.left != null) {
        pushState(node.left, lower, node.val);
      }
    }

    return true;
  }

  // Review 2.
  public boolean isValidBstDfsRecurR2(TreeNode root) {
    // corner.
    // if (root == null) {
    // return false;
    // }

    return isValidBstDfsRecurR2(root, null, null);
  }

  private boolean isValidBstDfsRecurR2(TreeNode node, Integer min, Integer max) {
    if (node == null) {
      return true;
    }

    if (min != null && node.val <= min) {
      return false;
    }
    if (max != null && node.val >= max) {
      return false;
    }

    return isValidBstDfsRecurR2(node.left, min, node.val)
        && isValidBstDfsRecurR2(node.right, node.val, max);
  }


  // Review 2. DFS. Iterative.
  public boolean isValidBstDfsIterR2(TreeNode root) {
    if (root == null) {
      return true;
    }

    Stack<TreeNode> stackNode = new Stack<>();
    Stack<Integer> stackMin = new Stack<>();
    Stack<Integer> stackMax = new Stack<>();

    stackNode.push(root);
    stackMin.push(null);
    stackMax.push(null);

    while (!stackNode.isEmpty()) {
      TreeNode node = stackNode.pop();
      Integer min = stackMin.pop();
      Integer max = stackMax.pop();

      if (min != null && node.val <= min) {
        return false;
      }
      if (max != null && node.val >= max) {
        return false;
      }

      if (node.right != null) {
        stackNode.push(node.right);
        stackMin.push(node.val);
        stackMax.push(max);
      }
      if (node.left != null) {
        stackNode.push(node.left);
        stackMin.push(min);
        stackMax.push(node.val);
      }
    }

    return true;
  }


  // Review 2. Inorder. Iterative.
  public boolean isValidBstInorderR2(TreeNode node) {
    // corner. => ok.

    Stack<TreeNode> stack = new Stack<>();
    Integer prev = null;
    while (!stack.isEmpty() || node != null) {
      while (node != null) {
        stack.push(node);
        node = node.left;
      }
      node = stack.pop();
      if (prev != null && node.val <= prev) {
        return false;
      }
      prev = node.val;
      node = node.right;
    }

    return true;
  }



  // For testing.
  public static void main(String[] args) {
    ValidateBinarySearchTree solution = new ValidateBinarySearchTree();

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
    BinaryTree bst = new BinaryTree();
    TreeNode n1 = new TreeNode(6);
    TreeNode n2 = new TreeNode(3);
    TreeNode n3 = new TreeNode(1);
    TreeNode n4 = new TreeNode(4);
    TreeNode n5 = new TreeNode(12);
    TreeNode n6 = new TreeNode(9);
    TreeNode n7 = new TreeNode(14);

    bst.root = n1;
    n1.setLeft(n2);
    n1.setRight(n5);
    n2.setLeft(n3);
    n2.setRight(n4);
    n5.setLeft(n6);
    n5.setRight(n7);

    boolean ret = solution.isValidBST(bst.root);
    System.out.println(ret); // true

    // This is not a Binary Search Tree.
    // 6
    // / \
    // 3 12
    // / \ / \
    // 1 7 9 14
    BinaryTree notBst = new BinaryTree();
    notBst.root = n1;
    TreeNode n8 = new TreeNode(7);
    n1.setLeft(n2);
    n1.setRight(n5);
    n2.setLeft(n3);
    n2.setRight(n8);
    n5.setLeft(n6);
    n5.setRight(n7);

    ret = solution.isValidBST(notBst.root);
    System.out.println(ret); // false


    // TreeNode n9 = new TreeNode(1);
    // TreeNode n10 = new TreeNode(1);
    // n9.left = n10;
    // System.out.println(solution.isValidBST4(n9));



  }

}


