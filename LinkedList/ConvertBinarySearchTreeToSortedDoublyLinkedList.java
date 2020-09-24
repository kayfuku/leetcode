//
// Author:
// Date  : September 23, 2020

package leetcode;

public class ConvertBinarySearchTreeToSortedDoublyLinkedList {
  // fields and classes here.
  // private int count;

  public ConvertBinarySearchTreeToSortedDoublyLinkedList() {
    // Initialization here.
    // this.count = 0;

  }

  // Definition for a Node.
  class Node {
    public int val;
    public Node left;
    public Node right;

    public Node() {
    }

    public Node(int val) {
      this.val = val;
    }

    public Node(int val, Node left, Node right) {
      this.val = val;
      this.left = left;
      this.right = right;
    }
  }

  // Author: leetcode + kei
  // Date : September 23, 2020

  // DLL type
  // the smallest (first) and the largest (last) nodes
  Node first = null;
  Node last = null;

  public Node treeToDoublyList(Node root) {
    if (root == null) {
      return null;
    }

    helper(root);

    // Close DLL.
    last.right = first;
    first.left = last;

    return first;
  }

  public void helper(Node node) {
    if (node == null) {
      return;
    }

    // In-order traversal
    helper(node.left);
    if (last != null) {
      // Link the previous node (last)
      // with the current one (node)
      last.right = node;
      node.left = last;
    } else {
      // Keep the smallest node to close DLL later on.
      first = node;
    }
    // last is supposed to point to the last node.
    last = node;

    helper(node.right);
  }

  // For testing.
  @SuppressWarnings("unused")
  public static void main(String[] args) {
    ConvertBinarySearchTreeToSortedDoublyLinkedList solution = new ConvertBinarySearchTreeToSortedDoublyLinkedList();

    // Test arguments.
    // int num = 24;
    // int target = 2;
    // solution.getInt(num, target);

  }

}
