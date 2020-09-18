//
// Author:
// Date  : September 16, 2020

package leetcode;

public class QuickSort {
  // fields and classes here.
  // private int count;

  public QuickSort() {
    // Initialization here.
    // this.count = 0;

  }


  // Quicksort
  // Author: leetcode + kei
  // Date  : September 16, 2020
  public int[] sortArray(int[] lst) {
    // Sort an array in the ascending order in O(NlogN) time. 
    int n = lst.length;
    qSort(lst, 0, n - 1);
    return lst;
  }
 
  private void qSort(int[] lst, int left, int right) {
    if (left >= right) {
      return;
    }

    // 'p' is the index of pivot. 
    int p = partition(lst, left, right);
    qSort(lst, left, p - 1);
    qSort(lst, p + 1, right);
  }
 
  private int partition(int[] lst, int left, int right) {
    // Pick the last element 'right' as a pivot. 
    // and return the index of pivot value in the sorted array. 
    int pivot = lst[right];
    int i = left;
    // i points to the first element that is bigger than pivot. 
    for (int j = left; j < right; j++) {
      if (lst[j] < pivot) {
        int tmp = lst[i];
        lst[i] = lst[j];
        lst[j] = tmp;
        i++;
      }
    }
    // Swap the lst[i] with pivot. 
    int tmp = lst[i];
    lst[i] = lst[right];
    lst[right] = tmp;

    return i;
  }


  // For testing.
  @SuppressWarnings("unused")
  public static void main(String[] args) {
    QuickSort solution = new QuickSort();

    // Test arguments.
    // int num = 24;
    // int target = 2;
    // solution.getInt(num, target);



  }




}


