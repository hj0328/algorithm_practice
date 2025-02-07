package dp.knapsack;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 호텔
 * 골드4
 * https://www.acmicpc.net/problem/1106
 */
public class Baek1106 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        int C = Integer.parseInt(st.nextToken());   // 목표 고객 C명,    1 ~ 1000
        int N = Integer.parseInt(st.nextToken());   // 도시 수 N,       1 ~ 20

        int[] costs = new int[N + 1];
        int[] customer = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            costs[i] = Integer.parseInt(st.nextToken());
            customer[i] = Integer.parseInt(st.nextToken());
        }

        int[][] dp = new int[21][100001];

        for (int i = 1; i <= N; i++) { // 도시
            for (int p = 1; p <= 100000; p++) { // 비용
                dp[i][p] = dp[i - 1][p]; // i 번째 도시에서 투자를 안하는 경우

                // i 번째 도시까지 투자하는 경우
                if (p - costs[i] >= 0) {
                    // 이전 조합에, 도시 투자 1회 추가
                    dp[i][p] = Math.max(dp[i][p], dp[i][p - costs[i]] + customer[i]);
                }
            }
        }

        int ans = Integer.MAX_VALUE;
        for (int c = 1; c <= 100000; c++) {
            if(dp[N][c] >= C){
                ans = Math.min(ans, c);
                break;
            }
        }

        System.out.println(ans);
    }
}