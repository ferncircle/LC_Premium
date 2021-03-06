/**
 * 
 */
package com.chawkalla.lc.premium.design.gotit;

import java.util.HashMap;
import java.util.Map;

/**https://leetcode.com/problems/logger-rate-limiter/#/description
 *
 *Design a logger system that receive stream of messages along with its timestamps, each message should be printed if and 
 *only if it is not printed in the last 10 seconds.

Given a message and a timestamp (in seconds granularity), return true if the message should be printed in the given timestamp,
 otherwise returns false.

It is possible that several messages arrive roughly at the same time.

Example:

Logger logger = new Logger();

// logging string "foo" at timestamp 1
logger.shouldPrintMessage(1, "foo"); returns true; 

// logging string "bar" at timestamp 2
logger.shouldPrintMessage(2,"bar"); returns true;

// logging string "foo" at timestamp 3
logger.shouldPrintMessage(3,"foo"); returns false;

// logging string "bar" at timestamp 8
logger.shouldPrintMessage(8,"bar"); returns false;

// logging string "foo" at timestamp 10
logger.shouldPrintMessage(10,"foo"); returns false;

// logging string "foo" at timestamp 11
logger.shouldPrintMessage(11,"foo"); returns true;
Credits:
Special thanks to @memoryless for adding this problem and creating all test cases.

Hide Company Tags Google
Hide Tags
 *
 *
 *Solution: Instead of logging print times, I store when it's ok for a message to be printed again. Should be slightly faster, because I don't always have to add or subtract (e.g., timestamp < log[message] + 10) but only do in the true case. Also, it leads to a shorter/simpler longest line of code. 
 */
public class LoggerRateLimiter {

	private Map<String, Integer> ok = new HashMap<>();

    public boolean shouldPrintMessage(int timestamp, String message) {
        if (timestamp < ok.getOrDefault(message, 0))
            return false;
        ok.put(message, timestamp + 10);
        return true;
    }
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
