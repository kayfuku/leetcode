// 
// Author: 
// Date  : January 2, 2020

package leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class MinimumIndexSumOfTwoLists {
	// fields and classes here. 
	//private int count;

	public MinimumIndexSumOfTwoLists() {
		// Initialization here. 
		//this.count = 0;

	}


	// Better approach. 
	// Sum => Need to traverse both lists, but => Traversing one list first makes it easier. 
	// Author: cloud.runner + kei
	// Date  : January 2, 2020
	public String[] findRestaurant(String[] list1, String[] list2) {
		List<String> res = new ArrayList<>();
		// One map is enough. 
		HashMap<String, Integer> map = new HashMap<>();
		
		// Traverse one list first to make a map. 
		for (int i = 0; i < list1.length; i++) {
			map.put(list1[i], i);
		}
		
		int min = Integer.MAX_VALUE;
		int sum = 0;
		// Don't forget the optimization, && j <= min
		for (int j = 0; j < list2.length && j <= min; j++) {
			if (map.containsKey(list2[j])) {
				sum = j + map.get(list2[j]);
				if (sum < min) {
					res.clear();
					res.add(list2[j]);
					min = sum;
				} else if (sum == min) {
					res.add(list2[j]);
				}
			}
		}
		
		// Convert list to array. 
		return res.toArray(new String[res.size()]);
	}
	
	
	// Traverse two lists at the same time is a little more complicated. 
	// Author: kei (AC)
	// Date  : January 2, 2020
	public String[] findRestaurant2(String[] list1, String[] list2) {
		List<String> res = new ArrayList<>();
		HashMap<String, Integer> map1 = new HashMap<>();
		HashMap<String, Integer> map2 = new HashMap<>();
		
		int min = Integer.MAX_VALUE;
		int i = 0, j = 0;
		while ((i != list1.length || j != list2.length ) && min >= Math.min(i, j)) {
			int sum = 0;
			if (i != list1.length) {
				if (map2.containsKey(list1[i])) {
					sum = i + map2.get(list1[i]);
					if (sum < min) {
						res.clear();
						res.add(list1[i]);
						min = sum;
					} else if (sum == min) {
						res.add(list1[i]);
					} 
				}
				map1.put(list1[i], i);
				i++;
			}
			
			if (j != list2.length) {
				if (map1.containsKey(list2[j])) {
					sum = j + map1.get(list2[j]);
					if (sum < min) {
						res.clear();
						res.add(list2[j]);
						min = sum;
					} else if (sum == min) {
						res.add(list2[j]);
					} 
				}
				map2.put(list2[j], j);
				j++;
			}
		}
		
		// Convert list to array. 
		return res.toArray(new String[res.size()]);
	}







	// For testing. 
	public static void main(String[] args) {
		MinimumIndexSumOfTwoLists solution = new MinimumIndexSumOfTwoLists();

		// Test arguments. 
		// int num = 24;
		// int target = 2;
		// solution.getInt(num, target);

		String[] strings1 = new String[]{ "Shogun", "Tapioca Express", "Burger King", "KFC" };
		String[] strings2 = new String[]{ "Piatti", "The Grill at Torrey Pines", "Hungry Hunter Steakhouse", "Shogun" };

		String[] res = solution.findRestaurant2(strings1, strings2);
		System.out.println(Arrays.toString(res));

	}

}















