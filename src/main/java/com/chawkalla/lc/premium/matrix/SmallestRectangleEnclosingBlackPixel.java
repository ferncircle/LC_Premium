/**
 * 
 */
package com.chawkalla.lc.premium.matrix;

/**
 * https://leetcode.com/problems/smallest-rectangle-enclosing-black-pixels/#/description
 * 
 * An image is represented by a binary matrix with 0 as a white pixel and 1 as a black pixel. The black pixels are connected, i.e., there is only one black region. Pixels are connected horizontally and vertically. Given the location (x, y) of one of the black pixels, return the area of the smallest (axis-aligned) rectangle that encloses all black pixels.

For example, given the following image:

[
  "0010",
  "0110",
  "0100"
]
and x = 0, y = 2,
Return 6.

Hide Company Tags Google
Hide Tags


Solution:

Suppose we have a 2D array

"000000111000000"
"000000101000000"
"000000101100000"
"000001100100000"
Imagine we project the 2D array to the bottom axis with the rule "if a column has any black pixel it's projection is black otherwise white". The projected 1D array is

"000001111100000"
Theorem

If there are only one black pixel region, then in a projected 1D array all the black pixels are connected.

Proof by contradiction

Assume to the contrary that there are disconnected black pixels at i
and j where i < j in the 1D projection array. Thus there exists one
column k, k in (i, j) and and the column k in the 2D array has no
black pixel. Therefore in the 2D array there exists at least 2 black
pixel regions separated by column k which contradicting the condition
of "only one black pixel region".

Therefore we conclude that all the black pixels in the 1D projection
array is connected.
This means we can do a binary search in each half to find the boundaries, if we know one black pixel's position. And we do know that.

To find the left boundary, do the binary search in the [0, y) range and find the first column vector who has any black pixel.

To determine if a column vector has a black pixel is O(m) so the search in total is O(m log n)

We can do the same for the other boundaries. The area is then calculated by the boundaries.
Thus the algorithm runs in O(m log n + n log m)


Solution2: DFS
 *
 */
public class SmallestRectangleEnclosingBlackPixel {

	private char[][] image;
	public int minArea(char[][] iImage, int x, int y) {
	    image = iImage;
	    int m = image.length, n = image[0].length;
	    int left = searchColumns(0, y, 0, m, true);
	    int right = searchColumns(y + 1, n, 0, m, false);
	    int top = searchRows(0, x, left, right, true);
	    int bottom = searchRows(x + 1, m, left, right, false);
	    return (right - left) * (bottom - top);
	}
	private int searchColumns(int i, int j, int top, int bottom, boolean opt) {
	    while (i != j) {
	        int k = top, mid = (i + j) / 2;
	        while (k < bottom && image[k][mid] == '0') ++k;
	        if (k < bottom == opt)
	            j = mid;
	        else
	            i = mid + 1;
	    }
	    return i;
	}
	private int searchRows(int i, int j, int left, int right, boolean opt) {
	    while (i != j) {
	        int k = left, mid = (i + j) / 2;
	        while (k < right && image[mid][k] == '0') ++k;
	        if (k < right == opt)
	            j = mid;
	        else
	            i = mid + 1;
	    }
	    return i;
	}
	
	
	public int minAreaDFS (char[][] image, int x, int y) {
        int column = image.length; // vertical
        if (column == 0) return 0;
        int row = image[0].length; // horizontal
        
        int[] res = new int[4];
        res[0] = column-1; // initial upper bound value
        res[1] = 0;        // initial bottom bound value
        res[2] = row - 1;  // initial left bound value
        res[3] = 0;        // initial right bound value
        dfs(image, x, y, res);
        return (res[1]-res[0]+1) * (res[3]-res[2]+1); // (bot - upper + 1) * (right - left + 1)
    }
    
    public void dfs(char[][] image, int x, int y, int[] res) {
        int column = image.length;
        int row = image[0].length;
        if (x < 0 || x > column-1 || y < 0 || y > row-1) return; 
        if (image[x][y] == '0') return;
        image[x][y] = '0';          // once visit, set to 0
        
        if (x < res[0]) res[0] = x; // update upper bound
        if (x > res[1]) res[1] = x; // update bottom bound
        if (y < res[2]) res[2] = y; // update left bound
        if (y > res[3]) res[3] = y; // update right bound
        
        dfs(image, x+1, y, res);
        dfs(image, x, y+1, res);
        dfs(image, x-1, y, res);
        dfs(image, x, y-1, res);
    }
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
