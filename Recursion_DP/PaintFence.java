// 
// Author: 
// Date  : July 4, 2019

package leetcode;

public class PaintFence {
	// fields here. 
//	private int count;
	
	public PaintFence() {
		// Initialization here. 
//		this.count = 0;
	}
		
	// Dynamic Programming. Accepted. 
	// Counting ways. 
	// We divided it into two cases.
	// 1. the last two posts have the same color, the number of ways to paint in this case is sameColorCounts.
	// 2. the last two posts have different colors, and the number of ways in this case is diffColorCounts.
	// 
	// https://leetcode.com/problems/paint-fence/discuss/71156/O(n)-time-java-solution-O(1)-space
	// O(N) time. O(1) space. 
    public int numWays(int n, int k) {
    	if (n == 0) {
			return 0;
		}
    	if (n == 1) {
			return k;
		}
    	// n == 2
    	int sameCnt = k;
    	int diffCnt = k * (k - 1);
    	// Induction step.
    	for (int i = 3; i <= n; i++) {
			int temp = diffCnt;
			diffCnt = sameCnt * (k - 1) + diffCnt * (k - 1);
    		sameCnt = temp;    		
		}
    	
    	return sameCnt + diffCnt;
    }
	
	

    
    // For testing. 
	public static void main(String[] args) {
	    PaintFence solution = new PaintFence();
	    
	    // Test arguments. 
	    // int num = 24;
	    // int target = 2;
	    // solution.getInt(num, target);
	    
	    
	    
	}

}















