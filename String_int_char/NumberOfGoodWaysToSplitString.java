package leetcode;

public class NumberOfGoodWaysToSplitString {
  // fields and classes here.
  // private int count;

  public NumberOfGoodWaysToSplitString() {
    // Initialization here.
    // this.count = 0;

  }

  // Author: hobiter + kei
  // Date : October 4, 2021
  public int numSplits(String s) {
    int[] rc = new int[26];
    int[] lc = new int[26];
    int l = 0, r = 0, res = 0;
    for (char c : s.toCharArray()) {
      if (rc[c - 'a'] == 0) {
        r++;
      }
      rc[c - 'a']++;
    }
    for (char c : s.toCharArray()) {
      if (lc[c - 'a'] == 0) {
        l++;
      }
      lc[c - 'a']++;
      rc[c - 'a']--;
      if (rc[c - 'a'] == 0) {
        r--;
      }
      if (l == r) {
        res++;
      }
    }
    return res;
  }

  // For testing.
  @SuppressWarnings("unused")
  public static void main(String[] args) {
    NumberOfGoodWaysToSplitString solution = new NumberOfGoodWaysToSplitString();

    // Test arguments.
    // int num = 24;
    // int target = 2;
    // System.out.println(solution.getInt(num, target));

    System.out.println("\ndone.");
  }

}
