// 
// Author: 
// Date  : July 5, 2019

package leetcode;

import java.util.Arrays;

public class CoinChange {
	// fields here. 
	//private int count;

	public CoinChange() {
		// Initialization here. 
		//this.count = 0;
	}

	// https://leetcode.com/problems/coin-change/solution/

	// 1. Recursion (Move on to the DP solution!)
	// O(A^n) time, where A is the amount. 
	// Author: @elmirap + kei (TLE)
	// Date  : August 24, 2019
	public int coinChange(int[] coins, int amount) {
		return coinChange(0, coins, amount);
	}
	private int coinChange(int idxCoin, int[] coins, int amount) {
		if (amount == 0) {
			return 0;
		}

		if (idxCoin < coins.length && amount > 0) {
			int maxVal = amount / coins[idxCoin];
			int minCost = Integer.MAX_VALUE;

			// x number of coin[idxCoin] are used. 
			for (int x = 0; x <= maxVal; x++) {
				if (amount >= x * coins[idxCoin]) {
					int res = coinChange(idxCoin + 1, coins, amount - x * coins[idxCoin]);
					if (res != -1) {
						minCost = Math.min(minCost, res + x);
					}
				}                    
			}	 	

			return (minCost == Integer.MAX_VALUE) ? -1 : minCost;
		}	

		return -1;
	}  


	// 3. Dynamic Programming (Bottom Up)
	// I'm going to find the smallest number of coins required for amount 'a'. 
	// When I pick up any coin d_i, the remaining amount is 'a' - d_i. 
	// Then I have a smaller subproblem, and then think about the remaining amount 
	// after using the first coin.
	// 
	// Let dp[a] store the smallest number of coins required for amount 'a', then 
	// the dp[a] looks like the following. 
	// 
	// Base case     : dp[0] = 0 
	// Recursive case: dp[a] = min_i(dp[a - d_i] + 1)
	// 
	// O(Amount * numCoins) time. O(Amount) space. 
	// 
	// Author: @elmirap + kei (AC)
	// Date  : August 24, 2019
	public int coinChange3(int[] coins, int amount) {
		if (coins == null || coins.length == 0) {
			return 0;
		}
		
		// To find min, fill the dp with max. 
		// dp[a] stores the smallest number of coins required for amount 'a'. 
		int max = amount + 1;             
		int[] dp = new int[amount + 1];  
		Arrays.fill(dp, max);  
		
		dp[0] = 0;
		for (int a = 1; a <= amount; a++) {
			// Find the smallest number of coins required for amount 'a'. 
			for (int i = 0; i < coins.length; i++) {
				// If coins[i] > a, no need to consider that coin. 
				if (coins[i] <= a) {
					// Check the number of coins when I use coins[i], and find min. 
					dp[a] = Math.min(dp[a], dp[a - coins[i]] + 1);
				}
			}
		}
		
		return dp[amount] > amount ? -1 : dp[amount];
	}







	// For testing. 
	public static void main(String[] args) {
		CoinChange solution = new CoinChange();

		// Test arguments. 
		// int num = 24;
		// int target = 2;
		// solution.getInt(num, target);
		int[] coins = new int[]{ 1, 2, 5 };
		int amount = 11;
		System.out.println(solution.coinChange3(coins, amount)); // 3

		coins = new int[]{ 1, 4, 6 };
		amount = 8;
		System.out.println(solution.coinChange3(coins, amount)); // 2
		
		coins = new int[]{ 2 };
		amount = 3;
		System.out.println(solution.coinChange3(coins, amount)); // -1


	}

}















