//
// Author:
// Date : May 28, 2020

package leetcode;

import java.util.LinkedList;
import java.util.Queue;

public class PopulatingNextRightPointersInEachNodeII {
  // fields and classes here.
  // private int count;

  public PopulatingNextRightPointersInEachNodeII() {
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
  }


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


  // O(N) time, O(N) space
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


  // O(N) time, O(1) space
  // Author: LeetCode + kei
  // Date : May 28, 2020

  Node prev, leftmost;

  public void processChild(Node childNode) {
    if (childNode == null) {
      return;
    }

    // If the "prev" pointer is already set i.e. if we
    // already found at least one node on the next level,
    // set up its next pointer.
    if (this.prev != null) {
      this.prev.next = childNode;
    } else {
      // Else it means this child node is the first node
      // we have encountered on the next level, so, we
      // set the leftmost pointer
      this.leftmost = childNode;
    }

    this.prev = childNode;
  }

  public Node connect3(Node root) {
    if (root == null) {
      return root;
    }

    // The root node is the only node on the first level
    // and hence it's the leftmost node for that level.
    this.leftmost = root;

    // Variable to keep track of leading node on the "current" level
    Node curr = leftmost;

    // We have no idea about the structure of the tree,
    // so, we keep going until we do find the last level.
    // The nodes on the last level won't have any children.
    while (this.leftmost != null) {
      // "prev" tracks the latest node on the "next" level
      // while "curr" tracks the latest node on the current
      // level.
      this.prev = null;
      curr = this.leftmost;

      // We reset this so that we can re-assign it to the leftmost
      // node of the next level. Also, if there isn't one, this
      // would help break us out of the outermost loop.
      this.leftmost = null;

      // Iterate on the nodes in the current level using
      // the next pointers already established by prev pointer.
      while (curr != null) {
        // Process both the children and update the prev
        // and leftmost pointers as necessary.
        this.processChild(curr.left);
        this.processChild(curr.right);

        // Move onto the next node.
        curr = curr.next;
      }
    }

    return root;
  }



  // For testing.
  @SuppressWarnings("unused")
  public static void main(String[] args) {
    PopulatingNextRightPointersInEachNodeII solution =
        new PopulatingNextRightPointersInEachNodeII();

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


