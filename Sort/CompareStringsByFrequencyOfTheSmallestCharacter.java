// 
// Author: 
// Date  : December 15, 2019

package leetcode;

public class CompareStringsByFrequencyOfTheSmallestCharacter {
	// fields and classes here. 
	//private int count;

	public CompareStringsByFrequencyOfTheSmallestCharacter() {
		// Initialization here. 
		//this.count = 0;

	}


	// Counting Sort. 
	// Author: alklin + kei
	// Date  : December 15, 2019
	public int[] numSmallerByFrequency(String[] queries, String[] words) {
		// Looking at this constraints, 
		// 1 <= queries[i].length, words[i].length <= 10
		// word length is at most 10! Short! Counting sort. 
		// Index 10 is needed, so the size is 11. 
		int[] fCounts = new int[11];
		for (String word : words) {
			int count = getFCount(word);
			fCounts[count]++;
		}

		// Get cumulative sum to get the desired total counts of words, 
		// which meets f(queries[i]) < f(W), in O(1) time later. 
		int cumSum = 0;
		for (int i = 0; i < fCounts.length; i++) {
			cumSum += fCounts[i];
			fCounts[i] = cumSum;
		}

		int[] res = new int[queries.length];
		for (int i = 0; i < queries.length; i++) {
			int count = getFCount(queries[i]);
			// Get the total counts of words that has larger count than 'count'. 
			res[i] = cumSum - fCounts[count];
		}

		return res; 
	}

	public int getFCount(String word) {
		int[] count = new int[26];

		for (int i = 0; i < word.length(); i++) {
			char c = word.charAt(i);
			count[c - 'a']++;
		}

		for (int i = 0; i < count.length; i++) {            
			if (count[i] != 0) {
				return count[i];
			}
		}

		return 0;
	}







	// For testing. 
	public static void main(String[] args) {
		CompareStringsByFrequencyOfTheSmallestCharacter solution = new CompareStringsByFrequencyOfTheSmallestCharacter();

		// Test arguments. 
		// int num = 24;
		// int target = 2;
		// solution.getInt(num, target);



	}

}















