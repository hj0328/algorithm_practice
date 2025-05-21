package dp.knapsack;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Baek11727 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        var dp = new int[N + 3];
        dp[1] = 1;
        dp[2] = 3;
        dp[3] = dp[2] + dp[1] * 2;

        for (int i = 3; i <= N; i++) {
            dp[i] = (dp[i - 1] + (dp[i - 2] * 2) % 10007 ) % 10007;
        }

        System.out.println(dp[N]);
    }
}

/*


 */