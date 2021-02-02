package leetcode;

import java.util.Arrays;

public class CanYouEatYouFavoriteCandyOnYourFavoriteDay {
  // fields and classes here.
  // private int count;

  public CanYouEatYouFavoriteCandyOnYourFavoriteDay() {
    // Initialization here.
    // this.count = 0;

  }

  // Author: int65535 + kei
  // Date : February 1, 2021
  public boolean[] canEat(int[] candiesCount, int[][] queries) {
    int n = candiesCount.length;
    // Cumulative sum
    long[] cum = new long[n + 1];
    for (int i = 0; i < n; i++) {
      cum[i + 1] = cum[i] + candiesCount[i];
    }

    boolean[] ans = new boolean[queries.length];
    for (int i = 0; i < queries.length; i++) {
      int type = queries[i][0];
      long day = queries[i][1];
      long cap = queries[i][2];

      // ans[i] = cum[type + 1] >= (day + 1) * 1 && cum[type] + 1 <= (day + 1) * cap;
      long minDay = cum[type] / cap;
      long maxDay = cum[type + 1] - 1;
      ans[i] = day >= minDay && day <= maxDay;
    }
    return ans;
  }

  // NG!
  // Author: kei (TLE)
  // Date : February 1, 2021
  public boolean[] canEat2(int[] candiesCount, int[][] queries) {
    boolean[] ans = new boolean[queries.length];
    Arrays.fill(ans, true);

    for (int i = 0; i < queries.length; i++) {
      int type = queries[i][0];
      long day = queries[i][1];
      long cap = queries[i][2];

      int minType = candiesCount.length;
      long maxCandies = (day + 1) * 1;
      for (int j = 0; j < candiesCount.length; j++) {
        maxCandies -= candiesCount[j];
        if (maxCandies <= 0) {
          minType = j;
          break;
        }
      }
      if (minType > type) {
        ans[i] = false;
        continue;
      }

      int maxType = candiesCount.length;
      maxCandies = (day + 1) * cap;
      for (int j = 0; j < candiesCount.length; j++) {
        maxCandies -= candiesCount[j];
        if (maxCandies <= 0) {
          maxType = j;
          break;
        }
      }
      if (maxType < type) {
        ans[i] = false;
      }
    }

    return ans;
  }

  // For testing.
  @SuppressWarnings("unused")

  public static void main(String[] args) {
    CanYouEatYouFavoriteCandyOnYourFavoriteDay solution = new CanYouEatYouFavoriteCandyOnYourFavoriteDay();

    // Test arguments.
    // int num = 24;
    // int target = 2;
    // System.out.println(solution.getInt(num, target));

    System.out.println("\ndone.");
  }

}
