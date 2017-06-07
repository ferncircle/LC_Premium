/**
 * 
 */
package com.chawkalla.lc.premium.tree;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

/**
 * https://leetcode.com/problems/binary-tree-vertical-order-traversal/#/description
 * 
 * Given a binary tree, return the vertical order traversal of its nodes' values. (ie, from top to bottom, column by column).

If two nodes are in the same row and column, the order should be from left to right.

Examples:

Given binary tree [3,9,20,null,null,15,7],
   3
  /\
 /  \
 9  20
    /\
   /  \
  15   7
return its vertical order traversal as:
[
  [9],
  [3,15],
  [20],
  [7]
]
Given binary tree [3,9,8,4,0,1,7],
     3
    /\
   /  \
   9   8
  /\  /\
 /  \/  \
 4  01   7
return its vertical order traversal as:
[
  [4],
  [9],
  [3,0,1],
  [8],
  [7]
]
Given binary tree [3,9,8,4,0,1,7,null,null,null,2,5] (0's right child is 2 and 1's left child is 5),
     3
    /\
   /  \
   9   8
  /\  /\
 /  \/  \
 4  01   7
    /\
   /  \
   5   2
return its vertical order traversal as:
[
  [4],
  [9,5],
  [3,0,1],
  [8,2],
  [7]
]
Hide Company Tags Google Snapchat Facebook
Hide Tags










Solution:(See image)
he following solution takes 5ms.

BFS, put node, col into queue at the same time
Every left child access col - 1 while right child col + 1
This maps node into different col buckets
Get col boundary min and max on the fly
Retrieve result from cols
Note that TreeMap version takes 9ms.

Here is an example of [1,2,3,4,5,6,7,8,9,10,11,12,13,14,15]. Notice that every child access changes one column bucket id. So 12 actually goes ahead of 11.

vertical by yavinci on 500px.com

Alternatively, we can calculate the rang first, then insert into buckets
 *
 */
public class TreeVerticalOrderTraversal {

	public List<List<Integer>> verticalOrder(TreeNode root) {
	    List<List<Integer>> res = new ArrayList<>();
	    if (root == null) {
	        return res;
	    }
	    
	    Map<Integer, ArrayList<Integer>> map = new HashMap<>();
	    Queue<TreeNode> q = new LinkedList<>();
	    Queue<Integer> cols = new LinkedList<>();

	    q.add(root); 
	    cols.add(0);

	    int min = 0;
	    int max = 0;
	    
	    while (!q.isEmpty()) {
	        TreeNode node = q.poll();
	        int col = cols.poll();
	        
	        if (!map.containsKey(col)) {
	            map.put(col, new ArrayList<Integer>());
	        }
	        map.get(col).add(node.val);

	        if (node.left != null) {
	            q.add(node.left); 
	            cols.add(col - 1);
	            min = Math.min(min, col - 1);
	        }
	        
	        if (node.right != null) {
	            q.add(node.right);
	            cols.add(col + 1);
	            max = Math.max(max, col + 1);
	        }
	    }

	    for (int i = min; i <= max; i++) {
	        res.add(map.get(i));
	    }

	    return res;
	}
	
	
	public List<List<Integer>> verticalOrder1(TreeNode root) {
	    List<List<Integer>> cols = new ArrayList<>();
	    if (root == null) {
	        return cols;
	    }
	    
	    int[] range = new int[] {0, 0};
	    getRange(root, range, 0);
	    
	    for (int i = range[0]; i <= range[1]; i++) {
	        cols.add(new ArrayList<Integer>());
	    }
	    
	    Queue<TreeNode> queue = new LinkedList<>();
	    Queue<Integer> colQueue = new LinkedList<>();
	    
	    queue.add(root);
	    colQueue.add(-range[0]);
	    
	    while (!queue.isEmpty()) {
	        TreeNode node = queue.poll();
	        int col = colQueue.poll();
	        
	        cols.get(col).add(node.val);
	        
	        if (node.left != null) {
	            queue.add(node.left);   
	            colQueue.add(col - 1);
	        } 
	        if (node.right != null) {
	            queue.add(node.right);
	            colQueue.add(col + 1);
	        }
	    }
	    
	    return cols;
	}

	public void getRange(TreeNode root, int[] range, int col) {
	    if (root == null) {
	        return;
	    }
	    range[0] = Math.min(range[0], col);
	    range[1] = Math.max(range[1], col);
	    
	    getRange(root.left, range, col - 1);
	    getRange(root.right, range, col + 1);
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
