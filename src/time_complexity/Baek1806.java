package time_complexity;

import java.util.*;
import java.io.*;

public class Baek1806 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int S = Integer.parseInt(st.nextToken());
		
		int[] arr = new int[N+2];
		
		st = new StringTokenizer(br.readLine());
		for(int i = 1 ; i <= N ; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		int l = 0;
		int r = 1;
		int sum = arr[r];
		int minLen = Integer.MAX_VALUE;
		while(r <= N) {
			if(sum < S) {
				r++;
				sum += arr[r];
			} else {
				
				if(minLen > r - l) {
					minLen = r - l;
				}
				
				l++;
				sum -= arr[l];
			}
		}
		
		if(minLen == Integer.MAX_VALUE) {
			System.out.println(0);
		} else {
			System.out.println(minLen);
		}
	}

}
