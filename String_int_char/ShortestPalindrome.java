//
// Author:
// Date  : December 4, 2020

package leetcode;

public class ShortestPalindrome {
  // fields and classes here.
  // private int count;

  public ShortestPalindrome() {
    // Initialization here.
    // this.count = 0;

  }

  // 1. Brute Force
  // O(N^2) time, O(N) space
  // Author: leetcode + kei
  // Date : December 4, 2020
  String shortestPalindrome(String s) {
    int n = s.length();
    // Reverse String.
    String rev = new StringBuilder(s).reverse().toString();
    // Find the longest prefix that is equal to the suffix.
    // Matching with the reversed string will give a palindrome.
    for (int i = 0; i < n; i++) {
      // Check the prefix in the string and the suffix in the reversed string.
      if (s.substring(0, n - i).equals(rev.substring(i))) {
        // Palindrome found.
        return rev.substring(0, i) + s;
      }
    }

    return "";
  }

  // 3. Use KMP (Knuth–Morris–Pratt) Algorithm
  // KMP Explanation video
  // https://www.youtube.com/watch?v=BXCEFAzhxGY
  // O(N) time, O(N) space
  // Author: leetcode + kei
  // Date : December 5, 2020
  String shortestPalindromeKMP(String s) {
    int n = s.length();
    // Reverse String.
    String rev = new StringBuilder(s).reverse().toString();
    // Generate the KMP lookup table.
    String strNew = s + "#" + rev;
    int nNew = strNew.length();
    // The lookup table that stores the number of prefix chars that match
    // the suffix chars in the substring 0 to t inclusive, which also indicates
    // the index that the checking pointer should get back to.
    int[] f = new int[nNew];
    // Iterate over the strNew.
    for (int i = 1; i < nNew; i++) {
      int t = f[i - 1];
      // Skip the first round.
      // strNew.charAt(t) is the prefix.
      while (t > 0 && strNew.charAt(i) != strNew.charAt(t)) {
        // Mismatch occurs.
        // t might not need to get back all the way to the beginning.
        // t gets back to the index that f[t - 1] stores, which means
        // In the string that already matched, t gets back where t should start at.
        // In other words, the already matched string might have the prefix
        // that matches the suffix.
        // Repeat until finding a match or t gets to 0.
        t = f[t - 1];
      }
      if (strNew.charAt(i) == strNew.charAt(t)) {
        // Match the prefix char and the char at index i.
        // t indicates a pointer from the beginning and the number of
        // chars that match.
        t++;
      }
      f[i] = t;
    }

    // f[nNew - 1] means the number of chars that match the prefix
    // in the original string. So the chars to be added in front of s
    // are the chars other than matched chars in the reversed string.
    return rev.substring(0, n - f[nNew - 1]) + s;
  }

  // // NG!
  // // Author: + kei
  // // Date : December 4, 2020
  // public String shortestPalindrome(String s) {
  // char[] chars = s.toCharArray();
  // int n = s.length();
  // int c = (n - 1) / 2;

  // int i = (n % 2 == 0) ? c : c - 1;
  // int j = c + 1;
  // while (i >= 0 && j < n) {
  // if (chars[i] == chars[j]) {
  // i--;
  // j++;
  // }
  // c--;

  // }
  // if (i == -1 && j == n) {
  // return s;
  // }

  // return "";
  // }

  // For testing.
  @SuppressWarnings("unused")
  public static void main(String[] args) {
    ShortestPalindrome solution = new ShortestPalindrome();

    // Test arguments.
    // int num = 24;
    // int target = 2;
    // solution.getInt(num, target);
    String s = "aacecaaa";
    System.out.println(solution.shortestPalindrome(s));

  }

}
