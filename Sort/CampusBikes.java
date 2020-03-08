//
// Author:
// Date : December 15, 2019

package leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

public class CampusBikes {
  // fields and classes here.
  // private int count;

  public CampusBikes() {
    // Initialization here.
    // this.count = 0;

  }


  // The next solution is better.
  // O(MNlogMN) time, O(MN) space, where M and N are the length of workers and bikes respectively.
  // Author: ts1992 + xueyezhen + kei
  // Date : December 15, 2019
  class Node {
    int dist;
    int workerIdx;
    int bikeIdx;

    public Node(int dist, int workerIdx, int bikeIdx) {
      this.dist = dist;
      this.workerIdx = workerIdx;
      this.bikeIdx = bikeIdx;
    }
  }

  public int[] assignBikes(int[][] workers, int[][] bikes) {
    int w = workers.length, b = bikes.length;
    int[] wo = new int[w], bi = new int[b];
    Arrays.fill(wo, -1);
    Arrays.fill(bi, -1);

    // Create min heap ordered by dist, workerIdx, bikeIdx, ASC.
    PriorityQueue<Node> minHeap = new PriorityQueue<Node>(new Comparator<Node>() {
      @Override
      public int compare(Node o1, Node o2) {
        if (o1.dist == o2.dist) {
          if (o1.workerIdx == o2.workerIdx) {
            return o1.bikeIdx - o2.bikeIdx;
          }
          return o1.workerIdx - o2.workerIdx;
        }

        return o1.dist - o2.dist;
      }
    });

    // Put the data in the heap.
    for (int i = 0; i < workers.length; i++) {
      for (int j = 0; j < bikes.length; j++) {
        int dist = calcDist(workers[i], bikes[j]);
        Node n = new Node(dist, i, j);
        minHeap.offer(n);
      }
    }

    int assigned = 0;
    while (!minHeap.isEmpty() && assigned < w) {
      Node n = minHeap.poll();
      if (wo[n.workerIdx] == -1 && bi[n.bikeIdx] == -1) {
        wo[n.workerIdx] = n.bikeIdx;
        bi[n.bikeIdx] = n.workerIdx;
        assigned++;
      }
    }

    return wo;
  }

  public int calcDist(int[] worker, int[] bike) {
    return Math.abs(worker[0] - bike[0]) + Math.abs(worker[1] - bike[1]);
  }


  // Counting Sort! This is better.
  // O(MN) time. O(MN) space, where N is the num of workers and M is the num of bikes.
  // Author: ts1992 + kei
  // Date : December 15, 2019
  @SuppressWarnings("unchecked")
  public int[] assignBikes2(int[][] workers, int[][] bikes) {
    // Notice that the Manhattan distance is between 0 and 2000 (1998?),
    // which means we can sort easily without even using priority queue.
    int w = workers.length, b = bikes.length;
    int[] assigned = new int[w], occupied = new int[b];
    List<int[]>[] dists = new List[2001];
    Arrays.fill(assigned, -1);
    Arrays.fill(occupied, -1);

    // Check the distance between every combination of workers and bikes.
    // Note that index of worker is first. Then index of bike is next,
    // which is important for a tie case.
    // O(MN) time and O(MN) space
    for (int i = 0; i < w; i++) {
      for (int j = 0; j < b; j++) {
        int[] worker = workers[i];
        int[] bike = bikes[j];
        int dist = Math.abs(worker[0] - bike[0]) + Math.abs(worker[1] - bike[1]);
        if (dists[dist] == null) {
          dists[dist] = new ArrayList<int[]>();
        }
        // Add the pair to that distance list.
        dists[dist].add(new int[] {i, j});
      }
    }

    int numAssigned = 0;
    // To find the shortest distance, we search from index 0.
    // We can stop when all the workers are assigned.
    // O(1) time.
    for (int d = 0; d < 2001 && numAssigned < w; d++) {
      if (dists[d] == null) {
        continue;
      }

      // foreach loop guarantees the order of insertion, which means smaller index of worker is
      // earlier and then smaller index of bike is earlier.
      for (int[] pair : dists[d]) {
        // Need to filter it because we added all the combinations of workers and bikes.
        if (assigned[pair[0]] == -1 && occupied[pair[1]] == -1) {
          // assigned is what we want.
          assigned[pair[0]] = pair[1];
          occupied[pair[1]] = pair[0];
          numAssigned++;
        }
      }
    }

    return assigned;
  }



  // For testing.
  public static void main(String[] args) {
    CampusBikes solution = new CampusBikes();

    // Test arguments.
    // int num = 24;
    // int target = 2;
    // solution.getInt(num, target);



  }

}


