// 
// Author: 
// Date  : January 3, 2020

package leetcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GroupShiftedStrings {
	// fields and classes here.
	// private int count;

	public GroupShiftedStrings() {
		// Initialization here.
		// this.count = 0;

	}

	// Author: diddit + kei
	// Date : January 3, 2020
	public List<List<String>> groupStrings(String[] strings) {
		Map<String, List<String>> map = new HashMap<>();

		for (String s : strings) {
			String key = getKey(s);
			List<String> list = map.getOrDefault(key, new ArrayList<>());
			list.add(s);
			map.put(key, list);
		}

		return new ArrayList<>(map.values());
	}

	private String getKey(String s) {
		char[] chars = s.toCharArray();
		StringBuilder key = new StringBuilder();
		for (int i = 1; i < chars.length; i++) {
			// Make a signature by appending the difference from the previous character.

			char c = (char) ((s.charAt(i) - s.charAt(i - 1) + 26) % 26 + 'a');
			key.append(c);
		}

		return key.toString();
	}

	// For testing.
	@SuppressWarnings("unused")
	public static void main(String[] args) {
		GroupShiftedStrings solution = new GroupShiftedStrings();

		// Test arguments.
		// int num = 24;
		// int target = 2;
		// solution.getInt(num, target);

	}

}
