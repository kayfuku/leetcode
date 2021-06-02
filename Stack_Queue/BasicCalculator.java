package leetcode;

import java.util.Deque;
import java.util.LinkedList;

public class BasicCalculator {
  // fields and classes here.
  // private int count;

  public BasicCalculator() {
    // Initialization here.
    // this.count = 0;

  }

  // Stack
  // O(N) time and space
  // Author: leetcode + kei
  // Date : May 22, 2021
  public int calculate(String s) {

    Deque<Integer> stack = new LinkedList<>();
    int operand = 0;
    int result = 0; // For the on-going result
    int sign = 1; // 1 means positive, -1 means negative

    // Iterate expression string character by character.
    // Evaluate to the left when we find '+', '-', or ')'.
    // We use a stack when we find parenthesis.
    for (int i = 0; i < s.length(); i++) {
      char ch = s.charAt(i);
      if (Character.isDigit(ch)) {
        // Forming operand, since it could be more than one digit
        operand = 10 * operand + (int) (ch - '0');
      } else if (ch == '+') {
        // Evaluate the expression to the left,
        // with result, sign, operand
        result += sign * operand;
        // Save the recently encountered '+' sign
        sign = 1;
        // Reset operand
        operand = 0;
      } else if (ch == '-') {
        result += sign * operand;
        sign = -1;
        operand = 0;
      } else if (ch == '(') {
        // Push the result and sign on to the stack, for later
        // We push the result first, then sign
        stack.push(result);
        stack.push(sign);
        // Reset sign and result, as if new evaluation begins for the new
        // sub-expression
        sign = 1;
        result = 0;
      } else if (ch == ')') {
        // Evaluate the expression to the left with result, sign and operand
        result += sign * operand;
        // ')' marks end of expression within a set of parenthesis
        // Its result is multiplied with sign on top of stack
        // as stack.pop() is the sign before the parenthesis
        result *= stack.pop();
        // Then add to the next operand on the top.
        // as stack.pop() is the result calculated before this parenthesis
        // (operand on stack) + (sign on stack * (result from parenthesis))
        result += stack.pop();
        // Reset sign and operand
        sign = 1;
        operand = 0;
      }
    }

    result += sign * operand;
    return result;
  }

  // For testing.
  @SuppressWarnings("unused")
  public static void main(String[] args) {
    BasicCalculator solution = new BasicCalculator();

    // Test arguments.
    // int num = 24;
    // int target = 2;
    // System.out.println(solution.getInt(num, target));

    System.out.println("\ndone.");
  }

}
