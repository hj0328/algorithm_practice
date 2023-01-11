package dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 *  dp 구간 합 
 */
public class Baek11659 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());

		long[] sum = new long[N + 1];
		st = new StringTokenizer(br.readLine());

		sum[1] = Long.parseLong(st.nextToken());
		for (int i = 2; i <= N; i++) {
			sum[i] = sum[i - 1] + Long.parseLong(st.nextToken());
		}

		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int s = Integer.parseInt(st.nextToken());
			int e = Integer.parseInt(st.nextToken());

			sb.append(sum[e] - sum[s - 1]).append("\n");
		}
		System.out.print(sb.toString());
	}

}
