// 
// Author: 
// Date  : May 25, 2019

package leetcode;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Stack;


public class ValidParentheses {

	// Some fields here. 
	private Map<Character, Character> map;


	public ValidParentheses() {
		// Initialization here. 
		map = new HashMap<Character, Character>();
		map.put(')', '(');
		map.put('}', '{');
		map.put(']', '[');

	}

	// Basically, when I find a opening bracket, I push it to the stack. 
	// when I find a closing bracket, I check the top of the stack to see if 
	// there is the opening bracket of the same kind. 
	// O(n) time, O(n) space. 
	public boolean isValid(String s) {
		if (s == null || s.length() == 0) {
			return true;
		}

		LinkedList<Character> stack = new LinkedList<>();

		for (int i = 0; i < s.length(); i++) {
			char c = s.charAt(i);
			if (!map.containsKey(c)) {
				stack.push(c);
			} else {
				char topElement = (stack.isEmpty()) ? '#' : stack.pop();
				if (map.get(c) != topElement) {
					return false;
				}
			}
		}

		return stack.isEmpty();
	}


	// Review.
	public boolean isValidR(String s) {
		// corner
		if (s == null || s.length() == 0) {
			return true;
		}
		if (s.length() == 1) {
			return false;
		}

		Deque<Character> stack = new ArrayDeque<>();
		for (int i = 0; i < s.length(); i++) {
			char c = s.charAt(i);
			// The string only contains the 6 kinds of characters. 
			if (!map.containsKey(c)) {
				//			if (c == '(' || c == '[' || c == '{') { // ok
				stack.offerFirst(c);
			} else {
				if (stack.isEmpty()) {
					return false;
				}
				char open = map.get(c);
				char top = stack.pollFirst();	
				// If it's not the opening bracket of the same kind, 
				// then return false immediately. 
				// Otherwise keep going. 
				if (open != top) {
					return false;
				}
			}
		}    

		return stack.isEmpty();
	}


	// Review. 
	public boolean isValidR2(String s) {
		if (s.length() == 0 || s == null) {
			return true;
		}
		if (s.length() == 1) {
			return false;
		}

		Stack<Character> stack = new Stack<>();
		for (int i = 0; i < s.length(); i++) {
			char c = s.charAt(i);
			if (!map.containsKey(c)) {
				stack.push(c);
			} else {
				if (stack.isEmpty()) {
					return false;
				}
				char top = stack.pop();
				if (map.get(c) != top) {
					return false;
				}
			}
		}

		return stack.isEmpty();
	}








	// For testing. 
	public static void main(String[] args) {
		ValidParentheses solution = new ValidParentheses();

		// Test arguments. 
		//	    int num = 24;
		//	    int target = 2;
		//	    solution.getInt(num, target);

		String parentheses = "({}[()])";
		System.out.println(solution.isValidR(parentheses)); // true
		parentheses = "{}[()])";
		System.out.println(solution.isValidR(parentheses)); // false



	}

}


















