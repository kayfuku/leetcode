// 
// Author: 
// Date  : June 28, 2019

package leetcode;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

public class TopKFrequentElements {
	// fields here. 
//	private int count;
	
	public TopKFrequentElements() {
		// Initialization here. 
//		this.count = 0;
	}
		
	// O(NlogK) time, where N is the total number of nodes in the array, and 
	// K is as top k frequent elements. 
	// O(N) space. 
    public List<Integer> topKFrequent(int[] nums, int k) {
    	// 1. Count the frequency. 
    	
    	// K: num, V: frequency
    	// O(N) time. 
    	Map<Integer, Integer> map = new HashMap<>();
    	for (int num : nums) {
			map.put(num, map.getOrDefault(num, 0) + 1);
		}
    	
    	// 2. Build a Min-Heap to keep top k frequent elements in the heap. 
    	
    	// The less frequent element comes top. 
    	// Note that lambda expression is much slower than normal anonymous Comparator. 
//    	PriorityQueue<Integer> minHeap = 
//    			new PriorityQueue<>((n1, n2) -> map.get(n1) - map.get(n2));
    	PriorityQueue<Integer> minHeap = 
    			new PriorityQueue<>(new Comparator() {
					@Override
					public int compare(Object n1, Object n2) {
						return map.get(n1) - map.get(n2);
					}
				});
    	
    	// O(NlogK) time. 
    	for (int num : map.keySet()) {
			minHeap.offer(num);
			if (minHeap.size() > k) {
				minHeap.poll();
			}
		}
    	
    	// 3. Make the output. 
    	
    	List<Integer> topK = new ArrayList<>();
    	// O(KlogK) time. 
    	while (!minHeap.isEmpty()) {
			topK.add(minHeap.poll());
		}
    	topK.sort(Comparator.naturalOrder());
    	
    	return topK;
    }
	
	
	// other classes here. 

    
    // For testing. 
	public static void main(String[] args) {
	    TopKFrequentElements solution = new TopKFrequentElements();
	    
	    // Test arguments. 
//	    int num = 24;
//	    int target = 2;
//	    solution.getInt(num, target);
	    
	    int[] nums = new int[]{ 1, 1, 1, 2, 2, 3 };
	    List<Integer> ans = solution.topKFrequent(nums, 2);
	    System.out.println(ans);
	    
	    
	    
	}

}















