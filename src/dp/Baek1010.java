package dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
    다리놓기
    서쪽에 N개, 동쪽에 M 지역이 있다.
    N개는 무조건 선택해 M 지역에 다리를 이은다.
    다리는 서로 겹치면 안된다.
    dp[M][N] = M개 지역과 N 번쨰 지역 연결 시 경우의 수 
 */
public class Baek1010 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());

        for (int t = 0; t < T; t++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            int M = Integer.parseInt(st.nextToken());

            // M개 중에 N개를 선택
            long[][] dp = new long[M + 1][N + 1];

            // i개 중 1개를 선택하는 경우 = i가지
            for (int i = 1; i <= M; i++) {
                dp[i][1] = i;
            }

            // M개중 N개 선택 = M-1개에서 N번째를 선택하거나 선택안하는 경우의 합
            for (int j = 2; j <= M; j++) {
                for (int i = 2; i <= N; i++) {
                    dp[j][i] = dp[j-1][i] + dp[j-1][i-1];
                }
            }

            System.out.println(dp[M][N]);
        }

    }
}
