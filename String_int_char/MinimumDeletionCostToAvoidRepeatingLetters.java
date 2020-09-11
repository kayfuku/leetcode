//
// Author:
// Date  : September ?, 2020

package leetcode;

public class MinimumDeletionCostToAvoidRepeatingLetters {
  // fields and classes here.
  // private int count;

  public MinimumDeletionCostToAvoidRepeatingLetters() {
    // Initialization here.
    // this.count = 0;

  }


  // Author: uwi + kei
  // Date  : September 8, 2020
  public int minCost(String S, int[] cost) {
    // This is important to simplify the code. 
    char[] s = S.toCharArray();

    // We just have to subtract max cost among a group of characters (including len 1) 
    // from total cost. 
    // Calculate total cost. 
    int total = 0;
    for (int v : cost) {
      total += v;
    }

    // Initialize pre to any character other than lowercase English letters. 
    char pre = '0';
    int max = 0;

    // Iterate through two arrays using foreach and pointer. 
    int p = 0;
    for (char c : s) {
      if (c != pre) {
        total -= max;
        // Reset max. 
        max = 0;
        pre = c;
      }
      max = Math.max(max, cost[p]);
      p++;
    }
    // Don't forget this. 
    total -= max;

    return total;
  }



  // For testing.
  @SuppressWarnings("unused")
  public static void main(String[] args) {
    ReplaceAllQsToAvoidConsecutiveRepeatingCharacters solution = new ReplaceAllQsToAvoidConsecutiveRepeatingCharacters();

    // Test arguments.
    // int num = 24;
    // int target = 2;
    // solution.getInt(num, target);



  }




}


