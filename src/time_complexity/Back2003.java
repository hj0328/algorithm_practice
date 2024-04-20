package time_complexity;

import java.io.*;
import java.util.*;

public class Back2003 {

	public static void main(String[] args) throws IOException {
		 
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		long M = Long.parseLong(st.nextToken());
		
		
		long[] pre = new long[N+1];
		st = new StringTokenizer(br.readLine());
		for(int i = 1 ; i <= N ; i++) {
			pre[i] = Long.parseLong(st.nextToken()) + pre[i-1];
		}
		
		int count = 0;
		for(int i = 1 ; i <= N ; i++) {
			for(int j = 1 ; j <= i ; j++) {
				long sub = pre[i] - pre[j-1];
				if(sub == M) {
					count++;
				}
			}
		}
		
		System.out.println(count);
	}

}
