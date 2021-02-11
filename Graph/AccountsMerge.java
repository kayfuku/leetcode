package leetcode;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Stack;

public class AccountsMerge {
  // fields and classes here.
  // private int count;

  public AccountsMerge() {
    // Initialization here.
    // this.count = 0;

  }

  // 1. DFS, Connected Component
  // Author: leetcode + kei
  // Date : February 10, 2021
  public List<List<String>> accountsMerge(List<List<String>> accounts) {
    // Create a graph. (Adjacent list)
    Map<String, List<String>> graph = new HashMap<>();
    // Get a set of unique emails and keep track of which name an email belongs to.
    // K: email, V: name
    Map<String, String> emailToName = new HashMap<>();
    for (List<String> account : accounts) {
      String name = "";
      String email1 = account.get(1);
      for (String email : account) {
        if (name == "") {
          name = email;
          continue;
        }
        graph.putIfAbsent(email, new ArrayList<>());
        graph.get(email).add(email1);
        graph.putIfAbsent(email1, new ArrayList<>());
        graph.get(email1).add(email);
        emailToName.put(email, name);
      }
    }

    List<List<String>> ans = new ArrayList<>();

    // DFS, Connect Components
    Set<String> seen = new HashSet<>();
    for (String node : graph.keySet()) {
      if (seen.contains(node)) {
        continue;
      }
      List<String> component = new ArrayList<>();
      // Iterate within connected nodes.
      Deque<String> stack = new ArrayDeque<>();
      stack.push(node);
      seen.add(node);
      while (!stack.isEmpty()) {
        String n = stack.pop();
        component.add(n);
        for (String nei : graph.get(n)) {
          if (seen.contains(nei)) {
            continue;
          }
          stack.push(nei);
          seen.add(nei);
        }
      }

      Collections.sort(component);
      component.add(0, emailToName.get(node));
      ans.add(component);
    }

    return ans;
  }

  // 2. Union Find, Connected Component
  // Author: leetcode + kei
  // Date : February 10, 2021
  public List<List<String>> accountsMerge2(List<List<String>> accounts) {
    // Use Union Find (Disjoint Set) to group nodes.
    DSU dsu = new DSU();
    // Get a set of unique emails and keep track of which name an email belongs to.
    // K: email, V: name
    Map<String, String> emailToName = new HashMap<>();
    // Maybe, if we use String type in UF, then this is not necessary.
    Map<String, Integer> emailToID = new HashMap<>();
    int id = 0;
    for (List<String> account : accounts) {
      String name = "";
      String email1 = account.get(1);
      for (String email : account) {
        if (name == "") {
          name = email;
          continue;
        }
        if (!emailToID.containsKey(email)) {
          emailToID.put(email, id);
          id++;
        }
        dsu.unite(emailToID.get(email1), emailToID.get(email));
        emailToName.put(email, name);
      }
    }

    Map<Integer, List<String>> ans = new HashMap<>();
    for (String email : emailToName.keySet()) {
      // Find a root of the group.
      int index = dsu.find(emailToID.get(email));
      // Group them.
      ans.putIfAbsent(index, new ArrayList<>());
      ans.get(index).add(email);
    }
    // Add names.
    for (List<String> component : ans.values()) {
      Collections.sort(component);
      component.add(0, emailToName.get(component.get(0)));
    }

    return new ArrayList<>(ans.values());
  }

  class DSU {
    int[] parent;

    public DSU() {
      parent = new int[10001];
      for (int i = 0; i <= 10000; i++) {
        parent[i] = i;
      }
    }

    public int find(int x) {
      if (parent[x] == x) {
        return x;
      }
      return find(parent[x]);
    }

    public void unite(int x, int y) {
      parent[find(x)] = find(y);
    }
  }

  // For testing.
  @SuppressWarnings("unused")
  public static void main(String[] args) {
    AccountsMerge solution = new AccountsMerge();

    // Test arguments.
    // int num = 24;
    // int target = 2;
    // System.out.println(solution.getInt(num, target));

    System.out.println("\ndone.");
  }

}
