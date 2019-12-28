// 
// Author: 
// Date  : December 25, 2019

package leetcode;

import java.util.HashMap;

public class ValidSudoku {
	// fields and classes here. 
	//private int count;

	public ValidSudoku() {
		// Initialization here. 
		//this.count = 0;

	}


	// One iteration. Set can be used. 
	// Author: @liaison and @andvary + kei
	// Date  : December 25, 2019
	@SuppressWarnings("unchecked")
	public boolean isValidSudoku(char[][] board) {
		// Initialize. 
		HashMap<Integer, Integer>[] rows = new HashMap[9];
		HashMap<Integer, Integer>[] columns = new HashMap[9];
		HashMap<Integer, Integer>[] boxes = new HashMap[9];
		for (int i = 0; i < 9; i++) {
			rows[i] = new HashMap<>();
			columns[i] = new HashMap<>();
			boxes[i] = new HashMap<>();
		}

		// Iterating through the board, for each square, 
		// validate row, column, and box at the same iteration. 
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				char num = board[i][j];
				if (num != '.') {
					int n = (int) num;
					// Get a box index. 
					int boxIndex = (i / 3) * 3 + j / 3;

					// Keep the current cell value in the maps. 
					rows[i].put(n, rows[i].getOrDefault(n, 0) + 1);
					columns[j].put(n, columns[j].getOrDefault(n, 0) + 1);
					boxes[boxIndex].put(n, boxes[boxIndex].getOrDefault(n, 0) + 1);

					// Check if this value has been already seen before. 
					if (rows[i].get(n) > 1 || columns[j].get(n) > 1 || boxes[boxIndex].get(n) > 1) {
						return false;
					}
				}
			}
		}

		return true;
	}







	// For testing. 
	public static void main(String[] args) {
		ValidSudoku solution = new ValidSudoku();

		// Test arguments. 
		// int num = 24;
		// int target = 2;
		// solution.getInt(num, target);



	}

}















