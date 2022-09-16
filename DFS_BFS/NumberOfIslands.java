// 
// Author: 
// Date  : July 6, 2019

package leetcode;

import java.util.LinkedList;
import java.util.Queue;

public class NumberOfIslands {
	// fields here.
	// private int count;

	public NumberOfIslands() {
		// Initialization here.
		// this.count = 0;
	}

	// 1. DFS. This is the best for DFS.
	// O(MN) time, where M is the num of rows, and N is num of columns,
	// because we visit every square at most twice.
	// O(MN) space, in the case that the grid map is filled with lands
	// where DFS goes by M x N deep.

	// up, right, down, left
	private static final int[][] D = { { -1, 0 }, { 0, 1 }, { 1, 0 }, { 0, -1 } };

	public int numIslands(char[][] grid) {
		// Epithet code. Note that grid == null is first.
		if (grid == null || grid.length == 0) {
			return 0;
		}
		int m = grid.length;
		int n = grid[0].length;

		// Iterate through the grid, and if we find a '1', then we start DFS.
		// And count the number of triggers of DFS.
		int numIslands = 0;
		for (int r = 0; r < m; r++) {
			for (int c = 0; c < n; c++) {
				if (grid[r][c] == '1') {
					numIslands++;
					dfs(grid, r, c);
				}
			}
		}

		return numIslands;
	}

	private void dfs(char[][] grid, int r, int c) {
		if (r < 0 || r >= grid.length || c < 0 || c >= grid[0].length || grid[r][c] == VISITED) {
			return;
		}

		// Mark as visited using '0'.
		grid[r][c] = VISITED;

		for (int[] d : D) {
			dfs(grid, r + d[0], c + d[1]);
		}
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

	// BFS. Review. This is the best for BFS!
	// Author: kei (AC)
	// Date : January 6, 2020, April 9, 2021
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
	// 0: up, 1: right, 2: down, 3: left
	private static final int[][] DIR = { { -1, 0 }, { 0, 1 }, { 1, 0 }, { 0, -1 } };

	private void bfsR2(char[][] grid, int r, int c) {
		int R = grid.length;
		int C = grid[0].length;

		Queue<int[]> queue = new LinkedList<>();
		// Adding to the queue and marking as visited.
		queue.add(new int[] { r, c });
		grid[r][c] = VISITED;

		while (!queue.isEmpty()) {
			int[] square = queue.poll();
			int row = square[0];
			int col = square[1];
			// grid[row][col] = VISITED; // NG!

			// Neighbors.
			for (int[] d : DIR) {
				int nr = row + d[0];
				int nc = col + d[1];
				if (nr < 0 || nc < 0 || nr >= R || nc >= C || grid[nr][nc] == VISITED) {
					continue;
				}
				// Attention! Adding and marking as visited must be at the same time!
				// because two children can have the same child node.
				queue.add(new int[] { nr, nc });
				grid[nr][nc] = VISITED;
			}
		}
	}

	// Review DFS
	public int numIslandsR2(char[][] grid) {
		if (grid == null || grid.length == 0) {
			return 0;
		}

		int R = grid.length;
		int C = grid[0].length;

		int count = 0;
		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				if (grid[i][j] == '1') {
					dfsR2(grid, i, j);
					count++;
				}
			}
		}

		return count;
	}

	void dfsR2(char[][] grid, int r, int c) {
		if (r < 0 || r >= grid.length || c < 0 || c >= grid[0].length || grid[r][c] == '0') {
			return;
		}

		grid[r][c] = '0';

		dfsR2(grid, r - 1, c);
		dfsR2(grid, r + 1, c);
		dfsR2(grid, r, c - 1);
		dfsR2(grid, r, c + 1);
	}

	// Review BFS
	public int numIslandsR3(char[][] grid) {
		if (grid == null || grid.length == 0) {
			return 0;
		}
		int R = grid.length;
		int C = grid[0].length;

		int count = 0;
		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				if (grid[i][j] == '1') {
					bfsR3(grid, i, j);
					count++;
				}
			}
		}

		return count;
	}

	private final int[] DIR_ROW = new int[] { -1, 1, 0, 0 };
	private final int[] DIR_COL = new int[] { 0, 0, -1, 1 };

	void bfsR3(char[][] grid, int r, int c) {
		LinkedList<int[]> queue = new LinkedList<>();
		queue.add(new int[] { r, c, });
		while (!queue.isEmpty()) {
			int[] s = queue.poll();
			int row = s[0];
			int col = s[1];

			for (int i = 0; i < 4; i++) {
				int nr = row + DIR_ROW[i];
				int nc = col + DIR_COL[i];
				if (nr < 0 || nc < 0 || nr >= grid.length || nc >= grid[0].length || grid[nr][nc] == '0') {
					continue;
				}
				grid[nr][nc] = '0';
				queue.add(new int[] { nr, nc });
			}
		}
	}

	// For testing.
	@SuppressWarnings("unused")
	public static void main(String[] args) {
		NumberOfIslands solution = new NumberOfIslands();

		// Test arguments.
		// int num = 24;
		// int target = 2;
		// solution.getInt(num, target);

	}

}
