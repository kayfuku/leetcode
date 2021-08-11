package leetcode;

public class ValidPalindrome {
  // fields and classes here.
  // private int count;

  public ValidPalindrome() {
    // Initialization here.
    // this.count = 0;

  }

  // Author: leetcode + kei
  // Date : August 10, 2021
  public boolean isPalindrome(String s) {
    for (int i = 0, j = s.length() - 1; i < j; i++, j--) {
      // Move i to the first alphanumeric char from the leftmost.
      while (i < j && !Character.isLetterOrDigit(s.charAt(i))) {
        i++;
      }
      // Move j to the first alphanumeric char from the rightmost.
      while (i < j && !Character.isLetterOrDigit(s.charAt(j))) {
        j--;
      }
      // Assert that i and j points to alphanumeric chars.

      // Don't forget to lower them before comparing.
      if (Character.toLowerCase(s.charAt(i)) != Character.toLowerCase(s.charAt(j))) {
        return false;
      }
    }

    return true;
  }

  // For testing.
  @SuppressWarnings("unused")
  public static void main(String[] args) {
    ValidPalindrome solution = new ValidPalindrome();

    // Test arguments.
    // int num = 24;
    // int target = 2;
    // System.out.println(solution.getInt(num, target));

    System.out.println("\ndone.");
  }

}
