package leetcode;

import java.util.ArrayDeque;
import java.util.Deque;

public class BasicCalculator {
  // fields and classes here.
  // private int count;

  public BasicCalculator() {
    // Initialization here.
    // this.count = 0;

  }

  // Stack
  // I'm gonna go through the input String character by character.
  // If the character is digit, then ...
  // If the character is '+' sign, then ...
  //
  // O(N) time and space, where N is the total number of characters.
  // Author: leetcode + kei
  // Date : May 22, 2021
  public int calculate(String s) {
    Deque<Integer> stack = new ArrayDeque<>();

    // Init
    int result = 0; // For the on-going result
    int sign = 1; // 1 means positive, -1 means negative
    int operand = 0;

    // 1 + 2 + 1
    // The tricky part is that we find an operator/sign first, then we know
    // the operand after that. We save the sign first, and when we evaluate the
    // expression so far, we use that sign.

    // Go through the expression string character by character.
    // Evaluate to the left when we find '+', '-', ')', or end of loop.
    // We use a stack when we find parenthesis.
    for (int i = 0; i < s.length(); i++) {
      char c = s.charAt(i);
      if (Character.isDigit(c)) {
        // Form operand, since it could be more than one digit.
        operand = 10 * operand + (c - '0');
      } else if (c == '+') {
        // We can evaluate the expression to the left,
        // with result, sign, operand
        result += sign * operand;
        // Save the recently encountered '+' sign
        sign = 1;
        // Reset operand
        operand = 0;
      } else if (c == '-') {
        // We can evaluate the expression to the left,
        result += sign * operand;
        // Save the '-' sign.
        sign = -1;
        operand = 0;
      } else if (c == '(') {
        // Push the result so far and sign onto the stack, for later use.
        // We push the result first, then sign in the stack.
        stack.push(result);
        stack.push(sign);
        // Reset result, sign, and operand, as if new evaluation begins for the new
        // sub-expression
        result = 0;
        sign = 1;
        operand = 0;
      } else if (c == ')') {
        // We can evaluate the sub-expression to the left.
        result += sign * operand;
        // Now that we know the sub-expression ended, we can also evaluate to the left.
        // First we take the sign out of the stack, and then add the result we saved
        // before to the result of the sub-expression.
        result *= stack.pop();
        result += stack.pop();
        // Reset sign and operand.
        sign = 1;
        operand = 0;
      }
    }

    // We need to evaluate again.
    result += sign * operand;

    return result;
  }

  // Review
  public int calculateR(String s) {
    Deque<Integer> stack = new ArrayDeque<>();
    int result = 0;
    int sign = 1;
    int operand = 0;

    for (char c : s.toCharArray()) {
      if (Character.isDigit(c)) {
        // Form the operand.
        operand = operand * 10 + (int) (c - '0');
      } else if (c == '+') {
        // 1 + 2 + 3
        // The tricky part is that we find sign first, and then find operand after that.
        // So, we need to save the sign for the next operand.
        // res: 1
        // When we find the '+' sign, we update the sign.
        // sign: 1
        // op: 0
        result += sign * operand;
        sign = 1;
        operand = 0;
      } else if (c == '-') {
        result += sign * operand;
        sign = -1;
        operand = 0;
      } else if (c == '(') {
        // res - (2 + 4)
        // Set aside the result in the stack.
        stack.push(result);
        stack.push(sign);
        // We initialize variables so that we can start the evaluation for the
        // sub-expression.
        result = 0;
        sign = 1;
        operand = 0;
      } else if (c == ')') {
        // ... res + 4)
        result += sign * operand;
        // Now that we know the sub-expression ended, we can also evaluate to the left.
        // First we take the sign out of the stack, and then add the result we saved
        // before to the result of the sub-expression.
        result *= stack.pop();
        result += stack.pop();
        // Init
        sign = 1;
        operand = 0;
      }
    }

    // Don't forget this.
    // ... res + 5
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
