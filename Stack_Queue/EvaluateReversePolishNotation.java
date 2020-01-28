// 
// Author: 
// Date  : January 8, 2020

package leetcode;

import java.util.Stack;

public class EvaluateReversePolishNotation {
	// fields and classes here. 
	//private int count;

	public EvaluateReversePolishNotation() {
		// Initialization here. 
		//this.count = 0;

	}


	// Author: jeantimex + kei
	// Date  : January 8, 2020
	public int evalRPN(String[] a) {

		Stack<Integer> stack = new Stack<Integer>();

		for (int i = 0; i < a.length; i++) {
			switch (a[i]) {
			case "+":
				stack.push(stack.pop() + stack.pop());
				break;

			case "-":
				// Be careful about the order. 
				stack.push(-stack.pop() + stack.pop());
				break;

			case "*":
				stack.push(stack.pop() * stack.pop());
				break;

			case "/":
				int n1 = stack.pop(), n2 = stack.pop();
				// Be careful about the order. 
				stack.push(n2 / n1);
				break;

			default:
				// Number
				stack.push(Integer.parseInt(a[i]));
			}
		}

		return stack.pop();
	}







		// For testing. 
		public static void main(String[] args) {
			EvaluateReversePolishNotation solution = new EvaluateReversePolishNotation();

			// Test arguments. 
			// int num = 24;
			// int target = 2;
			// solution.getInt(num, target);



		}

	}















