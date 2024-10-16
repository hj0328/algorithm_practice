package dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Baek7579 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int[] mArr = new int[N + 1];
        int[] cArr = new int[N + 1];

        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            mArr[i] = Integer.parseInt(st.nextToken());
        }
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            cArr[i] = Integer.parseInt(st.nextToken());
        }

        // dp[i][c]: i번째까지 선택을 고려했을때 c 비용을 사용했을 때, 최대 확보 메모리
        long[][] dp = new long[N + 1][10001];
        for (int i = 1; i <= N; i++) {

            for (int c = 0; c < 10001; c++) {
                dp[i][c] = Math.max(dp[i - 1][c], dp[i][c]);

                if (c >= cArr[i]) {
                    dp[i][c] = Math.max(dp[i - 1][c - cArr[i]] + mArr[i], dp[i][c]);
                }
            }
        }

        // M을 확보하는 경우 중 최소 비용 c를 구하기
        int minC = Integer.MAX_VALUE;
        for (int c = 0; c < 10001; c++) {
            if (dp[N][c] >= M && minC > c) {
                minC = c;
            }
        }

        System.out.println(minC);
    }
}
