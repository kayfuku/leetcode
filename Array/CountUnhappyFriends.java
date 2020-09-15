//
// Author:
// Date  : September 14, 2020

package leetcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CountUnhappyFriends {
  // fields and classes here.
  // private int count;

  public CountUnhappyFriends() {
    // Initialization here.
    // this.count = 0;

  }


  // Author:
  // Date  : September 14, 2020
  public int unhappyFriends(int n, int[][] preferences, int[][] pairs) {



    return 0;
  }


  // Author: kei (AC)
  // Date  : September 12, 2020
  public int unhappyFriends2(int n, int[][] preferences, int[][] pairs) {
    Map<Integer, Integer> pairMap = new HashMap<>();
    for (int[] pair : pairs) {
      pairMap.put(pair[0], pair[1]);
      pairMap.put(pair[1], pair[0]);
    }

    int count = 0;
    for (int i = 0; i < n; i++) {
      int b = pairMap.get(i);
      if (check(i, b, preferences, pairMap)) {
        count++;
      } 
    }

    return count;
  }

  boolean check(int i, int b, int[][] preferences, Map<Integer, Integer> pairMap) {
    List<Integer> hiList = new ArrayList<>(); 
    for (int e : preferences[i]) {
      if (e == b) {
        break;
      }
      hiList.add(e);
    }
    for (int h : hiList) {
      for (int e : preferences[h]) {
        if (e == pairMap.get(h)) {
          break;
        }
        if (e == i) {
          return true;
        }
      }
    }

    return false;
  }




  // For testing.
  @SuppressWarnings("unused")
  public static void main(String[] args) {
    SpecialPositionsInBinaryMatrix solution = new SpecialPositionsInBinaryMatrix();

    // Test arguments.
    // int num = 24;
    // int target = 2;
    // solution.getInt(num, target);



  }




}


