/**
 * 
 */
package com.chawkalla.lc.premium.string;

/**
 * https://leetcode.com/problems/longest-substring-with-at-most-k-distinct-characters/#/description
 * 
 * Given a string, find the length of the longest substring T that contains at most k distinct characters.

For example, Given s = “eceba” and k = 2,

T is "ece" which its length is 3.


 *
 */
public class LongestSubstringAtMostKDistinctCharacters {

	public int lengthOfLongestSubstringKDistinct(String s, int k) {
		int[] count = new int[256];
		int num = 0, i = 0, res = 0;
		for (int j = 0; j < s.length(); j++) {
			if (count[s.charAt(j)]++ == 0) num++;
			if (num > k) {
				while (--count[s.charAt(i++)] > 0);
				num--;
			}
			res = Math.max(res, j - i + 1);
		}
		return res;
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
