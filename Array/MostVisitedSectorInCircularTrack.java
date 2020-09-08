//
// Author:
// Date : August ?, 2020

package leetcode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MostVisitedSectorInCircularTrack {
  // fields and classes here.
  // private int count;

  public MostVisitedSectorInCircularTrack() {
    // Initialization here.
    // this.count = 0;

  }


  // Author: jtjl + kei (AC)
  // Date : August 25, 2020
  public List<Integer> mostVisited(int n, int[] rounds) {
    List<Integer> ret = new ArrayList<>();
    int s = rounds[0];
    int e = rounds[rounds.length - 1];
    if (s <= e) {
      for (int i = s; i <= e; i++) {
        ret.add(i);
      }
    } else {
      // s > e
      for (int i = s; i <= e + n; i++) {
        ret.add((i - 1) % n + 1);
      }
      Collections.sort(ret);
    }

    return ret;
  }


  // Better
  // Author: kei (AC)
  // Date : August 22, 2020
  public List<Integer> mostVisited2(int n, int[] rounds) {
    List<Integer> ret = new ArrayList<>();
    int s = rounds[0];
    int e = rounds[rounds.length - 1];
    if (s <= e) {
      for (int i = s; i <= e; i++) {
        ret.add(i);
      }
    } else {
      for (int i = 1; i <= e; i++) {
        ret.add(i);
      }
      for (int i = s; i <= n; i++) {
        ret.add(i);
      }
    }

    return ret;
  }



  // For testing.
  @SuppressWarnings("unused")
  public static void main(String[] args) {
    MostVisitedSectorInCircularTrack solution = new MostVisitedSectorInCircularTrack();

    // Test arguments.
    // int num = 24;
    // int target = 2;
    // solution.getInt(num, target);

    int n = 3;
    int[] rounds = {3, 2, 1, 2, 1, 3, 2, 1, 2, 1, 3, 2, 3, 1};
    System.out.println(solution.mostVisited(n, rounds));



  }



  public void dummyMethod() {



  }



  public void dummyMethod2() {



  }



}


