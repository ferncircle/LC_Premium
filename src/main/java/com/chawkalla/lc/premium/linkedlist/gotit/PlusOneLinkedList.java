/**
 * 
 */
package com.chawkalla.lc.premium.linkedlist.gotit;

import com.chawkalla.lc.premium.linkedlist.ListNode;

/**
 * https://leetcode.com/problems/plus-one-linked-list/#/description
 * Given a non-negative integer represented as non-empty a singly linked list of digits, plus one to the integer.

You may assume the integer do not contain any leading zero, except the number 0 itself.

The digits are stored such that the most significant digit is at the head of the list.

Example:
Input:
1->2->3

Output:
1->2->4

 Google
Hide Tags

Solution:
i stands for the most significant digit that is going to be incremented if there exists a carry
dummy node can handle cases such as "9->9>-9" automatically
 *
 */
public class PlusOneLinkedList {

	public ListNode plusOne(ListNode head) {
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode i = dummy;
        ListNode j = dummy;
        
        while (j.next != null) {
            j = j.next;
            if (j.val != 9) {
                i = j;
            }
        }
        
        if (j.val != 9) {
            j.val++;
        } else {
            i.val++;
            i = i.next;
            while (i != null) {
                i.val = 0;
                i = i.next;
            }
        }
        
        if (dummy.val == 0) {
            return dummy.next;
        }
        
        return dummy;
    }
	
	public ListNode plusOneRecursion(ListNode head) {
	    if( DFS(head) == 0){
	        return head;
	    }else{
	        ListNode newHead = new ListNode(1);
	        newHead.next = head;
	        return newHead;
	    }
	}

	public int DFS(ListNode head){
	    if(head == null) return 1;
	    
	    int carry = DFS(head.next);
	    
	    if(carry == 0) return 0;
	    
	    int val = head.val + 1;
	    head.val = val%10;
	    return val/10;
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
