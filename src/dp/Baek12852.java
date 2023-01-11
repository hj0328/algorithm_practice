package dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/*
 * 1로 만들기 2 
 * 
 * 	기본 dp 문제에서 dp 과정을 함께 출력하는 것이 목표 
 * 	어떻게든 풀었지만 하나하나 총 과정을 기록하는게 아니었다. 
 */
public class Baek12852 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int N = Integer.parseInt(br.readLine());

		int[] dp = new int[N + 3];

		StringBuilder[] r = new StringBuilder[N + 3];
		for (int i = 0; i <= N+2; i++) {
			r[i] = new StringBuilder();
		}

		dp[1] = 0;
		dp[2] = 1;
		dp[3] = 1;

		r[1].append(1).append(" ");
		r[2].append(2).append(" ").append(r[1]).append(" ");
		r[3].append(3).append(" ").append(r[1]).append(" ");
		
		for (int i = 4; i <= N; i++) {
			int i1 = dp[i - 1];

			int i3 = Integer.MAX_VALUE;
			if (i % 3 == 0) {
				i3 = dp[i / 3];
			}

			int i2 = Integer.MAX_VALUE;
			if (i % 2 == 0) {
				i2 = dp[i / 2];
			}

			// 최소를 하는 인덱스를 구하기
			int minIdx = 0;
			if (i1 < i3) {
				minIdx = i - 1;
			} else {
				minIdx = i / 3;
			}
			
			if(dp[minIdx] > i2) {
				minIdx = i / 2;
			}
			
			dp[i] = dp[minIdx] + 1;
			r[i].append(i).append(" ").append(r[minIdx]).append(" ");
		}
		
		System.out.println(dp[N]);
		System.out.println(r[N].toString());
	}

}
