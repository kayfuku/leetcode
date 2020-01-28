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
	// The count can be determined based only on the last count. => Bottom up dynamic programming
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
    	// (n == 2)
    	// The number of the cases where the first color and the second color are the same. 
    	int sameColorCnt = k;
    	// The number of the cases where the first color and the second color are different. 
    	int diffColorCnt = k * (k - 1);
    	// (n >= 3) Induction step. 
    	for (int i = 3; i <= n; i++) {
			int temp = diffColorCnt;
			// In the case where the last two colors are the same, there are k - 1 patterns for the 
			// current colors such that the last color and the current color are different. 
			// In the case where the last two colors are different, there are k - 1 patterns for the 
			// current colors such that the last color and the current color are different. 
			diffColorCnt = sameColorCnt * (k - 1) + diffColorCnt * (k - 1);
			// In the case where the last two colors are the same, there is no pattern for the 
			// current colors such that the last color and the current color are the same. 
			// In the case where the last two colors are different, there is 1 color that is the same 
			// as the last color such that the last color and the current color are the same. 
			sameColorCnt = temp;    		
		}
    	
    	return sameColorCnt + diffColorCnt;
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















