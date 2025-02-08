package dp.knapsack;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Baek2629 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int gCount = Integer.parseInt(br.readLine());
        int[] gs = new int[gCount + 1];
        int[][] dp = new int[gCount + 1][45001];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= gCount; i++) {
            gs[i] = Integer.parseInt(st.nextToken());   // 추 1 ~ 500, 중복 가능
            dp[i][gs[i]] = 1;   // 추 하나로 만들 수 있는 경우 1 초기화
        }

        int ballCount = Integer.parseInt(br.readLine());
        int[] balls = new int[ballCount + 1];
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= ballCount; i++) {
            balls[i] = Integer.parseInt(st.nextToken()); // 구슬 1 ~ 40000
        }

        for (int i = 1; i <= gCount; i++) {
            for (int m = 1; m < 45001; m++) {
                dp[i][m] += dp[i - 1][m];   // 선택안하는 경우, 이전 경우의 수? 더하기.

                if(m - gs[i] >= 0){         // 추 선택하는 경우
                    dp[i][m] += dp[i - 1][m - gs[i]];
                }
//                System.out.print(dp[i][m] + " ");  일부 범위에 대해 계획한대로 동작하는지 확인
            }
//            System.out.println();
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= ballCount; i++) {
            boolean balanced = false;

            // 추, 구슬 같이 두는 경우
            for (int m = 1; m < 45001; m++) {
                if (dp[gCount][m] > 0 && m - balls[i] >= 0 && dp[gCount][m - balls[i]] > 0) {
                    balanced = true;
                    break;
                }
            }

            // 구슬만 한쪽에 두는 경우
            if(dp[gCount][balls[i]] > 0) balanced = true;

            if (balanced) {
                sb.append("Y ") ;
            } else {
                sb.append("N ");
            }
        }

        System.out.println(sb.toString());
    }
}