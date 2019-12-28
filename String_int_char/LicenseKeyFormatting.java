// 
// Author: 
// Date  : July 30, 2019

package leetcode;

public class LicenseKeyFormatting {
	// fields and classes here. 
	//private int count;

	public LicenseKeyFormatting() {
		// Initialization here. 
		//this.count = 0;
	}

	
	// O(N) time, O(1) space. 
	// Author: kei (Accepted)
	// Date  : July 30, 2019
    public String licenseKeyFormatting(String S, int K) {
    	S = S.toUpperCase();
    	S = S.replace("-", "");
    	int n = S.length();
    	StringBuilder sb = new StringBuilder();
    	
    	// % => firstNum: 0 ok?
    	int firstNum = n % K;
    	sb.append(S.substring(0, firstNum));
    	for (int i = firstNum; i + K <= n; i += K) {
    		// ok
//    		if (i > 0) {
//				sb.append("-");
//			}
    		sb.append((i == 0) ? "" : "-"); // ok
			sb.append(S.substring(i, i + K));
		}
    	
		return sb.toString();
	}



	
	
	

	// For testing. 
	public static void main(String[] args) {
		LicenseKeyFormatting solution = new LicenseKeyFormatting();

		// Test arguments. 
		// int num = 24;
		// int target = 2;
		// solution.getInt(num, target);
		String S = "2-5g-3-J";
		int K = 2;
		System.out.println(solution.licenseKeyFormatting(S, K));



	}

}















