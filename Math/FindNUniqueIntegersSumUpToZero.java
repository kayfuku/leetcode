package leetcode;

public class FindNUniqueIntegersSumUpToZero {
  // fields and classes here.
  // private int count;

  public FindNUniqueIntegersSumUpToZero() {
    // Initialization here.
    // this.count = 0;

  }

  // n(a_0 + a_n-1)/2 = 0, a_n = a_0 + n * d
  // => a_i = (1 - n) + i * 2 if a_0 = 1 - n and d = 2
  //
  // Author: lee215 + kei
  // Date : June 19, 2021
  public int[] sumZero(int n) {
    int[] arr = new int[n];
    for (int i = 0; i < n; i++) {
      arr[i] = (1 - n) + i * 2;
    }
    return arr;
  }

  // For testing.
  @SuppressWarnings("unused")
  public static void main(String[] args) {
    FindNUniqueIntegersSumUpToZero solution = new FindNUniqueIntegersSumUpToZero();

    // Test arguments.
    // int num = 24;
    // int target = 2;
    // System.out.println(solution.getInt(num, target));

    System.out.println("\ndone.");
  }

}
