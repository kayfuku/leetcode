//
// Author:
// Date  : September 17, 2020

package leetcode;

import java.util.HashSet;
import java.util.Set;

public class NQueensII {
  // fields and classes here.
  // private int count;

  public NQueensII() {
    // Initialization here.
    // this.count = 0;

  }


  // 1. 'count' travels within the recursion stack.  
  // Author: AlexTheGreat + kei
  // Date  : September 17, 2020
  private final Set<Integer> occupiedCols2 = new HashSet<>();
  private final Set<Integer> occupiedDiag1s2 = new HashSet<>();
  private final Set<Integer> occupiedDiag2s2 = new HashSet<>();

  public int totalNQueens2(int n) {
    return totalNQueensHelper(0, 0, n);
  }
  
  private int totalNQueensHelper(int row, int count, int n) {

    for (int col = 0; col < n; col++) {
      // Check if it's under attack. 
      if (occupiedCols2.contains(col)) {
        continue;
      }
      int diag1 = row - col;
      if (occupiedDiag1s2.contains(diag1)) {
        continue;
      }
      int diag2 = row + col;
      if (occupiedDiag2s2.contains(diag2)) {
        continue;
      }

      if (row == n - 1) {
        count++;
      } else {
        // We can now place a queen here.
        occupiedCols2.add(col);
        occupiedDiag1s2.add(diag1);
        occupiedDiag2s2.add(diag2);

        // Move on to the next row. 
        count = totalNQueensHelper(row + 1, count, n);

        // Recover. 
        occupiedCols2.remove(col);
        occupiedDiag1s2.remove(diag1);
        occupiedDiag2s2.remove(diag2);
      }
    }
    
    return count;
  }

  
  // 2. 'count' sits on each recursion stack. I like it. 
  // We can only place one queen for each row. So, recurse on the rows and 
  // for each row, iterate through the columns. 
  // Author: AlexTheGreat + kei (AC)
  // Date  : September 17, 2020
  private final Set<Integer> occupiedCols = new HashSet<>();
  private final Set<Integer> occupiedDiag1s = new HashSet<>();
  private final Set<Integer> occupiedDiag2s = new HashSet<>();

  public int totalNQueens(int n) {
    return totalNQueensHelper2(0, n);
  }

  private int totalNQueensHelper2(int row, int n) {
    int count = 0;
    for (int col = 0; col < n; col++) {
      // Check if it's under attack. (Constraints)
      // If it's under attack, then move to the next square. 
      if (occupiedCols.contains(col)) {
        continue;
      }
      int diag1 = row - col;
      if (occupiedDiag1s.contains(diag1)) {
        continue;
      }
      int diag2 = row + col;
      if (occupiedDiag2s.contains(diag2)) {
        continue;
      }

      if (row == n - 1) {
        return 1;
      } 

      // We can now place a queen here. (Partial candidate solution)
      occupiedCols.add(col);
      occupiedDiag1s.add(diag1);
      occupiedDiag2s.add(diag2);

      // Move on to the next row and get the count. (Explore further)
      count += totalNQueensHelper2(row + 1, n);
      
      // We got the count. We're done. So, move the queen to the next square. (Backtrack)
      occupiedCols.remove(col);
      occupiedDiag1s.remove(diag1);
      occupiedDiag2s.remove(diag2);
    }
      
    return count;
  }
  



  // For testing.
  @SuppressWarnings("unused")
  public static void main(String[] args) {
    NQueensII solution = new NQueensII();

    // Test arguments.
    // int num = 24;
    // int target = 2;
    // solution.getInt(num, target);



  }




}


