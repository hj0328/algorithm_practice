package dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/*
 *  암호코드 백준 2011번
 *  	수열을 변경 가능한 알파벳으로 바꾸는 경우의 수 구하기
 *  	변경 불가능한 잘못된 수열이라면 0출력
 *  
 *  특징 
 *  	1 수열이기 때문에 001과 같은 숫자가 들어올 수 있다. 
 *  	여러 예제를 테스트해봐서 코너케이스를 미리 알 수 있어서 다행이었다. 
 *  
 *   	2 문자, 숫자간에 변경을 잘못했다. 
 *   
 *   	3 배열을 패딩하면 더 깔끔한 코드로 변경 가능했다. 
 *   
 *   다시 풀어보기!
 */
public class Baek2011 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		char[] charArray = br.readLine().toCharArray();
		int len = charArray.length;

		// dp[i]: i번 째까지 탐색했을 때 해석가능한 가짓수
		int[] dp = new int[len + 1];

		if (len > 0 && charArray[0] != '0') {
			dp[0] = 1;
		}

		if (len > 1 && charArray[0] != '0' && charArray[1] != '0') {
			dp[1] = 1;
		}

		if (len > 1 && ((charArray[0] - '0') * 10 + (charArray[1] - '0')) <= 26) {
			dp[1] = dp[1] + dp[0];
		}

		for (int i = 2; i < len; i++) {

			// char c1 = Character.forDigit(charArray[i - 2], 10);
			int c2 = charArray[i - 1] - '0';
			int c3 = charArray[i] - '0';

			// i - 1, i 문자가 정상이면 dp[i-2] 까지의 방법들을 받는다.
			if (10 <= c2 * 10 + c3 && c2 * 10 + c3 <= 26) {
				dp[i] = (dp[i] + dp[i - 2]) % 1000000;
			}

			// i가 정상이면 dp[i-1] 까지의 방법들을 받는다.
			if (c3 != 0) {
				dp[i] = (dp[i] + dp[i - 1]) % 1000000;
			}
		}

		System.out.println(dp[len - 1]);

	}

}
