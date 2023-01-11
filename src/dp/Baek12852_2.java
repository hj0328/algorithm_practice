package dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Baek12852_2 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int N = Integer.parseInt(br.readLine());

		// dp[i]: i가 1이 되는데 최소 과정
		int[] dp = new int[N + 4];

		// pre[i]: i가 1이 되기 위한 최소 과정 중 첫 번째로 변한 숫자
		int[] pre = new int[N + 4];

		dp[1] = 0;
		dp[2] = 1;
		pre[2] = 1;
		dp[3] = 1;
		pre[3] = 1;

		for (int i = 4; i <= N; i++) {
			int selectedNum = 0;
			int count = Integer.MAX_VALUE;

			// i가 2로 나누어 지는 경우
			if (i % 2 == 0) {
				int d2 = dp[i / 2];
				count = d2;
				selectedNum = i / 2;
			}

			// i가 3으로 나누어 지는 경우
			if (i % 3 == 0) {
				int d3 = dp[i / 3];
				if (d3 < count) {
					count = d3;
					selectedNum = i / 3;
				}
			}

			// i에 1을 빼는 경우
			int d1 = dp[i - 1];
			if (d1 < count) {
				count = d1;
				selectedNum = i - 1;
			}

			dp[i] = count + 1;
			pre[i] = selectedNum;
		}

		StringBuilder sb = new StringBuilder();
		int target = N;
		while (target != 0) {
			sb.append(target).append(" ");
			target = pre[target];
		}

		System.out.println(dp[N]);
		System.out.println(sb.toString());
	}

}
