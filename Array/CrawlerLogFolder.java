//
// Author:
// Date  : September 27, 2020

package leetcode;

public class CrawlerLogFolder {
  // fields and classes here.
  // private int count;

  public CrawlerLogFolder() {
    // Initialization here.
    // this.count = 0;

  }

  // Author: lucifer1004 + kei
  // Date : September 27, 2020
  public int minOperations(String[] logs) {
    int depth = 0;
    for (String s : logs) {
      if (s.equals("./")) {
        continue;
      }
      if (s.equals("../")) {
        depth = Math.max(0, depth - 1);
        continue;
      }
      depth++;
    }

    return depth;
  }

  // Author: kei (AC)
  // Date : September 26, 2020
  public int minOperations2(String[] logs) {
    int count = 0;
    for (String s : logs) {
      if (s.charAt(0) == '.') {
        if (s.charAt(1) == '.') {
          count = Math.max(0, count - 1);
        }
      } else {
        count++;
      }

    }

    return count;
  }

  // For testing.
  @SuppressWarnings("unused")
  public static void main(String[] args) {
    CrawlerLogFolder solution = new CrawlerLogFolder();

    // Test arguments.
    // int num = 24;
    // int target = 2;
    // solution.getInt(num, target);

  }

}
