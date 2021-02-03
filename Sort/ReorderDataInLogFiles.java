package leetcode;

import java.util.Arrays;
import java.util.Comparator;

public class ReorderDataInLogFiles {
  // fields and classes here.
  // private int count;

  public ReorderDataInLogFiles() {
    // Initialization here.
    // this.count = 0;

  }

  // O(MNlogN) time, where N is the number of logs in the list and M is
  // the maximum length of a single log.
  // O(MlogN) space.
  // Author: leetcode + kei
  // Date : February 2, 2021
  public String[] reorderLogFiles(String[] logs) {
    Comparator<String> myComp = new Comparator<String>() {
      @Override
      public int compare(String log1, String log2) {
        // Split each log into identifier and content.
        // split(reg, n): apply split at most n - 1 times
        // from the head using delimiter reg.
        // O(M) space.
        String[] split1 = log1.split(" ", 2);
        String[] split2 = log2.split(" ", 2);

        // Check if a char is a number or not.
        boolean isDigit1 = Character.isDigit(split1[1].charAt(0));
        boolean isDigit2 = Character.isDigit(split2[1].charAt(0));

        // case 1). both logs are letter-logs
        if (!isDigit1 && !isDigit2) {
          // first compare the content
          // O(M) time.
          int cmp = split1[1].compareTo(split2[1]);
          if (cmp != 0) {
            return cmp;
          }
          // logs of same content, compare the identifiers
          return split1[0].compareTo(split2[0]);
        }

        // case 2). one of logs is digit-log
        if (!isDigit1 && isDigit2) {
          // the letter-log comes before digit-logs
          // Returning negative number means log1 (letter-log) comes first
          // then log2 (digit-log) comes next.
          return -1;
        } else if (isDigit1 && !isDigit2) {
          // Returning positive number means log2 (letter-log) comes first
          // then log1 (digit-log) comes next.
          return 1;
        } else {
          // case 3). both logs are digit-logs
          // Maintain their relative ordering because
          // the Arrays.sort() interface that we use is stable,
          // as one can find in the specification.
          return 0;
        }
      }
    };

    // Dual-pivot Quicksort takes O(NlogN) time and O(logN) space in itself.
    Arrays.sort(logs, myComp);

    return logs;
  }

  // For testing.
  @SuppressWarnings("unused")
  public static void main(String[] args) {
    ReorderDataInLogFiles solution = new ReorderDataInLogFiles();

    // Test arguments.
    // int num = 24;
    // int target = 2;
    // System.out.println(solution.getInt(num, target));

    System.out.println("\ndone.");
  }

}
