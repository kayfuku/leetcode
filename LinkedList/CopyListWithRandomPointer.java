//
// Author:
// Date : May 26, 2020

package leetcode;

import java.util.HashMap;

public class CopyListWithRandomPointer {
  // fields and classes here.
  // private int count;

  public CopyListWithRandomPointer() {
    // Initialization here.
    // this.count = 0;

  }

  class Node {
    int val;
    Node next;
    Node random;

    public Node() {}

    public Node(int val) {
      this.val = val;
      this.next = null;
      this.random = null;
    }

    public Node(int val, Node next, Node random) {
      this.val = val;
      this.next = next;
      this.random = random;
    }
  }


  // 1. Recursive
  // DFS like a graph
  // O(N) time, O(N) space
  // Author: LeetCode + kei
  // Date : May 26, 2020

  // HashMap which holds old nodes as keys and new nodes as its values.
  // K: old node, V: new node
  HashMap<Node, Node> visitedHash = new HashMap<Node, Node>();

  public Node copyRandomList(Node head) {
    if (head == null) {
      return null;
    }

    // If we have already processed the current node,
    // then we simply return the cloned version of it.
    if (this.visitedHash.containsKey(head)) {
      return this.visitedHash.get(head);
    }

    // Create a new node with the value same as old node. (i.e. copy the node)
    Node node = new Node(head.val, null, null);

    // Save this value in the hash map. This is needed since there might be
    // loops during traversal due to randomness of random pointers and
    // this would help us avoid them.
    this.visitedHash.put(head, node);

    // Recursively copy the remaining linked list starting once from the next pointer
    // and then from the random pointer.
    // Thus we have two independent recursive calls.
    // Finally we update the next and random pointers for the new node created.
    node.next = this.copyRandomList(head.next);
    node.random = this.copyRandomList(head.random);

    return node;
  }


  // 2. Iterative
  // O(N) time, O(N) space
  // Author: LeetCode + kei
  // Date : May 26, 2020

  // Visited dictionary to hold old node reference as "key" and new node reference as the "value"
  // K: old node, V: new node
  HashMap<Node, Node> visited = new HashMap<Node, Node>();

  public Node getClonedNode(Node node) {
    if (node == null) {
      return null;
    }

    if (this.visited.containsKey(node)) {
      return this.visited.get(node);
    }

    // Create a new node and add it to the dictionary.
    this.visited.put(node, new Node(node.val, null, null));

    return this.visited.get(node);
  }

  public Node copyRandomListIter(Node head) {
    if (head == null) {
      return null;
    }

    Node oldNode = head;

    // Creating the new head node.
    Node newNode = new Node(oldNode.val);
    this.visited.put(oldNode, newNode);

    // Iterate on the linked list until all nodes are cloned.
    while (oldNode != null) {
      // Get the clones of the nodes referenced by random and next pointers.
      newNode.random = this.getClonedNode(oldNode.random);
      newNode.next = this.getClonedNode(oldNode.next);

      // Move one step ahead in the linked list.
      oldNode = oldNode.next;
      newNode = newNode.next;
    }

    return this.visited.get(head);
  }


  // 3. Optional (Not review yet)
  // O(N) time, O(1) space.
  public Node copyRandomListOp(Node head) {

    if (head == null) {
      return null;
    }

    // Creating a new woven list of original and copied nodes.
    Node ptr = head;
    while (ptr != null) {

      // Cloned node
      Node newNode = new Node(ptr.val);

      // Inserting the cloned node just next to the original node.
      // If A->B->C is the original linked list,
      // Linked list after weaving cloned nodes would be A->A'->B->B'->C->C'
      newNode.next = ptr.next;
      ptr.next = newNode;
      ptr = newNode.next;
    }

    ptr = head;

    // Now link the random pointers of the new nodes created.
    // Iterate the newly created list and use the original nodes' random pointers,
    // to assign references to random pointers for cloned nodes.
    while (ptr != null) {
      ptr.next.random = (ptr.random != null) ? ptr.random.next : null;
      ptr = ptr.next.next;
    }

    // Unweave the linked list to get back the original linked list and the cloned list.
    // i.e. A->A'->B->B'->C->C' would be broken to A->B->C and A'->B'->C'
    Node ptr_old_list = head; // A->B->C
    Node ptr_new_list = head.next; // A'->B'->C'
    Node head_old = head.next;
    while (ptr_old_list != null) {
      ptr_old_list.next = ptr_old_list.next.next;
      ptr_new_list.next = (ptr_new_list.next != null) ? ptr_new_list.next.next : null;
      ptr_old_list = ptr_old_list.next;
      ptr_new_list = ptr_new_list.next;
    }
    return head_old;
  }


  // For testing.
  @SuppressWarnings("unused")
  public static void main(String[] args) {
    CopyListWithRandomPointer solution = new CopyListWithRandomPointer();

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


