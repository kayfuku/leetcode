package leetcode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class ReconstructItinerary {
  // fields and classes here.
  // private int count;

  public ReconstructItinerary() {
    // Initialization here.
    // this.count = 0;

  }

  // 1. Graph traversal, Eulerian path
  // a trail in a finite graph that visits every edge exactly once (allowing for
  // revisiting vertices).
  //
  // Author: + kei
  // Date : February 10, 2021

  // Adjacent list graph if we use List (not Set), then we can handle
  // more than one edge to the same destination node.
  HashMap<String, List<String>> g = new HashMap<>();
  // To keep track of if the edge is used
  HashMap<String, boolean[]> visitedEdges = new HashMap<>();
  // I think we need this in the case where duplicate tickets exist.
  int numEdges = 0;
  List<String> result = null;

  public List<String> findItinerary(List<List<String>> tickets) {
    // 1) Build the graph first.
    for (List<String> ticket : tickets) {
      String s = ticket.get(0);
      String t = ticket.get(1);
      this.g.putIfAbsent(s, new ArrayList<>());
      this.g.get(s).add(t);
    }

    // 2) Order the destinations and init the visited.
    for (Map.Entry<String, List<String>> entry : this.g.entrySet()) {
      Collections.sort(entry.getValue());
      // Note that this line is a bit different from a normal graph traversal.
      // To keep track of if the edge is used, we use boolean array.
      // We cannot use set here because same nodes must be considered differet nodes.
      this.visitedEdges.put(entry.getKey(), new boolean[entry.getValue().size()]);
    }

    // 3) Backtracking
    this.numEdges = tickets.size();
    List<String> route = new LinkedList<>();
    route.add("JFK");
    this.backtracking("JFK", route);

    return this.result;
  }

  protected boolean backtracking(String origin, List<String> route) {
    if (route.size() == this.numEdges + 1) {
      // All the edges are used.
      this.result = new LinkedList<>(route);
      return true;
    }

    if (!this.g.containsKey(origin)) {
      // There should be nodes that we have not visited but there is no destination
      // from the current node.
      return false;
    }

    List<String> nei = this.g.get(origin);
    boolean[] visited = this.visitedEdges.get(origin);
    for (int i = 0; i < nei.size(); i++) {
      if (visited[i]) {
        continue;
      }
      visited[i] = true;
      String dest = nei.get(i);
      route.add(dest);
      if (backtracking(dest, route)) {
        return true;
      }
      route.remove(route.size() - 1);
      visited[i] = false;
    }

    return false;
  }

  // 2. Hierholzer's Algorithm (Not completely reviewed yet)
  // origin -> list of destinations
  // Author: leetcode + kei
  // Date : February 10, 2021

  HashMap<String, LinkedList<String>> flightMap = new HashMap<>();

  public List<String> findItinerary2(List<List<String>> tickets) {
    // 1) build the graph first
    for (List<String> ticket : tickets) {
      String s = ticket.get(0);
      String t = ticket.get(1);
      this.flightMap.putIfAbsent(s, new LinkedList<>());
      this.flightMap.get(s).add(t);
    }

    // 2) order the destinations
    this.flightMap.forEach((key, value) -> Collections.sort(value));

    this.result = new LinkedList<>();
    // 3) post-order DFS
    this.DFS("JFK");

    return this.result;
  }

  protected void DFS(String origin) {
    // Visit all the outgoing edges first.
    if (this.flightMap.containsKey(origin)) {
      LinkedList<String> destList = this.flightMap.get(origin);
      while (!destList.isEmpty()) {
        // while we visit the edge, we trim it off from graph.
        String dest = destList.pollFirst();
        DFS(dest);
      }
    }
    // add the airport to the head of the itinerary
    this.result.add(0, origin);
  }

  // For testing.
  @SuppressWarnings("unused")
  public static void main(String[] args) {
    ReconstructItinerary solution = new ReconstructItinerary();

    // Test arguments.
    // int num = 24;
    // int target = 2;
    // System.out.println(solution.getInt(num, target));

    System.out.println("\ndone.");
  }

}
