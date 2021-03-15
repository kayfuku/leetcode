package leetcode;

public class CheckIfOneStringSwapCanMakeStringsEqual {
  // fields and classes here.
  // private int count;

  public CheckIfOneStringSwapCanMakeStringsEqual() {
    // Initialization here.
    // this.count = 0;

  }

  // Author: kei (AC)
  // Date : March 13, 2021
  public boolean areAlmostEqual(String s1, String s2) {
    if (s1.equals(s2)) {
      return true;
    }
    for (int i = 0; i < s1.length(); i++) {
      for (int j = i + 1; j < s1.length(); j++) {
        char[] chars = s1.toCharArray();
        char temp = chars[i];
        chars[i] = chars[j];
        chars[j] = temp;
        if (String.valueOf(chars).equals(s2)) {
          return true;
        }
      }
    }
    return false;
  }

  // For testing.
  @SuppressWarnings("unused")
  public static void main(String[] args) {
    CheckIfOneStringSwapCanMakeStringsEqual solution = new CheckIfOneStringSwapCanMakeStringsEqual();

    // Test arguments.
    // int num = 24;
    // int target = 2;
    // System.out.println(solution.getInt(num, target));

    System.out.println("\ndone.");
  }

}
