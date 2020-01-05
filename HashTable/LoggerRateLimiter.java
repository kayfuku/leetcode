// 
// Author: 
// Date  : January 3, 2020

package leetcode;

import java.util.HashMap;

public class LoggerRateLimiter {
	// fields and classes here. 
	//private int count;

	private HashMap<String, Integer> msgDict;

	/** Initialize your data structure here. */
	public LoggerRateLimiter() {
		msgDict = new HashMap<>();
	}

	/**
	 * Returns true if the message should be printed in the given timestamp, otherwise returns false.
	 */
	// Author: LeetCode + kei
	// Date  : January 3, 2020
	public boolean shouldPrintMessage(int timestamp, String message) {

		if (!msgDict.containsKey(message)) {
			msgDict.put(message, timestamp);
			return true;
		}

		// Check the last time when we saw it. 
		Integer oldTimestamp = msgDict.get(message);
		if (timestamp - oldTimestamp >= 10) {
			// Update the timestamp. 
			msgDict.put(message, timestamp);
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * Your Logger object will be instantiated and called as such:
	 * Logger obj = new Logger();
	 * boolean param_1 = obj.shouldPrintMessage(timestamp, message);
	 */


	// For testing. 
	public static void main(String[] args) {
		LoggerRateLimiter solution = new LoggerRateLimiter();

		// Test arguments. 
		// int num = 24;
		// int target = 2;
		// solution.getInt(num, target);



	}

}















