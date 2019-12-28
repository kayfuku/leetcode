// 
// Author: 
// Date  : May 21, 2019

package leetcode;

import java.util.ArrayList;
import java.util.List;


public class ZigZagConversion {

    // Time Complexity: O(n), where n is the string length. 
    // Space Complexity: O(n).
    public static String convert(String s, int numRows) {
    	if (numRows <= 1) {
			return s;
		}
        
        List<StringBuilder> rows = new ArrayList<StringBuilder>();
    	for (int i = 0; i < Math.min(numRows, s.length()); i++) {
    		rows.add(new StringBuilder());
		}
    	
    	int currRow = 0;
    	boolean goDown = false;
    	
        for (Character c : s.toCharArray()) {
        	rows.get(currRow).append(c);
			if (currRow == numRows - 1 || currRow == 0) {
				goDown = !goDown;
			}
        	currRow += (goDown) ? 1 : -1;
		}
        
        StringBuilder ret = new StringBuilder();
        for (StringBuilder row : rows) {
        	ret.append(row);
		}
    	
    	return ret.toString();
    }
    
    // Review 
    public String convertR(String s, int numRows) {
    	if (numRows <= 1) {
    		return s;
    	}

    	List<StringBuilder> rows = new ArrayList<>();
    	for (int i = 0; i < Math.min(s.length(), numRows); i++) {
    		rows.add(new StringBuilder());
    	}

    	int curRow = 0;
    	boolean goDown = false;
    	// foreach is better. 
    	for (int i = 0; i < s.length(); i++) {
    		rows.get(curRow).append(s.charAt(i));
    		if (curRow == numRows - 1 || curRow == 0) {
    			goDown = !goDown;
    		}
    		curRow += (goDown) ? 1 : -1;
    	}

    	StringBuilder ans = new StringBuilder();
    	for (StringBuilder sb : rows) {
    		ans.append(sb);
    	}

    	return ans.toString();
    }

    
    

	public static void main(String[] args) {
		String str = "PAHNAPLSIIGYIR";
		String retString = convert(str, 1);
	


	}

}



















