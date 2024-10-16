package dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Baek12865_2 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] split = br.readLine().split(" ");
        int N = Integer.parseInt(split[0]);
        int K = Integer.parseInt(split[1]);

        int[][] dp = new int[N + 1][K + 1];
        for (int i = 1; i <= N; i++) {
            split = br.readLine().split(" ");
            int w = Integer.parseInt(split[0]);
            int v = Integer.parseInt(split[1]);

            for (int k = 0; k <= K; k++) {
                dp[i][k] = Math.max(dp[i-1][k], dp[i][k]);

                if (k - w >= 0) {
                    dp[i][k] = Math.max(dp[i-1][k - w] + v, dp[i][k]);
                }
            }
        }

        System.out.println(dp[N][K]);
    }
}
