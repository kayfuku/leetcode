// 
// Author: 
// Date  : June 26, 2019

package leetcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FirstUniqueCharacterInString {
	// fields here. 
	//	private int count;

	public FirstUniqueCharacterInString() {
		// Initialization here. 
		//		this.count = 0;
	}


	// 1. Two passes. 
	// O(N) time, where N is the total number of characters in the input string. 
	// O(1) space. It takes additional constant space for count array. 
	public int firstUniqChar(String s) {
		int[] count = new int[26];
		for (int i = 0; i < s.length(); i++) {
			char c = s.charAt(i);
			count[c - 'a']++;
		}

		for (int i = 0; i < s.length(); i++) {
			char c = s.charAt(i);
			if (count[c - 'a'] == 1) {
				return i;
			}
		}

		return -1;
	}


	// 2. One pass. 
	// O(N) time, where N is the total number of characters in the input string. 
	// O(1) space, because map contains at most 26 characters, and list contains 
	// at most 26 indices. 
	// Author: fabrizio3
	// Date  : June 26, 2019
	public int firstUniqChar2(String s) {
		if (s == null || s.length() == 0) {
			return -1;
		}

		char[] chars = s.toCharArray();
		Map<Character, Integer> charToIdx = new HashMap<>();
		List<Integer> uniqCharIndices = new ArrayList<>();
		for (int i = 0; i < chars.length; i++) {
			if (!charToIdx.containsKey(chars[i])) {
				charToIdx.put(chars[i], i);
				uniqCharIndices.add(i);
			} else {
				Integer firstIdx = charToIdx.get(chars[i]);
				// O(1) time because uniqCharIndices contains at most 26 elements. 
				// If firstIdx is not in the list, then remove() does nothing. 
				uniqCharIndices.remove(firstIdx);				
			}
		}

		return uniqCharIndices.isEmpty() ? -1 : uniqCharIndices.get(0);
	}


	// Review
	public int firstUniqCharR(String s) {
		// corner. 
		if (s == null || s.length() == 0) {
			return -1;
		}

		int[] count = new int[26];
		for (char c : s.toCharArray()) {
			count[c - 'a']++;
		}

		for (int i = 0; i < s.length(); i++) {
			char c = s.charAt(i);
			if (count[c - 'a'] == 1) {
				return i;
			}
		}

		return -1;	
	}



	// For testing. 
	public static void main(String[] args) {
		FirstUniqueCharacterInString solution = new FirstUniqueCharacterInString();

		// Test arguments. 
		//	    int num = 24;
		//	    int target = 2;
		//	    solution.getInt(num, target);



	}

}















