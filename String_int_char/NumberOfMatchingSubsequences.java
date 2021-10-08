package leetcode;

import java.util.ArrayList;

public class NumberOfMatchingSubsequences {
  // fields and classes here.
  // private int count;

  public NumberOfMatchingSubsequences() {
    // Initialization here.
    // this.count = 0;

  }

  // O(S.length() * N) time, where N is # of words.
  // O(C) space, where C is total # of characters in words.
  // Author: leetcode + kei
  // Date : October 8, 2021
  @SuppressWarnings("unchecked")
  public int numMatchingSubseq(String S, String[] words) {
    ArrayList<Node>[] waitingLetters = new ArrayList[26];
    // Don't use foreach for this purpose. It won't work!
    for (int i = 0; i < 26; i++) {
      waitingLetters[i] = new ArrayList<>();
    }
    for (String word : words) {
      waitingLetters[word.charAt(0) - 'a'].add(new Node(word, 0));
    }

    int ans = 0;
    for (char c : S.toCharArray()) {
      // Get the words that start with char c.
      // Deep copy of array list.
      ArrayList<Node> wordsStartWithC = new ArrayList<>(waitingLetters[c - 'a']);
      waitingLetters[c - 'a'].clear();

      for (Node node : wordsStartWithC) {
        node.index++;
        if (node.index == node.word.length()) {
          ans++;
        } else {
          // Move the node(word) to the next bucket.
          waitingLetters[node.word.charAt(node.index) - 'a'].add(node);
        }
      }
      wordsStartWithC.clear();
    }

    return ans;
  }

  class Node {
    String word;
    int index; // Points to the char that we wait for.

    public Node(String w, int i) {
      word = w;
      index = i;
    }
  }

  // For testing.
  @SuppressWarnings("unused")
  public static void main(String[] args) {
    NumberOfMatchingSubsequences solution = new NumberOfMatchingSubsequences();

    // Test arguments.
    // int num = 24;
    // int target = 2;
    // System.out.println(solution.getInt(num, target));
    String S = "abcde";
    String[] words = { "a", "bb", "acd", "ace" };
    System.out.println(solution.numMatchingSubseq(S, words));

    System.out.println("\ndone.");
  }

}
