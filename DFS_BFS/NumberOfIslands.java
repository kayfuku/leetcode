// 
// Author: 
// Date  : July 6, 2019

package leetcode;

import java.util.LinkedList;
import java.util.Queue;

public class NumberOfIslands {
	// fields here. 
	//	private int count;

	public NumberOfIslands() {
		// Initialization here. 
		//		this.count = 0;
	}

	// 1. DFS.
	// O(MN) time, where M is the num of rows, and N is num of columns, 
	// because we visit every cell at most twice. 
	// O(MN) space, in the case that the grid map is filled with lands 
	// where DFS goes by M x N deep. 
	public int numIslands(char[][] grid) {
		if (grid == null || grid.length == 0) {
			return 0;
		}
		int nr = grid.length;
		int nc = grid[0].length;

		// Iterate through the grid, and if we find a '1', then we start DFS. 
		// And count the number of DFS. 
		int numIslands = 0;
		for (int r = 0; r < nr; r++) {
			for (int c = 0; c < nc; c++) {
				if (grid[r][c] == '1') {
					numIslands++;
					dfs(grid, r, c);
				}
			}
		}

		return numIslands;
	}
	private void dfs(char[][] grid, int r, int c) {
		if (r < 0 || r >= grid.length || c < 0 || c >= grid[0].length || 
				grid[r][c] == '0') {
			return;
		}

		// Mark as visited.
		grid[r][c] = '0';

		dfs(grid, r - 1, c);
		dfs(grid, r + 1, c);
		dfs(grid, r, c - 1);
		dfs(grid, r, c + 1);
	}


	// 2. BFS. 
	// O(MN) time, because we visit every cell at most twice. 
	// O(M + N) space, the size of the queue can grow up at most 
	// the perimeter of the grid. 
	public int numIslands2(char[][] grid) {
		if (grid == null || grid.length == 0) {
			return 0;
		}
		int nr = grid.length;
		int nc = grid[0].length;

		// Iterate through the grid, and if we find a '1', then we start BFS. 
		// And count the number of BFS.
		int numIslands = 0;
		for (int r = 0; r < nr; r++) {
			for (int c = 0; c < nc; c++) {
				if (grid[r][c] == '1') {
					numIslands++;
					bfs(grid, r, c);
				}
			}
		}

		return numIslands;
	}
	private void bfs(char[][] grid, int r, int c) {
		int nr = grid.length;
		int nc = grid[0].length;

		Queue<Integer> queue = new LinkedList<>();
		// Technique not to use double queues. 
		// 'r * nc + c' contains info of both row and column. 
		// Note that it starts from 0;
		queue.add(r * nc + c);
		// Mark as visited. 
		grid[r][c] = '0';
		while (!queue.isEmpty()) {
			int rc = queue.poll();
			int row = rc / nc;
			int col = rc % nc;

			// Neighbors. 
			// Up. 
			if (row - 1 >= 0 && grid[row - 1][col] == '1') {
				queue.add((row - 1) * nc + col);
				grid[row - 1][col] = '0';
			}
			// Down. 
			if (row + 1 < nr && grid[row + 1][col] == '1') {
				queue.add((row + 1) * nc + col);
				grid[row + 1][col] = '0';
			}
			// Left. 
			if (col - 1 >= 0 && grid[row][col - 1] == '1') {
				queue.add(row * nc + (col - 1));
				grid[row][col - 1] = '0';
			}
			// Right. 
			if (col + 1 < nc && grid[row][col + 1] == '1') {
				queue.add(row * nc + (col + 1));
				grid[row][col + 1] = '0';
			}
		}
	}
	
	
	// 3. Union Find. ** check later
	// 
	public int numIslands3(char[][] grid) {
		
		
		
		
		
		return 0;
	}

	
	
	
	
	

	// For testing. 
	public static void main(String[] args) {
		NumberOfIslands solution = new NumberOfIslands();

		// Test arguments. 
		// int num = 24;
		// int target = 2;
		// solution.getInt(num, target);



	}

}















