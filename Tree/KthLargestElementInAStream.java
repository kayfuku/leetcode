//
// Author:
// Date : June 27, 2019

package leetcode;

import java.util.PriorityQueue;

public class KthLargestElementInAStream {
  // fields here.
  // private int count;

  public KthLargestElementInAStream() {
    // Initialization here.
    // this.count = 0;
  }

  // 1. Using Priority Queue (Min-Heap).
  // O(NlogK), where N is the total number of elements in the nums, and
  // K is as in the K-th largest.
  class PqVersion {
    PriorityQueue<Integer> minHeap;
    int k;

    // Build. O(NlogK) time.
    public PqVersion(int k, int[] nums) {
      this.k = k;

      // Build Min-Heap. O(NlogK) time.
      // Use Min-Heap because we continue throwing away the minimum to keep
      // k largest numbers by that time while streaming.
      minHeap = new PriorityQueue<>(k);
      // After this finishes, only k largest numbers are in the heap.
      // because every time the size exceeds k, we take out the minimum.
      for (int num : nums) {
        add(num);
      }
    }

    // Query. O(logK) time.
    // Return the k-th largest number.
    public int add(int val) {
      minHeap.offer(val);
      // Keep only the k largest numbers in the heap.
      if (minHeap.size() > k) {
        // Take out the minimum so far.
        minHeap.poll();
      }

      return minHeap.peek();
    }
  }

  // 2. Using BST comprised of special tree nodes that have the number of nodes
  // under the node as a root. (No need to review)
  // H is the height of tree with the average and best time O(logN) and worst time
  // O(N),
  // where N is the total number of nodes in the BST.
  // Author: LeetCode + kei
  // Date : June 9, 2020
  class KthLargest {
    // O(H) time
    private Node insertNode(Node node, int num) {
      if (node == null) {
        return new Node(num, 1);
      }
      if (num < node.val) {
        node.left = insertNode(node.left, num);
      } else {
        node.right = insertNode(node.right, num);
      }
      node.cnt++;
      return node;
    }

    // O(H) time
    private int searchKth(Node node, int k) {
      // m: size of right subtree
      int m = node.right != null ? node.right.cnt : 0;
      // The current node is the (m + 1)th largest node in the BST.
      if (k == m + 1) {
        return node.val;
      }
      if (k <= m) {
        // Find k-th largest in the right subtree.
        return searchKth(node.right, k);
      } else {
        // Find (k - (m + 1))th largest in the left subtree.
        return searchKth(node.left, k - m - 1);
      }
    }

    private Node root;
    private int k;

    // O(NH) time
    public KthLargest(int k, int[] nums) {
      for (int i = 0; i < nums.length; ++i) {
        this.root = insertNode(this.root, nums[i]);
      }
      this.k = k;
    }

    // O(H) time
    public int add(int val) {
      root = insertNode(root, val);
      return searchKth(root, k);
    }
  }

  class Node {
    Node left;
    Node right;
    int val;
    int cnt; // The size of the subtree rooted at this node

    public Node(int v, int c) {
      left = null;
      right = null;
      val = v;
      cnt = c;
    }
  }

  // Using BST comprised of the nodes without count.
  class BstWithCountVersion {
    TreeNodeC root;
    int k;

    // Build BST. O(NH) time. (O(NlogK) time)
    public BstWithCountVersion(int k, int[] nums) {
      this.k = k;
      for (int num : nums) {
        root = insert(root, num);
      }

      // For test.
      preorder(root);
      System.out.println();
      inorder(root);
      System.out.println();
    }

    // O(H) time. (O(logK) time)
    public int add(int val) {
      // O(H) time.
      root = insert(root, val);
      // O(H) time.
      return findKthLargest();
    }

    // O(H) time. (O(logK) time)
    private TreeNodeC insert(TreeNodeC node, int val) {
      if (node == null) {
        return new TreeNodeC(val);
      }

      // node.count is the number of nodes in the
      // tree whose root is the node.
      node.count++;

      if (val < node.val) {
        node.left = insert(node.left, val);
      } else {
        // val >= node.val
        node.right = insert(node.right, val);
      }

      return node;
    }

    // Query. O(H) time. (O(logK) time)
    private int findKthLargest() {
      int count = k;
      TreeNodeC cur = root;

      while (count > 0) {
        int pos = 1 + (cur.right != null ? cur.right.count : 0);
        if (pos == count) {
          break;
        }
        if (pos < count) {
          // pos, which is the number of nodes, cur + (all the nodes of right subtree of
          // cur),
          // is not enough to reach k. So, go down to the left subtree to find the
          // next largest nodes.
          count -= pos;
          cur = cur.left;
        } else if (pos > count) {
          cur = cur.right;
        }
      }

      return cur.val;
    }

  }

  // Review
  class Review {
    int k;
    PriorityQueue<Integer> minHeap;

    public Review(int k, int[] nums) {
      this.k = k;
      minHeap = new PriorityQueue<>(k);

      for (int num : nums) {
        add(num);
      }
    }

    public int add(int val) {
      minHeap.offer(val);
      if (minHeap.size() > k) {
        minHeap.poll();
      }

      return minHeap.peek();
    }

  }

  // For testing.
  public static void main(String[] args) {
    KthLargestElementInAStream solution = new KthLargestElementInAStream();
    // Test arguments.
    // int num = 24;
    // int target = 2;
    // solution.getInt(num, target);
    int[] nums = new int[] { 4, 5, 8, 2 };
    PqVersion pqVersion = solution.new PqVersion(3, nums);
    System.out.println(pqVersion.add(3)); // 4
    System.out.println(pqVersion.add(5)); // 5
    System.out.println(pqVersion.add(10)); // 5
    System.out.println(pqVersion.add(9)); // 8
    System.out.println(pqVersion.add(4)); // 8
    System.out.println();

    BstWithCountVersion bstVersion = solution.new BstWithCountVersion(3, nums);
    System.out.println(bstVersion.add(3)); // 4
    System.out.println(bstVersion.add(5)); // 5
    System.out.println(bstVersion.add(10)); // 5
    System.out.println(bstVersion.add(9)); // 8
    System.out.println(bstVersion.add(4)); // 8
    solution.preorder(bstVersion.root);
    System.out.println(); // 4235485109
    solution.inorder(bstVersion.root);
    System.out.println(); // 2345485109

  }

  // For test.
  private void preorder(TreeNodeC node) {
    if (node == null) {
      return;
    }

    System.out.print(node.val);
    preorder(node.left);
    preorder(node.right);
  }

  private void inorder(TreeNodeC node) {
    if (node == null) {
      return;
    }

    inorder(node.left);
    System.out.print(node.val);
    inorder(node.right);
  }

}
