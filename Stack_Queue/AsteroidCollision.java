package leetcode;

import java.util.LinkedList;

public class AsteroidCollision {
  // fields and classes here.
  // private int count;

  public AsteroidCollision() {
    // Initialization here.
    // this.count = 0;

  }

  // Author: alexander + daijidj + kei
  // Date : October 3, 2021
  public int[] asteroidCollision(int[] asteroids) {
    // Use LinkedList to simulate a stack so that we don't need to reverse at end.
    LinkedList<Integer> stack = new LinkedList<>();
    for (int ast : asteroids) {
      // Think about what should be in the stack.
      // Collision occurs in (+, -) pair.
      if (ast > 0) {
        stack.add(ast);
        continue;
      }
      // ast is negative.
      while (!stack.isEmpty() && stack.peekLast() > 0 && stack.peekLast() < Math.abs(ast)) {
        // s| ? 2 1 3 | -5
        // s| ? . . . | -5
        stack.pollLast();
      }
      if (stack.isEmpty() || stack.peekLast() < 0) {
        // s| -5 |
        stack.add(ast);
      } else if (ast + stack.peekLast() == 0) {
        // s| 2 1 5 | -5
        // s| 2 1 |
        stack.pollLast();
      }
    }

    return stack.stream().mapToInt(i -> i).toArray();
  }

  // For testing.
  @SuppressWarnings("unused")
  public static void main(String[] args) {
    AsteroidCollision solution = new AsteroidCollision();

    // Test arguments.
    // int num = 24;
    // int target = 2;
    // System.out.println(solution.getInt(num, target));

    System.out.println("\ndone.");
  }

}
