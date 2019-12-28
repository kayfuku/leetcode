// 
// Author: 
// Date  : May 23, 2019 

package leetcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LetterCombinationsOfAPhoneNumber {

	private List<String> output;
	private Map<String, String> map;

	LetterCombinationsOfAPhoneNumber() {
		output = new ArrayList<String>();
		map = new HashMap<String, String>();     

		map.put("2", "abc");
		map.put("3", "def");
		map.put("4", "ghi");
		map.put("5", "jkl");
		map.put("6", "mno");
		map.put("7", "pqrs");
		map.put("8", "tuv");
		map.put("9", "wxyz");   

	}

	// O(3^N * 4^M) time, where N is the number of digits in the input that 
	// maps to 3 letters (e.g. 2, 3, 4, 5, 6, 8) and M is the number of digits 
	// in the input that maps to 4 letters (e.g. 7, 9), and 
	// N+M is the total number digits in the input.
	// O(3^N * 4^M) space since one has to keep 3^N * 4^M solutions. 
	public List<String> letterCombinations(String digits) {
		if (digits.length() == 0) {
			return output;
		}
		// This is not good. 
		//    	StringBuilder combination = new StringBuilder();
		//    	backtrack(combination, digits);    	

		// The point is to pass in the empty string. 
		backtrack("", digits);    	
		return output;
	}
	private void backtrack(String combination, String nextDigits) {
		// Base Case. 
		if (nextDigits.length() == 0) {
			output.add(combination);
			return;
		}
		// DFS. 
		// Pop the first digit. 
		String nextDigit = nextDigits.substring(0, 1);
		String letters = map.get(nextDigit);
		for (int i = 0; i < letters.length(); i++) {
			char letter = letters.charAt(i);
			// The point is add letter in the argument. 
			backtrack(combination + letter, nextDigits.substring(1));
		}

	}


	// Review. 
	public List<String> letterCombinationsR(String digits) {
		// corner
		if (digits.length() == 0) {
			return output;
		}

		letterCombinationsR(digits, "");
		return output;
	}
	private void letterCombinationsR(String digits, String combination) {
		if (digits.length() == 0) {
			output.add(combination);
			return;
		}

		String nextDigit = digits.substring(0, 1);
		String letters = map.get(nextDigit);
		for (char letter : letters.toCharArray()) {

			letterCombinationsR(digits.substring(1), combination + letter);

			//combination += letter; // NG!
			//letterCombinations2(digits.substring(1), combination);
		}
	}

	// Review. 
	public List<String> letterCombinationsR2(String digits) {
		// corner
		if (digits.length() == 0) {
			return output;
		}

		StringBuilder prefix = new StringBuilder("");
		letterCombinationsR2(digits, prefix);
		return output;
	}
	private void letterCombinationsR2(String digits, StringBuilder prefix) {
		if (digits.length() == 0) {
			output.add(prefix.toString());
			return;
		}

		// Neighbors. 
		String nextDigit = digits.substring(0, 1);
		String letters = map.get(nextDigit);
		for (char letter : letters.toCharArray()) {
			// When we come back from lower recursion stack, we can start 
			// from common 'prefix' so far.  
			StringBuilder nextPrefix = new StringBuilder(prefix);
			nextPrefix.append(letter);
			
			letterCombinationsR2(digits.substring(1), nextPrefix);
		}
	}








	// Review. Iterative. 
	//	public List<String> letterCombinations2(String digits) {
	//    	List<String> list = new ArrayList<>();
	//    	
	//    	Set<String> visited = new HashSet<>();
	//    	Deque<String> stack = new ArrayDeque<>();
	//    	
	//    	
	//    	stack.offerFirst("dummy");
	//    	while (!stack.isEmpty()) {
	//    		String s = stack.pollFirst();
	//			if (!s.equals("dummy")) {
	//				
	//				
	//				
	//			}
	//    		
	//    		
	//    		
	//    		
	//		}
	//    	
	//    	
	//		
	//		
	//		
	//		return list;
	//	}







	public static void main(String[] args) {
		LetterCombinationsOfAPhoneNumber solution = new LetterCombinationsOfAPhoneNumber();

		String digits = "234";
		//		String digits = "";
		List<String> ret = solution.letterCombinationsR2(digits);
		System.out.println(ret.toString()); 
		// [adg, adh, adi, aeg, aeh, aei, afg, afh, afi, bdg, bdh, bdi, beg, beh, bei, bfg, bfh, bfi, cdg, cdh, cdi, ceg, ceh, cei, cfg, cfh, cfi]


	}

}



















