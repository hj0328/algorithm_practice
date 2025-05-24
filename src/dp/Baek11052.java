package dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
    i를 하나씩 모든 조합으로 계산하는건 매우 시간이 오래 걸림. 특히 중복 계산이 존재
    i가 6이라면, 3 + (3), 3 + (2 + 1), 3 + (1 + 1 + 1) 여기서 3을 만드는데 계산을 여러번 하는데, 이 작업이 반복함
    그래서 경우의 수 결과를 DP에 저장해서 반복해서 계산하는 경우를 줄임

    dp[i]: 카드 i개 뽑는데 필요한 최대 비용
    dp[i] = 모든 카드에 대해 dp[i-c] + cost[c]
 */
public class Baek11052 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] cost = new int[N + 1];
        for(int i = 1 ; i <= N ; i++) {
            cost[i] = Integer.parseInt(st.nextToken());
        }

        int[] dp = new int[N + 1]; // dp[i]: 카드 i 개 뽑는데 필요한 최대 비용

        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= i; j++) {
                dp[i] = Math.max(dp[i], dp[i - j] + cost[j]);
            }
        }
        System.out.println(dp[N]);
    }
}
