/**
 * 
 */
package com.chawkalla.lc.premium.graph.gotit;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

/**
 * 
 * https://leetcode.com/problems/alien-dictionary/#/description
 * 
 * There is a new alien language which uses the latin alphabet. However, the order among letters are unknown to you. You receive 
 * a list of non-empty words from the dictionary, where words are sorted lexicographically by the rules of this new language. 
 * Derive the order of letters in this language.

Example 1:
Given the following words in dictionary,

[
  "wrt",
  "wrf",
  "er",
  "ett",
  "rftt"
]
The correct order is: "wertf".

Example 2:
Given the following words in dictionary,

[
  "z",
  "x"
]
The correct order is: "zx".

Example 3:
Given the following words in dictionary,

[
  "z",
  "x",
  "z"
]
The order is invalid, so return "".

Solution:

* Just compare current word with next word, instead of comparing rest of the words
* Use bfs approach in inDegrees count. 
* In the absense of cycle, the count of characters collected(inDegrees=0) should be equal to total characters present


 *
 */
public class AlienDictionary {

	public String alienOrder(String[] words) {
	    Map<Character, Set<Character>> map=new HashMap<Character, Set<Character>>();
	    int[] inDegrees=new int[256];
	    Set<Character> uniques=new HashSet<Character>();
	    String result="";
	    if(words==null || words.length==0) return result;
	    
	    for(int i=0; i<words.length-1; i++){
	        String cur=words[i];
	        String next=words[i+1];
	        int length=Math.min(cur.length(), next.length());
	        for(int j=0; j<length; j++){
	            char c1=cur.charAt(j);
	            char c2=next.charAt(j);
	            uniques.add(c1);uniques.add(c2);
	            if(c1!=c2){
	            	map.putIfAbsent(c1, new HashSet<Character>());
	                if(!map.get(c1).contains(c2)){
	                	map.get(c1).add(c2);
	                    inDegrees[c2]++;
	                }
	                break;
	            }
	        }
	    }
	    Queue<Character> q=new LinkedList<Character>();
	    for(char c: uniques)
	        if(inDegrees[c]==0) q.add(c);
	    
	    while(!q.isEmpty()){
	        char c=q.remove();
	        result+=c;
	        if(map.containsKey(c))
	            for(char c2: map.get(c))
	            	if(--inDegrees[c2]==0) q.add(c2);         
	        
	    }
	    if(result.length()!=uniques.size()) return "";
	    return result;
	}
	public static void main(String[] args) {
		assertThat(new AlienDictionary().alienOrder(new String[]{"z",
				  "x",
				  "z",}), is(""));
		assertThat(new AlienDictionary().alienOrder(new String[]{"wrt",
				  "wrf",
				  "er",
				  "ett",
				  "rftt"}), is("wertf"));
		
		
		System.out.println("all cases passed");

	}

}
