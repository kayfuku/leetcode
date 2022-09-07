// 
// Author: 
// Date  : June 26, 2019

package leetcode;

import java.util.HashMap;
import java.util.Map;

public class SubarraySumEqualsK {
	// fields here.
	// private int count;

	public SubarraySumEqualsK() {
		// Initialization here.
		// this.count = 0;
	}

	// 1. Brute Force.
	// Consider every possible subarrays from i to j.
	// O(N^3) time, where N is the array length, because considering every possible
	// subarray takes O(N^2) time, and for each of the subarray, calculating the sum
	// takes O(N) time.
	// O(1) space.

	// 2. Cumulative Sum. (No need to review, move on to the Approach 3)
	// O(N^2) time.
	// O(N) space.
	public int subarraySum2(int[] nums, int k) {
		int count = 0;
		int[] cumuSum = new int[nums.length + 1];
		cumuSum[0] = 0;
		for (int i = 1; i <= nums.length; i++) {
			cumuSum[i] = cumuSum[i - 1] + nums[i - 1];
		}

		for (int start = 0; start < cumuSum.length; start++) {
			for (int end = start + 1; end < cumuSum.length; end++) {
				if (cumuSum[end] - cumuSum[start] == k) {
					count++;
				}
			}
		}

		return count;
	}

	// 3. Cumulative Sum without cumulative sum array.
	// O(N^2) time.
	// O(1) space.
	public int subarraySum3(int[] nums, int k) {
		int count = 0;
		for (int i = 0; i < nums.length; i++) {
			int cumSum = 0;
			for (int j = i; j < nums.length; j++) {
				cumSum += nums[j];
				if (cumSum == k) {
					count++;
				}
			}
		}

		return count;
	}

	// 4. Cumulative Sum using HashMap.
	// Basically,
	//
	// curCumSum - cumSumSoFar = subarraySum
	//
	// In other words, if there is a subarray whose sum is k, then
	//
	// curCumSum - k = cumSumSoFar
	//
	// While iterating through the input array, I calculate cumulative sum.
	// If I subtract k from the current cumulative sum and get the same value as
	// the cumulative sum I've seen so far, then there is a subarray whose sum is k.
	//
	// I store each cumulative sum in a map as a key and if the same cumulative sum
	// occurs, then I store the number of occurrences of the sum as a value so that
	// I can count the number of subarrays whose sum is k.
	//
	// O(N) time.
	// O(N) space.
	public int subarraySum4(int[] nums, int k) {
		int count = 0, cumSum = 0;
		// K: cumulative sum, V: occurrences of the cumulative sum
		Map<Integer, Integer> map = new HashMap<>();
		// cumulative sum 0 is needed if the subarray starts from the beginning.
		map.put(0, 1);
		for (int i = 0; i < nums.length; i++) {
			cumSum += nums[i];
			if (map.containsKey(cumSum - k)) {
				count += map.get(cumSum - k);
			}
			map.put(cumSum, map.getOrDefault(cumSum, 0) + 1);
		}

		return count;
	}

	// Review
	// nums:2 3 -1, k:2
	// csum:2 5 4,
	// c-k: 0 3 2,
	// map: 0:1, 2:1, 5:1, 4:1,
	public int subarraySumR(int[] nums, int k) {
		if (nums == null) {
			return 0;
		}

		int count = 0, sum = 0;
		Map<Integer, Integer> map = new HashMap<>();

		map.put(0, 1);
		for (int n : nums) {
			sum += n;
			if (map.containsKey(sum - k)) {
				count += map.get(sum - k);
			}
			map.put(sum, map.getOrDefault(sum, 0) + 1);
		}

		return count;
	}

	// For testing.
	@SuppressWarnings("unused")
	public static void main(String[] args) {
		SubarraySumEqualsK solution = new SubarraySumEqualsK();

		// Test arguments.
		// int num = 24;
		// int target = 2;
		// solution.getInt(num, target);

	}

}
