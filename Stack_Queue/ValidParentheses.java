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
		map = new HashMap<>();
		// To quickly check the type of the paired opening brackets.
		map.put(')', '(');
		map.put('}', '{');
		map.put(']', '[');
	}

	// The best solution is shown later.
	// Basically, when I find an opening bracket, I push it to the stack.
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
				// if (c == '(' || c == '[' || c == '{') { // ok
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

	// Review. This is the best.
	// Author: kei (AC)
	// Date : May 25, 2019, October 13, 2020
	public boolean isValidR2(String s) {
		if (s == null || s.length() == 0) {
			return true;
		}
		if (s.length() == 1) {
			return false;
		}

		Stack<Character> stack = new Stack<>();
		for (char c : s.toCharArray()) {
			if (!map.containsKey(c)) {
				// c is a opening bracket.
				stack.push(c);
			} else {
				// c is a closing bracket.
				// Don't forget this!
				if (stack.isEmpty()) {
					// Invalid order.
					return false;
				}
				char top = stack.pop();
				if (map.get(c) != top) {
					// Type mismatch.
					return false;
				}
			}
		}

		// Lack of closing bracket(s).
		return stack.isEmpty();
	}

	// R3
	public boolean isValidR3(String S) {
		if (S == null || S.length() == 0) {
			return true;
		}
		Stack<Character> s = new Stack<>();
		for (char c : S.toCharArray()) {
			if (!map.containsKey(c)) {
				s.push(c);
			} else {
				if (s.isEmpty()) {
					return false;
				}
				char top = s.pop();
				if (top != map.get(c)) {
					return false;
				}
			}
		}

		return s.isEmpty();
	}

	// For testing.
	public static void main(String[] args) {
		ValidParentheses solution = new ValidParentheses();

		// Test arguments.
		// int num = 24;
		// int target = 2;
		// solution.getInt(num, target);

		String parentheses = "({}[()])";
		System.out.println(solution.isValidR(parentheses)); // true
		parentheses = "{}[()])";
		System.out.println(solution.isValidR(parentheses)); // false

	}

}
