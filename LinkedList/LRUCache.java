// 
// Author: 
// Date  : August 1, 2019

package leetcode;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class LRUCache extends LinkedHashMap<Integer, Integer> {

	// 1. LinkedHashMap.
	// LinkedHashMap.put(k, v) removes the oldest element if the capacity is full,
	// but you need to override the removeEldestEntry().
	public class LRUCacheClass extends LinkedHashMap<Integer, Integer> {
		private int capacity;

		// 1. LinkedHashMap.
		public LRUCacheClass(int capacity) {
			// true: access order, false: insertion order.
			super(capacity, 0.75f, true);
			this.capacity = capacity;
		}

		public int get(int key) {
			// If the key does not exist, then return -1 as described.
			return super.getOrDefault(key, -1);
		}

		public void put(int key, int value) {
			super.put(key, value);
		}

		@Override
		// Called after put().
		protected boolean removeEldestEntry(Map.Entry<Integer, Integer> eldest) {
			return size() > capacity;
		}
	}

	// 2. Hash Map + Doubly Linked List (DLL).
	// Add the node to the head, remove the node from the tail.
	// Implement following the steps.
	// add => remove => moveToHead => popTail => get => put
	// O(1) time for get and put operation.
	// O(C) space, where C is the capacity of the cache.
	public class LRUCacheClass2 {

		// DLL makes things easier.
		// It also has key and value.
		class DLLNode {
			int key;
			int value;
			DLLNode prev;
			DLLNode next;
		}

		// To access a node in the DLL in O(1) time.
		// K: key, V: DLL node
		private HashMap<Integer, DLLNode> cache = new HashMap<>();
		private int size;
		private int capacity;
		// Using head and tail nodes makes things easier.
		private DLLNode head, tail;

		// Constructor.
		public LRUCacheClass2(int capacity) {
			this.size = 0;
			this.capacity = capacity;

			// Actual node, not a pointer
			head = new DLLNode();
			tail = new DLLNode();

			// head <=> tail
			// Only head and tail are connected.
			head.next = tail;
			tail.prev = head;
		}

		// Add the new node right after head.
		private void addNode(DLLNode node) {
			// Here, head is a node, not pointer.
			// head <= node => head.next
			node.prev = head;
			node.next = head.next;

			// head => node <= head.next
			head.next.prev = node; // This first!
			head.next = node;
		}

		// Remove the node. O(1) time.
		// Don't use tail because moveToHead() uses this method.
		// Keep it general.
		private void removeNode(DLLNode node) {
			// prev <= node => next
			DLLNode prev = node.prev;
			DLLNode next = node.next;

			// So easy!
			// prev <=> next
			prev.next = next;
			next.prev = prev;
		}

		// Pop the tail node. O(1) time.
		private DLLNode popTail() {
			// ... <=> res <=> tail
			DLLNode res = tail.prev;
			// Just pass in the last node.
			removeNode(res);

			return res;
		}

		// When a node is accessed or updated,
		// move the node to the first position. O(1) time.
		private void moveToHead(DLLNode node) {
			removeNode(node);
			addNode(node);
		}

		// put operation.
		public void put(int key, int value) {
			// Check if the key exists.
			// Access the node in O(1) time.
			DLLNode node = cache.get(key);
			if (node == null) {
				// The key does not exist in the map.
				// Create a node.
				DLLNode newNode = new DLLNode();
				newNode.key = key;
				newNode.value = value;

				// Add it to the doubly linked list.
				addNode(newNode);
				// Add it to the map.
				cache.put(key, newNode);
				size++;

				// After adding, check if the size is bigger than capacity.
				if (size > capacity) {
					// Remove it from the linked list.
					DLLNode tail = popTail();
					// Remove it from the map.
					cache.remove(tail.key);
					size--;
				}

			} else {
				// The key is already in the cache.
				// Update the value.
				node.value = value;

				// Move the node to the head because the node is recently updated.
				moveToHead(node);
			}
		}

		// get operation.
		public int get(int key) {
			// Check if the key exists.
			// Access the node in O(1) time.
			DLLNode node = cache.get(key);

			if (node == null) {
				return -1;
			}

			// Move the node to the head because the node is recently accessed.
			moveToHead(node);

			return node.value;
		}

	}

	/**
	 * Your LRUCache object will be instantiated and called as such: LRUCache obj =
	 * new LRUCache(capacity); int param_1 = obj.get(key); obj.put(key,value);
	 */

	// For testing.
	public static void main(String[] args) {
		LRUCache solution = new LRUCache();

		// Test arguments.
		// int num = 24;
		// int target = 2;
		// solution.getInt(num, target);

	}

}
