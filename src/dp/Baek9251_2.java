package dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/*
 * 	LCS
 * 	
 */
public class Baek9251_2 {

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		char[] arr1 = br.readLine().toCharArray();
		char[] arr2 = br.readLine().toCharArray();

		int[][] dp = new int[arr1.length + 2][arr2.length + 2];

		for (int i = 0; i <= arr1.length; i++) {
			for (int j = 0; j <= arr2.length; j++) {
				if (i < arr1.length && j < arr2.length && arr1[i] == arr2[j]) {
					// 값이 같은 경우, [i+1][j+1]에 +1하여 추가
					dp[i + 1][j + 1] = dp[i][j] + 1;
				} else {
					// 값이 다른 경우, [i][j+1] 와 [i+1][j]에 추가
					dp[i][j + 1] = Math.max(dp[i][j + 1], dp[i][j]);
					dp[i + 1][j] = Math.max(dp[i + 1][j], dp[i][j]);
				}
			}
		}

		System.out.println(dp[arr1.length][arr2.length]);
	}

}
