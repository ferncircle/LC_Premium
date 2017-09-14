/**
 * 
 */
package com.chawkalla.lc.premium.string;

/**
 * 
 * https://leetcode.com/problems/read-n-characters-given-read4-ii-call-multiple-times/#/description
 * 
 * The API: int read4(char *buf) reads 4 characters at a time from a file.

The return value is the actual number of characters read. For example, it returns 3 if there is only 3 characters left in the
 file.

By using the read4 API, implement the function int read(char *buf, int n) that reads n characters from the file.

Note:
The read function may be called multiple times.

Hide Company Tags Bloomberg Google Facebook
Hide Tags



solution:

I used buffer pointer (buffPtr) and buffer Counter (buffCnt) to store the data received in previous calls. In the while loop,
 if buffPtr reaches current buffCnt, it will be set as zero to be ready to read new data.
 *
 *The key is to store memorized variable in the class level and remember offset position and remaining number of elements.
 */
public class ReadNCharsGivenRead42 {

	private int buffPtr = 0;
	private int buffCnt = 0;
	private char[] buff = new char[4];
	public int read(char[] buf, int n) {
		int ptr = 0;
		while (ptr < n) {
			if (buffPtr == 0) {
				buffCnt = read4(buff);
			}
			while (ptr < n && buffPtr < buffCnt) {
				buf[ptr++] = buff[buffPtr++];
			}
			// all chars in buff used up, set pointer to 0
			if (buffPtr == buffCnt) buffPtr = 0;
			// read4 returns less than 4, end of file
			if (buffCnt < 4) break;
		}
		return ptr;
	}


	int read4(char[] buf){
		return 0;
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
