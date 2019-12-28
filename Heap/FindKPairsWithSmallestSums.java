// 
// Author: 
// Date  : July 22, 2019

package leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

public class FindKPairsWithSmallestSums {
	// fields here. 
	//	private int count;

	public FindKPairsWithSmallestSums() {
		// Initialization here. 
		//		this.count = 0;
	}

	// 
	
	// Accepted, but there is a better solution in terms of space complexity. 
	// O(K(logM + logN)) time, O(M^2 + N^2) space, where M is nums1 length, and N is nums2 length. 
	// 
	// https://leetcode.com/problems/find-k-pairs-with-smallest-sums/discuss/84551/simple-Java-O(KlogK)-solution-with-explanation
	// or this might be better. 
	// https://leetcode.com/problems/find-k-pairs-with-smallest-sums/discuss/84556/Java-PriorityQueue-9ms-without-helper-class
	// Author: kei
    // Date  : July 22, 2019	
	public List<List<Integer>> kSmallestPairsK(int[] nums1, int[] nums2, int k) {
		List<List<Integer>> res = new ArrayList<>();
		if (nums1 == null || nums1.length == 0 || nums2 == null || nums2.length == 0 || k == 0) {
			return res;
		}
		
//		// Use Min-Heap. Hold a pair of nums. [i, j]
//		PriorityQueue<List<Integer>> minHeap = new PriorityQueue<>((a, b) -> a.get(0) + a.get(1) - (b.get(0) + b.get(1)));
//		PriorityQueue<List<Integer>> minHeap = new PriorityQueue<>(new Comparator<List<Integer>>() {
//			@Override
//			public int compare(List<Integer> a, List<Integer> b) {
//				// The less sum of the pair comes first. 
//				return a.get(0) + a.get(1) - (b.get(0) + b.get(1));
//			}
//		});
		// Use Min-Heap. Hold a pair of nums. [i, j, sum]
		PriorityQueue<List<Integer>> minHeap = new PriorityQueue<>(new Comparator<List<Integer>>() {
			@Override
			public int compare(List<Integer> a, List<Integer> b) {
				// The less sum of the pair comes first. 
				return a.get(2) - b.get(2);
			}
		});
		
		boolean[][] offered = new boolean[nums1.length][nums2.length];

		// i, j, sum
		minHeap.offer(new ArrayList<>(Arrays.asList(0, 0, 0))); 
		
		// k times. 
		while (k > 0 && !minHeap.isEmpty()) {
			// O(log(M^2) + log(N^2)) = O(logM + logN) time. 
			List<Integer> pairIndices = minHeap.poll();
			int i = pairIndices.get(0);
			int j = pairIndices.get(1);
			
			res.add(new ArrayList<>(Arrays.asList(nums1[i], nums2[j])));
			
			if (i + 1 < nums1.length && !offered[i + 1][j]) {
				minHeap.offer(Arrays.asList(i + 1, j, nums1[i + 1] + nums2[j]));
				offered[i + 1][j] = true;
			}
			if (j + 1 < nums2.length && !offered[i][j + 1]) {
				minHeap.offer(Arrays.asList(i, j + 1, nums1[i] + nums2[j + 1]));
				offered[i][j + 1] = true;
			}
			
			k--;			
		}
		
		return res;
	}


	// other classes here. 


	// For testing. 
	public static void main(String[] args) {
		FindKPairsWithSmallestSums solution = new FindKPairsWithSmallestSums();

		// Test arguments. 
		//	    int num = 24;
		//	    int target = 2;
		//	    solution.getInt(num, target);
		int[] nums1 = new int[]{ 1, 7, 11, 16 };
		System.out.println(Arrays.toString(nums1));
		int[] nums2 = new int[]{ 2, 9, 10, 15 };
		System.out.println(Arrays.toString(nums2));
		int k = 3;
		System.out.println(solution.kSmallestPairsK(nums1, nums2, k));
		
		nums1 = new int[]{ 1, 1, 2 };
		System.out.println(Arrays.toString(nums1));
		nums2 = new int[]{ 1, 2, 3 };
		System.out.println(Arrays.toString(nums2));
		k = 10;
		System.out.println(solution.kSmallestPairsK(nums1, nums2, k));

		
		
		


	}

}















