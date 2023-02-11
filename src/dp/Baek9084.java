package dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * 	동전	
 * 	 dp의 경우의 수 문제 
 * 	 dp[i]가 의미하는 경우의 수를 어떻게 정의할 것인지 찾기
 * 	 dp 점화식 세우는 것이 어려웠던 문제 
 * 
 * 	 dp[i]: 모든 코인으로 i숫자를 만들기 위한 경우의 수
 * 	 dp[i-coin(x)]: coin(x)를 사용하여 'i-coin(x)'를 만들기 위한 경우의 수 
 * 	
 */
public class Baek9084 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int TC = Integer.parseInt(br.readLine());

		for (int tc = 0; tc < TC; tc++) {
			int N = Integer.parseInt(br.readLine());
			StringTokenizer st = new StringTokenizer(br.readLine());

			int[] costs = new int[N];
			for (int i = 0; i < N; i++) {
				costs[i] = Integer.parseInt(st.nextToken());
			}

			int totalCost = Integer.parseInt(br.readLine());

			// dp[i]: 주어진 동전으로 i 비용을 만드는 경우의 수
			int[] dp = new int[totalCost + 1];
			
			dp[0] = 1;
			for (int i = 0; i < N; i++) {
				for (int c = 0; c <= totalCost; c++) {
					if (c - costs[i] < 0) {
						continue;
					}

					dp[c] += dp[c - costs[i]];
				}
			}

			System.out.println(dp[totalCost]);
		}
	}

}
