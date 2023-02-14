package dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * 	평범한 배낭
 * 	냅샙 문제 
 * 
 * 	특징
 * 		물건은 한 번만 배낭에 넣을 수 있다.
 * 		표로 그려보기
 * 
 * 	나중에 다시 풀어보기
 */
public class Baek12865 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int kg = Integer.parseInt(st.nextToken());

		int[][] stuff = new int[N][2];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			stuff[i][0] = Integer.parseInt(st.nextToken());
			stuff[i][1] = Integer.parseInt(st.nextToken());
		}

		// dp[k]: k무게를 가방에 넣었을 때 최대 가치
		// 물건이 중복으로 가방에 들어가면 안돼.. 어떻게 중복이 안되게 하지?

		// dp[kg][n번째 물건]: kg 무게가 들어있는 가방에 n번째 물건을 넣을지 고려중일 때, 최대 가치
		int[][] dp = new int[kg + 1][N + 1];

		for (int k = stuff[0][0]; k <= kg; k++) {
			dp[k][0] = stuff[0][1];
		}
		for (int k = 1; k <= kg; k++) {
			for (int i = 1; i < N; i++) {
				if (k - stuff[i][0] < 0) {
					dp[k][i] = dp[k][i - 1];
					continue;
				}

				dp[k][i] = Math.max(dp[k][i - 1], dp[k - stuff[i][0]][i - 1] + stuff[i][1]);
			}
		}

		System.out.println(dp[kg][N - 1]);
	}

}
