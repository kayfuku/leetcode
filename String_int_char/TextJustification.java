package leetcode;

import java.util.ArrayList;
import java.util.List;

public class TextJustification {
  // fields and classes here.
  // private int count;

  public TextJustification() {
    // Initialization here.
    // this.count = 0;

  }

  // To fully justify words, we need to know which word is the leftmost and which
  // word is the rightmost in a line.
  //
  // Author: kidOptimo + kei
  // Date : May 15, 2021, June 8, 2021
  public List<String> fullJustify(String[] words, int maxWidth) {
    int left = 0;
    List<String> lines = new ArrayList<>();

    while (left < words.length) {
      // Find the rightmost word index.
      int right = findRight(left, words, maxWidth);

      // Pad spaces to justify using the leftmost word and rightmost word.
      lines.add(justify(left, right, words, maxWidth));

      left = right + 1;
    }

    return lines;
  }

  // Return the rightmost word index.
  // To find the rightmost word, I'm gonna go through the words array, checking if
  // the word fits in the line.
  private int findRight(int left, String[] words, int maxWidth) {
    // I'm gonna set the 'right' equal to the first word.
    int right = left;
    // Keep the length.
    int sum = words[left].length();

    // Check if the next word fits in.
    right++;
    while (right < words.length && (sum + 1 + words[right].length()) <= maxWidth) {
      // The current word fits in that line.
      sum += 1 + words[right].length();
      // Move on to the next.
      right++;
    }

    // Return the rightmost word index.
    return right - 1;
  }

  // Return the complete line.
  // left: leftmost word index
  // right: right most word index
  private String justify(int left, int right, String[] words, int maxWidth) {
    StringBuilder line = new StringBuilder();

    // Basically, there are three cases.
    // 1. two or more words --- distribute spaces evenly to the slots
    // 2. one word --- pad with spaces
    // 3. last line --- no need to justify the words

    // 2.
    if (right - left == 0) {
      // Only one word in the line
      // Pad with spaces.
      return padResult(words[left], maxWidth);
    }

    // Beware of how to identify if it's the last line.
    boolean isLastLine = (right == words.length - 1);
    if (isLastLine) {
      // 3.
      String space = " ";

      // Make the line.
      for (int i = left; i <= right; i++) {
        // Left justified and no extra space is inserted between words
        line.append(words[i]).append(space);
      }

      // We need to pad with spaces.
      // Don't forget to remove the trailing space because the rightmost word can
      // already be justified.
      return padResult(line.toString().trim(), maxWidth);

    } else {
      // 1.
      // To distribute spaces evenly, divide total spaces by the number of slots and
      // distribute the remainder one by one from the leftmost slot.
      int numSlots = right - left;
      int totalSpaces = maxWidth - getWordsLength(left, right, words);
      // Get base spaces by dividing the total spaces by the number of slots.
      String baseSpaces = getSpaces(totalSpaces / numSlots);
      int remainder = totalSpaces % numSlots;

      // Make the line.
      for (int i = left; i <= right; i++) {
        // Distribute the remainder one by one from the leftmost slot.
        line.append(words[i]).append(baseSpaces).append(remainder > 0 ? " " : "");
        remainder--;
      }

      // After getting out of the loop, we need to trim the trailing spaces
      // because we've already distributed spaces needed in between words.
      return line.toString().trim();
    }
  }

  // Return the length of all words.
  private int getWordsLength(int left, int right, String[] words) {
    int wordsLength = 0;
    for (int i = left; i <= right; i++) {
      wordsLength += words[i].length();
    }
    return wordsLength;
  }

  // Return padded line.
  // Pad some spaces to fill up the line.
  // For the last line or only one word in the line
  private String padResult(String line, int maxWidth) {
    return line + getSpaces(maxWidth - line.length());
  }

  // Return n continuous spaces.
  private String getSpaces(int n) {
    String spaces = "";
    for (int i = 0; i < n; i++) {
      spaces += " ";
    }
    return spaces;
  }

  // Review
  public List<String> fullJustifyR(String[] words, int maxWidth) {

    return null;
  }

  // For testing.
  @SuppressWarnings("unused")
  public static void main(String[] args) {
    TextJustification solution = new TextJustification();

    // Test arguments.
    // int num = 24;
    // int target = 2;
    // System.out.println(solution.getInt(num, target));

    System.out.println("\ndone.");
  }

}
