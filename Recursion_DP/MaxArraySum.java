package hackerrank;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

import whiteboard.DP_CountWays;

public class MaxArraySum {

	
	
	// Dynamic Programming. 
	// dp stores the maximum guaranteed so far. 
	// Since I cannot choose the adjacent element, the dp_i is described like below. 
	// dp_i = max(dp_i-2 + nums_i, nums_i-1)
	// 
	// Author: kei (AC)
	// Date  : August 23, 2019
    static int maxSubsetSum(int[] arr) {
		// corner 
    	if (arr.length == 1) {
			return arr[0];
		}
    	
    	int[] dp = new int[arr.length];
    	// Well, I believe this IS correct because a negative number could be 
    	// an answer. (WA)
//    	dp[0] = arr[0]; // (not AC but...)
    	dp[0] = Math.max(arr[0], 0); // (AC)
    	dp[1] = Math.max(arr[1], dp[0]);
    	
    	// n >= 3
    	for (int i = 2; i < arr.length; i++) {
    		dp[i] = Math.max(dp[i - 2] + arr[i], dp[i - 1]);
		}
    	
		return dp[arr.length - 1];
	}


	
    private static Scanner sc;

	public static void main(String[] args) throws IOException {
		sc = new Scanner(new BufferedReader(new InputStreamReader(
				new FileInputStream("src/data/input.txt"))));
		
		int n = ni();
		int[] arr = ai(n);
		System.out.println(maxSubsetSum(arr));
		
	}
	
	private static int ni() {
		int n = sc.nextInt();
		return n;
	}
	
	private static int[] ai(int n) {
		int[] array = new int[n];
		for (int i = 0; i < n; i++) {
			array[i] = sc.nextInt();
		}
		return array;
	}
	
	
	
}

























