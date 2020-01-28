// 
// Author: 
// Date  : January 8, 2020

package leetcode;

import java.util.ArrayList;
import java.util.HashMap;

public class CloneGraph {
	// fields and classes here. 
	//private int count;

	public CloneGraph() {
		// Initialization here. 
		//this.count = 0;

	}


	// Author: @godayaldivya + kei
	// Date  : January 8, 2020
	
	// Key: original node, Value: clone node
	private HashMap<GraphNode, GraphNode> visited = new HashMap<>();
	
	// Input is root node of a graph, and output is clone node of the node. 
	public GraphNode cloneGraph(GraphNode node) {
		if (node == null) {
			return node;
		}

		// If the node was already visited before, 
		// then return the clone node from the visited dictionary.
		// A kind of memoization? 
		if (visited.containsKey(node)) {
			return visited.get(node);
		}

		// Create a clone node for the given node.
		// Note that we don't have cloned neighbors as of now, hence [].
		GraphNode cloneNode = new GraphNode(node.val, new ArrayList<>());
		visited.put(node, cloneNode);

		// Iterate through the neighbors to generate their clones
		// and prepare a list of cloned neighbors to be added to the clone node.
		for (GraphNode neighbor : node.neighbors) {
			// Even if 'neighbor' has been visited, the clone node of the 'neighbor' 
			// should be added to the neighbors list of the 'cloneNode'. 
			// So there is no if filter here. 
			cloneNode.neighbors.add(cloneGraph(neighbor));
		}
		
		// Return the clone node of 'node'. 
		return cloneNode;
	}







	// For testing. 
	public static void main(String[] args) {
		CloneGraph solution = new CloneGraph();

		// Test arguments. 
		// int num = 24;
		// int target = 2;
		// solution.getInt(num, target);



	}

}















