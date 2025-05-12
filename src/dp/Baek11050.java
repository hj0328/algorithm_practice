package dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Baek11050 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        long[][] dp = new long[N + 1][N + 1];
        for (int i = 0; i <= N; i++) {
            dp[i][0] = 1;
            dp[i][i] = 1;
        }

        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= K; j++) {
                dp[i][j] = dp[i - 1][j - 1] + dp[i - 1][j];
//                System.out.print(dp[i][j] + " ");
            }
//            System.out.println();
        }

        System.out.println(dp[N][K]);
    }
}
