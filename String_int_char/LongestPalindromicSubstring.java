// 
// Author: 
// Date  : May 21, 2019

package leetcode;

public class LongestPalindromicSubstring {

	// Time complexity : O(n^2). Since expanding a palindrome around its center 
	// could take O(n) time, the overall complexity is O(n^2). 
	// Space complexity : O(1).
	public static String longestPalindrome(String s) {
		if (s == null || s.length() < 1) {
			return "";
		}
		int start = 0, end = 0;
		for (int i = 0; i < s.length(); i++) {
			int len1 = expandAroundCenter(s, i, i);
			int len2 = expandAroundCenter(s, i, i + 1);
			int len = Math.max(len1, len2);
			if (len > end - start + 1) {
				// This needs a little bit of verification using examples. 
				start = i - (len - 1) / 2; 
				end = i + len / 2;
			}
		}

		return s.substring(start, end + 1);

	}

	private static int expandAroundCenter(String s, int start, int end) {
		while (start >= 0 && end < s.length() && s.charAt(start) == s.charAt(end)) {
			start--;
			end++;			
		}
		// The start and end represent two elements wider than 
		// the substring length after the while loop. 
		return end - start - 1;
	}

	// Review 
	public String longestPalindromeR(String s) {
		if (s == null || s.length() < 1) {
			return "";
		}
		int start = 0, end = 0;
		for (int i = 0; i < s.length(); i++) {
			int len1 = expandAroundCenterR(s, i, i);
			int len2 = expandAroundCenterR(s, i, i + 1);
			int len = Math.max(len1, len2);
			if (len > end - start + 1) {
				start = i - (len - 1) / 2;
				end = i + len / 2;
			}
		}
		return s.substring(start, end + 1);
	}

	private int expandAroundCenterR(String s, int left, int right) {
		while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
			left--;
			right++;
		}
		return right - left - 1;
	}




	public static void main(String[] args) {
		String str = "babad";
		String retString = longestPalindrome(str);
		System.out.println(retString);

	}

}



















