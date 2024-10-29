package dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Baek2293 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        int[] coin = new int[N];
        for (int i = 0; i < N; i++) {
            int val = Integer.parseInt(br.readLine());
            coin[i] = val;
        }

        int[] dp = new int[K + 1];
        dp[0] = 1;
        for (int c = 0; c < N; c++) {
            for (int k = 1; k <= K; k++) {
                if (k - coin[c] < 0) {
                    continue;
                }
                dp[k] += dp[k-coin[c]];
            }
        }

        System.out.println(dp[K]);
    }
}
