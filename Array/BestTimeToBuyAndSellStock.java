// 
// Author: 
// Date  : June 4, 2019

package leetcode;

public class BestTimeToBuyAndSellStock {
	// fields here. 
	//	private int count;

	public BestTimeToBuyAndSellStock() {
		// Initialization here. 
		//		this.count = 0;
	}


	// 7 1 3 2 5
	// 7 6 4
	// 2 4 1

	// Unlike the real stock transaction, you need to find the min price 
	// through the array and keep track of max profit using the current min.
	// Note that you do not need to keep track of max price. 
	// O(N) time, O(1) space. 
	public int maxProfit(int[] prices) {
		int min = Integer.MAX_VALUE;
		int maxProfit = 0;
		for (int i = 0; i < prices.length; i++) {
			if (prices[i] < min) {
				// Buy the stock. 
				min = prices[i];
			} else {
				if (prices[i] - min > maxProfit) {
					maxProfit = prices[i] - min;
				}
			}
		}

		return maxProfit;
	}


	// Review
	public int maxProfitR(int[] prices) {
		int min = Integer.MAX_VALUE;
		int maxProfit = 0;
		for (int i = 0; i < prices.length; i++) {
			if (prices[i] < min) {
				min = prices[i];
			} else {
				if (prices[i] - min > maxProfit) {
					maxProfit = prices[i] - min;
				}
			}
		}

		return maxProfit;	
	}


	// Review 2 (AC)
	// Author: kei
	// Date  : August 23, 2019 
	public int maxProfitR2(int[] prices) {
		// corner 
		if (prices == null || prices.length < 2) {
			return 0;
		}

		int min = prices[0];
		int maxProfit = 0;
		for (int i = 1; i < prices.length; i++) {
			min = Math.min(min, prices[i]);
			int profit = 0;
			if (prices[i] > min) {
				profit = prices[i] - min;
				maxProfit = Math.max(maxProfit, profit);
			}
		}

		return maxProfit; 
	}




	// NG! 
	//    public int maxProfit(int[] prices) {
	//    	// TODO: corner
	//    	if (prices == null || prices.length <= 1) {
	//			return 0;
	//		}
	//    	
	//    	int min = Integer.MAX_VALUE;
	//    	int max = Integer.MIN_VALUE;
	//    	int buy, sell;
	//    	for (int i = 0; i < prices.length; i++) {
	//    		if (prices[i] < min) {
	//				min = prices[i];
	//				max = Integer.MIN_VALUE;
	//			}
	//			if (prices[i] > max && prices[i] > min) {
	//				max = Math.max(max, prices[i]);
	//			}
	//		}
	//    	if (max == Integer.MIN_VALUE) {
	//			return 0;
	//		}
	//    	
	//    	return max - min;
	//    }


	// For testing. 
	public static void main(String[] args) {
		BestTimeToBuyAndSellStock solution = new BestTimeToBuyAndSellStock();

		// Test arguments. 
		//	    int num = 24;
		//	    int target = 2;
		//	    solution.getInt(num, target);



	}

}















