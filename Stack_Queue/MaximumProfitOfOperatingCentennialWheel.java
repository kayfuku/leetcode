//
// Author:
// Date  : September 27, 2020

package leetcode;

public class MaximumProfitOfOperatingCentennialWheel {
  // fields and classes here.
  // private int count;

  public MaximumProfitOfOperatingCentennialWheel() {
    // Initialization here.
    // this.count = 0;

  }

  // Author: cf_newbie + kei
  // Date : September 27, 2020
  public int minOperationsMaxProfit(int[] customers, int boardingCost, int runningCost) {
    // The problem statement says that if there is no senario where the profit is
    // positive, return -1. Set 'ret' to -1 and only update when profit is bigger
    // than 0.
    int maxProfit = 0;
    int ret = -1;

    int queue = 0, cum = 0;
    int i = 0;
    // We calculate profit for each rotation.
    // We must check the customers array til end.
    // Rotation could be bigger than array length and keep going while have > 0.
    while (i < customers.length || queue > 0) {
      // Convert i to number of rotations.
      int rot = i + 1;

      // Queueing.
      // Avoid index out of bound.
      if (i < customers.length) {
        // Add customers from array to the queue.
        queue += customers[i];
      }
      // Take out from the queue with capacity 4!
      int take = Math.min(4, queue);
      queue -= take;
      cum += take;

      // We calculate profit for each rotation.
      int profit = cum * boardingCost - rot * runningCost;

      if (profit > maxProfit) {
        maxProfit = profit;
        ret = rot;
      }
      i++;
    }

    // Just return ret because ret is initialized -1 and only updated when profit
    // is bigger than 0.
    return ret;
  }

  // Author: kei (AC)
  // Date : September 26, 2020
  public int minOperationsMaxProfit2(int[] customers, int boardingCost, int runningCost) {
    int maxProfit = Integer.MIN_VALUE;
    int ret = 0;
    int carry = 0, cum = 0;
    int i = 0;
    while (i < customers.length || carry > 0) {
      int profit = 0;
      if (i < customers.length) {
        carry += customers[i];
      }
      if (carry < 4) {
        cum += carry;
        carry = 0;
      } else {
        cum += 4;
        carry -= 4;
      }
      // i + 1 here, not i!
      profit = cum * boardingCost - (i + 1) * runningCost;

      if (profit > maxProfit) {
        maxProfit = profit;
        ret = i + 1;
      }
      i++;
    }

    return (maxProfit > 0) ? ret : -1;
  }

  // For testing.
  @SuppressWarnings("unused")
  public static void main(String[] args) {
    MaximumProfitOfOperatingCentennialWheel solution = new MaximumProfitOfOperatingCentennialWheel();

    // Test arguments.
    // int num = 24;
    // int target = 2;
    // solution.getInt(num, target);

  }

}
