//
// Author:
// Date :

package leetcode;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

class SolutionOtherClasses {

  // For testing.
  public static void main(String[] args) {

    // Linked List.
    MyLinkedList list = new MyLinkedList();
    list.add(7);
    list.add(9);
    list.add(2);
    list.add(10);
    list.add(1);
    list.add(8);
    list.add(6);
    System.out.println(list.toString()); // [ 7 9 2 10 1 8 6 ]

    // Binary Tree
    // 1
    // \
    // 2
    // /
    // 3
    TreeNode n1 = new TreeNode(1);
    TreeNode n2 = new TreeNode(2);
    TreeNode n3 = new TreeNode(3);
    n1.right = n2;
    n2.left = n3;

    // Binary Search Tree
    // 6
    // / \
    // 3 12
    // / \ / \
    // 1 4 9 14
    TreeNode tn1 = new TreeNode(6);
    TreeNode tn2 = new TreeNode(3);
    TreeNode tn3 = new TreeNode(1);
    TreeNode tn4 = new TreeNode(4);
    TreeNode tn5 = new TreeNode(12);
    TreeNode tn6 = new TreeNode(9);
    TreeNode tn7 = new TreeNode(14);
    tn1.left = tn2;
    tn1.right = tn5;
    tn2.left = tn3;
    tn2.right = tn4;
    tn5.left = tn6;
    tn5.right = tn7;

    System.out.println("preorder: ");
    preorder(tn1);
    System.out.println();
    System.out.println("inorder: ");
    inorder(tn1);

  }

  // For test.
  private static void preorder(TreeNode node) {
    if (node == null) {
      return;
    }

    System.out.print(node.val);
    preorder(node.left);
    preorder(node.right);
  }

  private static void inorder(TreeNode node) {
    if (node == null) {
      return;
    }

    inorder(node.left);
    System.out.print(node.val);
    inorder(node.right);
  }

}

class ListNode {
  int val;
  ListNode next;

  ListNode(int x) {
    val = x;
  }

  public void add(ListNode node) {
    this.next = node;

  }

  @Override
  public String toString() {
    return Integer.toString(this.val);
  }
}

class DoublyListNode {
  int val;
  DoublyListNode next, prev;

  DoublyListNode(int x) {
    val = x;
  }
}

class MyLinkedList {
  ListNode head;
  ListNode tail;

  public MyLinkedList() {

  }

  public void add(int data) {
    ListNode newNode = new ListNode(data);
    if (head == null) {
      head = newNode;
      tail = newNode;
    } else {
      this.tail.next = newNode;
      tail = newNode;
    }
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    ListNode node = head;
    sb.append("[ ");
    while (node != null) {
      sb.append(node.toString());
      sb.append(" ");
      node = node.next;
    }
    sb.append("]");
    return sb.toString();
  }

}

class BinaryTree {
  TreeNode root;
}

class TreeNode {
  int val;
  TreeNode left;
  TreeNode right;

  TreeNode() {
  }

  TreeNode(int val) {
    this.val = val;
  }

  TreeNode(int val, TreeNode left, TreeNode right) {
    this.val = val;
    this.left = left;
    this.right = right;
  }
}

class TreeNodeC implements Comparable<TreeNodeC> {
  int val;
  int count;
  TreeNodeC left;
  TreeNodeC right;

  public TreeNodeC() {
  }

  public TreeNodeC(int data) {
    this.val = data;
    this.count = 1;
  }

  public void setLeft(TreeNodeC node) {
    left = node;
  }

  public void setRight(TreeNodeC node) {
    right = node;
  }

  @Override
  public int compareTo(TreeNodeC o) {
    if (this.val < o.val) {
      return -1;
    } else if (this.val == o.val) {
      return 0;
    } else {
      return 1;
    }
  }

  // Auto-generated.
  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + val;
    return result;
  }

  // Auto-generated.
  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;
    TreeNodeC other = (TreeNodeC) obj;
    if (val != other.val)
      return false;
    return true;
  }

  @Override
  public String toString() {
    return String.valueOf(val);
  }

}

// This is also in the javafx library.
class Pair {
  private String key;
  private int value;

  public Pair(String key, int value) {
    this.key = key;
    this.value = value;
  }

  public String getKey() {
    return key;
  }

  public int getValue() {
    return value;
  }

}

class PairGen<T1, T2> {
  private T1 key;
  private T2 value;

  public PairGen(T1 key, T2 value) {
    this.key = key;
    this.value = value;
  }

  public T1 getKey() {
    return key;
  }

  public T2 getValue() {
    return value;
  }

}

// Graph without weight.
class GraphNode {
  public int val;
  public List<GraphNode> neighbors;

  public GraphNode() {
  }

  public GraphNode(int val, List<GraphNode> neighbors) {
    this.val = val;
    this.neighbors = neighbors;
  }
}

// Graph without weight.
class Graph {
  int numNodes;
  LinkedList<Integer>[] adjacencyList;

  Graph(int numNodes) {
    this.numNodes = numNodes;
    // Contain n nodes indexed from 1.
    adjacencyList = new LinkedList[numNodes + 1];
    for (int i = 1; i < adjacencyList.length; i++) {
      adjacencyList[i] = new LinkedList<>();
    }
  }

  public void addEgde(int src, int dest) {
    adjacencyList[src].add(dest);
    adjacencyList[dest].add(src);
  }

  public void printGraph() {
    for (int i = 1; i < adjacencyList.length; i++) {
      LinkedList<Integer> list = adjacencyList[i];
      for (int j = 0; j < list.size(); j++) {
        System.out.println("" + i + " - " + list.get(j));
      }
    }
  }
}

// Graph with weight.
class Edge {
  int src;
  int dest;
  int weight;

  public Edge(int src, int dest, int weight) {
    this.src = src;
    this.dest = dest;
    this.weight = weight;
  }
}

class GraphWithWeight {
  int numNodes;
  LinkedList<Edge>[] adjacencyList;

  GraphWithWeight(int numNodes) {
    this.numNodes = numNodes;
    adjacencyList = new LinkedList[numNodes + 1];
    // Initialize adjacency lists for all the vertices.
    for (int i = 1; i < adjacencyList.length; i++) {
      adjacencyList[i] = new LinkedList<>();
    }
  }

  public void addEgde(int src, int dest, int weight) {
    Edge edge1 = new Edge(src, dest, weight);
    adjacencyList[src].addFirst(edge1);
    Edge edge2 = new Edge(dest, src, weight);
    adjacencyList[dest].addFirst(edge2);
  }

  public void printGraph() {
    for (int i = 1; i < adjacencyList.length; i++) {
      LinkedList<Edge> list = adjacencyList[i];
      for (int j = 0; j < list.size(); j++) {
        System.out.println("" + i + " - " + list.get(j).dest + " weight: " + list.get(j).weight);
      }
    }
  }
}

// Union Find (Disjoint Set)
class UF {
  public int[] par;

  public UF(int n) {
    par = new int[n];
    Arrays.fill(par, -1);
  }

  // Find root.
  public int find(int x) {
    if (par[x] < 0) {
      return x;
    }
    par[x] = find(par[x]);
    return par[x];
  }

  // Merge the two trees.
  // Return true if they are not in the same tree.
  public boolean union(int x, int y) {
    x = find(x);
    y = find(y);
    if (x != y) {
      // // Balance the tree to optimize the performance.
      // // Put the rank-like value in the parent of the root.
      // if (par[y] < par[x]) {
      // int d = x;
      // x = y;
      // y = d;
      // }
      // par[x] += par[y];

      // Merge tree y into x.
      par[y] = x;
    }
    return x != y;
  }

  // Check if the two nodes belong to the same tree.
  public boolean same(int x, int y) {
    return find(x) == find(y);
  }
}
