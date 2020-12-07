// 
// Author: 
// Date  : May 28, 2019

package leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GroupAnagrams {

	// Some fields here.
	// private int count;

	public GroupAnagrams() {
		// Initialization here.
		// count = 0;
	}

	// 1. Categorize by Sorted String. Slower.
	// Two strings are anagrams if and only if their sorted strings are equal.
	// O(NKlogK) time, O(NK) space, where N is the strs length, and K is the
	// maximum length of a string in strs.
	public List<List<String>> groupAnagrams2(String[] strs) {
		if (strs.length == 0) {
			return new ArrayList<List<String>>();
		}
		Map<String, List<String>> map = new HashMap<>();
		for (String string : strs) {
			char[] chars = string.toCharArray();
			// O(KlogK)
			Arrays.sort(chars);
			String key = new String(chars);
			if (!map.containsKey(key)) {
				map.put(key, new ArrayList<String>());
			}
			map.get(key).add(string);
		}

		return new ArrayList<List<String>>(map.values());
	}

	// 2. Categorize by Count. This is the best.
	// O(NK) time, O(NK) space, where N is the strs length, and
	// K is the maximum length of a string in the array.
	// Author: leetcode + kei (AC)
	// Date : October 13, 2020
	public List<List<String>> groupAnagrams(String[] strs) {
		// corner.
		// Don't forget this! Especially, when the main logic is pretty long,
		// this is forgettable.
		if (strs == null || strs.length == 0) {
			return new ArrayList<>();
		}

		Map<String, List<String>> map = new HashMap<>();
		int[] counts = new int[26];
		// N times.
		for (String str : strs) {
			// Initialize the count. Don't forget this!
			Arrays.fill(counts, 0);

			// O(K) time.
			for (char c : str.toCharArray()) {
				counts[c - 'a']++;
			}

			// Make a signature.
			StringBuilder sb = new StringBuilder();
			for (int n : counts) {
				// Be careful!
				// This delimiter is needed to distinguish "bbbbbbbbbbc" and "bdddddddddd"
				// Otherwise, sig would be the same, "0101000..."
				sb.append('#');
				sb.append(n);
			}
			String sig = sb.toString();

			// Group strings using the signature and map.
			if (!map.containsKey(sig)) {
				map.put(sig, new ArrayList<>());
			}
			map.get(sig).add(str);

			// NG! because ArrayList should be put in the map.
			// map.getOrDefault(sig, new ArrayList<>()).add(str);
		}

		// Return a list of lists of string.
		return new ArrayList<>(map.values());
	}

	// 2. Categorize by Count. NG. No need to review.
	// Two strings are anagrams if and only if their character counts
	// (respective number of occurrences of each character) are the same.
	// O(NK) time, O(NK) space, where N is the strs length, and K is the
	// maximum length of a string in strs.
	public List<List<String>> groupAnagramsNG(String[] strs) {
		if (strs.length == 0) {
			return new ArrayList<List<String>>();
		}
		Map<String, List<String>> map = new HashMap<>();
		int[] counts = new int[26];
		for (String s : strs) {
			Arrays.fill(counts, 0);
			char[] chars = s.toCharArray();
			// O(K). Make character counts.
			for (char c : chars) {
				counts[c - 'a']++;
			}
			StringBuilder sb = new StringBuilder("");
			// Make character counts string.
			for (int num : counts) {
				sb.append(num);
			}
			String key = sb.toString();
			if (!map.containsKey(key)) {
				map.put(key, new ArrayList<String>());
			}
			map.get(key).add(s);
		}

		return new ArrayList<List<String>>(map.values());
	}

	// R3
	public List<List<String>> groupAnagramsR3(String[] strs) {
		if (strs == null || strs.length == 0) {
			return new ArrayList<>();
		}
		Map<String, List<String>> map = new HashMap<>();
		for (String s : strs) {
			int[] counts = new int[26];
			for (char c : s.toCharArray()) {
				counts[c - 'a']++;
			}
			StringBuilder sb = new StringBuilder();
			for (int n : counts) {
				sb.append(n);
				sb.append('#');
			}
			String sig = sb.toString();
			if (!map.containsKey(sig)) {
				map.put(sig, new ArrayList<>());
			}
			map.get(sig).add(s);
		}

		return new ArrayList<>(map.values());
	}

	// For testing.
	public static void main(String[] args) {
		GroupAnagrams solution = new GroupAnagrams();

		// Test arguments.
		// int num = 24;
		// int target = 2;
		// solution.getInt(num, target);

		// String[] strs = new String[] { "eat", "tea", "tan", "ate", "nat", "bat" };
		// List<List<String>> ret = solution.groupAnagrams(strs);
		// System.out.println(ret.toString());
		// ret = solution.groupAnagrams2(strs);
		// System.out.println(ret.toString());

		// String[] strs2 = { "bdddddddddd", "bbbbbbbbbbc" };
		String[] strs2 = { "" };
		List<List<String>> ret2 = solution.groupAnagrams(strs2);
		System.out.println(ret2);

	}

}
