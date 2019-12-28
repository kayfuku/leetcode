// 
// Author: 
// Date  : July 15, 2019

package leetcode;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

public class MeetingRooms {
	// fields and classes here. 
	//private int count;

	public MeetingRooms() {
		// Initialization here. 
		//this.count = 0;
	}

	// Sort the meetings by starting time. Then, go through the meetings one by one, and 
	// make sure that each meeting ends before the next one starts. 
	// Author: jiangyucara + kei
	// Date  : July 15, 2019
    public boolean canAttendMeetings(int[][] intervals) {
    	// corner: len:0, 1 => ok. 
    	
    	Arrays.sort(intervals, new Comparator<int[]>() {
			@Override
			public int compare(int[] o1, int[] o2) {
				return o1[0] - o2[0];
			}
    	});

    	for (int i = 0; i + 1 < intervals.length; i++) {
			if (intervals[i][1] > intervals[i + 1][0]) {
				return false;
			}
		}
    	
		return true;
	}



	
	
	

	// For testing. 
	public static void main(String[] args) {
		MeetingRooms solution = new MeetingRooms();

		// Test arguments. 
		// int num = 24;
		// int target = 2;
		// solution.getInt(num, target);



	}

}















