// 
// Author: 
// Date  : July 1, 2019

package leetcode;

import java.util.LinkedList;
import java.util.Stack;

public class MaxAreaOfIsland {
	// fields here.
	// private int count;

	public MaxAreaOfIsland() {
		// Initialization here.
		// this.count = 0;
	}

	// 1. DFS, Recursive.
	// I'm going to iterate through the grid, and when I find a 1,
	// trigger DFS to calculate the area.
	// Keep track of the maximum of the areas and the grid that I've visited.
	//
	// O(RC) time, where R is the number of rows in the input grid, and C is the
	// number of columns. We visit every cell once.
	// O(RC) space, the space used by 'visited' and recursion stack.
	int[][] grid;
	boolean[][] visited;

	public int maxAreaOfIsland(int[][] grid) {
		this.grid = grid;
		this.visited = new boolean[grid.length][grid[0].length];
		int maxArea = 0;
		for (int r = 0; r < grid.length; r++) {
			for (int c = 0; c < grid[0].length; c++) {
				maxArea = Math.max(maxArea, getArea(r, c));
			}
		}

		return maxArea;
	}

	private int getArea(int r, int c) {
		if (r < 0 || r >= grid.length || c < 0 || c >= grid[0].length || visited[r][c] || grid[r][c] == 0) {
			return 0;
		}

		visited[r][c] = true;
		int area = 1 + getArea(r + 1, c) + /* Down */
				getArea(r - 1, c) + /* Up */
				getArea(r, c - 1) + /* Left */
				getArea(r, c + 1); /* Right */

		return area;
	}

	// Better solution
	// No need to use visited 2D array. O(1) space
	// Author: kei (AC)
	// Date : October 8, 2020

	// int[][] grid;

	public int maxAreaOfIslandB(int[][] grid) {
		if (grid == null || grid.length == 0) {
			return 0;
		}
		int R = grid.length;
		int C = grid[0].length;

		this.grid = grid;
		int maxArea = 0;
		for (int r = 0; r < R; r++) {
			for (int c = 0; c < C; c++) {
				maxArea = Math.max(maxArea, getAreaT(r, c));
			}
		}

		return maxArea;
	}

	private int getAreaT(int r, int c) {
		if (r < 0 || r >= grid.length || c < 0 || c >= grid[0].length || grid[r][c] == 0) {
			return 0;
		}

		grid[r][c] = 0;
		int area = 1 + getAreaT(r + 1, c) + /* Down */
				getAreaT(r - 1, c) + /* Up */
				getAreaT(r, c - 1) + /* Left */
				getAreaT(r, c + 1); /* Right */

		return area;
	}

	// 2. DFS, Iterative.
	// Every time I encounter a 1, run DFS to calculate the area.
	//
	// O(RC) time, where R is the number of rows in the input grid, and C is the
	// number of columns. We visit every cell once.
	// O(RC) space, the space used by 'visited' and 'stackRc'.
	public int maxAreaOfIslandIter(int[][] grid) {
		int R = grid.length;
		int C = grid[0].length;
		boolean[][] visited = new boolean[R][C];
		int[] dr = new int[] { 1, -1, 0, 0 };
		int[] dc = new int[] { 0, 0, -1, 1 };

		int maxArea = 0;
		for (int r = 0; r < R; r++) {
			for (int c = 0; c < C; c++) {
				if (!visited[r][c] && grid[r][c] == 1) {
					// For each island.
					int area = 0;

					// DFS using Stack.
					Stack<int[]> stackRc = new Stack<>();
					stackRc.push(new int[] { r, c });
					visited[r][c] = true;
					while (!stackRc.isEmpty()) {
						int[] rc = stackRc.pop();
						int dfsR = rc[0], dfsC = rc[1];
						// Add 1 to the area.
						area++;
						// Neighbors.
						for (int i = 0; i < dr.length; i++) {
							int nr = dfsR + dr[i];
							int nc = dfsC + dc[i];
							// Check validity.
							if (nr >= 0 && nr < R && nc >= 0 && nc < C && !visited[nr][nc] && grid[nr][nc] == 1) {
								stackRc.push(new int[] { nr, nc });
								visited[nr][nc] = true;
							}
						}
					}

					maxArea = Math.max(maxArea, area);
				}
			} // c
		} // r

		return maxArea;
	}

	// Review. Recursive.
	// int[][] grid;
	// boolean[][] visited;
	public int maxAreaOfIslandRecurR(int[][] grid) {
		int R = grid.length, C = grid[0].length;
		this.grid = grid;
		this.visited = new boolean[R][C];

		int maxArea = 0;
		for (int r = 0; r < R; r++) {
			for (int c = 0; c < C; c++) {
				maxArea = Math.max(maxArea, getAreaRecurR(r, c));
			}
		}

		return maxArea;
	}

	private int getAreaRecurR(int r, int c) {
		if (r < 0 || r >= grid.length || c < 0 || c >= grid[0].length || grid[r][c] == 0 || visited[r][c]) {
			return 0;
		}

		visited[r][c] = true;
		int area = 1 + getAreaRecurR(r - 1, c) + getAreaRecurR(r + 1, c) + getAreaRecurR(r, c - 1)
				+ getAreaRecurR(r, c + 1);

		return area;
	}

	// Review. Iterative.
	public int maxAreaOfIslandIterR(int[][] grid) {
		int R = grid.length;
		int C = grid[0].length;
		boolean[][] visited = new boolean[R][C];

		int[] dr = new int[] { 1, -1, 0, 0 };
		int[] dc = new int[] { 0, 0, 1, -1 };

		int maxArea = 0;
		for (int r = 0; r < R; r++) {
			for (int c = 0; c < C; c++) {
				if (!visited[r][c] && grid[r][c] == 1) {
					int area = 0;

					Stack<int[]> stackRc = new Stack<>();
					stackRc.push(new int[] { r, c });
					visited[r][c] = true;
					while (!stackRc.isEmpty()) {
						int[] rc = stackRc.pop();
						int dfsR = rc[0], dfsC = rc[1];
						area++;

						// Neighbors.
						for (int i = 0; i < dr.length; i++) {
							int nr = dfsR + dr[i];
							int nc = dfsC + dc[i];
							// Check validity.
							if (nr >= 0 && nr <= R - 1 && nc >= 0 && nc <= C - 1 && !visited[nr][nc] && grid[nr][nc] == 1) {
								stackRc.push(new int[] { nr, nc });
								visited[nr][nc] = true;
							}
						}
					}

					maxArea = Math.max(maxArea, area);
				}
			} // c
		} // r

		return maxArea;
	}

	// DFS Review 3
	// int[][] grid;

	public int maxAreaOfIslandR3(int[][] grid) {
		if (grid == null || grid.length == 0) {
			return 0;
		}
		this.grid = grid;

		int maxArea = 0;
		for (int i = 0; i < grid.length; i++) {
			for (int j = 0; j < grid[0].length; j++) {
				if (grid[i][j] == 1) {
					maxArea = Math.max(maxArea, dfsR3(i, j));
				}
			}
		}

		return maxArea;
	}

	int dfsR3(int r, int c) {
		if (r < 0 || c < 0 || r >= grid.length || c >= grid[0].length || grid[r][c] == 0) {
			return 0;
		}

		grid[r][c] = 0;
		int area = 1 + dfsR3(r - 1, c) + dfsR3(r + 1, c) + dfsR3(r, c - 1) + dfsR3(r, c + 1);

		return area;
	}

	// BFS Review 3
	// int[][] grid;

	public int maxAreaOfIslandR3b(int[][] grid) {
		if (grid == null || grid.length == 0) {
			return 0;
		}
		this.grid = grid;

		int maxArea = 0;
		for (int i = 0; i < grid.length; i++) {
			for (int j = 0; j < grid[0].length; j++) {
				if (grid[i][j] == 1) {
					maxArea = Math.max(maxArea, bfsR3(i, j));
				}
			}
		}

		return maxArea;
	}

	private final int[] DIR_R = new int[] { -1, 1, 0, 0 };
	private final int[] DIR_C = new int[] { 0, 0, -1, 1 };

	int bfsR3(int r, int c) {
		LinkedList<int[]> q = new LinkedList<>();
		q.add(new int[] { r, c });
		grid[r][c] = 0;

		int count = 0;
		while (!q.isEmpty()) {
			int[] s = q.poll();
			int row = s[0];
			int col = s[1];
			count++;
			// NG!
			// grid[nr][nc] = 0;

			for (int i = 0; i < 4; i++) {
				int nr = row + DIR_R[i];
				int nc = col + DIR_C[i];
				if (nr < 0 || nc < 0 || nr >= grid.length || nc >= grid[0].length || grid[nr][nc] == 0) {
					continue;
				}
				// Attention! Adding and marking as visited must be at the same time!
				q.add(new int[] { nr, nc });
				grid[nr][nc] = 0;
			}
		}

		return count;
	}

	// For testing.
	public static void main(String[] args) {
		MaxAreaOfIsland solution = new MaxAreaOfIsland();

		// Test arguments.
		// int num = 24;
		// int target = 2;
		// solution.getInt(num, target);

	}

}
