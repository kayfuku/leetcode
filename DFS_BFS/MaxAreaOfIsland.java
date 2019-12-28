// 
// Author: 
// Date  : July 1, 2019

package leetcode;

import java.util.Stack;

public class MaxAreaOfIsland {
	// fields here. 
//	private int count;
	
	public MaxAreaOfIsland() {
		// Initialization here. 
//		this.count = 0;
	}
		
	// 1. DFS, Recursive. 
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
    	if (r < 0 || r >= grid.length || c < 0 || c >= grid[0].length || 
    		visited[r][c] || grid[r][c] == 0) {
			return 0;    		
		}
    	
    	visited[r][c] = true;
    	int area = 1 + getArea(r + 1, c) +   /* Down */
    			       getArea(r - 1, c) +   /* Up */
    			       getArea(r, c - 1) +   /* Left */
    			       getArea(r, c + 1);    /* Right */
    	
    	return area;
    }
    
    
    // 2. DFS, Iterative. 
	// O(RC) time, where R is the number of rows in the input grid, and C is the 
	// number of columns. We visit every cell once. 
	// O(RC) space, the space used by 'visited' and 'stackRc'. 
    public int maxAreaOfIslandIter(int[][] grid) {
    	int R = grid.length;
    	int C = grid[0].length;
    	boolean[][] visited = new boolean[R][C];
    	int[] dr = new int[]{ 1, -1, 0, 0 };
    	int[] dc = new int[]{ 0, 0, -1, 1 };
    	
    	int maxArea = 0;
    	for (int r = 0; r < R; r++) {
    		for (int c = 0; c < C; c++) {
				if (!visited[r][c] && grid[r][c] == 1) {
					// For each island. 
					int area = 0;
					// DFS using Stack. 
					Stack<int[]> stackRc = new Stack<>();
					stackRc.push(new int[]{ r, c });
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
							if (nr >= 0 && nr < R && nc >= 0 && nc < C && 
								!visited[nr][nc] && grid[nr][nc] == 1) {
								stackRc.push(new int[]{ nr, nc });
								visited[nr][nc] = true;
							}
						}
					}
					
					maxArea = Math.max(maxArea, area);
				}
			}
		}
    	
    	return maxArea;
    }

    
    
    
    
	
	
	// other classes here. 

    
    // For testing. 
	public static void main(String[] args) {
	    MaxAreaOfIsland solution = new MaxAreaOfIsland();
	    
	    // Test arguments. 
//	    int num = 24;
//	    int target = 2;
//	    solution.getInt(num, target);
	    
	    
	    
	}

}















