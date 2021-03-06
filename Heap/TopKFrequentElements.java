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
	// private int count;

	public TopKFrequentElements() {
		// Initialization here.
		// this.count = 0;
	}

	// 1. Min-Heap
	//
	// 1. Count the frequency.
	// 2. Use a Min-Heap to keep top k frequent elements in the heap.
	// 3. Make the output.
	//
	// O(N + MlogK + KlogK) time, where M is the number of distinct numbers in the
	// array, and K is as in top k frequent elements.
	// O(N) space for map.
	public List<Integer> topKFrequent(int[] nums, int k) {

		// 1. Count the frequency.
		// K: num, V: frequency
		// O(N) time, where N is total number of elements in the array.
		Map<Integer, Integer> map = new HashMap<>();
		for (int num : nums) {
			map.put(num, map.getOrDefault(num, 0) + 1);
		}

		// 2. Build and use a Min-Heap to keep top k frequent elements in the heap.
		// Note that lambda expression is much slower than normal anonymous Comparator.
		// PriorityQueue<Integer> minHeap =
		// new PriorityQueue<>((n1, n2) -> map.get(n1) - map.get(n2));
		PriorityQueue<Integer> minHeap = new PriorityQueue<>(new Comparator<Integer>() {
			@Override
			public int compare(Integer n1, Integer n2) {
				// You can also use data structure outside of Comparator
				// to determine which is bigger or smaller.
				// The less frequent element comes top.
				// Just memorize this:
				// return o1 - o2 => ascending order, min heap
				// Order considering their frequency.
				return map.get(n1) - map.get(n2);
			}
		});

		// O(MlogK) time, where M is the number of distinct numbers in the array.
		// The point here is to use key set, not nums.
		for (int num : map.keySet()) {
			// O(logK) time.
			minHeap.offer(num);
			if (minHeap.size() > k) {
				// The less frequency elements are taken out.
				minHeap.poll();
			}
		}

		// 3. Make the output.
		// This is the most tricky part! ref. TopKFrequentWords.java
		List<Integer> topK = new ArrayList<>();
		// O(KlogK) time.
		while (!minHeap.isEmpty()) {
			topK.add(minHeap.poll());
		}
		System.out.println(topK);
		topK.sort(Comparator.naturalOrder());
		System.out.println(topK);

		return topK;
	}

	// 2. Quicksort-like approach. (Min-Heap is good enough in interview)
	// O(N) time

	// Review
	public List<Integer> topKFrequentR(int[] nums, int k) {
		Map<Integer, Integer> map = new HashMap<>();
		for (int num : nums) {
			map.put(num, map.getOrDefault(num, 0) + 1);
		}

		PriorityQueue<Integer> minHeap = new PriorityQueue<>(new Comparator<Integer>() {
			@Override
			public int compare(Integer n1, Integer n2) {
				return map.get(n1) - map.get(n2);
			}
		});

		for (int num : map.keySet()) {
			minHeap.offer(num);
			if (minHeap.size() > k) {
				minHeap.poll();
			}
		}

		List<Integer> ret = new ArrayList<>();
		while (!minHeap.isEmpty()) {
			ret.add(minHeap.poll());
		}

		return ret;
	}

	// R3
	public int[] topKFrequentR3(int[] nums, int k) {
		Map<Integer, Integer> count = new HashMap<>();
		for (int n : nums) {
			count.put(n, count.getOrDefault(n, 0) + 1);
		}

		PriorityQueue<Integer> minHeap = new PriorityQueue<>((n1, n2) -> count.get(n1) - count.get(n2));

		for (int e : count.keySet()) {
			minHeap.offer(e);
			if (minHeap.size() > k) {
				minHeap.poll();
			}
		}

		int[] ret = new int[k];
		int i = 0;
		while (!minHeap.isEmpty()) {
			ret[i] = minHeap.poll();
			i++;
		}

		return ret;
	}

	// For testing.
	public static void main(String[] args) {
		TopKFrequentElements solution = new TopKFrequentElements();

		// Test arguments.
		// int num = 24;
		// int target = 2;
		// solution.getInt(num, target);

		int[] nums = new int[] { 1, 1, 1, 2, 2, 3 };
		List<Integer> ans = solution.topKFrequent(nums, 2);
		System.out.println(ans);

	}

}
