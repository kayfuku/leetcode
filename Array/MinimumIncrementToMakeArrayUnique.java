//
// Author:
// Date : March 12, 2020

package leetcode;

public class MinimumIncrementToMakeArrayUnique {
  // fields and classes here.
  // private int count;

  public MinimumIncrementToMakeArrayUnique() {
    // Initialization here.
    // this.count = 0;

  }


  // Count the elements and if it's more than one, then move it to 
  // the empty space in count array. 
  // O(N) time, O(N) space
  // Author: @awice + kei
  // Date : March 12, 2020
  public int minIncrementForUnique(int[] A) {
    // The reason for 80000 is because of the case where
    // an input of 40000 elements and every element is 39999.
    // In this case, count array space needs up to 80000.
    int[] count = new int[80000];
    // Count the number of each element.
    for (int x : A) {
      count[x]++;
    }

    // ans: distance to move, taken: num of elems to move
    int ans = 0, taken = 0;

    // Iterate the count array from the min value.
    for (int x = 0; x < count.length; x++) {
      // Check duplicates and empty space.
      if (count[x] >= 2) {
        // We've got duplicates. Leave one and take the rest.
        taken += count[x] - 1;
        // Subtract the total distances in advance, which will be added later.
        // This works because we can only increment to move according to the 
        // problem statement. 
        ans -= x * (count[x] - 1);
      } else if (taken > 0 && count[x] == 0) {
        // There is an empty space here at x.
        // Assign one of the taken to it and add the distance.
        taken--;
        ans += x;
      }
    }

    return ans;
  }



  // For testing.
  @SuppressWarnings("unused")
  public static void main(String[] args) {
    MinimumIncrementToMakeArrayUnique solution = new MinimumIncrementToMakeArrayUnique();

    // Test arguments.
    // int num = 24;
    // int target = 2;
    // solution.getInt(num, target);



  }

}


