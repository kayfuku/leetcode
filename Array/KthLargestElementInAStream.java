// 
// Author: 
// Date  : June 27, 2019

package leetcode;

import java.util.PriorityQueue;


public class KthLargestElementInAStream {
	// fields here. 
	//	private int count;

	public KthLargestElementInAStream() {
		// Initialization here. 
		//		this.count = 0;
	}

	// 1. Using Priority Queue (Min-Heap). 
	// O(NlogK), where N is the total number of elements in the nums, and 
	// K is as the K-th largest. 
	PriorityQueue<Integer> pq;
	int K;
	
	// Build. O(NlogK) time. 
	public KthLargestElementInAStream(int k, int[] nums) {
        this.K = k;
        
        // Build Min-Heap. O(NlogK) time. 
        pq = new PriorityQueue<>(K);
        for (int num: nums) {
        	add(num);
        }
    }
	// Query. O(logK) time. 
    public int add(int val) {
    	pq.offer(val);
    	if (pq.size() > K) {
			pq.poll();
		}
    	    	
    	return pq.peek();
    }
	
	
	// 2. Using BST comprised of special tree nodes that have the number of nodes 
	// under the node as a root.  
	// H is the height of tree with the average and best time O(logN) and worst time O(N), 
	// where N is the total number of nodes in the BST. 

	TreeNode root;
	int k;

	// Build BST. O(NH) time. 
//	public KthLargestElementInAStream(int k, int[] nums) {
//		this.k = k;
//		for (int num : nums) {
//			root = insert(root, num);
//		}
//		
//		// For test. 
//		preorder(root);
//		System.out.println();
//		inorder(root);
//		System.out.println();
//	}

	// O(H) time. 
//	public int add(int val) {
//		// O(H) time. 
//		root = insert(root, val);
//		// O(H) time. 
//		return findKthLargest();
//	}

	// O(H) time.  
	private TreeNode insert(TreeNode node, int val) {
		if (node == null) {
			return new TreeNode(val);
		}

		// node.count is the number of nodes in the 
		// tree whose root is the node. 
		node.count++;

		if (val < node.val) {
			node.left = insert(node.left, val);
		} else {
			// val >= node.val
			node.right = insert(node.right, val);
		}

		return node;
	}

	// Query. O(H) time. 
	private int findKthLargest() {
		int count = k;
		TreeNode cur = root;

		while (count > 0) {
			int pos = 1 + (cur.right != null ? cur.right.count : 0);
			if (pos == count) {
				break;
			}
			if (pos < count) {
				// pos, which is the number of nodes, cur + (all the nodes of right subtree of cur), 
				// is not enough to reach k. So, go down to the left subtree to find the 
				// next largest nodes. 
				count -= pos;
				cur = cur.left;
			} else if (pos > count) {
				cur = cur.right;
			}
		}

		return cur.val;
	}







	// other classes here. 


	// For testing. 
	public static void main(String[] args) {

		// Test arguments. 
		//	    int num = 24;
		//	    int target = 2;
		//	    solution.getInt(num, target);
		int[] nums = new int[]{ 4, 5, 8, 2 };
		KthLargestElementInAStream solution = new KthLargestElementInAStream(3, nums);
		System.out.println(solution.add(3)); // 4
		System.out.println(solution.add(5)); // 5
		System.out.println(solution.add(10)); // 5
		System.out.println(solution.add(9)); // 8
		System.out.println(solution.add(4)); // 8
		solution.preorder(solution.root);
		System.out.println(); // 4235485109
		solution.inorder(solution.root);
		System.out.println(); // 2345485109


	}

	// For test. 
	private void preorder(TreeNode node) {
		if (node == null) {
			return;
		}

		System.out.print(node.val);
		preorder(node.left);
		preorder(node.right);
	}
	private void inorder(TreeNode node) {
		if (node == null) {
			return;
		}

		inorder(node.left);
		System.out.print(node.val);
		inorder(node.right);
	}



}















