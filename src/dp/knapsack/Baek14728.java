package dp.knapsack;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Baek14728 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());   // 단원 수 1 ~ 100
        int T = Integer.parseInt(st.nextToken());   // 총 공부 시간 1 ~ 10000

        int[][] dp = new int[N + 1][T + 1];

        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            int t = Integer.parseInt(st.nextToken());
            int score = Integer.parseInt(st.nextToken());

            for (int time = 1; time <= T; time++) {
                dp[i][time] = dp[i-1][time];    // i 단원 선택을 안하는 경우

                // i 단원 선택하는 경우
                if (time - t >= 0) {
                    dp[i][time] = Math.max(dp[i][time], dp[i - 1][time - t] + score);
                }
            }
        }

        int max = 0;
        for (int time = 1; time <= T; time++) {
            if(max < dp[N][time]) max = dp[N][time];
        }
        System.out.println(max);
    }
}
