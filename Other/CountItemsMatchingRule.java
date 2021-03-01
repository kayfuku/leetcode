package leetcode;

import java.util.List;

public class CountItemsMatchingRule {
  // fields and classes here.
  // private int count;

  public CountItemsMatchingRule() {
    // Initialization here.
    // this.count = 0;

  }

  // Author: uwi + kei
  // Date : February 28, 2021
  public int countMatches(List<List<String>> items, String ruleKey, String ruleValue) {
    int idx = ruleKey.equals("type") ? 0 : ruleKey.equals("color") ? 1 : 2;
    int ret = 0;
    for (List<String> item : items) {
      if (item.get(idx).equals(ruleValue)) {
        ret++;
      }
    }
    return ret;
  }

  // For testing.
  @SuppressWarnings("unused")
  public static void main(String[] args) {
    CountItemsMatchingRule solution = new CountItemsMatchingRule();

    // Test arguments.
    // int num = 24;
    // int target = 2;
    // System.out.println(solution.getInt(num, target));

    System.out.println("\ndone.");
  }

}
