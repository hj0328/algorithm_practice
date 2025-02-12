package dp.knapsack;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Baek2662 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int totalInvest = Integer.parseInt(st.nextToken()); // 1 ~ 300
        int companys = Integer.parseInt(st.nextToken());    // 1 ~ 20

        int[][] revenue = new int[companys + 1][301];  // 회사의 투자 비용에 따른 수익
        int[] costs = new int[totalInvest + 1];
        for (int i = 1; i <= totalInvest; i++) {
            st = new StringTokenizer(br.readLine());
            int cost = Integer.parseInt(st.nextToken()); // 1 ~ 300
            costs[i] = cost;
            for (int j = 1; j <= companys; j++) {
                int r = Integer.parseInt(st.nextToken());
                revenue[j][i] = r;
            }
        }


        // invent[i][m] i기업에 대해 총 m 금액이 있을 때, 직접에 투자한 금액
        int[][] invest = new int[companys + 1][301];
        int[][] dp = new int[companys + 1][301];
        for (int i = 1; i <= companys; i++) {   // i 회사
            for (int p = 1; p <= totalInvest; p++) {    // p 금액 투자
                dp[i][p] = dp[i - 1][p];

                for (int p2 = 1; p2 <= p; p2++) { // i 번 기업에 투자할 금액
                    if(dp[i][p] < dp[i-1][p-p2] + revenue[i][p2]) {
                        dp[i][p] = dp[i-1][p-p2] + revenue[i][p2];
                        invest[i][p] = p2;
                    }
//                    dp[i][p] = Math.max(dp[i][p], dp[i - 1][p2] + revenue[i][p]);
                }
//                System.out.print(dp[i][p] + " ");

            }
//            System.out.println();
        }

        System.out.println(dp[companys][totalInvest]);


        int total = totalInvest;
        LinkedList<Integer> list = new LinkedList<>();
        for (int c = companys; c > 0; c--) {
//            System.out.println(invest[c][total]);
            list.addFirst(invest[c][total]);
            total -= invest[c][total];
        }

        StringBuilder sb = new StringBuilder();
        for (int c : list) {
            sb.append(c).append(" ");
        }
        System.out.println(sb);
    }
}
