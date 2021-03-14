package leetcode;

public class MinimumElementsToAddToFormGivenSum {
  // fields and classes here.
  // private int count;

  public MinimumElementsToAddToFormGivenSum() {
    // Initialization here.
    // this.count = 0;

  }

  // Author: uwi + kei
  // Date : March 13, 2021
  public int minElements(int[] nums, int limit, int goal) {
    long sum = 0;
    for (int num : nums) {
      sum += num;
    }

    long dist = Math.abs(goal - sum);

    // If the remainder is more than 0, then this line adds one to the quotient.
    return (int) ((dist + limit - 1) / limit);
    // instead of
    // return (dist % limit == 0) ? (int) (dist / limit) : (int) (dist / limit) + 1;
  }

  // For testing.
  @SuppressWarnings("unused")
  public static void main(String[] args) {
    MinimumElementsToAddToFormGivenSum solution = new MinimumElementsToAddToFormGivenSum();

    // Test arguments.
    // int num = 24;
    // int target = 2;
    // System.out.println(solution.getInt(num, target));

    System.out.println("\ndone.");
  }

}
