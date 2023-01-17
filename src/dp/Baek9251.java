package dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/*
 * 	DP의 LCS 길이 구하기
 * 
 *  틀렸던 케이스 
 *   - 두 문자열의 같은 두 알파벳을 찾았을 때, (i+1, j)와 (i, j+1)에 값을 추가했다.
 *     왜냐하면 찾은 알파벳이 같은경우가 아닐 수 있기 때문이다. 
 *     하지만 이 경우는 i와 j 번째가 다른 경우에서 고려되기 때문에 중복으로 처리할 필요가 없다.
 */
public class Baek9251 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		char[] first = br.readLine().toCharArray();
		char[] second = br.readLine().toCharArray();

		int fLen = first.length;
		int sLen = second.length;

		long[][] dp = new long[fLen + 2][sLen + 2];
		long max = 0;
		for (int i = 0; i < fLen; i++) {
			for (int j = 0; j < sLen; j++) {
				if (first[i] == second[j]) {
					// 같은 부분 문자를 발견 시 dp(i+1, j+1)에만 영향을 주면 됨
					dp[i][j]++;
					dp[i + 1][j + 1] = Math.max(dp[i + 1][j + 1], dp[i][j]);
				} else {
					// 다른 경우 어디에 같은 문자가 시작하는지 모르기 때문에 각각 +1 하는 두 가지 경우 모두 검사
					dp[i][j + 1] = Math.max(dp[i][j + 1], dp[i][j]);
					dp[i + 1][j] = Math.max(dp[i + 1][j], dp[i][j]);
				}

				if (max < dp[i][j]) {
					max = dp[i][j];
				}
			}
		}

		System.out.println(max);
	}

}
