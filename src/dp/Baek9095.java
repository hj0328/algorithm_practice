package dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/*
 * 1, 2, 3 더하기 
 * 
 * 	주어진 N 숫자를 만드는데 1, 2, 3을 만드는데 필요한 경우의 수를 구하기
 */
public class Baek9095 {

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int TC = Integer.parseInt(br.readLine());
		long[] dp = new long[11];
		dp[1] = 1;
		dp[2] = 2;
		dp[3] = 4;
		for (int tc = 0; tc < TC; tc++) {
			int N = Integer.parseInt(br.readLine());

			for (int i = 4; i <= N; i++) {
				dp[i] = dp[i - 2] + dp[i - 3] + dp[i - 1];
			}

			System.out.println(dp[N]);
		}

	}

}
