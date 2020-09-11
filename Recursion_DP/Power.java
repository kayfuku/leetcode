// 
// Author: 
// Date  : July 9, 2019

package leetcode;

public class Power {
	// fields here. 
	//private int count;

	public Power() {
		// Initialization here. 
		//this.count = 0;
	}

	// 1. Brute Force. Time Limit Exceeded. 
	// O(N) time, O(1) space. 
    public double myPow(double x, int n) {
    	// Note that the x and return value type are double. 
    	
    	// The type is long because it causes integer overflow 
    	// when Integer.MIN_MAX * (-1). 
    	long N = n;
    	if (N < 0) {
			N = -N;
			x = 1 / x;
		}
    	
    	// Type double is capable of representing numbers 
    	// much wider range and more accurate than type long. 
    	double ans = 1;
    	for (int i = 0; i < N; i++) {
			ans *= x;
		}
    	
		return ans;
	}
    
    
    // 2. Fast Power Algorithm Recursive. Accepted. This is good enough. 
    // Recurrence Formula: 
    // f(n) = f(n/2) * f(n/2)      if n is even. 
    //      = f(n/2) * f(n/2) * x  if n is odd.
    //      = 1                    if n = 0. 
    // O(logN) time because each time we apply the formula, N is reduced by half. 
    // O(logN) space, for each recursion, we need O(1) space, and we need O(logN) space 
    // for recursion stack. 
    public double myPow2(double x, int n) {
    	long N = n;
    	if (N < 0) {
			N = -N;
			x = 1 / x;
		}
    	// NG!
    	// -n will still be -2147483648 because type int 
    	// cannot represent 2147483648. 
    	// In this case, put it in type long first, and then 
    	// process it. 
//    	if (n < 0) {
//			N = -n;
//			x = 1 / x;
//		}
    	
    	return fastPow(x, N);
    }
    private double fastPow(double x, long n) {
    	if (n == 0) {
			return 1.0;
		}
    	
    	double half = fastPow(x, n / 2);
    	
    	if (n % 2 == 0) {
			return half * half;
		} else {
			return half * half * x;
		}
	}
    
    
    // 3. Fast Power Algorithm Iterative. (Approach 2 is good enough.)
    // https://leetcode.com/problems/powx-n/solution/
    // 
    public double myPow3(double x, int n) {
    	long N = n;
    	if (N < 0) {
			N = -N;
			x = 1 / x;
		}
    	
    	double ans = 1;
    	double curProd = x;
        for (long i = N; i > 0; i /= 2) {
            if ((i % 2) == 1) {
                ans *= curProd;
            }
            curProd *= curProd;
        }
        
        return ans;
    }

    	
    

	

	// For testing. 
	public static void main(String[] args) {
		Power solution = new Power();

		// Test arguments. 
		// int num = 24;
		// int target = 2;
		// solution.getInt(num, target);

		double x = 2.0;
		int n = 5;
		System.out.println(solution.myPow3(x, n)); // 32.0

		x = 2.0;
		n = Integer.MIN_VALUE;
		System.out.println(solution.myPow3(x, n));

	}

}















