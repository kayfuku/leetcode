// 
// Author: 
// Date  : May 22, 2019

package leetcode;


public class ContainerWithMostWater {

	// O(N) time, O(1) space. 
    public static int maxArea(int[] height) {
    	int left = 0, right = height.length - 1;
    	int maxArea = 0;
    	while (left < right) {
			maxArea = Math.max(maxArea, Math.min(height[left], height[right]) * (right - left));
			if (height[left] < height[right]) {
				left++;
			} else {
				right--;
			}
		}
    	
    	return maxArea;
    }

	public static void main(String[] args) {
	


	}

}



















