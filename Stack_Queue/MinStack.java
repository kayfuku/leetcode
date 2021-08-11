// 
// Author: 
// Date  : January 7, 2020

package leetcode;

import java.util.ArrayDeque;
import java.util.Deque;

public class MinStack {

	// Author: sometimescrazy + kei
	// Date : January 7, 2020
	int min = Integer.MAX_VALUE;
	// Class Deque has built-in push() and pop().
	Deque<Integer> stack;

	public MinStack() {
		stack = new ArrayDeque<>();
	}

	public void push(int x) {
		// Push the old min first if x is smaller than or equal to the min.
		if (x <= min) {
			stack.push(min);
			// Update the current min.
			min = x;
		}
		stack.push(x);
	}

	public void pop() {
		// If pop operation could result in the change of the current min,
		// then pop twice and update the current min to the last min.
		if (stack.pop() == min) {
			// Update the current min.
			min = stack.pop();
		}
	}

	public int top() {
		return stack.peek();
	}

	public int getMin() {
		return min;
	}

	/**
	 * Your MinStack object will be instantiated and called as such: MinStack obj =
	 * new MinStack(); obj.push(x); obj.pop(); int param_3 = obj.top(); int param_4
	 * = obj.getMin();
	 */

	// For testing.
	@SuppressWarnings("unused")
	public static void main(String[] args) {
		MinStack solution = new MinStack();

		// Test arguments.
		// int num = 24;
		// int target = 2;
		// solution.getInt(num, target);

	}

}
