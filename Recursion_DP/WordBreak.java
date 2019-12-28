// 
// Author: 
// Date  : July 5, 2019

package leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;

public class WordBreak {
	// fields here. 
	//	private int count;

	public WordBreak() {
		// Initialization here. 
		//		this.count = 0;
	}


	// 1. Brute Force
	// O(N^N) time, O(N) space. 
	public boolean wordBreak(String s, List<String> wordDict) {
		return wordBreak(s, new HashSet<>(wordDict), 0);
	}
	public boolean wordBreak(String s, Set<String> wordDict, int start) {
		if (start == s.length()) {
			return true;
		}

		for (int end = start + 1; end <= s.length(); end++) {
			String subs = s.substring(start, end);
			if (wordDict.contains(subs)) {
				return wordBreak(s, wordDict, end);
			}
		}

		return false;
	}


	// 2. Recursion with Memoization. 
	// O(N^2) time, O(N) space. 
	public boolean wordBreak2(String s, List<String> wordDict) {
		return wordBreak2(s, new HashSet<>(wordDict), 0, new Boolean[s.length()]);
	}
	public boolean wordBreak2(String s, Set<String> wordDict, int start, Boolean[] memo) {
		if (start == s.length()) {
			return true;
		}

		if (memo[start] != null) {
			return memo[start];
		}

		for (int end = start + 1; end <= s.length(); end++) {
			String subs = s.substring(start, end);
			if (wordDict.contains(subs)) {
				memo[start] = wordBreak2(s, wordDict, end, memo);
				return memo[start];
			}
		}

		memo[start] = false;
		return memo[start];
	}


	// 3. BFS. 
	// O(N^2) time, O(N) space.
	public boolean wordBreak3(String s, List<String> wordDict) {
		Set<String> wordDictSet = new HashSet<>(wordDict);
		Queue<Integer> queue = new LinkedList<>();
		int[] visited = new int[s.length()];
		queue.add(0);
		while (!queue.isEmpty()) {
			int start = queue.remove();
			if (visited[start] == 0) {
				for (int end = start + 1; end <= s.length(); end++) {
					String subs = s.substring(start, end);
					if (wordDictSet.contains(subs)) {
						queue.add(end);
						if (end == s.length()) {
							return true;
						}
					}
				}
				visited[start] = 1;
			}
		}
		return false;
	}


	// 4. Dynamic Programming. 
	// O(N^2) time, O(N) space.
	public boolean wordBreak4(String s, List<String> wordDict) {
		Set<String> wordDictSet = new HashSet<>(wordDict);
		boolean[] dp = new boolean[s.length() + 1];
		
		dp[0] = true;
		for (int i = 1; i <= s.length(); i++) {
			for (int j = 0; j < i; j++) {
				String subs = s.substring(j, i);
				if (dp[j] && wordDictSet.contains(subs)) {
					dp[i] = true;
					break;
				}
			}
		}
		
		return dp[s.length()];
	}



	// other classes here. 


	// For testing. 
	public static void main(String[] args) {
		WordBreak solution = new WordBreak();

		// Test arguments. 
		// int num = 24;
		// int target = 2;
		// solution.getInt(num, target);
		String s = "leetcode";
		List<String> list = new ArrayList<>(Arrays.asList("leet", "code"));
		System.out.println(solution.wordBreak4(s, list)); // true
		s = "applepenapple";
		list = new ArrayList<>(Arrays.asList("apple", "pen"));
		System.out.println(solution.wordBreak4(s, list)); // true
		s = "catsanddog";
		System.out.println(s);
		list = new ArrayList<>(Arrays.asList("cats", "dog", "sand", "and", "cat"));
		System.out.println(solution.wordBreak4(s, list)); // true
		s = "catsandog";
		System.out.println(s);
		list = new ArrayList<>(Arrays.asList("cats", "dog", "sand", "and", "cat"));
		System.out.println(solution.wordBreak4(s, list)); // false




	}

}















