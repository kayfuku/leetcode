// 
// Author: 
// Date  : 

package leetcode;

import java.util.HashSet;

public class RemoveAllAdjacentDuplicatesInString {
	// fields and classes here. 
	//private int count;

	public RemoveAllAdjacentDuplicatesInString() {
		// Initialization here. 
		//this.count = 0;
		
	}

	
	// 1. Replace. Approach 2 is better. 
	// 
	// O(N^2) time, where N is the input string length. 
	// The while loop runs not more than N/2 times. The for loop runs 26 times. 
	// replace() takes O(N) time. In total O((N/2)*26*N) time. 
	// O(N) space, because replace function actually creates a copy of the string and 
	// thus uses O(N) space. 
	// 
	// Author: 
	// Date  : August 24, 2019
    public String removeDuplicates2(String S) {
    	// Generate 26 possible duplicates. 
    	HashSet<String> duplicates = new HashSet<>();
    	StringBuilder sb = new StringBuilder();
    	for (int i = 'a'; i <= 'z'; i++) {
			sb.setLength(0);
			sb.append(i);
			sb.append(i);
			duplicates.add(sb.toString());			
		}
    	
    	int prevLen = -1;
    	while (prevLen != S.length()) {
			prevLen = S.length();
			for (String d : duplicates) {
				S = S.replace(d, "");
			}
		}
    	
		return S;
	}
    
    // 2. Stack 
    // Iterate through all the characters in the string. 
    // If the current character is equal to the last element in stack, 
    // then pop that last element out of the stack. 
    // If the current character is not equal to the last element, 
    // then add the current character into the stack. 
    // 
    // O(N) time. 
    // O(N - D) time, where D is a total length for all duplicates. 
    // Author: @liaison and @andvary + kei
    // Date  : August 31, 2019
    public String removeDuplicates(String S) {
    	// corner
    	if (S == null || S.length() == 0) {
			return "";
		}
    	
    	StringBuilder sb = new StringBuilder();
    	sb.append(S.charAt(0));
    	for (int i = 1; i < S.length(); i++) {
    		// Attention!
    		// sb.length could be 0 during the loop. 
    		if (sb.length() != 0 && S.charAt(i) == sb.charAt(sb.length() - 1)) {
				sb.deleteCharAt(sb.length() - 1);
			} else {
				sb.append(S.charAt(i));
			}
		}
    	
    	return sb.toString();    	
    }




	
	
	

	// For testing. 
	public static void main(String[] args) {
		RemoveAllAdjacentDuplicatesInString solution = new RemoveAllAdjacentDuplicatesInString();

		// Test arguments. 
		// int num = 24;
		// int target = 2;
		// solution.getInt(num, target);
		String str = "abbaca";
		System.out.println(solution.removeDuplicates(str));



	}

}















