package leetcode;

public class ChangeMinimumCharactersToSatisfyOneOfThreeConditions {
  // fields and classes here.
  // private int count;

  public ChangeMinimumCharactersToSatisfyOneOfThreeConditions() {
    // Initialization here.
    // this.count = 0;

  }

  // Author: uwi + kei
  // Date : January 30, 2021
  public int minCharacters(String a, String b) {
    char[] s = a.toCharArray();
    char[] t = b.toCharArray();
    int[] fa = new int[26];
    for (char c : s) {
      fa[c - 'a']++;
    }
    int[] fb = new int[26];
    for (char c : t) {
      fb[c - 'a']++;
    }

    // int min = 999999999;
    int min = Integer.MAX_VALUE;

    // 1)
    // i starts from 1 because 'b'(i=1) is the leftmost letter in b.
    // In other words, i is the borderline.
    for (int i = 1; i <= 25; i++) {
      int cost = 0;
      // Calculate cost for moving from the right side of i in a.
      for (int j = i; j < 26; j++) {
        cost += fa[j];
      }
      // Calculate cost for moving from the left side of i in b.
      for (int j = 0; j < i; j++) {
        cost += fb[j];
      }
      min = Math.min(min, cost);
    }
    // 2)
    for (int i = 1; i <= 25; i++) {
      int cost = 0;
      for (int j = i; j < 26; j++) {
        cost += fb[j];
      }
      for (int j = 0; j < i; j++) {
        cost += fa[j];
      }
      min = Math.min(min, cost);
    }
    // 3)
    int n = s.length + t.length;
    for (int i = 0; i < 26; i++) {
      min = Math.min(min, n - (fa[i] + fb[i]));
    }

    return min;
  }

  // For testing.
  @SuppressWarnings("unused")
  public static void main(String[] args) {
    ChangeMinimumCharactersToSatisfyOneOfThreeConditions solution = new ChangeMinimumCharactersToSatisfyOneOfThreeConditions();

    // Test arguments.
    // int num = 24;
    // int target = 2;
    // System.out.println(solution.getInt(num, target));

    System.out.println("\ndone.");
  }

}
