package leetcode;

public class CheckIfBinaryStringHasAtMostOneSegmentOfOnes {
  // fields and classes here.
  // private int count;

  public CheckIfBinaryStringHasAtMostOneSegmentOfOnes() {
    // Initialization here.
    // this.count = 0;

  }

  // Author: uwi
  // Date : March 13, 2021
  public boolean checkOnesSegment(String S) {
    return S.matches("0*1+0*");
  }

  // Author: LayCurse + kei
  // Date : March 13, 2021
  public boolean checkOnesSegment2(String S) {
    char[] chars = S.toCharArray();
    for (int i = 1; i < chars.length; i++) {
      // Considering one of the restriction, s[0] is '1',
      // "At most one '1'" means to return false if you find a '01' pattern.
      if (chars[i - 1] == '0' && chars[i] == '1') {
        return false;
      }
    }
    return true;
  }

  // Not very good. Error prone.
  // Author: kei (AC)
  // Date : March 6, 2021
  public boolean checkOnesSegment3(String s) {
    char[] chars = s.toCharArray();
    int count = 1;
    boolean flag = true;
    for (int i = 1; i < chars.length; i++) {
      if (!flag && chars[i] == '1') {
        count++;
        flag = true;
      } else if (chars[i] == '0') {
        flag = false;
      }
    }

    return count == 1;
  }

  // For testing.
  @SuppressWarnings("unused")
  public static void main(String[] args) {
    CheckIfBinaryStringHasAtMostOneSegmentOfOnes solution = new CheckIfBinaryStringHasAtMostOneSegmentOfOnes();

    // Test arguments.
    // int num = 24;
    // int target = 2;
    // System.out.println(solution.getInt(num, target));

    System.out.println("\ndone.");
  }

}
