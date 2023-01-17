package dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/*
 * 	쉬운 계단 수 
 * 	https://www.acmicpc.net/problem/10844
 * 
 * 	2차원 dp 풀이 
 * 	dp[i][num] 정의: 계단 수 i번째 자리에서 num으로 끝날 때 계단 수
 *  
 *  특히 답을 구할 때는 모든 끝나느 0 ~ 9 자리에 대한 경우의 수를 모두 더해야 한다.
 */
public class Baek10844 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int N = Integer.parseInt(br.readLine());

		// dp[i][num] 정의: i번째 자리에서 num으로 끝날 때 계단 수
		long[][] dp = new long[N + 1][10];

		// 첫 자리수의 가짓수
		for (int i = 1; i < 10; i++) {
			dp[1][i] = 1;
		}

		for (int i = 2; i <= N; i++) {
			// 1로 끝나는 경우는 예외
			dp[i][0] = dp[i - 1][1];

			for (int num = 1; num < 9; num++) {
				dp[i][num] = (dp[i - 1][num - 1] + dp[i - 1][num + 1]) % 1000000000;
			}

			// 9로 끝나느 경우는 예외
			dp[i][9] = dp[i - 1][8];
		}

		long ans = 0;
		for (int i = 0; i < 10; i++) {
			ans = (ans + dp[N][i]) % 1000000000;
		}

		System.out.println(ans);
	}

}
