package dp.knapsack;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Baek1106_2 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int C = Integer.parseInt(st.nextToken()); // 최소 고객 수
        int N = Integer.parseInt(st.nextToken()); // 도시의 수

        // DP 배열의 크기를 C + 101로 설정 (C 이상을 채울 수 있도록)
        int MAX = C + 101;
        int INF = 1000000000; // 충분히 큰 수로 초기화
        int[] dp = new int[MAX];    // dp[인원]= '인원'만큼 늘리기 위한 최소 비용
        Arrays.fill(dp, INF);
        dp[0] = 0;

        // 각 도시의 광고 비용과 고객 수 입력
        int[][] city = new int[N][2];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int cost = Integer.parseInt(st.nextToken());
            int cust = Integer.parseInt(st.nextToken());
            city[i][0] = cost;
            city[i][1] = cust;
        }

        // DP 진행: 고객 수 0부터 MAX-1까지 탐색
        for (int i = 0; i < MAX; i++) { // i 인원
            for (int j = 0; j < N; j++) { // 모든 도시에 대해
                int cost = city[j][0];
                int cust = city[j][1];
                // 고객 수 i에서 광고를 진행하여 고객 수를 증가시킴
                int next = i + cust;
                if (next >= MAX) next = MAX - 1;
                dp[next] = Math.min(dp[next], dp[i] + cost);
            }
        }

        // dp[C]가 최소 고객 C 이상을 유치하는 데 드는 최소 비용입니다.
        int min = Integer.MAX_VALUE;
        for (int c = C; c < MAX; c++) {
            if(min > dp[c]) min = dp[c];
        }

        System.out.println(min);
    }
}
