package leetcode;

import java.util.PriorityQueue;

public class NumberOfOrdersInBacklog {
  // fields and classes here.
  // private int count;

  public NumberOfOrdersInBacklog() {
    // Initialization here.
    // this.count = 0;

  }

  // Do not subtract one by one from amount. Use subtraction.
  // Author: heyshb + kei
  // Date : March 20, 2021
  public int getNumberOfBacklogOrders(int[][] orders) {
    // orders[i] = [pricei, amounti, orderTypei]
    // int[price, count]
    PriorityQueue<int[]> buyMaxH = new PriorityQueue<>((x, y) -> y[0] - x[0]);
    PriorityQueue<int[]> sellMinH = new PriorityQueue<>((x, y) -> x[0] - y[0]);

    for (int[] o : orders) {
      int price = o[0];
      int amount = o[1];
      int type = o[2];
      if (type == 0) {
        // buy
        while (amount > 0 && !sellMinH.isEmpty() && sellMinH.peek()[0] <= price) {
          int[] sell = sellMinH.poll();
          int del = Math.min(amount, sell[1]);
          amount -= del;
          sell[1] -= del;
          if (sell[1] > 0) {
            sellMinH.offer(sell);
          }
        }
        if (amount > 0) {
          buyMaxH.offer(new int[] { price, amount });
        }
      } else {
        // sell
        while (amount > 0 && !buyMaxH.isEmpty() && buyMaxH.peek()[0] >= price) {
          int[] buy = buyMaxH.poll();
          int del = Math.min(amount, buy[1]);
          amount -= del;
          buy[1] -= del;
          if (buy[1] > 0) {
            buyMaxH.offer(buy);
          }
        }
        if (amount > 0) {
          sellMinH.offer(new int[] { price, amount });
        }
      }
    }

    final int D = 1000000007;
    long ret = 0;
    while (!buyMaxH.isEmpty()) {
      ret += buyMaxH.poll()[1];
    }
    while (!sellMinH.isEmpty()) {
      ret += sellMinH.poll()[1];
    }

    return (int) (ret % D);
    // NG!
    // return (int) ret % D;
  }

  // // NG!
  // // Author: kei (TLE)
  // // Date : March 20, 2021
  // public int getNumberOfBacklogOrdersTLE(int[][] orders) {
  // // orders[i] = [pricei, amounti, orderTypei]
  // PriorityQueue<Integer> maxHeap = new PriorityQueue<>((x, y) -> y - x);
  // PriorityQueue<Integer> minHeap = new PriorityQueue<>((x, y) -> x - y);

  // for (int[] order : orders) {
  // int price = order[0];
  // int amount = order[1];
  // int type = order[2];
  // if (type == 0) {
  // // buy
  // while (amount > 0 && !minHeap.isEmpty() && minHeap.peek() <= price) {
  // minHeap.poll();
  // amount--;
  // }
  // while (amount > 0) {
  // maxHeap.offer(price);
  // amount--;
  // }
  // } else {
  // // sell
  // while (amount > 0 && !maxHeap.isEmpty() && maxHeap.peek() >= price) {
  // maxHeap.poll();
  // amount--;
  // }
  // while (amount > 0) {
  // minHeap.offer(price);
  // amount--;
  // }
  // }
  // }
  // int D = 1000000007;

  // return (int) ((long) minHeap.size() + maxHeap.size()) % D;
  // }

  // For testing.
  @SuppressWarnings("unused")
  public static void main(String[] args) {
    NumberOfOrdersInBacklog solution = new NumberOfOrdersInBacklog();

    // Test arguments.
    // int num = 24;
    // int target = 2;
    // System.out.println(solution.getInt(num, target));

    System.out.println("\ndone.");
  }

}
