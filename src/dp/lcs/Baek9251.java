package dp.lcs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/*
    LCS 기본 문제, gold 5

 */
public class Baek9251 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        char[] arr1 = br.readLine().toCharArray();
        char[] arr2 = br.readLine().toCharArray();

        // dp[i][j]: arr1에 i까지, arr2에 j까지 검사했을 때 LCS는?
        int[][] dp = new int[arr1.length + 1][arr2.length + 1];

        for (int i = 0; i < arr1.length; i++) {
            for (int j = 0; j < arr2.length; j++) {
                if (arr1[i] == arr2[j]) {
                    if(i > 0 && j > 0)
                        dp[i][j] = Math.max(dp[i][j], dp[i - 1][j - 1] + 1);
                    else
                        dp[i][j] = 1;
                } else {
                    if(i > 0) dp[i][j] = Math.max(dp[i][j], dp[i-1][j]);
                    if(j > 0) dp[i][j] = Math.max(dp[i][j], dp[i][j - 1]);
                }
            }
        }

        System.out.println(dp[arr1.length-1][arr2.length-1]);
    }
}
