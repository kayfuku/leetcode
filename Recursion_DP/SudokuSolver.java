//
// Author:
// Date  : September 18, 2020

package leetcode;

public class SudokuSolver {
  // fields and classes here.
  // private int count;

  public SudokuSolver() {
    // Initialization here.
    // this.count = 0;

  }

  // Author: leetcode + kei
  // Date : September 18, 2020

  // Box size
  int n = 3;
  // Row size
  int N = n * n;

  // To check if each of the digits 1-9 must occur exactly once.
  int[][] rows = new int[N][N + 1];
  int[][] columns = new int[N][N + 1];
  int[][] boxes = new int[N][N + 1];

  char[][] board;

  boolean sudokuSolved = false;

  public void solveSudoku(char[][] board) {
    this.board = board;

    // Init rows, columns and boxes.
    for (int i = 0; i < N; i++) {
      for (int j = 0; j < N; j++) {
        char num = board[i][j];
        if (num != '.') {
          int d = Character.getNumericValue(num);
          placeNumber(d, i, j);
        }
      }
    }

    backtrack(0, 0);
  }

  // Backtracking
  public void backtrack(int row, int col) {
    if (board[row][col] != '.') {
      moveToNextSquare(row, col);
      return;
    }

    // The cell is empty.
    // Iterate over all numbers from 1 to 9.
    for (int d = 1; d < 10; d++) {
      if (couldPlace(d, row, col)) {
        placeNumber(d, row, col);
        moveToNextSquare(row, col);
        if (sudokuSolved) {
          break;
        }
        // Recover.
        removeNumber(d, row, col);
      }
    }
  }

  // Check if one could place a number d in (row, col) cell.
  public boolean couldPlace(int d, int row, int col) {
    // Box index
    int idx = (row / n) * n + col / n;
    // Check if there is no 'd' in the row, col, and box.
    return rows[row][d] + columns[col][d] + boxes[idx][d] == 0;
  }

  // Place a number d in (row, col) cell.
  public void placeNumber(int d, int row, int col) {
    // Box index
    int idx = (row / n) * n + col / n;

    rows[row][d]++;
    columns[col][d]++;
    boxes[idx][d]++;
    // Convert int number to corresponding char and put it in the board.
    board[row][col] = (char) (d + '0');
  }

  // Call backtrack function recursively to continue to place numbers
  // till we have a solution.
  public void moveToNextSquare(int row, int col) {
    if ((col == N - 1) && (row == N - 1)) {
      // We're in the last cell.
      // That means we have the solution.
      sudokuSolved = true;
    } else if (col == N - 1) {
      // We're in the end of the row.
      // Go to the next row and col 0.
      backtrack(row + 1, 0);
    } else {
      // Go to the next column.
      backtrack(row, col + 1);
    }
  }

  // Remove a number which didn't lead to a solution.
  public void removeNumber(int d, int row, int col) {
    int idx = (row / n) * n + col / n;

    rows[row][d]--;
    columns[col][d]--;
    boxes[idx][d]--;
    board[row][col] = '.';
  }

  // For testing.
  @SuppressWarnings("unused")
  public static void main(String[] args) {
    SudokuSolver solution = new SudokuSolver();

    // Test arguments.
    // int num = 24;
    // int target = 2;
    // solution.getInt(num, target);

  }

}
