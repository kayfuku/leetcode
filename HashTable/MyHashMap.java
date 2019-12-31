// 
// Author: 
// Date  : 

package leetcode;

import java.util.ArrayList;
import java.util.List;

class MyHashMap {
	private final int MAX_LEN = 100000;             // the amount of buckets
	private List<PairG<Integer, Integer>>[] map;     // hash map implemented by array

	/** Returns the corresponding bucket index. */
	private int getIndex(int key) {
		return key % MAX_LEN;
	}

	/** Search the key in a specific bucket. Returns -1 if the key does not existed. */
	private int getPos(int key, int index) {
		// Each bucket contains a list.
		List<PairG<Integer, Integer>> temp = map[index];
		if (temp == null) {
			return -1;
		}
		// Iterate all the elements in the bucket to find the target key.
		for (int i = 0; i < temp.size(); ++i) {
			if (temp.get(i).getKey() == key) {
				return i;
			}
		}
		return -1;
	}

	/** Initialize your data structure here. */
	@SuppressWarnings("unchecked")
	public MyHashMap() {
		map = (List<PairG<Integer, Integer>>[])new ArrayList[MAX_LEN];
	}

	/** value will always be positive. */
	public void put(int key, int value) {
		int index = getIndex(key);
		int pos = getPos(key, index);
		if (pos < 0) {
			// Add new (key, value) pair if key does not exist.
			if (map[index] == null) {
				map[index] = new ArrayList<PairG<Integer, Integer>>();
			}
			map[index].add(new PairG<>(key, value));
		} else {
			// Update the value if key exists.
			map[index].set(pos, new PairG<>(key, value));
		}
	}

	/** Returns the value to which the specified key is mapped, 
	 * or -1 if this map contains no mapping for the key */
	public int get(int key) {
		int index = getIndex(key);
		int pos = getPos(key, index);
		if (pos < 0) {
			return -1;
		} else {
			return map[index].get(pos).getValue();
		}
	}

	/** Removes the mapping of the specified value key if this map contains a mapping for the key */
	public void remove(int key) {
		int index = getIndex(key);
		int pos = getPos(key, index);
		if (pos >= 0) {
			// O(N).
			// For custom remove(), First, swap the element which we want to remove 
			// with the last element in the bucket. Then remove the last element. 
			// By this way, we successfully remove the element in O(1) time complexity.
			map[index].remove(pos);
		}
	}


	/**
	 * Your MyHashMap object will be instantiated and called as such:
	 * MyHashMap obj = new MyHashMap();
	 * obj.put(key,value);
	 * int param_2 = obj.get(key);
	 * obj.remove(key);
	 */


	// For testing. 
	public static void main(String[] args) {
		MyHashMap solution = new MyHashMap();

		// Test arguments. 
		// int num = 24;
		// int target = 2;
		// solution.getInt(num, target);



	}

}
















