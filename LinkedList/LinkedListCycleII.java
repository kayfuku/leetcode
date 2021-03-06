// 
// Author: 
// Date  : June 18, 2019

package leetcode;

import java.util.HashSet;
import java.util.Set;

public class LinkedListCycleII {
	// fields here.
	// private int count;

	public LinkedListCycleII() {
		// Initialization here.
		// this.count = 0;
	}

	// 1. HashSet.
	// O(N) time, O(N) space.
	public ListNode detectCycle(ListNode head) {
		Set<ListNode> set = new HashSet<>();
		ListNode cur = head;

		while (cur != null) {
			if (set.contains(cur)) {
				return cur;
			}

			set.add(cur);
			cur = cur.next;
		}

		return null;
	}

	// 2. Two Pointers. No need. Too dificult.
	// Let X be the length from head to the start point of cycle, and
	// Y is the length of the cycle. We know slow moves t, while fast moves 2t.
	// They meet at K where is the length from the start point of the cycle.
	// Then we have :
	//
	// t = X + nY + K
	// 2t = X + mY + K
	//
	// X = (Y - K) + (m - 2n - 1)Y
	// which means by finishing the rest length of the cycle and
	// some number of cycle lengths, the traveled distance is equal to X.
	// So, after the slow and fast pointers meet, move the fast and head one node
	// at a time. And they meet at the node we want. Just memorize it.

	// Then we get, t = (n - m)Y
	// O(N) time.
	// The time complexity depends on the cycle length. The cycle length is at most
	// N, where N is the total number of nodes, which means O(N) time complexity.

	// The easier time complexity analysis.
	// Looking at the slow pointer, it travels to the meeting point, which is O(N)
	// time. For the fast pointer, it travels for twice as much distance as the slow
	// pointer does, which makes no difference in asymptotic time complexity (Big-O
	// notation). So the time complexity is O(N).

	// O(1) space.
	public ListNode detectCycle2(ListNode head) {
		// if (head == null || head.next == null) {
		// // No cycle.
		// return null;
		// }

		ListNode slow = head, fast = head;
		while (fast != null && fast.next != null) {
			fast = fast.next.next;
			slow = slow.next;
			// Check after both pointers move.
			if (slow == fast) {
				// After the slow and fast pointers meet, move the fast and head one node
				// at a time. And they meet at the node we want.
				while (head != fast) {
					fast = fast.next;
					head = head.next;
				}
				return head;
			}
		}

		return null;
	}

	// Review
	public ListNode detectCycleR(ListNode head) {
		Set<ListNode> set = new HashSet<>();
		ListNode cur = head;
		while (cur != null) {
			if (set.contains(cur)) {
				return cur;
			} else {
				set.add(cur);
			}
			cur = cur.next;
		}

		return null;
	}

	// R3
	public ListNode detectCycleR3(ListNode head) {
		if (head == null || head.next == null) {
			return null;
		}
		Set<ListNode> set = new HashSet<>();
		ListNode node = head;
		while (node != null) {
			if (set.contains(node)) {
				return node;
			}
			set.add(node);
			node = node.next;
		}

		return null;
	}

	// For testing.
	public static void main(String[] args) {
		LinkedListCycleII solution = new LinkedListCycleII();

		// Test arguments.
		// int num = 24;
		// int target = 2;
		// solution.getInt(num, target);

	}

}
