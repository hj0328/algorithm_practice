package time_complexity;

import java.util.*;
import java.io.*;

public class Baek1654 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());
		
		// 랜선 개수  1 ~ 1000
		int K = Integer.parseInt(st.nextToken());
		
		// 필요한 랜선의 수 1 ~ 1,000,000, K <= N
		int N = Integer.parseInt(st.nextToken());
		
		long max = 0;
		long [] lines = new long[K];
		for(int i = 0 ; i < K ; i++) {
			long l = Long.parseLong(br.readLine());
			lines[i] = l;
			if(max  < l) {
				max = l;
			}
		}
		
		// 이진 탐색 시작
		long min = 0;
		while(min < max) {
			long mid = (min + max) / 2;
			
			// 길이 mid로 만들 수 있는 랜선 수
			long count = 0;
			for(int i = 0 ; i < K ; i++) {
				count += (lines[i] / mid);
			}
			
			if (N <= count) {
				// 상한선 내리기 = count 감소 = min 증가
				min = mid + 1;
				
			} else {
				// N > count 
				// 상한선 올리기 = count 증가 = max 감소
				max = mid;
			}
			// N == count
			// 최대를 구해야 하니 max 증가 방향 x
			// upper bound를 구해야 함 = mid를 증가 
		}
		
		System.out.println(min - 1);
		
	}

}
