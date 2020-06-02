//
// Author:
// Date : May 30, 2020

package leetcode;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class SerializeAndDeserializeBinaryTree {
  // fields and classes here.
  // private int count;

  public SerializeAndDeserializeBinaryTree() {
    // Initialization here.
    // this.count = 0;

  }

  public class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode(int x) {
      val = x;
    }
  }


  // Author: LeetCode + kei
  // Date : May 30, 2020
  // Encodes a tree to a single string.

  // Serialize.
  public String serialize(TreeNode root) {
    return rserialize(root, "");
  }

  public String rserialize(TreeNode root, String str) {
    // Recursive serialization.
    if (root == null) {
      str += "null,";
      return str;
    }

    // Pre-order traversal
    str += String.valueOf(root.val) + ",";
    str = rserialize(root.left, str);
    str = rserialize(root.right, str);

    return str;
  }

  // Deserialize.
  public TreeNode deserialize(String data) {
    String[] dataArray = data.split(",");
    // Use LinkedList, not ArrayList because we want to delete the node,
    // which is less expensive time complexity in linked list.
    List<String> dataList = new LinkedList<String>(Arrays.asList(dataArray));
    return rdeserialize(dataList);
  }

  public TreeNode rdeserialize(List<String> list) {
    // Recursive deserialization.
    if (list.get(0).equals("null")) {
      list.remove(0);
      return null;
    }

    // Pre-order traversal
    TreeNode node = new TreeNode(Integer.valueOf(list.get(0)));
    list.remove(0);
    node.left = rdeserialize(list);
    node.right = rdeserialize(list);

    return node;
  }


  // For testing.
  @SuppressWarnings("unused")
  public static void main(String[] args) {
    SerializeAndDeserializeBinaryTree solution = new SerializeAndDeserializeBinaryTree();

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


