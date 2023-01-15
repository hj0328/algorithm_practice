package dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/*
 *  dp 기본 문제 
 *  
 *   문제 내용이 낯설 수 있지만 예제를 하나하나 해보면 dp 정의 규칙이 보인다. 
 */
public class Baek9461 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		long[] dp = new long[101];
		dp[1] = 1;
		dp[2] = 1;
		dp[3] = 1;
		dp[4] = 2;
		dp[5] = 2;
		dp[6] = 3;
		dp[7] = 4;
		dp[8] = 5;
		dp[9] = 7;
		for (int i = 10; i <= 100; i++) {
			dp[i] = dp[i - 1] + dp[i - 5];
		}

		int TC = Integer.parseInt(br.readLine());

		for (int i = 0; i < TC; i++) {
			int n = Integer.parseInt(br.readLine());
			System.out.println(dp[n]);
		}
	}

}
