/**
 * 
 */
package com.chawkalla.lc.premium.matrix.gotit;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;

/**
 * https://leetcode.com/problems/sparse-matrix-multiplication/#/description
 * Given two sparse matrices A and B, return the result of AB.

You may assume that A's column number is equal to B's row number.

Example:

A = [
  [ 1, 0, 0],
  [-1, 0, 3]
]

B = [
  [ 7, 0, 0 ],
  [ 0, 0, 0 ],
  [ 0, 0, 1 ]
]


     |  1 0 0 |   | 7 0 0 |   |  7 0 0 |
AB = | -1 0 3 | x | 0 0 0 | = | -7 0 3 |
                  | 0 0 1 |
 *
 *
 *LinkedIn, Facebook
 *
 *
 *
 *Solution: 
 *A sparse matrix can be represented as a sequence of rows, each of which is a sequence of (column-number, value) 
 *pairs of the nonzero values in the row.
So let's create a non-zero array for A, and do multiplication on B.


See concise solution as well

 */
public class SparseMatrixMultiplication {

	public int[][] multiply(int[][] A, int[][] B){

		int m=A.length, n=B[0].length;

		int[][] res=new int[m][n];

		ArrayList<HashMap<Integer, Integer>> list=new ArrayList<HashMap<Integer,Integer>>();

		for (int i = 0; i < A.length; i++) {
			list.add(new HashMap<Integer, Integer>());
			for (int j = 0; j < A[0].length; j++) {
				if(A[i][j]!=0)
					list.get(i).put(j, A[i][j]);
			}
		}

		for (int i = 0; i < m; i++) {
			HashMap<Integer, Integer> row=list.get(i);
			for(Entry<Integer,Integer> e:row.entrySet()){
				for (int j = 0; j < n; j++) {
					res[i][j]+=e.getValue()*B[e.getKey()][j];
				}
				
			}

		}

		return res;

	}

	public int[][] multiplyConcise(int[][] A, int[][] B) {
		int m = A.length, n = A[0].length, nB = B[0].length;
		int[][] C = new int[m][nB];

		for(int i = 0; i < m; i++) {
			for(int k = 0; k < n; k++) {
				if (A[i][k] != 0) {
					for (int j = 0; j < nB; j++) {
						if (B[k][j] != 0) C[i][j] += A[i][k] * B[k][j];
					}
				}
			}
		}
		return C;   
	}
	public static void main(String[] args) {

		assertThat(new SparseMatrixMultiplication().multiply(new int[][]{
			{ 1, 0, 2},
			{-1, 0, 3}
		}, new int[][]{
			{ 7, 0, 0 },
			{ 0, 0, 0 },
			{ 0, 0, 1 }
		}), is(new int[][]{
			{ 7, 0, 2},
			{-7, 0, 3}
		}));

		System.out.println("all cases passed");

	}

}
