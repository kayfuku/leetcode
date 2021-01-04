package leetcode;

public class FindNumbersWithEvenNumberOfDigits {
  // fields and classes here.
  // private int count;

  public FindNumbersWithEvenNumberOfDigits() {
    // Initialization here.
    // this.count = 0;

  }

  // Author: rock + kei
  // Date : December 26, 2020
  public int findNumbers(int[] nums) {
    int count = 0;
    // fori is faster than foreach!?
    for (int i = 0; i < nums.length; i++) {
      // When we need to count something related to even/odd, we might not need
      // if statement.
      // String.valueOf(nums[i]) is faster than Integer.toString(nums[i])!?
      count += 1 - String.valueOf(nums[i]).length() % 2;
    }

    return count;
  }

  // For testing.
  @SuppressWarnings("unused")
  public static void main(String[] args) {
    FindNumbersWithEvenNumberOfDigits solution = new FindNumbersWithEvenNumberOfDigits();

    // Test arguments.
    // int num = 24;
    // int target = 2;
    // solution.getInt(num, target);

  }

}
