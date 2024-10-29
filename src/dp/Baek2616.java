package dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Baek2616 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] arr = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        int K = Integer.parseInt(br.readLine());
        int[][] dp = new int[N + 1][4];

        for (int i = K; i <= N; i++) {
            for (int t = 1; t <= 3; t++) {
                // i 포함 k개 이전
                int pre = i - K + 1;

                // i를 마지막으로 하는 칸을 선택하지 않는 경우
                dp[i][t] = Math.max(dp[i][t], dp[i-1][t]);
                if (pre >= 1) {
                    // i - k + 1 부터 i 까지 k 개칸의 합
                    int val = 0;
                    for(int a = pre ; a <= i ; a++) {
                        val += arr[a];
                    }

                    // i를 마지막으로 하는 칸을 선택하는 경우
                    // pre 보다 1개 이전 dp 값을 가져와야 한다. pre는 현재 선택한 객차의 첫 번쨰 칸이기 떄문
                    dp[i][t] = Math.max(dp[i][t], dp[pre - 1][t - 1] + val);
                }
            }
        }

//        for (int i = 0; i <= N; i++) {
//            for (int t = 0; t <= 3; t++) {
//                System.out.print(dp[i][t] + " ");
//            }
//            System.out.println();
//        }


        System.out.println(dp[N][3]);
    }
}
