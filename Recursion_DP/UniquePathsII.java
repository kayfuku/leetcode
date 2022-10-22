// 
// Author: 
// Date  : May 30, 2019

package leetcode;

public class UniquePathsII {

	// fields here.
	// private int count;

	public UniquePathsII() {
		// Initialization here.
		// count = 0;
	}

	// Bottom Up Dynamic Programming.
	// Iterating from the top-left corner to the bottom-right corner.
	// The number of ways to reach [i, j] is (the num of ways to reach [i, j - 1]) +
	// (the num of ways to reach [i - 1, j]).
	// If the cell has an obstacle, then the number of ways is considered 0.
	//
	// O(RC) time, O(1) space.
	// Author: @godayaldivya + kei
	// Date : May 30, 2019
	public int uniquePathsWithObstacles(int[][] obstacleGrid) {
		int R = obstacleGrid.length;
		int C = obstacleGrid[0].length;

		// If the starting cell has an obstacle, then simply return as there would be
		// no paths to the destination.
		if (obstacleGrid[0][0] == 1) {
			return 0;
		}

		// Dynamic Programming needs initial value.
		// Number of ways of reaching the starting cell = 1.
		// Use the given 2D array and overwrite it.
		obstacleGrid[0][0] = 1;

		// Fill the values for the first column.
		// Start from 1, not 0!
		for (int i = 1; i < R; i++) {
			// We just have to look at the square right above the current square because
			// the robot can only move either down or right.
			obstacleGrid[i][0] = (obstacleGrid[i][0] == 0 && obstacleGrid[i - 1][0] == 1) ? 1 : 0;
		}

		// Fill the values for the first row.
		// Start from 1, not 0!
		for (int j = 1; j < C; j++) {
			obstacleGrid[0][j] = (obstacleGrid[0][j] == 0 && obstacleGrid[0][j - 1] == 1) ? 1 : 0;
		}

		// Starting from cell[1, 1], fill up the values.
		// The number of ways of reaching cell[i][j] = cell[i - 1][j] + cell[i][j - 1]
		// Start from [1, 1], not [0, 0]!
		for (int i = 1; i < R; i++) {
			for (int j = 1; j < C; j++) {
				if (obstacleGrid[i][j] == 0) {
					obstacleGrid[i][j] = obstacleGrid[i - 1][j] + obstacleGrid[i][j - 1];
				} else {
					// Obstacle.
					obstacleGrid[i][j] = 0;
				}
			}
		}

		return obstacleGrid[R - 1][C - 1];
	}

	// Bottom Up Dynamic Programming. No need to review.
	// O(RC) time, O(1) space.
	// Author: @godayaldivya + kei
	// Date : May 30, 2019
	public int uniquePathsWithObstacles2(int[][] obstacleGrid) {
		int R = obstacleGrid.length;
		int C = obstacleGrid[0].length;

		// The goal cell is obstacle.
		if (obstacleGrid[R - 1][C - 1] == 1) {
			return 0;
		}

		// The number of ways of reaching to the goal is 1.
		obstacleGrid[R - 1][C - 1] = 1;

		// First initialize the R - 1 row and C - 1 column before scanning all the
		// cells.
		for (int i = R - 2; i >= 0; i--) {
			if (obstacleGrid[i][C - 1] == 0 && obstacleGrid[i + 1][C - 1] == 1) {
				// No obstacle in this cell and the number of ways of reaching to the goal from
				// this cell is
				// the same as that of reaching to the cell below it, plus the value of the
				// cell, which is 1.
				obstacleGrid[i][C - 1] = 1;
			} else {
				// Obstacle in this cell.
				obstacleGrid[i][C - 1] = 0;
			}
		}
		for (int j = C - 2; j >= 0; j--) {
			if (obstacleGrid[R - 1][j] == 0 && obstacleGrid[R - 1][j + 1] == 1) {
				// No obstacle in this cell and the number of ways of reaching to the goal from
				// this cell is
				// the same as that of reaching to the cell to the right of it, plus the value
				// of the cell, which is 1.
				obstacleGrid[R - 1][j] = 1;
			} else {
				// Obstacle in this cell.
				obstacleGrid[R - 1][j] = 0;
			}
		}

		// Scan through all the cells.
		for (int i = R - 2; i >= 0; i--) {
			for (int j = C - 2; j >= 0; j--) {
				if (obstacleGrid[i][j] == 0) {
					// No obstacle.
					obstacleGrid[i][j] = obstacleGrid[i + 1][j] + obstacleGrid[i][j + 1];
				} else {
					// Obstacle.
					obstacleGrid[i][j] = 0;
				}
			}
		}

		return obstacleGrid[0][0];
	}

	// For testing.
	@SuppressWarnings("unused")
	public static void main(String[] args) {
		UniquePathsII solution = new UniquePathsII();

		// Test arguments.
		// int num = 24;
		// int target = 2;
		// solution.getInt(num, target);

	}

}
