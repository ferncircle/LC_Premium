/**
 * 
 */
package com.chawkalla.lc.premium.dp;

/**
 * https://leetcode.com/problems/paint-house-ii/#/description
 * 
 * There are a row of n houses, each house can be painted with one of the k colors. The cost of painting each house with a 
 * certain color is different. You have to paint all the houses such that no two adjacent houses have the same color.

The cost of painting each house with a certain color is represented by a n x k cost matrix. For example, costs[0][0] is the 
cost of painting house 0 with color 0; costs[1][2] is the cost of painting house 1 with color 2, and so on... Find the minimum 
cost to paint all houses.

Note:
All costs are positive integers.

Follow up:
Could you solve it in O(nk) runtime?
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *Solution:
 Explanation: dp[i][j] represents the min paint cost from house 0 to house i when house i use color j; The formula will be 
 dp[i][j] = Math.min(any k!= j| dp[i-1][k]) + costs[i][j].

Take a closer look at the formula, we don't need an array to represent dp[i][j], we only need to know the min cost to the 
previous house of any color and if the color j is used on previous house to get prev min cost, use the second min cost that are 
not using color j on the previous house. So I have three variable to record: prevMin, prevMinColor, prevSecondMin. and the above 
formula will be translated into: 
dp[currentHouse][currentColor] = (currentColor == prevMinColor? prevSecondMin: prevMin) + costs[currentHouse][currentColor].
 */
public class PaintHouse2 {

	public int minCostII(int[][] costs) {
		if(costs == null || costs.length == 0 || costs[0].length == 0) return 0;

		int n = costs.length, k = costs[0].length;
		if(k == 1) return (n==1? costs[0][0] : -1);

		int prevMin = 0, prevMinInd = -1, prevSecMin = 0;//prevSecMin always >= prevMin
		for(int i = 0; i<n; i++) {
			int min = Integer.MAX_VALUE, minInd = -1, secMin = Integer.MAX_VALUE;
			for(int j = 0; j<k;  j++) {
				int val = costs[i][j] + (j == prevMinInd? prevSecMin : prevMin);
				if(minInd< 0) {min = val; minInd = j;}//when min isn't initialized
				else if(val < min) {//when val < min, 
					secMin = min;
					min = val;
					minInd = j;
				} else if(val < secMin) { //when min<=val< secMin
					secMin = val;
				}
			}
			prevMin = min;
			prevMinInd = minInd;
			prevSecMin = secMin;
		}
		return prevMin;
	}	


	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
