//
// Author:
// Date  : September 17, 2020

package leetcode;

import java.util.HashSet;
import java.util.Set;

public class RobotRoomCleaner {
  // fields and classes here.
  // private int count;

  public RobotRoomCleaner() {
    // Initialization here.
    // this.count = 0;

  }

  
  // This is the robot's control interface.
  // You should not implement it, or speculate about its implementation
  interface Robot {
    // returns true if next cell is open and robot moves into the cell.
    // returns false if next cell is obstacle and robot stays on the current cell.
    boolean move();

    // Robot will stay on the same cell after calling turnLeft/turnRight.
    // Each turn will be 90 degrees.
    void turnLeft();
    void turnRight();

    // Clean the current cell.
    void clean();
  }


  // O(N - M) time, O(N - M) space, where N is a number of cells in the room 
  // and M is a number of obstacles.
  // Author: leetcode + kei
  // Date  : September 17, 2020

  // Going clockwise: 0: 'up', 1: 'right', 2: 'down', 3: 'left'
  int[][] directions = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
  Set<PairGen<Integer, Integer>> visited = new HashSet<>();
  Robot robot;

  public void cleanRoom(Robot robot) {
    this.robot = robot;
    backtrack(0, 0, 0);
  }

  public void backtrack(int row, int col, int d) {
    visited.add(new PairGen<>(row, col));
    robot.clean();

    // Going clockwise : 0: 'up', 1: 'right', 2: 'down', 3: 'left'
    // We need 'd' because this solution is based on the same idea as 
    // maze solving algorithm called right-hand rule.
    // https://en.wikipedia.org/wiki/Maze_solving_algorithm#Wall_follower
    for (int i = 0; i < 4; i++) {
      int newD = (d + i) % 4;
      int newRow = row + directions[newD][0];
      int newCol = col + directions[newD][1];

      if (!visited.contains(new PairGen<>(newRow, newCol)) && robot.move()) {
        backtrack(newRow, newCol, newD);
        // Go back with direction being the same.
        goBack();
      }
      // Turn the robot following chosen direction : clockwise
      robot.turnRight();
    }
  }

  public void goBack() {
    robot.turnRight();
    robot.turnRight();
    robot.move();
    robot.turnRight();
    robot.turnRight();
  }

  



  // For testing.
  @SuppressWarnings("unused")
  public static void main(String[] args) {
    RobotRoomCleaner solution = new RobotRoomCleaner();

    // Test arguments.
    // int num = 24;
    // int target = 2;
    // solution.getInt(num, target);



  }




}


