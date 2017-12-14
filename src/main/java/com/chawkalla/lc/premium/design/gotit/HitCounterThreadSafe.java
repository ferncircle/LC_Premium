/**
 * 
 */
package com.chawkalla.lc.premium.design.gotit;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.atomic.AtomicLongArray;

/**
 * 
 * https://leetcode.com/problems/design-hit-counter/#/description
 * 
 * Design a hit counter which counts the number of hits received in the past 5 minutes.

Each function accepts a timestamp parameter (in seconds granularity) and you may assume that calls are being made to the 
system in chronological order (ie, the timestamp is monotonically increasing). You may assume that the earliest timestamp 
starts at 1.

It is possible that several hits arrive roughly at the same time.

Example:
HitCounter counter = new HitCounter();

// hit at timestamp 1.
counter.hit(1);

// hit at timestamp 2.
counter.hit(2);

// hit at timestamp 3.
counter.hit(3);

// get hits at timestamp 4, should return 3.
counter.getHits(4);

// hit at timestamp 300.
counter.hit(300);

// get hits at timestamp 300, should return 4.
counter.getHits(300);

// get hits at timestamp 301, should return 3.
counter.getHits(301); 
Follow up:
What if the number of hits per second could be very large? Does your design scale?

Credits:
Special thanks to @elmirap for adding this problem and creating all test cases.

Hide Company Tags Dropbox Google
Hide Tags

Solution 1:
O(s) s is total seconds in given time interval, in this case 300.
basic ideal is using buckets. 1 bucket for every second because we only need to keep the recent hits info for 300 seconds. 
hit[] array is wrapped around by mod operation. Each hit bucket is associated with times[] bucket which record current time. 
If it is not current time, it means it is 300s or 600s... ago and need to reset to 1.

Solution 2:
I use a queue to record the information of all the hits. Each time we call the function getHits( ), we have to delete the 
elements which hits beyond 5 mins (300). The result would be the length of the queue : )
 *
 */
public class HitCounterThreadSafe {
    private AtomicLongArray times;
    private AtomicLongArray hits;    
    
    /** Initialize your data structure here. */
    public HitCounterThreadSafe() {
        times = new AtomicLongArray(300);
        hits = new AtomicLongArray(300);
    }
    
    /** Record a hit.
        @param timestamp - The current timestamp (in seconds granularity). */
    public void hit(int timestamp) {
        int index = timestamp % 300;
        long currentTime=times.get(index);
        long currentHits=hits.get(index);
        if ( currentTime!= timestamp) {
            times.compareAndSet(index, currentTime, timestamp);
            hits.compareAndSet(index, currentHits, 1);
        } else {
            hits.incrementAndGet(index);
        }
    }
    
    /** Return the number of hits in the past 5 minutes.
        @param timestamp - The current timestamp (in seconds granularity). */
    public int getHits(int timestamp) {
        int total = 0;
        for (int i = 0; i < 300; i++) {
            if (timestamp - times.get(i) < 300) {
                total += hits.get(i);
            }
        }
        return total;
    }
    
	public static void main(String[] args) {
		
		HitCounterThreadSafe hc=new HitCounterThreadSafe();
		
		hc.hit(1);
		hc.hit(1);
		hc.hit(1);
		hc.hit(2);
		hc.hit(2);

		assertThat(hc.getHits(4), is(5));
		System.out.println("all cases passed");
	}

}
