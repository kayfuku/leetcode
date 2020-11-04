//
// Author:
// Date  : October 29, 2020

package leetcode;

public class ImplementStrStr {
  // fields and classes here.
  // private int count;

  public ImplementStrStr() {
    // Initialization here.
    // this.count = 0;

  }

  // Approach 1
  // O((N-L)L) time, where N is the length of haystack and L is the length of
  // needle. O(1) space.
  // Author: leetcode + kei
  // Date : October 29, 2020
  public int strStr1(String haystack, String needle) {
    int L = needle.length(), N = haystack.length();

    // Note that 'start < N - L + 1'.
    for (int start = 0; start < N - L + 1; ++start) {
      if (haystack.substring(start, start + L).equals(needle)) {
        return start;
      }
    }
    return -1;
  }

  // Approach 2
  // O((N-L)L) time, where N is the length of haystack and L is the length of
  // needle. O(1) space.
  // Author: kei (AC)
  // Date : October 29, 2020
  public int strStr(String haystack, String needle) {
    int L = needle.length(), N = haystack.length();
    if (L == 0) {
      return 0;
    }

    for (int pN = 0; pN < N - L + 1; pN++) {
      if (haystack.charAt(pN) == needle.charAt(0)) {
        if (check(haystack, needle, pN)) {
          return pN;
        }
      }
    }

    return -1;
  }

  private boolean check(String haystack, String needle, int pN) {
    for (int pL = 0; pL < needle.length(); pL++) {
      if (haystack.charAt(pN) != needle.charAt(pL)) {
        return false;
      }
      pN++;
    }
    return true;
  }

  // Approach 3. Rabin Karp
  // You just say that there is an algorithm called "Rabin Karp".
  // No need to code.
  //
  // O(N) time, O(1) space
  // function to convert character to integer
  public int charToInt(int idx, String s) {
    return (int) s.charAt(idx) - (int) 'a';
  }

  public int strStr3(String haystack, String needle) {
    int L = needle.length(), n = haystack.length();
    if (L > n)
      return -1;

    // base value for the rolling hash function
    int a = 26;
    // modulus value for the rolling hash function to avoid overflow
    long modulus = (long) Math.pow(2, 31);

    // compute the hash of strings haystack[:L], needle[:L]
    long h = 0, ref_h = 0;
    for (int i = 0; i < L; ++i) {
      h = (h * a + charToInt(i, haystack)) % modulus;
      ref_h = (ref_h * a + charToInt(i, needle)) % modulus;
    }
    if (h == ref_h)
      return 0;

    // const value to be used often : a**L % modulus
    long aL = 1;
    for (int i = 1; i <= L; ++i)
      aL = (aL * a) % modulus;

    for (int start = 1; start < n - L + 1; ++start) {
      // compute rolling hash in O(1) time
      h = (h * a - charToInt(start - 1, haystack) * aL + charToInt(start + L - 1, haystack)) % modulus;
      if (h == ref_h)
        return start;
    }
    return -1;
  }

  // For testing.
  @SuppressWarnings("unused")
  public static void main(String[] args) {
    ImplementStrStr solution = new ImplementStrStr();

    // Test arguments.
    // int num = 24;
    // int target = 2;
    // solution.getInt(num, target);

  }

}
