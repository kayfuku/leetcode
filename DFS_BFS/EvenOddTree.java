//
// Author:
// Date  : October 4, 2020

package leetcode;

import java.util.LinkedList;
import java.util.Queue;

public class EvenOddTree {
  // fields and classes here.
  // private int count;

  public EvenOddTree() {
    // Initialization here.
    // this.count = 0;

  }

  // Author: + kei (AC)
  // Date : October 3, 2020
  public boolean isEvenOddTree(TreeNode root) {
    Queue<TreeNode> queue = new LinkedList<>();
    queue.add(root);

    int level = 0;
    while (!queue.isEmpty()) {
      int size = queue.size();
      int prev = (level % 2 == 0) ? 0 : Integer.MAX_VALUE;
      for (int i = 0; i < size; i++) {
        TreeNode node = queue.poll();
        // Do something here.
        if (level % 2 == 0) {
          if (node.val % 2 != 1) {
            return false;
          }
          if (node.val <= prev) {
            return false;
          }
        } else {
          if (node.val % 2 != 0) {
            return false;
          }
          if (node.val >= prev) {
            return false;
          }
        }
        prev = node.val;

        if (node.left != null) {
          queue.add(node.left);
        }
        if (node.right != null) {
          queue.add(node.right);
        }
      }
      // Don't put this in the for loop!
      level++;
    }

    return true;
  }

  // For testing.
  @SuppressWarnings("unused")
  public static void main(String[] args) {
    EvenOddTree solution = new EvenOddTree();

    // Test arguments.
    // int num = 24;
    // int target = 2;
    // solution.getInt(num, target);

  }

}
