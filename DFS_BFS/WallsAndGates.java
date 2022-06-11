// 
// Author: 
// Date  : January 6, 2020

package leetcode;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class WallsAndGates {
	// fields and classes here.
	// private int count;

	public WallsAndGates() {
		// Initialization here.
		// this.count = 0;

	}

	// BFS. Start nodes take turns traversing the graph.
	// Each gate is not fully searched before moving on to a new gate.
	// Each gate only looks at the areas within 1 space before we check the next
	// gate.
	// So each area within one space of the gates are checked and these rooms are
	// marked,
	// then added to the queue. Once all gates are checked, each new space is
	// checked, and so forth.
	// So, once a room gets hit, it has to be from the closest gate. (gregpen)
	// Author: LeetCode + kei
	// Date : January 6, 2020
	private static final int EMPTY = Integer.MAX_VALUE;
	private static final int GATE = 0;
	private static final List<int[]> DIRECTIONS = Arrays.asList(
			new int[] { 1, 0 }, // Up
			new int[] { -1, 0 }, // Down
			new int[] { 0, 1 }, // Right
			new int[] { 0, -1 } // Left
	);

	public void wallsAndGates(int[][] rooms) {
		// This order of lines is important. Otherwise, rooms[0] cause an error.
		if (rooms == null || rooms.length == 0) {
			return;
		}
		int R = rooms.length, C = rooms[0].length;

		// Put all the gates (start nodes) in the queue first so that
		// we get the distance to its nearest gate.
		// From the gate's point of view, each gate marks the rooms before other gates
		// mark the room.
		Queue<int[]> q = new LinkedList<>();
		for (int row = 0; row < R; row++) {
			for (int col = 0; col < C; col++) {
				if (rooms[row][col] == GATE) {
					q.add(new int[] { row, col });
				}
			}
		}

		// The gates take turns traversing the graph.
		while (!q.isEmpty()) {
			int[] point = q.poll();
			int row = point[0];
			int col = point[1];
			// Get the neighbors (child nodes).
			for (int[] dir : DIRECTIONS) {
				int r = row + dir[0];
				int c = col + dir[1];
				if (r < 0 || c < 0 || r >= R || c >= C || rooms[r][c] != EMPTY) {
					continue;
				}

				// The child node get one step further than the parent node.
				rooms[r][c] = rooms[row][col] + 1;
				// Put the child node in the queue.
				q.add(new int[] { r, c });
			}
		}
	}

	// For testing.
	@SuppressWarnings("unused")
	public static void main(String[] args) {
		WallsAndGates solution = new WallsAndGates();

		// Test arguments.
		// int num = 24;
		// int target = 2;
		// solution.getInt(num, target);

	}

}
