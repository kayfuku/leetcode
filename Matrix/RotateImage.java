// 
// Author: 
// Date  : December 25, 2019

package leetcode;

public class RotateImage {
	// fields and classes here. 
	//private int count;

	public RotateImage() {
		// Initialization here. 
		//this.count = 0;

	}


	// Author: @liaison and @andvary + kei
	// Date  : December 25, 2019
	public void rotate(int[][] matrix) {
		int n = matrix.length;

		// Transpose matrix. 
		for (int i = 0; i < n; i++) {
			for (int j = i; j < n; j++) {
				int tmp = matrix[j][i];
				matrix[j][i] = matrix[i][j];
				matrix[i][j] = tmp;
			}
		}
		
		// Reverse each row.
		for (int i = 0; i < n; i++) {
			// Regardless of odd or even, j < n / 2. 
			for (int j = 0; j < n / 2; j++) {
				int tmp = matrix[i][j];
				matrix[i][j] = matrix[i][n - j - 1];
				matrix[i][n - j - 1] = tmp;
			}
		}
	}







	// For testing. 
	public static void main(String[] args) {
		RotateImage solution = new RotateImage();

		// Test arguments. 
		// int num = 24;
		// int target = 2;
		// solution.getInt(num, target);



	}

}















