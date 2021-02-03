package leetcode;

public class RobotBoundedInCircle {
  // fields and classes here.
  // private int count;

  public RobotBoundedInCircle() {
    // Initialization here.
    // this.count = 0;

  }

  // Author: leetcode + kei
  // Date : February 2, 2021
  public boolean isRobotBounded(String instructions) {
    // 2D array for directions { x, y }.
    // 0: up, 1: right, 2: down, 3: left
    int[][] directions = new int[][] { { 0, 1 }, { 1, 0 }, { 0, -1 }, { -1, 0 } };
    // Initial position is in the center.
    int x = 0, y = 0;
    // facing north
    int dir = 0;

    for (char i : instructions.toCharArray()) {
      if (i == 'L') {
        // Trun 90 degrees to the left.
        // 'dir = (dir - 1) % 4' have to deal with negative indices.
        dir = (dir + 3) % 4;
      } else if (i == 'R') {
        // Trun 90 degrees to the right.
        dir = (dir + 1) % 4;
      } else {
        x += directions[dir][0];
        y += directions[dir][1];
      }
    }

    // after one cycle:
    // robot returns into initial position
    // or robot doesn't face north (Theory)
    // https://leetcode.com/problems/robot-bounded-in-circle/solution/
    return (x == 0 && y == 0) || (dir != 0);
  }

  // For testing.
  @SuppressWarnings("unused")
  public static void main(String[] args) {
    RobotBoundedInCircle solution = new RobotBoundedInCircle();

    // Test arguments.
    // int num = 24;
    // int target = 2;
    // System.out.println(solution.getInt(num, target));

    System.out.println("\ndone.");
  }

}
