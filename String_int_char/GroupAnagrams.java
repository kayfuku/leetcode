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
	//	private int count;


	public GroupAnagrams() {
		// Initialization here. 
		//		count = 0;
	}

	// 1. Categorize by Sorted String. 
	// Two strings are anagrams if and only if their sorted strings are equal. 
	// O(NKlogK) time, O(NK) space, where N is the strs length, and K is the 
	// maximum length of a string in strs. 
	public List<List<String>> groupAnagrams(String[] strs) {
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

	// 2. Categorize by Count. 
	// Two strings are anagrams if and only if their character counts
	// (respective number of occurrences of each character) are the same.
	// O(NK) time, O(NK) space, where N is the strs length, and K is the 
	// maximum length of a string in strs. 
	public List<List<String>> groupAnagrams2(String[] strs) {
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

	// Review 
	// O(NK) time, O(NK) space, where N is the strs length, and
	// K is the maximum length of a string in the array. 
	public List<List<String>> groupAnagramsR(String[] strs) {
		// corner.
		// Don't forget this! Especially, when the main logic is pretty long, 
		// this is forgettable. 
		if (strs.length == 0 || strs == null) {
			return new ArrayList<List<String>>();
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
				sb.append(n);
			}
			String sig = sb.toString();
			
			// Group strings using the signature and map. 
			if (!map.containsKey(sig)) {
				map.put(sig, new ArrayList<String>());
			}
			map.get(sig).add(str);
		}

		return new ArrayList<List<String>>(map.values());
	}






	// For testing. 
	public static void main(String[] args) {
		GroupAnagrams solution = new GroupAnagrams();

		// Test arguments. 
		//	    int num = 24;
		//	    int target = 2;
		//	    solution.getInt(num, target);

		String[] strs = new String[]{ "eat", "tea", "tan", "ate", "nat", "bat" };
		List<List<String>> ret = solution.groupAnagrams(strs);
		System.out.println(ret.toString());
		ret = solution.groupAnagrams2(strs);
		System.out.println(ret.toString());


	}

}















