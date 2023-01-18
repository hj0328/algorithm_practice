package dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/*
 * 	제곱수의 합
 * 
 * 	dp 응용문제. 
 * 	dp 문제인줄 모르고 풀었다면 greedy로 풀었을 문제다. 
 * 
 *  greedy로 접근한다면 N이하의 수 중 가장 큰 제곱 수를 찾고 나머지 수에 대해서 제곱수의 개수를 찾게 된다. 
 *  하지만 N이 12인 경우에 가장 큰 제곱 수 9가 아니라 4일 때 최소 제곱수의 개수를 갖게됨을 알 수 있다. 
 *  따라서 greedy 문제가 아니다.
 *  
 *  그래서 N보다 작은 모든 제곱수에 대해서 루프를 돌며 찾아야 하며, 
 *  이 과정에서 중복된 계산 값을 다시 찾아야하기 때문에 dp 문제로 접근해야 한다.
 */
public class Baek1699 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int N = Integer.parseInt(br.readLine());

		int[] dp = new int[N + 1];
		// 모든 항이 기본적으로 1 제곱으로 표현이 가능하기 때문에 1로 초기화
		for (int i = 1; i <= N; i++) {
			dp[i] = i;
		}

		for (int i = 1; i <= N; i++) {
			int q = (int) Math.sqrt(i);
			for (int j = 0; j <= q; j++) {
				dp[i] = Math.min(dp[i], dp[i - j * j] + 1);
			}
		}

		System.out.println(dp[N]);
	}

}
