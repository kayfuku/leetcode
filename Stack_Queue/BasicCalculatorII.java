package leetcode;

import java.util.ArrayDeque;
import java.util.Deque;

public class BasicCalculatorII {
  // fields and classes here.
  // private int count;

  public BasicCalculatorII() {
    // Initialization here.
    // this.count = 0;

  }

  // Author: leetcode + kei
  // Date : June 24, 2021
  public int calculate(String s) {
    Deque<Integer> stack = new ArrayDeque<>();
    int result = 0;
    int sign = 1;
    int operand = 0;

    for (char c : s.toCharArray()) {
      if (Character.isDigit(c)) {
        // Form the operand.
        operand = operand * 10 + (c - '0');
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

  // Author: leetcode + kei
  // Date : June 24, 2021
  public int calculate2(String s) {
    if (s == null || s.length() == 0) {
      return 0;
    }

    int len = s.length();
    Deque<Integer> stack = new ArrayDeque<>();
    int operand = 0;
    char operator = '+';
    for (int i = 0; i < len; i++) {
      char curr = s.charAt(i);
      if (Character.isDigit(curr)) {
        operand = (operand * 10) + (curr - '0');
      }
      if (!Character.isDigit(curr) && !Character.isWhitespace(curr) || i == len - 1) {
        if (operator == '-') {
          stack.push(-operand);
        } else if (operator == '+') {
          stack.push(operand);
        } else if (operator == '*') {
          stack.push(stack.pop() * operand);
        } else if (operator == '/') {
          stack.push(stack.pop() / operand);
        }
        operator = curr;
        operand = 0;
      }
    }

    int result = 0;
    while (!stack.isEmpty()) {
      result += stack.pop();
    }

    return result;
  }

  // For testing.
  @SuppressWarnings("unused")
  public static void main(String[] args) {
    BasicCalculatorII solution = new BasicCalculatorII();

    // Test arguments.
    // int num = 24;
    // int target = 2;
    // System.out.println(solution.getInt(num, target));

    String s = "22 - 3 * 5";
    System.out.println(solution.calculate2(s));

    System.out.println("\ndone.");
  }

}
