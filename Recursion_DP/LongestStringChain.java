package leetcode;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class LongestStringChain {
  // fields and classes here.
  // private int count;

  public LongestStringChain() {
    // Initialization here.
    // this.count = 0;

  }

  // 2. Top Down DP
  // O(L^2N) time, O(N) space
  // Author: LeetCode + kei
  // Date : October 1, 2021
  public int longestStrChain2(String[] words) {
    Map<String, Integer> memo = new HashMap<>();
    Set<String> wordsSet = new HashSet<>();
    wordsSet.addAll(Arrays.asList(words));
    int ans = 0;
    for (String word : words) {
      ans = Math.max(ans, dfs(wordsSet, word, memo));
    }
    return ans;
  }

  private int dfs(Set<String> words, String currentWord, Map<String, Integer> memo) {
    // If the word is encountered previously we just return its value present in the
    // map (memoization).
    if (memo.containsKey(currentWord)) {
      return memo.get(currentWord);
    }
    // This stores the maximum length of word sequence possible with the
    // 'currentWord' as the
    int maxLength = 1;
    StringBuilder sb = new StringBuilder(currentWord);

    // creating all possible strings taking out one character at a time from the
    // `currentWord`
    for (int i = 0; i < currentWord.length(); i++) {
      sb.deleteCharAt(i);
      String newWord = sb.toString();
      // If the new word formed is present in the list, we do a dfs search with this
      // newWord.
      if (words.contains(newWord)) {
        int currentLength = 1 + dfs(words, newWord, memo);
        maxLength = Math.max(maxLength, currentLength);
      }
      sb.insert(i, currentWord.charAt(i));
    }
    memo.put(currentWord, maxLength);

    return maxLength;
  }

  // 1. Bottom Up DP
  // Author: LeetCode + kei
  // Date : October 1, 2021
  public int longestStrChain(String[] words) {
    Map<String, Integer> dp = new HashMap<>();

    // Sorting the list in terms of the word length.
    // O(NlogN) time
    Arrays.sort(words, (a, b) -> a.length() - b.length());

    int longestWordSequenceLength = 1;

    // O(NL^2) time
    for (String word : words) {
      int presentLength = 1;
      // Find all possible predecessors for the current word by removing one letter at
      // a time.
      // O(L^2) time
      for (int i = 0; i < word.length(); i++) {
        StringBuilder temp = new StringBuilder(word);
        temp.deleteCharAt(i);
        // O(L) time
        String predecessor = temp.toString();
        int previousLength = dp.getOrDefault(predecessor, 0);
        presentLength = Math.max(presentLength, previousLength + 1);
      }
      dp.put(word, presentLength);
      longestWordSequenceLength = Math.max(longestWordSequenceLength, presentLength);
    }
    return longestWordSequenceLength;
  }

  // For testing.
  @SuppressWarnings("unused")
  public static void main(String[] args) {
    LongestStringChain solution = new LongestStringChain();

    // Test arguments.
    // int num = 24;
    // int target = 2;
    // System.out.println(solution.getInt(num, target));

    System.out.println("\ndone.");
  }

}
