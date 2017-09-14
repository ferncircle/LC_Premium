/**
 * 
 */
package com.chawkalla.lc.premium.recursion.gotit;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * https://leetcode.com/problems/flip-game/#/description
 * 
 *You are playing the following Flip Game with your friend: Given a string that contains only these two characters: + 
 *and -, you and your friend take turns to flip two consecutive "++" into "--". The game ends when a person can no longer 
 *make a move and therefore the other person will be the winner.

Write a function to compute all possible states of the string after one valid move.

For example, given s = "++++", after one move, it may become one of the following states:

[
  "--++",
  "+--+",
  "++--"
]
If there is no valid move, return an empty list [].

Hide Company Tags Google
Hide Tags String
Show Similar Problems



 *
 */
public class FlippingGame1 {

	public List<String> generatePossibleNextMoves(String s) {
	    List list = new ArrayList();
	    for (int i=-1; (i = s.indexOf("++", i+1)) >= 0; )
	        list.add(s.substring(0, i) + "--" + s.substring(i+2));
	    return list;
	}
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
