package leetcode;

public class MaximumPointsYouCanObtainFromCards {
  // fields and classes here.
  // private int count;

  public MaximumPointsYouCanObtainFromCards() {
    // Initialization here.
    // this.count = 0;

  }

  // Author: kei (AC)
  // Date : October 6, 2021
  public int maxScore(int[] cardPoints, int k) {
    int sum = 0;
    int n = cardPoints.length;
    int start = n - k, end = n - 1;
    for (int i = start; i < n; i++) {
      sum += cardPoints[i];
    }
    int max = sum;
    while (start != 0) {
      end = (end + 1) % n;
      sum += cardPoints[end];
      sum -= cardPoints[start];
      start = (start + 1) % n;
      max = Math.max(max, sum);
    }

    return max;
  }

  // For testing.
  @SuppressWarnings("unused")
  public static void main(String[] args) {
    MaximumPointsYouCanObtainFromCards solution = new MaximumPointsYouCanObtainFromCards();

    // Test arguments.
    // int num = 24;
    // int target = 2;
    // System.out.println(solution.getInt(num, target));

    System.out.println("\ndone.");
  }

}
