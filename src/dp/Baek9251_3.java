package dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Baek9251_3 {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        char[] arr1 = br.readLine().toCharArray();
        char[] arr2 = br.readLine().toCharArray();

        int len = Math.max(arr1.length, arr2.length);
        int[][] dp = new int[len][len];

        // 가장 처음 문자가 같은 경우 1을 세준다.
        if(arr1[0] == arr2[0]) {
            dp[0][0] = 1;
        }

        // arr2가 0번째 인경우 공식 처리
        for (int i = 1; i < arr1.length; i++) {
            if (arr1[i] == arr2[0]) {
                dp[i][0] = 1;
            } else {
                dp[i][0] = dp[i-1][0];
            }
        }

        // arr1이 0번째 인경우 공식 처리
        for (int i = 1; i < arr2.length; i++) {
            if (arr1[0] == arr2[i]) {
                dp[0][i] = 1;
            } else {
                dp[0][i] = dp[0][i-1];
            }
        }

        // arr1, arr2 각각 1부터 마지막까지 순환식 풀기
        for (int i = 1; i < arr1.length; i++) {
            for (int j = 1; j < arr2.length; j++) {
                if (arr1[i] == arr2[j]) {
                    dp[i][j] = Math.max(dp[i][j], dp[i - 1][j - 1] + 1);
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }

        System.out.println(dp[arr1.length - 1][arr2.length - 1]);
    }
}
