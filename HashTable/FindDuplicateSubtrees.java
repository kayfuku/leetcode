// 
// Author: 
// Date  : January 3, 2020

package leetcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FindDuplicateSubtrees {
	// fields and classes here. 
	//private int count;

	public FindDuplicateSubtrees() {
		// Initialization here. 
		//this.count = 0;

	}


	// O(N) time, O(N) space. 
	// Author: awice + StefanPochmann + kei
	// Date  : January 3, 2020
	int t;
	Map<String, Integer> trees;
	Map<Integer, Integer> count;
	List<TreeNode> ans;

	public List<TreeNode> findDuplicateSubtrees(TreeNode root) {
		t = 1;
		trees = new HashMap<>();
		count = new HashMap<>();
		ans = new ArrayList<>();
		lookup(root);
		return ans;
	}

	public int lookup(TreeNode node) {
		if (node == null) {
			return 0;
		}
		
		// Make an id for this tree and count the same tree. 
		// Serialize the tree. 
		String serial = node.val + "," + lookup(node.left) + "," + lookup(node.right);
		// computeIfAbsent(key, function) use the function if the key is not in the map. 
		// lambda function returns t++. 
		int uid = trees.computeIfAbsent(serial, x -> t++);
		count.put(uid, count.getOrDefault(uid, 0) + 1);
		
		if (count.get(uid) == 2) {
			ans.add(node);
		}
		
		return uid;
	}







	// For testing. 
	public static void main(String[] args) {
		FindDuplicateSubtrees solution = new FindDuplicateSubtrees();

		// Test arguments. 
		// int num = 24;
		// int target = 2;
		// solution.getInt(num, target);
		
		// Binary Tree
		//       1
		//      / \
		//     2   3 
		//    /   / \
		//   4   2   4
		//      /
		//     4
		TreeNode n1 = new TreeNode(1);
		TreeNode n2 = new TreeNode(2);
		TreeNode n3 = new TreeNode(3);
		TreeNode n4 = new TreeNode(4);
		TreeNode n5 = new TreeNode(2);
		TreeNode n6 = new TreeNode(4);
		TreeNode n7 = new TreeNode(4);
		n1.left = n2;
		n1.right = n3;
		n2.left = n4;
		n3.left = n5;
		n3.right = n6;
		n5.left = n7;
		System.out.println(solution.findDuplicateSubtrees(n1));
		



	}

}















