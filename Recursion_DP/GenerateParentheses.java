// 
// Author: 
// Date  : May 25, 2019

package leetcode;

import java.util.ArrayList;
import java.util.List;

public class GenerateParentheses {

	// Some fields here.
	// private int count;

	public GenerateParentheses() {
		// Initialization here.
		// count = 0;
	}

	// 1. Brute Force. No need to review.
	// O(2^n*n) time, O(2^n*n) space.
	public List<String> generateParenthesis2(int n) {
		List<String> result = new ArrayList<>();
		if (n < 1) {
			return result;
		}
		generateAll(new char[2 * n], 0, result);
		return result;
	}

	private void generateAll(char[] combinations, int pos, List<String> result) {
		// Base case.
		if (pos == combinations.length) {
			if (isValid(combinations)) {
				result.add(new String(combinations));
			}
			return;
		}

		// Left subtree.
		combinations[pos] = '(';
		generateAll(combinations, pos + 1, result);

		// Right subtree.
		combinations[pos] = ')';
		generateAll(combinations, pos + 1, result);
	}

	// O(n) time, O(n) space.
	private boolean isValid(char[] combinations) {
		int balance = 0;
		for (char c : combinations) {
			if (c == '(') {
				balance++;
			} else {
				balance--;
				if (balance < 0) {
					return false;
				}
			}
		}

		return balance == 0;
	}

	// 2. Backtracking.
	// Count the number of open and close parentheses so that one can check
	// if it's possible to add it.
	// O(4^N/sqrt(N)) time and space.
	// Author: LeetCode 22 + kei
	// Date : July 27, 2019
	public List<String> generateParenthesis(int n) {
		List<String> result = new ArrayList<>();
		if (n < 1) {
			return result;
		}

		backtrack("", 0, 0, result, n);
		return result;
	}

	private void backtrack(String cur, int open, int close, List<String> result, int n) {
		if (cur.length() == n * 2) {
			// Since 'cur' is not the same 'cur' on the previous stack, you don't have to
			// "new" it.
			result.add(cur);
			return;
		}

		// Left subtree to add open parentheses.
		// Cut the branches.
		if (open < n) {
			// By passing cur + '(', cur preserves the prefix on each recursion stack.
			backtrack(cur + '(', open + 1, close, result, n);
		}

		// Right subtree to add close parentheses.
		// Cut the branches.
		if (close < open) {
			backtrack(cur + ')', open, close + 1, result, n);
		}
	}

	// 2-1. Backtracking.
	// Well, since creating new string builder on each stack, space benefit is
	// almost the same as the above solution, I guess.
	// Author: LeetCode + kei
	// Date : July 27, 2019
	public List<String> generateParenthesis3(int n) {
		List<String> result = new ArrayList<>();
		if (n < 1) {
			return result;
		}

		StringBuilder sb = new StringBuilder(n * 2);
		backtrack3(sb, 0, 0, result, n);
		return result;
	}

	private void backtrack3(StringBuilder prefix, int open, int close, List<String> result, int n) {
		if (prefix.length() == n * 2) {
			result.add(new String(prefix));
			return;
		}

		// Left subtree.
		// Cut the branches.
		if (open < n) {
			// Create a new StringBuilder to separate the prefix so far and the string from
			// now on.
			StringBuilder curSb = new StringBuilder(prefix);
			curSb.append('(');
			backtrack3(curSb, open + 1, close, result, n);
		}

		// Right subtree.
		// Cut the branches.
		if (close < open) {
			// Create a new StringBuilder to separate the prefix so far and the string from
			// now on.
			StringBuilder curSb = new StringBuilder(prefix);
			curSb.append(')');
			backtrack3(curSb, open, close + 1, result, n);
		}
	}

	// 2-2. NG! Why is it wrong?
	// Because curSb is traversing the whole recursion stack so prefix has been
	// updated when coming back to the previous stack. We need to preserve the
	// prefix on each stack by copying the string builder before passing it in the
	// recursion call.
	// Author: LeetCode + kei
	// Date : July 27, 2019
	public List<String> generateParenthesisNG(int n) {
		List<String> result = new ArrayList<>();
		if (n < 1) {
			return result;
		}

		StringBuilder curSb = new StringBuilder(n * 2);
		backtrackNG(curSb, 0, 0, result, n, 1);
		return result;
	}

	private void backtrackNG(StringBuilder curSb, int open, int close, List<String> result, int n, int level) {
		if (curSb.length() == n * 2) {
			result.add(new String(curSb));
			return;
		}

		// Left subtree.
		// Cut the branches.
		if (open < n) {
			curSb.append('(');
			backtrackNG(curSb, open + 1, close, result, n, level + 1);
			// Backtrack. Undo the appending.
			String str = curSb.substring(0, curSb.length() - 1);
			curSb = new StringBuilder(str);
		}

		// Right subtree.
		// Cut the branches.
		if (close < open) {
			curSb.append(')');
			backtrackNG(curSb, open, close + 1, result, n, level + 1);
			// Backtrack. Undo the appending.
			String str = curSb.substring(0, curSb.length() - 1);
			curSb = new StringBuilder(str);
		}
	}

	// Review
	public List<String> generateParenthesisR(int n) {
		List<String> list = new ArrayList<>();

		// corner case.
		if (n < 1) {
			return list;
		}

		generateParenthesisR(n, "", 0, 0, list);
		return list;
	}

	private void generateParenthesisR(int n, String parentheses, int open, int close, List<String> list) {
		// Base case.
		if (parentheses.length() == n * 2) {
			list.add(parentheses);
			return;
		}

		if (open < n) {
			generateParenthesisR(n, parentheses + '(', open + 1, close, list);
		}
		if (open > close) {
			generateParenthesisR(n, parentheses + ')', open, close + 1, list);
		}
	}

	// Review.
	public List<String> generateParenthesisR2(int n) {
		List<String> ret = new ArrayList<>();
		if (n < 1) {
			return ret;
		}
		generateParenthesisR2("", 0, 0, ret, n);
		return ret;
	}

	private void generateParenthesisR2(String curStr, int open, int close, List<String> ret, int n) {
		if (curStr.length() == n * 2) {
			ret.add(curStr);
			return;
		}

		if (open < n) {
			generateParenthesisR2(curStr + '(', open + 1, close, ret, n);
		}
		if (close < open) {
			generateParenthesisR2(curStr + ')', open, close + 1, ret, n);
		}
	}

	// For testing.
	public static void main(String[] args) {
		GenerateParentheses solution = new GenerateParentheses();

		// Test arguments.
		// int num = 24;
		// int target = 2;
		// solution.getInt(num, target);

		// List<String> list = solution.generateParenthesis(3);
		List<String> list = solution.generateParenthesis2(3);
		System.out.println(list.toString()); // [((())), (()()), (())(), ()(()), ()()()]
		list = solution.generateParenthesisR(3);
		System.out.println(list.toString()); // [((())), (()()), (())(), ()(()), ()()()]
		list = solution.generateParenthesisNG(3);
		System.out.println(list.toString()); // [((())), ((())), ((()))]
		list = solution.generateParenthesis3(3);
		System.out.println(list.toString()); // [((())), (()()), (())(), ()(()), ()()()]

	}

}
