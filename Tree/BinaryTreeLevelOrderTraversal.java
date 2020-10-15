//
// Author:
// Date : June 3, June 12 2019

package leetcode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class BinaryTreeLevelOrderTraversal {
  // fields here.
  // private int count;

  public BinaryTreeLevelOrderTraversal() {
    // Initialization here.
    // this.count = 0;
  }

  // 1. Level order traversal (BFS, Iterative).
  // O(N) time, O(N) space.
  public List<List<Integer>> levelOrder(TreeNode root) {
    List<List<Integer>> result = new ArrayList<>();
    if (root == null) {
      return result;
    }

    Queue<TreeNode> queue = new LinkedList<>();
    queue.add(root);

    // This is not necessary for this problem, but useful for others.
    int level = 0;
    while (!queue.isEmpty()) {
      // For each level.
      List<Integer> list = new ArrayList<>();
      // Note that you cannot merge this line into the for loop condition
      // because queue.size() varies during the loop.
      int size = queue.size();
      // Prepare for the next level.
      for (int i = 0; i < size; i++) {
        TreeNode node = queue.poll();
        // Do something here.
        list.add(node.val);

        if (node.left != null) {
          queue.add(node.left);
        }
        if (node.right != null) {
          queue.add(node.right);
        }
      }

      result.add(list);
      level++;
    }

    return result;
  }

  // 2. Level order traversal (DFS, Recursive).
  // This one is a little hard.
  // For DFS, I'm going to use level variable to learn which level the current
  // node is in.
  // For each level, I use a list, and while traversing the tree, I add the node
  // to the corresponding list.
  // O(N) time, O(logN) (balanced) or O(N) (not balanced) space.
  public List<List<Integer>> levelOrderDfs(TreeNode root) {
    List<List<Integer>> ret = new ArrayList<>();
    if (root == null) {
      return ret;
    }
    dfs(root, 0, ret);
    return ret;
  }

  private void dfs(TreeNode node, int level, List<List<Integer>> ret) {
    if (node == null) {
      return;
    }

    // When we go into the new level for the first time,
    // the number of lists in the return list is equal to the level.
    if (ret.size() == level) {
      ret.add(new ArrayList<>());
    }
    ret.get(level).add(node.val);

    // Every time I go to the next recursion stack, increment the level.
    dfs(node.left, level + 1, ret);
    dfs(node.right, level + 1, ret);
  }

  // 3. Level order traversal (DFS, Iterative).
  // Author: kei (AC)
  // Date : October 11 2020
  public List<List<Integer>> levelOrderIterDfs(TreeNode root) {
    List<List<Integer>> ret = new ArrayList<>();
    if (root == null) {
      return ret;
    }

    LinkedList<TreeNode> s1 = new LinkedList<>();
    LinkedList<Integer> s2 = new LinkedList<>(); // Just for convenience
    s1.push(root);
    s2.push(0);

    while (!s1.isEmpty()) {
      TreeNode node = s1.pop();
      int level = s2.pop();

      if (ret.size() == level) {
        ret.add(new ArrayList<>());
      }
      ret.get(level).add(node.val);

      if (node.right != null) {
        s1.push(node.right);
        s2.push(level + 1);
      }
      if (node.left != null) {
        s1.push(node.left);
        s2.push(level + 1);
      }
    }

    return ret;
  }

  // 4.
  // No need to review.
  // Level order traversal. Accepted. The first one might be better.
  // O(N) time, O(N) space.
  public List<List<Integer>> levelOrder2(TreeNode node) {
    List<List<Integer>> ans = new LinkedList<List<Integer>>();

    if (node == null) {
      return ans;
    }

    LinkedList<TreeNode> nodesAtLevel = new LinkedList<>();
    nodesAtLevel.add(node);

    while (!nodesAtLevel.isEmpty()) {
      LinkedList<TreeNode> parents = nodesAtLevel;

      nodesAtLevel = new LinkedList<>();
      List<Integer> valsAtLevel = new ArrayList<>();
      for (TreeNode parent : parents) {
        valsAtLevel.add(parent.val);

        if (parent.left != null) {
          nodesAtLevel.add(parent.left);
        }
        if (parent.right != null) {
          nodesAtLevel.add(parent.right);
        }
      }

      ans.add(valsAtLevel);
    }

    return ans;
  }

  // Review.
  public List<List<Integer>> levelOrderBfsR(TreeNode root) {
    List<List<Integer>> res = new ArrayList<>();

    // corner.
    if (root == null) {
      return res;
    }

    Queue<TreeNode> queue = new LinkedList<>();
    queue.offer(root);
    while (!queue.isEmpty()) {
      List<Integer> listL = new ArrayList<>();
      int size = queue.size();
      for (int i = 0; i < size; i++) {
        TreeNode node = queue.poll();
        listL.add(node.val);

        if (node.left != null) {
          queue.offer(node.left);
        }
        if (node.right != null) {
          queue.offer(node.right);
        }
      }

      res.add(listL);
    }

    return res;
  }

  // Review. DFS Recursive.
  public List<List<Integer>> levelOrderDfsR(TreeNode root) {
    List<List<Integer>> ret = new ArrayList<>();
    if (root == null) {
      return ret;
    }
    levelOrderDfsR(root, 0, ret);
    return ret;
  }

  private void levelOrderDfsR(TreeNode node, int level, List<List<Integer>> ret) {
    if (node == null) {
      return;
    }

    if (ret.size() == level) {
      ret.add(new ArrayList<>());
    }
    ret.get(level).add(node.val);

    levelOrderDfsR(node.left, level + 1, ret);
    levelOrderDfsR(node.right, level + 1, ret);
  }

  // For testing.
  public static void main(String[] args) {
    BinaryTreeLevelOrderTraversal solution = new BinaryTreeLevelOrderTraversal();

    // Test arguments.
    // int num = 24;
    // int target = 2;
    // solution.getInt(num, target);

  }

}
