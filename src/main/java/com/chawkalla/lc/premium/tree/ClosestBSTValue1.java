/**
 * 
 */
package com.chawkalla.lc.premium.tree;

/**
 * https://leetcode.com/problems/closest-binary-search-tree-value/#/description

Given a non-empty binary search tree and a target value, find the value in the BST that is closest to the target.

Note:
Given target value is a floating point.
You are guaranteed to have only one unique value in the BST that is closest to the target.
Hide Company Tags Microsoft Google Snapchat
Hide Tags
 *
 */
public class ClosestBSTValue1 {

	public int closestValue(TreeNode root, double target) {
	    int a = root.val;
	    TreeNode kid = target < a ? root.left : root.right;
	    if (kid == null) return a;
	    int b = closestValue(kid, target);
	    return Math.abs(a - target) < Math.abs(b - target) ? a : b;
	}
	
	public int closestValueIterative(TreeNode root, double target) {
	    int ret = root.val;   
	    while(root != null){
	        if(Math.abs(target - root.val) < Math.abs(target - ret)){
	            ret = root.val;
	        }      
	        root = root.val > target? root.left: root.right;
	    }     
	    return ret;
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
