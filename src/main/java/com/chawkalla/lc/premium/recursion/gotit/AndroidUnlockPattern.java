/**
 * 
 */
package com.chawkalla.lc.premium.recursion.gotit;

/**
 * https://leetcode.com/problems/android-unlock-patterns/#/description
 * 
 * See image
 * Given an Android 3x3 key lock screen and two integers m and n, where 1 ≤ m ≤ n ≤ 9, count the total number of unlock patterns 
 * of the Android lock screen, which consist of minimum of m keys and maximum n keys.

Rules for a valid pattern:
Each pattern must connect at least m keys and at most n keys.
All the keys must be distinct.
If the line connecting two consecutive keys in the pattern passes through any other keys, the other keys must have previously 
selected in the pattern. No jumps through non selected key is allowed.
The order of keys used matters.

Explanation:
| 1 | 2 | 3 |
| 4 | 5 | 6 |
| 7 | 8 | 9 |
Invalid move: 4 - 1 - 3 - 6 
Line 1 - 3 passes through key 2 which had not been selected in the pattern.

Invalid move: 4 - 1 - 9 - 2
Line 1 - 9 passes through key 5 which had not been selected in the pattern.

Valid move: 2 - 4 - 1 - 3 - 6
Line 1 - 3 is valid because it passes through key 2, which had been selected in the pattern

Valid move: 6 - 5 - 4 - 1 - 9 - 2
Line 1 - 9 is valid because it passes through key 5, which had been selected in the pattern.

Example:
Given m = 1, n = 1, return 9.

Credits:
Special thanks to @elmirap for adding this problem and creating all test cases.

Hide Company Tags Google
Hide Tags


Solution: 
The general idea is DFS all the possible combinations from 1 to 9 and skip invalid moves along the way.

We can check invalid moves by using a jumping table. e.g. If a move requires a jump and the key that it is crossing is 
not visited, then the move is invalid. Furthermore, we can utilize symmetry to reduce runtime, in this case it reduced 
from ~120ms to ~70ms.

I felt clueless when first encountered this problem, and considered there must be lots of edge cases. Turns out, it's pretty 
straight forward. Hope this solution helps :D

 *
 */
public class AndroidUnlockPattern {

	private int[][] jumps;
	private boolean[] visited;

	public int numberOfPatterns(int m, int n) {
	    jumps = new int[10][10];
	    jumps[1][3] = jumps[3][1] = 2;
	    jumps[4][6] = jumps[6][4] = 5;
	    jumps[7][9] = jumps[9][7] = 8;
	    jumps[1][7] = jumps[7][1] = 4;
	    jumps[2][8] = jumps[8][2] = 5;
	    jumps[3][9] = jumps[9][3] = 6;
		jumps[1][9] = jumps[9][1] = jumps[3][7] = jumps[7][3] = 5;
	    visited = new boolean[10];
	    int count = 0;
		count += DFS(1, 1, 0, m, n) * 4; // 1, 3, 7, 9 are symmetrical
		count += DFS(2, 1, 0, m, n) * 4; // 2, 4, 6, 8 are symmetrical
		count += DFS(5, 1, 0, m, n);
		return count;
	}

	private int DFS(int num, int len, int count, int m, int n) {
		if (len >= m) count++; // only count if moves are larger than m
		len++;
		if (len > n) return count;
	    visited[num] = true;
	    for (int next = 1; next <= 9; next++) {
	        int jump = jumps[num][next];
	        if (!visited[next] && (jump == 0 || visited[jump])) {
	            count = DFS(next, len, count, m, n);
	        }
	    }
		visited[num] = false; // backtracking
	    return count;
	}
    
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
