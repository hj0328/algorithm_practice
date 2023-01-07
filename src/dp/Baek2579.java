package dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/*
 * 계단 오르기 
 * 
 * 	dp 점화식까지 모두 했지만 배열 인덱스 런타임 에러 발생 
 * 	하드코드로 넣는 값 부분에서 발생
 * 	가장 초기 값을 작게줬을 경우 dp[3]의 경우 에러가 발생가능하다. 
 * 	그렇기 때문에 배열 크기를 기준보다 4 더 크게 생성
 * 
 */
public class Baek2579 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int totalCount = Integer.parseInt(br.readLine());

		int[] scores = new int[totalCount + 4];
		for (int i = 1; i <= totalCount; i++) {
			scores[i] = Integer.parseInt(br.readLine());
		}

		int[] dp = new int[totalCount + 4];
		dp[1] = scores[1];
		dp[2] = scores[1] + scores[2];
		dp[3] = scores[3] + Math.max(scores[1], scores[2]);

		for (int i = 4; i <= totalCount; i++) {
			dp[i] = scores[i] + Math.max(dp[i - 2], dp[i - 3] + scores[i - 1]);
		}

		System.out.println(dp[totalCount]);
	}

}
