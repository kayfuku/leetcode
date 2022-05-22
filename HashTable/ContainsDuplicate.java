// 
// Author: 
// Date  : December 31, 2019

package leetcode;

import java.util.HashSet;
import java.util.Set;

public class ContainsDuplicate {
	// fields and classes here.
	// private int count;

	public ContainsDuplicate() {
		// Initialization here.
		// this.count = 0;

	}

	// Author: leetcode + kei
	// Date : December 31, 2019
	public boolean containsDuplicate(int[] nums) {
		Set<Integer> set = new HashSet<>(nums.length);
		for (int x : nums) {
			if (set.contains(x)) {
				return true;
			}
			set.add(x);
		}

		return false;
	}

	// For testing.
	@SuppressWarnings("unused")

	public static void main(String[] args) {
		ContainsDuplicate solution = new ContainsDuplicate();

		// Test arguments.
		// int num = 24;
		// int target = 2;
		// solution.getInt(num, target);

	}

}
