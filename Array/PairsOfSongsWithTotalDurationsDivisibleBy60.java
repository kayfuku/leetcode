package leetcode;

public class PairsOfSongsWithTotalDurationsDivisibleBy60 {
  // fields and classes here.
  // private int count;

  public PairsOfSongsWithTotalDurationsDivisibleBy60() {
    // Initialization here.
    // this.count = 0;

  }

  // - If the sum is Divisible by 60 =>
  // a % 60 + b % 60 == 0 or a % 60 + b % 60 == 60
  // - Count the number of pairs => possible to just do one path
  // O(N) time, O(1) space.
  // Author: leetcode + kei
  // Date : February 2, 2021
  public int numPairsDivisibleBy60(int[] time) {
    // To count the number of times for each remainder
    int remainders[] = new int[60];
    int count = 0;
    for (int t : time) {
      if (t % 60 == 0) {
        // t whose remainder is 0 can be a counterpart for this t.
        count += remainders[0];
      } else {
        // t whose remainder is '60 - t % 60' can be a counterpart for this t.
        count += remainders[60 - t % 60];
      }
      // remember to update the remainders
      remainders[t % 60]++;
    }
    return count;
  }

  // O(N^2) time, O(1) space
  // Author: kei (TLE)
  // Date : February 2, 2021
  public int numPairsDivisibleBy60TLE(int[] time) {
    int count = 0;
    for (int i = 0; i < time.length; i++) {
      for (int j = i + 1; j < time.length; j++) {
        if ((time[i] + time[j]) % 60 == 0) {
          count++;
        }
      }
    }

    return count;
  }

  // For testing.
  @SuppressWarnings("unused")
  public static void main(String[] args) {
    PairsOfSongsWithTotalDurationsDivisibleBy60 solution = new PairsOfSongsWithTotalDurationsDivisibleBy60();

    // Test arguments.
    // int num = 24;
    // int target = 2;
    // System.out.println(solution.getInt(num, target));

    System.out.println("\ndone.");
  }

}
