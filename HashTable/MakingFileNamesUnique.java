//
// Author:
// Date : June 20, 2020

package leetcode;

import java.util.HashMap;
import java.util.Map;

public class MakingFileNamesUnique {
  // fields and classes here.
  // private int count;

  public MakingFileNamesUnique() {
    // Initialization here.
    // this.count = 0;

  }


  // Author: kei (AC)
  // Date : June 20, 2020
  public String[] getFolderNames(String[] names) {
    Map<String, Integer> map = new HashMap<String, Integer>();
    String[] ans = new String[names.length];

    for (int i = 0; i < names.length; i++) {
      String str = names[i];
      String stem = str;
      while (map.containsKey(str)) {
        int num = map.get(stem) + 1;
        map.put(stem, num);
        String suffix = '(' + String.valueOf(num) + ')';
        str = stem + suffix;
      }
      map.put(str, 0);
      ans[i] = str;
    }

    return ans;
  }



  // For testing.
  @SuppressWarnings("unused")
  public static void main(String[] args) {
    MakingFileNamesUnique solution = new MakingFileNamesUnique();

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


