// 
// Author: 
// Date  : May 20, 2019

package leetcode;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class LongestSubstringWithoutRepeatingCharacters {

	// 1. Using Set. (Sliding Window)
	// Time complexity : O(2n) = O(n). In the worst case each character will be
	// visited twice by i and j.
	// Space complexity : O(min(m, n)). Same as the previous approach. We need O(k)
	// space for the sliding window,
	// where k is the size of the Set. The size of the Set is upper bounded by the
	// size of the string n and
	// the size of the charset/alphabet m.
	public static int lengthOfLongestSubstring1(String s) {
		Set<Character> set = new HashSet<Character>();
		// Two pointers.
		// i is start of substring, and j is end of substring.
		// Keep track of max length.
		int i = 0, j = 0, max = 0;
		int n = s.length();
		// i and j should be within the array.
		while (i < n && j < n) {
			if (!set.contains(s.charAt(j))) {
				set.add(s.charAt(j));
				// Length of the substring indicated by i and j inclusive is j - i + 1.
				max = Math.max(max, j - i + 1);
				j++;
			} else {
				// Invalid. Fix it.
				// Remove all the chars before the char that j now points to.
				set.remove(s.charAt(i));
				i++;
			}
		}

		return max;
	}

	// 2. Using Map. (Sliding Window)
	// Author: leetcode + kei
	// Date : October 15, 2020
	public static int lengthOfLongestSubstring2(String S) {
		// K: char, V: index (rightmost if there are duplicates.)
		Map<Character, Integer> map = new HashMap<>();
		int max = 0;
		// s: Start point of a substring.
		// e: End point of a substring.
		// 'e - s + 1' is the length of the substring.
		int s = 0;
		for (int e = 0; e < S.length(); e++) {
			char c = S.charAt(e);
			if (map.containsKey(c)) {
				// Point!
				// map.get(c): last (rightmost) index of the same char as c
				// s: previous start point.
				// Unlike approach 1, we don't remove char from the map, which means
				// there can be c in the map that does not lie in between map.get(c) and s.
				// If we hit c in the map, then we have to take the one of the two
				// which is closer to e.
				s = Math.max(map.get(c) + 1, s);
			}
			// K: char, V: index (rightmost, updated every time it occurs)
			map.put(c, e);
			// 'e - s + 1' is the length of the substring.
			// Note that even if I find the duplicate in the map, I need to check this
			// when the last duplicate is to the left of s. So, not if-else.
			max = Math.max(max, e - s + 1);
		}

		return max;
	}

	// Using Map.
	public static int lengthOfLongestSubstring22(String s) {
		Map<Character, Integer> map = new HashMap<>();
		int ans = 0;
		int n = s.length();
		for (int i = 0, j = 0; j < n; j++) {
			if (map.containsKey(s.charAt(j))) {
				i = Math.max(map.get(s.charAt(j)), i);
			}
			map.put(s.charAt(j), j + 1);
			ans = Math.max(ans, j - i + 1);
		}

		return ans;
	}

	// Review
	public static int lengthOfLongestSubstring(String s) {
		Set<Character> set = new HashSet<>();
		int i = 0, j = 0, maxLen = 0;
		int n = s.length();
		while (i < n && j < n) {
			if (!set.contains(s.charAt(j))) {
				set.add(s.charAt(j));
				maxLen = Math.max(maxLen, j - i + 1);
				j++;
			} else {
				set.remove(s.charAt(i));
				i++;
			}
		}

		return maxLen;
	}

	// R3 NG!
	// public static int lengthOfLongestSubstringR3(String S) {
	// if (S == null || S.length() == 0) {
	// return 0;
	// }
	// Map<Character, Integer> map = new HashMap<>();
	// int s = 0;
	// int e = 0;
	// int max = 0;
	// while (s < S.length() && e < S.length()) {
	// if (map.containsKey(S.charAt(e))) {
	// s = Math.max(map.get(S.charAt(e)) + 1, s);
	// } else {
	// // Check only here is NG!
	// max = Math.max(max, e - s + 1);
	// }
	// map.put(S.charAt(e), e);
	// e++;
	// }

	// return max;
	// }

	// NG!
	// public static int lengthOfLongestSubstringR3(String S) {
	// Set<Character> set = new HashSet<Character>();
	// // Two pointers.
	// // i is start of substring, and j is end of substring.
	// // Keep track of max length.
	// int s = 0, e = 0, max = 0;
	// int n = S.length();
	// // i and j should be within the array.
	// while (s < n && e < n) {
	// char c = S.charAt(e);
	// if (set.contains(c)) {
	// set.remove(S.charAt(s));
	// s++;
	// }
	// max = Math.max(max, e - s + 1);
	// set.add(c);
	// e++;
	// }

	// return max;
	// }

	public static void main(String[] args) {

		String str = "tmmzuxt";
		// String str = "abcabcbb";
		int ret = lengthOfLongestSubstring(str);

	}

}
