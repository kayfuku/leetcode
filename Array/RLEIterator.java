package leetcode;

public class RLEIterator {
  // fields and classes here.
  // private int count;

  public RLEIterator() {
    // Initialization here.
    // this.count = 0;

  }

  // Author: mrhhsmr + kei
  // Date : November 16, 2021
  int p;
  int[] encoded;

  public RLEIterator(int[] encoded) {
    this.encoded = encoded;
    p = 0;
  }

  public int next(int n) {
    // Note that if n is greater than encoded[p], then move forward.
    // If n is equal to encoded[p], then get out of the loop.
    while (n > 0 && p < encoded.length && n > encoded[p]) {
      n -= encoded[p];
      p += 2;
    }
    if (p >= encoded.length) {
      return -1;
    }
    // n <= encoded[p]

    encoded[p] -= n;
    return encoded[p + 1];
  }

  // For testing.
  @SuppressWarnings("unused")
  public static void main(String[] args) {
    RLEIterator solution = new RLEIterator();

    // Test arguments.
    // int num = 24;
    // int target = 2;
    // System.out.println(solution.getInt(num, target));

    System.out.println("\ndone.");
  }

}
