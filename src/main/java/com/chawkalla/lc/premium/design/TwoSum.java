/**
 * 
 */
package com.chawkalla.lc.premium.design;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 *https://leetcode.com/problems/two-sum-iii-data-structure-design/#/description
 *
 *Design and implement a TwoSum class. It should support the following operations: add and find.

add - Add the number to an internal data structure.
find - Find if there exists any pair of numbers which sum is equal to the value.

For example,
add(1); add(3); add(5);
find(4) -> true
find(7) -> false
Hide Company Tags LinkedIn
Hide Tags






Solution:
The big data test only have the condition that lots of add and few find. In fact, there has to be one operation's time complexity is O(n) and the other is O(1), no matter add or find. So clearly there's trade off when solve this problem, prefer quick find or quick add.

If consider more find and less add or we only care time complexity in finding.For example, add operation can be pre-done.

Three approaches solution...

 *
 */
public class TwoSum {

	Set<Integer> sum;
	Set<Integer> num;

	public TwoSum(){
		sum = new HashSet<Integer>();
		num = new HashSet<Integer>();
	}
	// Add the number to an internal data structure.
	public void add(int number) {
		if(num.contains(number)){
			sum.add(number * 2);
		}else{
			Iterator<Integer> iter = num.iterator();
			while(iter.hasNext()){
				sum.add(iter.next() + number);
			}
			num.add(number);
		}
	}

	// Find if there exists any pair of numbers which sum is equal to the value.
	public boolean find(int value) {
		return sum.contains(value);
	}



	Map<Integer,Integer> hm;

	public TwoSum(boolean other){
		hm = new HashMap<Integer,Integer>();
	}
	// Add the number to an internal data structure.
	public void add1(int number) {
		if(hm.containsKey(number)){
			hm.put(number,2);
		}else{
			hm.put(number,1);
		}
	}

	// Find if there exists any pair of numbers which sum is equal to the value.
	public boolean find1(int value) {
		Iterator<Integer> iter = hm.keySet().iterator();
		while(iter.hasNext()){
			int num1 = iter.next();
			int num2 = value - num1;
			if(hm.containsKey(num2)){
				if(num1 != num2 || hm.get(num2) == 2){
					return true;
				}
			}
		}
		return false;
	}


	private List<Integer> list = new ArrayList<Integer>();
    private Map<Integer, Integer> map = new HashMap<Integer, Integer>();

    // Add the number to an internal data structure.
	public void add2(int number) {
	    if (map.containsKey(number)) map.put(number, map.get(number) + 1);
	    else {
	        map.put(number, 1);
	        list.add(number);
	    }
	}

    // Find if there exists any pair of numbers which sum is equal to the value.
	public boolean find2(int value) {
	    for (int i = 0; i < list.size(); i++){
	        int num1 = list.get(i), num2 = value - num1;
	        if ((num1 == num2 && map.get(num1) > 1) || (num1 != num2 && map.containsKey(num2))) return true;
	    }
	    return false;
	}


	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
