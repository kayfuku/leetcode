// 
// Author: 
// Date  : July 8, 2019

package leetcode;

public class CapacityToShipPackagesWithinDDays {
	// fields here. 
	//private int count;

	public CapacityToShipPackagesWithinDDays() {
		// Initialization here. 
		//this.count = 0;
	}

	// Binary Search. 
	// https://leetcode.com/problems/capacity-to-ship-packages-within-d-days/discuss/256729/JavaC%2B%2BPython-Binary-Search
	//
	// Taking capacity (equivalent to index for normal Binary Search) as the horizontal axis and 
	// days needed as the vertical axis, if capacity increases, then days needed decreases. 
	// Therefore capacity and days needed form a monotonically non-increasing curve. 
	// The reason of non-increasing is because even if capacity increases a little, days needed might not change. 
	// I can perform Binary Search on that curve. 
	// What I need first is the min capacity and max capacity. 
	// 
	// Author: lee215 + kei (Accepted)
	// Date  : July 8, 2019
    public int shipWithinDays(int[] weights, int D) {
    	// Find min and max capacity. 
    	// left is min, and right is max. 
    	int left = 0, right = 0;
    	for (int w : weights) {
			left = Math.max(left, w);
			right += w;    		
		}
//    	right++; // You can use this if you want. 
    	
    	// Binary Search. R = M version. The reason is below. 
    	while (left < right) {
			int mid = (left + right) / 2;
			
			// Monotonically non-increasing curve. 
			int needD = getDaysFromCap(mid, weights);
    		
			// I cannot stop Binary Search even when needD is equal to D  
			// because I have to find the least weight capacity (leftmost index). 
			// Because of that, I use R = M version. 
    		if (needD <= D) {
    			// Search on the left subarray. Need to use smaller capacity 
    			// so that we can find longer days needed or keep searching 
    			// minimum capacity if needD is equal to D.  
				right = mid;
			} else {
				// Search on the right subarray. Need to use larger capacity 
    			// so that we can find fewer days needed. 
    			left = mid + 1;
			}
		}
    	// The 'left' that gets out of the loop matches the leftmost target index (capacity). 
		return left;
	}
    // Get the days needed using the capacity given the weights.  
    private int getDaysFromCap(int capacity, int[] weights) {
    	int needD = 1;
		int cumW = 0;
		// Try to load all the packages using that capacity. 
		for (int w : weights) {
			if (cumW + w > capacity) {
				needD++;
				cumW = 0;
			}
			cumW += w;
		}
		
		return needD;
    }


	// For testing. 
	public static void main(String[] args) {
		CapacityToShipPackagesWithinDDays solution = new CapacityToShipPackagesWithinDDays();

		// Test arguments. 
		// int num = 24;
		// int target = 2;
		// solution.getInt(num, target);
		int[] weights = new int[]{ 10 };
		int D = 1;
		System.out.println(solution.shipWithinDays(weights, D)); // 10
		weights = new int[]{ 10, 11 };
		D = 2;
		System.out.println(solution.shipWithinDays(weights, D)); // 11


	}

}















