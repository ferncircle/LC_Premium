/**
 * 
 */
package com.chawkalla.lc.premium.recursion;

/**
 * https://leetcode.com/problems/flip-game-ii/#/description
 *  You are playing the following Flip Game with your friend: Given a string that contains only these two characters: + and -, you and your friend take turns to flip two consecutive "++" into "--". The game ends when a person can no longer make a move and therefore the other person will be the winner.

Write a function to determine if the starting player can guarantee a win.

For example, given s = "++++", return true. The starting player can guarantee a win by flipping the middle "++" to become "+--+".

Follow up:
Derive your algorithm's runtime complexity.

Hide Company Tags Google
Hide Tags



Solution:

The idea is try to replace every "++" in the current string s to "--" and see if the opponent can win or not, if the opponent cannot win, great, we win!

For the time complexity, here is what I thought, let's say the length of the input string s is n, there are at most n - 1 ways to replace "++" to "--" (imagine s is all "+++..."), once we replace one "++", there are at most (n - 2) - 1 ways to do the replacement, it's a little bit like solving the N-Queens problem, the time complexity is (n - 1) x (n - 3) x (n - 5) x ..., so it's O(n!!), double factorial.


Solution 2:

At first glance, backtracking seems to be the only feasible solution to this problem. We can basically try every possible move for the first player (Let's call him 1P from now on), and recursively check if the second player 2P has any chance to win. If 2P is guaranteed to lose, then we know the current move 1P takes must be the winning move. The naive implementation is actually very simple:
Now let's check the time complexity: Suppose originally the board of size N contains only '+' signs, then roughly we have:

T(N) = (N-2) * T(N-2) = (N-2) * (N-4) * T(N-4) ... = (N-2) * (N-4) * (N-6) * ... ~ O(N!!)
Can we even do better than that? Sure! Below I'll show the time complexity can be reduced to O(N^2) using Dynamic Programming, but the improved method requires some non-trivial understanding of the game theory, and therefore is not expected in a real interview. If you are not interested, please simply skip the rest of the article:
 */
public class FlippingGame2 {

	public boolean canWin(String s) {
		  if (s == null || s.length() < 2) {
		    return false;
		  }
		    
		  for (int i = 0; i < s.length() - 1; i++) {
		    if (s.startsWith("++", i)) {
		      String t = s.substring(0, i) + "--" + s.substring(i + 2);
		      
		      if (!canWin(t)) {
		        return true;
		      }
		    }
		  }
		    
		  return false;
		}
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
