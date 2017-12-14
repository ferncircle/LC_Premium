/**
 * 
 */
package com.chawkalla.lc.premium.recursion;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * https://leetcode.com/problems/nested-list-weight-sum-ii/#/description
 * 
 * Given a nested list of integers, return the sum of all integers in the list weighted by their depth.

Each element is either an integer, or a list -- whose elements may also be integers or other lists.

Different from the previous question where weight is increasing from root to leaf, now the weight is defined from bottom up.
 i.e., the leaf level integers have weight 1, and the root level integers have the largest weight.

Example 1:
Given the list [[1,1],2,[1,1]], return 8. (four 1's at depth 1, one 2 at depth 2)

Example 2:
Given the list [1,[4,[6]]], return 17. (one 1 at depth 3, one 4 at depth 2, and one 6 at depth 1; 1*3 + 4*2 + 6*1 = 17)

Hide Company Tags LinkedIn
Hide Tags


Solution:
Inspired by lzb700m's solution and one of mine. Instead of multiplying by depth, add integers multiple times 
(by going level by level and adding the unweighted sum to the weighted sum after each level).

 *
 */
public class NestedListWeightSum2 {

	public int depthSumInverse(List<NestedInteger> nestedList) {
	    int unweighted = 0, total = 0;
	    while (!nestedList.isEmpty()) {
	        List<NestedInteger> nextLevel = new ArrayList<>();
	        for (NestedInteger ni : nestedList) {
	            if (ni.isInteger())
	                unweighted += ni.getInteger();
	            else
	                nextLevel.addAll(ni.getList());
	        }
	        total += unweighted;
	        nestedList = nextLevel;
	    }
	    return total;
	}
	
	
	public int depthSumInverseBFS(List<NestedInteger> nestedList) {
        if (nestedList == null) return 0;
        Queue<NestedInteger> queue = new LinkedList<NestedInteger>(nestedList);
        int prev = 0;
        int total = 0;
        
        while (!queue.isEmpty()) {
            int size = queue.size();
            int levelSum = 0;
            for (int i = 0; i < size; i++) {
                NestedInteger current = queue.poll();
                if (current.isInteger()) levelSum += current.getInteger();
                List<NestedInteger> nextList = current.getList();
                if (nextList != null) {
                	queue.addAll(nextList);
                }
            }
            prev += levelSum;
            total += prev;
        }
        return total;
    }
	
	public int sumBFS(List<NestedInteger> list){
		
		int total=0,unWeightedSum=0;
		
		Queue<NestedInteger> queue=new LinkedList<NestedInteger>(list);
		while(!queue.isEmpty()){
			int size=queue.size();
			
			for(int i=0;i<size;i++){
				NestedInteger ni=queue.poll();
				if(ni.isInteger())
					unWeightedSum+=ni.getInteger();
				else
					queue.addAll(ni.getList());
			}
			
			total+=unWeightedSum;
		}
		
		return total;
		
		
	}
	
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
