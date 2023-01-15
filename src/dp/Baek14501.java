package dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * 	퇴사 
 * 	 dp 기본문제
 * 
 * 	dp[i] = Max(j=0-i-1) (dp[j], dp[i]) + Pi   
 */
public class Baek14501 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int N = Integer.parseInt(br.readLine());
		int[] tArr = new int[N];
		int[] pArr = new int[N];
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());

			tArr[i] = Integer.parseInt(st.nextToken());
			pArr[i] = Integer.parseInt(st.nextToken());
		}

		int[] dp = new int[N];
		for (int i = 0; i < N; i++) {
			// 기간 초과 확인
			if (i + tArr[i] - 1 >= N) {
				continue;
			}

			for (int j = 0; j < i; j++) {
				if (j + tArr[j] - 1 >= i) {
					continue;
				}

				dp[i] = Math.max(dp[i], dp[j]);
			}
			dp[i] += pArr[i];
		}

		int max = 0;
		for (int i = 0; i < N; i++) {
			if (max < dp[i]) {
				max = dp[i];
			}
		}

		System.out.println(max);
	}
}
