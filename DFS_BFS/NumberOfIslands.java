// 
// Author: 
// Date  : July 6, 2019

package leetcode;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class NumberOfIslands {
	// fields here.
	// private int count;

	public NumberOfIslands() {
		// Initialization here.
		// this.count = 0;
	}

	// 1. DFS. The best solution is the BFS at the bottom.
	// O(MN) time, where M is the num of rows, and N is num of columns,
	// because we visit every square at most twice.
	// O(MN) space, in the case that the grid map is filled with lands
	// where DFS goes by M x N deep.
	public int numIslands(char[][] grid) {
		// Epithet code.
		if (grid == null || grid.length == 0) {
			return 0;
		}
		int nr = grid.length;
		int nc = grid[0].length;

		// Iterate through the grid, and if we find a '1', then we start DFS.
		// And count the number of triggers of DFS.
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
		if (r < 0 || r >= grid.length || c < 0 || c >= grid[0].length || grid[r][c] == '0') {
			return;
		}

		// Mark as visited using '0'.
		grid[r][c] = '0';

		dfs(grid, r - 1, c); // up
		dfs(grid, r + 1, c); // down
		dfs(grid, r, c - 1); // left
		dfs(grid, r, c + 1); // right
	}

	// 2. BFS. The bottom one is the best.
	// O(MN) time, because we visit every square at most twice.
	// O(M + N) space, the size of the queue can grow up at most
	// the perimeter of the grid.
	public int numIslands2(char[][] grid) {
		// Epithet code.
		if (grid == null || grid.length == 0) {
			return 0;
		}
		int nr = grid.length;
		int nc = grid[0].length;

		// Iterate through the grid, and if we find a '1', then we start BFS.
		// And count the number of triggers of BFS.
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
		// => How about int[]? => Yes. See the bottom.
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
				// Mark as visited.
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

	// 3. Union Find.
	// Author: imsure + kei (AC)
	// Date : July 31, 2019
	class UnionFind {
		int count; // # of islands
		int[] parent;
		int[] rank;

		// Initialization
		public UnionFind(char[][] grid) { // for problem 200
			count = 0;
			int m = grid.length;
			int n = grid[0].length;
			parent = new int[m * n];
			rank = new int[m * n];
			for (int i = 0; i < m; i++) {
				for (int j = 0; j < n; j++) {
					if (grid[i][j] == '1') {
						// Initialization. Every node has its own node as a parent node.
						// Store the coordinate [i, j] itself as the parent coordinate.
						parent[i * n + j] = i * n + j;
						// Count the number of '1'
						count++;
					}
					rank[i * n + j] = 0;
				}
			}
		}

		// ex)
		// 2 - 0 - 3
		// |
		// 1 - 4
		// i: 0 1 2 3 4 find(4) i: 0 1 2 3 4
		// p: 2 0 2 0 1 ======> p: 2 2 2 0 2
		public int find(int i) { // path compression <= find root + path compression?
			if (parent[i] != i) {
				parent[i] = find(parent[i]);
			}
			return parent[i];
		}

		// Connect the components.
		public void union(int x, int y) { // union with rank
			int rootx = find(x);
			int rooty = find(y);
			if (rootx != rooty) {
				if (rank[rootx] > rank[rooty]) {
					// Add lower tree y to higher tree x.
					// The parent of rooty is rootx.
					parent[rooty] = rootx;
				} else if (rank[rootx] < rank[rooty]) {
					// Add lower tree x to higher tree y.
					parent[rootx] = rooty;
				} else {
					parent[rooty] = rootx;
					rank[rootx]++;
				}
				// Decrement the count because we just connected '1' and '1'.
				// 'count' is eventually the number of islands.
				count--;
			}
		}

		public int getCount() {
			return count;
		}
	}

	public int numIslands3(char[][] grid) {
		if (grid == null || grid.length == 0) {
			return 0;
		}

		int nr = grid.length;
		int nc = grid[0].length;

		UnionFind uf = new UnionFind(grid);
		for (int r = 0; r < nr; r++) {
			for (int c = 0; c < nc; c++) {
				if (grid[r][c] == '1') {
					// Mark as visited.
					grid[r][c] = '0';

					// Up.
					if (r - 1 >= 0 && grid[r - 1][c] == '1') {
						uf.union(r * nc + c, (r - 1) * nc + c);
					}
					// Down.
					if (r + 1 < nr && grid[r + 1][c] == '1') {
						uf.union(r * nc + c, (r + 1) * nc + c);
					}
					// Left.
					if (c - 1 >= 0 && grid[r][c - 1] == '1') {
						uf.union(r * nc + c, r * nc + c - 1);
					}
					// Right.
					if (c + 1 < nc && grid[r][c + 1] == '1') {
						uf.union(r * nc + c, r * nc + c + 1);
					}
				}
			}
		}

		return uf.getCount();
	}

	// Review. This is the best!
	// Author: kei (AC)
	// Date : January 6, 2020
	public int numIslandsR(char[][] grid) {
		// Epithet code.
		if (grid == null || grid.length == 0) {
			return 0;
		}
		int R = grid.length;
		int C = grid[0].length;

		// Iterate through the grid, and if we find a '1', then we start BFS.
		// And count the number of triggers of BFS.
		int numIslands = 0;
		for (int r = 0; r < R; r++) {
			for (int c = 0; c < C; c++) {
				if (grid[r][c] == '1') {
					numIslands++;
					bfsR2(grid, r, c);
				}
			}
		}

		return numIslands;
	}

	private static final char VISITED = '0';
	private static final int[] ROW_DIR = new int[] { -1, 1, 0, 0 };
	private static final int[] COL_DIR = new int[] { 0, 0, -1, 1 };

	private void bfsR2(char[][] grid, int r, int c) {
		int R = grid.length;
		int C = grid[0].length;

		Queue<int[]> queue = new LinkedList<>();
		queue.add(new int[] { r, c });
		// Mark as visited.
		grid[r][c] = VISITED;

		while (!queue.isEmpty()) {
			int[] square = queue.poll();
			int row = square[0];
			int col = square[1];

			// Neighbors.
			for (int i = 0; i < 4; i++) {
				int nr = row + ROW_DIR[i];
				int nc = col + COL_DIR[i];
				if (nr < 0 || nc < 0 || nr >= R || nc >= C || grid[nr][nc] == VISITED) {
					continue;
				}
				queue.add(new int[] { nr, nc });
				grid[nr][nc] = VISITED;
			}

		}
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
