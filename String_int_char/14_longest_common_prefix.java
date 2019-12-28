// 14 Longest Common Prefix
// https://leetcode.com/problems/longest-common-prefix/solution/
// Author: LeetCode + kei
// Date  : January 17, 2019

package whiteboard;

public class Solution {
    
    
    // Solution 1: Horizontal scanning
    // O(N) time, where N is the sum of all characters in all strings. 
    public static String longestCommonPrefix1(String[] strs) {
        if (strs == null || strs.length == 0) {
            return "";
        }
        
        String prefix = strs[0];
        for (int i = 1; i < strs.length; i++) {
            // Keep checking if 'prefix' is the prefix of strs[i]
            // shrinking the prefix by removing the last character. 
            while (strs[i].indexOf(prefix) != 0) {
                prefix = prefix.substring(0, prefix.length() - 1);
                if (prefix.isEmpty()) {
                    return "";
                }
            }
        }
        
        return prefix;
    }
    
    // Solution 2: Vertical scanning
    // O(N) time, where N is the sum of all characters in all strings. 
    // Even though the worst case is still the same as Solution 1, 
    // in the best case there are at most n * minLen comparisons where 
    // minLen is the length of the shortest string in the array.
    // O(1) space. 
    public static String longestCommonPrefix2(String[] strs) {
        if (strs == null || strs.length == 0) {
            return "";
        }
        
        for (int i = 0; i < strs[0].length(); i++) {
            char c = strs[0].charAt(i);
            for (int j = 1; j < strs.length; j++) {
                // strs[j].charAt(i) should be careful about index out of bounds. 
                // Check length of strs[j] before that.  
                if (strs[j].length() == i || strs[j].charAt(i) != c) {
                    return strs[0].substring(0, i);
                }   
            }   
        }
        
        return strs[0];
    }
    
    
    

    public static void main(String[] args) {




    }

}



















