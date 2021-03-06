// 
// Author: 
// Date  : July 8, 2019

package leetcode;

public class CapacityToShipPackagesWithinDDays {
	// fields here.
	// private int count;

	public CapacityToShipPackagesWithinDDays() {
		// Initialization here.
		// this.count = 0;
	}

	// Binary Search.
	// https://leetcode.com/problems/capacity-to-ship-packages-within-d-days/discuss/256729/JavaC%2B%2BPython-Binary-Search
	//
	// Taking capacity (equivalent to index for normal Binary Search) as the
	// horizontal axis and days needed as the vertical axis, if capacity increases,
	// then days needed decreases.
	// Therefore, capacity and days needed form a monotonically non-increasing
	// curve. The reason of non-increasing is because even if capacity increases a
	// little, days needed might not change.
	// I can perform Binary Search on that curve.
	// What I need first is the min capacity and max capacity.
	//
	// Author: lee215 + kei (AC)
	// Date : July 8, 2019, October 9, 2020
	public int shipWithinDays(int[] weights, int D) {
		// Find min and max capacity.
		// Since we cannot break one package into pieces, min weight is the min
		// capacity. left is min, and right is max.
		int left = 0, right = 0;
		for (int w : weights) {
			left = Math.max(left, w);
			right += w;
		}

		// R=M-1, leftmost version, but monotonically non-increasing version.
		while (left <= right) {
			// No overflow according to the restrictions.
			int mid = (left + right) / 2;

			// Monotonically non-increasing curve.
			int daysNeeded = getDaysFromCap(mid, weights);

			// I cannot stop Binary Search even when daysNeeded is equal to D
			// because I have to find the least weight capacity (leftmost index).
			// Because of that, I use R = M version.
			if (daysNeeded <= D) {
				// Search on the left subarray. Need to use smaller capacity
				// so that we can find longer days needed or keep searching
				// minimum capacity if daysNeeded is equal to D.
				right = mid - 1;
			} else {
				// Search on the right subarray. Need to use larger capacity
				// so that we can find fewer days needed.
				left = mid + 1;
			}
		}
		// The 'left' that gets out of the loop matches the leftmost target index
		// (capacity).
		return left;
	}

	// Monotonically non-increasing function.
	// Get the days needed using the capacity given the weights.
	private int getDaysFromCap(int capacity, int[] weights) {
		int daysNeeded = 1;
		int cumW = 0;
		// Try to load all the packages using that capacity.
		for (int w : weights) {
			cumW += w;
			if (cumW > capacity) {
				daysNeeded++;
				cumW = w;
			}
		}

		return daysNeeded;
	}

	// For testing.
	public static void main(String[] args) {
		CapacityToShipPackagesWithinDDays solution = new CapacityToShipPackagesWithinDDays();

		// Test arguments.
		// int num = 24;
		// int target = 2;
		// solution.getInt(num, target);
		int[] weights = new int[] { 10 };
		int D = 1;
		System.out.println(solution.shipWithinDays(weights, D)); // 10
		weights = new int[] { 10, 11 };
		D = 2;
		System.out.println(solution.shipWithinDays(weights, D)); // 11

	}

}
