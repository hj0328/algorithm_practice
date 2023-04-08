package two_pointer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Baek2003 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
			
		int N = Integer.parseInt(st.nextToken());
		long M = Long.parseLong(st.nextToken());
		
		int[] arr = new int[N];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		// left 커서 
		// l은 연속 구간 제일 왼쪽 칸보다 왼쪽 칸
		int l = -1;
		
		// right 커서
		// r은 연속 구간 제일 오른쪽 칸
		int r = 0;
		long sum = arr[0];
		int count = 0;
		while(l <= r && r < N) {
			if(sum >= M) {
				if(sum == M) {
					count++;
				}
			
				l++;
				sum -= arr[l];
			} else {
				r++;
				if(r == N) {
					break;
				}
				sum += arr[r];

			}
		}
		
		System.out.println(count);
	}

}
