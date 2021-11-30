package leetcode;

import java.util.ArrayDeque;
import java.util.Deque;

public class LongestAbsoluteFilePath {
  // fields and classes here.
  // private int count;

  public LongestAbsoluteFilePath() {
    // Initialization here.
    // this.count = 0;

  }

  // 2. Stack
  // Author: sky-xu + kei
  // Date : November 19, 2021
  public int lengthLongestPath2(String input) {
    Deque<Integer> stack = new ArrayDeque<>();
    stack.push(0); // "dummy" length
    int maxLen = 0;
    for (String s : input.split("\n")) {
      // number of "\t". Note that '\t' is length 1.
      int numTab = s.lastIndexOf("\t") + 1;
      // Find parent
      // stack.size(): size + dummy, numTab + 1: num of strings
      while (stack.size() - 1 >= numTab + 1) {
        stack.pop();
      }
      // remove "/t", add"/"
      int len = stack.peek() - numTab + 1 + s.length();
      stack.push(len);
      // check if it is file
      if (s.contains(".")) {
        maxLen = Math.max(maxLen, len - 1);
      }
    }
    return maxLen;
  }

  // 1. Stack using array, no need to pop
  // Author: sky-xu + kei
  // Date : November 30, 2021
  public int lengthLongestPath(String input) {
    String[] paths = input.split("\n");
    int[] stack = new int[paths.length + 1];
    int maxLen = 0;
    for (String s : paths) {
      // number of "\t". Note that '\t' is length 1.
      int numTab = s.lastIndexOf("\t") + 1;
      // remove "/t", add"/"
      int curLen = stack[numTab] - numTab + 1 + s.length();
      // Push it.
      stack[numTab + 1] = curLen;
      if (s.contains(".")) {
        maxLen = Math.max(maxLen, curLen - 1);
      }
    }
    return maxLen;
  }

  // For testing.
  @SuppressWarnings("unused")
  public static void main(String[] args) {
    LongestAbsoluteFilePath solution = new LongestAbsoluteFilePath();

    // Test arguments.
    // int num = 24;
    // int target = 2;
    // System.out.println(solution.getInt(num, target));

    System.out.println("\ndone.");
  }

}
