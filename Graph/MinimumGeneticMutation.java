package leetcode;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.HashSet;
import java.util.Set;

public class MinimumGeneticMutation {
  // fields and classes here.
  // private int count;

  public MinimumGeneticMutation() {
    // Initialization here.
    // this.count = 0;

  }

  // Author: xietao0221 + kei
  // Date : February 11, 2021
  public int minMutation(String start, String end, String[] bank) {
    if (start.equals(end)) {
      return 0;
    }

    Set<String> bankSet = new HashSet<>(Arrays.asList(bank));
    char[] charSet = new char[] { 'A', 'C', 'G', 'T' };

    int level = 0;
    Set<String> visited = new HashSet<>();
    Deque<String> queue = new ArrayDeque<>();
    queue.offer(start);
    visited.add(start);
    while (!queue.isEmpty()) {
      int size = queue.size();
      for (int i = 0; i < size; i++) {
        String curr = queue.poll();
        if (curr.equals(end)) {
          return level;
        }

        char[] currChars = curr.toCharArray();
        // Just at most 32 possible child nodes. (8 chars x 4 chars)
        for (int j = 0; j < currChars.length; j++) {
          char old = currChars[j];
          for (char c : charSet) {
            if (c == old) {
              continue;
            }
            // Get a string that has only one character different from original.
            currChars[j] = c;
            String next = new String(currChars);
            if (!visited.contains(next) && bankSet.contains(next)) {
              queue.offer(next);
              visited.add(next);
            }
          }
          // Don't forget this. Get it back before next char.
          currChars[j] = old;
        }
      }
      level++;
    }

    // There is no such a mutation.
    return -1;
  }

  // For testing.
  @SuppressWarnings("unused")
  public static void main(String[] args) {
    MinimumGeneticMutation solution = new MinimumGeneticMutation();

    // Test arguments.
    // int num = 24;
    // int target = 2;
    // System.out.println(solution.getInt(num, target));

    System.out.println("\ndone.");
  }

}
