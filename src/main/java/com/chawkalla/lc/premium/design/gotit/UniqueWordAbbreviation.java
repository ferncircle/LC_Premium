/**
 * 
 */
package com.chawkalla.lc.premium.design.gotit;

import java.util.HashMap;

/**
 * https://leetcode.com/problems/unique-word-abbreviation/#/description
 * 
 * An abbreviation of a word follows the form <first letter><number><last letter>. Below are some examples of word 
 * abbreviations:

a) it                      --> it    (no abbreviation)

     1
b) d|o|g                   --> d1g

              1    1  1
     1---5----0----5--8
c) i|nternationalizatio|n  --> i18n

              1
     1---5----0
d) l|ocalizatio|n          --> l10n
Assume you have a dictionary and given a word, find whether its abbreviation is unique in the dictionary. A word's abbreviation is 
unique if no other word from the dictionary has the same abbreviation.

Example: 
Given dictionary = [ "deer", "door", "cake", "card" ]

isUnique("dear") -> 
false

isUnique("cart") -> 
true

isUnique("cane") -> 
false

isUnique("make") -> 
true

Hide Company Tags Google
Hide Tags


Solution:

The description (A word's abbreviation is unique if no other word from the dictionary has the same abbreviation) is clear 
however a bit twisting. It took me a few "Wrong Answer"s to finally understand what it's asking for.
We are trying to search for a word in a dictionary. If this word (also this word’s abbreviation) is not in the dictionary OR 
this word and only it’s abbreviation in the dictionary. We call a word’s abbreviation unique.
EX:

1) [“dog”]; isUnique(“dig”);   
//False, because “dig” has the same abbreviation with “dog" and “dog” is already in the dictionary. It’s not unique.

2) [“dog”, “dog"]; isUnique(“dog”);  
//True, because “dog” is the only word that has “d1g” abbreviation.

3) [“dog”, “dig”]; isUnique(“dog”);   
//False, because if we have more than one word match to the same abbreviation, this abbreviation will never be unique.
 *
 */
public class UniqueWordAbbreviation {

	HashMap<String, String> map;
    public void ValidWordAbbr(String[] dictionary) {
        map = new HashMap<String, String>();
        for(String str:dictionary){
            String key = getKey(str);
            // If there is more than one string belong to the same key
            // then the key will be invalid, we set the value to ""
            if(map.containsKey(key)){
                if(!map.get(key).equals(str)){
                    map.put(key, "");
                }
            }
            else{
                map.put(key, str);
            }
        }
    }

    public boolean isUnique(String word) {
        return !map.containsKey(getKey(word))||map.get(getKey(word)).equals(word);
    }
    
    String getKey(String str){
        if(str.length()<=2) return str;
        return str.charAt(0)+Integer.toString(str.length()-2)+str.charAt(str.length()-1);
    }
    
    
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
