package leetcode;

import java.util.HashMap;

public class PrisonCellsAfterNDays {
  // fields and classes here.
  // private int count;

  public PrisonCellsAfterNDays() {
    // Initialization here.
    // this.count = 0;

  }

  // 1. Simulation with Fast Forwarding
  // Author: leetcode + kei
  // Date : February 3, 2021

  // Convert int[] to int bit map.
  protected int cellsToBitmap(int[] cells) {
    int stateBitmap = 0;
    // cells: [1, 0, 1, 0, 0, 0, 1, 1]
    for (int cell : cells) {
      // left shift by one bit
      stateBitmap <<= 1;
      // Store the bit.
      stateBitmap = (stateBitmap | cell);
    }
    // stateBitmap: 10100011
    return stateBitmap;
  }

  // Create the next cells based on the rule.
  protected int[] nextDay(int[] cells) {
    int[] newCells = new int[cells.length];
    // The first column in a new cell is always 0.
    newCells[0] = 0;
    for (int i = 1; i < cells.length - 1; i++) {
      // The rule
      newCells[i] = (cells[i - 1] == cells[i + 1]) ? 1 : 0;
    }
    // The last column in a new cell is always 0.
    newCells[cells.length - 1] = 0;
    return newCells;
  }

  public int[] prisonAfterNDays(int[] cells, int N) {
    HashMap<Integer, Integer> seen = new HashMap<>();
    boolean isFastForwarded = false;

    // step 1). run the simulation with hashmap
    // If we find a cycle during the simulation, then we can skip to the remainder.
    // The pattern to repeat something based on the remaining times.
    int n = N;
    while (n > 0) {
      if (!isFastForwarded) {
        int stateBitmap = this.cellsToBitmap(cells);
        if (seen.containsKey(stateBitmap)) {
          // the length of the cycle is seen[state_key] - n
          n %= seen.get(stateBitmap) - n;
          if (n == 0) {
            break;
          }
          isFastForwarded = true;
        } else {
          seen.put(stateBitmap, n);
        }
      }
      // check if there is still some steps remained,
      // with or without the fast-forwarding.
      cells = this.nextDay(cells);
      n--;
    }
    return cells;
  }

  // 2. Simulation with Bitmap
  // Author: leetcode + kei
  // Date : February 3, 2021

  public int[] prisonAfterNDays2(int[] cells, int N) {
    HashMap<Integer, Integer> seen = new HashMap<>();
    boolean isFastForwarded = false;

    // step 1). convert the cells to bitmap
    int stateBitmap = 0;
    for (int cell : cells) {
      stateBitmap <<= 1;
      stateBitmap = (stateBitmap | cell);
    }

    // step 2). run the simulation with hashmap
    int n = N;
    while (n > 0) {
      if (!isFastForwarded) {
        if (seen.containsKey(stateBitmap)) {
          // the length of the cycle is seen[state_key] - n
          n %= seen.get(stateBitmap) - n;
          if (n == 0) {
            break;
          }
          isFastForwarded = true;
        } else {
          seen.put(stateBitmap, n);
        }
      }
      // check if there is still some steps remained,
      // with or without the fast forwarding.
      stateBitmap = this.nextDay2(stateBitmap);
      n--;
    }

    // step 3). convert the bitmap back to the state cells
    int ret[] = new int[cells.length];
    // Take the bit out of LSB and put it in the array from tail.
    for (int i = cells.length - 1; i >= 0; i--) {
      ret[i] = (stateBitmap & 0x1);
      stateBitmap = stateBitmap >> 1;
    }
    return ret;
  }

  protected int nextDay2(int stateBitmap) {
    // Create the next cells based on the rule.
    stateBitmap = ~(stateBitmap << 1) ^ (stateBitmap >> 1);
    // set the head and tail to zero (0x7e: 01111110)
    stateBitmap = stateBitmap & 0x7e;
    return stateBitmap;
  }

  // For testing.
  @SuppressWarnings("unused")
  public static void main(String[] args) {
    PrisonCellsAfterNDays solution = new PrisonCellsAfterNDays();

    // Test arguments.
    // int num = 24;
    // int target = 2;
    // System.out.println(solution.getInt(num, target));

    System.out.println("\ndone.");
  }

}
