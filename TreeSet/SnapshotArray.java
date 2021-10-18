package leetcode;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

// Binary Search Tree
// Author: lee215 + kei
// Date : October 18, 2021
@SuppressWarnings("unchecked")
class SnapshotArray {

  // K: snapId, V: val
  TreeMap<Integer, Integer>[] A;
  int snapId = 0;

  public SnapshotArray(int length) {
    A = new TreeMap[length];
    // We cannot use foreach to initialize.
    for (int i = 0; i < length; i++) {
      A[i] = new TreeMap<>();
      A[i].put(0, 0);
    }
  }

  public void set(int index, int val) {
    A[index].put(snapId, val);
  }

  public int snap() {
    // return snapId, then add one to it.
    return snapId++;
  }

  public int get(int index, int snapId) {
    // floorEntry() returns an entry that has a key that is less than or equal to
    // the given key.
    return A[index].floorEntry(snapId).getValue();
  }
}

class SnapshotArrayMLE {
  // fields and classes here.
  // private int count;

  int[] currArray;
  int snapId;
  Map<Integer, int[]> snapshots;

  // MLE!
  // Author: kei (MLE)
  // Date : October 18, 2021
  public SnapshotArrayMLE(int length) {
    currArray = new int[length];
    snapId = 0;
    snapshots = new HashMap<>();
  }

  public void set(int index, int val) {
    currArray[index] = val;
  }

  public int snap() {
    int[] newArray = Arrays.copyOf(currArray, currArray.length);
    snapshots.put(snapId, newArray);
    snapId++;
    return snapId - 1;
  }

  public int get(int index, int snapId) {
    int val = snapshots.get(snapId)[index];
    return val;
  }

  /**
   * Your SnapshotArray object will be instantiated and called as such:
   * SnapshotArray obj = new SnapshotArray(length); obj.set(index,val); int
   * param_2 = obj.snap(); int param_3 = obj.get(index,snap_id);
   */

  // For testing.
  @SuppressWarnings("unused")
  public static void main(String[] args) {
    SnapshotArray solution = new SnapshotArray(0);

    // Test arguments.
    // int num = 24;
    // int target = 2;
    // System.out.println(solution.getInt(num, target));

    System.out.println("\ndone.");
  }

}
