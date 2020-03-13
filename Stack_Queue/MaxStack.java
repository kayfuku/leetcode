//
// Author:
// Date : March 12, 2020

package leetcode;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import java.util.TreeMap;

public class MaxStack {
  // fields and classes here.
  // private int count;


  // popMax() O(N) time
  // Author: @awice + kei
  // Date : March 12, 2020
  Stack<Integer> stack;
  Stack<Integer> maxStack;

  public MaxStack() {
    stack = new Stack<>();
    maxStack = new Stack<>();
  }

  public void push(int x) {
    int max = maxStack.isEmpty() ? x : maxStack.peek();
    maxStack.push(x > max ? x : max);
    stack.push(x);
  }

  public int pop() {
    maxStack.pop();
    return stack.pop();
  }

  public int top() {
    return stack.peek();
  }

  public int peekMax() {
    return maxStack.peek();
  }

  public int popMax() {
    int max = peekMax();
    Stack<Integer> buffer = new Stack<>();
    // Pop the elements until we find the max.
    while (top() != max) {
      buffer.push(pop());
    }
    // Pop the max.
    pop();
    // Push the popped elements back on the stack.
    while (!buffer.isEmpty()) {
      push(buffer.pop());
    }

    return max;
  }

  /**
   * Your MaxStack object will be instantiated and called as such: MaxStack obj = new MaxStack();
   * obj.push(x); int param_2 = obj.pop(); int param_3 = obj.top(); int param_4 = obj.peekMax(); int
   * param_5 = obj.popMax();
   */


  // popMax() O(logN) time
  // Author: @awice + kei
  // Date : March 13, 2020
  class MaxStack2 {
    TreeMap<Integer, List<Node>> map;
    DoubleLinkedList dll;

    public MaxStack2() {
      map = new TreeMap<>();
      dll = new DoubleLinkedList();
    }

    public void push(int x) {
      Node node = dll.add(x);
      if (!map.containsKey(x)) {
        map.put(x, new ArrayList<Node>());
      }
      map.get(x).add(node);
    }

    public int pop() {
      int val = dll.pop();
      List<Node> L = map.get(val);
      L.remove(L.size() - 1);
      if (L.isEmpty()) {
        map.remove(val);
      }
      return val;
    }

    public int top() {
      return dll.peek();
    }

    public int peekMax() {
      return map.lastKey();
    }

    public int popMax() {
      int max = peekMax();
      List<Node> L = map.get(max);
      Node node = L.remove(L.size() - 1);
      dll.unlink(node);
      if (L.isEmpty())
        map.remove(max);
      return max;
    }
  }

  class DoubleLinkedList {
    Node head, tail;

    public DoubleLinkedList() {
      head = new Node(0);
      tail = new Node(0);
      head.next = tail;
      tail.prev = head;
    }

    public Node add(int val) {
      Node x = new Node(val);
      x.next = tail;
      x.prev = tail.prev;
      tail.prev = tail.prev.next = x;
      return x;
    }

    public int pop() {
      return unlink(tail.prev).val;
    }

    public int peek() {
      return tail.prev.val;
    }

    public Node unlink(Node node) {
      node.prev.next = node.next;
      node.next.prev = node.prev;
      return node;
    }
  }

  class Node {
    int val;
    Node prev, next;

    public Node(int v) {
      val = v;
    }
  }


  // For testing.
  @SuppressWarnings("unused")
  public static void main(String[] args) {
    MaxStack solution = new MaxStack();

    // Test arguments.
    // int num = 24;
    // int target = 2;
    // solution.getInt(num, target);



  }

}


