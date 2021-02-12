// 
// Author: 
// Date  : January 8, 2020

package leetcode;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

public class DailyTemperatures {
	// fields and classes here.
	// private int count;

	public DailyTemperatures() {
		// Initialization here.
		// this.count = 0;

	}

	// 1. Array. Next one is better.
	// O(NW) time, O(N + W) space.
	// Author: awice + kei
	// Date : January 8, 2020
	public int[] dailyTemperatures2(int[] T) {
		int[] ans = new int[T.length];
		// Temperature is at most 100.
		int[] next = new int[101];
		Arrays.fill(next, Integer.MAX_VALUE);
		// Traverse in reverse order.
		for (int i = T.length - 1; i >= 0; i--) {
			int warmerIndex = Integer.MAX_VALUE;
			// Find min index in the next array (soonest occurrence).
			for (int t = T[i] + 1; t <= 100; t++) {
				if (next[t] < warmerIndex) {
					warmerIndex = next[t];
				}
			}
			// If there is no warmerIndex, then ans[i] stays 0.
			if (warmerIndex < Integer.MAX_VALUE) {
				ans[i] = warmerIndex - i;
			}
			// next array holds indices in the index corresponding to the temperature.
			next[T[i]] = i;
		}
		return ans;
	}

	// 2. Stack
	// We are only interested in the future. In such case, we can iterate from
	// the tail.
	//
	// O(N) time, where N is the length of T.
	// Each index gets pushed and popped at most once from the stack.
	// O(W) space, where W is the number of allowed values for T[i].
	// The size of the stack is bounded as it represents strictly increasing
	// temperatures.
	// Author: awice + kei
	// Date : January 8, 2020
	public int[] dailyTemperatures(int[] T) {
		int[] ans = new int[T.length];
		// Holds a list of indices representing a list of strictly increasing
		// temperatures.
		Deque<Integer> stack = new ArrayDeque<>();
		// Iterate from the tail because we are only interested in the future.
		for (int i = T.length - 1; i >= 0; i--) {
			// The indices that have temperatures smaller than or equal to T[i] is no longer
			// needed because earlier index that has higher temperature is more important
			// than those values.
			while (!stack.isEmpty() && T[i] >= T[stack.peek()]) {
				// Remove the indices that have values smaller than or equal to T[i].
				stack.pop();
			}
			// Assert that stack.peek() returns an index that has the value next larger than
			// T[i].
			// stack.peek() - i tells how many days we have to wait until a warmer
			// temperature.
			ans[i] = stack.isEmpty() ? 0 : stack.peek() - i;

			stack.push(i);
		}

		return ans;
	}

	// For testing.
	@SuppressWarnings("unused")
	public static void main(String[] args) {
		DailyTemperatures solution = new DailyTemperatures();

		// Test arguments.
		// int num = 24;
		// int target = 2;
		// solution.getInt(num, target);

	}

}
