package dp.knapsack;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Baek1535 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        int[] lose = new int[N + 1];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            lose[i] = Integer.parseInt(st.nextToken());
        }

        int[] happy = new int[N + 1];
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            happy[i] = Integer.parseInt(st.nextToken());
        }

        int[][] dp = new int[N + 1][101]; // dp[i][] i번째 선택 또는 안하는 경우 고려시 최대 happy

        for (int i = 1; i <= N; i++) {
            for (int e = 0; e <= 100; e++) {
                dp[i][e] = dp[i-1][e];  // i번째 사람 선택 안함


                if (e - lose[i] > 0) {
                    dp[i][e] = Math.max(dp[i][e], dp[i - 1][e - lose[i]] + happy[i]);
                }
            }
        }

        int max = 0;
        for (int i = 1; i <= 100; i++) {
            if(max < dp[N][i]) max = dp[N][i];
        }

        System.out.println(max);
    }
}
