/**
 * 
 */
package com.chawkalla.lc.premium.string;

/**
 * https://leetcode.com/problems/reverse-words-in-a-string-ii/#/description
 * 
 * Given an input string, reverse the string word by word. A word is defined as a sequence of non-space characters.

The input string does not contain leading or trailing spaces and the words are always separated by a single space.

For example,
Given s = "the sky is blue",
return "blue is sky the".

Could you do it in-place without allocating extra space?

Related problem: Rotate Array

Hide Company Tags Amazon Microsoft Uber
Hide Tags


 */
public class ReverseWordsInString2 {

	public void reverseWords(char[] s) {
	    // Three step to reverse
	    // 1, reverse the whole sentence
	    reverse(s, 0, s.length - 1);
	    // 2, reverse each word
	    int start = 0;
	    int end = -1;
	    for (int i = 0; i < s.length; i++) {
	        if (s[i] == ' ') {
	            reverse(s, start, i - 1);
	            start = i + 1;
	        }
	    }
	    // 3, reverse the last word, if there is only one word this will solve the corner case
	    reverse(s, start, s.length - 1);
	}

	public void reverse(char[] s, int start, int end) {
	    while (start < end) {
	        char temp = s[start];
	        s[start] = s[end];
	        s[end] = temp;
	        start++;
	        end--;
	    }
	}
	
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
