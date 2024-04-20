package time_complexity;

import java.io.*;
import java.util.StringTokenizer;

public class Back2003_2 {

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		long M = Long.parseLong(st.nextToken());
		
		int[] arr = new int[N + 2];
		st = new StringTokenizer(br.readLine());
		for(int i = 1 ; i <= N ; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		int l = 0;
		int r = 1;
		int count = 0;
		long sum = arr[r];
		while(r <= N) {
			if(sum < M) {
				r++;
				sum += arr[r];
			} else {
				
				if(sum == M) {
					count++;
					//System.out.println("l " + l + ", r " + r);
				}
				
				l++;
				sum -= arr[l];
			}
		}
		
		System.out.println(count);
	}

}
