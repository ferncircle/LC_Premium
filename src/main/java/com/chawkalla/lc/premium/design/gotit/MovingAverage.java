/**
 * 
 */
package com.chawkalla.lc.premium.design.gotit;

/**
 * leetcode.com/problems/moving-average-from-data-stream/#/description
 * 
Given a stream of integers and a window size, calculate the moving average of all integers in the sliding window.

For example,
MovingAverage m = new MovingAverage(3);
m.next(1) = 1
m.next(10) = (1 + 10) / 2
m.next(3) = (1 + 10 + 3) / 3
m.next(5) = (10 + 3 + 5) / 3
Hide Company Tags Google
Hide Tags *











Solution:
The idea is to keep the sum so far and update the sum just by replacing the oldest number with the new entry.
 */
public class MovingAverage {

	private int [] window;
    private int n, insert;
    private long sum;
    
    /** Initialize your data structure here. */
    public MovingAverage(int size) {
        window = new int[size];
        insert = 0;
        sum = 0;
    }
    
    public double next(int val) {
        if (n < window.length)  n++;
        sum -= window[insert];
        sum += val;
        window[insert] = val;
        insert = (insert + 1) % window.length;
        
        return (double)sum / n;
    }
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
