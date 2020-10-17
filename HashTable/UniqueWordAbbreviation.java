//
// Author:
// Date  : October 16, 2020

package leetcode;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class UniqueWordAbbreviation {
  // fields and classes here.
  // private int count;

  public UniqueWordAbbreviation() {
  }

  // Author: leetcode + kei
  // Date : October 16, 2020
  private final Map<String, Set<String>> abbrDict = new HashMap<>();

  public UniqueWordAbbreviation(String[] dictionary) {
    for (String s : dictionary) {
      String abbr = toAbbr(s);
      Set<String> words = abbrDict.containsKey(abbr) ? abbrDict.get(abbr) : new HashSet<>();
      words.add(s);
      abbrDict.put(abbr, words);
    }
  }

  public boolean isUnique(String word) {
    String abbr = toAbbr(word);
    Set<String> words = abbrDict.get(abbr);
    return words == null || (words.size() == 1 && words.contains(word));
  }

  private String toAbbr(String s) {
    int n = s.length();
    if (n <= 2) {
      return s;
    }
    return s.charAt(0) + Integer.toString(n - 2) + s.charAt(n - 1);
  }

  /**
   * Your ValidWordAbbr object will be instantiated and called as such:
   * ValidWordAbbr obj = new ValidWordAbbr(dictionary); boolean param_1 =
   * obj.isUnique(word);
   */

  // For testing.
  @SuppressWarnings("unused")
  public static void main(String[] args) {
    UniqueWordAbbreviation solution = new UniqueWordAbbreviation();

    // Test arguments.
    // int num = 24;
    // int target = 2;
    // solution.getInt(num, target);

  }

}
