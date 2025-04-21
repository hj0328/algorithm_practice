package dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 *  dp[i] = max(dp[i-1], dp[i]) 3번째 연속이 나니 i-1
 *
 *  dp[i][3]: i 번째 술에 대해, 연속 j 번째로 술을 마셨을때 최대 술
 *
 *  dp[i][0] = max(dp[i-1][0], dp[i-1][1], dp[i-1][2])
 *  dp[i][1] = arr[i] + max(dp[i-2][0], dp[i-2][1], dp[i-2][2])
 *  dp[i][2] = arr[i] + dp[i-1][1]
 *
 *  100 99 1
 */
public class Baek2156 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        int[] drink = new int[n];
        for (int i = 0; i < n; i++) {
            drink[i] = Integer.parseInt(br.readLine());
        }

        int[][] dp = new int[n][3];
        for (int i = 0; i < n; i++) {

            if(i > 0) {
                dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][1]);
                dp[i][0] = Math.max(dp[i][0], dp[i - 1][2]);
            }

            int val = 0;
            if(i > 1) {
                val = Math.max(dp[i - 2][0], dp[i - 2][1]);
                val = Math.max(val, dp[i - 2][2]);
            }
            dp[i][1] = drink[i] + val;

            if(i > 0)
                dp[i][2] = drink[i] + dp[i-1][1];
        }

        int ans = 0;
        for(int i = 0; i < 3 ; i++) {
            ans = Math.max(ans, dp[n - 1][i]);
        }
        System.out.println(ans);
    }
}
