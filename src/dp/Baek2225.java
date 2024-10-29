package dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Baek2225 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        int[][] dp = new int[N + 1][K + 1];
        for (int i = 0; i <= N; i++) {
            //dp[i][0] = 1;
            dp[i][1] = 1;
        }

        for (int n = 0; n <= N; n++) {
            for (int k = 1; k <= K; k++) {

                for (int nn = 0; nn <= n; nn++) {
                    dp[n][k] += dp[n - nn][k-1];
                    dp[n][k] = dp[n][k] % 1000000000;
                }
            }
        }

        System.out.println(dp[0][1]);
        System.out.println(dp[N][K] % 1000000000);
    }
}
