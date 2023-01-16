package dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * 	dp[i]: i일자에 받을 수 있는 최대 금액. 
 * 	i일자에 일을 했든 안했든 상관없이 받을 수 있는 최대 금액이다. 
 */
public class Baek15486 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int N = Integer.parseInt(br.readLine());
		int[] tArr = new int[N + 1];
		long[] pArr = new long[N + 1];
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());

			tArr[i] = Integer.parseInt(st.nextToken());
			pArr[i] = Long.parseLong(st.nextToken());
		}

		long[] dp = new long[N + 1];

		for (int i = 0; i <= N; i++) {
			if (i != 0) {
				dp[i] = Math.max(dp[i], dp[i - 1]);
			}

			// i일자에 일해서 i + tArr[i] 일자에 받을 수 있는 최대 금액 조사
			if (i + tArr[i] <= N) {
				dp[i + tArr[i]] = Math.max(dp[i + tArr[i]], dp[i] + pArr[i]);
			}
		}

		System.out.println(dp[N]);
	}

}
