// 
// Author: 
// Date  : June 8, 2019

package leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
//import javafx.util.pair;
import java.util.Set;

public class WordLadder {
	// fields here. 
	//	private int count;

	public WordLadder() {
		// Initialization here. 
		//		this.count = 0;
	}

	// other classes here. 

	// This is better, and Reviewed one is even better because of no need to use Pair. 
	// To find a shortest path, build a graph and do BFS. 
	// Author: kei + LeetCode (Accepted)
	// O(MN) time, where M is the word length and N is the total number of words 
	// in the list because it takes M iterations for each of N words. Also, 
	// BFS in the worst case might go to each of the N words.
	// O(MN) space because, to store all M transformations for each of the N words, and 
	// visited dictionary is of N size, and  
	// queue for BFS in worst case would need space for all N words.
	public int ladderLength(String beginWord, String endWord, List<String> wordList) {
		// 1. Build a graph with adjacent list. 
		Map<String, ArrayList<String>> g = new HashMap<>();
		wordList.add(beginWord);

		int len = beginWord.length();

		// Group words using wild card '*'. ex. hot => [ *ot, h*t, ho* ]
		// Adjacent List is good enough if you can get the neighbor nodes when you traverse. 
		// Key does not have to be node. 
		// Lambda version of foreach is slower than this!
		// O(MN) time. O(MN) space. 
		for (String word : wordList) {
			for (int i = 0; i < len; i++) {
				StringBuilder sb = new StringBuilder(len);
				sb.append(word.substring(0, i));
				sb.append("*");
				sb.append(word.substring(i + 1, len));
				String newWord = sb.toString();
				ArrayList<String> neighbors = g.getOrDefault(newWord, new ArrayList<>());
				neighbors.add(word);
				// Don't forget this!
				g.put(newWord, neighbors); 	
			}
		}

		// 2. Do BFS to find a shortest path. 
		// O(MN) time, O(MN) space. 
		LinkedList<Pair> queue = new LinkedList<>();
		Set<String> visited = new HashSet<>(); 

		queue.add(new Pair(beginWord, 1));
		visited.add(beginWord);

		while (!queue.isEmpty()) {
			Pair p = queue.poll();
			String curWord = p.getKey();
			int level = p.getValue();

			// Traverse. 
			for (int i = 0; i < len; i++) {
				StringBuilder sb = new StringBuilder(len);
				sb.append(curWord.substring(0, i));
				sb.append("*");
				sb.append(curWord.substring(i + 1, len));
				String newWord = sb.toString();	
				// Iterate neighbors. 
				for (String adjacent : g.get(newWord)) {
					if (!visited.contains(adjacent)) {
						// Checking is here because here is the first touch of the new nodes. 
						if (adjacent.equals(endWord)) {
							return level + 1;
						}
						queue.add(new Pair(adjacent, level + 1));
						visited.add(adjacent);
					}
				}
			}
		}

		return 0;
	}


	// No need to review this. 
	public int ladderLength2(String beginWord, String endWord, List<String> wordList) {

		// Since all words are of same length.
		int L = beginWord.length();

		// Dictionary to hold combination of words that can be formed,
		// from any given word. By changing one letter at a time.
		HashMap<String, ArrayList<String>> allComboDict = new HashMap<String, ArrayList<String>>();

		wordList.forEach(
				word -> {
					for (int i = 0; i < L; i++) {
						// Key is the generic word
						// Value is a list of words which have the same intermediate generic word.
						String newWord = word.substring(0, i) + '*' + word.substring(i + 1, L);
						ArrayList<String> transformations =
								allComboDict.getOrDefault(newWord, new ArrayList<String>());
						transformations.add(word);
						allComboDict.put(newWord, transformations);
					}
				});

		// Queue for BFS
		Queue<Pair> Q = new LinkedList<>();
		Q.add(new Pair(beginWord, 1));

		// Visited to make sure we don't repeat processing same word.
		HashMap<String, Boolean> visited = new HashMap<String, Boolean>();
		visited.put(beginWord, true);

		while (!Q.isEmpty()) {
			Pair node = Q.remove();
			String word = node.getKey();
			int level = node.getValue();
			for (int i = 0; i < L; i++) {

				// Intermediate words for current word
				String newWord = word.substring(0, i) + '*' + word.substring(i + 1, L);

				// Next states are all the words which share the same intermediate state.
				for (String adjacentWord : allComboDict.getOrDefault(newWord, new ArrayList<>())) {
					// If at any point if we find what we are looking for
					// i.e. the end word - we can return with the answer.
					if (adjacentWord.equals(endWord)) {
						return level + 1;
					}
					// Otherwise, add it to the BFS Queue. Also mark it visited
					if (!visited.containsKey(adjacentWord)) {
						visited.put(adjacentWord, true);
						Q.add(new Pair(adjacentWord, level + 1));
					}
				}
			}
		}

		return 0;
	}


	// Review. Accepted. 
	public int ladderLengthR(String beginWord, String endWord, List<String> wordList) {
		// 1. Build a graph. 
		Map<String, List<String>> map = new HashMap<>();
		
		wordList.add(beginWord);
		for (String word : wordList) {
			for (int i = 0; i < word.length(); i++) {
				StringBuilder sb = new StringBuilder();
				sb.append(word.substring(0, i));
				sb.append("*");
				sb.append(word.substring(i + 1, word.length()));
				String newWord = sb.toString();
				List<String> neighbors = map.getOrDefault(newWord, new ArrayList<String>());
				neighbors.add(word);
				map.put(newWord, neighbors);
			}
		}

		// 2. Do BFS with level. 
		Queue<String> queue = new LinkedList<>();
		Set<String> visited = new HashSet<>();

		queue.add(beginWord);
		visited.add(beginWord);
		int numWords = 0; // level
		while (!queue.isEmpty()) {
			// For each level. 
			numWords++;
			int size = queue.size();
			for (int j = 0; j < size; j++) {
				String curWord = queue.poll();

				for (int i = 0; i < curWord.length(); i++) {
					StringBuilder sb = new StringBuilder();
					sb.append(curWord.substring(0, i));
					sb.append("*");
					sb.append(curWord.substring(i + 1, curWord.length()));
					String newWord = sb.toString();
					for (String word : map.get(newWord)) {
						if (!visited.contains(word)) {
							if (word.equals(endWord)) {
								return numWords + 1;	
							}	
							queue.add(word);
							visited.add(word);
						}	
					}
				} // neighbors

			} // level
		} // while

		return 0;	
	}





	// For testing. 
	public static void main(String[] args) {
		WordLadder solution = new WordLadder();

		// Test arguments. 
		//	    int num = 24;
		//	    int target = 2;
		//	    solution.getInt(num, target);

		String beginWord = "hit";
		String endWord = "cog";
		List<String> wordList = new ArrayList<String>(
				Arrays.asList("hot", "dot", "dog", "lot", "log", "cog"));

		System.out.println(solution.ladderLength(beginWord, endWord, wordList)); // 5




	}

}

// This is also in the javafx library. 
class Pair {
	private String string;
	private int integer;

	public Pair(String string, int integer) {
		this.string = string;
		this.integer = integer;
	}

	public String getKey() {
		return string;
	}
	public int getValue() {
		return integer;
	}

}












