// 
// Author: 
// Date  : January 7, 2020

package leetcode;

import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

public class OpenTheLock {
	// fields and classes here. 
	//private int count;

	public OpenTheLock() {
		// Initialization here. 
		//this.count = 0;

	}


	// BFS
	// Author: yaoyimingg + kei
	// Date  : January 7, 2020
	public int openLock(String[] deadends, String target) {
		Queue<String> q = new LinkedList<>();
		Set<String> deads = new HashSet<>(Arrays.asList(deadends));
		Set<String> visited = new HashSet<>();

		if (deads.contains("0000")) {
			return -1;
		}

		q.offer("0000");
		visited.add("0000");

		// BFS with level count. 
		int numTurns = 0;
		while (!q.isEmpty()) {
			
			int size = q.size();
			
			// For each level
			while (size > 0) {
				String s = q.poll();

				if (s.equals(target)) {
					return numTurns;
				}

				// Neighbors
				StringBuilder sb = new StringBuilder(s);
				for (int i = 0; i < 4; i++) {
					char c = sb.charAt(i);

					// Dial up. c - '0' converts char to int. 
					String s1 = sb.substring(0, i) + (c == '9' ? 0 : c - '0' + 1) + sb.substring(i + 1);
					// Dial down. 
					String s2 = sb.substring(0, i) + (c == '0' ? 9 : c - '0' - 1) + sb.substring(i + 1);

					if (!visited.contains(s1) && !deads.contains(s1)) {
						q.offer(s1);
						visited.add(s1);
					}
					if (!visited.contains(s2) && !deads.contains(s2)) {
						q.offer(s2);
						visited.add(s2);
					}
				}
				
				size--;
			}
			
			numTurns++;
		}

		return -1;
	}


	// 2-end BFS (Bidirectional BFS?)
	// yaoyimingg talks about the improvement, which makes it three times faster. 
	// https://leetcode.com/problems/open-the-lock/discuss/110237/Regular-java-BFS-solution-and-2-end-BFS-solution-with-improvement
	// Author: yaoyimingg + kei
	// Date  : January 7, 2020
	class Solution {
		public int openLock(String[] deadends, String target) {
			// Note that these are sets, not queues. 
			Set<String> begin = new HashSet<>();
			Set<String> end = new HashSet<>();
			// deads plays a visited role as well. 
			Set<String> deads = new HashSet<>(Arrays.asList(deadends));
			
			if (deads.contains("0000")) {
				return -1;
			}
			begin.add("0000");
			end.add(target);
			
			int level = 0;
			Set<String> temp;
			while(!begin.isEmpty() && !end.isEmpty()) {
				if (begin.size() > end.size()) {
					temp = begin;
					begin = end;
					end = temp;
				}
				// Assert that begin set is smaller. 
				
				// Since we use sets, the next layer should be a different set. 
				// Otherwise they would be mixed. 
				temp = new HashSet<>();
				for (String s : begin) {
					// Intersect. 
					if (end.contains(s)) {
						return level;
					}
					
					deads.add(s);
					for (int i = 0; i < 4; i++) {
						char c = s.charAt(i);
						// Dial up.
						String s1 = s.substring(0, i) + (c == '9' ? 0 : c - '0' + 1) + s.substring(i + 1);
						// Dial down. 
						String s2 = s.substring(0, i) + (c == '0' ? 9 : c - '0' - 1) + s.substring(i + 1);
						if (!deads.contains(s1)) {
							temp.add(s1);
						}
						if (!deads.contains(s2)) {
							temp.add(s2);
						}
					}
				}
				
				level++;
				begin = temp;
			}
			
			return -1;
		}
	}







	// For testing. 
	public static void main(String[] args) {
		OpenTheLock solution = new OpenTheLock();

		// Test arguments. 
		// int num = 24;
		// int target = 2;
		// solution.getInt(num, target);



	}

}















