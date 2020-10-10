// 
// Author: 
// Date  : July 6, 2019

package leetcode;

public class NumberOfConnectedComponentsInAnUndirectedGraph {
	// fields here.
	// private int count;

	public NumberOfConnectedComponentsInAnUndirectedGraph() {
		// Initialization here.
		// this.count = 0;
	}

	// Union Find. Bottom one is better.
	// https://leetcode.com/problems/number-of-connected-components-in-an-undirected-graph/discuss/77574/Easiest-2ms-Java-Solution
	// Author: yavinci + kei
	// Data : July 31, 2019
	public int countComponents(int n, int[][] edges) {
		// Indeicate parent node of the index node.
		int[] roots = new int[n];
		// Initialization. Each node is isolated.
		// One isolated node's parent is the node itself.
		for (int i = 0; i < n; i++) {
			roots[i] = i;
		}

		for (int[] e : edges) {
			// e[0] and e[1] is connected according to the input.
			// Check if they are in the same group, if not, connect.

			// Find the root node (representative of a group) first.
			int root1 = find(roots, e[0]);
			int root2 = find(roots, e[1]);

			// Check if they are the same.
			if (root1 != root2) {
				// Union.
				// e[0] and e[1] should be connected, if their root nodes are different,
				// then unite them.
				// Connect root1 to root2 and reduce the number of groups.
				roots[root1] = root2;
				n--;
			}
			// root1 == root2, which means root1 and root2 belong to the same group.
			// That is ok. Do nothing.
			// Do not reduce n.
		}

		return n;
	}

	// Find the root with path compression. (Recursive)
	public int find(int[] roots, int id) {
		if (id == roots[id]) {
			return roots[id];
		}
		// Pass the parent to move forward, not id itself.
		roots[id] = find(roots, roots[id]);
		return roots[id];
	}

	// Find the root with path compression. (Iterative)
	public int find2(int[] roots, int id) {
		while (roots[id] != id) {
			// Path Compression.
			roots[id] = roots[roots[id]];
			id = roots[id];
		}
		return id;
	}

	// Review 3
	public int countComponentsR3(int n, int[][] edges) {
		UF uf = new UF(n);

		for (int[] e : edges) {
			int s = e[0];
			int t = e[1];
			int x = uf.find(s);
			int y = uf.find(t);
			if (x != y) {
				uf.unite(x, y);
				n--;
			}
		}

		return n;
	}

	class UF {
		int[] roots;

		UF(int n) {
			roots = new int[n];
			for (int i = 0; i < n; i++) {
				roots[i] = i;
			}
		}

		int find(int x) {
			if (x == roots[x]) {
				return x;
			}
			roots[x] = find(roots[x]);
			return roots[x];
		}

		void unite(int x, int y) {
			roots[x] = y;
		}
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
