//
// Author:
// Date : June 9, 2020

package leetcode;

public class DesignBrowserHistory {
  // fields and classes here.
  // private int count;


  // Author: JOHNKRAM + kei
  // Date : June 9, 2020
  int n, c;
  String[] a = new String[5005];

  public DesignBrowserHistory(String homepage) {
    n = c = 0;
    a[0] = homepage;
  }

  void visit(String url) {
    c++;
    n = c;
    a[c] = url;
  }

  String back(int steps) {
    steps = Math.min(steps, c);
    c -= steps;
    return a[c];
  }

  String forward(int steps) {
    steps = Math.min(steps, n - c);
    c += steps;
    return a[c];
  }



  // For testing.
  @SuppressWarnings("unused")
  public static void main(String[] args) {
    DesignBrowserHistory solution = new DesignBrowserHistory("");

    // Test arguments.
    // int num = 24;
    // int target = 2;
    // solution.getInt(num, target);



  }



  public void dummyMethod() {



  }



  public void dummyMethod2() {



  }



}


