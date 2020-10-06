//
// Author:
// Date : June 12, 2019

package leetcode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

public class BinarySearchTreeIterator {
  // fields here.
  // private int count;

  public BinarySearchTreeIterator() {
    // Initialization here.
    // this.count = 0;
  }

  // 1. Flattening the BST.
  // The in-order traversal of a BST gives us the elements in an ascending order.
  // For iterator, I just need an array and a pointer.
  // next(): O(1) time, hasNext(): O(1) time
  // O(N) space.
  class BSTIterator {

    // For iterator, you just need array and pointer to the current value.
    List<Integer> inorderNodes;
    int index;

    public BSTIterator(TreeNode root) {
      // Array containing all the nodes in the sorted order.
      inorderNodes = new ArrayList<>();
      // Pointer to the next smallest element in the BST.
      index = -1;
      // Call to flatten the input binary search tree.
      createInorderArray(root);
    }

    // BST to in-order array.
    private void createInorderArray(TreeNode node) {
      if (node == null) {
        return;
      }

      createInorderArray(node.left);
      inorderNodes.add(node.val);
      createInorderArray(node.right);
    }

    // @return the next smallest number
    public int next() {
      index++;
      return inorderNodes.get(index);
    }

    // @return true if we have a next smallest number
    public boolean hasNext() {
      return index + 1 < inorderNodes.size();
    }
  }

  // 2. Controlled Recursion.
  // next(): Amortized O(1) time, hasNext(): O(1) time.
  // O(h) space, where h is the height of the tree, which is also the size of the
  // stack we use.
  // Author: LeetCode + kei
  // Date : June 12, 2019
  /**
   * https://leetcode.com/problems/binary-search-tree-iterator/solution/
   * 
   * next() involves two major operations. One is where we pop an element from the
   * stack which becomes the next smallest element to return. This is a O(1)
   * operation. However, we then make a call to our helper function
   * addLeftmostNodes which iterates over a bunch of nodes. This is clearly a
   * linear time operation i.e. O(N) in the worst case. This is true. However, the
   * important thing to note here is that we only make such a call for nodes which
   * have a right child. Otherwise, we simply return. Also, even if we end up
   * calling the helper function, it won't always process N nodes. They will be
   * much lesser. Only if we have a skewed tree would there be N nodes for the
   * root. But that is the only node for which we would call the helper function.
   * Thus, the amortized (average) time complexity for this function would still
   * be O(1).
   */
  class BSTIterator2 {

    LinkedList<TreeNode> stack;

    public BSTIterator2(TreeNode root) {
      stack = new LinkedList<>();
      addLeftmostNodes(root);
    }

    // For a given node, push all the elements in the leftmost branch of the tree.
    private void addLeftmostNodes(TreeNode node) {
      while (node != null) {
        stack.push(node);
        node = node.left;
      }
    }

    /** @return the next smallest number */
    // When this method is called, it is guaranteed that there is a next node.
    public int next() {
      TreeNode node = stack.poll();
      if (node.right != null) {
        addLeftmostNodes(node.right);
      }
      return node.val;
    }

    /** @return whether we have a next smallest number */
    public boolean hasNext() {
      // Both are ok.
      return !stack.isEmpty();
      // return stack.size() > 0;
    }
  }

  /**
   * Your BSTIterator object will be instantiated and called as such: BSTIterator
   * obj = new BSTIterator(root); int param_1 = obj.next(); boolean param_2 =
   * obj.hasNext();
   */

  // Review
  // O(N) time, O(N) space.
  class BSTIteratorR {
    List<Integer> sortedArray;
    int index;

    public BSTIteratorR(TreeNode node) {
      sortedArray = new ArrayList<>();
      index = -1;
      flattenBst(node);
    }

    private void flattenBst(TreeNode node) {
      if (node == null) {
        return;
      }

      flattenBst(node.left);
      sortedArray.add(node.val);
      flattenBst(node.right);
    }

    public int nextR() {
      index++;
      return sortedArray.get(index);
    }

    public boolean hasNextR() {
      return index + 1 < sortedArray.size();
    }

  }

  // Review
  // next(): Amortized O(1) time, hasNext(): O(1) time.
  // O(h) space, where h is the height of the tree.
  class BSTIteratorR2 {

    Stack<TreeNode> stackNode;

    public BSTIteratorR2(TreeNode root) {
      stackNode = new Stack<>();
      pushLeftmostNodes(root);
    }

    private void pushLeftmostNodes(TreeNode node) {
      while (node != null) {
        stackNode.push(node);
        node = node.left;
      }
    }

    public int next() {
      TreeNode node = stackNode.pop();
      if (node.right != null) {
        pushLeftmostNodes(node.right);
      }

      return node.val;
    }

    public boolean hasNext() {
      return !stackNode.isEmpty();
    }

  }

  // For testing.
  public static void main(String[] args) {
    BinarySearchTreeIterator solution = new BinarySearchTreeIterator();

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

    BSTIterator2 bstIterator2 = solution.new BSTIterator2(tn1);
    System.out.println(bstIterator2.next()); // 1
    System.out.println(bstIterator2.next()); // 3
    System.out.println(bstIterator2.next()); // 4
    System.out.println(bstIterator2.next()); // 6
    System.out.println(bstIterator2.hasNext()); // true
    System.out.println(bstIterator2.next()); // 9
    System.out.println(bstIterator2.next()); // 12
    System.out.println(bstIterator2.next()); // 14
    System.out.println(bstIterator2.hasNext()); // false

  }

}
