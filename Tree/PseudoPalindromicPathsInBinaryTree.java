//
// Author:
// Date : May 23, 2020

package leetcode;

public class PseudoPalindromicPathsInBinaryTree {
  // fields and classes here.
  // private int count;

  public PseudoPalindromicPathsInBinaryTree() {
    // Initialization here.
    // this.count = 0;

  }


  public class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode() {}

    TreeNode(int val) {
      this.val = val;
    }

    TreeNode(int val, TreeNode left, TreeNode right) {
      this.val = val;
      this.left = left;
      this.right = right;
    }
  }


  // No need to review. Next solution is better.
  // Author: kei (AC)
  // Date : May 24, 2020
  int count = 0;

  public int pseudoPalindromicPaths2(TreeNode root) {
    int[] check = new int[10];
    dfs(root, check);
    return count;
  }

  private void dfs(TreeNode node, int[] check) {
    if (node.left == null && node.right == null) {
      toggle(check, node);
      if (isPalindromic(check)) {
        count++;
      }
      toggle(check, node);
      return;
    }

    toggle(check, node);

    if (node.left != null) {
      dfs(node.left, check);
    }
    if (node.right != null) {
      dfs(node.right, check);
    }
    toggle(check, node);
  }

  public static boolean isPalindromic(int[] check) {
    int c = 0;
    for (int n : check) {
      c += n;
    }
    return c == 0 || c == 1;
  }

  public void toggle(int[] check, TreeNode node) {
    if (check[node.val] == 0) {
      check[node.val]++;
    } else {
      check[node.val]--;
    }
  }


  // This is better!
  // Author: uwi + kei
  // Date : May 24, 2020
  int ct = 0;

  public int pseudoPalindromicPaths(TreeNode root) {
    dfs(root, 0);
    return ct;
  }

  void dfs(TreeNode node, int ptn) {
    if (node == null) {
      return;
    }

    // Pre-order traversal
    // Count the number of characters using bit vector.
    ptn ^= 1 << node.val;
    // Check palindrome using (ptn & ptn - 1) == 0 if node is a leaf.
    // (ptn & ptn - 1) == 0 holds true if ptn has all 0s or ptn has just one 1.
    if (node.left == null && node.right == null && (ptn & ptn - 1) == 0) {
      ct++;
    }

    dfs(node.left, ptn);
    dfs(node.right, ptn);
  }



  // For testing.
  @SuppressWarnings("unused")
  public static void main(String[] args) {
    PseudoPalindromicPathsInBinaryTree solution = new PseudoPalindromicPathsInBinaryTree();

    // Test arguments.
    // int num = 24;
    // int target = 2;
    // solution.getInt(num, target);

    TreeNode n1 = solution.new TreeNode(2);
    TreeNode n2 = solution.new TreeNode(3);
    TreeNode n3 = solution.new TreeNode(1);
    TreeNode n4 = solution.new TreeNode(3);
    TreeNode n5 = solution.new TreeNode(1);
    TreeNode n6 = solution.new TreeNode(1);

    n1.left = n2;
    n1.right = n3;
    n2.left = n4;
    n2.right = n5;
    n3.right = n6;

    System.out.println(solution.pseudoPalindromicPaths2(n1));



  }



  public void dummyMethod() {



  }



  public void dummyMethod2() {



  }



}


