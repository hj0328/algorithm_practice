package dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Baek11660 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        // dp bottom up
        int[][] dp = new int[N + 1][N + 1];
        for (int y = 1; y <= N; y++) {
            st = new StringTokenizer(br.readLine());
            int sub = 0;
            for (int x = 1; x <= N; x++) {
                int val = Integer.parseInt(st.nextToken());
                dp[y][x] = sub + dp[y - 1][x] + val;
                sub += val;
            }
        }


        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a1 = Integer.parseInt(st.nextToken());
            int a2 = Integer.parseInt(st.nextToken());
            int b1 = Integer.parseInt(st.nextToken());
            int b2 = Integer.parseInt(st.nextToken());

            sb.append(dp[b1][b2] - (dp[a1 - 1][b2] + dp[b1][a2 - 1]) + dp[a1 - 1][a2 - 1]).append("\n");
        }
        System.out.print(sb);
    }
}
