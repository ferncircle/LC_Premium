/**
 * 
 */
package com.chawkalla.lc.premium.matrix;

import java.util.HashSet;
import java.util.Set;

/**
 * https://leetcode.com/problems/longest-line-of-consecutive-one-in-matrix/#/description
 * 
 * Given a 01 matrix M, find the longest line of consecutive one in the matrix. The line could be horizontal, vertical, diagonal or anti-diagonal.

Example:
Input:
[[0,1,1,0],
 [0,1,1,0],
 [0,0,0,1]]
Output: 3
Hint: The number of elements in the given matrix will not exceed 10,000.

Hide Company Tags Google
Show Tags



 */
public class LongestLineOfConsecutiveOneInMatrix {

	public int longestLine(int[][] M) {
	    int n = M.length, max = 0;
	    if (n == 0) return max;
	    int m = M[0].length;
	    int[][][] dp = new int[n][m][4];
	    for (int i=0;i<n;i++) 
	        for (int j=0;j<m;j++) {
	            if (M[i][j] == 0) continue;
	            for (int k=0;k<4;k++) dp[i][j][k] = 1;
	            if (j > 0) dp[i][j][0] += dp[i][j-1][0]; // horizontal line
	            if (j > 0 && i > 0) dp[i][j][1] += dp[i-1][j-1][1]; // anti-diagonal line
	            if (i > 0) dp[i][j][2] += dp[i-1][j][2]; // vertical line
	            if (j < m-1 && i > 0) dp[i][j][3] += dp[i-1][j+1][3]; // diagonal line
	            max = Math.max(max, Math.max(dp[i][j][0], dp[i][j][1]));
	            max = Math.max(max, Math.max(dp[i][j][2], dp[i][j][3]));
	        }
	    return max;
	}
	
	public int longestLine2(int[][] M) {
        int m = M.length;
        if (m <= 0) return 0;
        int n = M[0].length;
        if (n <= 0) return 0;
        
        Set<String> horizontal = new HashSet<String>();
        Set<String> vertical = new HashSet<String>();
        Set<String> diagonal = new HashSet<String>();
        Set<String> antidiagonal = new HashSet<String>();
        int max = 0;
        
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (M[i][j] == 0) continue;
                String pos = i + "," + j;
                if (!horizontal.contains(pos)) {
                    int count = 0;
                    for (int k = j; k < n; k++) {
                        if (M[i][k] == 1) {
                            count++;
                            horizontal.add(i + "," + k);
                        }
                        else break;
                    }
                    max = Math.max(max, count);
                }
                if (!vertical.contains(pos)) {
                    int count = 0;
                    for (int k = i; k < m; k++) {
                        if (M[k][j] == 1) {
                            count++;
                            vertical.add(k + "," + j);
                        }
                        else break;
                    }
                    max = Math.max(max, count);
                }
                if (!diagonal.contains(pos)) {
                    int count = 0;
                    for (int k = i, l = j; k < m && l < n; k++, l++) {
                        if (M[k][l] == 1) {
                            count++;
                            diagonal.add(k + "," + l);
                        }
                        else break;
                    }
                    max = Math.max(max, count);
                }
                if (!antidiagonal.contains(pos)) {
                    int count = 0;
                    for (int k = i, l = j; k < m && l >= 0; k++, l--) {
                        if (M[k][l] == 1) {
                            count++;
                            antidiagonal.add(k + "," + l);
                        }
                        else break;
                    }
                    max = Math.max(max, count);
                }
            }
        }
        
        return max;
    }
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
