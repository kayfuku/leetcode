//
// Author:
// Date : July 17, 2019

package leetcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class IsSubsequence {
  // fields and classes here.
  // private int count;

  public IsSubsequence() {
    // Initialization here.
    // this.count = 0;
  }


  // 1. Using two pointers.
  // Set the pointers to the first character of each string.
  // Iterating through string t, check if the characters are the same.
  // If it's same, then move forward both of pointers.
  // If it's not, then move on to the next character of t until finding the same character.
  //
  // https://leetcode.com/problems/is-subsequence/discuss/87254/Straight-forward-Java-simple-solution
  // O(N) time, where N is t length (~=500,000).
  // O(1) space.
  // Author: sampsonchan + kei
  // Date : July 16, 2019
  public boolean isSubsequence(String s, String t) {
    // s (len<=100), t (len~=500,000)
    // corner
    if (s == null || s.length() == 0) {
      return true;
    }
    if (t == null) {
      return false;
    }

    int idxS = 0, idxT = 0;
    while (idxT < t.length()) {
      if (s.charAt(idxS) == t.charAt(idxT)) {
        idxS++;
        if (idxS == s.length()) {
          return true;
        }
      }

      idxT++;
    }

    return false;
  }

  // 2. Using built-in indexOf().
  // Use indexOf(char, fromIdx).
  // For each character in s string (len<=100), check if t (len~=500,000) string contains that
  // character.
  // And then if it's in there, save that index, and start searching for the next character
  // after that index.
  //
  // https://leetcode.com/problems/is-subsequence/discuss/87384/java-1ms-solution
  // O(N) time, where N is t length (~=500,000).
  // O(1) space.
  //
  // Author: shuoshankou + kei
  // Date : July 16, 2019
  public boolean isSubsequence2(String s, String t) {
    if (s == null || s.length() == 0) {
      return true;
    }
    if (t == null) {
      return false;
    }

    int fromIdx = 0;
    // indexOf() takes O(N) time, so it looks this for loop takes O(MN) time, but
    // by using fromIdx, it takes only O(N) time.
    for (int i = 0; i < s.length(); i++) {
      char c = s.charAt(i);
      // Search in t from fromIdx.
      fromIdx = t.indexOf(c, fromIdx);
      if (fromIdx < 0) {
        // Not found. indexOf() returned -1.
        return false;
      }
      // Don't forget this!
      fromIdx++;
    }

    return true;
  }

  // 3. Follow Up.
  // If there are lots of incoming S, say S1, S2, ... , Sk where k >= 1B, and
  // you want to check one by one to see if T has its subsequence.
  // In this scenario, how would you change your code?
  //
  // Preprocessing and Search/Query using Binary Search.
  // https://leetcode.com/problems/is-subsequence/discuss/87321/Java-code-for-the-problem-(two-pointer)-and-the-follow-up-(Binary-Search)
  //
  // O(N) time.
  // O(N) time to preprocess, where N is t length (~=500,000).
  // O(MlogN) time to search/query, where M is s length (<=100), and N is list length (<=500,000).
  // O(N) space for preprocessing.
  //
  // Author: shuoshankou + kei
  // Date : July 16, 2019
  public boolean isSubsequence3(String s, String t) {
    // Preprocess t in order to improve search speed.
    // Index each character of t.
    // O(N) time.
    // K: character, V: a list of indices of t string
    Map<Character, List<Integer>> charToIdxList = new HashMap<>();
    for (int i = 0; i < t.length(); i++) {
      if (!charToIdxList.containsKey(t.charAt(i))) {
        charToIdxList.put(t.charAt(i), new ArrayList<Integer>());
      }
      charToIdxList.get(t.charAt(i)).add(i);
    }

    // Search/Query.
    // O(MlogN) time, where M is s length, and N is list length.
    int prevIdxOfT = -1;
    // Iterate string s.
    for (int i = 0; i < s.length(); i++) {
      char ch = s.charAt(i);

      int indexOfTForCh = getNextIndex(charToIdxList.get(ch), prevIdxOfT);
      if (indexOfTForCh < 0) {
        // indexOfTForCh == -1, which means the ch is not in t.
        return false;
      }

      prevIdxOfT = indexOfTForCh;
    }

    return true;
  }

  // Binary Search R = M, Continuous version. (Next int)
  // Binary Search to find an index of the elem in the list, which is the smallest, but
  // greater than the 'prevElem'.
  // Note that the list is a list of indices in string t.
  //
  // O(logN) time, where N is the list length.
  // Author: kei (+ shuoshankou)
  // Date : July 17, 2019
  public int getNextIndex(List<Integer> list, int prevElem) {
    if (list == null) {
      return -1;
    }
    int left = 0, right = list.size();

    while (left < right) {
      int mid = left + (right - left) / 2;
      if (list.get(mid) > prevElem) {
        // Search on the left subarray to find the index of the smallest elem that is bigger than
        // the previous elem.
        right = mid;
      } else {
        // list.get(mid) <= prevElem
        // The mid elem value (index in t string) is less than or equal to the
        // previous elem. This elem (index in t string) cannot be used as part of s subsequence
        // because we cannot disturb the relative order.
        // Search on the right subarray to find a bigger elem than the previous elem.
        left = mid + 1;
      }
    }

    // 'left' is the index of the next elem.
    // Note that it returns an index at which the next elem would be
    // if prevElem is not in the list, which is ok as long as prevElem is not bigger than
    // or equal to the last elem. In this case, t has an index for the character ch.
    // However, if prevElem is bigger than or equal to the last element, then
    // it returns list.size(), an index out of bound.
    // 'left' is an index of the 'list'.
    // Return the index of t of the character ch.
    return (left != list.size()) ? list.get(left) : -1;
  }



  // For testing.
  public static void main(String[] args) {
    IsSubsequence solution = new IsSubsequence();

    // Test arguments.
    // int num = 24;
    // int target = 2;
    // solution.getInt(num, target);



  }

}


