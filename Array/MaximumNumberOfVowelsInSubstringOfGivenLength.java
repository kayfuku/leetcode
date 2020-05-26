//
// Author:
// Date : May 23, 2020

package leetcode;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class MaximumNumberOfVowelsInSubstringOfGivenLength {
  // fields and classes here.
  // private int count;

  public MaximumNumberOfVowelsInSubstringOfGivenLength() {
    // Initialization here.
    // this.count = 0;

  }


  // Author: cai_lw + kei
  // Date : May 24, 2020
  public int maxVowelsS(String s, int k) {
    Set<Character> vowelSet = new HashSet<>(Arrays.asList('a', 'e', 'i', 'o', 'u'));
    char[] t = s.toCharArray();
    int max = 0;
    int count = 0;
    for (int i = 0; i < k; i++) {
      if (vowelSet.contains(t[i])) {
        count++;
      }
    }
    max = count;
    for (int i = k; i < s.length(); i++) {
      if (vowelSet.contains(t[i])) {
        count++;
      }
      if (vowelSet.contains(t[i - k])) {
        count--;
      }
      max = Math.max(max, count);
    }

    return max;
  }



  // For testing.
  @SuppressWarnings("unused")
  public static void main(String[] args) {
    MaximumNumberOfVowelsInSubstringOfGivenLength solution =
        new MaximumNumberOfVowelsInSubstringOfGivenLength();

    // Test arguments.
    // int num = 24;
    // int target = 2;
    // solution.getInt(num, target);



  }



  public void dummyMethod() {



  }



  public void dummyMethod2() {



  }



}


