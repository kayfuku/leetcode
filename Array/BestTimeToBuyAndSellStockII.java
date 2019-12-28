// 
// Author: 
// Date  : June 4, 2019

package leetcode;

public class BestTimeToBuyAndSellStockII {
	// fields here. 
	//	private int count;

	public BestTimeToBuyAndSellStockII() {
		// Initialization here. 
		//		this.count = 0;
	}

	// other classes here. 


	// 1. Not good. Move on to the approach 2. 
	// O(N) time, O(1) space. 
	public int maxProfit(int[] prices) {
		if (prices == null || prices.length == 0) {
			return 0;
		}
		int valley = prices[0];
		int peak = prices[0];
		int i = 0;
		int maxProfit = 0;
		while (i + 1 < prices.length) {
			// To find valley. 
			while (i + 1 < prices.length && prices[i] >= prices[i + 1]) {
				i++;
			}
			valley = prices[i];
			// To find peak. 
			while (i + 1 < prices.length && prices[i] <= prices[i + 1]) {
				i++;
			}
			peak = prices[i];
			maxProfit += peak - valley;
		}

		return maxProfit;
	}

	// 2. Better solution. 
	// Just add up the profit every time we make. 
	// O(N) time, O(1) space. 
	public int maxProfit2(int[] prices) {
		if (prices == null || prices.length == 0) {
			return 0;
		}
		int maxProfit = 0;
		for (int i = 1; i < prices.length; i++) {
			// If the price get higher than previous, then 
			// add the difference as a profit. 
			if (prices[i] > prices[i - 1]) {
				maxProfit += prices[i] - prices[i - 1];
			}
		}

		return maxProfit;
	}
	
	
	// Review. 
	public int maxProfitR2(int[] prices) {
		// corner 
		if (prices == null || prices.length < 2) {
			return 0;
		}

		int profit = 0;
		for (int i = 1; i < prices.length; i++) {
			if (prices[i] > prices[i - 1]) {
				profit += prices[i] - prices[i - 1];			
			}
		}

		return profit;	
	}




	// For testing. 
	public static void main(String[] args) {
		BestTimeToBuyAndSellStockII solution = new BestTimeToBuyAndSellStockII();

		// Test arguments. 
		//	    int num = 24;
		//	    int target = 2;
		//	    solution.getInt(num, target);



	}

}















