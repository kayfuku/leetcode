// 
// Author: LeetCode + kei
// Date  : December 26, 2019

package leetcode;

import java.util.ArrayList;
import java.util.List;


class DesignHashSet {
	
	private final int MAX_LEN = 100000; // the amount of buckets
	private List<Integer>[] set;      // hash set implemented by array

	
	/** Initialize your data structure here. */
	@SuppressWarnings("unchecked")
	public DesignHashSet() {
		// Array of ArrayLists. 
		set = (List<Integer>[]) new ArrayList[MAX_LEN];
	}
	
	/** Returns the corresponding bucket index. */
	private int getIndex(int key) {
		return key % MAX_LEN;
	}

	/** Search the key in a specific bucket. Returns -1 if the key does not existed. */
	private int getPos(int key, int index) {
		// Each bucket contains a list.
		List<Integer> temp = set[index];
		if (temp == null) {
			return -1;
		}
		// Iterate all the elements in the bucket to find the target key.
		for (int i = 0; i < temp.size(); ++i) {
			if (temp.get(i) == key) {
				return i;
			}
		}
		return -1;
	}

	public void add(int key) {
		int index = getIndex(key);
		int pos = getPos(key, index);
		if (pos < 0) {
			// Add new key if key does not exist.
			if (set[index] == null) {
				set[index] = new ArrayList<Integer>();
			}
			set[index].add(key);
		}
	}

	public void remove(int key) {
		int index = getIndex(key);
		int pos = getPos(key, index);
		if (pos >= 0) {
			// Remove the key if key exists.
			set[index].remove(pos);
		}
	}

	/** Returns true if this set did not already contain the specified element */
	public boolean contains(int key) {
		int index = getIndex(key);
		int pos = getPos(key, index);
		return pos >= 0;
	}


	/**
	 * Your MyHashSet object will be instantiated and called as such:
	 * MyHashSet obj = new MyHashSet();
	 * obj.add(key);
	 * obj.remove(key);
	 * boolean param_3 = obj.contains(key);
	 */


	// For testing. 
	public static void main(String[] args) {
		DesignHashSet solution = new DesignHashSet();

		// Test arguments. 
		// int num = 24;
		// int target = 2;
		// solution.getInt(num, target);



	}

}















