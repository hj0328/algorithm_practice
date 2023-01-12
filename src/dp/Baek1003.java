package dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 *  피보나치 함수
 *  기본적인 DP 문제
 */
public class Baek1003 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());
		int TC = Integer.parseInt(st.nextToken());

		int[][] d = new int[41][41];
		d[0][0] = 1;
		d[0][1] = 0;
		d[1][0] = 0;
		d[1][1] = 1;
		for (int i = 2; i <= 40; i++) {
			d[i][0] = d[i - 1][0] + d[i - 2][0];
			d[i][1] = d[i - 1][1] + d[i - 2][1];
		}

		StringBuilder ans = new StringBuilder();
		for (int tc = 0; tc < TC; tc++) {
			int N = Integer.parseInt(br.readLine());
			ans.append(d[N][0]).append(" ").append(d[N][1]).append("\n");
		}
		
		System.out.print(ans.toString());
	}

}
