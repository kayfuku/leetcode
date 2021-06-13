package leetcode;

public class BinaryTreeTilt {
  // fields and classes here.
  // private int count;

  public BinaryTreeTilt() {
    // Initialization here.
    // this.count = 0;

  }

  // Author: leetcode + kei
  // Date : June 12, 2021
  private int totalTilt = 0;

  public int findTilt(TreeNode root) {
    totalTilt = 0;
    valueSum(root);
    return totalTilt;
  }

  private int valueSum(TreeNode node) {
    if (node == null) {
      return 0;
    }

    int leftSum = valueSum(node.left);
    int rightSum = valueSum(node.right);
    int tilt = Math.abs(leftSum - rightSum);
    this.totalTilt += tilt;

    // Return the sum of values starting from this node.
    return node.val + leftSum + rightSum;
  }

  // For testing.
  @SuppressWarnings("unused")
  public static void main(String[] args) {
    BinaryTreeTilt solution = new BinaryTreeTilt();

    // Test arguments.
    // int num = 24;
    // int target = 2;
    // System.out.println(solution.getInt(num, target));

    System.out.println("\ndone.");
  }

}
