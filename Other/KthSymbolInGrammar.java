// 
// Author: 
// Date  : July 9, 2019

package leetcode;

public class KthSymbolInGrammar {
	// fields and classes here.
	// private int count;

	public KthSymbolInGrammar() {
		// Initialization here.
		// this.count = 0;
	}

	// https://leetcode.com/problems/k-th-symbol-in-grammar/solution/

	// 2. Recursion. Parent Variant.
	// The symbol depends on the parent and whether K is even or odd.
	// parent Kth
	// 0 0 (K:odd)
	// 1 (K:even)
	// 1 1 (K:odd)
	// 0 (K:even)
	// O(N) time, O(N) space.
	// Author: leetcode + kei
	// Date : September 10, 2020
	public int kthGrammar(int N, int K) {
		if (N == 1) {
			// The symbol is 0 regardless of K.
			return 0;
		}

		// We can calculate parent position.
		int parent = kthGrammar(N - 1, (K + 1) / 2);

		// Take LSB (& 1).
		// K:odd => (~K & 1): 0
		// K:even => (~K & 1): 1
		// parent ^ X => Toggle X if parent is 1.
		// return parent ^ (~K & 1);
		return (parent == 0) ? (~K & 1) : (K & 1);
	}

	// 3. Recursion. Flip Variant.
	// O(N) time, O(1) space.
	public int kthGrammar3(int N, int K) {
		if (N == 1) {
			return 0;
		}

		// K is in the first half.
		// 1 << N - 2 represents the last index of the first half.
		if (K <= 1 << N - 2) {
			return kthGrammar3(N - 1, K);
		}
		// K is in the second half.
		// ^ 1 means flip/toggle.
		// K - (1 << N - 2) means the corresponding index in the first half of the
		// parent
		// to the index in the second half of the current.
		return kthGrammar3(N - 1, K - (1 << N - 2)) ^ 1;
	}

	// 4. Binary Count. ** later
	// O(logN) time, the number of binary bits in N.
	// If O(logN) is taken to be bounded, this can be considered to be O(1).
	// O(1) space.
	public int kthGrammar4(int N, int K) {

		return Integer.bitCount(K - 1) % 2;
	}

	// For testing.
	@SuppressWarnings("unused")
	public static void main(String[] args) {
		KthSymbolInGrammar solution = new KthSymbolInGrammar();

		// Test arguments.
		// int num = 24;
		// int target = 2;
		// solution.getInt(num, target);

	}

}
