// 
// Author: 
// Date  : May 22, 2019

package leetcode;

public class LongestCommonPrefix {

	// 1. Horizontal scanning.
	// O(M^2N) time, where M is the length of a string and N is the length of strs.
	// indexOf() takes O(M) time.
	public static String longestCommonPrefix1(String[] strs) {
		if (strs.length == 0) {
			return "";
		}

		String prefix = strs[0];
		for (int i = 1; i < strs.length; i++) {
			while (strs[i].indexOf(prefix) != 0) {
				// Cut off the last char.
				prefix = prefix.substring(0, prefix.length() - 1);
				if (prefix.isEmpty()) {
					return "";
				}
			}
		}

		return prefix;
	}

	// 2. Vertical scanning. (For interview)
	// O(S) time, where S is the total number of characters in the array.
	// In the best case, O(N * minLen), where N is the number of the strings, and
	// minLen is the shortest length of the strings in the array.
	// O(1) space.
	public static String longestCommonPrefix2(String[] strs) {
		if (strs == null || strs.length == 0) {
			return "";
		}
		if (strs.length == 1) {
			return strs[0];
		}
		// Compare characters on the same column (same character index of the strings)
		// before moving on to the next column.
		for (int j = 0; j < strs[0].length(); j++) {
			char c = strs[0].charAt(j);
			for (int i = 1; i < strs.length; i++) {
				if (strs[i].length() == j || strs[i].charAt(j) != c) {
					return strs[0].substring(0, j);
				}
			}
		}

		return strs[0];
	}

	// 3. Divide and Conquer.
	public static String longestCommonPrefix3(String[] strs) {
		if (strs == null || strs.length == 0) {
			return "";
		}
		return longestCommonPrefix3(strs, 0, strs.length - 1);
	}

	private static String longestCommonPrefix3(String[] strs, int left, int right) {
		if (left == right) {
			return strs[left];
		}

		int mid = (left + right) / 2;
		String lcpLeft = longestCommonPrefix3(strs, left, mid);
		String lcpRight = longestCommonPrefix3(strs, mid + 1, right);

		return commonPrefix(lcpLeft, lcpRight);
	}

	private static String commonPrefix(String str1, String str2) {
		int minLen = Math.min(str1.length(), str2.length());
		for (int i = 0; i < minLen; i++) {
			if (str1.charAt(i) != str2.charAt(i)) {
				return str1.substring(0, i);
			}
		}
		return str1.substring(0, minLen);
	}

	// *** Need to go over later. This is not going to work!
	// 4. Binary Search.
	public static String longestCommonPrefix4(String[] strs) {
		if (strs == null || strs.length == 0) {
			return "";
		}
		int minLen = Integer.MIN_VALUE;
		for (String string : strs) {
			minLen = Math.min(minLen, string.length());
		}
		int left = 0;
		// Maximum possible length of common prefix.
		int right = minLen - 1;
		int mid = 0;
		while (left <= right) {
			mid = (left + right) / 2;
			if (isCommonPrefix(strs, mid)) {
				left = mid;
			} else {
				right = mid - 1;
			}
		}

		return strs[0].substring(0, mid);
	}

	private static boolean isCommonPrefix(String[] strs, int mid) {
		String str = strs[0].substring(0, mid);
		for (int i = 0; i < strs.length; i++) {
			if (!strs[i].startsWith(str)) {
				return false;
			}
		}

		return true;
	}

	// 5. Trie.
	// O(S) time and space, where S is the total number of characters in the array.
	public static String longestCommonPrefix5(String[] strs) {
		if (strs == null || strs.length == 0) {
			return "";
		}
		if (strs.length == 1) {
			return strs[0];
		}

		// Create a Trie.
		// O(S) time, O(S) space.
		Trie trie = new Trie();
		for (int i = 1; i < strs.length; i++) {
			trie.insert(strs[i]);
		}

		// LCP Query: O(m) time, where m is strs[0] length.
		String lcp = trie.searchLongestPrefix(strs[0]);

		return lcp;
	}

	// Review (Vertical scanning).
	public String longestCommonPrefix(String[] strs) {
		// corner
		if (strs == null || strs.length == 0) {
			return "";
		}
		if (strs.length == 1) {
			return strs[0];
		}

		// Compare characters on the same column.
		for (int j = 0; j < strs[0].length(); j++) {
			char c = strs[0].charAt(j);
			for (int i = 1; i < strs.length; i++) {
				if (strs[i].length() == j || strs[i].charAt(j) != c) {
					return strs[0].substring(0, j);
				}
			}
		}

		return strs[0];
	}

	public static void main(String[] args) {
		String[] strs = new String[] { "leet", "leed", "le", "let" };
		System.out.println(longestCommonPrefix5(strs));

	}

}

class TrieNode {
	// R links to node children.
	private TrieNode[] links;
	private final int R = 26;
	private boolean isEnd;
	// Number of child nodes (non-null links).
	private int size;

	public TrieNode() {
		links = new TrieNode[R];
		isEnd = false;
		size = 0;
	}

	public boolean containsKey(char c) {
		return links[c - 'a'] != null;
	}

	public TrieNode get(char c) {
		return links[c - 'a'];
	}

	public void put(char c, TrieNode node) {
		links[c - 'a'] = node;
		size++;
	}

	public void setEnd() {
		isEnd = true;
	}

	public boolean isEnd() {
		return isEnd;
	}

	public int getNumChild() {
		return size;
	}
}

class Trie {
	private TrieNode root;

	public Trie() {
		root = new TrieNode();
	}

	// Inserts a word into the trie. O(m) time, O(m) space, where m is the word
	// length.
	public void insert(String word) {
		TrieNode node = root;
		for (int i = 0; i < word.length(); i++) {
			char currChar = word.charAt(i);
			if (!node.containsKey(currChar)) {
				node.put(currChar, new TrieNode());
			}
			node = node.get(currChar);
		}
		node.setEnd();
	}

	// Search the word in the Trie. O(m) time, O(1) space, where m is the word
	// length.
	public boolean search(String word) {
		TrieNode node = searchPrefix(word);
		return node != null && node.isEnd();
	}

	// Return the last node.
	public TrieNode searchPrefix(String word) {
		TrieNode node = root;
		for (int i = 0; i < word.length(); i++) {
			char currChar = word.charAt(i);
			if (node.containsKey(currChar)) {
				node = node.get(currChar);
			} else {
				return null;
			}
		}

		return node;
	}

	// Returns true if there is any word in the trie
	// that starts with the given prefix. O(m) time, O(1) space, where m is the
	// prefix length.
	public boolean startsWith(String prefix) {
		TrieNode node = searchPrefix(prefix);
		return node != null;
	}

	// O(m) time, O(1) space, where m is the word length.
	public String searchLongestPrefix(String word) {
		TrieNode node = root;
		StringBuilder prefix = new StringBuilder();
		for (int i = 0; i < word.length(); i++) {
			char currChar = word.charAt(i);
			if (node.containsKey(currChar) && node.getNumChild() == 1 && !node.isEnd()) {
				prefix.append(currChar);
				node = node.get(currChar);
			} else {
				return prefix.toString();
			}
		}

		return prefix.toString();
	}

}
