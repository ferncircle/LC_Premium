/**
 * 
 */
package com.chawkalla.lc.premium.math;

/**
 * 
 * https://leetcode.com/problems/squirrel-simulation/#/description
 * 
 * There's a tree, a squirrel, and several nuts. Positions are represented by the cells in a 2D grid. Your goal is to find 
 * the minimal distance for the squirrel to collect all the nuts and put them under the tree one by one. The squirrel can 
 * only take at most one nut at one time and can move in four directions - up, down, left and right, to the adjacent cell. 
 * The distance is represented by the number of moves.

Example 1:
Input: 
Height : 5
Width : 7
Tree position : [2,2]
Squirrel : [4,4]
Nuts : [[3,0], [2,5]]
Output: 12
Explanation:
See Image

Note:
All given positions won't overlap.
The squirrel can take at most one nut at one time.
The given positions of nuts have no order.
Height and width are positive integers. 3 <= height * width <= 10,000.
The given positions contain at least one nut, only one tree and one squirrel.
Hide Company Tags Square
Show Tags








Solution:
Proof: Let the minimum distance from each nut to the tree be a_1, ..., a_n and let the minimum distance from each nut to the initial squirrel position be b_1, ..., b_n. Note that the minimum distance between two positions in the matrix is determined by their Manhattan distance.

Then, if the squirrel were to start at the tree, then the minimum total distance required to collect all the nuts is 2a_1 + ... + 2a_n. However, since the squirrel starts elsewhere, we just need to substitute one of the 2a_i terms with a_i + b_i. Or equivalently, we replace one of the a_i terms in the sum with b_i. To minimize the total sum value at the end, we choose i that maximizes a_i - b_i.

 *
 */
public class SquirrelSimulation {

	public int minDistance(int height, int width, int[] tree, int[] squirrel, int[][] nuts) {
	    int sum = 0, maxDiff = Integer.MIN_VALUE;
	    for (int[] nut : nuts) {
	        int dist = Math.abs(tree[0] - nut[0]) + Math.abs(tree[1] - nut[1]);
	        sum += 2*dist;
	        maxDiff = Math.max(maxDiff, dist - Math.abs(squirrel[0] - nut[0]) - Math.abs(squirrel[1] - nut[1]));
	    }
	    return sum - maxDiff;
	}
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
