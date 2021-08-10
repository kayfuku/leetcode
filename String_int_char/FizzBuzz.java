package leetcode;

import java.util.ArrayList;
import java.util.List;

public class FizzBuzz {
  // fields and classes here.
  // private int count;

  public FizzBuzz() {
    // Initialization here.
    // this.count = 0;

  }

  // Author: kei (AC)
  // Date : August 10, 2021
  public List<String> fizzBuzz(int n) {
    List<String> ans = new ArrayList<>();
    for (int i = 1; i <= n; i++) {
      if (i % 3 == 0 && i % 5 == 0) {
        ans.add("FizzBuzz");
      } else if (i % 3 == 0) {
        ans.add("Fizz");
      } else if (i % 5 == 0) {
        ans.add("Buzz");
      } else {
        // String.valueOf() is a lot faster than "" + i
        ans.add(String.valueOf(i));
      }
    }

    return ans;
  }

  // Faster
  // Author: leetcode + kei
  // Date : August 10, 2021
  public List<String> fizzBuzz2(int n) {
    List<String> ans = new ArrayList<>();
    for (int i = 1; i <= n; i++) {
      if (i % 3 == 0 && i % 5 != 0) {
        ans.add("Fizz");
      } else if (i % 3 != 0 && i % 5 == 0) {
        ans.add("Buzz");
      } else if (i % 3 == 0 && i % 5 == 0) {
        ans.add("FizzBuzz");
      } else {
        ans.add(String.valueOf(i));
      }
    }

    return ans;
  }

  // For testing.
  @SuppressWarnings("unused")
  public static void main(String[] args) {
    FizzBuzz solution = new FizzBuzz();

    // Test arguments.
    // int num = 24;
    // int target = 2;
    // System.out.println(solution.getInt(num, target));

    System.out.println("\ndone.");
  }

}
