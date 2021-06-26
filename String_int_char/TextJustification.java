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
  // word is the rightmost in one line.
  //
  // O(N) time, where N is the total number of words.
  // O(N) space
  // Author: kidOptimo + kei
  // Date : May 15, 2021, June 8, 2021
  public List<String> fullJustify(String[] words, int maxWidth) {
    int left = 0;
    List<String> lines = new ArrayList<>();

    while (left < words.length) {
      // Find the rightmost word index.
      int right = findRight(left, words, maxWidth);

      // Justify using the leftmost word and rightmost word.
      lines.add(justify(left, right, words, maxWidth));

      // Move on to the next.
      left = right + 1;
    }

    return lines;
  }

  // Return the rightmost word index.
  // To find the rightmost word, I'm gonna go through the words array, checking if
  // the word fits in the line.
  private int findRight(int left, String[] words, int maxWidth) {
    // I'm gonna set the 'right' equal to the first word in that line.
    int right = left;
    // To keep the length
    int sum = words[left].length();

    // Check if the next word fits in.
    right++;
    // If the word does not fit in the line, then we get out of the loop.
    while (right < words.length && (sum + 1 + words[right].length()) <= maxWidth) {
      // The current word fits in that line.
      sum += 1 + words[right].length();
      // Move on to the next.
      right++;
    }

    // Return the rightmost word index.
    return right - 1;
  }

  // Return the complete line, which has spaces distributed evenly in between
  // those words.
  // left: leftmost word index
  // right: rightmost word index
  private String justify(int left, int right, String[] words, int maxWidth) {
    StringBuilder line = new StringBuilder();

    // Basically, there are three cases.
    // Case 1. two or more words --- distribute spaces evenly to the slots
    // Case 2. one word --- pad with spaces
    // Case 3. last line --- no need to justify the words

    // Case 2.
    if (right - left == 0) {
      // Only one word in the line
      line.append(words[left]);
      // Pad with spaces.
      line.append(getSpaces(maxWidth - words[left].length()));
      return line.toString();
    }

    // Check if the line has the last word.
    if (right == words.length - 1) {
      // Case 3.
      // last line
      for (int i = left; i <= right; i++) {
        // Left justified and no extra space is inserted between words
        line.append(words[i]).append(" ");
      }
      // Don't forget to remove the trailing space because the rightmost word can
      // already be justified.
      String ret = line.toString().trim();

      // We need to pad with spaces.
      return ret + getSpaces(maxWidth - ret.length());

    } else {
      // Case 1.
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

      // After getting out of the loop, we need to trim the trailing spaces for the
      // right justification because we've already distributed spaces needed in
      // between words.
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

  // Return n continuous spaces.
  private String getSpaces(int n) {
    StringBuilder spaces = new StringBuilder();
    for (int i = 0; i < n; i++) {
      spaces.append(" ");
    }
    return spaces.toString();
  }

  // Review
  public List<String> fullJustifyR(String[] words, int maxWidth) {
    List<String> lines = new ArrayList<>();
    int left = 0;

    while (left < words.length) {
      int right = getRightmostR(left, words, maxWidth);
      lines.add(justifyR(left, right, words, maxWidth));
      left = right + 1;
    }

    return lines;
  }

  private int getRightmostR(int left, String[] words, int maxWidth) {
    int right = left;
    int sum = words[left].length();

    right++;
    while (right < words.length && sum + 1 + words[right].length() <= maxWidth) {
      sum += 1 + words[right].length();
      right++;
    }

    return right - 1;
  }

  private String justifyR(int left, int right, String[] words, int maxWidth) {
    StringBuilder line = new StringBuilder();

    // 1. two or more words --- distribute spaces evenly
    // 2. just one word --- pad with spaces
    // 3. the last line --- no need to justify and distribute spaces evenly

    // 2.
    if (left == right) {
      line.append(words[left]);
      line.append(getSpacesR(maxWidth - words[left].length()));
      return line.toString();
    }

    // Check if the line has the last word.
    if (right == words.length - 1) {
      // last line
      for (int i = left; i <= right; i++) {
        line.append(words[i]).append(" ");
      }
      String ret = line.toString().trim();

      return ret + getSpacesR(maxWidth - ret.length());

    } else {
      // 1.
      int numSlots = right - left;
      int totalSpaces = maxWidth - getTotalLengthR(left, right, words);
      String baseSpaces = getSpacesR(totalSpaces / numSlots);
      int remainder = totalSpaces % numSlots;

      for (int i = left; i <= right; i++) {
        line.append(words[i]).append(baseSpaces).append((remainder > 0 ? " " : ""));
        remainder--;
      }

      return line.toString().trim();
    }
  }

  private int getTotalLengthR(int left, int right, String[] words) {
    int sum = 0;
    for (int i = left; i <= right; i++) {
      sum += words[i].length();
    }
    return sum;
  }

  private String getSpacesR(int n) {
    String spaces = "";
    for (int i = 0; i < n; i++) {
      spaces += " ";
    }
    return spaces;
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
