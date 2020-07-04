//
// Author:
// Date : June 19, 2020

package leetcode;

public class MinimumNumberOfDaysToMakeMBouquets {
  // fields and classes here.
  // private int count;

  public MinimumNumberOfDaysToMakeMBouquets() {
    // Initialization here.
    // this.count = 0;

  }



  // Taking days as the x axis and the number of bouquets I can make as the y axis,
  // As days increases, then the number of bouquets also increases.
  // Therefore days and the number of bouquets form a monotonically non-decreasing curve.
  // The reason of non-decreasing is because even if days increases a little,
  // the number of bouquets might not change.
  //
  // So, I can perform Binary Search (Find leftmost) on that curve.
  //
  // Author: Akatsuki_ + kei
  // Date : June 19, 2020
  int minDays(int[] bloomDay, int m, int k) {
    int left = 1;
    int right = (int) Math.pow(10, 9);
    int ans = -1;

    // Binary Search (find leftmost).
    while (left <= right) {
      int mid = left - (left - right) / 2;

      // Check to see if I can make the m bouquets.
      // Count the number of bouquets after mid days passed.
      int flowers = 0, bouquets = 0;
      for (int i = 0; i < bloomDay.length; i++) {
        if (bloomDay[i] <= mid) {
          // i-th flower has bloomed.
          flowers++;
          if (flowers == k) {
            bouquets++;
            flowers = 0;
          }
        } else {
          flowers = 0;
        }
      }

      if (bouquets >= m) {
        ans = mid;
        // Search on the left even if bouquets == m because we need to find
        // the minimum days.
        right = mid - 1;
      } else {
        // Search on the right.
        left = mid + 1;
      }
    }

    return ans;
  }


  // For testing.
  @SuppressWarnings("unused")
  public static void main(String[] args) {
    MinimumNumberOfDaysToMakeMBouquets solution = new MinimumNumberOfDaysToMakeMBouquets();

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


