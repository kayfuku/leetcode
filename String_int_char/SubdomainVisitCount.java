package leetcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SubdomainVisitCount {
  // fields and classes here.
  // private int count;

  public SubdomainVisitCount() {
    // Initialization here.
    // this.count = 0;

  }

  // Author: kei (AC)
  // Date : May 15, 2021
  public List<String> subdomainVisits(String[] cpdomains) {
    Map<String, Integer> map = new HashMap<>();
    for (String cpdomain : cpdomains) {
      String[] temp = cpdomain.split(" ");
      int count = Integer.parseInt(temp[0]);
      String domain = temp[1];
      map.put(domain, map.getOrDefault(domain, 0) + count);
      int idx = domain.indexOf(".");
      while (idx != -1) {
        domain = domain.substring(idx + 1);
        map.put(domain, map.getOrDefault(domain, 0) + count);
        idx = domain.indexOf(".");
      }
    }

    List<String> ans = new ArrayList<>();
    for (Map.Entry<String, Integer> e : map.entrySet()) {
      String cpd = String.valueOf(e.getValue()) + " " + e.getKey();
      ans.add(cpd);
    }

    return ans;
  }

  // Author: leetcode + kei
  // Date : May 15, 2021
  public List<String> subdomainVisits2(String[] cpdomains) {
    Map<String, Integer> counts = new HashMap<>();
    for (String domain : cpdomains) {
      String[] cpinfo = domain.split("\\s+");
      String[] frags = cpinfo[1].split("\\.");
      int count = Integer.valueOf(cpinfo[0]);
      String cur = "";
      for (int i = frags.length - 1; i >= 0; --i) {
        cur = frags[i] + (i < frags.length - 1 ? "." : "") + cur;
        counts.put(cur, counts.getOrDefault(cur, 0) + count);
      }
    }

    List<String> ans = new ArrayList<>();
    for (String dom : counts.keySet())
      ans.add("" + counts.get(dom) + " " + dom);
    return ans;
  }

  // For testing.
  @SuppressWarnings("unused")
  public static void main(String[] args) {
    SubdomainVisitCount solution = new SubdomainVisitCount();

    // Test arguments.
    // int num = 24;
    // int target = 2;
    // System.out.println(solution.getInt(num, target));

    System.out.println("\ndone.");
  }

}
