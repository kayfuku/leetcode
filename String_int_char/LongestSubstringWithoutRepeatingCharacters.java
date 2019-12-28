// 
// Author: 
// Date  : May 20, 2019

package leetcode;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;




public class LongestSubstringWithoutRepeatingCharacters {

	// Using Set. 
	// Time complexity : O(2n) = O(n). In the worst case each character will be visited twice by i and j.
    // Space complexity : O(min(m, n)). Same as the previous approach. We need O(k) space for the sliding window, 
	// where k is the size of the Set. The size of the Set is upper bounded by the size of the string n and 
	// the size of the charset/alphabet m. 
    public static int lengthOfLongestSubstring1(String s) {
    	Set<Character> set = new HashSet<Character>();
    	int i = 0, j = 0, ans = 0;
    	int n = s.length();
    	while (i < n && j < n) {
			if (!set.contains(s.charAt(j))) {
				set.add(s.charAt(j));
				ans = Math.max(ans, j - i + 1);
				j++;
			} else {
				set.remove(s.charAt(i));
				i++;
			}
		}
    	
    	return ans;	
    }


	// Using Map. 
    public static int lengthOfLongestSubstring2(String s) {
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


    
    

	public static void main(String[] args) {
		
		String str = "tmmzuxt";
		int ret = lengthOfLongestSubstring2(str);
		


	}

}




































