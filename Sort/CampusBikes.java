// 
// Author: 
// Date  : December 15, 2019

package leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

public class CampusBikes {
	// fields and classes here. 
	//private int count;

	public CampusBikes() {
		// Initialization here. 
		//this.count = 0;

	}


	// The next solution is better. 
	// O(MNlogMN) time, O(MN) space, where M and N are the length of workers and bikes respectively. 
	// Author: ts1992 + xueyezhen + kei
	// Date  : December 15, 2019
	class Node{
		int dist;
		int workerIdx;
		int bikeIdx;

		public Node(int dist, int workerIdx, int bikeIdx){
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
		PriorityQueue<Node> minHeap = new PriorityQueue<Node>(new Comparator<Node>(){
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
	// O(MN) time. O(MN) space. 
	// Author: ts1992 + kei
	// Date  : December 15, 2019
	@SuppressWarnings("unchecked")
	public int[] assignBikes2(int[][] workers, int[][] bikes) {
		// Notice that the Manhattan distance is between 0 and 2000, 
		// which means we can sort easily without even using priority queue. 
		int w = workers.length, b = bikes.length;
		int[] assigned = new int[w], occupied = new int[b];
		List<int[]>[] dists = new List[2001];
		Arrays.fill(assigned, -1);
		Arrays.fill(occupied, -1);
		
		// Check the distance between every combination of workers and bikes. 
		// Note that index of worker is first. Then index of bike is next. 
		for (int i = 0; i < w; i++) {
			for (int j = 0; j < b; j++) {
				int[] worker = workers[i];
				int[] bike = bikes[j];
				int dist = Math.abs(worker[0] - bike[0]) + Math.abs(worker[1] - bike[1]);
				if (dists[dist] == null) {
					dists[dist] = new ArrayList<int[]>();
				}
				dists[dist].add(new int[]{ i, j });
			}
		}
		
		// Need to keep track of the num of assigned because we added M * N pairs to the dists. 
		int numAssigned = 0;
		// To find the shortest distance, we search from index 0. 
		for (int d = 0; d < 2001 && numAssigned < w; d++) {
			if (dists[d] == null) continue;
			
			// foreach guarantees the order of insertion, which means smaller index of worker is
			// earlier and then smaller index of bike is earlier. 
			for (int[] pair : dists[d]) {
				// Filter it. 
				if (assigned[pair[0]] == -1 && occupied[pair[1]] == -1) {
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















