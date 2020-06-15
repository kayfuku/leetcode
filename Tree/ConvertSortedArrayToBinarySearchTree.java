//
// Author:
// Date : June 21, 2019

package leetcode;

public class ConvertSortedArrayToBinarySearchTree {
  // fields here.
  // private int count;

  public ConvertSortedArrayToBinarySearchTree() {
    // Initialization here.
    // this.count = 0;
  }

  // 1. Recursive.
  // I'm going to pick up the element right in the middle and use it as a root node
  // so that the left subarray size and the right subarray size never differ more than one.
  // And the left subarray is going to be the left subtree, and the right subarray is going to
  // be the right subtree. And then recursively pick up the middle element in the subarray as a
  // root.
  //
  // The recursion stack input is the input array, left index and right index.
  // The output is the root node of the BST.
  //
  // O(N) time, O(N) space.
  // Author: kei (AC)
  // Date : June 21, 2019
  public TreeNode sortedArrayToBST(int[] nums) {
    if (nums == null || nums.length == 0) {
      return null;
    }
    return sortedArrayToBST(nums, 0, nums.length - 1);
  }

  private TreeNode sortedArrayToBST(int[] nums, int left, int right) {
    if (left > right) {
      return null;
    }

    int mid = (left + right) / 2;
    // int mid = left + (right - left) / 2; // ok.

    // Create a new node.
    TreeNode root = new TreeNode(nums[mid]);

    root.left = sortedArrayToBST(nums, left, mid - 1);
    root.right = sortedArrayToBST(nums, mid + 1, right);

    return root;
  }


  // Review
  public TreeNode sortedArrayToBSTR(int[] nums) {
    // corner.
    if (nums == null || nums.length == 0) {
      return null;
    }

    return sortedArrayToBSTR(nums, 0, nums.length - 1);
  }

  private TreeNode sortedArrayToBSTR(int[] nums, int left, int right) {
    // base case.
    if (left > right) {
      return null;
    }

    int mid = (left + right) / 2;
    TreeNode root = new TreeNode(nums[mid]);

    root.left = sortedArrayToBSTR(nums, left, mid - 1);
    root.right = sortedArrayToBSTR(nums, mid + 1, right);

    return root;
  }



  // For testing.
  public static void main(String[] args) {
    ConvertSortedArrayToBinarySearchTree solution = new ConvertSortedArrayToBinarySearchTree();

    // Test arguments.
    // int num = 24;
    // int target = 2;
    // solution.getInt(num, target);



  }

}


