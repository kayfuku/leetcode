package leetcode;

public class CountAndSay {
  // fields and classes here.
  // private int count;

  public CountAndSay() {
    // Initialization here.
    // this.count = 0;

  }

  // 1
  // O(N) time and space?
  // Author: zhibzhang + kei
  // Date : June 21, 2021
  public String countAndSay(int n) {
    String s = "1";
    for (int i = 1; i < n; i++) {
      s = countIdx(s);
    }
    return s;
  }

  public String countIdx(String s) {
    StringBuilder sb = new StringBuilder();
    char c = s.charAt(0);
    int count = 1;
    for (int i = 1; i < s.length(); i++) {
      if (s.charAt(i) == c) {
        count++;
      } else {
        sb.append(count);
        sb.append(c);
        c = s.charAt(i);
        count = 1;
      }
    }
    sb.append(count);
    sb.append(c);

    return sb.toString();
  }

  // 2
  public String countAndSay2(int n) {
    return countIdx2("", n);
  }

  public String countIdx2(String s, int n) {
    if (n == 1) {
      return "1";
    }
    String str = countIdx2(s, n - 1);
    StringBuilder sb = new StringBuilder();
    char c = str.charAt(0);
    int count = 1;
    for (int i = 1; i < str.length(); i++) {
      if (str.charAt(i) == c) {
        count++;
      } else {
        sb.append(count);
        sb.append(c);
        c = str.charAt(i);
        count = 1;
      }
    }
    sb.append(count);
    sb.append(c);

    return sb.toString();
  }

  // For testing.
  @SuppressWarnings("unused")
  public static void main(String[] args) {
    CountAndSay solution = new CountAndSay();

    // Test arguments.
    // int num = 24;
    // int target = 2;
    // System.out.println(solution.getInt(num, target));

    System.out.println("\ndone.");
  }

}
