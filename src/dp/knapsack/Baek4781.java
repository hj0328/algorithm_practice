package dp.knapsack;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 *  사탕가게
 *  https://www.acmicpc.net/problem/4781
 *
 *  완전배낭문제 (물건을 무한히 선택가능)
 */
public class Baek4781 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        while (true) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken()); // 사탕종료 1 ~ 5000
            String[] split = st.nextToken().split("\\.");
            int M = Integer.parseInt(split[0]) * 100 + Integer.parseInt(split[1]);   // 갖고있는 돈, 1 ~ 10000
            if (N == 0 && M == 0) {
                break;
            }

            int[] cals = new int[N + 1];
            int[] prices = new int[N + 1];
            for (int i = 1; i <= N; i++) {
                st = new StringTokenizer(br.readLine());
                cals[i] = Integer.parseInt(st.nextToken());
                split = st.nextToken().split("\\.");        // 소수를 정수로 변경. 단순 100곱하기는 오차발생
                prices[i] = Integer.parseInt(split[0]) * 100 + Integer.parseInt(split[1]);
            }

            int[] dp = new int[M + 1];  // M 원으로 살 수 있는 사탕의 최대 칼로리
            for (int i = 1; i <= N; i++) {
                for (int m = 1; m <= M; m++) { // 살 수 있는 모든 가격에 대해 사탕을 구매해보는 조합
                    if (m - prices[i] >= 0) {
                        dp[m] = Math.max(dp[m], dp[m - prices[i]] + cals[i]);
                    }
                }
            }
            System.out.println(dp[M]);
        }
    }
}
