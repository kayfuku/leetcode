// 
// Author: 
// Date  : June 21, 2019

package leetcode;

import java.util.Stack;

public class MergeTwoBinaryTrees {
	// fields here. 
	//	private int count;

	public MergeTwoBinaryTrees() {
		// Initialization here. 
		//		this.count = 0;
	}

	// 1. Recursive. (Faster than approach 2 and 3)
	// I am going to use t1 as the output and traverse both t1 and t2 
	// in the same way using DFS. 
	// 
	// For recursive DFS, the input of the recursion stack is two root nodes of the trees. 
	// The output of the recursion stack is a root node of the merged tree. 
	// 
	// O(M) time, where M is the number of overlapping nodes. 
	// O(M) space. It does not matter whether the input trees are balanced or not.  
	public TreeNode mergeTrees(TreeNode t1, TreeNode t2) {
		// If the node of t1 is null, return t2 regardless of whether t2 is null or not. 
		if (t1 == null) {
			return t2;
		}
		// If t1 is not null and the node of t2 is null, then return t1. 
		if (t2 == null) {
			return t1;
		}
		// Assert that both of t1 and t2 is not null. 

		// Both t1 and t2 are not null. 
		// Traverse both trees in the same way. 
		// The left subtree is a merged subtree between t1 left subtree and t2 left subtree. 
		// The right subtree is a merged subtree between t1 right subtree and t2 right subtree. 
		t1.val += t2.val;
		t1.left = mergeTrees(t1.left, t2.left); 
		t1.right = mergeTrees(t1.right, t2.right);

		return t1;
	}

	// 2. Iterative. Accepted. (Faster than approach 3)
	// O(M) time, where M is the number of overlapping nodes. 
	// O(M) space. It does not matter whether the input trees are balanced or not.  
	// Author: kei
	// Date  : June 21, 2019
	public TreeNode mergeTreesKei(TreeNode t1, TreeNode t2) {
		if (t1 == null) {
			return t2;
		}
		if (t2 == null) {
			return t1;
		}

		// Since the type of the inputs are the same, we can use 
		// stack of array. 
		Stack<TreeNode[]> stack = new Stack<>();
		stack.push(new TreeNode[]{ t1, t2 });

		while (!stack.isEmpty()) {
			TreeNode[] t = stack.pop();

			// At this point, need to assert that both of the nodes 
			// are not null. 			
			t[0].val += t[1].val;

			// For left child.  
			//		t1.left		t2.left
			//	1.	v			v		Keep going DFS. Push t1.left & t2.left. 
			//	2.	null		v		Attach t2.left to t1.left. 
			//	3.	v			null	Do nothing because we use t1 as an output. No need to do DFS. 
			//  4.	null		null	Do nothing because we use t1 as an output. No need to do DFS.
			if (t[0].left != null && t[1].left != null) {
				// Case 1. Keep going DFS. 
				stack.push(new TreeNode[]{ t[0].left, t[1].left });
			} else if (t[0].left == null && t[1].left != null) {	
				// Case 2. 
				t[0].left = t[1].left;
			} 
			// Case 3 or 4. Do nothing. 

			// For right child, do the same as the left child.  
			if (t[0].right != null && t[1].right != null) {
				// Case 1. Keep going DFS. 
				stack.push(new TreeNode[]{ t[0].right, t[1].right });
			} else if (t[0].right == null && t[1].right != null) {	
				// Case 2. 
				t[0].right = t[1].right;
			} 
			// Case 3 or 4. Do nothing. 
		}

		// t1 is the root node of the merged tree. 
		return t1;
	}

	
	// 3. Iterative. No need to review. 
	// O(m) time, where m is the number of overlapping nodes. 
	// O(logm) space for balanced, O(m) space for not balanced. 
	// Author: @vinod23
	// Date  : June 21, 2019
	public TreeNode mergeTrees2(TreeNode t1, TreeNode t2) {
		if (t1 == null)
			return t2;
		Stack < TreeNode[] > stack = new Stack < > ();
		stack.push(new TreeNode[] {t1, t2});
		while (!stack.isEmpty()) {
			TreeNode[] t = stack.pop();
			// This and the following is not really clear for me. 
			if (t[0] == null || t[1] == null) {
				continue;
			}
			t[0].val += t[1].val;
			if (t[0].left == null) {
				t[0].left = t[1].left;
			} else {
				stack.push(new TreeNode[] {t[0].left, t[1].left});
			}
			if (t[0].right == null) {
				t[0].right = t[1].right;
			} else {
				stack.push(new TreeNode[] {t[0].right, t[1].right});
			}
		}
		return t1;
	}


	// Review
	public TreeNode mergeTreesR(TreeNode t1, TreeNode t2) {
		if (t1 == null) {
			return t2;
		}
		if (t2 == null) {
			return t1;
		}	

		t1.val += t2.val;
		t1.left = mergeTrees(t1.left, t2.left);
		t1.right = mergeTrees(t1.right, t2.right);

		return t1;
	}


	// Review 
	public TreeNode mergeTreesIterR(TreeNode t1, TreeNode t2) {
		// corner. 
		if (t1 == null) {
			return t2;
		}
		if (t2 == null) {
			return t1;
		}

		Stack<TreeNode[]> stack = new Stack<>();
		stack.push(new TreeNode[]{ t1, t2 });

		while (!stack.isEmpty()) {
			TreeNode[] t = stack.pop();

			t[0].val += t[1].val;

			if (t[0].right != null && t[1].right != null) {
				stack.push(new TreeNode[]{ t[0].right, t[1].right });
			} else if (t[0].right == null && t[1].right != null) {
				t[0].right = t[1].right;
			}

			// 	t1.left	t2.left
			// 1	v		v		DFS. 
			// 2	null		v		Attach t2.left to t1.left. 
			// 3 	v		null		
			// 4 	null		null		
			if (t[0].left != null && t[1].left != null) {
				stack.push(new TreeNode[]{ t[0].left, t[1].left });
			} else if (t[0].left == null && t[1].left != null) {
				t[0].left = t[1].left;
			}
		}

		return t1;
	}





	// For testing. 
	public static void main(String[] args) {
		MergeTwoBinaryTrees solution = new MergeTwoBinaryTrees();

		// Test arguments. 
		//	    int num = 24;
		//	    int target = 2;
		//	    solution.getInt(num, target);



	}

}















