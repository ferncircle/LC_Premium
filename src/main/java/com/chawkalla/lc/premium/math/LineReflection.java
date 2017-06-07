/**
 * 
 */
package com.chawkalla.lc.premium.math;

import java.util.HashSet;

/**
 * https://leetcode.com/problems/line-reflection/#/description
 * 
 * Given n points on a 2D plane, find if there is such a line parallel to y-axis that reflect the given points.

Example 1:
Given points = [[1,1],[-1,1]], return true.

Example 2:
Given points = [[1,1],[-1,-1]], return false.

Follow up:
Could you do better than O(n2)?

Credits:
Special thanks to @memoryless for adding this problem and creating all test cases.

Hide Company Tags Google
Hide Tags



 *
 */
public class LineReflection {

	public boolean isReflected(int[][] points) {
	    int max = Integer.MIN_VALUE;
	    int min = Integer.MAX_VALUE;
	    HashSet<String> set = new HashSet<>();
	    for(int[] p:points){
	        max = Math.max(max,p[0]);
	        min = Math.min(min,p[0]);
	        String str = p[0] + "a" + p[1];
	        set.add(str);
	    }
	    int sum = max+min;
	    for(int[] p:points){
	        //int[] arr = {sum-p[0],p[1]};
	        String str = (sum-p[0]) + "a" + p[1];
	        if( !set.contains(str))
	            return false;
	        
	    }
	    return true;
	}
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
