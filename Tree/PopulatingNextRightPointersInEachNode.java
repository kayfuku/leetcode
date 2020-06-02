//
// Author:
// Date : May 27, 2020

package leetcode;

import java.util.LinkedList;
import java.util.Queue;

public class PopulatingNextRightPointersInEachNode {
  // fields and classes here.
  // private int count;

  public PopulatingNextRightPointersInEachNode() {
    // Initialization here.
    // this.count = 0;

  }

  class Node {
    public int val;
    public Node left;
    public Node right;
    public Node next;

    public Node() {}

    public Node(int _val) {
      val = _val;
    }

    public Node(int _val, Node _left, Node _right, Node _next) {
      val = _val;
      left = _left;
      right = _right;
      next = _next;
    }

    public String toString() {
      return String.valueOf(val);
    }
  };


  // Author: kei (AC)
  // Date : May 28, 2020
  public Node connect(Node root) {
    if (root == null) {
      return null;
    }

    Queue<Node> queue = new LinkedList<>();
    queue.add(root);
    while (!queue.isEmpty()) {
      int size = queue.size();
      Node prev = null;
      for (int i = 0; i < size; i++) {
        Node curr = queue.poll();
        curr.next = prev;
        if (curr.right != null) {
          queue.add(curr.right);
        }
        if (curr.left != null) {
          queue.add(curr.left);
        }
        prev = curr;
      }
    }

    return root;
  }


  // Author: LeetCode + kei
  // Date : May 28, 2020
  public Node connect2(Node root) {
    if (root == null) {
      return root;
    }

    // Initialize a queue data structure which contains
    // just the root of the tree
    Queue<Node> Q = new LinkedList<Node>();
    Q.add(root);

    // Outer while loop which iterates over
    // each level
    while (Q.size() > 0) {

      // Note the size of the queue
      int size = Q.size();

      // Iterate over all the nodes on the current level
      for (int i = 0; i < size; i++) {

        // Pop a node from the front of the queue
        Node node = Q.poll();

        // This check is important. We don't want to
        // establish any wrong connections. The queue will
        // contain nodes from 2 levels at most at any
        // point in time. This check ensures we only
        // don't establish next pointers beyond the end
        // of a level
        if (i < size - 1) {
          node.next = Q.peek();
        }

        // Add the children, if any, to the back of
        // the queue
        if (node.left != null) {
          Q.add(node.left);
        }
        if (node.right != null) {
          Q.add(node.right);
        }
      }
    }

    // Since the tree has now been modified, return the root node
    return root;
  }


  // Author: LeetCode + kei
  // Date : May 28, 2020
  public Node connect3(Node root) {
    if (root == null) {
      return root;
    }

    // Start with the root node. There are no next pointers
    // that need to be set up on the first level
    Node leftmost = root;

    // Once we reach the final level, we are done
    while (leftmost.left != null) {

      // Iterate the "linked list" starting from the head
      // node and using the next pointers, establish the
      // corresponding links for the next level
      Node head = leftmost;

      while (head != null) {

        // CONNECTION 1
        head.left.next = head.right;

        // CONNECTION 2
        if (head.next != null) {
          head.right.next = head.next.left;
        }

        // Progress along the list (nodes on the current level)
        head = head.next;
      }

      // Move onto the next level
      leftmost = leftmost.left;
    }

    return root;
  }


  // For testing.
  @SuppressWarnings("unused")
  public static void main(String[] args) {
    PopulatingNextRightPointersInEachNode solution = new PopulatingNextRightPointersInEachNode();

    // Test arguments.
    // int num = 24;
    // int target = 2;
    // solution.getInt(num, target);
    Node n1 = solution.new Node(1);
    Node n2 = solution.new Node(2);
    Node n3 = solution.new Node(3);
    Node n4 = solution.new Node(4);
    Node n5 = solution.new Node(5);
    Node n6 = solution.new Node(6);
    Node n7 = solution.new Node(7);

    n1.left = n2;
    n1.right = n3;
    n2.left = n4;
    n2.right = n5;
    n3.left = n6;
    n3.right = n7;

    solution.connect(n1);



  }



  public void dummyMethod() {



  }



  public void dummyMethod2() {



  }



}


