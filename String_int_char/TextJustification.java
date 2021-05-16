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

  // Author: kidOptimo + kei
  // Date : May 15, 2021
  public List<String> fullJustify(String[] words, int maxWidth) {
    int left = 0;
    List<String> lines = new ArrayList<>();

    while (left < words.length) {
      int right = findRight(left, words, maxWidth);
      lines.add(justify(left, right, words, maxWidth));
      left = right + 1;
    }

    return lines;
  }

  private int findRight(int left, String[] words, int maxWidth) {
    int right = left;
    int sum = words[right].length();
    right++;

    // Check if the next one fits in.
    while (right < words.length && (sum + 1 + words[right].length()) <= maxWidth) {
      sum += 1 + words[right].length();
      right++;
    }

    return right - 1;
  }

  private String justify(int left, int right, String[] words, int maxWidth) {
    if (right - left == 0) {
      return padResult(words[left], maxWidth);
    }

    // Beware of how to identify if it's the last line.
    boolean isLastLine = (right == words.length - 1);

    // To distribute spaces evenly, divide total spaces by num of spaces and
    // distribute the remainder one by one from the leftmost space.
    int numSpaces = right - left;
    int totalSpaces = maxWidth - getWordsLength(left, right, words);
    String space = isLastLine ? " " : getSpaces(totalSpaces / numSpaces);
    int remainder = isLastLine ? 0 : totalSpaces % numSpaces;

    StringBuilder line = new StringBuilder();
    for (int i = left; i <= right; i++) {
      // Distribute the remainder one by one from the leftmost space.
      line.append(words[i]).append(space).append(remainder > 0 ? " " : "");
      remainder--;
    }

    // If the line is the last line, the last space should be removed.
    return padResult(line.toString().trim(), maxWidth);
  }

  private int getWordsLength(int left, int right, String[] words) {
    int wordsLength = 0;
    for (int i = left; i <= right; i++) {
      wordsLength += words[i].length();
    }
    return wordsLength;
  }

  private String padResult(String line, int maxWidth) {
    return line + getSpaces(maxWidth - line.length());
  }

  private String getSpaces(int n) {
    String spaces = "";
    for (int i = 0; i < n; i++) {
      spaces += " ";
    }
    return spaces;

    // ??
    // return new String(new char[n]).replace('\0', ' ');
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
