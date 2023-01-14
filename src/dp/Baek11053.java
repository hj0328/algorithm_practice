package dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 *	가장 긴 증가하는 부분 수열
 *	수열 A가 주어졌을 때, 가장 긴 증가하는 부분 수열 구하기
 *	수열 A = {10, 20, 10, 30, 20, 50} 인 경우에 가장 긴 증가하는 부분 수열은 A = {10, 20, 10, 30, 20, 50} 이고, 길이는 4
 *
 * 	핵심 
 * 		DP의 LIS 알고리즘 
 */
public class Baek11053 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int N = Integer.parseInt(br.readLine());

		StringTokenizer st = new StringTokenizer(br.readLine());
		int[] arr = new int[N];
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}

		// dp[i]: i로 끝나는 부분 수열 중 가장 긴 길이
		int[] dp = new int[N];

		for (int i = 0; i < N; i++) {
			// i보다 작은 모든 원소 j에 대해
			for (int j = 0; j < i; j++) {
				if (arr[i] <= arr[j]) {
					continue;
				}

				if (dp[i] < dp[j]) {
					dp[i] = dp[j];
				}
			}
			dp[i]++;
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
