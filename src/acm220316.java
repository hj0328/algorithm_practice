import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 스티커
 * https://www.acmicpc.net/problem/9465
 *
 * 전형적인 DP 문제
 * 1. 이전에 계산한 문제를 저장해두고 다음 문제에서 재계산 없이 사용
 * 2. 값의 최대값을 원함 (꼭 DP만의 특징은 아니지만 DP 문제임을 의심해볼 수 있음)
 *
 */
public class acm220316 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        for (int t = 0; t < T; t++) {
            int n = Integer.parseInt(br.readLine());
            int[][] dp = new int[3][n];
            int[][] sticker = new int[2][n];

            for (int i = 0; i < 2; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                for (int j = 0; j < n; j++) {
                    sticker[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            dp[2][0] = sticker[0][0];
            dp[1][0] = sticker[1][0];

            for (int i = 1; i < n; i++) {
                dp[0][i] = Math.max(dp[0][i-1], dp[1][i-1]);
                dp[0][i] = Math.max(dp[0][i], dp[2][i-1]);

                dp[1][i] = Math.max(dp[0][i-1], dp[2][i-1]) + sticker[1][i];

                dp[2][i] = Math.max(dp[0][i-1], dp[1][i-1]) + sticker[0][i];
                dp[2][i] = Math.max(dp[0][i-1], dp[1][i-1]) + sticker[0][i];
            }

            int ans = Math.max(dp[0][n-1], dp[1][n-1]);
            ans = Math.max(ans, dp[2][n-1]);
            System.out.println(ans);
        }
    }
}
