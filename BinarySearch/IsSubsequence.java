// 
// Author: 
// Date  : July 17, 2019

package leetcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class IsSubsequence {
	// fields and classes here. 
	//private int count;

	public IsSubsequence() {
		// Initialization here. 
		//this.count = 0;
	}

	
	// 1. Using two pointers. 
	// For each character in s string (len<=100), check if t (len~=500,000) string contains that character. 
	// If it's in there, then move forward the pointer for s, otherwise move forward the 
	// pointer for t until finding the same character.  
	// 
	// https://leetcode.com/problems/is-subsequence/discuss/87254/Straight-forward-Java-simple-solution
	// O(N) time, where N is t length (~=500,000). 
	// O(1) space. 
	// 
	// Author: sampsonchan + kei
	// Date  : July 16, 2019
    public boolean isSubsequence(String s, String t) {
    	// corner
    	if (s == null || s.length() == 0) {
			return true;
		}
    	if (t== null) {
			return false;
		}
    	
    	int idxS = 0, idxT = 0;
    	while (idxT < t.length()) {
    		if (s.charAt(idxS) == t.charAt(idxT)) {
				idxS++;
    			if (idxS == s.length()) {
					return true;
				}
			}
			
    		idxT++;
		}
    	
		return false;
	}

    // 2. Using indexOf(). 
    // Use indexOf(char, fromIdx). 
	// For each character in s string (len<=100), check if t (len~=500,000) string contains that character. 
    // And then if it's in there, save that index, and start searching for the next character 
    // after that index. 
    // 
    // https://leetcode.com/problems/is-subsequence/discuss/87384/java-1ms-solution
	// O(N) time, where N is t length (~=500,000). 
	// O(1) space. 
    // 
	// Author: shuoshankou + kei
	// Date  : July 16, 2019
    public boolean isSubsequence2(String s, String t) {
    	if (s == null || s.length() == 0) {
			return true;
		}
    	if (t== null) {
			return false;
		}
    	
    	int fromIdx = 0;
    	for (int i = 0; i < s.length(); i++) {
    		char c = s.charAt(i);
    		fromIdx = t.indexOf(c, fromIdx);
    		if (fromIdx < 0) {
				return false;
			}
    		// Don't forget this!
    		fromIdx++;
		}
    	
		return true;
	}
    
    // 3. Follow Up. 
    // If there are lots of incoming S, say S1, S2, ... , Sk where k >= 1B, and 
    // you want to check one by one to see if T has its subsequence. 
    // In this scenario, how would you change your code?
    // 
    // Preprocessing and Search/Query using Binary Search. 
    // https://leetcode.com/problems/is-subsequence/discuss/87321/Java-code-for-the-problem-(two-pointer)-and-the-follow-up-(Binary-Search)
	// 
    // O(N) time.  
    // O(N) time to preprocess, where N is t length (~=500,000). 
	// O(MlogN) time to search/query, where M is s length (<=100), and N is list length (<=500,000). 
    // O(N) space for preprocessing. 
    // 
    // Author: shuoshankou + kei
	// Date  : July 16, 2019  
    public boolean isSubsequence3(String s, String t) {
        // Preprocessing. 
    	// O(N) time. 
    	// K: character, V: a list of indices of t string
        Map<Character, List<Integer>> charToIdxList = new HashMap<>();
        for (int i = 0; i < t.length(); i++) {
            if (!charToIdxList.containsKey(t.charAt(i))) {
            	charToIdxList.put(t.charAt(i), new ArrayList<Integer>());
            }
            charToIdxList.get(t.charAt(i)).add(i);
        }
        
        // Search/Query. 
        // O(MlogN) time, where M is s length, and N is list length. 
        int prevIdx = -1;
        for (int i = 0; i < s.length(); i++) {
            char ch= s.charAt(i);

            int nextIndex = getNextIndex(charToIdxList.get(ch), prevIdx);
            if (nextIndex < 0) {
            	return false;
            }
            prevIdx = nextIndex;
        }
        
        return true;
    }
    
    // R = M, Continuous version. (Next int)
    // Binary Search to find an index that is the smallest, but 
    // greater than the 'prevIdx'.
    // O(logN) time, where N is the list length.  
    // Author: kei (+ shuoshankou)
    // Date  : July 17, 2019
    public int getNextIndex(List<Integer> list, int prevIdx) {
        if (list == null) {
        	return -1;
        }
        int left = 0, right = list.size();

        while (left < right) {
            int mid = left + (right - left) / 2;
            if (list.get(mid) > prevIdx) {
            	// Search on the left subarray to find the smallest index that is bigger than the 
            	// previous index. 
            	right = mid;
            } else {
            	// list.get(mid) <= prevIdx
            	// The mid value (index in t string) is less than or equal to the 
                // previous index. This index cannot be used as part of s subsequence because 
            	// we cannot disturb the relative order. 
            	// Search on the right subarray to find a bigger index than the previous index.              	
            	left = mid + 1;
            }
        }
        
		// 'left' is the index of the next int. 
		// Note that it returns an index at which the next int would be 
		// if x is the last element in the list, or bigger than the last element, 
		// which means, it returns arr.length, an index out of bound.  
        return (left != list.size()) ? list.get(left) : -1;
    }
    
    
	
	
	

	// For testing. 
	public static void main(String[] args) {
		IsSubsequence solution = new IsSubsequence();

		// Test arguments. 
		// int num = 24;
		// int target = 2;
		// solution.getInt(num, target);



	}

}















