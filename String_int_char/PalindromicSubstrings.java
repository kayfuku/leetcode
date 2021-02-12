package leetcode;

public class PalindromicSubstrings {
  // fields and classes here.
  // private int count;

  public PalindromicSubstrings() {
    // Initialization here.
    // this.count = 0;

  }

  // Author: leetcode + kei
  // Date : Feburary 11, 2021
  public int countSubstrings(String s) {
    int ans = 0;
    for (int i = 0; i < s.length(); i++) {
      // Odd-length palindromes, single character center
      ans += countPalindromesAroundCenter(s, i, i);
      // Even-length palindromes, consecutive characters center
      // Note that i + 1 for the last index is safely avoided in the meathod.
      ans += countPalindromesAroundCenter(s, i, i + 1);
    }

    return ans;
  }

  private int countPalindromesAroundCenter(String ss, int left, int right) {
    int count = 0;
    // Continuing condition: left and right should be in the array
    while (left >= 0 && right < ss.length()) {
      if (ss.charAt(left) != ss.charAt(right)) {
        // The first and last characters don't match!
        break;
      }

      // Expand around the center.
      left--;
      right++;

      count++;
    }

    return count;
  }

  // For testing.
  @SuppressWarnings("unused")
  public static void main(String[] args) {
    PalindromicSubstrings solution = new PalindromicSubstrings();

    // Test arguments.
    // int num = 24;
    // int target = 2;
    // solution.getInt(num, target);

  }

}
