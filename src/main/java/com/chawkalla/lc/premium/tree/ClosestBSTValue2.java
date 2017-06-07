/**
 * 
 */
package com.chawkalla.lc.premium.tree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

/**
 * 
 * https://leetcode.com/problems/closest-binary-search-tree-value-ii/#/description
 * Given a non-empty binary search tree and a target value, find k values in the BST that are closest to the target.

Note:
Given target value is a floating point.
You may assume k is always valid, that is: k ≤ total nodes.
You are guaranteed to have only one unique set of k values in the BST that are closest to the target.
Follow up:
Assume that the BST is balanced, could you solve it in less than O(n) runtime (where n = total nodes)?

Hide Company Tags Google
Hide Tags

Solution 1: Two stacks
The idea is to compare the predecessors and successors of the closest node to the target, we can use two stacks to track the predecessors and successors, then like what we do in merge sort, we compare and pick the closest one to the target and put it to the result list.

As we know, inorder traversal gives us sorted predecessors, whereas reverse-inorder traversal gives us sorted successors.

We can use iterative inorder traversal rather than recursion, but to keep the code clean, here is the recursion version.



Solution 2: Inorder traversal

 */
public class ClosestBSTValue2 {


	public List<Integer> closestKValues(TreeNode root, double target, int k) {
		List<Integer> res = new ArrayList<>();

		Stack<Integer> s1 = new Stack<>(); // predecessors
		Stack<Integer> s2 = new Stack<>(); // successors

		inorder(root, target, false, s1);
		inorder(root, target, true, s2);

		while (k-- > 0) {
			if (s1.isEmpty())
				res.add(s2.pop());
			else if (s2.isEmpty())
				res.add(s1.pop());
			else if (Math.abs(s1.peek() - target) < Math.abs(s2.peek() - target))
				res.add(s1.pop());
			else
				res.add(s2.pop());
		}

		return res;
	}

	// inorder traversal
	void inorder(TreeNode root, double target, boolean reverse, Stack<Integer> stack) {
		if (root == null) return;

		inorder(reverse ? root.right : root.left, target, reverse, stack);
		// early terminate, no need to traverse the whole tree
		if ((reverse && root.val <= target) || (!reverse && root.val > target)) return;
		// track the value of current node
		stack.push(root.val);
		inorder(reverse ? root.left : root.right, target, reverse, stack);
	}


	public List<Integer> closestKValuesInOrder(TreeNode root, double target, int k) {
		LinkedList<Integer> list = new LinkedList<Integer>();
		closestKValuesHelper(list, root, target, k);
		return list;
	}

	/**
	 * @return <code>true</code> if result is already found.
	 */
	private boolean closestKValuesHelper(LinkedList<Integer> list, TreeNode root, double target, int k) {
		if (root == null) {
			return false;
		}

		if (closestKValuesHelper(list, root.left, target, k)) {
			return true;
		}

		if (list.size() == k) {
			if (Math.abs(list.getFirst() - target) < Math.abs(root.val - target)) {
				return true;
			} else {
				list.removeFirst();
			}
		}

		list.addLast(root.val);
		return closestKValuesHelper(list, root.right, target, k);
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
