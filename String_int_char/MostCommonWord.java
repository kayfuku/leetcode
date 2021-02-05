package leetcode;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class MostCommonWord {
  // fields and classes here.
  // private int count;

  public MostCommonWord() {
    // Initialization here.
    // this.count = 0;

  }

  // 1. String Processing in Pipeline
  // O(N + M) time, where N is the number of characters in the paragraph and M is
  // the number of words in banned array.
  // O(N + M) space.
  // Author: leetcode + kei
  // Date : February 4, 2021
  public String mostCommonWord(String paragraph, String[] banned) {
    // First replace non-letter-or-digit characters with a space.
    // Then, split it by one or more white spaces. Wow!

    // 1). Replace the punctuations with spaces, and put all letters in lower case.
    String normalizedStr = paragraph.replaceAll("[^a-zA-Z0-9]", " ").toLowerCase();

    // 2). Split the string into words.
    String[] words = normalizedStr.split("\\s+");

    // 3). Count the appearance of each word, excluding the banned words.
    Map<String, Integer> wordCount = new HashMap<>();
    Set<String> bannedSet = new HashSet<>(Arrays.asList(banned));
    for (String word : words) {
      if (!bannedSet.contains(word)) {
        wordCount.put(word, wordCount.getOrDefault(word, 0) + 1);
      }
    }

    // 4). Return the word with the highest frequency.
    return Collections.max(wordCount.entrySet(), Map.Entry.comparingByValue()).getKey();
  }

  // 2. Character Processing in One-Pass
  // Author: leetcode + kei
  // Date : February 4, 2021
  public String mostCommonWord2(String paragraph, String[] banned) {
    Set<String> bannedSet = new HashSet<>(Arrays.asList(banned));

    String ans = "";
    int maxCount = 0;
    Map<String, Integer> wordCount = new HashMap<>();
    StringBuilder wordBuffer = new StringBuilder();
    char[] chars = paragraph.toCharArray();

    for (int p = 0; p < chars.length; p++) {
      char currChar = chars[p];

      // 1). Consume the characters in a word.
      if (Character.isLetter(currChar)) {
        wordBuffer.append(Character.toLowerCase(currChar));
        if (p != chars.length - 1) {
          // Skip the rest of the processing because we are in a word.
          continue;
        }
      }

      // 2). At the end of one word or at the end of paragraph
      if (wordBuffer.length() > 0) {
        String word = wordBuffer.toString();
        // Identify the maximum count while updating the wordCount table.
        if (!bannedSet.contains(word)) {
          int newCount = wordCount.getOrDefault(word, 0) + 1;
          wordCount.put(word, newCount);
          if (newCount > maxCount) {
            maxCount = newCount;
            ans = word;
          }
        }
        // Reset the buffer for the next word.
        wordBuffer = new StringBuilder();
      }
    }
    return ans;
  }

  // // NG with input like "a, a, a, a, b,b,b,c, c", ["a"] => "b"
  // // Author: + kei (WA)
  // // Date : February 4, 2021
  // public String mostCommonWordNG(String paragraph, String[] banned) {
  // Set<String> bannedSet = new HashSet<>(Arrays.asList(banned));
  // String[] words = paragraph.split(" ");
  // Map<String, Integer> counts = new HashMap<>();
  // for (String word : words) {
  // word = word.toLowerCase().replaceAll("[!?',;.]", "");
  // if (!bannedSet.contains(word)) {
  // counts.put(word, counts.getOrDefault(word, 0) + 1);
  // }
  // }

  // int max = 0;
  // String ans = "";
  // for (Map.Entry<String, Integer> entry : counts.entrySet()) {
  // String word = entry.getKey();
  // int count = entry.getValue();
  // if (count > max) {
  // max = count;
  // ans = word;
  // }
  // }

  // return ans;
  // }

  // For testing.
  @SuppressWarnings("unused")
  public static void main(String[] args) {
    MostCommonWord solution = new MostCommonWord();

    // Test arguments.
    // int num = 24;
    // int target = 2;
    // System.out.println(solution.getInt(num, target));
    String paragraph = "Bob hit a ball, the hit BALL flew far after it was hit.";
    String[] banned = { "hit" };
    System.out.println(solution.mostCommonWord(paragraph, banned));

    System.out.println("\ndone.");
  }

}
