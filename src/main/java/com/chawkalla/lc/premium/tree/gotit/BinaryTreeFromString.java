/**
 * 
 */
package com.chawkalla.lc.premium.tree.gotit;

import com.chawkalla.lc.premium.tree.TreeNode;

/**
 *https://leetcode.com/problems/construct-binary-tree-from-string/#/description
 *
 *You need to construct a binary tree from a string consisting of parenthesis and integers.

The whole input represents a binary tree. It contains an integer followed by zero, one or two pairs of parenthesis.
 The integer represents the root's value and a pair of parenthesis contains a child binary tree with the same structure.

You always start to construct the left child node of the parent first if it exists.

Example:
Input: "4(2(3)(1))(6(5))"
Output: return the tree root node representing the following tree:

       4
     /   \
    2     6
   / \   / 
  3   1 5   
Note:
There will only be '(', ')', '-' and '0' ~ '9' in the input string.
An empty tree is represented by "" instead of "()".
Hide Company Tags Amazon
Hide Tags



 *
 */
public class BinaryTreeFromString {

	public TreeNode str2tree(String s) {
        // Base case
        if (s.length() == 0) return null;
        
        // Create root
        int i = 0, j = 0;
        while (j < s.length() && (Character.isDigit(s.charAt(j)) || s.charAt(j) == '-')) j++;
        TreeNode root = new TreeNode(Integer.parseInt(s.substring(i, j)));
        
        // Left child
        if (j < s.length()) {
            i = j;
            int count = 1;
            while (j + 1 < s.length() && count != 0) {
                j++;
                if (s.charAt(j) == ')') count--;
                if (s.charAt(j) == '(') count++;
            }
            root.left = str2tree(s.substring(i + 1, j));
        }
        
        j++;
        // Right child
        if (j < s.length()) {
            root.right = str2tree(s.substring(j + 1, s.length() - 1));
        }
        
        return root;
    }
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
