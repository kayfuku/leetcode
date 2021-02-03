package leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class AnalyzeUserWebsiteVisitPattern {
  // fields and classes here.
  // private int count;

  public AnalyzeUserWebsiteVisitPattern() {
    // Initialization here.
    // this.count = 0;

  }

  // swaranker's solution uses List as a key of set or map, which surprised me
  // because the lists are considered the same based on their contents, not their
  // addresses in memory.
  // https://leetcode.com/problems/analyze-user-website-visit-pattern/discuss/550403/Java-Solution%3A-With-Comments-95-faster
  //
  // In my solution, I used String as a key of set or map by concatenating using
  // spaces. For example, a list of ["a", "b", "c"] becomes "a b c".
  // swaranker's solution is faster.
  //
  // Author: swarankar + kei
  // Date : February 2, 2021
  class Visit {
    String userName;
    int timestamp;
    String website;

    Visit(String u, int t, String w) {
      this.userName = u;
      this.timestamp = t;
      this.website = w;
    }

    Visit() {
    }
  }

  public List<String> mostVisitedPattern(String[] username, int[] timestamp, String[] website) {
    // Convert all the entries to visit entries to easily understand.
    List<Visit> visitList = new ArrayList<>();
    for (int i = 0; i < username.length; i++) {
      visitList.add(new Visit(username[i], timestamp[i], website[i]));
    }

    // Sort all the visit entries in ascending order by its timestamp.
    Collections.sort(visitList, (v1, v2) -> v1.timestamp - v2.timestamp);

    // Create a list of websites for each user.
    Map<String, List<String>> userWebSitesMap = new HashMap<>();
    for (Visit v : visitList) {
      // A pattern of building a map where the value has a list.
      userWebSitesMap.putIfAbsent(v.userName, new ArrayList<>());
      userWebSitesMap.get(v.userName).add(v.website);
    }

    // Count the number of 3-sequences for each user.
    Map<String, Integer> counts = new HashMap<>();
    // Get all the values of all the users.
    for (List<String> websitesList : userWebSitesMap.values()) {
      if (websitesList.size() < 3) {
        // No need to consider.
        continue;
      }
      // Get all the unique combinations of 3 sequences.
      Set<String> sequencesSet = getSequencesSet(websitesList);
      // Count the number of 3-sequences.
      for (String seq : sequencesSet) {
        counts.put(seq, counts.getOrDefault(seq, 0) + 1);
      }
    }

    List<String> res = new ArrayList<>();
    int max = 0;
    for (Map.Entry<String, Integer> entry : counts.entrySet()) {
      String seq = entry.getKey();
      int count = entry.getValue();
      if (count > max) {
        max = count;
        res = new ArrayList<>(Arrays.asList(seq.split(" ")));
      } else if (count == max) {
        if (seq.compareTo(res.get(0) + " " + res.get(1) + " " + res.get(2)) < 0) {
          res = new ArrayList<>(Arrays.asList(seq.split(" ")));
        }
      }
    }
    return res;
  }

  private Set<String> getSequencesSet(List<String> websitesList) {
    // Get all the unique combinations of 3 sequences.
    Set<String> sequenceSet = new HashSet<>();
    for (int i = 0; i < websitesList.size(); i++) {
      for (int j = i + 1; j < websitesList.size(); j++) {
        for (int k = j + 1; k < websitesList.size(); k++) {
          String seq = websitesList.get(i) + " " + websitesList.get(j) + " " + websitesList.get(k);
          sequenceSet.add(seq);
        }
      }
    }
    return sequenceSet;
  }

  // For testing.
  @SuppressWarnings("unused")
  public static void main(String[] args) {
    AnalyzeUserWebsiteVisitPattern solution = new AnalyzeUserWebsiteVisitPattern();

    // Test arguments.
    // int num = 24;
    // int target = 2;
    // System.out.println(solution.getInt(num, target));

    System.out.println("\ndone.");
  }

}
