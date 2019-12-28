// 
// Author: 
// Date  : July 6, 2019

package leetcode;

public class NumberOfConnectedComponentsInAnUndirectedGraph {
	// fields here. 
	//private int count;

	public NumberOfConnectedComponentsInAnUndirectedGraph() {
		// Initialization here. 
		//this.count = 0;
	}

	// Union Find. 
	// https://leetcode.com/problems/number-of-connected-components-in-an-undirected-graph/discuss/77574/Easiest-2ms-Java-Solution
	// Author: yavinci + kei
	// Data  : July 31, 2019
	public int countComponents(int n, int[][] edges) {
	    int[] roots = new int[n];
	    // Initialization. 
	    for (int i = 0; i < n; i++) {
	    	roots[i] = i; 
	    }

	    for (int[] e : edges) {
	    	// Find their root nodes. 
	        int root1 = find(roots, e[0]);
	        int root2 = find(roots, e[1]);
	        
	        if (root1 != root2) {    
	        	// Union. 
	        	// Although e[0] and e[1] is connected, their root nodes are different. 
	        	// Connect their root nodes and reduce the number of groups. 
	            roots[root1] = root2;  
	            n--;
	        }
	        // root1 == root2, which means root1 and root2 belong to the same group. 
	        // Do not reduce n. 
	    }
	    return n;
	}

	// Find the root with path compression. 
	public int find(int[] roots, int id) {
	    while (roots[id] != id) {
	    	// Path Compression. 
	        roots[id] = roots[roots[id]];  
	        id = roots[id];
	    }
	    return id;
	}



	
	

	// For testing. 
	public static void main(String[] args) {
		NumberOfConnectedComponentsInAnUndirectedGraph solution = new NumberOfConnectedComponentsInAnUndirectedGraph();

		// Test arguments. 
		// int num = 24;
		// int target = 2;
		// solution.getInt(num, target);



	}

}















