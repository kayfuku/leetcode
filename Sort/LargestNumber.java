// 
// Author: 
// Date  : June 13, 2019

package leetcode;

import java.util.Arrays;
import java.util.Comparator;

public class LargestNumber {
	// fields here. 
//	private int count;
	
	public LargestNumber() {
		// Initialization here. 
//		this.count = 0;
	}
	
	
	// O(NlogN) time, O(N) space, where N is the total number of elements in the input array. 
	// Custom Comparator takes constant time. Overall runtime is dominated by sorting, which is O(NlogN). 
	// I use additional O(N) space for strs. 
    public String largestNumber(int[] nums) {
    	// Convert int[] to String[]. 
    	String[] strs = new String[nums.length];
    	for (int i = 0; i < strs.length; i++) {
    		strs[i] = String.valueOf(nums[i]); 
		}
		
    	// Sort in custom order. 
    	Arrays.sort(strs, new LargeNumberComparator());
    	
    	if (strs[0].equals("0")) {
    		return "0";
		}
    	
    	StringBuilder ans = new StringBuilder();
    	for (String string : strs) {
			ans.append(string);
		}
    	
    	return ans.toString();
    }
    
    // Author: LeetCode + kei. https://leetcode.com/problems/largest-number/solution/
 	// Date  : June 13, 2019
 	private class LargeNumberComparator implements Comparator<String> {
 		@Override
 		public int compare(String o1, String o2) {
 			String numStr1 = o1 + o2; // 330
 			String numStr2 = o2 + o1; // 303
 			// Reverse order because I want larger one come first. 
 			// Arrange o1 and o2 such that o1 comes first if numStr1 is larger than numStr2. 
 			return numStr2.compareTo(numStr1);
 		}
 	}
 	
 	private class StringComparator implements Comparator<String> {
 		@Override
 		public int compare(String o1, String o2) {
 			// Natural order.
 			// Arrange o1 and o2 such that o1 comes first if o1 is smaller than o2. 
 			return o1.compareTo(o2);
 		}
 	}
 	
 	private class StringComparatorReverse implements Comparator<String> {
 		@Override
 		public int compare(String o1, String o2) {
 			// Reverse order.
 			// Arrange o1 and o2 such that o1 comes first if o1 is larger than o2. 
 			return o2.compareTo(o1);
 		}
 	}



    
    // For testing. 
	public static void main(String[] args) {
	    LargestNumber solution = new LargestNumber();
	    
	    // Test arguments. 
//	    int num = 24;
//	    int target = 2;
//	    solution.getInt(num, target);
	    
	    String[] strs = new String[]{ "3", "1", "2" };
	    Arrays.sort(strs, solution.new StringComparator());
	    System.out.println(Arrays.toString(strs)); // [1, 2, 3] 
	    
	    strs = new String[]{ "3", "1", "2" };
	    Arrays.sort(strs, solution.new StringComparatorReverse());
	    System.out.println(Arrays.toString(strs)); // [3, 2, 1] 

	    strs = new String[]{ "3", "30", "34", "5", "9" };
	    Arrays.sort(strs, solution.new StringComparator());
	    System.out.println(Arrays.toString(strs)); // [3, 30, 34, 5, 9] 
	    Arrays.sort(strs, solution.new StringComparatorReverse());
	    System.out.println(Arrays.toString(strs)); // [9, 5, 34, 30, 3] 
	    Arrays.sort(strs, solution.new LargeNumberComparator());
	    System.out.println(Arrays.toString(strs)); // [9, 5, 34, 3, 30]  

	    
	    
	    
	    
	    
	}

}















