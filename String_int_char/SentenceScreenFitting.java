package leetcode;

public class SentenceScreenFitting {
  // fields and classes here.
  // private int count;

  public SentenceScreenFitting() {
    // Initialization here.
    // this.count = 0;

  }

  // If we know a start word for a line, then we know the start word for the next
  // line.
  //
  // Considering 1 <= sentence.length <= 100 and 1 <= cols, rows <= 2 * 10^4 (many
  // sentences in one line), we first count the end of a sentence for each word
  // that a line would start with, which takes O(C) time, where C is cols
  // (sentence length is considered as constant). Then, using that info, we sum up
  // the counts for each row.
  //
  // O(C + R) time, where C is cols and R is rows.
  // O(1) space
  // Author: tjcd + kei
  // Date : October 19, 2021
  public int wordsTyping(String[] sentence, int rows, int cols) {
    int[] nextIndex = new int[sentence.length];
    int[] times = new int[sentence.length];
    // Iterate throgh the sentence.
    // For each word which a line would start with,
    // count the number of all words fitting in.
    for (int i = 0; i < sentence.length; i++) {
      // For a line that starts with word sentence[i]
      // startCol points to where inserting starts.
      int startCol = 0;
      int wordIdx = i;
      int time = 0;
      // Check if the next word fits in the line.
      // O(C) time
      while (startCol + sentence[wordIdx].length() <= cols) {
        startCol += sentence[wordIdx].length() + 1;
        wordIdx++;
        if (wordIdx == sentence.length) {
          // Count up because the last word fits in.
          time++;
          wordIdx = 0;
        }
      }
      nextIndex[i] = wordIdx;
      times[i] = time;
    }
    // Sum up the counts for each row.
    int ans = 0;
    int wordIdx = 0;
    // O(R) time
    for (int i = 0; i < rows; i++) {
      ans += times[wordIdx];
      wordIdx = nextIndex[wordIdx];
    }
    return ans;
  }

  // Author: kei (TLE)
  // Date : October 19, 2021
  public int wordsTypingTLE(String[] sentence, int rows, int cols) {
    int wordIdx = 0, count = 0;
    for (int i = 0; i < rows; i++) {
      // Make sure what j points to. j points to where inserting starts.
      for (int j = 0; j < cols && j + sentence[wordIdx].length() <= cols; j++) {
        j += sentence[wordIdx].length();
        wordIdx = (wordIdx + 1) % sentence.length;
        if (wordIdx == 0) {
          count++;
        }
      }
    }

    return count;
  }

  // For testing.
  @SuppressWarnings("unused")
  public static void main(String[] args) {
    SentenceScreenFitting solution = new SentenceScreenFitting();

    // Test arguments.
    // int num = 24;
    // int target = 2;
    // System.out.println(solution.getInt(num, target));
    String[] sentence = { "I", "had", "apple", "pie" };
    int rows = 4, cols = 5;
    System.out.println(solution.wordsTyping(sentence, rows, cols));

    System.out.println("\ndone.");
  }

}
