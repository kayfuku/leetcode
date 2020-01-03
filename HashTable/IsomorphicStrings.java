// 
// Author: 
// Date  : January 2, 2020

package leetcode;

public class IsomorphicStrings {
	// fields and classes here. 
	//private int count;

	public IsomorphicStrings() {
		// Initialization here. 
		//this.count = 0;

	}


	// Use a map to keep track of the information about the same character. 
	// Use index to hold order information. 
	// Author: grandyang + kei
	// Date  : January 2, 2020
	public boolean isIsomorphic(String s, String t) {
		int[] m1 = new int[256];
		int[] m2 = new int[256];
		int n = s.length();
		
		for (int i = 0; i < n; i++) {
			char c1 = s.charAt(i);
			char c2 = t.charAt(i);
			// If the previous index of the same character is the same, 
			// then structure is the same. 
			// Same character should have the same index. 
			if (m1[c1] != m2[c2]) {
				return false;
			}
			// Put the index except 0 to hold order information. 
			// Counting cannot hold the order information.
			m1[c1] = i + 1;
			m2[c2] = i + 1;
		}
		
		return true;
	}







	// For testing. 
	public static void main(String[] args) {
		IsomorphicStrings solution = new IsomorphicStrings();

		// Test arguments. 
		// int num = 24;
		// int target = 2;
		// solution.getInt(num, target);



	}

}















