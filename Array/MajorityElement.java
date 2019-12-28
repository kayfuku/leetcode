// 
// Author: 
// Date  : June 12, 2019

package leetcode;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class MajorityElement {
	// fields here. 
//	private int count;
	
	public MajorityElement() {
		// Initialization here. 
//		this.count = 0;
	}
	
	// other classes here. 
	
	// 1. Hash Table. 
	// O(N) time, O(N) space, where N is the total number of elements in the array. 
    public int majorityElement(int[] nums) {
    	Map<Integer, Integer> map =  new HashMap<>();
    	int majorityCount = nums.length / 2;
    	for (int num : nums) {
			if (!map.containsKey(num)) {
				map.put(num, 1);
			} else {
				map.put(num, map.get(num) + 1);
			}
    		
			if (map.get(num) > majorityCount) {
				return num;
			}	
		}
    	
    	return -1;
    }
    
    // 2. Sorting. 
    // https://leetcode.com/problems/majority-element/solution/
    // O(NlogN) time, O(1) space. 
    public int majorityElement2(int[] nums) {
    	Arrays.sort(nums);
    	return nums[nums.length / 2];
    }

    
    
    // For testing. 
	public static void main(String[] args) {
	    MajorityElement solution = new MajorityElement();
	    
	    // Test arguments. 
//	    int num = 24;
//	    int target = 2;
//	    solution.getInt(num, target);
	    
	    
	    
	}

}















