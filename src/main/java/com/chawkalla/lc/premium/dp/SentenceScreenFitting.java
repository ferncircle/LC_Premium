/**
 * 
 */
package com.chawkalla.lc.premium.dp;

import java.util.stream.IntStream;

/**
 * https://leetcode.com/problems/sentence-screen-fitting/#/description
 * 
 * Given a rows x cols screen and a sentence represented by a list of non-empty words, find how many times the given sentence 
 * can be fitted on the screen.

Note:

A word cannot be split into two lines.
The order of words in the sentence must remain unchanged.
Two consecutive words in a line must be separated by a single space.
Total words in the sentence won't exceed 100.
Length of each word is greater than 0 and won't exceed 10.
1 ≤ rows, cols ≤ 20,000.
Example 1:

Input:
rows = 2, cols = 8, sentence = ["hello", "world"]

Output: 
1

Explanation:
hello---
world---

The character '-' signifies an empty space on the screen.
Example 2:

Input:
rows = 3, cols = 6, sentence = ["a", "bcd", "e"]

Output: 
2

Explanation:
a-bcd- 
e-a---
bcd-e-

The character '-' signifies an empty space on the screen.
Example 3:

Input:
rows = 4, cols = 5, sentence = ["I", "had", "apple", "pie"]

Output: 
1

Explanation:
I-had
apple
pie-I
had--

The character '-' signifies an empty space on the screen.
 *
 *Google
 *
 *
 *Solution:
 *
 *Say sentence=["abc", "de", "f], rows=4, and cols=6.
The screen should look like

"abc de"
"f abc "
"de f  "
"abc de"
Consider the following repeating sentence string, with positions of the start character of each row on the screen.

"abc de f abc de f abc de f ..."
 ^      ^     ^    ^      ^
 0      7     13   18     25
Our goal is to find the start position of the row next to the last row on the screen, which is 25 here. Since actually it's the 
length of everything earlier, we can get the answer by dividing this number by the length of (non-repeated) sentence string. 
Note that the non-repeated sentence string has a space at the end; it is "abc de f " in this example.

Here is how we find that position. In each iteration, we need to adjust start based on spaces either added or removed.

"abc de f abc de f abc de f ..." // start=0
 012345                          // start=start+cols+adjustment=0+6+1=7 (1 space removed in screen string)
        012345                   // start=7+6+0=13
              012345             // start=13+6-1=18 (1 space added)
                   012345        // start=18+6+1=25 (1 space added)
                          012345
                          
 
 Solution 2:
 First off, we can easily come up with a brute-force solution. The basic idea of optimized solution is that

    sub-problem: if there's a new line which is starting with certain index in sentence, what is the starting index of next line 
    (nextIndex[]). BTW, we compute how many times the pointer in current line passes over the last index (times[]).
    relation : ans += times[i], i = nextIndex[i], for _ in 0..<row. where i indicates what is the first word in the current line.

Time complexity : O(n*(cols/lenAverage)) + O(rows), where n is the length of sentence array, lenAverage is the average length of 
the words in the input array.



 *
 *
 */
public class SentenceScreenFitting {

	public int wordsTyping(String[] sentence, int rows, int cols) {
        String s = String.join(" ", sentence) + " ";
        int start = 0, l = s.length();
        for (int i = 0; i < rows; i++) {
            start += cols;
            if (s.charAt(start % l) == ' ') {
                start++;	
            } else {
                while (start > 0 && s.charAt((start-1) % l) != ' ') {
                    start--;
                }
            }
        }
        
        return start / s.length();
    }
	
	public int wordsTypingOptimized(String[] sentence, int rows, int cols) {
        String s = String.join(" ", sentence) + " ";
        int[] offset = new int[s.length()];
        IntStream.range(1, s.length()).forEach(i -> offset[i] = s.charAt(i) == ' ' ? 1 : offset[i-1]-1);
        return IntStream.range(0, rows).reduce(0, (a, b) -> a + cols + offset[(a+cols) % s.length()]) / s.length();
    }
	
	public int wordsTypingDP(String[] sentence, int rows, int cols) {
        int[] nextIndex = new int[sentence.length];
        int[] times = new int[sentence.length];
        for(int i=0;i<sentence.length;i++) {
            int curLen = 0;
            int cur = i;
            int time = 0;
            while(curLen + sentence[cur].length() <= cols) {
                curLen += sentence[cur++].length()+1;
                if(cur==sentence.length) {
                    cur = 0;
                    time ++;
                }
            }
            nextIndex[i] = cur;
            times[i] = time;
        }
        int ans = 0;
        int cur = 0;
        for(int i=0; i<rows; i++) {
            ans += times[cur];
            cur = nextIndex[cur];
        }
        return ans;
    }
	public static void main(String[] args) {

		System.out.println(new SentenceScreenFitting().wordsTypingDP(new String[]{
				"abc", "de", "f"
		}, 4, 6));

	}

}
