// 
// Author: 
// Date  : December 31, 2019

package leetcode;

import java.util.HashSet;
import java.util.Set;

public class HappyNumber {
	// fields and classes here.
	// private int count;

	public HappyNumber() {
		// Initialization here.
		// this.count = 0;

	}

	// Author:
	// Date : January 2, 2020
	private int getNext(int n) {
		int totalSum = 0;
		while (n > 0) {
			int d = n % 10;
			n = n / 10;
			totalSum += d * d;
		}
		return totalSum;
	}

	public boolean isHappy(int n) {
		Set<Integer> seen = new HashSet<>();
		while (n != 1 && !seen.contains(n)) {
			seen.add(n);
			n = getNext(n);
		}
		return n == 1;
	}

	// For testing.
	@SuppressWarnings("unused")
	public static void main(String[] args) {
		HappyNumber solution = new HappyNumber();

		// Test arguments.
		// int num = 24;
		// int target = 2;
		// solution.getInt(num, target);

	}

}
