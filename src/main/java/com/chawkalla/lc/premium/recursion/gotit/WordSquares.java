/**
 * 
 */
package com.chawkalla.lc.premium.recursion.gotit;

import java.util.ArrayList;
import java.util.List;

/**
 * https://leetcode.com/problems/word-squares/#/description
 * 
 * Given a set of words (without duplicates), find all word squares you can build from them.

A sequence of words forms a valid word square if the kth row and column read the exact same string, where 
0 ≤ k < max(numRows, numColumns).

For example, the word sequence ["ball","area","lead","lady"] forms a word square because each word reads the 
same both horizontally and vertically.

b a l l
a r e a
l e a d
l a d y
Note:
There are at least 1 and at most 1000 words.
All words will have the exact same length.
Word length is at least 1 and at most 5.
Each word contains only lowercase English alphabet a-z.
Example 1:

Input:
["area","lead","wall","lady","ball"]

Output:
[
  [ "wall",
    "area",
    "lead",
    "lady"
  ],
  [ "ball",
    "area",
    "lead",
    "lady"
  ]
]

Explanation:
The output consists of two word squares. The order of output does not matter (just the order of words in each word
 square matters).
Example 2:

Input:
["abat","baba","atan","atal"]

Output:
[
  [ "baba",
    "abat",
    "baba",
    "atan"
  ],
  [ "baba",
    "abat",
    "baba",
    "atal"
  ]
]

Explanation:
The output consists of two word squares. The order of output does not matter (just the order of words in each word 
square matters).
Hide Company Tags Google
Hide Tags



Solution:
By considering the word squares as a symmetric matrix, my idea is to go through the top right triangular matrix 
in left-to-right and then down order.
For example, with the case of ["area","lead","wall","lady","ball"] where length = 4,
we start with 4 empty string
""
""
""
""
Next, [0,0] , "a","b", "l", "w" can be placed, we start with "a"
"a"
""
""
""
[0,1] go right, "r" can be placed after "a", but no words start with "r" at [1,0], so this DFS ends.
"ar"
""
""
""
Now, start with "b" at [0,0]
"b"
""
""
""
We can have "ba" at [0,1] and there is a word start with "a"
"ba"
"a"
""
""
Next
"bal"
"a"
"l"
""
Next
"ball"
"a"
"l"
"l"
When finish the first row, go down to next row and start at [1,1]
"ball"
"ar"
"l"
"l"
..... so on and so forth until reaching [4,4]

 *
 */
public class WordSquares {

	class Node{
        Node[] nodes;
        String word;
        Node(){
            this.nodes = new Node[26];
            this.word = null;
        }
    }
    void add(Node root, String word){
        Node node = root;
        for (char c : word.toCharArray() ) {
            int idx = c-'a';
            if (node.nodes[idx] == null) node.nodes[idx] = new Node();
            node = node.nodes[idx];
        }
        node.word = word;
    }
    void helper(int row, int col, int len, Node[] rows, List<List<String>> ret) {
        if ( (col == row) && (row == len) ) { // last char
            List<String> res = new ArrayList<String>();
            for (int i=0; i<len; i++) {
                res.add(new String(rows[i].word) );
            }
            ret.add( res );
        } else { // from left to right and then go down to the next row
            if ( col < len  ) { // left to right first
                Node pre_row = rows[row];
                Node pre_col = rows[col];
                for (int i=0; i<26; i++) { // find all the possible next char
                    if ( (rows[row].nodes[i] != null) && (rows[col].nodes[i] != null) ) {
                        rows[row] = rows[row].nodes[i];
                        if (col != row) rows[col] = rows[col].nodes[i];
                        helper(row, col+1, len, rows, ret);
                        rows[row] = pre_row;
                        if (col != row) rows[col] = pre_col;
                    }
                }
            } else { // reach the end of column, go to the next row
                helper(row+1, row+1, len, rows, ret);
            }
        }
    }
    public List<List<String>> wordSquares(String[] words) {
        List<List<String>> ret = new ArrayList();
        if (words==null || words.length==0) return ret;
        Node root = new Node();
        int len = words[0].length();
        for (String word: words) add(root, word);
        Node[] rows = new Node[len];
        for (int i=0; i<len; i++) rows[i]=root;
        helper(0, 0, len, rows, ret);
        return ret;
    }
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
