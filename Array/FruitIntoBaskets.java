// 
// Author: 
// Date  : July 30, 2019

package leetcode;

import java.util.HashMap;
import java.util.Map;

public class FruitIntoBaskets {
	// fields and classes here. 
	//private int count;

	public FruitIntoBaskets() {
		// Initialization here. 
		//this.count = 0;
	}

	
	// 2. Sliding Window. (Two Pointers)
	// i: points to the beginning of the valid subarray. 
	// j: points to the end of the valid subarray. 
	// j moves forward as long as the subarray [i, j] is valid. 
	// If it becomes invalid, then fix it. 
	// O(N) time, where N is the input array length. 
	// O(1) space because map will not go beyond 3. 
	// Author: @awice + kei
	// Date  : July 30, 2019
    public int totalFruit(int[] tree) {
    	int ans = 0, i = 0;
    	Map<Integer, Integer> map = new HashMap<>();
    	for (int j = 0; j < tree.length; j++) {
			map.put(tree[j], map.getOrDefault(tree[j], 0) + 1);
			while (map.size() >= 3) {
				// Invalid! Fix it. 
				map.put(tree[i], map.get(tree[i]) - 1);
				if (map.get(tree[i]) == 0) {
					map.remove(tree[i]);
				}
				i++;				
			}
    		
    		ans = Math.max(ans, j - i + 1);    		
		}

		return ans;
	}



	
	
	

	// For testing. 
	public static void main(String[] args) {
		FruitIntoBaskets solution = new FruitIntoBaskets();

		// Test arguments. 
		// int num = 24;
		// int target = 2;
		// solution.getInt(num, target);



	}

}















