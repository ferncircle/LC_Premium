/**
 * 
 */
package com.chawkalla.lc.premium.array;

/**
 * https://leetcode.com/problems/range-addition/#/description
 * 
Assume you have an array of length n initialized with all 0's and are given k update operations.

Each operation is represented as a triplet: [startIndex, endIndex, inc] which increments each element of subarray 
A[startIndex ... endIndex] (startIndex and endIndex inclusive) with inc.

Return the modified array after all k operations were executed.

Example:

Given:

    length = 5,
    updates = [
        [1,  3,  2],
        [2,  4,  3],
        [0,  2, -2]
    ]

Output:

    [-2, 0, 3, 5, 3]
Explanation:

Initial state:
[ 0, 0, 0, 0, 0 ]

After applying operation [1, 3, 2]:
[ 0, 2, 2, 2, 0 ]

After applying operation [2, 4, 3]:
[ 0, 2, 5, 5, 3 ]

After applying operation [0, 2, -2]:
[-2, 0, 3, 5, 3 ]
Credits:
Special thanks to @vinod23 for adding this problem and creating all test cases.

Hide Company Tags Google
Hide Tags
 *
 *
 *Solution:
 *Just store every a[start]=value and a[end+1]=-value

for example it will look like:

[1 , 3 , 2] , [2, 3, 3] (length = 5)

res[ 0, 2, ,0, 0 -2 ]

res[ 0 ,2, 3, 0, -5]

sum 0, 2, 5, 5, 0

res[0, 2, 5, 5, 0]
 */
public class RangeAddition {

	 public int[] getModifiedArray(int length, int[][] updates) {

		    int[] res = new int[length];
		     for(int[] update : updates) {
		        int value = update[2];
		        int start = update[0];
		        int end = update[1];
		        
		        res[start] += value;
		        
		        if(end < length - 1)
		            res[end + 1] -= value;
		        
		    }
		    
		    int sum = 0;
		    for(int i = 0; i < length; i++) {
		        sum += res[i];
		        res[i] = sum;
		    }
		    
		    return res;
		}
	 
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
