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


  // Author: kei (AC)
  // Date  : September 14, 2020
  public int unhappyFriends(int n, int[][] preferences, int[][] pairs) {
    // Map<Integer, Integer> pairMap = new HashMap<>();
    // Using array as a map is faster than using HashMap as a map if possible. 
    int[] pairMap = new int[n];

    for (int[] pair : pairs) {
      // pairMap.put(pair[0], pair[1]);
      // pairMap.put(pair[1], pair[0]);
      pairMap[pair[0]] = pair[1];
      pairMap[pair[1]] = pair[0];      
    }

    int count = 0;
    for (int i = 0; i < n; i++) {
      // int b = pairMap.get(i);
      int b = pairMap[i];      
      if (check(i, b, preferences, pairMap)) {
        count++;
      } 
    }

    return count;
  }

  // boolean check(int i, int b, int[][] preferences, Map<Integer, Integer> pairMap) {
  boolean check(int i, int b, int[][] preferences, int[] pairMap) {
    List<Integer> hiList = new ArrayList<>(); 
    for (int e : preferences[i]) {
      if (e == b) {
        break;
      }
      hiList.add(e);
    }
    for (int u : hiList) {
      for (int e : preferences[u]) {
        // int v = pairMap.get(u);
        int v = pairMap[u];
        if (e == v) {
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


