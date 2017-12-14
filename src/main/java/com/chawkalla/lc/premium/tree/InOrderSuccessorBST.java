/**
 * 
 */
package com.chawkalla.lc.premium.tree;

/**
 * https://leetcode.com/problems/inorder-successor-in-bst/#/description
 * 
 * Given a binary search tree and a node in it, find the in-order successor of that node in the BST.

Note: If the given node has no in-order successor in the tree, return null.

Hide Company Tags Pocket Gems Microsoft Facebook
Hide Tags


 *
 */
public class InOrderSuccessorBST {

	public TreeNode successor(TreeNode cur, TreeNode p) {
		if (cur == null)
			return null;

		if (cur.val <= p.val) {
			return successor(cur.right, p);
		} else {
			TreeNode left = successor(cur.left, p);
			return (left != null) ? left : cur;
		}
	}

	public TreeNode predecessor(TreeNode root, TreeNode p) {
		if (root == null)
			return null;

		if (root.val >= p.val) {
			return predecessor(root.left, p);
		} else {
			TreeNode right = predecessor(root.right, p);
			return (right != null) ? right : root;
		}
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
