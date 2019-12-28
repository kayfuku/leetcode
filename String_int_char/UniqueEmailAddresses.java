// 
// Author: 
// Date  : June 26, 2019

package leetcode;

import java.util.HashSet;
import java.util.Set;

public class UniqueEmailAddresses {
	// fields here. 
	//	private int count;

	public UniqueEmailAddresses() {
		// Initialization here. 
		//		this.count = 0;
	}

	// O(C) time, O(C) space, where C is the total number of 
	// characters in all the emails. 
	public int numUniqueEmails(String[] emails) {
		Set<String> set = new HashSet<>();
		for (String email : emails) {
			int i = email.indexOf("@");
			String local = email.substring(0, i);
			String rest = email.substring(i);
			if (local.contains("+")) {
				local = local.substring(0, local.indexOf("+"));
			}
			local = local.replaceAll("\\.", "");
			//			local.replaceAll("\\.", ""); // NG!
			set.add(local + rest);
		}

		return set.size();
	}


	// Review. 
	// O(C) time, where C is the total number of characters in all the emails. 
	public int numUniqueEmailsR(String[] emails) {
		// corner
		if (emails == null || emails.length == 0) {
			return 0;
		}
		if (emails.length == 1) {
			return 1;
		}

		Set<String> set = new HashSet<>();

		for (String s : emails) {
			int at = s.indexOf('@');
			String local = s.substring(0, at);
			String domain = s.substring(at);

			// Remove '+' first, then replace '.', which is faster. 
			int plus = local.indexOf('+');
			if (plus != -1) {
				local = local.substring(0, plus);
			}
			// Double backslashes, not single. 
			String newLocal = local.replaceAll("\\.", "");

			set.add(newLocal + domain);
		}

		return set.size();
	}



	// other classes here. 


	// For testing. 
	public static void main(String[] args) {
		UniqueEmailAddresses solution = new UniqueEmailAddresses();

		// Test arguments. 
		//	    int num = 24;
		//	    int target = 2;
		//	    solution.getInt(num, target);



	}

}















