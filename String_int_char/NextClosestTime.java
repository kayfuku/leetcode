// 
// Author: 
// Date  : July 30, 2019

package leetcode;

import java.util.HashSet;
import java.util.Set;

public class NextClosestTime {
	// fields and classes here. 
	//private int count;

	public NextClosestTime() {
		// Initialization here. 
		//this.count = 0;
	}


	// 1. Simulation. 
	// O(1) time, because we try up to 24 * 60 times;
	// O(1) space. 
	public String nextClosestTime(String time) {
		// Convert the input to the amount of minutes, which is 
		// the smallest unit to go. 
		// Integer.parseInt() allows you to convert String "15" to int 15. 
		int minutes = Integer.parseInt(time.substring(0, 2)) * 60;
		minutes += Integer.parseInt(time.substring(3));

		// Store the allowed digits. 
		Set<Integer> set = new HashSet<>();
		for (char c : time.toCharArray()) {
			if (c != ':') {
				set.add(c - '0');
			}
		}

		// Simulate the clock. 
		while (true) {
			// minutes is reset after 24 hours (24 * 60 minutes). 
			minutes = (minutes + 1) % (24 * 60);
			int[] digits = new int[]{ minutes / 60 / 10, minutes / 60 % 10, // hour
					                  minutes % 60 / 10, minutes % 60 % 10 }; // minute 
			if (checkValidity(digits, set)) {
				// '0' is needed in '02d' to add leading zeroes if the number of digits is not enough. 
				return String.format("%02d:%02d", minutes / 60, minutes % 60);
			}
			
			// No need to use label, which is not readable. 
//search : {
//			for (int d : digits) {
//				if (!set.contains(d)) {
//					break search;
//				}
//			}
//			return String.format("%02d:%02d", minutes / 60, minutes % 60);
//
//} // search

		} // while
	}

	private boolean checkValidity(int[] digits, Set<Integer> set) {
		for (int d : digits) {
			if (!set.contains(d)) {
				return false;
			}
		}		
		return true;
	}
	
	
	// 2. ??
	// 
	public String nextClosestTime2(String time) {
		
		
		return "";
	}

	
	
	
	
	


	// For testing. 
	public static void main(String[] args) {
		NextClosestTime solution = new NextClosestTime();

		// Test arguments. 
		// int num = 24;
		// int target = 2;
		// solution.getInt(num, target);



	}

}















