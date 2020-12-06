package leetcode;

import java.util.ArrayList;
import java.util.List;

public class SelfDividingNumbers {
  // fields and classes here.
  // private int count;

  public SelfDividingNumbers() {
    // Initialization here.
    // this.count = 0;

  }

  // Author: kei (AC)
  // Date : December 5, 2020
  public List<Integer> selfDividingNumbers(int left, int right) {
    List<Integer> ans = new ArrayList<>();
    for (int i = left; i <= right; i++) {
      if (isSelfDividing(i)) {
        ans.add(i);
      }
    }
    return ans;
  }

  private boolean isSelfDividing(int i) {
    int x = i;
    while (x != 0) {
      int d = x % 10;
      if (d == 0 || i % d != 0) {
        return false;
      }
      x /= 10;
    }
    return true;
  }

  // For testing.
  @SuppressWarnings("unused")
  public static void main(String[] args) {
    SelfDividingNumbers solution = new SelfDividingNumbers();

    // Test arguments.
    // int num = 24;
    // int target = 2;
    // solution.getInt(num, target);

  }

}
